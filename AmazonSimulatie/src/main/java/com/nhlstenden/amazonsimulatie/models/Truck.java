package com.nhlstenden.amazonsimulatie.models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/*
 * Deze class stelt een truck voor. Hij impelementeerd de class Object3D, omdat het ook een
 * 3D object is. Ook implementeerd deze class de interface Updatable. Dit is omdat
 * een robot geupdate kan worden binnen de 3D wereld om zich zo voort te bewegen.
 */
class Truck implements Object3D, Updatable {
    private UUID uuid;
    private double x = 0;  
    private double y = 0;
    private double z = 0;

    private double rotationX = 0;
    private double rotationY = 0;
    private double rotationZ = 0;

    private int speed = 6;
    private int timer = 0;
    private boolean drivingBackward = false;

    public String status = "unloading";
    public static List<Stellage> stellageLading; 

    public Truck() {
        this.uuid = UUID.randomUUID();
        stellageLading = new ArrayList<>();
        addStellages(2);
    }
    
    @Override
    public boolean update() {
        if (World.robotList.get(0).getStatus().equals("WachtendOpTruck") && World.robotList.get(1).getStatus().equals("WachtendOpTruck") && drivingBackward == false) {
            if(World.stellageList.isEmpty() && status.equals("leaving")){
                x = x - speed;  //Rij vooruit
            }
            else if (status.equals("leaving") && World.robotList.get(0).getStellage() == null && World.robotList.get(1).getStellage() == null) { //Als de truck vooruit kan rijden
                for(int i = 0; i <= World.stellageList.size(); i++){
                        World.stellageList.remove(0);
                        System.out.println("LAHDAJSJIJDJAKDA: " + World.stellageList);
                }
            }
        }
        if (drivingBackward) {
            speed = 6; 
             x = x + speed; 
             if(x == 0){
                 drivingBackward = false; 
                 status = "unloading";
                 addStellages(4);
                 for(Robot robot : World.robotList){
                     robot.setStatus("idle");
                 }

             }
        }
        else if(x == -96){
            timer += 1; 
            speed = 0; 
        }
        if (timer == 4) {    //Als de pauze van 4 ticks is gebeurd
            timer = 0;
            //Commando voor achteruit rijden
            drivingBackward = true;
        }
        return true;
    }

    public void addStellages(int count){
        for(int i = 0; i < count; i++){
            int available = Stellage.getAvailableStellagePosition(); 
            stellageLading.add(new Stellage(available)); 
            World.stellageList.add(new Stellage(available)); 
            World.stellageList.get(i).setY(-18);
        }
    }

    public int countStellage(){
        return this.stellageLading.size(); 
    }

    public void addStellage(Stellage stellage){
        stellageLading.add(stellage);
    }

    public Stellage getStellage(){
        int count = countStellage(); 
        if(count >= 1){
        Stellage stellage = World.stellageList.get(count - 1); 
        stellageLading.remove(0); 
        return stellage; 
        }
        return null; 
    }

    @Override
    public String getUUID() { return this.uuid.toString(); }

    //Dit onderdeel wordt gebruikt om het type van dit object als stringwaarde terug te kunnen geven. Het moet een stringwaarde zijn omdat deze informatie nodig 
    //is op de client, en die verstuurd moet kunnen worden naar de browser. In de javascript code wordt dit dan weer verder afgehandeld.
    @Override
    public String getType() { return Truck.class.getSimpleName().toLowerCase(); }
    @Override
    public double getX() { return this.x; }
    @Override
    public double getY() { return this.y; }
    @Override
    public double getZ() { return this.z; }
    @Override
    public double getRotationX() { return this.rotationX; }
    @Override
    public double getRotationY() { return this.rotationY; }
    @Override
    public double getRotationZ() { return this.rotationZ; }

    public void setStatus(String status){ this.status = status; }

    public String getStatus() { return this.status; }
}