package com.nhlstenden.amazonsimulatie.models;

import java.util.HashSet;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;
import java.util.Arrays;

public class GraphWeighted {
    private Set<NodeWeighted> nodes;
    private boolean directed;

    public GraphWeighted(boolean directed) {
        this.directed = directed;
        nodes = new HashSet<>();
    }
    public void addNode(NodeWeighted... n) { nodes.addAll(Arrays.asList(n)); }
    public void addEdge(NodeWeighted source, NodeWeighted destination, double weight) {
        //Doordat er gebruik wordt gemaakt van een Set. Worden alleen nog niet bestaande nodes in onze graph worden toegevoegd
        nodes.add(source);
        nodes.add(destination);
        addEdgeHelper(source, destination, weight); // Voor de dubbele controle helpt addEdgeHelper met het controleren op dubbele edges
        if (!directed && source != destination) { addEdgeHelper(destination, source, weight); }
    }
    private void addEdgeHelper(NodeWeighted a, NodeWeighted b, double weight) {
        
        for (EdgeWeighted edge : a.edges) { // Bekijk alle edges en of deze al zijn toegevoegd 
            if (edge.source == a && edge.destination == b) {               
                edge.weight = weight; // Verander de waarde als deze is gewijzigd
                return;
            }
        }
        a.edges.add(new EdgeWeighted(a, b, weight)); // Als deze nog niet is toegevoegd, dan wordt deze toegevoegd
    }    
    public void resetNodesVisited() { //Benodigd voor als het algoritme meerdere malen wordt aangeroepen
        for (NodeWeighted node : nodes) { node.unvisit(); }
    }
    public boolean hasEdge(NodeWeighted source, NodeWeighted destination) {
        LinkedList<EdgeWeighted> edges = source.edges;
        for (EdgeWeighted edge : edges) {
            if (edge.destination == destination) { return true; }
        }
        return false;
    }

    //Houdt bij welk pad ons het kortste pad geeft voor elk knooppunt door bij te houden hoe we bij een bepaald knooppunt zijn aangekomen.
    public String DijkstraShortestPath(NodeWeighted start, NodeWeighted end) { 
        HashMap<NodeWeighted, NodeWeighted> changedAt = new HashMap<>();
        changedAt.put(start, null);
        HashMap<NodeWeighted, Double> shortestPathMap = new HashMap<>(); //Bewaart het kortste pad tot nu toe voor elke node

        for (NodeWeighted node : nodes) {
            if (node == start)  shortestPathMap.put(start, 0.0); //Start node krijgt als gewicht mee 0
            else shortestPathMap.put(node, Double.POSITIVE_INFINITY); //Elke andere node krijgt maximale waarde mee
        }

        for (EdgeWeighted edge : start.edges) { //Ga hierna langs elke node vanaf de start node
            shortestPathMap.put(edge.destination, edge.weight);
            changedAt.put(edge.destination, start);
        }
        start.visit();
        
        while (true) { // Blijf doorgaan tot dat alle onbezochte nodes die nog berekend moeten worden vanaf de huidige al berekende nodes
            NodeWeighted currentNode = closestReachableUnvisited(shortestPathMap);            
            if (currentNode == null) { //Als er geen pad te vinden is tussen start en eind punt
                System.out.println("There isn't a path between " + start.name + " and " + end.name); //Geeft dan door wat de foutmelding is
                return null;
            }
            if (currentNode == end) { //Als de dichtsbijzijnde nog niet bezochte node de bestemming is
                System.out.println("The path with the smallest weight between " + start.name + " and " + end.name + " is:"); //Geef dan het pad
                NodeWeighted child = end;
                String path = end.name;
                while (true) {
                    NodeWeighted parent = changedAt.get(child);
                    if (parent == null) { break; }
                    // Omdat changedAt bijhoudt child -> parent relatie. Om het correct af te drukken moet de parent voor de child en nakomelingen
                    path = parent.name + " " + path;
                    child = parent;
                }
                System.out.println(path);
                System.out.println("The path costs: " + shortestPathMap.get(end));
                return path;
            }
            currentNode.visit();

            for (EdgeWeighted edge : currentNode.edges) { //Ga door alle buur onbezochte nodes van de huidige node en controleer welke dan het kortste is
                if (edge.destination.isVisited()) { continue; }
                if (shortestPathMap.get(currentNode) + edge.weight < shortestPathMap.get(edge.destination)) {
                    shortestPathMap.put(edge.destination, shortestPathMap.get(currentNode) + edge.weight);
                    changedAt.put(edge.destination, currentNode);
                }
            }
        }
    }
    private NodeWeighted closestReachableUnvisited(HashMap<NodeWeighted, Double> shortestPathMap) {
        double shortestDistance = Double.POSITIVE_INFINITY;
        NodeWeighted closestReachableNode = null;
        for (NodeWeighted node : nodes) {
            if (node.isVisited()) { continue; } 
            double currentDistance = shortestPathMap.get(node);
            if (currentDistance == Double.POSITIVE_INFINITY) { continue; }
            if (currentDistance < shortestDistance) {
                shortestDistance = currentDistance;
                closestReachableNode = node;
            }
        }
        return closestReachableNode;
    }
}