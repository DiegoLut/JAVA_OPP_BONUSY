/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.lutomski.kolokwium.lutomski.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import pl.lutomski.kolokwium.lutomski.data.ZakupionyProdukt;

/**
 *
 * @author User
 */
public class ZakupyManager implements ZakupyInterface{
    private List<ZakupionyProdukt> zakupy = new ArrayList<>();
    private static final int MAKS_WAGA = 20;

    @Override
    public void dodajProdukt(ZakupionyProdukt produkt) throws IncorrectValueException {
        if (produkt.getNazwaProduktu().isBlank())
            throw new IncorrectValueException("Nazwa nie może być pusta.");
        if (produkt.getWaga() < 1 || produkt.getWaga() > 20)
            throw new IncorrectValueException("Waga musi być między 1 a 20kg.");
        if (produkt.getCenaZaSztuke() < 0.01 || produkt.getCenaZaSztuke() > 1000)
            throw new IncorrectValueException("Cena musi być między 0.01 a 1000 zł.");
        if (produkt.getLiczbaSztuk() < 1 || produkt.getLiczbaSztuk() > 100)
            throw new IncorrectValueException("Liczba sztuk musi być między 1 a 100.");

        int nowaWaga = getCalkowitaWaga() + produkt.getWagaCalkowita();
        if (nowaWaga > MAKS_WAGA)
            throw new IncorrectValueException("Przekroczono maksymalną wagę 20kg.");

        zakupy.add(produkt);
    }

    @Override
    public List<ZakupionyProdukt> getListaZakupow() {
        return zakupy;
    }

    @Override
    public Optional<ZakupionyProdukt> getProdukt(int index) {
        if (index < 0 || index >= zakupy.size())
            return Optional.empty();
        return Optional.of(zakupy.get(index));
    }

    @Override
    public Optional<ZakupionyProdukt> getProdukt(String nazwa) {
        return zakupy.stream()
                .filter(p -> p.getNazwaProduktu().equalsIgnoreCase(nazwa))
                .findFirst();
    }

    @Override
    public int getLiczbaProduktow() {
        return zakupy.size();
    }

    @Override
    public int getCalkowitaWaga() {
        return zakupy.stream().mapToInt(ZakupionyProdukt::getWagaCalkowita).sum();
    }

    @Override
    public float getSumaWartosci() {
        return (float) zakupy.stream().mapToDouble(ZakupionyProdukt::getWartoscZakupu).sum();
    }
}
