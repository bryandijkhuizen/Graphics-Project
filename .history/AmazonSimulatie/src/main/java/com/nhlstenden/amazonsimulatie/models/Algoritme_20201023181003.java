public void DijkstraShortestPath(NodeWeighted start, NodeWeighted end) {
    // We keep track of which path gives us the shortest path for each node
    // by keeping track how we arrived at a particular node, we effectively
    // keep a "pointer" to the parent node of each node, and we follow that
    // path to the start
    HashMap<NodeWeighted, NodeWeighted> changedAt = new HashMap<>();
    changedAt.put(start, null);

    // Keeps track of the shortest path we've found so far for every node
    HashMap<NodeWeighted, Double> shortestPathMap = new HashMap<>();

    // Setting every node's shortest path weight to positive infinity to start
    // except the starting node, whose shortest path weight is 0
    for (NodeWeighted node : nodes) {
        if (node == start)
            shortestPathMap.put(start, 0.0);
        else shortestPathMap.put(node, Double.POSITIVE_INFINITY);
    }

    // Now we go through all the nodes we can go to from the starting node
    // (this keeps the loop a bit simpler)
    for (EdgeWeighted edge : start.edges) {
        shortestPathMap.put(edge.destination, edge.weight);
        changedAt.put(edge.destination, start);
    }

    start.visit();

    // This loop runs as long as there is an unvisited node that we can
    // reach from any of the nodes we could till then
    while (true) {
        NodeWeighted currentNode = closestReachableUnvisited(shortestPathMap);
        // If we haven't reached the end node yet, and there isn't another
        // reachable node the path between start and end doesn't exist
        // (they aren't connected)
        if (currentNode == null) {
            System.out.println("There isn't a path between " + start.name + " and " + end.name);
            return;
        }

        // If the closest non-visited node is our destination, we want to print the path
        if (currentNode == end) {
            System.out.println("The path with the smallest weight between "
                                   + start.name + " and " + end.name + " is:");

            NodeWeighted child = end;

            // It makes no sense to use StringBuilder, since
            // repeatedly adding to the beginning of the string
            // defeats the purpose of using StringBuilder
            String path = end.name;
            while (true) {
                NodeWeighted parent = changedAt.get(child);
                if (parent == null) {
                    break;
                }

                // Since our changedAt map keeps track of child -> parent relations
                // in order to print the path we need to add the parent before the child and
                // it's descendants
                path = parent.name + " " + path;
                child = parent;
            }
            System.out.println(path);
            System.out.println("The path costs: " + shortestPathMap.get(end));
            return;
        }
        currentNode.visit();

        // Now we go through all the unvisited nodes our current node has an edge to
        // and check whether its shortest path value is better when going through our
        // current node than whatever we had before
        for (EdgeWeighted edge : currentNode.edges) {
            if (edge.destination.isVisited())
                continue;

            if (shortestPathMap.get(currentNode)
               + edge.weight
               < shortestPathMap.get(edge.destination)) {
                shortestPathMap.put(edge.destination,
                                   shortestPathMap.get(currentNode) + edge.weight);
                changedAt.put(edge.destination, currentNode);
            }
        }
    }
}