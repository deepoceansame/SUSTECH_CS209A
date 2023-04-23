package org.example;


import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

public class Main {
    public static void main(String[] args) {

        BeanFactory container = new ClassPathXmlApplicationContext("bf.xml");
        House house = (House)container.getBean("House");
        System.out.println(house.getDoor().getDoorId());
    }
}