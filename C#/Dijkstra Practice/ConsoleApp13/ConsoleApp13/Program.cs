using ConsoleApp13;

Node n0 = new Node("0", new HashSet<Node>());
Node n1 = new Node("1", new HashSet<Node>());
Node n2 = new Node("2", new HashSet<Node>());
Node n3 = new Node("3", new HashSet<Node>());
Node n4 = new Node("4", new HashSet<Node>());
Node n5 = new Node("5", new HashSet<Node>());
Node n6 = new Node("6", new HashSet<Node>());
Node n7 = new Node("7", new HashSet<Node>());
Node n8 = new Node("8", new HashSet<Node>());

Edge e00 = new Edge(n0, n1, 4);
Edge e01 = new Edge(n0, n7, 8);
Edge e02 = new Edge(n1, n7, 11);
Edge e03 = new Edge(n1, n2, 8);
Edge e04 = new Edge(n2, n8, 2);
Edge e05 = new Edge(n2, n5, 4);
Edge e06 = new Edge(n2, n3, 7);
Edge e07 = new Edge(n3, n5, 14);
Edge e08 = new Edge(n3, n4, 9);
Edge e09 = new Edge(n4, n5, 10);
Edge e10 = new Edge(n5, n6, 2);
Edge e11 = new Edge(n6, n8, 6);
Edge e12 = new Edge(n6, n7, 1);
Edge e13 = new Edge(n7, n8, 7);

Graph graph = new Graph(new List<Node>()
{
    n0,
    n1,
    n2,
    n3,
    n4,
    n5,
    n6,
    n7,
    n8
}, new List<Edge>()
{
    e00,
    e01,
    e02,
    e03,
    e04,
    e05,
    e06,
    e07,
    e08,
    e09,
    e10,
    e11,
    e12,
    e13
});

List<Node> path = graph.GetShortestPath(graph.Nodes[0], graph.Nodes[4]);

foreach (var n in path)
{
    Console.WriteLine($"{n.Id} --> {n.Predecessor.Id} (Distance: {n.Distance})");
}
