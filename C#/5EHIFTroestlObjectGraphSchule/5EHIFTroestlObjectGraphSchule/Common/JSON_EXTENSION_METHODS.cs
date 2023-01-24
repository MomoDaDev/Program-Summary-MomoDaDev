using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text.Json;
using System.Threading.Tasks;

namespace _5EHIFTroestlObjectGraphSchule.Common
{
    internal static class JSON_EXTENSION_METHODS
    {
        public static void JSONSerializeObject(this object x, string path)
        {
            try
            {
                using (StreamWriter sw = new StreamWriter(path))
                {
                    sw.Write(JsonSerializer.Serialize(x));
                }
            }
            catch (Exception e)
            {
                Console.WriteLine($"Exception: {e.Message}");
            }
        }

        public static object JSONDeserializeObject(this object x, string path)
        {
            var dym = (dynamic)x.GetType();
            try
            {
                using (StreamReader reader = new StreamReader(path))
                {
                    return JsonSerializer.Deserialize(reader.ReadToEnd(), x.GetType());
                }
            }
            catch (Exception e)
            {
                Console.WriteLine($"Exception: {e.Message}");
                return null;
            }

        }
    }
}
