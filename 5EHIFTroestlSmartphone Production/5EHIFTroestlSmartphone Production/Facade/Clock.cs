using System.Timers;

namespace _5EHIFTroestlSmartphone_Production.Facade
{
    internal class Clock
    {
        #region Properties
        public Timer ProductionTimer { get; set; }
        #endregion

        #region Initialization
        public Clock()
        {
            ProductionTimer = new Timer();
        }
        #endregion

        #region Timer
        public void StartTimer()
        {
            ProductionTimer.Interval = 1000; // 1000 ms is one second
            ProductionTimer.Start();
        }
        public void StopProduction()
        {
            ProductionTimer.Stop();
        }
        #endregion
    }
}
