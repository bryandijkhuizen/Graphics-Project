package com.nhlstenden.amazonsimulatie.models;

//De punten binnen de graaf
public class Vertice {
    final private String id; //Coordinaat van punt
    final private String name; //Naam van punt

    public Vertice(String id, String name) {    //Initializeer
        this.id = id;
        this.name = name;
    }
    public String getId() {return id;}  //Geef de coordinaat van het punt
    public String getName() {return name;}  //Geef de naam van het punt
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {return true;}
        if (obj == null) {return false;}
        if (getClass() != obj.getClass()) {return false;}
        Vertice other = (Vertice) obj;
        if (id == null) {
            if (other.id != null) {return false;}                
        } else if (!id.equals(other.id)) {return false;}
        return true;
    }
    @Override
    public String toString() {
        return name;
    }    
}
