using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ConsoleApp13
{
    internal class Node
    {
        public string Id { get; private set; }
        public HashSet<Node> Neighbours { get; set; }
        // Dijkstra
        public Node? Predecessor { get; set; }
        public double Distance { get; set; }

        public Node(string id, HashSet<Node> neighbours)
        {
            Id = id;
            Neighbours = neighbours;
        }
    }
}
