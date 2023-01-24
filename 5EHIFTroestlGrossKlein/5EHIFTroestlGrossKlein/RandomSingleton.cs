using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace _5EHIFTroestlGrossKlein
{
    internal class RandomSingleton
    {
        private static readonly Random _random = new Random();

        private RandomSingleton() { }

        public static Random GetRandomInstance()
        {
            return _random;
        }
    }
}
