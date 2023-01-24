using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace _5EHIFTroestl_Recycling
{
    internal class Sorter
    {
        public static int SPEED = 1;
        public static Dictionary<string, List<Material>> SortMaterials(List<Material> m)
        {
            // count total time
            int totalCount = 0;
            int count;

            // Create dictionary
            Dictionary<string, List<Material>> dictionary = new Dictionary<string, List<Material>>();

            // iterate through list
            foreach (Material mat in m)
            {
                // Wait the time
                count = (int)Math.Floor(Math.Log10(mat.Weight)) / SPEED;
                totalCount += count;
                // Thread.Sleep((int)Math.Floor(Math.Log10(mat.Weight)));

                // check if key doesn't exist yet
                if (!dictionary.ContainsKey(mat.Name))
                    dictionary[mat.Name] = new List<Material>();

                // add material to group
                dictionary[mat.Name].Add(mat);
            }

            Dictionary<string, List<Material>> weighteddictionary = new Dictionary<string, List<Material>>();

            foreach (var d in dictionary)
            {
                int counter = 1;
                List<Material> list = new List<Material>();

                foreach (var mat in d.Value)
                {
                    if (list.Sum(x => x.Weight) + mat.Weight > 500_000)
                    {
                        count = 60 * 10;
                        weighteddictionary[d.Key + counter] = list;
                        list = new List<Material>();
                        counter++;
                    }
                    list.Add(mat);
                }

                if (list.Count != 0)
                {
                    weighteddictionary[d.Key + counter] = list;
                }
            }

            Console.WriteLine("Total time it took to sort:" + totalCount / 60 + " minutes");

            return weighteddictionary;
        }
    }
}
