using Newtonsoft.Json;
using Newtonsoft.Json.Converters;

namespace _5EHIFTroestlZeit_undDatumsberechnungen
{
    internal class Projekt
    {
        #region Properties
        public DateTime Starttime { get; set; } = DateTime.Now;
        public double MaxWorkingTime { get; set; } = 120;
        public double Breaktime { get; set; } = 30;

        public List<Workpackage> WorkPackages { get; set; }
        #endregion

        #region Initialization
        public Projekt()
        {
            WorkPackages = new List<Workpackage>();
        }
        #endregion

        #region Loading
        public void LoadParams(string filename)
        {
            try
            {
                using (System.IO.StreamReader sr = new System.IO.StreamReader(filename))
                {
                    string[] parameters;

                    while (!sr.EndOfStream)
                    {
                        try
                        {
                            parameters = GetParameters(sr.ReadLine());

                            switch (parameters[0])
                            {
                                case "Starttime":
                                    // new DateTime(Year, Month, Day, Hour, Minute, Second);
                                    Starttime = new DateTime(DateTime.Now.Year, DateTime.Now.Month, DateTime.Now.Day, 
                                        int.Parse(parameters[1].Split(':')[0]), int.Parse(parameters[1].Split(':')[1]), 0);
                                    break;
                                case "MaxWorkingTime":
                                    MaxWorkingTime = double.Parse(parameters[1]);
                                    break;
                                case "Breaktime":
                                    Breaktime = double.Parse(parameters[1]);
                                    break;
                                default:
                                    break;
                            }
                        }
                        catch (Exception ex)
                        {
                            Console.WriteLine($"Exception in Method LoadParams in Operation: {ex.Message}");
                        }
                    }
                }
            }
            catch (Exception ex)
            {
                Console.WriteLine($"Exception in Method LoadParams: {ex.Message}");
            }
        }
        
        /// <summary>
        /// returns a string[] with the parameter name and the parameter
        /// </summary>
        /// <param name="line"></param>
        /// <returns></returns>
        private string[] GetParameters(string line)
        {
            string paramterside = line.Split('#')[0];
            paramterside = paramterside.Trim();
            return paramterside.Split('=');
        }
        public void Load(string filename) 
        {
            try
            {
                using (System.IO.StreamReader sr = new System.IO.StreamReader(filename))
                {
                    string[] ls; // ls => line Split
                    while (!sr.EndOfStream)
                    {
                        try
                        {
                            ls = sr.ReadLine().Split(';');
                            WorkPackages.Add(new Workpackage(ls[0], ls[1], double.Parse(ls[2])));
                        }
                        catch (Exception ex)
                        {
                            Console.WriteLine($"Exception in Method Load in Operation: {ex.Message}");
                        }
                    }
                }
            }
            catch (Exception ex)
            {
                Console.WriteLine($"Exception in Method Load: {ex.Message}");
            }
        }
        #endregion

        #region Print
        public void PrintSchedule()
        {
            // used to add minutes for every task
            DateTime currentTime = Starttime;

            // used for MaxWorkingTime
            double workingTime = 0;

            foreach (var wp in WorkPackages)
            {
                // Check if a breaktime is required
                if (workingTime > MaxWorkingTime)
                {
                    Console.WriteLine($"{currentTime.ToString("HH:mm")} {Breaktime}-Minuten-Pause");
                    workingTime = 0;
                    currentTime = currentTime.AddMinutes(Breaktime);
                }

                Console.WriteLine($"{currentTime.ToString("HH:mm")} {wp.WP} {wp.Aufgabe}");
                workingTime += wp.Dauer;
                currentTime = currentTime.AddMinutes(wp.Dauer);
                

                
            }
        }
        #endregion

        #region Overrides
        public override string ToString()
        {
            return JsonConvert.SerializeObject(
                this, Formatting.Indented,
                new JsonConverter[] { new StringEnumConverter() });
        }
        #endregion
    }
}
