using System;
using System.Collections.Generic;
using Emgu.CV;
using Emgu.CV.Structure;
using ParkingService.Services;

namespace ParkingService
{
    class Program
    {
        static void Main(string[] args)
        {
            LicensePlateDetector licensePlateDetector = new LicensePlateDetector("");
            Mat m = new Mat("license-plate.jpg");
            //UMat um = m.GetUMat(AccessType.ReadWrite);
            var words = ProcessImage(m, licensePlateDetector);

            foreach (var word in words)
            {
                Console.WriteLine(word);
            }

            Console.ReadKey();
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
    }
}
