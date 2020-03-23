package Persistance;

import javax.tools.JavaCompiler;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;

public class FileParser {
    private static String defaultPath = "";
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
        if(!file.exists()) {
            try {
                String defaultXML = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><doc></doc>";
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
                Document doc = dbBuilder.parse(new InputSource(new StringReader(defaultXML)));

                doc.getDocumentElement().normalize();
                SaveToFile(doc);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Document ParseFile() {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
            Document doc = dbBuilder.parse(file);
            //This was recommended
            doc.getDocumentElement().normalize();
            return doc;
        }
        catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void SaveToFile(Document doc) {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(file);
            transformer.transform(source, result);
        }
        catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    public String getDefaultPath() {
        return defaultPath;
    }

    public void setDefaultPath(String newDefaultPath) {
        defaultPath = newDefaultPath;
    }

    public static void main(String[] args) {

        try {
            File xmlFile = new File(System.getenv("APPDATA") + "/Dungeonomicon/StatusEffects.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);

            doc.getDocumentElement().normalize();

            System.out.println("Root Element: " + doc.getDocumentElement().getNodeName());
        } catch ( Exception e) {
            e.printStackTrace();
        }
    }
}
