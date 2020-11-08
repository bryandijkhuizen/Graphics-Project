package com.nhlstenden.amazonsimulatie.tests;

import static org.junit.Assert.assertEquals;

import com.nhlstenden.amazonsimulatie.models.Robot;
import com.nhlstenden.amazonsimulatie.models.Stellage;

import org.junit.Test;

public class RobotTest{
    @Test
    public void RobotTest() {
        Robot wallE = new Robot("wallE", 80, 50);
        Stellage ikea = new Stellage(0);
        
        //Test basis waarden van robot
        assertEquals("Robot type doesnt match with robot","robot", wallE.getType());
        assertEquals("Robot doesn't start at X value 80", 80, wallE.getX(), 0.0);
        assertEquals("Robot doesn't start at Y value 0", 0, wallE.getY(), 0.0);
        assertEquals("Robot doesn't start at Z value 50", 50, wallE.getZ(), 0.0);
        assertEquals("Robot doesn't start at rotation X value 0", 0, wallE.getRotationX(), 0.0);
        assertEquals("Robot doesn't start at rotation Y value 0", 0, wallE.getRotationY(), 0.0);
        assertEquals("Robot doesn't start at rotation Z value 0", 0, wallE.getRotationZ(), 0.0);

        //Test coordinaat waarden van stellage
        ikea.setX(1);
        ikea.setY(2);
        ikea.setZ(3);
        assertEquals("Storage position isn't at X value 1", 1,ikea.getX(), 0.0);
        assertEquals("Storage position isn't at Y value 2", 2,ikea.getY(), 0.0);
        assertEquals("Storage position isn't at Z value 3", 3,ikea.getZ(), 0.0);
        
        //Test aanroep van stellage door robot
        //assertEquals("Storage object isn't ...", ?, wall_E.getStellage()); 1ste stellage waarde
        wallE.setStellage(ikea);
        assertEquals("Storage object isn't jysk", ikea, wallE.getStellage());


        //Test aanroep voor nieuwe route
        //wallE.CallNewRoute(85, 01);
        //assertEquals("Robot can't find new route from start to end", 85, 01, wallE.CallNewRoute(85, 01), 0.0);
        
        //Test aanroep van eind node door robot
        assertEquals("Robot doesn't have node 1 as end node", 01,wallE.getEnd(), 0.0);
        //Test aanroep van eind node door robot met wijziging van end node
        wallE.setEnd(5);
        assertEquals("Robot doesn't have node 5 as end node", 5,wallE.getEnd(), 0.0);

        //Test aanroep status van de robot
        assertEquals("Robot status isn't 'idle'", "idle", wallE.getStatus());
        //Test aanroep status van de robot met wijziging van status
        wallE.setStatus("onderweg");
        assertEquals("Robot status isn't 'onderweg'", "onderweg", wallE.getStatus());


    }
}