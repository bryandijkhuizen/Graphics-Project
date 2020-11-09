package com.nhlstenden.amazonsimulatie.models;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

import java.util.UUID;

/*
 * Deze class stelt een robot voor. Hij impelementeerd de class Object3D, omdat het ook een
 * 3D object is. Ook implementeerd deze class de interface Updatable. Dit is omdat
 * een robot geupdate kan worden binnen de 3D wereld om zich zo voort te bewegen.
 */
public class Stellage implements Object3D, Updatable {

    private UUID uuid;

    private double x = 0;
    private double y = 0;
    private double z = 0;

    private double rotationX = 0;
    private double rotationY = 0;
    private double rotationZ = 0;

    private Stellage stellage; 
    private int ID; 
    private int startX, startZ; 
    public static int xTarget;
    public static int zTarget; 

    public Stellage(int ID) {
        this.uuid = UUID.randomUUID();
        this.ID = ID; 
    
    }

    public static int getAvailableStellagePosition(){

        String stellagePositions = World.availableStellagePositions;

        if(stellagePositions.length() > 0){ 
            //coords is de eerste 2 cijfers in de string met beschikbare stellages
            String coords = World.availableStellagePositions.substring(0, 2);
            //de string naar int converten
            int c = Integer.parseInt(coords); 
            xTarget = Integer.parseInt(World.availableStellagePositions.substring(0, 1));
            zTarget = Integer.parseInt(World.availableStellagePositions.substring(1, 2));
            //de eerste 3 items uit de string verwijderen zodat we de volgende keer niet dezelfde stellage krijgen
            World.availableStellagePositions = World.availableStellagePositions.substring(3);
            //de stellage toevoegen aan de niet beschikbare stellage string
            World.unavailableStellagePositions = World.unavailableStellagePositions + coords + " ";
            return c; 
        }
        else return 00; 
    }
    public static int getOccupiedStellagePosition(){

        String stellagePositions = World.unavailableStellagePositions;

        if(stellagePositions.length() > 0){ 
            //coords is de eerste 2 cijfers in de string met beschikbare stellages
            String coords = World.unavailableStellagePositions.substring(0, 2);
            //de string naar int converten
            int c = Integer.parseInt(coords); 
            xTarget = Integer.parseInt(World.unavailableStellagePositions.substring(0, 1));
            zTarget = Integer.parseInt(World.unavailableStellagePositions.substring(1, 2));
            //de eerste 3 items uit de string verwijderen zodat we de volgende keer niet dezelfde stellage krijgen
            World.unavailableStellagePositions = World.unavailableStellagePositions.substring(3);
            //de stellage toevoegen aan de niet beschikbare stellage string
            World.availableStellagePositions = World.availableStellagePositions + coords + " ";
            System.out.println("BESCHIKBAAR: " + World.availableStellagePositions);
            System.out.println("ONBESCHIKBAAR: " + World.unavailableStellagePositions);
            return c; 
        }
        else return 00; 
    }

    @Override
    public boolean update() {
         return true; 
    }

    @Override
    public String getUUID() {
        return this.uuid.toString();
    }

    @Override
    public String getType() {
        /*
         * Dit onderdeel wordt gebruikt om het type van dit object als stringwaarde terug
         * te kunnen geven. Het moet een stringwaarde zijn omdat deze informatie nodig
         * is op de client, en die verstuurd moet kunnen worden naar de browser. In de
         * javascript code wordt dit dan weer verder afgehandeld.
         */
        return Stellage.class.getSimpleName().toLowerCase();
    }

    public Integer getPosition(){
        return (int)(x + (z/10)); 
    }

    @Override
    public double getX() {
        return this.x;
    }

    @Override
    public double getY() {
        return this.y;
    }

    @Override
    public double getZ() {
        return this.z;
    }

    @Override
    public double getRotationX() {
        return this.rotationX;
    }

    @Override
    public double getRotationY() {
        return this.rotationY;
    }

    @Override
    public double getRotationZ() {
        return this.rotationZ;
    }
    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public void setStellage(Stellage stellage){ this.stellage = stellage; }

    public Stellage getStellage() { return this.stellage; }

    public int getStellageID() { return this.ID; }
}