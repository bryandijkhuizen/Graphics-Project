package com.nhlstenden.amazonsimulatie.models;

import java.util.UUID;
/*
 * Deze class stelt een robot voor. Hij impelementeerd de class Object3D, omdat het ook een
 * 3D object is. Ook implementeerd deze class de interface Updatable. Dit is omdat
 * een robot geupdate kan worden binnen de 3D wereld om zich zo voort te bewegen.
 */
class Robot implements Object3D, Updatable {
    private UUID uuid;

    private double x = 80;
    private double y = 0;
    private double z = 50;

    private double rotationX = 0;
    private double rotationY = 0;
    private double rotationZ = 0;
    private Integer end; 
    private char[] path; 
    private int xIndex = 0;
    private int zIndex = 1;
    private Stellage stellage; 
    private String status = "idle";

    public String name; 
   
    
    

    public Robot(String name, double x, double z) {
        this.uuid = UUID.randomUUID();
        this.name = name; 
        this.x = x;
        this.z = z; 

    }

    /*
     * Deze update methode wordt door de World aangeroepen wanneer de World zelf
     * geupdate wordt. Dit betekent dat elk object, ook deze robot, in de 3D wereld
     * steeds een beetje tijd krijgt om een update uit te voeren. In de
     * updatemethode hieronder schrijf je dus de code die de robot steeds uitvoert
     * (bijvoorbeeld positieveranderingen). Wanneer de methode true teruggeeft
     * (zoals in het voorbeeld), betekent dit dat er inderdaad iets veranderd is en
     * dat deze nieuwe informatie naar de views moet worden gestuurd. Wordt false
     * teruggegeven, dan betekent dit dat er niks is veranderd, en de informatie
     * hoeft dus niet naar de views te worden gestuurd. (Omdat de informatie niet
     * veranderd is, is deze dus ook nog steeds hetzelfde als in de view)
     */
    @Override
    public boolean update() {
        Truck truck = World.truckList.get(0); 

        if(end != null){
            System.out.println("end: " + end);
            //status = "onderweg"; 
            if(path != null){
                status = "onderweg"; 
                this.x = ((path[xIndex]) - 48) * 10;
                this.z = ((path[zIndex]) -48) * 10;
                System.out.println("huidige x: " + x + "z: " + z);

                //als de robot een stellage heeft
                if(stellage != null){
                    //krijgt de stellage dezelfde coordinaten als de robot
                    stellage.setX(this.x);
                    stellage.setZ(this.z);
                }
                //als de zIndex kleiner is dan de lengte van het pad worden ze met 3 opgehoogd
                if(zIndex + 3 < path.length){
                    zIndex += 3;
                    xIndex += 3;  
                }
                //anders worden ze weer gereset
            else {
                zIndex = 1;
                xIndex = 0; 
                path = null;
                status = "idle"; 
                    if(stellage != null){
                        if(truck.getStatus().equals("unloading")){
                            stellage.setX(this.x + 10);
                            stellage.setZ(this.z);
                            stellage = null;
                            if(truck.countStellage() == 0){
                                end = 01; 
                                status = "KlaarOmInTeLaden";
                            }
                        }
                        else{
                            stellage.setX(truck.getX());
                            stellage.setZ(truck.getZ());
                            stellage.setY(truck.getY());
                            //World.stellageList.remove(stellage); 
                            stellage = null;
                            //end = Stellage.getOccupiedStellagePosition() - 10; 
                        }
                    }
                    else {
                        if(truck.getStatus().equals("unloading")){
                            if(status != "onderweg"){
                                end = Stellage.getOccupiedStellagePosition() - 10; 
                            }
                            //als de robot bij de stellage is aangekomen krijgen ze dezelfde x en z
                            for(int i = 0; i <= World.stellageList.size() - 1; i++){
                                if(World.stellageList.get(i).getStellageID() == end){
                                    this.stellage = World.stellageList.get(i); 
                                    stellage.setX(this.x);
                                    stellage.setZ(this.z);
                                }
                            }
                        }
                    }
                }
        } 
        else{
            CallNewRoute((int)(this.x + this.z /10), end);
        }
        try{
            Thread.sleep(100);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
        return true; 
    }

    public void CallNewRoute(int start, int end){
        String route = GraphShow.GetRoute(start, end);

        path = new char[route.length()];

        for (int i = 0; i < route.length(); i++) {
            path[i] = route.charAt(i);
         }
    }

    public void setEnd(Integer end) {
        this.end = end;
    }
    public int getEnd() { return this.end; }

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
    
    public void setStatus(String status){ this.status = status; }

    public String getStatus() { return this.status; }

    public void setStellage(Stellage stellage){ this.stellage = stellage; }

    public Stellage getStellage() { return this.stellage; }

}