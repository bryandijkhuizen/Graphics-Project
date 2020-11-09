package com.nhlstenden.amazonsimulatie.models;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

/*
 * Deze class is een versie van het model van de simulatie. In dit geval is het
 * de 3D wereld die we willen modelleren (magazijn). De zogenaamde domain-logic,
 * de logica die van toepassing is op het domein dat de applicatie modelleerd, staat
 * in het model. Dit betekent dus de logica die het magazijn simuleert.
 */
public class World implements Model {
    /*
     * De wereld bestaat uit objecten, vandaar de naam worldObjects. Dit is een lijst
     * van alle objecten in de 3D wereld. Deze objecten zijn in deze voorbeeldcode alleen
     * nog robots. Er zijn ook nog meer andere objecten die ook in de wereld voor kunnen komen.
     * Deze objecten moeten uiteindelijk ook in de lijst passen (overerving). Daarom is dit
     * een lijst van Object3D onderdelen. Deze kunnen in principe alles zijn. (Robots, vrachrtwagens, etc)
     */
    public static List<Robot> robotList;
    public static List<Stellage> stellageList;
    public static List<Truck> truckList;
    private List<Warehouse> warehouseList; 
    private boolean loaded; 
    public static String availableStellagePositions; 
    public static String unavailableStellagePositions; 

    /*
     * Dit onderdeel is nodig om veranderingen in het model te kunnen doorgeven aan de controller.
     * Het systeem werkt al as-is, dus dit hoeft niet aangepast te worden.
     */
    PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    /*
     * De wereld maakt een lege lijst voor worldObjects aan. Daarin wordt nu één robot gestopt.
     * Deze methode moet uitgebreid worden zodat alle objecten van de 3D wereld hier worden gemaakt.
     */
    public World() {
        this.warehouseList = new ArrayList<>();
        this.warehouseList.add(new Warehouse());
        this.availableStellagePositions = getAvailableStellagePositions(); 
        this.unavailableStellagePositions = getUnavailableStellagePositions(); 
        this.robotList = new ArrayList<>();
        this.stellageList = new ArrayList<>();
        this.truckList = new ArrayList<>();
        this.robotList.add(new Robot("Roberto", 80, 50));
        this.robotList.add(new Robot("Dustie", 60, 20));
        this.truckList.add(new Truck());
    }

    /*
     * Deze methode wordt gebruikt om de wereld te updaten. Wanneer deze methode wordt aangeroepen,
     * wordt op elk object in de wereld de methode update aangeroepen. Wanneer deze true teruggeeft
     * betekent dit dat het onderdeel daadwerkelijk geupdate is (er is iets veranderd, zoals een positie).
     * Als dit zo is moet dit worden geupdate, en wordt er via het pcs systeem een notificatie gestuurd
     * naar de controller die luisterd. Wanneer de updatemethode van het onderdeel false teruggeeft,
     * is het onderdeel niet veranderd en hoeft er dus ook geen signaal naar de controller verstuurd
     * te worden.
     */
    @Override
    public void update() {
        SimulationLoop();
        if(!loaded){
            for(Object3D object : warehouseList){
                if(object instanceof Updatable){
                    if(((Updatable)object).update()){ pcs.firePropertyChange(Model.UPDATE_COMMAND, null, new ProxyObject3D(object)); }
                }
            }
            this.loaded = true; 
        }
        for(Object3D object : robotList){
            if(object instanceof Updatable){
                if(((Updatable)object).update()){ pcs.firePropertyChange(Model.UPDATE_COMMAND, null, new ProxyObject3D(object)); }
            }
        }
        for(Object3D object : stellageList){
            if(object instanceof Updatable){
                if(((Updatable)object).update()){ pcs.firePropertyChange(Model.UPDATE_COMMAND, null, new ProxyObject3D(object)); }
            }
        }
        for(Object3D object : truckList){
            if(object instanceof Updatable){
                if(((Updatable)object).update()){ pcs.firePropertyChange(Model.UPDATE_COMMAND, null, new ProxyObject3D(object)); }
            }
        }  
    }

