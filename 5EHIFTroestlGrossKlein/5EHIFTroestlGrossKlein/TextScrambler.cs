using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace _5EHIFTroestlGrossKlein
{
    internal class TextScrambler
    {
        public static string ScrambleText(string text)
        {
            // random instance from singleton pattern
            Random random = RandomSingleton.GetRandomInstance();

            // variable for the result that gets returned at the end
            string result = String.Empty;

            // iterate through every character of the text
            foreach (var character in text.ToCharArray())
            {
                result += random.Next(2) == 0 ? char.ToUpper(character) : char.ToLower(character);
            }

            // return result
            return result;
        }

        public static List<string> ScrambleTextAllVariations(string text)
        {
            // variable for the result that gets returned at the end
            List<string> result = new List<string>();

            for (int i = 0; i < Math.Pow(2, text.Length); i++)
            {
                string p = Convert.ToString(i, 2);
                char[] pattern = p.ToCharArray();
                char[] r = text.ToCharArray();

                for (int j = pattern.Length - 1; j >= 0; j--)
                {
                    r[j] = pattern[j] == '0' ? Char.ToLower(r[j]) : Char.ToUpper(r[j]);
                }
                result.Add(new string(r));
            }

            // return result
            return result;
        }

        public static List<string> ScrambleTextAllVariationsSmaller3(string text)
        {
            // variable for the result that gets returned at the end
            List<string> result = new List<string>();

            for (int i = 0; i < Math.Pow(2, text.Length); i++)
            {
                string p = Convert.ToString(i, 2);
                p = p.PadLeft(text.Length, '0');

                // Check smaller 3 
                if (p != p.Replace("000", "").Replace("111", ""))
                    continue;

                char[] pattern = p.ToCharArray();
                char[] r = text.ToCharArray();

                for (int j = pattern.Length - 1; j >= 0; j--)
                {
                    if (pattern[j] == '1')
                    {
                        if (Char.IsUpper(r[j]))
                            r[j] = Char.ToLower(r[j]);
                        else
                            r[j] = Char.ToUpper(r[j]);
                    }
                }
                result.Add(new string(r));
            }

            // return result
            return result;
        }
    }
}
