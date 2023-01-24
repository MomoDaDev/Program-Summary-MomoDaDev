using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Breadth_first_search
{
    internal class AdjazenzList
    {
        public Node StartNode {  get; set; }
        public Dictionary<Node, HashSet<Node>> adjazenzList { get; set; }

        public AdjazenzList(Node startNode, Dictionary<Node, HashSet<Node>> adjazenzList)
        {
            StartNode = startNode;
            this.adjazenzList = adjazenzList;
        }
    }
}
