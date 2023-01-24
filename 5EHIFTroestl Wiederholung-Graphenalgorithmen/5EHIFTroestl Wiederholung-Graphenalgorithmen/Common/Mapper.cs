using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace _5EHIFTroestl_Wiederholung_Graphenalgorithmen.Common
{
    internal class Mapper
    {
        public static Dictionary<Node, HashSet<Node>> ReduceMapToMinimumSpanningTree(Node root, Dictionary<Node, HashSet<Node>> map)
        {
            Dictionary<Node, HashSet<Node>> reducedMap = new Dictionary<Node, HashSet<Node>>();

            // Used to iterate through map
            List<Node> visited = new List<Node>();
            Stack<Node> q = new Stack<Node>(map.Count);

            // Initialize reducedMapx
            foreach (var item in map)
            {
                reducedMap[item.Key] = new HashSet<Node>();
            }

            // Push root Node on to Stack
            q.Push(root);

            while (q.Count > 0)
            {
                Node node = q.Pop();
                List<Node> neighbours = map[node].ToList();

                foreach (var neighbour in neighbours)
                {
                    if (!visited.Contains(neighbour) && !q.Contains(neighbour))
                    {
                        q.Push(neighbour);

                        // Save connection
                        reducedMap[node].Add(neighbour);
                        reducedMap[neighbour].Add(node);
                    }
                }
                visited.Add(node);
            }

            return reducedMap;
        }
    }
}
