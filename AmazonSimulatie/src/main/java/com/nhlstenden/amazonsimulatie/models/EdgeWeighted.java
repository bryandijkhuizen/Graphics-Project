package com.nhlstenden.amazonsimulatie.models;

public class EdgeWeighted implements Comparable<EdgeWeighted> {

    NodeWeighted source, destination;
    double weight;

    public EdgeWeighted(NodeWeighted s, NodeWeighted d, double w) {
        source = s;
        destination = d;
        weight = w;
    }
    public String toString() {
        return String.format("(%s -> %s, %f)", source.name, destination.name, weight);
    }   
    //Deze methode is nodig, omdat we priorityqueues dan kunnen toeppassen in de plaats van linkedlists voor onze edges
    public int compareTo(EdgeWeighted otherEdge) {
        if (this.weight > otherEdge.weight) { return 1; }
        else return -1;
    }
}