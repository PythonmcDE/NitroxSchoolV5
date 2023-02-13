package xyz.daarkii.school.monster;

import org.bukkit.Location;
import xyz.daarkii.school.core.manager.LocationManager;

import java.util.ArrayList;

public class MonsterLocations {

    public static ArrayList<Location> dungeon1Locations = new ArrayList<>();
    public static ArrayList<Location> dungeon2Locations = new ArrayList<>();
    public static ArrayList<Location> dungeon3Locations = new ArrayList<>();

    public MonsterLocations(){

    }

    //List of all possible spwanlocations in a dungeon
    public ArrayList<Location> getAllLocations(byte dungeon){
        if(dungeon1Locations.isEmpty()){
            getAllLocationsDatabase();
        }
        return dungeon1Locations;
        //TODO if all dungeon set uncomment
        /*
        switch (dungeon){
            case 2:
                if(!dungeon2Locations.isEmpty())
                    return dungeon2Locations;
                else return dungeon1Locations;
            case 3:
                if(!dungeon3Locations.isEmpty())
                    return dungeon3Locations;
                else return dungeon1Locations;
            default:
                return dungeon1Locations;
        }*/
    }

    //List of all possible spawnlocations in a dungeon (data from mysql database)
    public void getAllLocationsDatabase(){
        ArrayList<Location> locations1 = new ArrayList<>();
        ArrayList<Location> locations2 = new ArrayList<>();
        ArrayList<Location> locations3 = new ArrayList<>();
        int dungeon = 1;
        for(int i = 1; i<= 200; i++){
            LocationManager locationManager = new LocationManager();
            if(locationManager.locationexists("dungeon" + dungeon + "point" + i)){
                locations1.add(locationManager.getLocation("dungeon" + dungeon + "point" + i));
            }
            if(locationManager.locationexists("dungeon" + (dungeon+1) + "point" + i)){
                locations2.add(locationManager.getLocation("dungeon" + (dungeon+1) + "point" + i));
            }
            if(locationManager.locationexists("dungeon" + (dungeon+2) + "point" + i)){
                locations3.add(locationManager.getLocation("dungeon" + (dungeon+2) + "point" + i));
            }
        }
        dungeon1Locations = locations1;
        dungeon2Locations = locations2;
        dungeon3Locations = locations3;
    }
}
