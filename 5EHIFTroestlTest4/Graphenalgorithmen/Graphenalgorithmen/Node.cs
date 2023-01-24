namespace Graphenalgorithmen
{
    internal class Node
    {
        #region Properties
        /// <summary>
        /// Is the name of the Node
        /// </summary>
        public string Id { get; set; }
        /// <summary>
        /// Is used for storing a path
        /// </summary>
        public Node? Next { get; set; }
        /// <summary>
        /// Cordinate X
        /// </summary>
        public int X { get; set; }
        /// <summary>
        /// Cordinate Y
        /// </summary>
        public int Y { get; set; }
        /// <summary>
        /// Prepared for Dijkstra just in case
        /// </summary>
        public HashSet<Node> Neighbours { get; set; }
        /// <summary>
        /// Prepared for Dijkstra just in case
        /// </summary>
        public Node? Predecessor { get; set; }
        /// <summary>
        /// Prepared for Dijkstra just in case
        /// </summary>
        public int Distance { get; set; }
        #endregion

        #region Initialization
        public Node(string id)
        {
            Id = id;
        }

        public Node(string id, int x, int y) : this(id)
        {
            X = x;
            Y = y;
        }
        #endregion

        #region Overrides
        public override bool Equals(object? obj)
        {
            return obj is Node node &&
                   Id == node.Id;
        }

        public override int GetHashCode()
        {
            return HashCode.Combine(Id);
        }
        #endregion
    }
}
