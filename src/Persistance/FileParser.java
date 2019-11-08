package Persistance;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;

public class FileParser {
    public static String defaultPath = "";
    private File file;

    /**
     * Construct a new FileParser with the given file location
     *
     * @param fileName the name of the file you want to find/create
     */
    public FileParser(String fileName) {
        this(fileName, true);
    }

    public FileParser(String fileName, boolean useDefaultPath) {
        if(defaultPath.isEmpty()) {
            defaultPath = System.getenv("APPDATA") + "/Dungeonomicon/";
        }
        if(useDefaultPath) {
            file = new File(defaultPath + fileName);
        }
        else {
            file = new File(fileName);
        }
    }

    public void ParseFile() {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
            Document doc = dbBuilder.parse(file);
            //This was recommended
            doc.getDocumentElement().normalize();

        }
        catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
    }

    public void SaveToFile() {

    }
}
