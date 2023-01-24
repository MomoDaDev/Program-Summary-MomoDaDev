using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace _5EHIFTroestlAutoMower.Facade
{
    class Field
    {
        #region Properties
        public int PosX { get; private set; }
        public int PosY { get; private set; }
        public int Width { get; private set; }
        public int Height { get; private set; }
        private int countMowed;
        public int CountMowed
        {
            get { return countMowed; }
            set
            {
                countMowed = value;
                Mowed = countMowed != 0;
            }
        }
        public bool Mowed { get; private set; }
        #endregion

        #region Initialize
        public Field(int posX, int posY, int width, int height, int countMowed)
        {
            PosX = posX;
            PosY = posY;
            Width = width;
            Height = height;
            this.CountMowed = countMowed;
            Mowed = false;
        }
        #endregion
    }
}
