using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace _5EHIFTroestlMinimum_Spanning_Tree
{
    internal class FileManager
    {
        public static List<Node> InitializeNodes(string path)
        {
            List<Node> n = new List<Node>();

            try
            {
                using (StreamReader r = new StreamReader(path))
                {
                    // Skip first Line: Id,X,Y
                    r.ReadLine();

                    // Read contenst
                    while (!r.EndOfStream)
                    {
                        // Read Line (ls = lineSpit)
                        string[] ls = r.ReadLine().Split(',');

                        n.Add(new Node(int.Parse(ls[0]), int.Parse(ls[1]), int.Parse(ls[2])));
                    }
                }
            }
            catch (Exception ex)
            {
                Console.WriteLine($"Exception: {ex.Message}");
            }

            return n;
        }
    }
}
