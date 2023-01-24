using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace _5EHIFTroestlDer_erste_Lokalbesuch_nach_Corona
{
    internal class InitializePeople
    {
        public static List<Person> Load()
        {
            List<Person> people = new List<Person>();

            // hardcode data
            people.Add(new Person("Anton", 50, 0, 0));
            people.Add(new Person("Benjamin", 50, 0, 0));
            people.Add(new Person("Christina", 50, 0, 0));
            people.Add(new Person("Doris", 50, 0, 0));
            people.Add(new Person("Erem", 50, 0, 0));

            return people;
        }
    }
}
