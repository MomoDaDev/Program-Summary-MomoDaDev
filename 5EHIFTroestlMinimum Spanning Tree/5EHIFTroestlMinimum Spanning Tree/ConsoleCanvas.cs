using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace _5EHIFTroestlMinimum_Spanning_Tree
{
    internal class ConsoleCanvas
    {
        public static void DrawNodes(List<Node> n)
        {
            n.ForEach(x =>
            {
                Console.SetCursorPosition(x.X, x.Y + 14);
                ConsoleColor cl = Console.ForegroundColor;
                Console.ForegroundColor = ConsoleColor.Red;
                Console.Write(x.Id);
                Console.ForegroundColor = cl;
            });
        }
    }
}
