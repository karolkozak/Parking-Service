using System.Threading.Tasks;
using Microsoft.Azure.WebJobs;
using Microsoft.Extensions.Logging;
using ParkingService.Models;

namespace ParkingService
{
    public class PlateReader
    {
        public async static Task ProcessPhotos([ServiceBusTrigger("plates"/*, Connection = "ServiceBus"*/)]
            string request, ILogger logger)
        {
            logger.LogInformation("Successfully finished " + request);
        }
    }
}
