package com.nhlstenden.amazonsimulatie.models;

import java.util.UUID;

/*
 * Deze class stelt een robot voor. Hij impelementeerd de class Object3D, omdat het ook een
 * 3D object is. Ook implementeerd deze class de interface Updatable. Dit is omdat
 * een robot geupdate kan worden binnen de 3D wereld om zich zo voort te bewegen.
 */
class Warehouse implements Object3D, Updatable {
    private UUID uuid;

    private double x = 0;
    private double y = 0;
    private double z = 0;

    private double rotationX = 0;
    private double rotationY = 0;
    private double rotationZ = 0;

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

    //private ArrayList<Integer> stellagePositions;
    private String availableStellagePositions;
    private String unavailableStellagePositions;

    public Warehouse() {
        this.uuid = UUID.randomUUID();
        // stellagePositions = new ArrayList<>(); 
        // stellagePositions.add(14);
        // stellagePositions.add(15);
        // stellagePositions.add(31);
        // stellagePositions.add(32);
        // stellagePositions.add(34);
        // stellagePositions.add(35);
        // stellagePositions.add(51);
        // stellagePositions.add(52);
        // stellagePositions.add(54);
        // stellagePositions.add(55);
        // stellagePositions.add(71);
        // stellagePositions.add(72);
        // stellagePositions.add(74);
        // stellagePositions.add(75);
        // stellagePositions.add(91);
        // stellagePositions.add(92);
        // stellagePositions.add(94);
        // stellagePositions.add(95);
        availableStellagePositions = "14 15 31 32 34 35 51 52 ";
        unavailableStellagePositions = "54 55 71 72 74 75 91 92 94 9 ";
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
        return true;
    }

    @Override
    public String getUUID() {
        return this.uuid.toString();
    }

    public String getAvailableStellagePositions(){
        return this.availableStellagePositions; 
    }

    public String getUnavailableStellagePositions(){
        return this.unavailableStellagePositions; 
    }

    @Override
    public String getType() {
        /*
         * Dit onderdeel wordt gebruikt om het type van dit object als stringwaarde terug
         * te kunnen geven. Het moet een stringwaarde zijn omdat deze informatie nodig
         * is op de client, en die verstuurd moet kunnen worden naar de browser. In de
         * javascript code wordt dit dan weer verder afgehandeld.
         */
        return Warehouse.class.getSimpleName().toLowerCase();
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
}