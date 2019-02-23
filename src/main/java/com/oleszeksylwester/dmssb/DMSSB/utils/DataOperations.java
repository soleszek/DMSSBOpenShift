package com.oleszeksylwester.dmssb.DMSSB.utils;

import com.oleszeksylwester.dmssb.DMSSB.enums.ObjectTypes;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.logging.Level;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
@Qualifier("dataOperations")
public class DataOperations {

    private static final Logger LOGGER = Logger.getLogger(DataOperations.class.getName());

    public static final String drawingsPath = "/home/sylwester/Dokumenty/DMSSBfiles/drawings/";
    public static final String documentsPath = "/home/sylwester/Dokumenty/DMSSBfiles/documents/";
    public static final String imagesPath = "/home/sylwester/Dokumenty/DMSSBfiles/images/";

    public static void saveData(String docType, InputStream fileContent, String fileName){
        if(docType.equals(ObjectTypes.DRAWING.getObjectType())){

            save(drawingsPath, fileContent, fileName);

        } else if (docType.equals(ObjectTypes.DOCUMENT.getObjectType())){

            save(documentsPath, fileContent, fileName);

        } else if(docType.equals(ObjectTypes.IMAGE.getObjectType())) {

            save(imagesPath, fileContent, fileName);
        }
    }

    public static void deleteData(String docType, String fileName){
        if(docType.equals(ObjectTypes.DRAWING.getObjectType())){

            delete(drawingsPath, fileName);

        } else if (docType.equals(ObjectTypes.DOCUMENT.getObjectType())){

            delete(documentsPath, fileName);

        } else if(docType.equals(ObjectTypes.IMAGE.getObjectType())) {

            delete(imagesPath, fileName);
        }
    }

    public static DataInputStream loadData(String docType, String fileName){
        if(docType.equals(ObjectTypes.DRAWING.getObjectType())){

            return read(drawingsPath, fileName);

        } else if (docType.equals(ObjectTypes.DOCUMENT.getObjectType())){

            return read(documentsPath, fileName);


        } else if(docType.equals(ObjectTypes.IMAGE.getObjectType())) {

            return read(imagesPath, fileName);

        }
        return null;
    }

    private static boolean delete(String path, String fileName){

        File targetFile = new File(path + fileName);
        boolean isFileDeleted = false;

        isFileDeleted = targetFile.delete();

        return isFileDeleted;

    }

    private static void save(String path, InputStream fileContent, String fileName){
        try {
            byte[] buffer = new byte[fileContent.available()];
            fileContent.read(buffer);

            File targetFile = new File(path + fileName);
            OutputStream outputStream = new FileOutputStream(targetFile);
            outputStream.write(buffer);

        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Can't save", e);
            e.printStackTrace();
        }
    }

    private static DataInputStream read(String path, String fileName){

        try {
            DataInputStream reader = new DataInputStream(new FileInputStream(path));
            return reader;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
