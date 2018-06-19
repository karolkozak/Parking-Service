using Microsoft.Azure.WebJobs;
using Microsoft.Extensions.Logging;
using Microsoft.ServiceBus.Messaging;
using Microsoft.WindowsAzure.Storage.Blob;
using Newtonsoft.Json;
using ParkingService;
using System.IO;
using System.Linq;
using System.Configuration;
using System.Threading.Tasks;
using ParkingService.Services;

namespace ParkingServiceWebJob
{
    public class PhotosJob
    {
        [Singleton]
        public async static Task ProcessPhoto([ServiceBusTrigger("plates"/*, Connection = "ServiceBus"*/)]
            string message, [Blob("license-plates")] CloudBlobContainer container, ILogger logger)
        {
            var request = JsonConvert.DeserializeObject<MessageModel>(message);
            logger.LogInformation("Starting " + request.Filename);
            var blockBlob = container.GetBlockBlobReference(request.Filename);
            var path = "./" + request.Filename;
            await blockBlob.DownloadToFileAsync(path, System.IO.FileMode.Create);

            var result = ImageProcessor.ProcessImageFilename(path);

            var id = request.Filename;

            foreach (var word in result)
            {
                logger.LogInformation(word);
            }

            var dbFacadeUrl = ConfigurationManager.AppSettings["DbFacadeUrl"];

            RequestManager requestManager = new RequestManager();
            await requestManager.UpdateAsync(dbFacadeUrl + "/plates/" + id, result.First());

            File.Delete(path);
            logger.LogInformation("Successfully finished " + request.Filename);
            
        }
    }
}
