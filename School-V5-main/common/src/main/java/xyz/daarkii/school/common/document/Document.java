package xyz.daarkii.school.common.document;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.StringReader;
import java.lang.reflect.Type;
import java.util.*;

public class Document {

    private JsonObject jsonObject;

    public Document() {
        this.jsonObject = new JsonObject();
    }

    public Document(@NotNull JsonElement element) {
        this.jsonObject = element.isJsonObject() ? element.getAsJsonObject() : new JsonObject();
    }

    public Document(@NotNull String json) {

        JsonObject jsonObject = null;

        try {

            JsonReader reader = new JsonReader(new StringReader(json));
            reader.setLenient(true);

            var parser = JsonParser.parseReader(reader);
            jsonObject = parser.isJsonObject() ? parser.getAsJsonObject() : new JsonObject();
        } catch (JsonSyntaxException e) {
            //ignore
            e.printStackTrace();
        }

        this.jsonObject = jsonObject != null ? jsonObject : new JsonObject();
    }

    public boolean contains(@NotNull String key) {
        var jsonObject = this.getJsonObjectFromKey(key);
        return jsonObject != null && jsonObject.has(this.getEndKey(key));
    }

    public Document remove(@NotNull String key) {
        var jsonObject = this.getJsonObjectFromKey(key);

        if(jsonObject == null)
            return this;

        jsonObject.remove(this.getEndKey(key));
        return this;
    }

    public Document append(@NotNull String key, @NotNull Object value) {

        var jsonObject = this.getJsonObjectFromKey(key);

        if(jsonObject == null)
            return this;

        jsonObject.add(this.getEndKey(key), DocumentUtil.GSON.toJsonTree(value));
        return this;
    }

    public Document append(@NotNull String key, @NotNull Number value) {

        var jsonObject = this.getJsonObjectFromKey(key);

        if(jsonObject == null)
            return this;

        jsonObject.addProperty(this.getEndKey(key), value);
        return this;
    }

    public Document append(@NotNull String key, boolean value) {

        var jsonObject = this.getJsonObjectFromKey(key);

        if(jsonObject == null)
            return this;

        jsonObject.addProperty(this.getEndKey(key), value);
        return this;
    }

    public Document append(@NotNull String key, @NotNull String value) {

        var jsonObject = this.getJsonObjectFromKey(key);

        if(jsonObject == null)
            return this;

        jsonObject.addProperty(this.getEndKey(key), value);
        return this;
    }

    public Document append(@NotNull String key, @NotNull Document value) {

        var jsonObject = this.getJsonObjectFromKey(key);

        if(jsonObject == null)
            return this;

        jsonObject.add(this.getEndKey(key), value.jsonObject);
        return this;
    }

    public Document appendNull(@NotNull String key) {
        this.jsonObject.add(key, JsonNull.INSTANCE);
        return this;
    }

    @Nullable
    public Document document(@NotNull String key) {

        if(!this.contains(key))
            return null;

        var jsonObject = this.getJsonObjectFromKey(key);

        if(jsonObject == null)
            return this;

        key = this.getEndKey(key);

        JsonElement element = jsonObject.get(key);
        return element.isJsonObject() ? new Document(element) : null;
    }

    public int getInt(@NotNull String key) {
        Bukkit.broadcastMessage("1");
        if(!this.contains(key))
            return 0;
        Bukkit.broadcastMessage("2");
        var jsonObject = this.getJsonObjectFromKey(key);
        Bukkit.broadcastMessage("3");
        if(jsonObject == null)
            return 0;
        Bukkit.broadcastMessage("4");
        key = this.getEndKey(key);
        Bukkit.broadcastMessage("5");
        JsonElement element = jsonObject.get(key);
        Bukkit.broadcastMessage("6");
        Bukkit.broadcastMessage((element.isJsonPrimitive() ? element.getAsInt() : 0) + "");
        return element.isJsonPrimitive() ? element.getAsInt() : 0;
    }

    public double getDouble(@NotNull String key) {

        if(!this.contains(key))
            return 0;

        var jsonObject = this.getJsonObjectFromKey(key);

        if(jsonObject == null)
            return 0;

        key = this.getEndKey(key);

        JsonElement element = jsonObject.get(key);
        return element.isJsonPrimitive() ? element.getAsDouble() : 0;
    }

    public float getFloat(@NotNull String key) {

        if(!this.contains(key))
            return 0;

        var jsonObject = this.getJsonObjectFromKey(key);

        if(jsonObject == null)
            return 0;

        key = this.getEndKey(key);

        JsonElement element = jsonObject.get(key);
        return element.isJsonPrimitive() ? element.getAsFloat() : 0;
    }

