package xyz.daarkii.school.core.bank;

import xyz.daarkii.school.common.document.Document;
import xyz.daarkii.school.core.entity.BasePlayer;

import java.util.Collection;

public interface Bank {

    long getID();

    Document getData();

    Collection<BasePlayer> getMember();

    double getGems();

    Level getLevel();

    double getLimit();

    void setLevel(Level level);

    void addGems(double gems);

    void removeGems(double gems);

    enum Level {

        WOOD(1000000),
        COAL(1000000),
        IRON(25000000),
        GOLD(100000000),
        DIAMOND(500000000),
        EMERALD(2000000000);

        private final double limit;

        Level(double limit) {
            this.limit = limit;
        }

        public double getLimit() {
            return this.limit;
        }

        public String pretty() {
            return this.toString().charAt(0) + this.toString().substring(1).toLowerCase();
        }

        public static Level fromString(String value) {
            return switch (value.toLowerCase()) {
                case "emerald" -> EMERALD;
                case "diamond" -> DIAMOND;
                case "gold" -> GOLD;
                case "iron" -> IRON;
                case "coal" -> COAL;
                default -> WOOD;
            };
        }
    }
}
