using Newtonsoft.Json;
using System;

namespace _5EHIFTroestlSmartphone_Production.Facade
{
    internal class Smartphone
    {
        #region Properties
        public Guid Id {  get; private set; }
        public string Name {  get; set; }
        #endregion

        #region Initialization
        public Smartphone(Guid id)
        {
            Id = id;
        }

        public Smartphone(Guid id, string name) : this(id)
        {
            Name = name;
        }
        #endregion

        #region Overrides
        public override string ToString()
          {
              return JsonConvert.SerializeObject(this);
          }
        #endregion
    }
}
