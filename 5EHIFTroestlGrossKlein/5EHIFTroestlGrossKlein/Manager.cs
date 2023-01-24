using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace _5EHIFTroestlGrossKlein
{
    internal class Manager
    {
        public static void StartScrambling()
        {
            // filepaths
            string path = "Muster.txt";
            string outputPath = "Output.txt";

            // read text from file
            string text = FileManager.ReadFile(path);

            // Scramble the text
            string scrambledText = TextScrambler.ScrambleText(text);

            List<string> allResults = TextScrambler.ScrambleTextAllVariations(text);

            List<string> allResultsSmaller3 = TextScrambler.ScrambleTextAllVariationsSmaller3(text);

            // print the result
            Console.WriteLine($"Original text:\n{text}\n" +
                $"Scrambled text:\n{scrambledText}");

            Console.WriteLine($"All results (count: 2^{text.Length} / {allResults.Count}):");
            // Console.WriteLine(String.Join("\n", allResults));

            Console.WriteLine($"All results smaller3 (count: {allResultsSmaller3.Count}): ");
            // Console.WriteLine(String.Join("\n", allResultsSmaller3));

            // write results
            FileManager.WriteFile(outputPath, scrambledText);
        }
    }
}
