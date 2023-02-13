package xyz.daarkii.school.commands;

import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.OutlinePane;
import com.github.stefvanschie.inventoryframework.pane.Pane;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;
import org.bukkit.Material;
import xyz.daarkii.school.common.message.MessageLoader;
import xyz.daarkii.school.common.message.PlaceHolder;
import xyz.daarkii.school.common.utils.ItemBuilder;
import xyz.daarkii.school.common.utils.StringUtils;
import xyz.daarkii.school.core.bank.PersonalBank;
import xyz.daarkii.school.core.command.Command;
import xyz.daarkii.school.core.entity.SchoolPlayer;

import java.util.List;

public class DungeonCommand extends Command {

    public DungeonCommand(){
        super("dungeon", "school.command.dungeon", "", List.of("monsterworld", "monsterwelt","dungeonworld", "dungoen"));
    }

    @Override
    public void execute(SchoolPlayer player, String[] args) {
        player.showInventory(this.getDungeonGui(player.getLanguage()));
    }

    private ChestGui getDungeonGui(String language){
        var key = "school.dungeon.inventory.headline";
        var gui = new ChestGui(6, MessageLoader.loadMessage(key, language));

        gui.addPane(this.backgroundPane());

        var pane = new StaticPane(0, 1, 9, 2);

        var titleKeyone = "school.dungeon.item.teleport.name";
        var loreKeyone = "school.dungeon.item.teleport.lore";
        var titleKeytwo = "school.dungeon.item.dungeon.name";
        var loreKeytwo = "school.dungeon.item.dungeon.lore";

        pane.addItem(new ItemBuilder(Material.ENDER_EYE)
                .setDisplayName(MessageLoader.loadComponent(titleKeyone, language))
                .setLore(MessageLoader.loadComponent(loreKeyone, language))
                .buildGuiItem(), 3, 0);

        pane.addItem(new ItemBuilder(Material.DIAMOND_SWORD)
                .setDisplayName(MessageLoader.loadComponent(titleKeytwo, language))
                .setLore(MessageLoader.loadComponent(loreKeytwo, language))
                .buildGuiItem(), 5, 0);

        gui.addPane(pane);
        return gui;
    }

    private OutlinePane backgroundPane() {

        var background = new OutlinePane(0, 0, 9 ,6);

        background.addItem(new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setDisplayName(" ").buildGuiItem());
        background.setRepeat(true);
        background.setPriority(Pane.Priority.LOWEST);

        background.setOnClick(event -> event.setCancelled(true));
        return background;
    }
}
