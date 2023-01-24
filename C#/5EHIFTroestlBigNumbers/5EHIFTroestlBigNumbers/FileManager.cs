using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace _5EHIFTroestlBigNumbers
{
    internal class FileManager
    {
        #region Read Files
        public static Cluster ReadReceipts(string path)
        {
            // Initialize Cluster   
            Cluster cluster = new Cluster(120);

            using (var reader = new StreamReader(path))
            {
                // skip first line
                reader.ReadLine();

                // loop through all rows to read the values
                while (!reader.EndOfStream)
                {
                    // read and split line, ls => lineSplit
                    string[] ls = reader.ReadLine().Split(';');

                    // Create Receipt and add to list
                    cluster.ReceiptList.Add(new Receipt(
                        double.Parse(ls[0]),
                        double.Parse(ls[1])
                        ));
                }
            }

            return cluster;
        }
        #endregion
    }
}
