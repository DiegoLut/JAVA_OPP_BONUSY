/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.lutomski.kolokwium.lutomski.logic;

import java.util.List;
import java.util.Optional;
import pl.lutomski.kolokwium.lutomski.data.ZakupionyProdukt;

/**
 *
 * @author User
 */
public interface ZakupyInterface {
    void dodajProdukt(ZakupionyProdukt produkt) throws IncorrectValueException;
    List<ZakupionyProdukt> getListaZakupow();
    Optional<ZakupionyProdukt> getProdukt(int index);
    Optional<ZakupionyProdukt> getProdukt(String nazwa);
    int getLiczbaProduktow();
    int getCalkowitaWaga();
    float getSumaWartosci();
}
