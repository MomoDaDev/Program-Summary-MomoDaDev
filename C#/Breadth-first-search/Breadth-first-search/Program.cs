using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Reflection;

namespace Breadth_first_search
{
    internal class Program
    {
        static void Main(string[] args)
        {
            AdjazenzList adjazenzList = FileSystem.ReadFileToAdjazenzList(
                Path.Combine(Path.GetDirectoryName(Assembly.GetExecutingAssembly().Location), "graph3.txt"));

            //Console.WriteLine(String.Join("\n", adjazenzList.adjazenzList
            //    .Select(x => x.Key.ToString() + "{\n" + JsonConvert.SerializeObject(x.Value) + "\n")));

            Dictionary<Node, HashSet<Node>> bft = Node.BFS(adjazenzList.StartNode, adjazenzList.adjazenzList);

            foreach (var item in bft)
            {
                string s = String.Join(",", item.Value.Select(x => x.Id));
                Console.WriteLine($"{item.Key.Id}: {s}");
            }

            Console.ReadKey(true);
        }
    }
}
