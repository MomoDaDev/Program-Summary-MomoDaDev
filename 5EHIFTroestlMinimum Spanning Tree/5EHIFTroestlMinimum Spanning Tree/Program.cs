// Moritz Troestl
// 5EHIF
// 07.12.2021
using System;
using System.Collections.Generic;
using System.Linq;

namespace _5EHIFTroestlMinimum_Spanning_Tree
{
    internal class Program
    {
        static void Main(string[] args)
        {
            const string TITLE = "Minimum Spanning Tree";
            Console.Title = TITLE;
            Console.ForegroundColor = ConsoleColor.Cyan;

            // example1.txt
            Console.WriteLine("Example 1: ");

            List<Node> n = FileManager.InitializeNodes("example1.txt");

            List<Edge> mst = Kruskal.BuildMinimumSpanningTree(n);

            Console.WriteLine(String.Join("\n\n", mst.Select(x => x.ToString())));

            Console.WriteLine("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");

            ConsoleCanvas.DrawNodes(n);

            //Console.WriteLine("press any key to continue . . .");
            Console.ReadKey(true);
        }
    }
}
