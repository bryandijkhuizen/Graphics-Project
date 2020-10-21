package com.nhlstenden.amazonsimulatie.models;
import java.util.List;

//De graaf met alle punten en paden
public class Graph {
    private final List<Vertice> vertices; //Lijst van alle punten in de graaf
    private final List<Edge> edges; //Lijst van alle paden in de graaf
    
    public Graph(List<Vertice>vertices, List<Edge> edges) { //Maak een graph van paden en punten
        this.vertices = vertices;
        this.edges = edges;
    }
    public List<Vertice> getVertices() {return vertices;} //Geef de punten terug
    public List<Edge> getEdges() {return edges;}    //Geef de paden terug
}
