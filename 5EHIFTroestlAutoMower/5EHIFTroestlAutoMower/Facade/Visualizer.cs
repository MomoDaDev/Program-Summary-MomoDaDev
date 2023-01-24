using _5EHIFTroestlAutoMower.Common;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace _5EHIFTroestlAutoMower.Facade
{
    class Visualizer
    {
        public static void PrintField(string[,] strLawn)
        {
            for (int i = 0; i < strLawn.GetLength(0); i++)
            {
                for (int j = 0; j < strLawn.GetLength(1); j++)
                {
                    Console.SetCursorPosition(i, j);
                    if (strLawn[i, j] == "X")
                        Console.BackgroundColor = ConsoleColor.Green;
                    else
                        Console.BackgroundColor = ConsoleColor.Red;
                    Console.Write(strLawn[i, j]);
                }
            }
        }

        public static void UpdateField(Field field)
        {
            Console.SetCursorPosition(field.PosX, field.PosY);
            if (field.CountMowed == 0)
            {
                Console.BackgroundColor = ConsoleColor.Red;
                Console.Write("O");
            }
            else
            {
                Console.BackgroundColor = ConsoleColor.Green;
                Console.Write("X");
            }
        }

        private static void UpdateCircle(Field field)
        {
            Console.SetCursorPosition(field.PosX, field.PosY);
            Console.BackgroundColor = ConsoleColor.White;
            Console.Write("M");
        }

        public static void DrawCircle(double radius, List<Field> fields, double oldPosX, double oldPosY, double newPosX, double newPosY)
        {
            foreach (Field field in fields)
            {
                if (CollisionChecker.CircleRect((float)oldPosX, (float)oldPosY, (float)radius, field.PosX, field.PosY, field.Width, field.Height))
                {
                    UpdateField(field);
                }
            }

            foreach (Field field in fields)
            {
                if (CollisionChecker.CircleRect((float)newPosX, (float)newPosY, (float)radius, field.PosX, field.PosY, field.Width, field.Height))
                {
                    UpdateCircle(field);
                }
            }
        }
    }
}
