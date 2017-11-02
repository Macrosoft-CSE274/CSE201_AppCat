using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net;
using System.Text;
using System.Threading.Tasks;

namespace GetGoolePlayData
{
    public class APP
    {
        public  int     ID              { get; set; }
        public  string  Name            { get; set; }
        public  string  AppLabel        { get; set; }
        public  int     AppLabelID      { get; set; }
        public  int     PlatformID      { get; set; }
        public  string  Industry        { get; set; }
        public  float   price           { get; set; }
        public  string  NumOfInstalls   { get; set; }
        public  float   AverageStar     { get; set; }
        public  string  weblink         { get; set; }
        public  int     deleted         { get; set; }
        public  string  description     { get; set; }

        public APP()
        {
            ID = 0;
            Name = "";
            AppLabel = "";
            AppLabelID = 0;
            PlatformID = 0;
            Industry = "";
            price = 0;
            NumOfInstalls = "";
            AverageStar = 0;
            weblink = "";
            deleted = 0;
            description = "";
        }
        public void SetAPP(int id, string name, string label, int labelID, int platformID,
                string ind, float price, string numOS, float AvgS, string link,
                int del, string desc)
        {
            this.ID               = id           ;
            this.Name             = name         ;
            this.AppLabel         = label        ;
            this.AppLabelID       = labelID      ;
            this.PlatformID       = platformID   ;
            this.Industry         = ind          ;
            this.price            = price        ;
            this.NumOfInstalls    = numOS        ;
            this.AverageStar      = AvgS         ;
            this.weblink          = link         ;
            this.deleted          = del          ;
            this.description      = desc         ;
        }
    }
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


            #region Set category array; category -> AppLabel
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
            category[50] = "FAMILY [0,5]";
            category[51] = "FAMILY [6,8]";
            category[52] = "FAMILY [9,255]";
            //Console.WriteLine(category.Length);
            #endregion

            #region Write AppLabel.txt
            string AppLabelTXT = "AppLabelID\tAppLabelName\tDeleted\r\n";
            int AppLabelCounter = 1;
            foreach (string x in category)
            {
                AppLabelTXT += (AppLabelCounter+"\t"+x+"\t0\r\n");
                AppLabelCounter++;
            }
            System.IO.File.WriteAllText("AppLabel.txt", AppLabelTXT);
            #endregion

            #region Set AppURL Stack
            Stack<string> AppURLStack = new Stack<string> { };//store all appURL, also help check duplicate url
            foreach(string tempLabelURL in categoryURL)
            {
                //Console.WriteLine(HomePageURL + tempLabelURL);
                string LabelSource = GetWebSourceCode(HomePageURL + tempLabelURL);
                //Console.WriteLine(LabelSource);
                string[] AppURLTemp = LabelSource.Split(new string[] { "<a class=\"title\" href=\"" }, StringSplitOptions.None);
                string[] AppURL = new string[AppURLTemp.Length - 1];
                for (int i = 1; i < AppURLTemp.Length; i++)
                {
                    string[] temp = AppURLTemp[i].Split('"');
                    AppURL[i - 1] = HomePageURL+temp[0];
                    if(AppURLStack.Contains(AppURL[i - 1]) is false)
                    {
                        AppURLStack.Push(AppURL[i - 1]);
                    }
                }
            }
            //foreach(string appURLtemp in AppURLStack)
            //{
            //    Console.WriteLine(appURLtemp);
            //}
            #endregion


            int AppCounter = 0;
            APP[] AppArray = new APP[AppURLStack.Count];
            for(int i= 0;i<AppURLStack.Count;i++)
            {
                AppArray[i] = new APP();
            }
            foreach(string appurltemp in AppURLStack)
            {
                string AppSource = GetWebSourceCode(appurltemp);
                int ID = AppCounter + 1;
                int PlatformID = 1;
                int deleted = 0;
                float AverageStar = 0;
                string[] industryHelper = AppSource.Split(new string[] { "<span itemprop=\"name\">" },StringSplitOptions.None);
                string Industry = industryHelper[1].Substring(0, industryHelper[1].IndexOf('<'));
                string[] priceHelper = AppSource.Split(new string[] { "\" itemprop=\"price\">" }, StringSplitOptions.None);
                string price = priceHelper[0].Substring(priceHelper[0].LastIndexOf('"')+1, priceHelper[0].Length - priceHelper[0].LastIndexOf('"')-1);
                if(price.Contains('$'))
                {
                    price = price.Substring(1, price.Length - 1);
                }
                string[] nameHelper = AppSource.Split(new string[] { "</script><title id=\"main-title\">" }, StringSplitOptions.None);
                string[] nameHelperHelper = nameHelper[1].Split(new string[] { " - Android Apps on Google Play" },StringSplitOptions.None);
                string Name = nameHelperHelper[0];
                string[] labelHelper = AppSource.Split(new string[] { "\"document-subtitle category\" href=\"/store/apps/category/" }, StringSplitOptions.None);
                string label = labelHelper[1].Substring(0,labelHelper[1].IndexOf('"'));
                if(label == "FAMILY? age = AGE_RANGE1")
                {
                    label = "FAMILY [0,5]";
                }
                if (label == "FAMILY? age = AGE_RANGE2")
                {
                    label = "FAMILY [6,8]";
                }
                if (label == "FAMILY? age = AGE_RANGE3")
                {
                    label = "FAMILY [9,255]";
                }
                label = label.Replace("_"," ");
                int labelID = 0;
                for(int i = 0; i < category.Length; i++)
                {
                    if (category[i] == label)
                    {
                        labelID = i;
                        break;
                    }
                }
                string[] numOfInsHelper = AppSource.Split(new string[] { "itemprop=\"numDownloads\">" },StringSplitOptions.None);
                string numOfIns = numOfInsHelper[1].Substring(0, numOfInsHelper[1].IndexOf('<'));
                AppArray[AppCounter].SetAPP(ID,Name,label,labelID, PlatformID, Industry, float.Parse(price), numOfIns, AverageStar, appurltemp, deleted, "");
                AppCounter++;
            }
        }
    }
}
