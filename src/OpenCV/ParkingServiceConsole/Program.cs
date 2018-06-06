using ParkingService;
using System;

namespace ParkingServiceConsole
{
    class Program
    {
        static void Main(string[] args)
        {
            var words = ImageProcessor.ProcessImageFilename("license-plate.png");

            foreach (var word in words)
            {
                Console.WriteLine(word);
            }

            Console.ReadKey();
        }
    }
}
