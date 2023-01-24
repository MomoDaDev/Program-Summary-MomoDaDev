using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace _5EHIFTroestlDer_erste_Lokalbesuch_nach_Corona
{
    internal class Price
    {
        public TimeOnly from { get; set; }
        public TimeOnly until { get; set; }
        public double DrinkPrice { get; set; }

        public Price(TimeOnly from, TimeOnly untill, double drinkPrice)
        {
            this.from = from;
            this.until = untill;
            DrinkPrice = drinkPrice;
        }
    }
}
