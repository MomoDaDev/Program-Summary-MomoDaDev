using _5EHIFTroestl_Wiederholung_Graphenalgorithmen.Common;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace _5EHIFTroestl_Wiederholung_Graphenalgorithmen.Facade
{
    internal class Printer
    {
        public static string MapToString(Dictionary<Node, HashSet<Node>> map)
        {
            return String.Join("\n", map.Select(x => $"Node: {x.Key}; Neighbours: {NeighboursToString(x.Value)}"));
        }
        
        private static string NeighboursToString(HashSet<Node> neighbours)
        {
            return String.Join(", ", neighbours.Select(x => x.Name));
        }
    }
}
