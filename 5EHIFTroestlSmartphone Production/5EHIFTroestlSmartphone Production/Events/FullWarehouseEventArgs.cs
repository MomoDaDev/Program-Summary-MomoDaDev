using System;

using _5EHIFTroestlSmartphone_Production.Facade;

namespace _5EHIFTroestlSmartphone_Production.Events
{
    internal class FullWarehouseEventArgs
    {
        public Warehouse Instance { get; set; }
        public DateTime Timestamp { get; set; }
    }
}
