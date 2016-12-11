package com.projects.scheduler.conferencescheduler.fileReader;

import com.projects.scheduler.conferencescheduler.event.Event;
import com.projects.scheduler.conferencescheduler.exception.ConferenceManagementBusinessException;
import com.projects.scheduler.conferencescheduler.util.ConferenceLogger;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import com.projects.scheduler.conferencescheduler.config.ConfigConstant;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SG0952876 on 12/3/2016. All test
 */
public class XmlFileReader implements FileReader{
    private static final Logger logger = ConferenceLogger.getLogger(XmlFileReader.class);
    private String path;

    public XmlFileReader(String path) {
        this.path = path;
    }

    @Override
    public List<Event>  read() {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = null;
        Document doc = null;
        List<Event> events = null;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        try {
            doc = dBuilder.parse(path);

        } catch (SAXException e) {
            throw new ConferenceManagementBusinessException(e);
        } catch (IOException e) {
            throw new ConferenceManagementBusinessException(e);
        }
        try {
            return createEvents(doc);
        } catch (IOException e) {
            throw new ConferenceManagementBusinessException(e);
        }
    }

    /**
     * createEvents creates event by reading xml file
     *
     * @param document
     * @return
     * @throws IOException
     */
    private List<Event> createEvents(Document document) throws IOException {
        logger.debug( "beginning of createEvents of "+ XmlFileReader.class);
        document.getDocumentElement().normalize();

        NodeList nList = document.getElementsByTagName(ConfigConstant.TOPIC.getName());
        List<Event> events = new ArrayList<>();

        for (int temp = 0; temp < nList.getLength(); temp++) {

            Node nNode = nList.item(temp);

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                Element eElement = (Element) nNode;

                String eventName = eElement.getElementsByTagName(ConfigConstant.NAME.getName()).item(0).getTextContent();
                String durationInString = eElement.getElementsByTagName(ConfigConstant.TIME.getName()).item(0).getTextContent();

                if (durationInString != null && ! durationInString.equals(ConfigConstant.EMPTY_STRING.getName())) {
                    int duration = Integer.parseInt(durationInString);

                    Event event = new Event.Builder().eventName(eventName).eventDuration(Duration.ofMinutes(duration)).build();
                    events.add(event);
                }
            }
        }
        logger.debug( "End of createEvents of "+ XmlFileReader.class);
        return events;
    }
}
