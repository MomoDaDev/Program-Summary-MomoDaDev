using System;
using System.Collections.Generic;
using System.Linq;

using _5EHIFTroestlSmartphone_Production.Events;
using _5EHIFTroestlSmartphone_Production.Threading;

namespace _5EHIFTroestlSmartphone_Production.Facade
{
    internal class Warehouse
    {
        #region Properties
        public string Name { get; set; }
        public Synchronized<Dictionary<DateTime, List<Smartphone>>> LogEntries { get; set; } = null;
        #endregion

        #region Initialization
        public Warehouse(string name)
        {
            this.Name = name;
            LogEntries = new Synchronized<Dictionary<DateTime, List<Smartphone>>>();
            LogEntries.Value = new Dictionary<DateTime, List<Smartphone>>();
        }
        #endregion

        #region Events
        private void FireNotification()
        {
            FullWarehouseEventArgs args = new FullWarehouseEventArgs();
            args.Instance = this;
            args.Timestamp = DateTime.Now;
            OnFullWarehouseReached(args);
        }

        protected virtual void OnFullWarehouseReached(FullWarehouseEventArgs e)
        {
            FullWarehouseReached?.Invoke(this, e);
        }

        public event EventHandler<FullWarehouseEventArgs> FullWarehouseReached;
        #endregion

        #region Subscribtions
        public void c_FullWarehouseDepartmentReached(object sender, FullWarehouseDepartmentEventArgs e)
        {
            LogEntries.Value.Add(e.Timestamp, e.Instance.TransferSmartphones().ToList());
            FireNotification();
        }
        #endregion
    }
}
