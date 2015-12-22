package org.total.interview.server.marshall;


import java.util.List;

public interface ContentHandler {

    public <T> T unMarshal(Class<T> clasz, String content);

    public <T> String marshal(T object);

    public <T> List<T> unmarshal(Class<T> clasz, String content);

    public <T> String marshal(List<T> list, String name);

}
