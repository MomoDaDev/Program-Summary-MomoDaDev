using System;
using System.Collections.Generic;
using System.Linq;
using System.Text.Json;
using System.Threading.Tasks;

namespace _5EHIFTroestlStreckenundPunkte
{
    internal class Strecke
    {
        #region Properties
        public Punkt P1 { get; private set; }
        public Punkt P2 { get; private set; }
        #endregion

        #region Initialization
        public Strecke(Punkt p1, Punkt p2)
        {
            P1 = p1;
            P2 = p2;
        }
        #endregion

        #region Calculations
        public double GetLength()
        {
            // sqrt ( (P1.X-P2.X)^2 + (P1.Y-P2.Y)^2 )
            return Math.Sqrt(Math.Pow((P1.X - P2.X), 2) + Math.Pow((P1.Y - P2.Y), 2));
        }
        #endregion

        #region Overrides
        public override string ToString()
        {
            return JsonSerializer.Serialize(this);
        }
        #endregion
    }
}
