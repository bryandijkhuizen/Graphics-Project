package com.nhlstenden.amazonsimulatie.models;
import java.util.List;
import java.util.LinkedList;

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
        int start = 0;
        int end = 4; 
        GraphShow.GetRoute(start, end);
        return true; 
    }
    //     if(target == null){
    //         LinkedList<Vertice> path = DijkstraAlgorithm.getPath(target); 
    //         if(!path.isEmpty()){
    //             target = path.get(path.size() - 1); 
    //             this.path.addAll(path);
    //         }
    //     }
    //     target = //coordinaten van het pad
    //     pad = getPath(Vertice target); //ik begrijp niet wat ik moet meegeven
    //     for (int i = 0; i < pad.length; i++) 
    //     { 
    //         if(){
    //             x += 25; 
    //         }
    //         //kijken of het omhoog, omlaag, naar links of rechts is. 
    //         //coordinaten van de robot aanpassen
    //     }
    // }

    // public void moveToTarget(Position target, Position current) {
    //     double deltaX = target.getX() - current.getX();
    //     double deltaY = target.getY() - current.getY();

    //     up = deltaY < 0 && Math.abs(deltaY) > Position.PROXIMITY_RANGE;
    //     right = deltaX > 0 && Math.abs(deltaX) > Position.PROXIMITY_RANGE;
    //     down = deltaY > 0 && Math.abs(deltaY) > Position.PROXIMITY_RANGE;
    //     left = deltaX < 0 && Math.abs(deltaX) > Position.PROXIMITY_RANGE;
    // }

    // private void setNewCurrentPosition()
    //     {
    //         // Get robot current row.
    //         int CurrentRow = (int)Math.floor(this.z / 25);
    //         // Get robot current column.
    //         int CurrentColumn = (int)Math.floor(this.x / 25);
    //         // Set current position.
    //         CurrentPos = CurrentRow.ToString() + CurrentColumn.ToString();
    //     }



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