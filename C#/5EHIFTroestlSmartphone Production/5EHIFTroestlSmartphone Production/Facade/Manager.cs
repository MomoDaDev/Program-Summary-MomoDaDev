using System;
using System.Collections.Generic;
using System.Timers;

namespace _5EHIFTroestlSmartphone_Production.Facade
{
    internal class Manager
    {
        #region Properties
        public static Random rnd = new Random();
        private Clock clock { get; set; } = null;
        private List<ProductionLine> productionLines { get; set; } = null;
        private WarehouseDepartment warehouseDepartment { get; set; } = null;
        private Warehouse warehouse { get; set; } = null;
        private SalesDepartment salesDepartment { get; set; } = null;
        #endregion

        #region Initialization
        public Manager()
        {
            StartProduction();
        }
        #endregion

        #region Production Management
        public void StartProduction()
        {
            //Clock Management
            InitializeClock();

            // Production Line Management
            InitializeProductionLines();
            SubscribeToClock();

            // Warehouse Department Management
            InitializeWarehouseDepartment();
            SubscribeToProductionLine();

            // Warehouse Management
            InitializeWarehouse();
            SubscribeToWarehouseDepartment();

            // Sales Department Management
            InitializeSalesDepartment();
            SubscribeToWarehouse();
        }
        #endregion

        #region Clock Management
        private void InitializeClock()
        {
            clock = new Clock();
            clock.StartTimer();
        }
        #endregion

        #region ProductionLine Management
        private void InitializeProductionLines()
        {
            productionLines = new List<ProductionLine>();

            productionLines.Add(new ProductionLine("Production A"));
            productionLines.Add(new ProductionLine("Production B"));
            productionLines.Add(new ProductionLine("Production C"));
        }
        private void SubscribeToClock()
        {
            foreach (var pl in productionLines)
            {
                clock.ProductionTimer.Elapsed += new ElapsedEventHandler(pl.ProductionTimerEvent);
            }
        }
        #endregion

        #region WarehouseDepartment Management
        private void InitializeWarehouseDepartment()
        {
            warehouseDepartment = new WarehouseDepartment("Warehouse Department A");
        }
        private void SubscribeToProductionLine()
        {
            foreach (var pl in productionLines)
            {
                pl.FullProductionLineReached += warehouseDepartment.c_FullProductionLineReached;
            }
        }
        #endregion

        #region Warehouse Management
        private void InitializeWarehouse()
        {
            warehouse = new Warehouse("Warehouse A");
        }
        private void SubscribeToWarehouseDepartment()
        {
            warehouseDepartment.FullWarehouseDepartmentReached += warehouse.c_FullWarehouseDepartmentReached;
        }
        #endregion

        #region SalesDepartment Management
        private void InitializeSalesDepartment()
        {
            salesDepartment = new SalesDepartment();
        }
        private void SubscribeToWarehouse()
        {
            warehouse.FullWarehouseReached += salesDepartment.c_FullWarehouseReached;
        }
        #endregion
    }
}
