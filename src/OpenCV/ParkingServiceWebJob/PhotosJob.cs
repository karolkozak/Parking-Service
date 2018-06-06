using Microsoft.Azure.WebJobs;
using Microsoft.Extensions.Logging;
using Microsoft.WindowsAzure.Storage.Blob;
using Newtonsoft.Json;
using ParkingService;
using System.Threading.Tasks;

namespace ParkingServiceWebJob
{
    public class PhotosJob
    {
        public async static Task ProcessPhoto([ServiceBusTrigger("plates"/*, Connection = "ServiceBus"*/)]
            string message, [Blob("license-plates")] CloudBlobContainer container, ILogger logger)
        {

            var request = JsonConvert.DeserializeObject<MessageModel>(message);

            var blockBlob = container.GetBlockBlobReference(request.Filename);

            await blockBlob.DownloadToFileAsync(request.Filename, System.IO.FileMode.Create);

            ImageProcessor.ProcessImageFilename(request.Filename);

            logger.LogInformation("Successfully finished " + request.Filename);
            
        }
    }
}
