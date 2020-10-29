package com.nhlstenden.amazonsimulatie.models;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GraphShow {
    public static String numberToMappingString(int number) {    //Methode die kijkt of het nummer 1 t/m 9 is en zet hier een "0" voor
        String numberString = Integer.toString(number); //Maak van het nummer eeen string
        //Als het nummer kleiner is als 10 zet er dan "0" voor
        if (number < 10) { return "0" + numberString;}
        return numberString; //geef de nieuwe string waarde van het nummer terug
    }    
    public static String GetRoute(int start, int end) { //Methode zoek de start en eind punten van de route met de benodigde paden
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

        GraphWeighted graphWeighted = new GraphWeighted(true); //maak een directed route
        List<NodeWeighted> nodesWeightedList = new ArrayList<>();   //Arraylist voor alle Nodes
        NodeWeighted[] nodesWeightedArray = new NodeWeighted[54];   //Nodeweighted array voor alle node waarde
        boolean isCreatingMappings = true;  //Check voor het aanmaken van de paden nadat alle Nodes zijn ingeladen
        boolean isCreated = false;  //Check voor het omzetten van alle list items naar Array
        try {
            //File pad naar TGF file waar alle nodes en paden in staan
            File myObj = new File("C:/xampp/htdocs/Licentiebeheer/Graphics-Project/AmazonSimulatie/src/main/resources/static/tgf/graaf.tgf");
            Scanner myReader = new Scanner(myObj);  //Scanner voor het lezen van de TGF file
            while (myReader.hasNextLine()) { //Terwijl er nog een volgende regel is 
                String data = myReader.nextLine();  //Maak een string van de volgende regel
                if (data.equals("#")) { //Als de string gelijk staat aan de divider
                    isCreatingMappings = false; //Dan zijn alle nodes ingeladen
                    continue;   //Ga dan verder
                }
                if (isCreatingMappings) { //Als de nodes nog worden aangemaakt
                    String[] name = data.split(" ");    //Splits dan de gegeven data op het punt waar een spatie staat en maak hiervan een array
                    String positionName = numberToMappingString(Integer.parseInt(name[0])); //Positie van variabele in lijst graaf
                    String variableName = name[1]; // Variabele naam onder boven genoemde positie

                    // Create Objects from mappings
                    //Maak een nieuw list item met als int positienaam en string de variabele naam
                    nodesWeightedList.add(new NodeWeighted(Integer.parseInt(positionName), variableName));
                    //Als het megegeven start nummer met een p ervoor gelijk staat aan de variabele naam dan is de positie van die variabele het nieuwe getal
                    if (("p" + Integer.toString(start)).equals(variableName)) {start = Integer.parseInt(name[0]);}
                } else {
                    if (!isCreated) {   //Als de list nog niet is omgezet naar een array doe dit dan
                        for (int i = 0; i < nodesWeightedList.size(); i++) {
                            nodesWeightedArray[i] = nodesWeightedList.get(i);
                        }
                        isCreated = true; //Geef terug dat de lijst is omgezet
                    }
                    String[] lineValues = data.split(" "); //Splits dan de gegeven data op het punt waar een spatie staat en maak hiervan een array
                    //Source node is Source value - 1 zodat deze aansluit met de daadwerkelijke index zoals deze staat in de TGF file
                    NodeWeighted source = nodesWeightedArray[Integer.parseInt(lineValues[0]) - 1]; 
                    //Destination node is destination value  - 1 zodat deze aansluit met de daadwerkelijke index zoals deze staat in de TGF file
                    NodeWeighted destination = nodesWeightedArray[Integer.parseInt(lineValues[1]) - 1];
                    int weight = Integer.parseInt(lineValues[2]);   //Gewicht zoals deze wordt meegegeven in de TGF file

                    graphWeighted.addEdge(source, destination, weight); // Maakt het pad vooruit aan
                    graphWeighted.addEdge(destination, source, weight); // Maakt het pad achteruit aan
                }
            }
            myReader.close();   //Stop de scanner
        } catch (FileNotFoundException e) { //Vang mogelijke problemen
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        NodeWeighted startNode = nodesWeightedArray[start - 1]; //Maak een startNode Nodeweighted variabele van de gevonden start data
        NodeWeighted endNode = nodesWeightedArray[end]; //Maak een endNode NodeWeighted variabele van de gevonden end data
        return graphWeighted.DijkstraShortestPath(startNode, endNode); //Geef de waarden terug
    }
}