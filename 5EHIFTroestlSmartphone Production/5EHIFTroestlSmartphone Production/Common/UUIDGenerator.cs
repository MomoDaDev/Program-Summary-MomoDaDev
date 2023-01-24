using System;

namespace _5EHIFTroestlSmartphone_Production.Common
{
    internal class UUIDGenerator
    {
        public static Guid GetUUID()
        {
            return Guid.NewGuid();
        }
    }
}
