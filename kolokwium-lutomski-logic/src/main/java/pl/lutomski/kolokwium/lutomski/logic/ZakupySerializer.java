/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.lutomski.kolokwium.lutomski.logic;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;
import pl.lutomski.kolokwium.lutomski.data.ZakupionyProdukt;

/**
 *
 * @author User
 */
public class ZakupySerializer {
     public void zapiszJSON(List<ZakupionyProdukt> lista, String sciezka) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File(sciezka), lista);
    }

    public List<ZakupionyProdukt> odczytajJSON(String sciezka) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return List.of(mapper.readValue(new File(sciezka), ZakupionyProdukt[].class));
    }

    public void zapiszXML(List<ZakupionyProdukt> lista, String sciezka) throws IOException {
        XmlMapper mapper = new XmlMapper();
        ZakupyWrapperXML wrapper = new ZakupyWrapperXML(lista);
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File(sciezka), wrapper);
    }

    public List<ZakupionyProdukt> odczytajXML(String sciezka) throws IOException {
        XmlMapper mapper = new XmlMapper();
        ZakupyWrapperXML wrapper = mapper.readValue(new File(sciezka), ZakupyWrapperXML.class);
        return wrapper.getLista();
    }
}
