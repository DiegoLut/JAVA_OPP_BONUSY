/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.lutomski.kolokwium.lutomski.data;

/**
 *
 * @author User
 */
public abstract class Produkt {
    private String nazwaProduktu;
    private int waga; // kg
    private float cenaZaSztuke; // zł

    public Produkt(String nazwaProduktu, int waga, float cenaZaSztuke) {
        this.nazwaProduktu = nazwaProduktu;
        this.waga = waga;
        this.cenaZaSztuke = cenaZaSztuke;
    }

    public String getNazwaProduktu() {
        return nazwaProduktu;
    }

    public int getWaga() {
        return waga;
    }

    public float getCenaZaSztuke() {
        return cenaZaSztuke;
    }

    public float getWartosc() {
        return cenaZaSztuke;
    }

}
