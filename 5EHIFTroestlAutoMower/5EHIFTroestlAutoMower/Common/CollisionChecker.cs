using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace _5EHIFTroestlAutoMower.Common
{
    class CollisionChecker
    {
        /// <summary>
        /// returns true if the given circle collides with the given rectangle
        /// </summary>
        /// <param name="cx">X position of circle</param>
        /// <param name="cy">Y position of circle</param>
        /// <param name="radius">radius of circle</param>
        /// <param name="rx">X position of rectangle</param>
        /// <param name="ry">Y position of rectangle</param>
        /// <param name="rw">width of rectangle</param>
        /// <param name="rh">height of rectangle</param>
        /// <returns></returns>
        public static bool CircleRect(float cx, float cy, float radius, float rx, float ry, float rw, float rh)
        {

            // temporary variables to set edges for testing
            float testX = cx;
            float testY = cy;

            // which edge is closest?
            if (cx < rx) testX = rx;      // test left edge
            else if (cx > rx + rw) testX = rx + rw;   // right edge
            if (cy < ry) testY = ry;      // top edge
            else if (cy > ry + rh) testY = ry + rh;   // bottom edge

            // get distance from closest edges
            float distX = cx - testX;
            float distY = cy - testY;
            float distance = (float)Math.Sqrt((distX * distX) + (distY * distY));

            // if the distance is less than the radius, collision!
            if (distance <= radius)
            {
                return true;
            }
            return false;
        }
    }
}
