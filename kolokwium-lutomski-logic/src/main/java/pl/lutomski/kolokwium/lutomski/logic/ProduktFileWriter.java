/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.lutomski.kolokwium.lutomski.logic;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import pl.lutomski.kolokwium.lutomski.data.ZakupionyProdukt;

/**
 *
 * @author User
 */
public class ProduktFileWriter implements FileWriterInterface<ZakupionyProdukt>{
   @Override
    public void zapiszDoPliku(List<ZakupionyProdukt> lista, String sciezka) {
        new Thread(() -> {
            try (FileWriter writer = new FileWriter(sciezka)) {
                lista.stream()
                        .sorted(Comparator.comparing(ZakupionyProdukt::getWartoscZakupu).reversed())
                        .forEach(produkt -> {
                            try {
                                writer.write(produkt.toString() + "\n");
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        });
                System.out.println("Zakończono zapis do pliku.");
            } catch (IOException e) {
                System.err.println("Błąd zapisu do pliku: " + e.getMessage());
            }
        }).start();
    } 
}
