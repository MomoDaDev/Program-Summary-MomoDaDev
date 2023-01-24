using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace _5EHIFTroestlBigNumbers
{
    internal class RegressionLine
    {
        #region Properties
        public double K { get; set; }
        public double D { get; set; }
        #endregion

        #region Initialization
        public RegressionLine(double k, double d)
        {
            K = k;
            D = d;
        }
        #endregion

        #region Overrides
        public override string ToString()
        {
            return $"RegressionLine: [ K: {K}; D:{D} ]";
        }
        #endregion
    }
}
