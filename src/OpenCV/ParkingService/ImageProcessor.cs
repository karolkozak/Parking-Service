using Emgu.CV;
using Emgu.CV.Structure;
using ParkingService.Services;
using System.Collections.Generic;

namespace ParkingService
{
    public class ImageProcessor
    {
        public static List<string> ProcessImageFilename(string filename)
        {
            LicensePlateDetector licensePlateDetector = new LicensePlateDetector("");
            Mat m = new Mat("license-plate.png");
            //UMat um = m.GetUMat(AccessType.ReadWrite);
            return ProcessImage(m, licensePlateDetector);
        }

        public static List<string> ProcessImage(IInputOutputArray image, LicensePlateDetector licensePlateDetector)
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
