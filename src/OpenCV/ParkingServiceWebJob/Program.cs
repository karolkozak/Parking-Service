using Microsoft.Azure.WebJobs;
using Microsoft.Extensions.Logging;
using System;

namespace ParkingServiceWebJob
{
    // To learn more about Microsoft Azure WebJobs SDK, please see https://go.microsoft.com/fwlink/?LinkID=320976
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
