using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace _5EHIFTroestlDer_erste_Lokalbesuch_nach_Corona
{
    internal class InitializeDrinks
    {
        public static List<Drink> Load()
        {
            List<Drink> drinks = new List<Drink>();

            // Hardcoding all drinks
            drinks.Add(new Drink("BioHimbeer", new List<Price>()
            {
                new Price(new TimeOnly(10, 0), new TimeOnly(10, 59, 59, 999), 1),
                new Price(new TimeOnly(11, 0), new TimeOnly(19, 0), 2.5)
            },
            3, 20));

            drinks.Add(new Drink("Smoothie", new List<Price>()
            {
                new Price(new TimeOnly(10, 0), new TimeOnly(10, 59, 59, 999), 1),
                new Price(new TimeOnly(11, 0), new TimeOnly(11, 59, 59, 999), 2.5),
                new Price(new TimeOnly(12, 0), new TimeOnly(19, 0), 3.5)
            },
            4, 30));

            drinks.Add(new Drink("WellnessDrink", new List<Price>()
            {
                new Price(new TimeOnly(10, 0), new TimeOnly(10, 59, 59, 999), 1),
                new Price(new TimeOnly(11, 0), new TimeOnly(11, 59, 59, 999), 2.5),
                new Price(new TimeOnly(12, 0), new TimeOnly(19, 0), 3.5)
            },
            3, 20));

            drinks.Add(new Drink("Apfelsaft pur", new List<Price>()
            {
                new Price(new TimeOnly(10, 0), new TimeOnly(19, 0), 2.3)
            },
            2, 40));

            drinks.Add(new Drink("Apfelsaft gespritzt", new List<Price>()
            {
                new Price(new TimeOnly(10, 0), new TimeOnly(19, 0), 2)
            },
            2, 15));

            drinks.Add(new Drink("LycheeSaft", new List<Price>()
            {
                new Price(new TimeOnly(10, 0), new TimeOnly(19, 0), 2.3)
            },
            2, 5));

            return drinks;
        }
    }
}
