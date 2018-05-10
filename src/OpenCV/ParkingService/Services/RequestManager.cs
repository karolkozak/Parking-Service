using System;
using System.Net;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;
using Newtonsoft.Json;
using ParkingService.Models;

namespace ParkingService.Services
{
    public class RequestManager
    {
        HttpClient client = new HttpClient();

        public async Task<Uri> CreateAsync(string path, IModel model)
        {
            var stringContent = new StringContent(JsonConvert.SerializeObject(model), Encoding.UTF8, "application/json");
            HttpResponseMessage response = await client.PostAsync(path, stringContent);
            response.EnsureSuccessStatusCode();

            // return URI of the created resource.
            return response.Headers.Location;
        }

        public async Task<IModel> GetAsync(string path)
        {
            IModel model = null;
            HttpResponseMessage response = await client.GetAsync(path);
            if (response.IsSuccessStatusCode)
            {
                // Read response as string.
                var stringModel = await response.Content.ReadAsStringAsync();
                // And next serialize to model.
                model = JsonConvert.DeserializeObject<IModel>(stringModel);
            }
            return model;
        }

        public async Task<IModel> UpdateAsync(string path, IModel model)
        {
            var stringContent = new StringContent(JsonConvert.SerializeObject(model), Encoding.UTF8, "application/json");
            HttpResponseMessage response = await client.PutAsync(path, stringContent);

            // If something went wrong, just return null.
            if (!response.IsSuccessStatusCode)
                return null;

            // Read response as string.
            var stringModel = await response.Content.ReadAsStringAsync();
            // And next serialize to model.
            model = JsonConvert.DeserializeObject<IModel>(stringModel);
            return model;
        }

        public async Task<HttpStatusCode> DeleteAsync(string path)
        {
            HttpResponseMessage response = await client.DeleteAsync(path);
            return response.StatusCode;
        }
    }
}
