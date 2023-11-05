package com.azarenka.service.impl.data;

import com.azarenka.exeptions.FileAppDataNotFoundException;
import com.azarenka.util.ApplicationUtil;

import org.slf4j.Logger;
import org.springframework.core.io.Resource;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Objects;

/**
 * Represents of .. .
 * <p>
 * Copyright (C) 2023 antazarenko@gmail.com
 * <p>
 * Date: 01/12/2023
 *
 * @author Anton Azarenka
 */
public abstract class AbstractFileDataLoaderManager {

    private static final Logger LOGGER = ApplicationUtil.getLogger();

    private Resource resource;

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    protected Object loadFile() throws FileAppDataNotFoundException{
        Object loadedObject;
        String filename = resource.getFilename();
        try (ObjectInputStream objectInputStream = new ObjectInputStream(
            new FileInputStream(Objects.requireNonNull(filename)))) {
            LOGGER.info("Load file started. FileName={}", filename);
            loadedObject = objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            LOGGER.info("File '{}' not found. Creating new file", filename);
            throw new FileAppDataNotFoundException(e);
        }
        LOGGER.info("Load file finished. FileName={}", filename);
        return loadedObject;
    }

    protected boolean saveFile(Object object) {
        String filename = resource.getFilename();
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(
            new FileOutputStream(Objects.requireNonNull(filename)))) {
            LOGGER.info("Save file started. FileName={}", filename);
            objectOutputStream.writeObject(object);
        } catch (IOException e) {
            LOGGER.error("Can not save an participants file: {}", e.getMessage());
            return false;
        }
        LOGGER.info("Save file finished. FileName={}", filename);
        return true;
    }
}
