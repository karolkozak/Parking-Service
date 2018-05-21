using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;
using Emgu.CV;
using Emgu.CV.Structure;
using Microsoft.Azure.WebJobs;
using Microsoft.Extensions.Logging;
using ParkingService.Services;

namespace ParkingService
{
    class Program
    {
        //static void Main(string[] args)
        //{
        //    Console.WriteLine("WebJob started");

        //    JobHostConfiguration config = new JobHostConfiguration();
        //    ConfigureJobHost(config);
        //    JobHost host = new JobHost(config);
            
        //    var cancellationToken = new WebJobsShutdownWatcher().Token;
        //    cancellationToken.Register(() =>
        //    {
        //        // Handle any shutdown operations here
        //        config.LoggerFactory.Dispose();
        //        host.Stop();
        //    });


        //    host.RunAndBlock();
        //    Console.WriteLine("WebJob stopped");
        //}

        private static void ConfigureJobHost(JobHostConfiguration config)
        {
            var log = new LoggerFactory();
            config.UseServiceBus();
            config.DashboardConnectionString = "";
            var loggerFactory = log.AddConsole();
            config.LoggerFactory = loggerFactory;
        }

        private static List<string> ProcessImage(IInputOutputArray image, LicensePlateDetector licensePlateDetector)
        {

            List<IInputOutputArray> licensePlateImagesList = new List<IInputOutputArray>();
            List<IInputOutputArray> filteredLicensePlateImagesList = new List<IInputOutputArray>();
            List<RotatedRect> licenseBoxList = new List<RotatedRect>();
            return licensePlateDetector.DetectLicensePlate(
                image,
                licensePlateImagesList,
                filteredLicensePlateImagesList,
                licenseBoxList);
        }
        static void Main(string[] args)
        {
            LicensePlateDetector licensePlateDetector = new LicensePlateDetector("");
            Mat m = new Mat("license-plate.png");
            //UMat um = m.GetUMat(AccessType.ReadWrite);
            var words = ProcessImage(m, licensePlateDetector);

            foreach (var word in words)
            {
                Console.WriteLine(word);
            }

            Console.ReadKey();
        }
    }
}
