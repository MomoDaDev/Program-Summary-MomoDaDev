using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Text.Json;
using System.Threading.Tasks;

namespace _5EHIFTroestlMinimum_Spanning_Tree
{
    internal class Node
    {
        #region Progerties
        public int Id { get; set; }
        public int X { get; set; }
        public int Y { get; set; }
        public HashSet<Node> Neighbours = null;
        #endregion

        #region Initialization
        public Node(int id, int x, int y)
        {
            Id = id;
            X = x;
            Y = y;
            Neighbours = new HashSet<Node>();
        }
        #endregion

        #region Mapping
        public double GetDistance(Node n)
        {
            return Math.Sqrt(Math.Pow(X - n.X, 2) + Math.Pow(Y - n.Y, 2));
        }
        #endregion

        #region Overrides
        public override bool Equals(object obj)
        {
            if (obj == null || !(obj is Node))
                return false;
            Node other = obj as Node;
            return X == other.X && Y == other.Y;

        }

        public override int GetHashCode()
        {
            return Id.GetHashCode();
        }

        public override string ToString()
        {
            return JsonSerializer.Serialize(this);
        }
        #endregion
    }
}
