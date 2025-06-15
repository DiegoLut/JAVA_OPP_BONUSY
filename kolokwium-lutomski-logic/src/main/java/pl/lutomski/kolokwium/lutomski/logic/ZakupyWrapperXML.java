/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.lutomski.kolokwium.lutomski.logic;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import java.util.List;
import pl.lutomski.kolokwium.lutomski.data.ZakupionyProdukt;

@JacksonXmlRootElement(localName = "Zakupy")
public class ZakupyWrapperXML {

    @JsonProperty("produkt")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<ZakupionyProdukt> lista;

    // Konstruktor bezargumentowy – wymagany przez Jackson
    public ZakupyWrapperXML() {}

    // Poprawny konstruktor z listą produktów
    public ZakupyWrapperXML(List<ZakupionyProdukt> lista) {
        this.lista = lista;
    }

    public List<ZakupionyProdukt> getLista() {
        return lista;
    }

    public void setLista(List<ZakupionyProdukt> lista) {
        this.lista = lista;
    }
}