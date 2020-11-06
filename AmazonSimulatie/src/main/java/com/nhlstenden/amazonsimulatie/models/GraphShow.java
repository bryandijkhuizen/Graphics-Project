package com.nhlstenden.amazonsimulatie.models;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GraphShow {

    // Scanner scanner;

    // public static String numberToMappingString(int number) {
    //     String numberString = Integer.toString(number);
    //     if (number < 10) {
    //         return "0" + numberString;
    //     }
    //     return numberString;
    // }

    // public static String GetRoute(int start, int end) {
    //     // 90 91x 92x 93 94x 95x
    //     // 80 81 82 83 84 85
    //     // 70 71x 72x 73 74x 75x
    //     // 60 61 62 63 64 65
    //     // 50 51x 52x 53 54x 55x
    //     // 40 41 42 43 44 45
    //     // 30 31x 32x 33 34x 35x
    //     // 20 21 22 23 24 25
    //     // 10 11 12 13 14x 15x
    //     // 00 01 02 03 04 05

    //     GraphWeighted graphWeighted = new GraphWeighted(true);

    //     List<NodeWeighted> nodesWeightedList = new ArrayList<>();
    //     NodeWeighted[] nodesWeightedArray = new NodeWeighted[54];
    //     boolean isCreatingMappings = true;
    //     boolean isCreated = false;
    //     try {
    //         File myObj = new File(
    //                 "C:/Gebruikers/Hieke Wiersma/Deze pc/Bureaublad/HBO ICT jaar 1/Periode 4/project/project/Graphics-Project/AmazonSimulatie/src/main/resources/static/tgf/graaf.tgf");
    //         Scanner myReader = new Scanner(myObj);
    //         while (myReader.hasNextLine()) {
    //             String data = myReader.nextLine();
    //             if (data.equals("#")) {
    //                 isCreatingMappings = false;
    //                 continue;
    //             }
    //             if (isCreatingMappings) {
    //                 String[] name = data.split(" ");
    //                 String positionName = name[0]; // Positie van variabele in lijst graaf
    //                 String variableName = name[1]; // Variabele zelf

    //                 // Create Objects from mappings
    //                 nodesWeightedList.add(new NodeWeighted(Integer.parseInt(positionName), variableName));
    //                 if (("p" + Integer.toString(start)).equals(variableName)) {
    //                     start = Integer.parseInt(name[0]) - 1;
    //                 }
    //             } else {
    //                 if (!isCreated) {
    //                     for (int i = 0; i < nodesWeightedList.size(); i++) {
    //                         nodesWeightedArray[i] = nodesWeightedList.get(i);
    //                     }
    //                     isCreated = true;
    //                 }
    //                 String[] lineValues = data.split(" ");
    //                 int test = Integer.parseInt(lineValues[0]) - 1;
    //                 NodeWeighted source = nodesWeightedArray[Integer.parseInt(lineValues[0]) - 1];
    //                 NodeWeighted destination = nodesWeightedArray[Integer.parseInt(lineValues[1]) - 1];
    //                 int weight = Integer.parseInt(lineValues[2]);

    //                 graphWeighted.addEdge(source, destination, weight); // Vooruit
    //                 graphWeighted.addEdge(destination, source, weight); // Achteruit
    //             }
    //         }
    //         myReader.close();
    //     } catch (FileNotFoundException e) {
    //         System.out.println("An error occurred.");
    //         e.printStackTrace();
    //     }

    //     NodeWeighted startNode = nodesWeightedArray[Integer.parseInt(numberToMappingString(start))];
    //     NodeWeighted endNode = nodesWeightedArray[Integer.parseInt(numberToMappingString(end))];
    //     return graphWeighted.DijkstraShortestPath(startNode, endNode);
    // }
    public static String GetRoute(int start, int end){
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

        // NodeWeighted[] punten = new NodeWeighted[96];
        // NodeWeighted[] p = new NodeWeighted[96];
        // for (int i = 0; i < punten.length; i++) {
        //     String lower = Integer.toString(i);
        //     p[i]  = new NodeWeighted(i, lower.length() == 1 ? "0" + lower : lower);
        // }
        NodeWeighted p90 = new NodeWeighted(90, "90");  NodeWeighted p93 = new NodeWeighted(93, "93");
        NodeWeighted p80 = new NodeWeighted(80, "80");  NodeWeighted p83 = new NodeWeighted(83, "83");
        NodeWeighted p70 = new NodeWeighted(70, "70");  NodeWeighted p73 = new NodeWeighted(73, "73");
        NodeWeighted p60 = new NodeWeighted(60, "60");  NodeWeighted p63 = new NodeWeighted(63, "63");
        NodeWeighted p50 = new NodeWeighted(50, "50");  NodeWeighted p53 = new NodeWeighted(53, "53");
        NodeWeighted p40 = new NodeWeighted(40, "40");  NodeWeighted p43 = new NodeWeighted(43, "43");
        NodeWeighted p30 = new NodeWeighted(30, "30");  NodeWeighted p33 = new NodeWeighted(33, "33");
        NodeWeighted p20 = new NodeWeighted(20, "20");  NodeWeighted p23 = new NodeWeighted(23, "23");
        NodeWeighted p10 = new NodeWeighted(10, "10");  NodeWeighted p13 = new NodeWeighted(13, "13");
        NodeWeighted p00 = new NodeWeighted(00, "00");  NodeWeighted p03 = new NodeWeighted(03, "03");

        NodeWeighted p91 = new NodeWeighted(91, "91");  NodeWeighted p94 = new NodeWeighted(94, "94");
        NodeWeighted p81 = new NodeWeighted(81, "81");  NodeWeighted p84 = new NodeWeighted(84, "84");
        NodeWeighted p71 = new NodeWeighted(71, "71");  NodeWeighted p74 = new NodeWeighted(74, "74");
        NodeWeighted p61 = new NodeWeighted(61, "61");  NodeWeighted p64 = new NodeWeighted(64, "64");
        NodeWeighted p51 = new NodeWeighted(51, "51");  NodeWeighted p54 = new NodeWeighted(54, "54");
        NodeWeighted p41 = new NodeWeighted(41, "41");  NodeWeighted p44 = new NodeWeighted(44, "44");
        NodeWeighted p31 = new NodeWeighted(31, "31");  NodeWeighted p34 = new NodeWeighted(34, "34");
        NodeWeighted p21 = new NodeWeighted(21, "21");  NodeWeighted p24 = new NodeWeighted(24, "24");
        NodeWeighted p11 = new NodeWeighted(11, "11");  NodeWeighted p14 = new NodeWeighted(14, "14");
        NodeWeighted p01 = new NodeWeighted(01, "01");  NodeWeighted p04 = new NodeWeighted(04, "04");

        NodeWeighted p92 = new NodeWeighted(92, "92");  NodeWeighted p95 = new NodeWeighted(95, "95");
        NodeWeighted p82 = new NodeWeighted(82, "82");  NodeWeighted p85 = new NodeWeighted(85, "85");
        NodeWeighted p72 = new NodeWeighted(72, "72");  NodeWeighted p75 = new NodeWeighted(75, "75");
        NodeWeighted p62 = new NodeWeighted(62, "62");  NodeWeighted p65 = new NodeWeighted(65, "65");
        NodeWeighted p52 = new NodeWeighted(52, "52");  NodeWeighted p55 = new NodeWeighted(55, "55");
        NodeWeighted p42 = new NodeWeighted(42, "42");  NodeWeighted p45 = new NodeWeighted(45, "45");
        NodeWeighted p32 = new NodeWeighted(32, "32");  NodeWeighted p35 = new NodeWeighted(35, "35");
        NodeWeighted p22 = new NodeWeighted(22, "22");  NodeWeighted p25 = new NodeWeighted(25, "25");
        NodeWeighted p12 = new NodeWeighted(12, "12");  NodeWeighted p15 = new NodeWeighted(15, "15");  
        NodeWeighted p02 = new NodeWeighted(02, "02");  NodeWeighted p05 = new NodeWeighted(05, "05");

        NodeWeighted x6 = new NodeWeighted(0, "0");  NodeWeighted x26 = new NodeWeighted(0, "0"); NodeWeighted x46 = new NodeWeighted(0, "0"); NodeWeighted x66 = new NodeWeighted(0, "0"); NodeWeighted x86 = new NodeWeighted(0, "0");
        NodeWeighted x7 = new NodeWeighted(0, "0");  NodeWeighted x27 = new NodeWeighted(0, "0"); NodeWeighted x47 = new NodeWeighted(0, "0"); NodeWeighted x67 = new NodeWeighted(0, "0"); NodeWeighted x87 = new NodeWeighted(0, "0");
        NodeWeighted x8 = new NodeWeighted(0, "0");  NodeWeighted x28 = new NodeWeighted(0, "0"); NodeWeighted x48 = new NodeWeighted(0, "0"); NodeWeighted x68 = new NodeWeighted(0, "0"); NodeWeighted x88 = new NodeWeighted(0, "0");
        NodeWeighted x9 = new NodeWeighted(0, "0");  NodeWeighted x29 = new NodeWeighted(0, "0"); NodeWeighted x49 = new NodeWeighted(0, "0"); NodeWeighted x69 = new NodeWeighted(0, "0"); NodeWeighted x89 = new NodeWeighted(0, "0");
        NodeWeighted x16 = new NodeWeighted(0, "0"); NodeWeighted x36 = new NodeWeighted(0, "0"); NodeWeighted x56 = new NodeWeighted(0, "0"); NodeWeighted x76 = new NodeWeighted(0, "0");
        NodeWeighted x17 = new NodeWeighted(0, "0"); NodeWeighted x37 = new NodeWeighted(0, "0"); NodeWeighted x57 = new NodeWeighted(0, "0"); NodeWeighted x77 = new NodeWeighted(0, "0");
        NodeWeighted x18 = new NodeWeighted(0, "0"); NodeWeighted x38 = new NodeWeighted(0, "0"); NodeWeighted x58 = new NodeWeighted(0, "0"); NodeWeighted x78 = new NodeWeighted(0, "0");
        NodeWeighted x19 = new NodeWeighted(0, "0"); NodeWeighted x39 = new NodeWeighted(0, "0"); NodeWeighted x59 = new NodeWeighted(0, "0"); NodeWeighted x79 = new NodeWeighted(0, "0");

        NodeWeighted[] punten = new NodeWeighted[]{ p00, p01, p02, p03, p04, p05, x6, x7, x8, x9, p10, p11, p12, p13, p14, p15, x16, x17, x18, x19, p20, p21, p22, p23, p24, p25, x26, x27, x28, x29, p30, p31, p32, p33, p34, p35, x36, x37, x38, x39, p40, p41, p42, p43, p44, p45, x46, x47, x48, x49, p50, p51, p52, p53, p54, p55, x56, x57, x58, x59, p60, p61, p62, p63, p64, p65, x66, x67, x68, x69, p70, p71, p72, p73, p74, p75, x76, x77, x78, x79, p80, p81, p82, p83, p84, p85, x86, x87, x88, x89, p90, p91, p92, p93, p94, p95}; 

        graphWeighted.addEdge(p00, p20, 2); graphWeighted.addEdge(p01, p00, 1); graphWeighted.addEdge(p02, p01, 1); graphWeighted.addEdge(p03, p02, 1); graphWeighted.addEdge(p04, p03, 1); graphWeighted.addEdge(p05, p04, 1);
        graphWeighted.addEdge(p20, p00, 2); graphWeighted.addEdge(p01, p02, 1); graphWeighted.addEdge(p02, p12, 1); graphWeighted.addEdge(p03, p13, 1); graphWeighted.addEdge(p04, p05, 1); graphWeighted.addEdge(p25, p24, 1);
        graphWeighted.addEdge(p20, p21, 1); graphWeighted.addEdge(p01, p11, 1); graphWeighted.addEdge(p02, p03, 1); graphWeighted.addEdge(p03, p04, 1); graphWeighted.addEdge(p24, p23, 1); graphWeighted.addEdge(p45, p44, 1);
        graphWeighted.addEdge(p20, p40, 2); graphWeighted.addEdge(p11, p01, 1); graphWeighted.addEdge(p12, p02, 1); graphWeighted.addEdge(p13, p03, 1); graphWeighted.addEdge(p24, p25, 1); graphWeighted.addEdge(p65, p64, 1);
        graphWeighted.addEdge(p40, p20, 2); graphWeighted.addEdge(p11, p10, 1); graphWeighted.addEdge(p12, p11, 1); graphWeighted.addEdge(p13, p12, 1); graphWeighted.addEdge(p44, p43, 1); graphWeighted.addEdge(p85, p84, 1);
        graphWeighted.addEdge(p40, p41, 1); graphWeighted.addEdge(p11, p21, 1); graphWeighted.addEdge(p12, p22, 1); graphWeighted.addEdge(p13, p23, 1); graphWeighted.addEdge(p44, p45, 1);
        graphWeighted.addEdge(p40, p60, 2); graphWeighted.addEdge(p11, p12, 1); graphWeighted.addEdge(p12, p13, 1); graphWeighted.addEdge(p23, p13, 1); graphWeighted.addEdge(p64, p63, 1);
        graphWeighted.addEdge(p60, p40, 2); graphWeighted.addEdge(p11, p01, 1); graphWeighted.addEdge(p22, p12, 1); graphWeighted.addEdge(p23, p22, 1); graphWeighted.addEdge(p64, p65, 1);
        graphWeighted.addEdge(p60, p61, 1); graphWeighted.addEdge(p21, p11, 1); graphWeighted.addEdge(p22, p21, 1); graphWeighted.addEdge(p23, p43, 2); graphWeighted.addEdge(p84, p83, 1);
        graphWeighted.addEdge(p60, p80, 2); graphWeighted.addEdge(p21, p20, 1); graphWeighted.addEdge(p22, p23, 1); graphWeighted.addEdge(p23, p24, 1); graphWeighted.addEdge(p84, p85, 1);
        graphWeighted.addEdge(p80, p60, 2); graphWeighted.addEdge(p21, p22, 1); graphWeighted.addEdge(p42, p41, 1); graphWeighted.addEdge(p43, p23, 2);
        graphWeighted.addEdge(p80, p81, 1); graphWeighted.addEdge(p41, p40, 1); graphWeighted.addEdge(p42, p43, 1); graphWeighted.addEdge(p43, p42, 1);
                                            graphWeighted.addEdge(p41, p42, 1); graphWeighted.addEdge(p62, p61, 1); graphWeighted.addEdge(p43, p63, 2);
                                            graphWeighted.addEdge(p61, p60, 1); graphWeighted.addEdge(p62, p63, 1); graphWeighted.addEdge(p43, p44, 1);
                                            graphWeighted.addEdge(p61, p62, 1); graphWeighted.addEdge(p82, p81, 1); graphWeighted.addEdge(p63, p43, 2);
                                            graphWeighted.addEdge(p81, p80, 1); graphWeighted.addEdge(p82, p83, 1); graphWeighted.addEdge(p63, p62, 1);
                                            graphWeighted.addEdge(p81, p82, 1);                                     graphWeighted.addEdge(p63, p83, 2);
                                                                                                                    graphWeighted.addEdge(p63, p64, 1);
                                                                                                                    graphWeighted.addEdge(p83, p63, 2);
                                                                                                                    graphWeighted.addEdge(p83, p82, 1);
                                                                                                                    graphWeighted.addEdge(p83, p84, 1);
      

        NodeWeighted s = punten[start];
        NodeWeighted e = punten[end];

        return graphWeighted.DijkstraShortestPath(s, e); 
       }
}