using System;
using System.Collections.Generic;
using System.Linq;
using System.Text.Json;
using System.Threading.Tasks;

namespace _5EHIFTroestlStreckenundPunkte
{
    internal class Punkt
    {
        #region Properties
        public int X { get; private set; }
        public int Y { get; private set; }
        #endregion

        #region Initialize
        public Punkt(int x, int y)
        {
            X = x;
            Y = y;
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
