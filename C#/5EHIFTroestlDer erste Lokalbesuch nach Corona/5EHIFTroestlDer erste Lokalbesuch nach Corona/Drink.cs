using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace _5EHIFTroestlDer_erste_Lokalbesuch_nach_Corona
{
    internal class Drink
    {
        public string Name { get; set; }
        public List<Price> prices { get; set; }
        public double Vitamingehalt { get; set; }
        public double Dauer { get; set; }

        public Drink(string name, List<Price> prices, double vitamingehalt, double dauer)
        {
            Name = name;
            this.prices = prices;
            Vitamingehalt = vitamingehalt;
            Dauer = dauer;
        }

        public double GetCurrentPrice(TimeOnly currentTime)
        {
            foreach (var price in prices)
            {
                if (price.from <= currentTime && currentTime <= price.until)
                {
                    return price.DrinkPrice;
                }
            }
            return 1;
        }
    }
}
