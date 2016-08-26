package com.epam.mentoring.multithreading.currency.util;

import java.io.File;
import java.nio.file.Paths;

/**
 * Created by Endeg on 26.08.2016.
 */
public class StorageUtil {

    public static final String STORAGE_PATH = Paths.get("./storage").toAbsolutePath().normalize().toString();

    public static String getPathInStorage(String fileName) {
        new File(STORAGE_PATH).mkdirs();
        return Paths.get(STORAGE_PATH, fileName).normalize().toString();
    }

}
