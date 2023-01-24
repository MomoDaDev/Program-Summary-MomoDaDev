using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace _5EHIFTroestlMinimum_Spanning_Tree
{
    internal class Kruskal
    {
        public static List<Edge> BuildMinimumSpanningTree(List<Node> n)
        {
            List<Edge> e = CreateList(n);

            List<Edge> result = new List<Edge>();
            // Loop for every connection
            e.ForEach(x =>
            {
                result.Add(x);
                if (CyrcleCheckDFS(x.Src, result))
                    result.Remove(x);
                else
                    result.Add(new Edge(x.Dest, x.Src, x.Weight));
            });

            return result;
        }
        private static List<Edge> CreateList(List<Node> nodes)
        {
            List<Edge> edges = new List<Edge>();

            foreach (var n1 in nodes)
            {
                List<Edge> n1Edges = new List<Edge>();
                foreach (var n2 in nodes)
                {
                    if (!n1.Equals(n2))
                        n1Edges.Add(new Edge(n1, n2, n1.GetDistance(n2)));
                }
                edges.Add(SortEdges(n1Edges).First());
            }
            return SortEdges(edges);
        }
        private static List<Edge> SortEdges(List<Edge> e)
        {
            return e.OrderBy(x => x.Weight).ToList();
        }

        #region Depth First Tree
        public static bool CyrcleCheckDFS(Node start, List<Edge> a)
        {
            List<Node> visited = new List<Node>();
            Stack<Node> q = new Stack<Node>(a.Count);

            // enqueue start node
            q.Push(start);

            while (q.Count > 0)
            {
                // initialize node and their neighbours
                Node node = q.Pop();
                node.Neighbours = new HashSet<Node>();
                List<Node> neighbours = new List<Node>();

                foreach (var edge in a)
                {
                    if (edge.Src.Equals(node))
                    {
                        neighbours.Add(edge.Dest);
                    }
                }

                foreach (var ne in neighbours)
                {
                    if (!visited.Contains(ne) && !q.Contains(ne))
                    {
                        // add Neighbour to node
                        node.Neighbours.Add(ne);

                        // enqueue
                        q.Push(ne);
                    }
                    if (visited.Contains(ne) && q.Contains(ne))
                        return true;
                }
                visited.Add(node);
            }
            return false;
        }
        #endregion
    }
}
