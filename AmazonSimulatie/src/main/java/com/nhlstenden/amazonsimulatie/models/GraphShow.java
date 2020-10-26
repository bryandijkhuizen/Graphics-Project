package com.nhlstenden.amazonsimulatie.models;

public class GraphShow {

    public String getRoute(int start, String end){
         //  90  91x 92x 93  94x 95x
        //  80  81  82  83  84  85
        //  70  71x 72x 73  74x 75x
        //  60  61  62  63  64  65
        //  50  51x 52x 53  54x 55x
        //  40  41  42  43  44  45
        //  30  31x 32x 33  34x 35x
        //  20  21  22  23  24  25
        //  10  11  12  13  14x 15x
        //  00  01  02  03  04  05

        GraphWeighted graphWeighted = new GraphWeighted(true);
        NodeWeighted p0 = new NodeWeighted(0, "0");
        NodeWeighted p1 = new NodeWeighted(1, "1");
        NodeWeighted p2 = new NodeWeighted(2, "2");
        NodeWeighted p3 = new NodeWeighted(3, "3");
        NodeWeighted p4 = new NodeWeighted(4, "4");
        NodeWeighted p5 = new NodeWeighted(5, "5");
        NodeWeighted p6 = new NodeWeighted(6, "6");

        // Our addEdge method automatically adds Nodes as well.
        // The addNode method is only there for unconnected Nodes,
        // if we wish to add any
        graphWeighted.addEdge(p0, p1, 8);
        graphWeighted.addEdge(p0, p2, 11);
        graphWeighted.addEdge(p1, p3, 3);
        graphWeighted.addEdge(p1, p4, 8);
        graphWeighted.addEdge(p1, p2, 7);
        graphWeighted.addEdge(p2, p4, 9);
        graphWeighted.addEdge(p3, p4, 5);
        graphWeighted.addEdge(p3, p5, 2);
        graphWeighted.addEdge(p4, p6, 6);
        graphWeighted.addEdge(p5, p4, 1);
        graphWeighted.addEdge(p5, p6, 8);

        NodeWeighted startNodeWeighted = new NodeWeighted(start, "p");
        NodeWeighted endNodeWeighted = new NodeWeighted(Integer. parseInt(end), "p");
        return graphWeighted.dijkstraShortestPath(startNodeWeighted, endNodeWeighted); 
    }
}