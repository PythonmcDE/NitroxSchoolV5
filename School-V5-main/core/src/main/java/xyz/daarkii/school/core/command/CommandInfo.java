package xyz.daarkii.school.core.command;

import org.jetbrains.annotations.ApiStatus;

import java.util.Arrays;
import java.util.Objects;

/**
 * This class holds default information for a {@link Command}
 *
 * @param name          The name of the command
 * @param permission    The permission of the command
 * @param usage         The message which should be sent when a user entered a wrong form of the command
 * @param aliases       The aliases of the command
 */
@ApiStatus.Internal
public record CommandInfo(String name, String permission, String usage, java.util.Collection<String> aliases) {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommandInfo that = (CommandInfo) o;
        return name.equals(that.name) && Objects.equals(permission, that.permission) && Objects.equals(usage, that.usage) && aliases.equals(that.aliases);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, permission, usage, aliases);
    }
}
