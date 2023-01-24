using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Breadth_first_search
{
    internal class FileSystem
    {
        public static AdjazenzList ReadFileToAdjazenzList(string path)
        {
            var result = new Dictionary<Node, HashSet<Node>>();
            Node startNode = null;

            using (var sr = new StreamReader(path))
            {
                while (!sr.EndOfStream)
                {
                    var line = sr.ReadLine().Trim();
                    if (line.Replace(" ", "") == "#Startknoten")
                    {
                        line = sr.ReadLine();
                        startNode = new Node() { Id = line };
                    }
                    if (line.Replace(" ", "") == "#KantenE")
                    {
                        while (!sr.EndOfStream)
                        {
                            line = sr.ReadLine().Trim();
                            line = line.Replace("(", "").Replace(")", "");

                            var strA = line.Split(',')[0];
                            var strB = line.Split(',')[1];

                            Node a = new Node() { Id = strA };
                            Node b = new Node() { Id = strB };

                            if (!result.ContainsKey(a))
                                result.Add(a, new HashSet<Node>());
                            result[a].Add(b);

                            if (!result.ContainsKey(b))
                                result.Add(b, new HashSet<Node>());
                            result[b].Add(a);
                        }
                    }
                }
            }
            return new AdjazenzList(startNode, result);
        }
    }
}