    public void SimulationLoop(){
        Truck truck = truckList.get(0); 
        //als de status van de truck op unloading staat:
        if(truck.getStatus().equals("unloading")){
            //voor elke robot:
            for(Robot robot : robotList){
                if(robot.getStatus().equals("KlaarOmInTeLaden")){
                    if(robotList.get(0).getStellage() == null && robotList.get(1).getStellage() == null){ truck.setStatus("loading"); }
                }
                //als er nog stellages in de truck staan:
                if(truck.countStellage() >= 0){
                    //als de robot geen stellage vervoert:
                    if(robot.getStellage() == null){
                        //als de robot bij de truck staat wordt de stellage van de truck op de robot gezet
                        if(robot.getX() == 0 && robot.getZ() == 10){
                            robot.setStellage(truck.getStellage());
                            robot.getStellage().setX(robot.getX());
                            robot.getStellage().setZ(robot.getZ());
                            robot.getStellage().setY(robot.getY());
                            //zet het einde van de de route op de stellage's bestemde positie
                            robot.setEnd(robot.getStellage().getStellageID() - 10);
                        }
                        //anders gaat de robot naar het loadingdock
                        else{ 
                            System.out.println("de robot gaat naar de vrachtwagen toe");
                            robot.setEnd(01); 
                            System.out.println("end after setting 3: " + robot.getEnd());
                        }
                    }
                }
                //als er geen stellages meer in de truck zijn is de truck aan het inladen
                else{ truck.setStatus("loading"); }
            }
        }       
         //als de truck aan het inladen is
         if(truck.getStatus().equals("loading")){
            //voor elke robot
            for(Robot robot : robotList){ //als er minder dan 5 stellages in de vrachtwagen staan
                if(truck.countStellage() < 5 && unavailableStellagePositions.length() >= 0){ //als de robot een stellage heeft
                        if(robot.getStellage() != null){ //als de robot bij de loadingdock staat zet de robot zijn stellage in de truck
                            if(robot.getX() == 0 && robot.getZ() == 10){
                                truck.addStellage(robot.getStellage());
                                robot.setStellage(null);   
                                truck.getStellage().setX(truck.getX());
                                truck.getStellage().setZ(truck.getZ());
                                robot.setEnd(Stellage.getOccupiedStellagePosition() - 10);
                                System.out.println("end after setting 1: " + robot.getEnd());
                            } else { //anders gaat de robot naar de loadingdock
                                robot.setEnd(01);
                                System.out.println("end after setting 5: " + robot.getEnd());
                            }
                        }
                        else{
                            //als de locatie van de robot zijn eindbestemming is
                            if((robot.getX() + (robot.getZ() / 10)) == robot.getEnd()){ 
                                //de juiste stellage pakken
                                for(int i = 0; i <= stellageList.size() - 1; i++){
                                    System.out.println("StellageID: " + stellageList.get(i).getStellageID());
                                    //als het em is
                                    if((stellageList.get(i)).getStellageID() == robot.getEnd() + 10){
                                        robot.setStellage(stellageList.get(i));
                                        robot.getStellage().setX(robot.getX());
                                        robot.getStellage().setZ(robot.getZ());
                                        robot.getStellage().setY(robot.getY());
                                        if(!(robot.getX() == 0 && robot.getZ() == 10)){
                                            robot.setEnd(01);
                                            System.out.println("end after setting 4: " + robot.getEnd());
                                        }
                                    }
                                } 
                            }
                        }
                        if(robot.getStatus().equals("onderweg")){ break; }
                }
                else {
                    truck.setStatus("leaving");
                    System.out.println("Truck is now leaving!");}
            }
        }
    }

    /*
     * Standaardfunctionaliteit. Hoeft niet gewijzigd te worden.
     */
    @Override
    public void addObserver(PropertyChangeListener pcl) { pcs.addPropertyChangeListener(pcl); }

    /*
     * Deze methode geeft een lijst terug van alle objecten in de wereld. De lijst is echter wel
     * van ProxyObject3D objecten, voor de veiligheid. Zo kan de informatie wel worden gedeeld, maar
     * kan er niks aangepast worden.
     */
    @Override
    public List<Object3D> getWorldObjectsAsList() {
        ArrayList<Object3D> returnList = new ArrayList<>();
        for(Object3D object : warehouseList){ returnList.add(new ProxyObject3D(object)); }
        for(Object3D object : robotList){ returnList.add(new ProxyObject3D(object)); }
        for(Object3D object : stellageList){ returnList.add(new ProxyObject3D(object)); }
        for(Object3D object : truckList){ returnList.add(new ProxyObject3D(object)); }
        return returnList;
    }
    public String getAvailableStellagePositions(){ return warehouseList.get(0).getAvailableStellagePositions(); }
    public String getUnavailableStellagePositions(){ return warehouseList.get(0).getUnavailableStellagePositions(); }
}