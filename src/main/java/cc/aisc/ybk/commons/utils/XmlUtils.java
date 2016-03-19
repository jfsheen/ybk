package cc.aisc.ybk.commons.utils;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by sjf on 15-11-14.
 */
public abstract class XmlUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(XmlUtils.class);

    public static String formatXML(String inputXML) {

        LOGGER.debug("format XML. ");

        String requestXML = null;
        Document document = null;

        try {
            SAXReader reader = new SAXReader();
            document = reader.read(new StringReader(inputXML));
        } catch (DocumentException e1) {
            e1.printStackTrace();
        }

        if (document != null) {
            XMLWriter writer = null;
            try {
                StringWriter stringWriter = new StringWriter();
                OutputFormat format = new OutputFormat("    ", true);
                writer = new XMLWriter(stringWriter, format);
                writer.write(document);
                writer.flush();
                requestXML = stringWriter.getBuffer().toString();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (writer != null) {
                    try {
                        writer.close();
                    } catch (IOException e) {
                    }
                }
            }
        }

        return requestXML;
    }

}
