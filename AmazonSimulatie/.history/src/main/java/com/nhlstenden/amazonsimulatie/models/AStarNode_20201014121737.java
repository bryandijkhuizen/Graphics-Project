package com.nhlstenden.amazonsimulatie.models;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

/**
  The AStarNode class, along with the AStarSearch class,
  implements a generic A* search algorithm. The AStarNode
  class should be subclassed to provide searching capability.
*/
public abstract class AStarNode implements Comparable {

  AStarNode pathParent; //f
  float costFromStart; //g
  float estimatedCostToGoal; //h


  //returned de kost tussen het begin en einde van de route
  public float getCost() {
    return costFromStart + estimatedCostToGoal;
  }


  public int compareTo(Object other) {
    float thisValue = this.getCost();
    float otherValue = ((AStarNode)other).getCost();

    float v = thisValue - otherValue;
    return (v>0)?1:(v<0)?-1:0; // sign function
  }


  /**
    Gets the cost between this node and the specified
    adjacent (AKA "neighbor" or "child") node.
  */
  public abstract float getCost(AStarNode node);


  /**
    Gets the estimated cost between this node and the
    specified node. The estimated cost should never exceed
    the true cost. The better the estimate, the more
    effecient the search.
  */
  public abstract float getEstimatedCost(AStarNode node);


  /**
    Gets the children (AKA "neighbors" or "adjacent nodes")
    of this node.
  */
  public List getNeighbors(ArrayList[][] node){

    //maakt een tweedimensionale lijst aan waar de buren later in worden opgeslagen
    ArrayList[][] buren = new ArrayList[9][4]; 
    //de i en j staan voor de x en y van de node
    int i,j;
    if(i < 9-1){
        buren.add(node[i+1][j]); 
    }
    if(i < 0){
        buren.add(node[i-1][j]);
   }
    if(j > 4-1){
        buren.add(node[i][j+1]);
    }
    if(j > 0){
        buren.add(node[i][j-1]);
    }
    
    return buren; 
  }
}  