using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;
using Microsoft.Azure.WebJobs;
using Microsoft.Extensions.Logging;

namespace ParkingService
{
    class Program
    {
        static void Main(string[] args)
        {
            Console.WriteLine("WebJob started");

            JobHostConfiguration config = new JobHostConfiguration();
            ConfigureJobHost(config);
            JobHost host = new JobHost(config);
            
            var cancellationToken = new WebJobsShutdownWatcher().Token;
            cancellationToken.Register(() =>
            {
                // Handle any shutdown operations here
                config.LoggerFactory.Dispose();
                host.Stop();
            });


            host.RunAndBlock();
            Console.WriteLine("WebJob stopped");
        }

        private static void ConfigureJobHost(JobHostConfiguration config)
        {
            var log = new LoggerFactory();
            config.UseServiceBus();
            config.DashboardConnectionString = "";
            var loggerFactory = log.AddConsole();
            config.LoggerFactory = loggerFactory;
        }
    }
}
