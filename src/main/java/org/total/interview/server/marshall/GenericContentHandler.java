package org.total.interview.server.marshall;

import org.apache.log4j.Logger;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;

import javax.xml.bind.*;
import javax.xml.transform.stream.StreamSource;
import javax.xml.namespace.QName;

public class GenericContentHandler implements ContentHandler {

    private static final Logger LOGGER = Logger.getLogger(GenericContentHandler.class);

    public <T> T unMarshal(Class<T> clasz, String content) {
        try {
            JAXBContext context = JAXBContext.newInstance(clasz);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return unmarshaller.unmarshal(new StreamSource(new StringReader(content)), clasz).getValue();
        } catch (JAXBException e) {
            LOGGER.error(e, e);
        }
        return null;
    }

    public <T> String marshal(T object) {
        try {
            StringWriter stringWriter = new StringWriter();
            JAXBContext context = JAXBContext.newInstance(object.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.marshal(object, stringWriter);
            return stringWriter.toString();
        } catch (JAXBException e) {
            LOGGER.error(e, e);
        }
        return null;
    }

    public <T> List<T> unmarshal(Class<T> clasz, String content) {
        try {
            JAXBContext context = JAXBContext.newInstance(Wrapper.class, clasz);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Wrapper<T> wrapper = (Wrapper<T>) unmarshaller.unmarshal(new StreamSource(new StringReader(content)), Wrapper.class).getValue();
            return wrapper.getItems();
        } catch (JAXBException e) {
            LOGGER.error(e, e);
        }
        return null;
    }

    public <T> String marshal(List<T> list, String name) {
        try {
            QName qName = new QName(null, name);

            StringWriter stringWriter = new StringWriter();

            Wrapper wrapper = new Wrapper(list);

            JAXBContext context = JAXBContext.newInstance(wrapper.getClass(), list.get(0).getClass());
            JAXBElement<Wrapper> jaxbElement = new JAXBElement<Wrapper>(qName, Wrapper.class, wrapper);

            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(jaxbElement, stringWriter);

            return stringWriter.toString();
        } catch (JAXBException e) {
            LOGGER.error(e,e);
        }
        return null;
    }

}