    public byte getByte(@NotNull String key) {

        if(!this.contains(key))
            return 0;

        var jsonObject = this.getJsonObjectFromKey(key);

        if(jsonObject == null)
            return 0;

        key = this.getEndKey(key);

        JsonElement element = jsonObject.get(key);
        return element.isJsonPrimitive() ? element.getAsByte() : 0;
    }

    public long getLong(@NotNull String key) {

        if(!this.contains(key))
            return 0;

        var jsonObject = this.getJsonObjectFromKey(key);

        if(jsonObject == null)
            return 0;

        key = this.getEndKey(key);

        JsonElement element = jsonObject.get(key);
        return element.isJsonPrimitive() ? element.getAsLong() : 0;
    }

    public long getShort(@NotNull String key) {

        if(!this.contains(key))
            return 0;

        var jsonObject = this.getJsonObjectFromKey(key);

        if(jsonObject == null)
            return 0;

        key = this.getEndKey(key);

        JsonElement element = jsonObject.get(key);
        return element.isJsonPrimitive() ? element.getAsShort() : 0;
    }

    public boolean getBoolean(@NotNull String key) {

        if(!this.contains(key))
            return false;

        var jsonObject = this.getJsonObjectFromKey(key);

        if(jsonObject == null)
            return false;

        key = this.getEndKey(key);

        JsonElement element = jsonObject.get(key);
        return element.isJsonPrimitive() && element.getAsBoolean();
    }

    public String getString(@NotNull String key) {

        if(!this.contains(key))
            return null;

        var jsonObject = this.getJsonObjectFromKey(key);

        if(jsonObject == null)
            return null;

        key = this.getEndKey(key);

        JsonElement element = jsonObject.get(key);
        return element.isJsonPrimitive() ? element.getAsString() : null;
    }

    @NotNull
    public List<String> getStringList(@NotNull String key) {
        return this.getList(key);
    }

    @NotNull
    public <T> List<T> getList(@NotNull String key) {

        Type type = new TypeToken<List<T>>(){}.getType();
        List<T> result = this.get(key, type);

        return result == null ? Collections.emptyList() : new ArrayList<>(result);
    }

    @NotNull
    public <T> List<T> getList(@NotNull String key, Class<T[]> cls) {
        T[] array = DocumentUtil.GSON.fromJson(this.get(key), cls);
        return array == null ? Collections.emptyList() : new ArrayList<>(Arrays.asList(array));
    }

    @Nullable
    public JsonElement get(@NotNull String key) {

        if(!this.contains(key))
            return null;

        var jsonObject = this.getJsonObjectFromKey(key);

        if(jsonObject == null)
            return null;

        key = this.getEndKey(key);

        return jsonObject.get(key);
    }

    @Nullable
    public <T> T get(@NotNull String key, @NotNull Class<T> clazz) {
        return this.get(key, DocumentUtil.GSON, clazz);
    }

    @Nullable
    public <T> T get(@NotNull String key, @NotNull Type type) {
        return this.get(key, DocumentUtil.GSON, type);
    }

    @Nullable
    public <T> T get(@NotNull String key, @NotNull Gson gson, @NotNull Class<T> clazz) {
        JsonElement element = this.get(key);
        return element != null ? gson.fromJson(element, clazz) : null;
    }

    @Nullable
    public <T> T get(@NotNull String key, @NotNull Gson gson, @NotNull Type type) {
        JsonElement element = this.get(key);
        return element != null ? gson.fromJson(element, type) : null;
    }

    public Document getDocument(String key) {
        return new Document().setJsonObject(this.get(key, JsonObject.class));
    }

    public Document setJsonObject(JsonObject object) {
        this.jsonObject = object;
        return this;
    }

    public JsonObject getJsonObject() {
        return this.jsonObject;
    }

    @NotNull
    public String toJson() {
        return DocumentUtil.GSON.toJson(this.jsonObject);
    }

    @Override
    public String toString() {
        return this.toJson();
    }

    private JsonObject getJsonObjectFromKey(@NotNull String key) {

        var keyArray = key.split("[.]");

        if(keyArray.length <= 1)
            return this.jsonObject;

        //remove the content key
        key = key.replace("." + keyArray[keyArray.length - 1], "");
        keyArray = key.split("[.]");

        Document document = null;

        for(var content : keyArray) {

            document = document == null ? this.getDocument(content) : document.getDocument(content);

            if(document == null)
                return null;
        }

        return document == null ? null : document.jsonObject;
    }

    private String getEndKey(String key) {
        var keyArray = key.split("[.]");
        return keyArray[keyArray.length - 1];
    }
}
