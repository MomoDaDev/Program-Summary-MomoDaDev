using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Breadth_first_search
{
    internal class Node
    {
        #region Progerties
        public string Id { get; set; }
        public int Distance { get; set; }
        public Node Previous { get; set; }
        #endregion
        
        #region Overrides
        /*
        public static bool operator ==(Node a, Node b)
        {
            if (a is null && b is null)
                return true;

            if (a is null)
                return false;

            return a.Equals(b);
        }

        public static bool operator !=(Node a, Node b)
        {
            return !(a == b);
        }
        */

        public override string ToString()
        {
            return "Id: " + Id;
        }
        #endregion
        
        #region Breadth First Tree
        //public static Dictionary<Node, HashSet<Node>> BFS(Node start, Dictionary<Node, HashSet<Node>> a)
        //{
        //    Dictionary<Node, HashSet<Node>> bft = new Dictionary<Node, HashSet<Node>>();
        //    List<Node> visited = new List<Node>();
        //    Queue<Node> q = new Queue<Node>(a.Count);
            
        //    // set level
        //    start.Distance = 0;

        //    // enqueue
        //    q.Enqueue(start);

        //    while (q.Count > 0)
        //    {
        //        Node node = q.Dequeue();
        //        List<Node> neighbours = a[node].ToList();
        //        bft.Add(node, new HashSet<Node>());

        //        foreach (var ne in neighbours)
        //        {
        //            if (!visited.Contains(ne) && !q.Contains(ne))
        //            {
        //                //ne.Distance = node.Distance + 1;
        //                //ne.Previous = node;
        //                q.Enqueue(ne);
        //                bft[node].Add(ne);
        //            }
        //        }
        //        visited.Add(node);
        //    }

        //    return bft;
        //}
        public static Dictionary<Node, HashSet<Node>> BFS(Node start, Dictionary<Node, HashSet<Node>> a)
        {
            Dictionary<Node, HashSet<Node>> bft = new Dictionary<Node, HashSet<Node>>();
            List<Node> visited = new List<Node>();
            Queue<Node> q = new Queue<Node>();

            start.Distance = 0;

            q.Enqueue(start);

            while (q.Count > 0)
            {
                Node node = q.Dequeue();
                List<Node> neighbours = a[node].ToList();
                bft.Add(node, new HashSet<Node>());

                foreach (var ne in neighbours)
                {
                    if (!visited.Contains(ne) && !q.Contains(ne))
                    {
                        ne.Distance = node.Distance + 1;
                        ne.Previous = node;
                        bft[node].Add(ne);
                        q.Enqueue(ne);
                    }
                }
                visited.Add(node);
            }

            return bft;
        }



        public override int GetHashCode()
        {
            return Id.GetHashCode();
        }

        public override bool Equals(object obj)
        {
            return obj is Node node &&
                   Id == node.Id;
        }
        #endregion


    }
}
