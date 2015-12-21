package org.total.interview.server.marshall;

import org.apache.log4j.Logger;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

public class GenericContentHandler implements ContentHandler {

    private static final Logger LOGGER = Logger.getLogger(GenericContentHandler.class);

    public <T> T unMarshal(String content, Class<T> clasz) {
        try {
            JAXBContext context = JAXBContext.newInstance( clasz );
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return unmarshaller.unmarshal(new StreamSource( new StringReader( content )), clasz).getValue();
        } catch (JAXBException e) {
            LOGGER.error(String.format("Exception while unmarshalling: %s", e.getMessage()));
        }
        return null;
    }

    public <T> String marshal(T object) {
        try {
            StringWriter stringWriter = new StringWriter();
            JAXBContext context = JAXBContext.newInstance( object.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.marshal(object, stringWriter);
            return stringWriter.toString();
        } catch (JAXBException e) {
            LOGGER.error(String.format("Exception while marshalling: %s", e.getMessage()));
        }
        return null;
    }
}
