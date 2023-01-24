using _5EHIFTroestl_Wiederholung_Graphenalgorithmen.Common;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace _5EHIFTroestl_Wiederholung_Graphenalgorithmen.Facade
{
    internal class Manager
    {
        public static void StartMapReduce()
        {
            // Input- and output folder
            string inputFolder = "input";
            string outputFolder = "output";

            // Initialize the full Map
            var map = FileManager.InitializeMap(inputFolder);

            // Reduce Map to MinimumSpanningTree
            var reducedMap = Mapper.ReduceMapToMinimumSpanningTree(map.First().Key, map);

            // Save Map to output folder
            FileManager.SaveMapToFolder(outputFolder, reducedMap);

            // Print soure Map
            Console.WriteLine("=============================================");
            Console.WriteLine("Source Map: ");
            Console.WriteLine(Printer.MapToString(map));
            Console.WriteLine("=============================================");

            // Print reduced Map
            Console.WriteLine("=============================================");
            Console.WriteLine("Reduced Map: ");
            Console.WriteLine(Printer.MapToString(reducedMap));
            Console.WriteLine("=============================================");
        }
    }
}
