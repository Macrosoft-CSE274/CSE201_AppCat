using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net;
using System.Text;
using System.Threading.Tasks;

namespace GetGoolePlayData
{
    class Program
    {
        public static String GetWebSourceCode(string Url)
        {
            HttpWebRequest myRequest = (HttpWebRequest)WebRequest.Create(Url);
            myRequest.Method = "GET";
            try
            {
                WebResponse myResponse = myRequest.GetResponse();
                StreamReader sr = new StreamReader(myResponse.GetResponseStream(), System.Text.Encoding.UTF8);
                string result = sr.ReadToEnd();
                sr.Close();
                myResponse.Close();
                return result;
            }
            catch
            {
                Console.WriteLine("GG");
            }

            return null;

        }

        static void Main(string[] args)
        {
            string HomePageURL = "https://play.google.com";
            string homePageSource = GetWebSourceCode(HomePageURL+ "/store/apps");
            //System.IO.File.WriteAllText("homePageSource.txt",homePageSource);

//Set category array; category -> AppLabel
#region
            string[] categoryTemp = homePageSource.Split(new string[] { "<a class=\"child-submenu-link\" href=\"" }, StringSplitOptions.None);
            string[] categoryURL = new string[categoryTemp.Length-1];
            string[] category = new string[categoryURL.Length];
            for (int i = 1; i < categoryTemp.Length; i++)
            {
                string[] temp = categoryTemp[i].Split('"');
                categoryURL[i-1] = temp[0];
                int indexTemp = categoryURL[i - 1].LastIndexOf('/');
                int lengthTemp = categoryURL[i - 1].Length;
                category[i - 1] = categoryURL[i-1].Substring(indexTemp+1, lengthTemp-indexTemp-1);

            }
            for(int i = 0;i<category.Length;i++)
            {
                category[i] = category[i].Replace("_"," ");
                //Console.WriteLine(category[i]);
            }
            category[50] = "FAMILY AGE [0,5]";
            category[51] = "FAMILY AGE [6,8]";
            category[52] = "FAMILY AGE [9,255]";
            //Console.WriteLine(category.Length);
            #endregion

            string AppLabelTXT = "AppLabelID\tAppLabelName\tDeleted\r\n";
            int AppLabelCounter = 1;
            foreach (string x in category)
            {
                AppLabelTXT += (AppLabelCounter+"\t"+x+"\t0\r\n");
                AppLabelCounter++;
            }
            System.IO.File.WriteAllText("AppLabel.txt", AppLabelTXT);
        }
    }
}
