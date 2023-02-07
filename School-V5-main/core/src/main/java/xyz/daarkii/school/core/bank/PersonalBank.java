package xyz.daarkii.school.core.bank;

import xyz.daarkii.school.common.document.Document;
import xyz.daarkii.school.core.entity.BasePlayer;

import java.util.Collection;
import java.util.List;

public class PersonalBank extends CoopBank {

    private final BasePlayer owner;

    public PersonalBank(Document data, BasePlayer owner) {
        super(data);
        this.owner = owner;
    }

    public BasePlayer getOwner() {
        return this.owner;
    }

    @Override
    public Collection<BasePlayer> getMember() {
        return List.of(this.owner);
    }
}
