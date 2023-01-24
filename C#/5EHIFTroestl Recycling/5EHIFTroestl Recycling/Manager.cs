using Newtonsoft.Json;
using Newtonsoft.Json.Converters;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace _5EHIFTroestl_Recycling
{
    internal class Manager
    {
        public void PlaySimulation()
        {
            // Read File with materials
            string relativePath = "WasteBelt.csv";
            List<Material> materials = FileManager.ReadMaterialList(relativePath);

            // Sort materials into groups in form of a dictionary
            Dictionary<string, List<Material>> sortedMaterials = Sorter.SortMaterials(materials);

            Console.WriteLine(JsonConvert.SerializeObject(
                sortedMaterials, Formatting.Indented,
                new JsonConverter[] { new StringEnumConverter() }));
            Console.WriteLine("Weight");
            Console.WriteLine(String.Join(", ", sortedMaterials.Select(x => x.Value.Sum(y => y.Weight))));
        }
    }
}
