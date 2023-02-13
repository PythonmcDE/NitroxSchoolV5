package xyz.daarkii.school.manager;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.checkerframework.checker.units.qual.A;
import xyz.daarkii.school.core.entity.SchoolPlayer;
import xyz.daarkii.school.core.manager.LocationManager;

import java.util.ArrayList;
import java.util.Locale;

public class SafeZoneManager {


    private final Entity entity;
    private ArrayList<Location> x;
    private ArrayList<Location> y;
    private final int safezoneamount;

    public SafeZoneManager(Entity entity){
        this.entity = entity;
        safezoneamount = loadAmountOfSafeZones();
        x = new ArrayList<>();
        y = new ArrayList<>();
        for(int i = 1; i<= safezoneamount; i++) {
            this.x.add(new LocationManager().getLocation("zone" + i + "point1"));
            this.y.add(new LocationManager().getLocation("zone" + i + "point2"));
        }
    }

    /**
     * check whether a entity is in safezone or not
     * @return
     */
    public boolean isInSafeZone(){
        Location playerloc = entity.getLocation();
        Location playerlocation = new Location(playerloc.getWorld(), (int)playerloc.getX(), (int)playerloc.getY(), (int)playerloc.getZ());
        for(int i = 0; i< safezoneamount; i++) {
            if (getAllLocationsInside(x.get(i), y.get(i)).contains(playerlocation))
                return true;

        }
        return false;
    }

    /**
     * get List of all blocks inside an area of two locations
     * @param loc1
     * @param loc2
     * @return
     */
    private ArrayList<Location> getAllLocationsInside(Location loc1, Location loc2){
        int yTop = 0;
        int yBottom = 0;
        int xTop = 0;
        int xBottom = 0;
        int zTop = 0;
        int zBottom = 0;

        ArrayList<Location> locs = new ArrayList<>();

        if(loc1.getBlockY() > loc2.getBlockY()) {
            yTop = loc1.getBlockY();
            yBottom = loc2.getBlockY();
        }else {
            yTop = loc2.getBlockY();
            yBottom = loc1.getBlockY();
        }

        if(loc1.getBlockX() > loc2.getBlockX()) {
            xTop = loc1.getBlockX();
            xBottom = loc2.getBlockX();
        }else {
            xTop = loc2.getBlockX();
            xBottom = loc1.getBlockX();
        }
        if(loc1.getBlockZ() > loc2.getBlockZ()) {
            zTop = loc1.getBlockZ();
            zBottom = loc2.getBlockZ();
        }else {
            zTop = loc2.getBlockZ();
            zBottom = loc1.getBlockZ();
        }
        for(int x = xBottom; x < xTop; x++)
            for(int y = yBottom; y < yTop; y++)
                for(int z = zBottom; z < zTop; z++)
                    locs.add(new Location(loc1.getWorld(), x, y, z));
        return locs;

    }

    /**
     * loads the amount of safezones by the database or the locationcache
     * @return
     */
    public Integer loadAmountOfSafeZones(){
        int amount = 0;
        for(int i = 1; i<= 5; i++){
            LocationManager locationManager = new LocationManager();
            if(locationManager.locationexists("zone" + i + "point1") && locationManager.locationexists("zone" + i + "point2")){
                amount++;
            }
        }
        return amount;
    }

}