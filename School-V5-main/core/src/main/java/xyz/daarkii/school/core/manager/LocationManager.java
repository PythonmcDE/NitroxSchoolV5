package xyz.daarkii.school.core.manager;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import xyz.daarkii.school.common.storage.MySQLStorage;
import xyz.daarkii.school.core.SchoolCore;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LocationManager {

    private final Map<String, Location> cachedLocations = new ConcurrentHashMap<>();

    private final MySQLStorage mySQL;

    public LocationManager() {
        this.mySQL = SchoolCore.getInstance().getMySQL();
    }

    public void addLocation(String key, Location location) {
        this.cachedLocations.put(key, location);

        try (var cn = this.mySQL.dataSource().getConnection(); var ps = cn.prepareStatement("INSERT INTO locations (name,world,x,y,z,yaw,pitch) VALUES (?,?,?,?,?,?,?) ON DUPLICATE KEY UPDATE world = ?, x = ?, y = ?, z = ?, yaw = ?, pitch = ?")) {

            ps.setString(1, key);
            ps.setString(2, location.getWorld().getName());
            ps.setDouble(3, location.getX());
            ps.setDouble(4, location.getY());
            ps.setDouble(5, location.getZ());
            ps.setFloat(6, location.getYaw());
            ps.setFloat(7, location.getPitch());

            ps.setString(8, location.getWorld().getName());
            ps.setDouble(9, location.getX());
            ps.setDouble(10, location.getY());
            ps.setDouble(11, location.getZ());
            ps.setFloat(12, location.getYaw());
            ps.setFloat(13, location.getPitch());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Location getLocation(String key) {
        return this.cachedLocations.getOrDefault(key, this.loadFromDatabase(key));
    }

    //Check whether Location exists or not
    public boolean locationexists(String key){
        try(var cn = this.mySQL.dataSource().getConnection(); var ps = cn.prepareStatement("SELECT * FROM locations WHERE name = ?")) {
            ps.setString(1, key);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return false;
    }

    private Location loadFromDatabase(String key) {

        try(var cn = this.mySQL.dataSource().getConnection(); var ps = cn.prepareStatement("SELECT * FROM locations WHERE name = ?")) {

            ps.setString(1, key);

            var result = ps.executeQuery();

            return !result.next() ? null : new Location(
                    Bukkit.getWorld(result.getString("world")),
                    result.getDouble("x"),
                    result.getDouble("y"),
                    result.getDouble("z"),
                    result.getFloat("yaw"),
                    result.getFloat("pitch")
            );
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
