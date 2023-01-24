// Moritz Troestl
// V.1.1
// 18-Jan-2022
using System;

using _5EHIFTroestlSmartphone_Production.Facade;

namespace _5EHIFTroestlSmartphone_Production
{
    internal class Program
    {
        static void Main(string[] args)
        {
            const string TITLE = "Smartphone Production";
            Console.Title = TITLE;
            Console.ForegroundColor = ConsoleColor.Cyan;

            Manager manager = new Manager();

            Console.WriteLine("press any key to continue . . .");
            Console.ReadKey(true);
        }
    }
}
