package xyz.daarkii.school.common.message;

import lombok.experimental.UtilityClass;
import net.kyori.adventure.text.Component;

@UtilityClass
public class MessageLoader {

    public String loadMessage(String key, String language) {
        return tv.aysu.api.lib.message.MessageLoader.loadMessage(key, language);
    }

    public Component loadComponent(String key, String language, PlaceHolder... placeHolders) {
        return MessageWrapper.wrap(loadMessage(key, language), placeHolders);
    }

}
