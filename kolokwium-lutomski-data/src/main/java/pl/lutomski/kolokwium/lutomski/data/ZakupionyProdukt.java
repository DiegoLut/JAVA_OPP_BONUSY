/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.lutomski.kolokwium.lutomski.data;

/**
 *
 * @author User
 */
public class ZakupionyProdukt extends Produkt{
    private int liczbaSztuk;

    public ZakupionyProdukt(String nazwaProduktu, int waga, float cenaZaSztuke, int liczbaSztuk) {
        super(nazwaProduktu, waga, cenaZaSztuke);
        this.liczbaSztuk = liczbaSztuk;
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
