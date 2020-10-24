package com.nhlstenden.amazonsimulatie.models;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.Arrays;

public class GraphWeighted {
    private Set<NodeWeighted> nodes;
    private boolean directed;

    GraphWeighted(boolean directed) {
        this.directed = directed;
        nodes = new HashSet<>();
    }

    // Doesn't need to be called for any node that has an edge to another node
// since addEdge makes sure that both nodes are in the nodes Set
public void addNode(NodeWeighted... n) {
    // We're using a var arg method so we don't have to call
    // addNode repeatedly
    nodes.addAll(Arrays.asList(n));
}

public void addEdge(NodeWeighted source, NodeWeighted destination, double weight) {
    // Since we're using a Set, it will only add the nodes
    // if they don't already exist in our graph
    nodes.add(source);
    nodes.add(destination);

    // We're using addEdgeHelper to make sure we don't have duplicate edges
    addEdgeHelper(source, destination, weight);

    if (!directed && source != destination) {
        addEdgeHelper(destination, source, weight);
    }
}

private void addEdgeHelper(NodeWeighted a, NodeWeighted b, double weight) {
    // Go through all the edges and see whether that edge has
    // already been added
    for (EdgeWeighted edge : a.edges) {
        if (edge.source == a && edge.destination == b) {
            // Update the value in case it's a different one now
            edge.weight = weight;
            return;
        }
    }
    // If it hasn't been added already (we haven't returned
    // from the for loop), add the edge
    a.edges.add(new EdgeWeighted(a, b, weight));
}

public void printEdges() {
    for (NodeWeighted node : nodes) {
        LinkedList<EdgeWeighted> edges = node.edges;

        if (edges.isEmpty()) {
            System.out.println("Node " + node.name + " has no edges.");
            continue;
        }
        System.out.print("Node " + node.name + " has edges to: ");

        for (EdgeWeighted edge : edges) {
            System.out.print(edge.destination.name + "(" + edge.weight + ") ");
        }
        System.out.println();
    }
}

public boolean hasEdge(NodeWeighted source, NodeWeighted destination) {
    LinkedList<EdgeWeighted> edges = source.edges;
    for (EdgeWeighted edge : edges) {
        // Again relying on the fact that all classes share the
        // exact same NodeWeighted object
        if (edge.destination == destination) {
            return true;
        }
    }
    return false;
}

// Necessary call if we want to run the algorithm multiple times
public void resetNodesVisited() {
    for (NodeWeighted node : nodes) {
        node.unvisit();
    }
}
}