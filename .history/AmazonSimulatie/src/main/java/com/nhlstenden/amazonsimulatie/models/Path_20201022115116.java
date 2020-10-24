package com.nhlstenden.amazonsimulatie.models;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Path {
    private List<Vertice> nodes;
    private List<Edge> edges;
    
    public void path() {
        nodes = new ArrayList<Vertice>();
        edges = new ArrayList<Edge>();
        for (int i = 0; i < 59; i++) {
            Vertice location = new Vertice("Node_" + i, "Node_" + i);
            nodes.add(location);
        }
        //  0   1x  2x  3   4x  5x
        //  6   7   8   9   10  11
        //  12  13x 14x 15  16x 17x
        //  18  19  20  21  22  23
        //  24  25x 26x 27  28x 29x
        //  30  31  32  33  34  35
        //  36  37x 38x 39  40x 41x
        //  42  43  44  45  46  47
        //  48  49  50  51  52x 53x
        //  54  55  56  57  58  59

        //Stellage op:  1, 2, 4, 5, 13, 14, 16, 17, 26, 27, 29, 30, 38, 39, 41, 42, 53, 54

        //Alle paden
        addPath("Pad_AA", 0, 6, 1);     addPath("Pad_AB", 6, 7, 1);     addPath("Pad_AC", 6, 12, 1);
        addPath("Pad_BA", 3, 9, 1);     addPath("Pad_BB", 8, 9, 1);     addPath("Pad_BC", 9, 10, 1);    addPath("Pad_BD", 9, 15, 1);    
        addPath("Pad_CA", 7, 8, 1);     addPath("Pad_CB", 10, 11, 1);
        addPath("Pad_DA", 12, 18, 1);   addPath("Pad_DB", 18, 19, 1);   addPath("Pad_DC", 18, 24, 1);
        addPath("Pad_EA", 15, 21, 1);   addPath("Pad_EB", 20, 21, 1);   addPath("Pad_EC", 21, 22, 1);   addPath("Pad_ED", 21, 27, 1);
        addPath("Pad_FA", 19, 20, 1);   addPath("Pad_FB", 22, 23, 1);   
        addPath("Pad_GA", 24, 30, 1);   addPath("Pad_GB", 30, 31, 1);   addPath("Pad_GC", 30, 36, 1);
        addPath("Pad_HA", 27, 33, 1);   addPath("Pad_HB", 32, 33, 1);   addPath("Pad_HC", 33, 34, 1);   addPath("Pad_HD", 33, 39, 1);
        addPath("Pad_IA", 31, 32, 1);   addPath("Pad_IB", 34, 35, 1);
        addPath("Pad_JA", 36, 42, 1);   addPath("Pad_JB", 42, 43, 1);   addPath("Pad_JC", 42, 48, 1);
        addPath("Pad_KA", 39, 45, 1);   addPath("Pad_KB", 44, 45, 1);   addPath("Pad_KC", 45, 46, 1);   addPath("Pad_KD", 45, 51, 1);
        addPath("Pad_LA", 43, 44, 1);   addPath("Pad_LB", 46, 47, 1);   
        addPath("Pad_MA", 48, 49, 1);   addPath("Pad_MB", 48, 54, 1);   addPath("Pad_MC", 49, 50, 1);   addPath("Pad_MD", 49, 55, 1);
        addPath("Pad_NA", 50, 51, 1);   addPath("Pad_NB", 50, 56, 1);   addPath("Pad_NC", 51, 57, 1);   addPath("Pad_ND", 57, 58, 1);
        addPath("Pad_O", 58, 59, 1);
        Graph graph = new Graph(nodes, edges);
        DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph);
        dijkstra.execute(nodes.get(0));
        LinkedList<Vertice> path = dijkstra.getPath(nodes.get(10));

       //assertNotNull(path);
        //assertTrue(path.size() > 0);

        for (Vertice vertex : path) {
            System.out.println(vertex);
        }
    }
    private void addPath(String laneId, int sourceLocNo, int destLocNo, int duration) {
        Edge lane = new Edge(laneId,nodes.get(sourceLocNo), nodes.get(destLocNo), duration);
        edges.add(lane);
    }    
}