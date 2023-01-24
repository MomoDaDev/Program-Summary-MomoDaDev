using System;
using System.Collections.Generic;
using System.Timers;

using _5EHIFTroestlSmartphone_Production.Common;
using _5EHIFTroestlSmartphone_Production.Events;
using _5EHIFTroestlSmartphone_Production.Threading;

namespace _5EHIFTroestlSmartphone_Production.Facade
{
    /// <summary>
    /// Produces smartphones with a maximum capacity of 5 smartphones
    /// </summary>
    internal class ProductionLine
    {
        #region Properties
        public string Name {  get; set; }
        private Synchronized<List<Smartphone>> smartphones { get; set; }
        private DateTime nextPhone;
        private bool producingPhone;
        private DateTime workerArivalTime;
        private bool workerOnTheWay;
        #endregion

        #region Initialization
        public ProductionLine(string name)
        {
            this.Name = name;
            this.smartphones = new Synchronized<List<Smartphone>>();
            this.smartphones.Value = new List<Smartphone>();
            this.producingPhone = false;
            this.workerOnTheWay = false;
        }
        #endregion

        #region Smartphone Production
        public void ProductionTimerEvent(object source, ElapsedEventArgs e)
        {
            // Kickstarts the production if it isn't running
            if (!producingPhone)
            {
                producingPhone = true;
                MakePhone();
            }
            CheckPhoneproduction();
        }
        private void MakePhone()
        {
            nextPhone = DateTime.Now.AddMilliseconds(Manager.rnd.Next(1000, 3001));
        }
        private Smartphone CollectPhone()
        {
            if (nextPhone <= DateTime.Now)
                return new Smartphone(UUIDGenerator.GetUUID(), "Ich hab ein Iphone und du hast kein Phone");
            else
                return null;
        }
        private void CheckPhoneproduction()
        {
            // Tries collecting a new Smartphone
            // CollectPhone() returns null if the Smartphone is not finished yet
            Smartphone phone = CollectPhone();
            if (phone != null)
            {
                smartphones.Value.Add(phone);
                MakePhone();

                // Check if there are at least 5 smartphones
                ManageWorker();

            }
        }
        #endregion

        #region Delivery
        private void ManageWorker()
        {
            if (smartphones.Value.Count >= 5)
                if (workerOnTheWay)
                {
                    if (DateTime.Now >= workerArivalTime)
                    {
                        FireNotification();
                        workerOnTheWay = false;
                    }
                }
                else
                    SendWorker();
        }
        private void SendWorker()
        {
            workerArivalTime = DateTime.Now.AddMilliseconds(Manager.rnd.Next(2000, 5001));
            workerOnTheWay = true;
        }
        public Smartphone[] TransferSmartphones()
        {
            Smartphone[] currentSmartphones = smartphones.Value.ToArray();
            foreach (Smartphone smartphone in currentSmartphones)
            {
                smartphones.Value.Remove(smartphone);
            }
            return currentSmartphones;
        }
        #endregion

        #region Events
        private void FireNotification()
        {
            FullProductionLineEventArgs args = new FullProductionLineEventArgs();
            args.Instance = this;
            args.Timestamp = DateTime.Now;
            OnFullProductionLineReached(args);
        }

        protected virtual void OnFullProductionLineReached(FullProductionLineEventArgs e)
        { 
            FullProductionLineReached?.Invoke(this, e);
        }

        public event EventHandler<FullProductionLineEventArgs> FullProductionLineReached;
        #endregion

        #region Overrides
        public override string ToString()
        {
            return $"{Name}; {smartphones.Value.Count}";
        }
        #endregion
    }
}
