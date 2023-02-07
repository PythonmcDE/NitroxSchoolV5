package xyz.daarkii.school.core.bank;

import xyz.daarkii.school.common.document.Document;
import xyz.daarkii.school.core.entity.BasePlayer;

import java.util.Collection;

public class CoopBank implements Bank {

    private final Document data;

    public CoopBank(Document data) {
        this.data = data;
    }

    @Override
    public long getID() {
        return this.data.getLong("id");
    }

    @Override
    public Document getData() {
        return null;
    }

    @Override
    public Collection<BasePlayer> getMember() {
        return null;
    }

    @Override
    public double getGems() {
        return this.data.getDouble("gems");
    }

    @Override
    public Level getLevel() {
        return Level.fromString(this.data.getString("level"));
    }

    @Override
    public double getLimit() {
        return this.getLevel().getLimit();
    }

    @Override
    public void setLevel(Level level) {
        this.data.append("level", level.toString().toLowerCase());
    }

    @Override
    public void addGems(double gems) {
        this.data.append("gems", this.getGems() + gems);
    }

    @Override
    public void removeGems(double gems) {
        this.data.append("gems", this.getGems() - gems);
    }
}
