using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using RestSharp;
using System.IO;

namespace GetSuperStoreOrders
{
    class Util
    {
        public static string getAdminAuthToken(string url, string username, string password)
        {
            var client = new RestClient(url + "/index.php/rest/V1/integration/admin/token");
            var request = new RestRequest(Method.POST);
            request.AddHeader("cache-control", "no-cache");
            request.AddHeader("content-type", "application/json");
            request.AddParameter("application/json", "{  \r\n    \"username\": \"" + username + "\",\r\n    \"password\": \"" + password + "\"\r\n}", ParameterType.RequestBody);
            IRestResponse response = client.Execute(request);
            //string t = response.Content;
            //string s = t;
            //string x = response.Content.Substring(1, response.Content.Length - 2);
            //t = t.Substring(1);
            //t = t.Substring(0, t.Length - 1);
            //return t;
            return response.Content.Substring(1, response.Content.Length-2);
        }

        public static void createFile(string f)
        {
            if (!File.Exists(f))
                WriteLine(f, "RowAdded" + "|" + "FromDateUTC" + "|" + "ToDateUTC" + "|" + "OrdersJSON");
        }

        public static void WriteLine(string f, string l)
        {
            StreamWriter sw = new StreamWriter(f, true);
            sw.Write(l);
            sw.Write(sw.NewLine);
            sw.Close();
        }

        public static DateTime getTimeStamp()
        {
            if (Properties.Settings.Default.ExecutionCount == 0)
            {
                Properties.Settings.Default.RunTime = new DateTime(2016, 4, 1, 00, 00, 00);
            }
            DateTime d = Properties.Settings.Default.RunTime.ToUniversalTime();
            return d;
        }

        public static IRestResponse getOrders(string url, string t, string qs)
        {
            //qs = "?searchCriteria%5BpageSize%5D=10&searchCriteria%5BcurrentPage%5D=1&searchCriteria%5BfilterGroups%5D%5B0%5D%5Bfilters%5D%5B0%5D%5Bfield%5D=created_at&searchCriteria%5BfilterGroups%5D%5B0%5D%5Bfilters%5D%5B0%5D%5Bvalue%5D=2016-04-26%2015%3A09%3A09&searchCriteria%5BfilterGroups%5D%5B0%5D%5Bfilters%5D%5B0%5D%5Bcondition_type%5D=gt";
            var client = new RestClient(url + "/index.php/rest/V1/orders/" + qs);
            var request = new RestRequest(Method.GET);
            request.AddHeader("cache-control", "no-cache");
            request.AddHeader("authorization", "Bearer " + t);
            request.AddHeader("content-type", "application/json");
            IRestResponse response = client.Execute(request);
            return response;
        }
    }
}
