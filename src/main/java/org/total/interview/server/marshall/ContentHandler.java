package org.total.interview.server.marshall;


public interface ContentHandler {

    public <T> T unMarshal(String content, Class<T> clazz);

    public <T> String marshal(T object);

}
