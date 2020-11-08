package com.nhlstenden.amazonsimulatie.models;

import java.util.ArrayList;
import java.util.List;
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
    private String availableStellagePositions, unavailableStellagePositions;

    public Warehouse() {
        this.uuid = UUID.randomUUID();
        availableStellagePositions = "14 15 31 32 34 35 51 52 54 55 71 72 74 75 91 92 94 95 ";
        unavailableStellagePositions = "";
    }

    
    @Override
    public boolean update() { return true; }
    @Override
    public String getUUID() { return this.uuid.toString(); }
    public String getAvailableStellagePositions() { return this.availableStellagePositions; }
    public String getUnavailableStellagePositions() { return this.unavailableStellagePositions; }
    @Override
    public String getType() { return Warehouse.class.getSimpleName().toLowerCase(); }
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