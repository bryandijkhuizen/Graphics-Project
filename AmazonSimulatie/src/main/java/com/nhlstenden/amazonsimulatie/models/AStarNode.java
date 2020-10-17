package com.nhlstenden.amazonsimulatie.models;

import java.util.ArrayList;
import java.util.List;

/**
  The AStarNode class, along with the AStarSearch class,
  implements a generic A* search algorithm. The AStarNode
  class should be subclassed to provide searching capability.
*/
public abstract class AStarNode implements Comparable {
  private int gridSizeX;
  private int gridSizeY;
  private AStarNode[][] grid;

  AStarNode pathParent; //f
  float costFromStart; //g
  float estimatedCostToGoal; //h

  private final int[][] navigatie = {
    {0, 1},   //Vooruit
    {1, 0},   //Rechts
    {0, -1},  //Achteruit
    {-1, 0}   //Links
  };


  //returned de kost tussen het begin en einde van de route
  public float getCost() { return costFromStart + estimatedCostToGoal; }


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
  public int getCost(AStarNode startNode, AStarNode endNode) {
    return Math.abs(startNode.getGridX() - endNode.getGridX()) + Math.abs(startNode.getGridY() - endNode.getGridY());
  }


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
  public List<AStarNode> getNeighbour(AStarNode node) {
    List<AStarNode> neighbours = new ArrayList<>();
    for (int[] vec : navigatie) {
      int checkX = (node.getGridX() + vec[0]);
      int checkY = (node.getGridY() + vec[1]);
      if (checkX >= 0 && checkX < gridSizeX && checkY >= 0 && checkY < gridSizeY) {
        neighbours.add(grid[checkX][checkY]);
      }
    }
    return neighbours;
  }
  public int getGridX() { return gridSizeX; }
  public int getGridY() { return gridSizeY; }
}  