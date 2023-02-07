package xyz.daarkii.school.common.document;

import com.google.gson.*;
import org.apache.commons.io.IOUtils;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.lang.reflect.Type;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class DocumentUtil {

    public static Gson GSON = new GsonBuilder()
            .serializeNulls()
            .disableHtmlEscaping()
            .setPrettyPrinting()
            .create();

    public static <T> void addTypeAdapter(Type type, TypeAdapter<T> adapter) {
        GSON = GSON.newBuilder().registerTypeAdapter(type, adapter).create();
    }

    public static <T> void addTypeAdapter(Type type, JsonDeserializer<T> adapter) {
        GSON = GSON.newBuilder().registerTypeAdapter(type, adapter).create();
    }

    @NotNull
    public static Document fromJson(String json) {
        return json != null ? new Document(json) : new Document();
    }

    @NotNull
    public static String toJson(Document document) {
        return document.toJson();
    }

    public static void writeFile(File file, Document document) {

        try(Writer writer = new FileWriter(file)) {
            GSON.toJson(document.getJsonObject(), writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Document readFile(File file) {

        try(InputStream stream = Files.newInputStream(file.toPath())) {
            String json = IOUtils.toString(stream, StandardCharsets.UTF_8);
            return json.isEmpty() ? new Document() : fromJson(json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static Document readFile(String name) {

        ClassLoader loader = DocumentUtil.class.getClassLoader();

        try(InputStream stream = loader.getResourceAsStream(name)) {

            if(stream == null)
                return new Document();

            return fromJson(IOUtils.toString(stream, StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Document readFile(File file, String name) {

        try {
            ClassLoader loader = new URLClassLoader(new URL[]{file.toURI().toURL()});

            try(InputStream stream = loader.getResourceAsStream(name)) {

                if(stream == null)
                    return new Document();

                return fromJson(IOUtils.toString(stream, StandardCharsets.UTF_8));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
