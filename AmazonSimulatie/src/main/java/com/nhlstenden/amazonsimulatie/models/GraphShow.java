package com.nhlstenden.amazonsimulatie.models;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GraphShow {

    Scanner scanner;

    public static String numberToMappingString(int number) {
        String numberString = Integer.toString(number);
        if (number < 10) {
            return "0" + numberString;
        }
        return numberString;
    }

    public static String GetRoute(int start, int end) {
        // 90 91x 92x 93 94x 95x
        // 80 81 82 83 84 85
        // 70 71x 72x 73 74x 75x
        // 60 61 62 63 64 65
        // 50 51x 52x 53 54x 55x
        // 40 41 42 43 44 45
        // 30 31x 32x 33 34x 35x
        // 20 21 22 23 24 25
        // 10 11 12 13 14x 15x
        // 00 01 02 03 04 05

        GraphWeighted graphWeighted = new GraphWeighted(true);

        List<NodeWeighted> nodesWeightedList = new ArrayList<>();
        NodeWeighted[] nodesWeightedArray = new NodeWeighted[54];
        boolean isCreatingMappings = true;
        boolean isCreated = false;
        try {
            File myObj = new File(
                    "C:/xampp/htdocs/Licentiebeheer/Graphics-Project/AmazonSimulatie/src/main/resources/static/tgf/graaf.tgf");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if (data.equals("#")) {
                    isCreatingMappings = false;
                    continue;
                }
                if (isCreatingMappings) {
                    String[] name = data.split(" ");
                    String positionName = name[0]; // Positie van variabele in lijst graaf
                    String variableName = name[1]; // Variabele zelf

                    // Create Objects from mappings
                    nodesWeightedList.add(new NodeWeighted(Integer.parseInt(positionName), variableName));
                    if (("p" + Integer.toString(start)).equals(variableName)) {
                        start = Integer.parseInt(name[0]) - 1;
                    }
                } else {
                    if (!isCreated) {
                        for (int i = 0; i < nodesWeightedList.size(); i++) {
                            nodesWeightedArray[i] = nodesWeightedList.get(i);
                        }
                        isCreated = true;
                    }
                    String[] lineValues = data.split(" ");
                    int test = Integer.parseInt(lineValues[0]) - 1;
                    NodeWeighted source = nodesWeightedArray[Integer.parseInt(lineValues[0]) - 1];
                    NodeWeighted destination = nodesWeightedArray[Integer.parseInt(lineValues[1]) - 1];
                    int weight = Integer.parseInt(lineValues[2]);

                    graphWeighted.addEdge(source, destination, weight); // Vooruit
                    graphWeighted.addEdge(destination, source, weight); // Achteruit
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        NodeWeighted startNode = nodesWeightedArray[Integer.parseInt(numberToMappingString(start))];
        NodeWeighted endNode = nodesWeightedArray[Integer.parseInt(numberToMappingString(end))];
        return graphWeighted.DijkstraShortestPath(startNode, endNode);
    }
}