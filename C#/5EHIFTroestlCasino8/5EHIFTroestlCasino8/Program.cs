using System;

namespace _5EHIFTroestlCasino8
{
    class Program
    {
        static void Main(string[] args)
        {
            string TITLE = "Casino8";
            Console.Title = TITLE;
            Console.ForegroundColor = ConsoleColor.Red;

            PlaySpieler1();
            TestAllStrategies();

            Console.WriteLine("press any key to continue . . .");
            Console.ReadKey(true);
        }

        private static void PlaySpieler1()
        {
            string directory = "";
            string filename = "Spieler1.conf";
            Game game = ReadFile.InitConfig(directory, filename);

            int roundIndex = 0;
            while (game.HasMoney)
            {
                if (roundIndex >= game.Rounds.Length)
                    break;
                game.PlayRound(roundIndex, true);
                roundIndex++;
            }
        }

        private static void TestAllStrategies()
        {
            Game[] games = new Game[] {
                new Game("Strat G", true, 100000, new string[] { "G 111" }),
                new Game("Strat U", true, 100000, new string[] { "U 111" }),
                new Game("Strat 0", true, 100000, new string[] { "0 111" }),
                new Game("Strat 1", true, 100000, new string[] { "1 111" }),
                new Game("Strat 2", true, 100000, new string[] { "2 111" }),
                new Game("Strat 3", true, 100000, new string[] { "3 111" }),
                new Game("Strat 4", true, 100000, new string[] { "4 111" }),
                new Game("Strat 5", true, 100000, new string[] { "5 111" }),
                new Game("Strat 6", true, 100000, new string[] { "6 111" }),
                new Game("Strat 7", true, 100000, new string[] { "7 111" }),
            };

            foreach (Game game in games)
            {
                for (int i = 0; i < 1000000; i++)
                {
                    game.PlayRound(0, false);
                }
                Console.WriteLine(game.ToString());
            }
        }
    }
}
