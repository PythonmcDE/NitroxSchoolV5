package xyz.daarkii.school.common.collection;

import xyz.daarkii.school.common.document.Document;
import xyz.daarkii.school.common.document.DocumentUtil;

import java.io.File;
import java.io.IOException;

import static java.util.Objects.requireNonNull;

public class Config extends Document {

    private final File file;
    private final String location;

    public Config(File file, String location) {
        this.file = file;
        this.location = location;

        this.load();
    }

    public void save() {
        DocumentUtil.writeFile(file, this);
    }

    protected void load() {

        if(!file.exists()) {

            //file does not exist
            file.getParentFile().mkdirs();

            //create file
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            //write data
            if(location != null)
                DocumentUtil.writeFile(file, DocumentUtil.readFile(location));
        }

        this.setJsonObject(DocumentUtil.readFile(file).getJsonObject());

        if(location != null) {

            Document tempFile = DocumentUtil.readFile(location);

            //remove the values which shouldn't be there anymore
            for(String key : this.getJsonObject().keySet()) {
                if(!tempFile.contains(key))
                    this.remove(key);
            }

            //add the new values
            for(String key : tempFile.getJsonObject().keySet()) {
                if(!this.contains(key))
                    this.append(key, requireNonNull(tempFile.get(key)));
            }

            this.save();
        }
    }

}
