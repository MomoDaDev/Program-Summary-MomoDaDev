using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace _5EHIFTroestlBigNumbers
{
    internal class Cluster
    {
        #region Proterties
        public List<Receipt> ReceiptList { get; set; } = null;
        public RegressionLine RegressionLine { get; set; } = null;
        #endregion

        #region Initialization
        public Cluster(int listSize)
        {
            this.ReceiptList = new List<Receipt>(listSize);
        }
        #endregion

        #region Data Organizing
        public void SortReceiptsByEffort()
        {
            ReceiptList = ReceiptList.OrderBy(r => r.Costs).ThenBy(r => r.Effort).ToList();
        }
        public void SortReceiptsByCosts()
        {
            ReceiptList = ReceiptList.OrderBy(r => r.Effort).ThenBy(r => r.Costs).ToList();
        }

        public void CalcualteRegressionLine()
        {
            // Those are the variables for the RegressioLine
            double k, d;

            // Get the lowest 1 % of Costs
            SortReceiptsByCosts();

            List<double> first4Percent = new List<double>();

            for (int i = 0; i < ReceiptList.Count * 0.01; i++)
            {
                first4Percent.Add(ReceiptList[i].Costs);
            }

            // Calculate d
            d = first4Percent.Average();

            // Calculate k
            k = ReceiptList.Average(c => c.Costs) / ReceiptList.Average(e => e.Effort);

            // set RegressionLine
            this.RegressionLine = new RegressionLine(k, d);
        }
        #endregion
    }
}
