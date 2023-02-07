package xyz.daarkii.school.common.message;

import net.kyori.adventure.text.Component;

public class PlaceHolder {

    private final String name;
    private final Component component;

    public PlaceHolder(String name, Component component) {
        this.name = name;
        this.component = component;
    }

    public PlaceHolder(String name, String value) {
        this(name, MessageWrapper.wrap(value));
    }

    public PlaceHolder(String name, int value) {
        this(name, String.valueOf(value));
    }

    public PlaceHolder(String name, long value) {
        this(name, String.valueOf(value));
    }

    public PlaceHolder(String name, double value) {
        this(name, String.valueOf(value));
    }

    public PlaceHolder(String name, float value) {
        this(name, String.valueOf(value));
    }

    public PlaceHolder(String name, boolean value) {
        this(name, String.valueOf(value));
    }

    public PlaceHolder(String name, Object value) {
        this(name, String.valueOf(value));
    }

    public String name() {
        return this.name;
    }

    public Component component() {
        return this.component;
    }
}
