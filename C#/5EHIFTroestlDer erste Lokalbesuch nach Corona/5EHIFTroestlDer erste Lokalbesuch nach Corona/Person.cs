using Newtonsoft.Json;
using Newtonsoft.Json.Converters;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace _5EHIFTroestlDer_erste_Lokalbesuch_nach_Corona
{
    internal class Person
    {
        public string Name { get; set; }
        public double Money { get; set; }
        public double minutesLeft { get; set; }
        public double Vitamine { get; set; }
        public List<string> drinks { get; set; }

        public Person(string name, double money, double minutesLeft, double vitamine)
        {
            Name = name;
            Money = money;
            this.minutesLeft = minutesLeft;
            Vitamine = vitamine;
            this.drinks = new List<string>();
        }

        public override string ToString()
        {
            return JsonConvert.SerializeObject(
                this, Formatting.Indented,
                new JsonConverter[] { new StringEnumConverter() });
        }
    }
}
