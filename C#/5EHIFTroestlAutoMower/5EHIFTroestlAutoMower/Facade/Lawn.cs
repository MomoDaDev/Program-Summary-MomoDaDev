using _5EHIFTroestlAutoMower.Common;
using System;
using System.Collections.Generic;

namespace _5EHIFTroestlAutoMower.Facade
{
    class Lawn
    {
        #region Properties
        private Random rnd = new Random();
        public Mower LawnMower { get; private set; }
        public List<Field> Fields { get; set; }
        public int XLength { get; set; }
        public int YLength { get; set; }
        #endregion

        #region Initialize
        public Lawn(Mower lawnMower, int xLength, int yLength)
        {
            Fields = new List<Field>();
            LawnMower = lawnMower;
            XLength = xLength;
            YLength = yLength;
        }
        #endregion

        #region Calculations
        public void GrowLawn()
        {
            for (int i = XLength - 1; i >= 0; i--)
            {
                for (int j = 0; j < YLength; j++)
                {
                    Fields.Add(new Field(i, j, 1, 1, 0));
                }
            }
        }

        public void StartCuttingLawn(int delay)
        {
            while (!LawnMowed())
            {
                MoveMower(delay);
            }
        }

        private void MoveMower(int delay)
        {
            double updatedPosX = LawnMower.PosX + Math.Cos(LawnMower.Rotation);
            double updatedPosY = LawnMower.PosY + Math.Sin(LawnMower.Rotation);

            if (updatedPosX < 0 + LawnMower.Radius || 
                updatedPosY < 0 + LawnMower.Radius || 
                updatedPosX + LawnMower.Radius > XLength || 
                updatedPosY + LawnMower.Radius > YLength)
            { // Rotate
                LawnMower.Rotation = LawnMower.Rotation + 90 + rnd.Next(0, 180);
            } else
            { // Move
                double oldPosX = LawnMower.PosX;
                double oldPosY = LawnMower.PosY;
                LawnMower.PosX = updatedPosX;
                LawnMower.PosY = updatedPosY;
                UpdateFields();
                Visualizer.DrawCircle(LawnMower.Radius, Fields, oldPosX, oldPosY, updatedPosX, updatedPosY);
            }
            System.Threading.Thread.Sleep(delay);
        }

        private void UpdateFields()
        {
            foreach (Field field in Fields)
            {
                if (CollisionChecker.CircleRect((float)LawnMower.PosX, (float)LawnMower.PosY, (float)LawnMower.Radius, field.PosX, field.PosY, field.Width, field.Height))
                {
                    field.CountMowed++;
                    Visualizer.UpdateField(field);
                }
            }
        }

        private bool LawnMowed()
        {
            bool fieldMowed = true;
            Fields.ForEach(delegate (Field field)
            {
                fieldMowed &= field.Mowed;
            });
            return fieldMowed;

        }
        #endregion

        #region ToString
        public string[,] GetField()
        {
            string[,] lawn = new string[XLength, YLength];

            foreach (Field field in Fields)
            {
                lawn[field.PosX, field.PosY] = field.CountMowed == 0 ? "O" : "X";
            }

            return lawn;
        }
        #endregion
    }
}
