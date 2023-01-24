using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace _5EHIFTroestl_Wiederholung_Graphenalgorithmen.Common
{
    internal class FileManager
    {
        public static Dictionary<Node, HashSet<Node>> InitializeMap(string inputFolder)
        {
            Dictionary<Node, HashSet<Node>> map = new Dictionary<Node, HashSet<Node>>();

            try
            {
                if (!Directory.Exists(inputFolder))
                {
                    Console.WriteLine("Directory not found");
                    return map;
                }
                else
                {
                    string name = String.Empty;
                    string content = String.Empty;
                    Node node = null;

                    string line = String.Empty;

                    foreach (var fileName in Directory.GetFiles(inputFolder))
                    {
                        using (StreamReader reader = new StreamReader(fileName))
                        {
                            name = Path.GetFileNameWithoutExtension(fileName);
                            node = new Node(name);
                            map[node] = new HashSet<Node>();

                            while (!reader.EndOfStream)
                            {
                                line = reader.ReadLine();
                                line = line == null ? String.Empty : line;
                                map[node].Add(new Node(line));
                            }
                        }
                    }
                    return map;
                }
            }
            catch (Exception ex)
            {
                Console.WriteLine($"Exception: {ex.Message}");
            }
            return map;
        }

        public static void SaveMapToFolder(string outputFolder, Dictionary<Node, HashSet<Node>> map)
        {
            // Check if folder exists
            if (!Directory.Exists(outputFolder))
                Directory.CreateDirectory(outputFolder);

            // Write files
            foreach (var node in map)
            {
                // Creating file
                using (StreamWriter writer = new StreamWriter($"{outputFolder}\\{node.Key.Name}.txt"))
                {
                    // Writing neighbours to file
                    writer.WriteLine(String.Join("\n", node.Value.Select(n => n.Name)));
                }
            }
        }
    }
}
