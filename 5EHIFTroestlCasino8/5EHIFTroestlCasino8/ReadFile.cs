using System;
using System.Collections.Generic;
using System.IO;

namespace _5EHIFTroestlCasino8
{
    class ReadFile
    {
        public static Game InitConfig(string directory, string filename)
        {
            try
            {
                using (StreamReader reader = new StreamReader($"{directory}{filename}"))
                {
                    string participantName = reader.ReadLine();
                    double balance = double.Parse(reader.ReadLine().Split(' ')[1]);
                    List<string> rounds = new List<string>();
                    while (!reader.EndOfStream)
                    {
                        string line = reader.ReadLine();
                        if (line == "ENDE")
                            break;
                        rounds.Add(line);
                    }

                    return new Game(participantName, balance > 0 ? true : false, balance, rounds.ToArray());
                }
            }
            catch (Exception)
            {
                return null;
            }
        }
    }
}
