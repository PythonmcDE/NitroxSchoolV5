package xyz.daarkii.school.core.commands;

import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.OutlinePane;
import com.github.stefvanschie.inventoryframework.pane.Pane;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import xyz.daarkii.school.common.message.MessageLoader;
import xyz.daarkii.school.common.message.PlaceHolder;
import xyz.daarkii.school.common.utils.ItemBuilder;
import xyz.daarkii.school.common.utils.StringUtils;
import xyz.daarkii.school.core.bank.Bank;
import xyz.daarkii.school.core.bank.CoopBank;
import xyz.daarkii.school.core.command.Command;
import xyz.daarkii.school.core.entity.SchoolPlayer;
import xyz.daarkii.school.core.factory.PlayerFactory;

import java.util.List;

public class BankCMD extends Command {

    public BankCMD() {
        super("bank", "school.command.bank", "", List.of("ba"));
    }

    @Override
    public void execute(SchoolPlayer player, String[] args) {

        var gui = new ChestGui(3, MessageLoader.loadMessage("school.bank.general.item.name", player.getLanguage()));

        gui.addPane(this.backgroundPane());

        var pane = new StaticPane(0, 3, 9, 1);

        if(player.hasCoop()) {

            pane.addItem(new ItemBuilder(Material.ENDER_CHEST)
                    .setDisplayName(MessageLoader.loadComponent("school.bank.coop.item.name", player.getLanguage()))
                    .buildGuiItem(), 4, 2);

            pane.addItem(new ItemBuilder(Material.CHEST)
                    .setDisplayName(MessageLoader.loadComponent("school.bank.personal.item.name", player.getLanguage()))
                    .buildGuiItem(), 6, 2);
        } else
            pane.addItem(new ItemBuilder(Material.CHEST)
                    .setDisplayName(MessageLoader.loadComponent("school.bank.general.item.name", player.getLanguage()))
                    .buildGuiItem(), 5, 2);

        gui.addPane(pane);

        gui.setOnTopClick(event -> {

            var item = event.getCurrentItem();

            if(item == null)
                return;

            if(item.getType() != Material.ENDER_CHEST && item.getType() != Material.CHEST)
                return;

            var schoolPlayer = PlayerFactory.getPlayer(event.getWhoClicked().getUniqueId());

            if(schoolPlayer == null)
                return;

            var bank = item.getType() == Material.ENDER_CHEST ? schoolPlayer.getCoopBank() : schoolPlayer.getBank();

            schoolPlayer.showInventory(this.getBankGui(bank, schoolPlayer.getLanguage()));
        });
    }

    private ChestGui getBankGui(Bank bank, String language) {

        var key = bank instanceof CoopBank ? "school.bank.gui.coop" : "school.bank.gui.personal";
        var gui = new ChestGui(3, MessageLoader.loadMessage(key, language));

        gui.addPane(this.backgroundPane());

        var pane = new StaticPane(0, 5, 9, 1);

        var fillKey = bank instanceof CoopBank ? "school.bank.coop.fill.item.lore" : "school.bank.personal.fill.item.lore";
        var depositKey = bank instanceof CoopBank ? "school.bank.coop.deposit.item.lore" : "school.bank.personal.deposit.item.lore";

        pane.addItem(new ItemBuilder(Material.CHEST)
                        .setDisplayName(MessageLoader.loadComponent("school.bank.fill.item.name", language))
                        .setLore(MessageLoader.loadComponent(fillKey, language, new PlaceHolder("gems", StringUtils.formatGems(bank.getGems()))))
                        .buildGuiItem(), 4, 8);

        pane.addItem(new ItemBuilder(Material.CHEST)
                .setDisplayName(MessageLoader.loadComponent("school.bank.deposit.item.name", language))
                .setLore(MessageLoader.loadComponent(depositKey, language, new PlaceHolder("gems", StringUtils.formatGems(bank.getGems()))))
                .buildGuiItem(), 6, 8);

        gui.addPane(pane);

        return gui;
    }

    private OutlinePane backgroundPane() {

        var background = new OutlinePane(0, 0, 9 ,1);

        background.addItem(new GuiItem(new ItemStack(Material.BLACK_STAINED_GLASS_PANE)));
        background.setRepeat(true);
        background.setPriority(Pane.Priority.LOWEST);

        return background;
    }

    private Component buildLore(SchoolPlayer player, Bank bank) {
        return MessageLoader.loadComponent(
                "school.bank.item.lore",
                player.getLanguage(),
                new PlaceHolder("value", StringUtils.formatGems(bank.getGems())),
                new PlaceHolder("limit", StringUtils.formatGems(bank.getLimit())),
                new PlaceHolder("level", bank.getLevel().pretty())
        );
    }
}
