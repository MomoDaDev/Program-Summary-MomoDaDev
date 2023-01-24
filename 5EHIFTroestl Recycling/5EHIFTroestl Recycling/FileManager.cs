using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace _5EHIFTroestl_Recycling
{
    internal class FileManager
    {
        public static List<Material> ReadMaterialList(string path)
        {
            List<Material> list = new List<Material>();

            try
            {
                using (StreamReader sr = new StreamReader(path))
                {
                    // skip first line
                    sr.ReadLine();

                    while (!sr.EndOfStream)
                    {
                        string line = sr.ReadLine();
                        // ls => line split
                        string[] ls = line.Split(',');

                        // create material
                        Material m = new Material() { Name = ls[0], Weight = double.Parse(ls[1]) };
                        
                        // add to list
                        list.Add(m);
                    }
                }
            }
            catch (Exception ex)
            {
                Console.WriteLine($"Exception: {ex.Message}");
            }

            return list;
        }
    }
}
