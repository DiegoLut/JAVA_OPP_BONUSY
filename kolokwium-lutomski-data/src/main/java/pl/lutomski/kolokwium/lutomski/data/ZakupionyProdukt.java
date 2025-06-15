/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.lutomski.kolokwium.lutomski.data;

import pl.lutomski.kolokwium.lutomski.logic.IncorrectValueException;

/**
 *
 * @author User
 */
public class ZakupionyProdukt extends Produkt{
    private int liczbaSztuk;

    public ZakupionyProdukt(String nazwaProduktu, int waga, float cenaZaSztuke, int liczbaSztuk) throws IncorrectValueException {
        super(validateNazwa(nazwaProduktu), validateWaga(waga), validateCena(cenaZaSztuke));
        this.liczbaSztuk = validateLiczba(liczbaSztuk);
    }

    private static String validateNazwa(String nazwa) throws IncorrectValueException {
        if (nazwa == null || nazwa.isBlank()) {
            throw new IncorrectValueException("Nazwa nie może być pusta.");
        }
        return nazwa;
    }

    private static int validateWaga(int waga) throws IncorrectValueException {
        if (waga <= 0) {
            throw new IncorrectValueException("Waga musi być większa od zera.");
        }
        return waga;
    }

    private static float validateCena(float cena) throws IncorrectValueException {
        if (cena <= 0) {
            throw new IncorrectValueException("Cena musi być większa od zera.");
        }
        return cena;
    }

    private static int validateLiczba(int liczba) throws IncorrectValueException {
        if (liczba <= 0) {
            throw new IncorrectValueException("Liczba sztuk musi być większa od zera.");
        }
        return liczba;
    }

    public int getLiczbaSztuk() {
        return liczbaSztuk;
    }

    public float getWartoscZakupu() {
        return getCenaZaSztuke() * liczbaSztuk;
    }

    public int getWagaCalkowita() {
        return getWaga() * liczbaSztuk;
    }

    @Override
    public String toString() {
        return getNazwaProduktu() + " | sztuk: " + liczbaSztuk + " | cena: " + getCenaZaSztuke() + " | waga: " + getWaga();
    }
}
