package com.nhlstenden.amazonsimulatie.models;
import java.util.UUID;

/*
 * Deze class stelt een truck voor. Hij impelementeerd de class Object3D, omdat het ook een
 * 3D object is. Ook implementeerd deze class de interface Updatable. Dit is omdat
 * een robot geupdate kan worden binnen de 3D wereld om zich zo voort te bewegen.
 */
class Truck implements Object3D, Updatable {
    private UUID uuid;
    private double x = 116;  
    private double y = -2.1; //hoog / laag
    private double z = 150.25;

    private double rotationX = 0;
    private double rotationY = 0;
    private double rotationZ = 0;

    public Truck() {
        this.uuid = UUID.randomUUID();
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
        /*eind locatie aandocken
        this.x = 62;
        this.y = -2.1;
        this.z = 15.25;*/
        return true;
    }

    @Override
    public String getUUID() { return this.uuid.toString(); }

    //Dit onderdeel wordt gebruikt om het type van dit object als stringwaarde terug te kunnen geven. Het moet een stringwaarde zijn omdat deze informatie nodig 
    //is op de client, en die verstuurd moet kunnen worden naar de browser. In de javascript code wordt dit dan weer verder afgehandeld.
    @Override
    public String getType() { return Robot.class.getSimpleName().toLowerCase(); }
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
}