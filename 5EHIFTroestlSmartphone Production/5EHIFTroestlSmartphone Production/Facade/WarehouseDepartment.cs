using System;
using System.Collections.Generic;
using System.Linq;

using _5EHIFTroestlSmartphone_Production.Events;
using _5EHIFTroestlSmartphone_Production.Threading;

namespace _5EHIFTroestlSmartphone_Production.Facade
{
    internal class WarehouseDepartment
    {
        #region Properties
        public string Name { get; set; }
        private Synchronized<List<Smartphone>> smartphones { get; set; } = null;
        #endregion

        #region Initialization
        public WarehouseDepartment(string name)
        {
            this.Name = name;
            this.smartphones = new Synchronized<List<Smartphone>>();
            this.smartphones.Value = new List<Smartphone>();
        }
        #endregion

        #region Inventory
        private void CheckInventory()
        {
            if (smartphones.Value.Count >= 20)
            {
                FireNotification();
            }
        }
        #endregion

        #region Delivery
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
            FullWarehouseDepartmentEventArgs args = new FullWarehouseDepartmentEventArgs();
            args.Instance = this;
            args.Timestamp = DateTime.Now;
            OnFullWarehouseDepartmentReached(args);
        }

        protected virtual void OnFullWarehouseDepartmentReached(FullWarehouseDepartmentEventArgs e)
        {
            FullWarehouseDepartmentReached?.Invoke(this, e);
        }

        public event EventHandler<FullWarehouseDepartmentEventArgs> FullWarehouseDepartmentReached;
        #endregion

        #region Subscribtions
        public void c_FullProductionLineReached(object sender, FullProductionLineEventArgs e)
        {
            Smartphone[] deliveredSmartphones = e.Instance.TransferSmartphones();
            foreach (Smartphone smartphone in deliveredSmartphones)
            {
                smartphones.Value.Add(smartphone);
                CheckInventory();
            }
        }
        #endregion

        #region Overrides
        public override string ToString()
        {
            return $"{Name}; \nPhones:\n" +
                $"{String.Join("\n", smartphones.Value.Select(x => $"({x.Id.ToString().Substring(0, 8)}){x.Name}"))}";
        }
        #endregion
    }
}
