package com.nhlstenden.amazonsimulatie.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class DijkstraAlgorithm {
    private final List<Vertice> nodes; //Lijst met punten die de robot kan gebruiken in de warehouse
    private final List<Edge> edges; //Lijst met paden die de robot kan gebruiken in de warehouse
    private Set<Vertice> settledNodes; //Lijst met punten waar de robot is geweest
    private Set<Vertice> unSettledNodes; //Lijst met punten waar de robot niet is geweest
    private Map<Vertice, Vertice> predecessors; //Lijst met coordinaten voorouders
    private Map<Vertice, Integer> distance; //Lijst met afstanden afgelegd.

    //Aanmaak van de array
    public DijkstraAlgorithm(Graph graph) {
        //Kopie van de array zodat er aan gewerkt kan worden
        this.nodes = new ArrayList<Vertice>(graph.getVertices());
        this.edges = new ArrayList<Edge>(graph.getEdges());
    }
    
    public void execute(Vertice source) {
        settledNodes = new HashSet<Vertice>();  //Aanmaak set voor bezochte nodes
        unSettledNodes = new HashSet<Vertice>();    //Aanmaak set voor onbezochte nodes
        predecessors = new HashMap<Vertice, Vertice>(); //Aanmaak set voor voorouder nodes
        distance = new HashMap<Vertice, Integer>(); //Aanmaak set voor afstanden
        distance.put(source, 0); //Afstand bron node staat op 0
        unSettledNodes.add(source); //Bron node toevoegen aan onbezochte nodes set
        while (unSettledNodes.size() > 0) { //Terwijl er nog onbezochte nodes zijn ga door
            Vertice node = getMinimum(unSettledNodes);  //Maak een punt van het minimale pad met de nog niet bezochte node
            settledNodes.add(node); //Daarna wordt deze toegevoegd aan de bezochte nodes set
            unSettledNodes.remove(node); //Verwijderd uit de niet bezochte punten set
            findMinimalDistances(node); //Bereken dan of er vanaf dit punt het kortste pad is
        }
    }
    private Vertice getMinimum(Set<Vertice> vertices) { //Geeft de minimum node met de set van punten
        Vertice minimum = null; //Minimum punt is niks
        for (Vertice vertice : vertices) {  //Voor elk punt in de set van punten
            if (minimum == null) {  //Als het minimum punt niks is
                minimum = vertice;  //Maak het minimum punt een punt in de set
            } else {
                //Als de minimale padgrootte groter is dan de huidige pad grootte maak dan van het huidige pad het minmale pad
                if (getShortestDistance(vertice) < getShortestDistance(minimum)) {minimum = vertice;}
            }
        }
        return minimum; //Geef het pad van minimum punten terug
    }
    private int getShortestDistance(Vertice destination) {  //Geeft het kortste pad naar het eind punt
        Integer d = distance.get(destination);  //Maak een pad naar het eind punt
        if (d == null) { //Als er geen pad is
            return Integer.MAX_VALUE; //Geef een te groot pad terug
        } else {
            return d; //Geef de pad grootte terug
        }
    }
    private void findMinimalDistances(Vertice node) {   //Zoekt de minimale afstand vanaf een punt
        List<Vertice> adjacentNodes = getNeighbors(node);   //Lijst van buur punten 
        for (Vertice target : adjacentNodes) {  //Voor elk punt in de buren lijst
            //Als het nieuwe buur punt groter is dan het huidig gekozen buur punt + de kosten naar die buur
            if (getShortestDistance(target) > getShortestDistance(node) + getDistance(node, target)) {                
                distance.put(target, getShortestDistance(node) + getDistance(node, target));    //afstand van gekozen buur met kortste pad met huidige punt + de kosten naar die buur
                predecessors.put(target, node); //Voorouder met gekozen buur punt en huidige node
                unSettledNodes.add(target); //Voeg dan de gekozen buur aan de lijst met onbezochten punten toe
            }
        }
    }
    private List<Vertice> getNeighbors(Vertice node) {  //Geeft een lijst van buur punten terug
        List<Vertice> neighbors = new ArrayList<Vertice>(); //Maak een nieuwe lijst van buur punten
        for (Edge edge : edges) {   //Voor elk pad in lijst met paden            
            if (edge.getSource().equals(node) && !isSettled(edge.getDestination())) { //Als de bron van dit pad gelijk staat aan het bron punt EN deze nog niet bezocht is
                neighbors.add(edge.getDestination());   //Voeg deze toe aan het pad naar het eindpunt
            }
        }
        return neighbors;   //Geef de buur terug
    }
    private int getDistance(Vertice node, Vertice target) { //Geef de afstand vanaf het huidige punt tot het eindpunt
        for (Edge edge : edges) {   //Voor elk pad in lijst met paden
            if (edge.getSource().equals(node) && edge.getDestination().equals(target)) { //Als de bron van dit pad gelijk staat aan dit punt En dit pad naar de bron gelijk staat aan de buur
                return edge.getWeight();    //Geef dan het pad terug met de kosten
            }
        }
        throw new RuntimeException("Should not happen"); //Voor het geval dat als het programma hier komt een fout melding
    }
    private boolean isSettled(Vertice vertex) {return settledNodes.contains(vertex);}

    public LinkedList<Vertice> getPath(Vertice target) {    //Geeft het pad van het bron punt tot de gekozen buur
        LinkedList<Vertice> path = new LinkedList<Vertice>();   //Lijst van punten in het pad
        Vertice step = target;  //Maak een stap van elke buur
        if (predecessors.get(step) == null) {return null;}  //Controle of er een pad bestaat
        path.add(step);//Voeg de stap toe aan het pad
        while (predecessors.get(step) != null) {    //Terwijl het voorouder punt bestaat
            step = predecessors.get(step);  //de stap is het voorouder punt
            path.add(step); //Voeg dan deze stap toe aan het pad
        }        
        Collections.reverse(path);  //Order het pad zodat het correct staat
        return path;    //Geef het pad terug
    }
}
