public class Node {
    Node parent;
    CityName cityName;
    int cost;
    int depth;

    public Node(Node parent, CityName cityName, int cost, int depth) {
        this.parent = parent;
        this.cityName = cityName;
        this.cost = cost;
        this.depth = depth;
    }
}
