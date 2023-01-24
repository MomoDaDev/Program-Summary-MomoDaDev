using _5EHIFTroestlAutoMower.Facade;
using System;

namespace _5EHIFTroestlAutoMower
{
    class Program
    {
        static int XLENGTH = 50;
        static int YLENGTH = 50;

        static void Main(string[] args)
        {
            string TITLE = "AutoMower";
            Console.Title = TITLE;

            Mower mower = new Mower(8, XLENGTH / 2, YLENGTH / 2, 20);
            Lawn lawn = new Lawn(mower, XLENGTH, YLENGTH);
            lawn.GrowLawn();

            // strLawn is for the visualization in the console
            string[,] strLawn = lawn.GetField();
            Visualizer.PrintField(strLawn);

            lawn.StartCuttingLawn(0);

            Console.SetCursorPosition(0, YLENGTH);
            Console.BackgroundColor = ConsoleColor.Black;
            Console.WriteLine("Press any key to exit the program . . .");
            Console.ReadKey(true);
        }
    }
}
