package com.nhlstenden.amazonsimulatie.models;

import java.util.UUID;

/*
 * Deze class stelt een robot voor. Hij impelementeerd de class Object3D, omdat het ook een
 * 3D object is. Ook implementeerd deze class de interface Updatable. Dit is omdat
 * een robot geupdate kan worden binnen de 3D wereld om zich zo voort te bewegen.
 */
class Robot implements Object3D, Updatable {
    private UUID uuid;

    private double x = 0;
    private double y = 0;
    private double z = 0;

    private double rotationX = 0;
    private double rotationY = 0;
    private double rotationZ = 0;

    int cols = 9;
    int rows = 4; 

    int[] openset = new int[cols*rows]; 
    int[] closedset = new int[cols*rows]; 
    int start;
    int end; 

    w = width / cols; 
    h = height / rows; 

    //deze functie geeft f(de kortste route), g(de afgelegde afstand) en h(de nog af te leggen afstand) een variabele
    public int Spot(i,j) {
        this.x = i;
        this.y = j; 

        this.f = 0;
        this.g = 0;
        this.h = 0; 
        } 
    }

    //de aanmaak van een tweedimensionale array als grid
    int[][] grid = new int[cols][rows]; 
    
    //voor elk punt in de array wordt er een punt aangemaakt
    for(int i = 0; i < cols; i++){
        for(int j = 0; j < rows; j++){
            grid[i][j] = new Spot(i,j); 
        }
    }

    start = grid[9][0];
    end = grid[2][4]; 

    openset.push(start); 

    while(openset.Length > 0){

    }
    else{
        //geen oplossing mogelijk
    }

    public Robot() {
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
        if(x < 15) {
            this.x += 0.5;
        } else {
            this.z += 0.5;
        }
        
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
        return Robot.class.getSimpleName().toLowerCase();
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