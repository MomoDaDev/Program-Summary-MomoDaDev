using System;
using System.IO;
using System.Xml.Serialization;
using System.Text.Json;

namespace _5EHIFTroestlObjectGraphSchule.Common
{
    public class SerDes
    {
        public static void XMLSerializeObject(object o, string path)
        {
            try
            {
                XmlSerializer x = new XmlSerializer(o.GetType());
                using (StreamWriter sw = new StreamWriter(path))
                {
                    x.Serialize(sw, o);
                }
            }
            catch (Exception e)
            {
                Console.WriteLine($"Exception: {e.Message}");
            }
        }

        public static object XMLDeserializeObject(Type type, string path)
        {
            try
            {
                XmlSerializer serializer = new XmlSerializer(type);
                using (Stream reader = new FileStream(path, FileMode.Open))
                {
                    return serializer.Deserialize(reader);
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
