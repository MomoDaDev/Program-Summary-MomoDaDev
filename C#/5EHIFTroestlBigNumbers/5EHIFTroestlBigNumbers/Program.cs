using System;

namespace _5EHIFTroestlBigNumbers
{
    internal class Program
    {
        static void Main(string[] args)
        {
            //211214Rechnungen.csv

            const string TITLE = "BigNumbers";
            Console.Title = TITLE;
            Console.ForegroundColor = ConsoleColor.Cyan;

            var cluster = FileManager.ReadReceipts("211214Rechnungen.csv");
            cluster.CalcualteRegressionLine();
            Console.WriteLine(cluster.RegressionLine);

            Console.WriteLine("press any key to continue . . .");
            Console.ReadKey(true);
        }
    }
}
