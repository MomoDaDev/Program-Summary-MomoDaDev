using Newtonsoft.Json;
using Newtonsoft.Json.Converters;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace _5EHIFTroestlDer_erste_Lokalbesuch_nach_Corona
{
    internal class Simulation
    {
        List<Person> people { get; set; }
        public int stepTime { get; set; }
        TimeOnly time { get; set; }
        TimeOnly closingTime { get; set; }
        List<Drink> drinks;
        public static Random random = new Random();

        public Simulation(List<Person> people, int stepTime, TimeOnly time, TimeOnly closingTime, List<Drink> drinks)
        {
            this.people = people;
            this.stepTime = stepTime;
            this.time = time;
            this.closingTime = closingTime;
            this.drinks = drinks;
        }

        public void StartSimulation()
        {
            while (time <= closingTime && MoneyLeft())
            {
                foreach (var p in people)
                {
                    if (p.minutesLeft == 0)
                    {
                        var d = drinks[random.Next(0, drinks.Count)];
                        if (d.GetCurrentPrice(time) <= p.Money)
                        {
                            p.Vitamine += d.Vitamingehalt;
                            p.minutesLeft = d.Dauer;
                            p.Money -= d.GetCurrentPrice(time);
                            p.drinks.Add(d.Name);
                        }
                    } else
                    {
                        p.minutesLeft--;
                    }
                }
                time = time.AddMinutes(1);
                if (stepTime != 0)
                    Thread.Sleep(stepTime);
            }

            Console.WriteLine("EndStats");
            Console.WriteLine(JsonConvert.SerializeObject(
                people, Formatting.Indented,
                new JsonConverter[] { new StringEnumConverter() }));
        }

        private bool MoneyLeft()
        {
            foreach (var p in people)
            {
                if (p.Money >= 1)
                    return true;
            }
            return false;
        }
        public override string ToString()
        {
            return JsonConvert.SerializeObject(
                this, Formatting.Indented,
                new JsonConverter[] { new StringEnumConverter() });
        }
    }
}
