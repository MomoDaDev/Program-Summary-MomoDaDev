using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ConsoleApp13
{
    internal class Graph
    {
        public List<Node> Nodes { get; set; }
        public List<Edge> Edges { get; set; }

        public Graph(List<Node> nodes, List<Edge> edges)
        {
            Nodes = nodes;
            Edges = edges;
            FillNeighbours();
        }

        private void FillNeighbours()
        {
            foreach (var edge in Edges)
            {
                edge.N1.Neighbours.Add(edge.N2);
                edge.N2.Neighbours.Add(edge.N1);
            }
        }

        public List<Node> GetShortestPath(Node start, Node dest)
        {
            ResetNodes();
            start.Predecessor = start;
            DijkstraRecursive(start, 0);

            List<Node> shortestPath = new List<Node>();
            Node? n = dest;

            while (n != start)
            {
                shortestPath.Add(n);
                n = n.Predecessor;
            }

            return shortestPath;
        }

        public void DijkstraRecursive(Node n, double distance)
        {
            foreach (var ne in n.Neighbours)
            {
                // Get current edge between n and ne
                Edge edge = Edges.First(x => (x.N1 == n && x.N2 == ne) || (x.N1 == ne && x.N2 == n));

                if (distance + edge.Distance < ne.Distance)
                {
                    ne.Distance = distance + edge.Distance;
                    ne.Predecessor = n;
                    DijkstraRecursive(ne, ne.Distance);
                }
            }
        }

        private void ResetNodes()
        {
            foreach (var n in Nodes)
            {
                n.Distance = int.MaxValue;
            }
        }
    }
}
