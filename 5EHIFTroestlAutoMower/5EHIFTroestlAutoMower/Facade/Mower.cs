using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace _5EHIFTroestlAutoMower.Facade
{
    class Mower
    {
        #region Properties
        public double Radius { get; private set; }
        public double PosX { get; set; }
        public double PosY { get; set; }
        private double rotation;
        public double Rotation
        {
            get { return rotation; }
            set
            {
                rotation = value % 360;
            }
        }
        #endregion

        #region Initialize
        public Mower(double radius, double posX, double posY, double rotation)
        {
            Radius = radius;
            PosX = posX;
            PosY = posY;
            Rotation = rotation;
        }
        #endregion
    }
}
