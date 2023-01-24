using System;
using System.Threading;

namespace Berliner_Mengenlehre_Uhr
{
    internal class Program
    {
        static void Main(string[] args)
        {
            const string TITLE = "Berliner Mengenlehre Uhr";
            Console.Title = TITLE;
            Console.ForegroundColor = ConsoleColor.Red;

            Timer timer = new Timer(new TimerCallback(TickTimer), null, 0, 1000);

            Console.ReadKey(true);
        }

        static ConsoleColor mainLight = ConsoleColor.Yellow;
        static ConsoleColor[] row1 = new ConsoleColor[] { ConsoleColor.Red, ConsoleColor.Red, ConsoleColor.Red, ConsoleColor.Red };
        static ConsoleColor[] row2 = new ConsoleColor[] { ConsoleColor.Red, ConsoleColor.Red, ConsoleColor.Red, ConsoleColor.Red };
        static ConsoleColor[] row3 = new ConsoleColor[] { ConsoleColor.Yellow, ConsoleColor.Yellow, ConsoleColor.Red, ConsoleColor.Yellow, ConsoleColor.Yellow, ConsoleColor.Red, ConsoleColor.Yellow, ConsoleColor.Yellow, ConsoleColor.Red, ConsoleColor.Yellow, ConsoleColor.Yellow };
        static ConsoleColor[] row4 = new ConsoleColor[] { ConsoleColor.Yellow, ConsoleColor.Yellow, ConsoleColor.Yellow, ConsoleColor.Yellow };


        private static void TickTimer(object state)
        {
            DateTime now = DateTime.Now;

            if (mainLight == ConsoleColor.Yellow)
                mainLight = ConsoleColor.DarkGray;
            else
                mainLight = ConsoleColor.Yellow;

            int i_row1 = 0;
            int i_row2 = 0;
            int i_row3 = 0;
            int i_row4 = 0;

            i_row1 = now.Hour / 5;
            i_row2 = now.Hour % 5; ;
            i_row3 = now.Minute / 5;
            i_row4 = now.Minute % 5;

            PrintData(i_row1, i_row2, i_row3, i_row4);
        }

        private static void PrintData(int i_row1, int i_row2, int i_row3, int i_row4)
        {
            Console.SetCursorPosition(0, 0);
            Console.ForegroundColor = mainLight;
            Console.WriteLine("          O          ");
            Console.WriteLine();

            PrintRow(i_row1, row1, " ▄▄▄▄");
            PrintRow(i_row2, row2, " ▄▄▄▄");
            PrintRow(i_row3, row3, "█ ");
            PrintRow(i_row4, row4, " ▄▄▄▄");
        }

        private static void PrintRow(int row, ConsoleColor[] colors, string str)
        {
            for (int i = 0; i < row; i++)
            {
                Console.ForegroundColor = colors[i];
                Console.Write(str);
            }
            for (int i = 0; i < colors.Length - row; i++)
            {
                Console.ForegroundColor = ConsoleColor.Gray;
                Console.Write(str);
            }
            Console.WriteLine();
            Console.WriteLine();
        }
    }
}
