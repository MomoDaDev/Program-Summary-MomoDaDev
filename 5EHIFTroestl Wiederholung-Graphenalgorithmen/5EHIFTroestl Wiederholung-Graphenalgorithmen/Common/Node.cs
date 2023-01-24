using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace _5EHIFTroestl_Wiederholung_Graphenalgorithmen.Common
{
    internal class Node
    {
        #region Properties
        public string Name { get; set; }
        #endregion

        #region Initialization
        public Node(string name)
        {
            this.Name = name;
        }
        #endregion


        public override bool Equals(object? obj)
        {
            if (obj == null || !(obj is Node))
                return false;

            Node other = (Node)obj;
            return String.Equals(this.Name, other.Name);
             
        }

        public override int GetHashCode()
        {
            return Name.GetHashCode();
        }

        public override string ToString()
        {
            return JsonConvert.SerializeObject(this);
        }
    }
}
