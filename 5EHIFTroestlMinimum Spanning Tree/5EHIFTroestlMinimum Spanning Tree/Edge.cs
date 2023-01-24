using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Text.Json;
using System.Threading.Tasks;

namespace _5EHIFTroestlMinimum_Spanning_Tree
{
    internal class Edge
    {
        #region Properties
        public Node Src { get; set; }
        public Node Dest { get; set; }
        public double Weight { get; set; }
        #endregion

        #region Initialization
        public Edge(Node src, Node dest, double weight)
        {
            Src = src;
            Dest = dest;
            Weight = weight;
        }
        #endregion

        #region Overrides
        public override bool Equals(object obj)
        {
            if (obj == null || !(obj is Edge))
                return false;
            Edge other = obj as Edge;
            return Weight == other.Weight && Src.Equals(other.Src) && Dest.Equals(other.Dest);

        }

        public override int GetHashCode()
        {
            return Src.GetHashCode();
        }

        public override string ToString()
        {
            return JsonSerializer.Serialize(this);
        }
        #endregion
    }
}
