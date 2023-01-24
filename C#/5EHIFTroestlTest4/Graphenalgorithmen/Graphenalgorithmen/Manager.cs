namespace Graphenalgorithmen
{
    internal class Manager
    {
        public void UseGraph()
        {
            // Initialize the graph
            Graph graph = new Graph(new List<Node>(), new List<Edge>());

            // Generate a 3x3 field
            int n = 7;
            graph.GenerateField(n);

            // Print the Nodes and Edges of the graph
            Console.WriteLine(graph.ToString());
            Console.WriteLine("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");

            // GetAllPaths with a practically guaranteed chance of finding all paths
            List<List<Node>> paths = graph.GetAllPaths(graph.Nodes[6], graph.Nodes[5], 1000);

            // Print all found paths
            Console.WriteLine("All found paths:");
            int i = 1;
            foreach (var path in paths)
            {
                if (i != 1)
                    Console.WriteLine("-------------------");
                Console.WriteLine($"Path {i}:");
                foreach (var node in path)
                {
                    Console.WriteLine($"{node.Id} --> {node.Next.Id}");
                }
                i++;
            }
            Console.WriteLine("-------------------");
            Console.WriteLine($"Total Paths: {paths.Count}");
            Console.WriteLine("-------------------");
            Console.WriteLine("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");

            // Get shortest path
            List<Node> shortestPath = graph.GetShortestPath(graph.Nodes[6], graph.Nodes[5]);

            // Print shortest path
            Console.WriteLine($"Shortest Path from {graph.Nodes[6].Id} to {graph.Nodes[5].Id}");
            for (i = shortestPath.Count - 1; i >= 0; i--)
            {
                Console.WriteLine($"{shortestPath[i].Predecessor.Id} --> {shortestPath[i].Id} (Distance: {shortestPath[i].Distance})");
            }
        }
    }
}
