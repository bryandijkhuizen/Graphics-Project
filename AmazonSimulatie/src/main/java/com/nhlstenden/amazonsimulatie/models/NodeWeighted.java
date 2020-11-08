package com.nhlstenden.amazonsimulatie.models;

import java.util.LinkedList;

public class NodeWeighted {
    // De int n en string name zijn willekeurig attributen. Die wij hebben gekozen voor onze nodes
    int n;
    String name;
    private boolean visited;
    LinkedList<EdgeWeighted> edges;

    public NodeWeighted(int n, String name) {
        this.n = n;
        this.name = name;
        visited = false;
        edges = new LinkedList<>();
    }
    public boolean isVisited() { return visited; }
    public void visit() { visited = true; }
    public void unvisit() { visited = false; }
}