package xyz.daarkii.school.mobs;

import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import org.bukkit.Location;
import org.checkerframework.checker.units.qual.A;
import xyz.daarkii.school.core.manager.LocationManager;

import java.util.ArrayList;

public class MonsterLocations {

    public static ArrayList<Location> dungeon1Locations = new ArrayList<>();
    //TODO for every dungeon (method / hashmap)

    public MonsterLocations(){

    }

    //List of all possible spwanlocations in a dungeon
    public ArrayList<Location> getAllLocations(byte dungeon){
        if(dungeon1Locations.isEmpty()){
            getAllLocationsDatabase(dungeon);
        }
        return dungeon1Locations;
    }

    //List of all possible spawnlocations in a dungeon (data from mysql database)
    public ArrayList<Location> getAllLocationsDatabase(byte dungeon){
        ArrayList<Location> locations = new ArrayList<>();
        for(int i = 1; i<= 200; i++){
            LocationManager locationManager = new LocationManager();
            if(locationManager.locationexists("dungeon" + dungeon + "point" + i)){
                System.out.println(locationManager.getLocation("dungeon" + dungeon  +"point" + i)); // Syntax key: "dungeon<1-3>point<1-infinity>"
                locations.add(locationManager.getLocation("dungeon" + dungeon + "point" + i));
            }
        }
        dungeon1Locations = locations;
        return locations;
    }
}
