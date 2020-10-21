package com.nhlstenden.amazonsimulatie.models;

//De paden binnen de graaf
public class Edge {
    private final String id; //naam van pad
    private final Vertice source; //Begin punt pad
    private final Vertice destination; //Eind punt pad
    private final int weight; //Kosten van het pad

    public Edge(String id, Vertice source, Vertice destination, int weight) {   //Initializeren
        this.id = id;
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }
    public String getId() {return id;}  //Geef de naam van het pad
    public Vertice getDestination() {return destination;}   //Geef het eindpunt van het pad
    public Vertice getSource() {return source;} //Geef het beginpunt van het pad
    public int getWeight() {return weight;} //Geef de kosten van het pad

    @Override
    public String toString() {return source + " " + destination;}
}
