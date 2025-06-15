/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.lutomski.kolokwium.lutomski.logic;

import java.util.List;
import pl.lutomski.kolokwium.lutomski.data.Produkt;

/**
 *
 * @author User
 * @param <T>
 */
public interface FileWriterInterface<T extends Produkt> {
    void zapiszDoPliku(List<T> lista, String sciezka);
}
