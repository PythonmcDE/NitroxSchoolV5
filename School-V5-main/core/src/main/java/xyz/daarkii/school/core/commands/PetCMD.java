package xyz.daarkii.school.core.commands;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import xyz.daarkii.school.core.command.Command;
import xyz.daarkii.school.core.entity.SchoolPlayer;
import xyz.daarkii.school.core.pets.SchoolPet;

import java.io.BufferedReader;
import java.util.List;

public class PetCMD extends Command {

    public PetCMD(){
        super("pet", "School.command.pet", "",List.of("pets", "haustier"));
    }

    @Override
    public void execute(SchoolPlayer player, String[] args) {
        Bukkit.broadcastMessage(player.getGems() + "");
        if(args.length == 1) {
            new SchoolPet(player, SchoolPet.Pet.ANTON).setPetOwned();
            Bukkit.broadcastMessage("Pet is now owned");
        }else if(args.length == 0){
            Bukkit.broadcastMessage(new SchoolPet(player, SchoolPet.Pet.ANTON).getPet("anton") + "");
        }else {
            player.sendMessage("School.command.pet.error");
            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_HURT, 1F, 1F);
        }
    }
}
