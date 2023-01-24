using System;
using System.Collections.Generic;
using System.Linq;

using _5EHIFTroestlSmartphone_Production.Events;

namespace _5EHIFTroestlSmartphone_Production.Facade
{
    internal class SalesDepartment
    {
        #region Subscribtions
        public void c_FullWarehouseReached(object sender, FullWarehouseEventArgs e)
        {
            Console.Clear();
            Console.WriteLine($"SalesDepartment:\n" +
                $"{e.Instance.Name}:\n" +
                $"{String.Join("\n", e.Instance.LogEntries.Value.Select(x => $"{x.Key.ToString()}:\n{PrintSmartphones(x.Value)}"))}");
        }
        #endregion

        #region Print
        private string PrintSmartphones(List<Smartphone> smartphones)
        {
            return String.Join("\n", smartphones.Select(x => $"({x.Id.ToString().Substring(0, 8)}){x.Name}"));
        }
        #endregion
    }
}
