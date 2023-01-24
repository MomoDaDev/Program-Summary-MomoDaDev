namespace Graphenalgorithmen
{
    internal class Graph
    {
        #region Properties
        public static Random random = new Random();
        public List<Node> Nodes { get; set; }
        public List<Edge> Edges { get; set; }
        #endregion

        #region Initialization
        public Graph(List<Node> nodes, List<Edge> edges)
        {
            Nodes = nodes;
            Edges = edges;
        }
        #endregion

        #region Field Generating
        public void GenerateField(int n)
        {
            // Initialize Nodes and Edges with calculated size
            Nodes = new List<Node>(n * n);
            Edges = new List<Edge>((n - 1) * n * 2);

            // Generate Nodes
            for (int i = 1; i <= n; i++)
                for (int j = 1; j <= n; j++)
                    Nodes.Add(new Node($"{i}{j.ToStr()}", i, j));

            // Generate Edges
            // Connect Collumns
            for (int i = 1; i <= n; i++)
                for (int j = 1; j <= n - 1; j++)
                    Edges.Add(new Edge(Nodes.First(x => x.X == i && x.Y == j), Nodes.First(x => x.X == i && x.Y == j + 1)));

            // Connect Rows
            for (int i = 1; i <= n - 1; i++)
                for (int j = 1; j <= n; j++)
                    Edges.Add(new Edge(Nodes.First(x => x.X == i && x.Y == j), Nodes.First(x => x.X == i + 1 && x.Y == j)));
        }
        #endregion

        #region Path Finding
        /// <summary>
        /// Es ist sehr wenig Zeit für das Beispiel
        /// Man iteriert vom Startpunkt aus in alle Richtungen aber nie zurück zu den Nodes,
        /// wo man bereits gewesen ist.Falls der Knoten das Ziel ist, dann höre auf.
        /// Falls man nicht mehr weiter gehen kann aber das Ziel nicht erreicht wurde, dann
        /// schmeiße den Weg weg.
        /// 
        /// Es wird umgesetzt, indem man eine List mit Nodes führt und wenn sich die Wege bei
        /// zwei Nachbarn trennen, dann wird ein zufälliger Weg gewählt.
        /// </summary>
        /// <param name="start"></param>
        /// <param name="dest"></param>
        /// <param name="precision"></param>
        /// <returns>Returns a List of all found paths</returns>
        public List<List<Node>> GetAllPaths(Node start, Node dest, int precision)
        {
            // List of all paths
            List<List<Node>> correctPaths = new List<List<Node>>();

            for (int i = 0; i < Edges.Count * precision; i++)
            {
                // Queue for the next upcoming node
                Queue<Node> q = new Queue<Node>();

                // List of all already visited nodes for this set of iterations
                List<Node> visited = new List<Node>();

                // A List to store the path
                List<Node> path = new List<Node>();

                // Set the Start node as the first upcoming node
                q.Enqueue(start);

                // Loop through as long as there are nodes in the queue
                while (q.Count > 0)
                {
                    // Get node from queue
                    Node node = q.Dequeue();
                    List<Node> neighbours = new List<Node>();

                    // Find all neighbours from the Node node with the help of the edges
                    foreach (var edge in Edges)
                    {
                        if (edge.N1.Equals(node))
                            neighbours.Add(edge.N2);
                        else if (edge.N2.Equals(node))
                            neighbours.Add(edge.N1);
                    }

                    // Get all neighbours that are valid to continue iterating through them
                    Node[] arrne = neighbours.Where(x => !visited.Contains(x) && !q.Contains(x)).ToArray();

                    // If no neighbours are found then stop
                    if (arrne.Length == 0)
                        break;

                    // Pick a random neighbour
                    Node ne = arrne[random.Next(0, arrne.Length)];

                    // Enqueue the picked neighbour for the net upcoming node
                    q.Enqueue(ne);

                    // Create a deep copy to store in the found path
                    Node n = new Node(node.Id);
                    n.Next = new Node(ne.Id);
                    n.Distance = Edges.First(x => (x.N1.Equals(node) && x.N2.Equals(ne)) ||
                        (x.N1.Equals(ne) && x.N2.Equals(node))).Distance;

                    // Add n (the deep copy) to the path
                    path.Add(n);

                    // If the destination has been reached then stop
                    if (ne.Equals(dest))
                        break;

                    // Mark node as already visited
                    visited.Add(node);
                }

                // If the path hasn't been found yet and it is valid then add to the correct paths
                if (IsPathValid(path, correctPaths, dest))
                    correctPaths.Add(path);
            }
            return correctPaths;
        }

        private bool IsPathValid(List<Node> path, List<List<Node>> correctPaths, Node dest)
        {
            foreach (var cp in correctPaths)
            {
                // Check if the path already exists in correctPaths
                if (cp.Count == path.Count)
                {
                    bool samePath = true;

                    for (int i = 0; i < cp.Count; i++)
                    {
                        if (!cp[i].Equals(path[i]))
                            samePath = false;
                        if (!cp[i].Next.Equals(path[i].Next))
                            samePath = false;
                    }

                    if (samePath)
                        return false;
                }
            }

            // Check if the end of the path is the destination
            if (path.Last().Next.Id == dest.Id)
                return true;
            return false;
        }
        #endregion

        #region Dijkstra Algorithm
        public List<Node> GetShortestPath(Node start, Node dest)
        {
            ResetNodes();
            InitializeNeighbours();
            start.Predecessor = start;

            DijkstraRecursive(start, 0);

            Node n = dest;
            List<Node> shortestPath = new List<Node>();

            while (n != start)
            {
                shortestPath.Add(n);
                n = n.Predecessor;
            }

            return shortestPath;
        }

        private void DijkstraRecursive(Node n, int distance)
        {
            foreach (var ne in n.Neighbours)
            {
                // Get current edge between n and ne
                Edge edge = Edges.First(x => (x.N1 == n && x.N2 == ne) || (x.N1 == ne && x.N2 == n));

                if (distance + edge.Distance < ne.Distance)
                {
                    ne.Distance = distance + edge.Distance;
                    ne.Predecessor = n;
                    DijkstraRecursive(ne, ne.Distance);
                }
            }
        }

        private void ResetNodes()
        {
            foreach (var node in Nodes)
            {
                node.Distance = int.MaxValue;
                node.Predecessor = null;
                node.Next = null;
                node.Neighbours = new HashSet<Node>();
            }
        }

        private void InitializeNeighbours()
        {
            foreach (var edge in Edges)
            {
                edge.N1.Neighbours.Add(edge.N2);
                edge.N2.Neighbours.Add(edge.N1);
            }
        }
        #endregion

        #region Overrides
        public override string ToString()
        {
            return "Nodes:\n" + String.Join("\n", Nodes.Select(x => x.Id)) + "\n" +
                "Edges:\n" + String.Join("\n", Edges.Select(x => x.N1.Id + "-" + x.N2.Id));
        }
        #endregion
    }
}
