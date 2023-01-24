using Newtonsoft.Json;
using Newtonsoft.Json.Converters;

namespace _5EHIFTroestlZeit_undDatumsberechnungen
{
    internal class Workpackage
    {
        public string WP { get; set; }
        public string Aufgabe { get; set; }
        public double Dauer { get; set; }

        public Workpackage(string wP, string aufgabe, double dauer)
        {
            WP = wP;
            Aufgabe = aufgabe;
            Dauer = dauer;
        }

        public override string ToString()
        {
            return JsonConvert.SerializeObject(
                this, Formatting.Indented,
                new JsonConverter[] { new StringEnumConverter() });
        }
    }
}
