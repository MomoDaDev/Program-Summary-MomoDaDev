using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace _5EHIFTroestlBigNumbers
{
    internal class Receipt
    {
        #region Properties
        /// <summary>
        /// Effort calculated in hours
        /// </summary>
        public double Effort { get; set; }
        /// <summary>
        /// Total costs
        /// </summary>
        public double Costs { get; set; }
        #endregion

        #region Initialization
        public Receipt(double effort, double costs)
        {
            Effort = effort;
            Costs = costs;
        }
        #endregion

        #region Overrides
        public override bool Equals(object obj)
        {
            if (obj == null || !(obj is Receipt))
                return false;

            Receipt other = obj as Receipt;
            return Double.Equals(Effort, other.Effort) && Double.Equals(Costs, other.Costs);
        }

        public override int GetHashCode()
        {
            return Costs.GetHashCode();
        }

        public override string ToString()
        {
            return $"Receipt: [ Effort: {Effort}h; Costs: {Costs}€ ];";
        }
        #endregion
    }
}
