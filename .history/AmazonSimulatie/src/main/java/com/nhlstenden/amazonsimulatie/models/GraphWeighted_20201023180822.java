public class GraphWeighted {
    private Set<NodeWeighted> nodes;
    private boolean directed;

    GraphWeighted(boolean directed) {
        this.directed = directed;
        nodes = new HashSet<>();
    }

    // ...
}