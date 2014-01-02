package me.aaa;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class MyJaxbBean {
    public String type;
    public String value;

    public MyJaxbBean() {} // JAXB needs this

    public MyJaxbBean(String type, String value) {
        this.type = type;
        this.value = value;
    }
}
