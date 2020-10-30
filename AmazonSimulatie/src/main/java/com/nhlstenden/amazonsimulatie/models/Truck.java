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
    private double x = 0;   //- vooruit, + achteruit
    private double y = 0; //- omlaag, + omhoog
    private double z = 0; //- rechts, + links

    private double rotationX = 0;
    private double rotationY = 0; //1.58 = 90 graden draaien
    private double rotationZ = 0;
    private int speed = 3;
    private int timer = 0;
    private boolean drivingForward = true;
    private boolean pause = true;
    private boolean drivingBackward = false;
    private boolean endPathCheck = false;

    private String status = "unloading";
    private List<Stellage> stellageLading; 

    public Truck() {
        this.uuid = UUID.randomUUID();
        stellageLading = new ArrayList<>();
        addStellages(1);
    }

    /*
     * Deze update methode wordt door de World aangeroepen wanneer de
     * World zelf geupdate wordt. Dit betekent dat elk object, ook deze
     * robot, in de 3D wereld steeds een beetje tijd krijgt om een update
     * uit te voeren. In de updatemethode hieronder schrijf je dus de code
     * die de robot steeds uitvoert (bijvoorbeeld positieveranderingen). Wanneer
     * de methode true teruggeeft (zoals in het voorbeeld), betekent dit dat
     * er inderdaad iets veranderd is en dat deze nieuwe informatie naar de views
     * moet worden gestuurd. Wordt false teruggegeven, dan betekent dit dat er niks
     * is veranderd, en de informatie hoeft dus niet naar de views te worden gestuurd.
     * (Omdat de informatie niet veranderd is, is deze dus ook nog steeds hetzelfde als
     * in de view)
     */
    @Override
    public boolean update() {
        
        if (status.equals("leaving")) { //Als de truck vooruit kan rijden
            drivingForward = true;
            x = x - speed;  //Rij vooruit
        }        
        if (endPathCheck) { //Als het einde van de rit is bereikt
            //Begin dan met tellen voor de pauze
            timer += 1;
        } else if (timer == 4) {    //Als de pauze van 4 ticks is gebeurd
            timer = 0;
            x = -96; //Spawn truck
            endPathCheck = false;
            //Commando voor achteruit rijden
            drivingBackward = true;
        }
        if (drivingBackward) {
             x = x + speed; 
        }
        return true;
    }

    public void drivingChecks() {
        while (!endPathCheck) { //Terwijl de route nog bezig is
            if(x == -96 || drivingForward) {    //Als de vertrokken is
                drivingForward = false; //stop met rijden
                x = 69420;   //ga weg
                endPathCheck = true;
            }
            if (x == 0 || drivingBackward) {   //Als de vrachtwacht aangedockt is
                drivingBackward = false;    //Vrachtwagen aangekomen
                status = "unloading";   //Stellages uitladen
            }
            // Als ? stellages zijn ingeladen
            // {
            //     //drivingForward = true;
            // }
        }
    }

    public void addStellages(int count){
        for(int i = 0; i < count; i++){
            stellageLading.add(new Stellage()); 
        }
    }

    public int countStellage(){
        return this.stellageLading.size(); 
    }

    public void addStellage(Stellage stellage){
        stellageLading.add(stellage);
    }

    public Stellage getStellage(){
        Stellage stellage = stellageLading.get(0);
        stellageLading.remove(0); 
        return stellage; 
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