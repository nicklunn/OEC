using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Configuration;
using RestSharp;
using Newtonsoft.Json;
using Newtonsoft.Json.Linq;

namespace GetSuperStoreOrders
{
    class Program
    {
        static void Main(string[] args)
        {
            string myurl, myusername, mypassword, myfile;
            int sleepTime;

            try { myurl = args[0]; }
            //catch (Exception e) { myurl = ConfigurationManager.AppSettings["URL"]; }
            catch (Exception e) { myurl = "http://lunn3241.ddns.net/magento"; }

            try { myusername = args[1]; }
            //catch (Exception e) { myusername = ConfigurationManager.AppSettings["ADMIN_USERNAME"]; }
            catch (Exception e) { myusername = "admin"; }

            try { mypassword = args[2]; }
            //catch (Exception e) { mypassword = ConfigurationManager.AppSettings["ADMIN_PASSWORD"]; }
            catch (Exception e) { mypassword = "password123"; }

            try { myfile = args[3]; }
            //catch (Exception e) { myfile = ConfigurationManager.AppSettings["LOG_FILE_PATH"]; }
            catch (Exception e) { myfile = "C:\\OrdersJSON.csv"; }

            try { sleepTime = Int32.Parse(args[4]); }
            catch (Exception e) { sleepTime = 1; }

            string token = Util.getAdminAuthToken(myurl, myusername, mypassword);   //get authorization token to run queries
            Properties.Settings.Default.ExecutionCount = 0;
            Properties.Settings.Default.Save();
            int x = 0;
            //Console.WriteLine(token);
            while (true)
            {

                DateTime date = Util.getTimeStamp(); //get the last time we ran this
                string dateformat = "yyyy-MM-dd HH:mm:ss";
                string fromdate = date.ToString(dateformat);
                DateTime now = DateTime.Now;
                string todate = now.ToUniversalTime().ToString(dateformat);

                string querystring = buildQueryString(fromdate, todate); //build up the searchCriteria
                                                                         
                //Console.WriteLine(querystring);

                //get the orders from the last time we ran
                IRestResponse r = Util.getOrders(myurl, token, querystring);

                //build up the line to write out
                string output = DateTime.Now.ToString() + "|" + fromdate + "|" + todate + "|" + r.Content;
                Util.createFile(myfile); //create the file, if necessary
                Util.WriteLine(myfile, output); //write output
                Console.WriteLine("Writing to: " + myfile + " " + (x + 1) + " times.");
                Properties.Settings.Default.ExecutionCount += 1;
                Properties.Settings.Default.RunTime = now;
                Properties.Settings.Default.Save();

                Console.WriteLine("Going to sleep at: " + DateTime.Now.ToString());
                System.Threading.Thread.Sleep(60000 * sleepTime); //sleep for 1 minute

                //System.Threading.Thread.Sleep(60000 * 60); //sleep for an hour
                Console.WriteLine("Waking up at: " + DateTime.Now.ToString());
                x++;


                //Properties.Settings.Default.ExecutionCount = 0;
                //if (Properties.Settings.Default.ExecutionCount == 0) //if first time runnning
                //{
                //    Properties.Settings.Default.RunTime = DateTime.Now; //set the date to now
                //    Properties.Settings.Default.Save();
                //}
                //DateTime d = Properties.Settings.Default.RunTime;

                //d = d.ToUniversalTime();

                //Console.WriteLine("Long Date: " + d.ToLongDateString());
                //Console.WriteLine("Long Time: " + d.ToLongTimeString());
                //Console.WriteLine("Short Date: " + d.ToShortDateString());
                //Console.WriteLine("Short Time: " + d.ToShortTimeString());
                //Console.WriteLine("ToString: " + d.ToString());

                //d = d.AddHours(8);

                //Properties.Settings.Default.RunTime = d;
                //d = Properties.Settings.Default.RunTime;
                //Console.WriteLine("Updating date");
                //d = Properties.Settings.Default.RunTime;
                //Console.WriteLine("Long Date: " + d.ToLongDateString());
                //Console.WriteLine("Long Time: " + d.ToLongTimeString());
                //Console.WriteLine("Short Date: " + d.ToShortDateString());
                //Console.WriteLine("Short Time: " + d.ToShortTimeString());
                //Console.WriteLine("ToString: " + d.ToString());

                //Properties.Settings.Default.ExecutionCount += 1;
                //Properties.Settings.Default.Save();
                
            }
            Console.WriteLine("Press any key to exit");
            string s = Console.ReadLine();
        }

        private static string buildQueryString(string fdate, string tdate)
        {
            string qs = "";
            qs += "?searchCriteria[pageSize]=10";
            qs += "&searchCriteria[currentPage]=1";
            qs += "&searchCriteria[filterGroups][0][filters][0][field]=created_at";
            qs += "&searchCriteria[filterGroups][0][filters][0][value]=" + fdate;
            qs += "&searchCriteria[filterGroups][0][filters][0][condition_type]=gt";
            qs += "&searchCriteria[filterGroups][1][filters][0][field]=created_at";
            qs += "&searchCriteria[filterGroups][1][filters][0][value]=" + tdate;
            qs += "&searchCriteria[filterGroups][1][filters][0][condition_type]=lt";
            return qs;
        }
    }
}
