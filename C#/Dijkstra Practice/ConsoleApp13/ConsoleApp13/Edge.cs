using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ConsoleApp13
{
    internal class Edge
    {
        public Node N1 { get; set; }
        public Node N2 { get; set; }
        public double Distance { get; set; }

        public Edge(Node n1, Node n2, double distance)
        {
            N1 = n1;
            N2 = n2;
            Distance = distance;
        }
    }
}
