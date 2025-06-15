/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package pl.lutomski.kolokwium.lutomski.logic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import pl.lutomski.kolokwium.lutomski.data.ZakupionyProdukt;

/**
 *
 * @author User
 */
public class KolokwiumLutomskiLogic {

    public static void main(String[] args) {
        ZakupyManager manager = new ZakupyManager();
        ProduktFileWriter writer = new ProduktFileWriter();
        ZakupySerializer serializer = new ZakupySerializer();
        Scanner sc = new Scanner(System.in);
        boolean dziala = true;

        while (dziala) {
            System.out.println("\n1. Dodaj produkt\n2. Pokaż listę\n3. Szczegóły produktu\n4. Raport\n5. Zapisz\n6. Wyjście\n7. Zapisz JSON\n8. Wczytaj JSON\n9. Zapisz XML\n10. Wczytaj XML");
            switch (sc.nextLine()) {
                case "1" -> {
                    try {
                        System.out.print("Nazwa: ");
                        String nazwa = sc.nextLine();
                        System.out.print("Waga (kg): ");
                        int waga = Integer.parseInt(sc.nextLine());
                        System.out.print("Cena za sztukę: ");
                        float cena = Float.parseFloat(sc.nextLine());
                        System.out.print("Liczba sztuk: ");
                        int liczba = Integer.parseInt(sc.nextLine());

                        ZakupionyProdukt produkt = new ZakupionyProdukt(nazwa, waga, cena, liczba);
                        manager.dodajProdukt(produkt);
                        System.out.println("Dodano produkt.");
                    } catch (IncorrectValueException e) {
                        System.out.println("Nie dodano: " + e.getMessage());
                    } catch (Exception e) {
                        System.out.println("Błąd danych wejściowych.");
                    }
                }
                case "2" -> manager.getListaZakupow().forEach(System.out::println);
                case "3" -> {
                    System.out.print("Nazwa produktu: ");
                    String nazwa = sc.nextLine();
                    manager.getProdukt(nazwa).ifPresentOrElse(
                            System.out::println,
                            () -> System.out.println("Nie znaleziono produktu.")
                    );
                }
                case "4" -> {
                    System.out.println("Liczba: " + manager.getLiczbaProduktow());
                    System.out.println("Waga: " + manager.getCalkowitaWaga());
                    System.out.println("Wartość: " + manager.getSumaWartosci());
                }
                case "5" -> {
                    System.out.print("Ścieżka do pliku: ");
                    String sciezka = sc.nextLine();
                    writer.zapiszDoPliku(manager.getListaZakupow(), sciezka);
                }
                case "6" -> {
                    dziala = false;
                    System.out.println("Zamknięcie programu.");
                }
                case "7" -> {
                    System.out.print("Ścieżka pliku JSON: ");
                    String path = sc.nextLine();
                    try {
                        serializer.zapiszJSON(manager.getListaZakupow(), path);
                        System.out.println("Zapisano do JSON.");
                    } catch (IOException e) {
                    System.out.println("Błąd zapisu: " + e.getMessage());
                    }
                }
                case "8" -> {
                    System.out.print("Ścieżka pliku JSON: ");
                    String path = sc.nextLine();
                    try {
                        List<ZakupionyProdukt> lista = serializer.odczytajJSON(path);
                        lista.forEach(produkt -> {
                            try {
                                manager.dodajProdukt(produkt);
                            } catch (IncorrectValueException ignored) {}
                        });
                        System.out.println("Wczytano z JSON.");
                    } catch (IOException e) {
                        System.out.println("Błąd odczytu: " + e.getMessage());
                    }
                }
                case "9" -> {
                    System.out.print("Ścieżka pliku XML: ");
                    String path = sc.nextLine();
                    try {
                        serializer.zapiszXML(manager.getListaZakupow(), path);
                        System.out.println("Zapisano do XML.");
                    } catch (IOException e) {
                        System.out.println("Błąd zapisu: " + e.getMessage());
                    }
                }
                case "10" -> {
                    System.out.print("Ścieżka pliku XML: ");
                    String path = sc.nextLine();
                    try {
                        List<ZakupionyProdukt> lista = serializer.odczytajXML(path);
                        lista.forEach(produkt -> {
                            try {
                                manager.dodajProdukt(produkt);
                            } catch (IncorrectValueException ignored) {}
                        });
                        System.out.println("Wczytano z XML.");
                    } catch (IOException e) {
                        System.out.println("Błąd odczytu: " + e.getMessage());
                    }
                }
                default -> System.out.println("Nieznana opcja.");
            }
        
        }
        
        //FILTROWANIE ZAKUPOW
        
        System.out.println("\nProdukty droższe niż 50 zł:");
        manager.getListaZakupow().stream()
            .filter(p -> p.getCenaZaSztuke() > 50)
            .forEach(System.out::println);
        
        
        double suma = manager.getListaZakupow().stream()
            .mapToDouble(p -> p.getCenaZaSztuke() * p.getLiczbaSztuk())
            .sum();
        System.out.println("\nSuma wartości zakupów: " + suma);
        
        
        Map<String, List<ZakupionyProdukt>> grupyCenowe = manager.getListaZakupow().stream()
            .collect(Collectors.groupingBy(p -> p.getCenaZaSztuke() > 50 ? "Drogie" : "Tanie"));

        System.out.println("\nProdukty pogrupowane wg ceny:");
        grupyCenowe.forEach((kategoria, lista) -> {
            System.out.println(kategoria + ":");
            lista.forEach(System.out::println);
            });


        System.out.println("\nUnikalne nazwy produktów (alfabetycznie):");
        manager.getListaZakupow().stream()
            .map(ZakupionyProdukt::getNazwaProduktu)
            .distinct()
            .sorted()
            .forEach(System.out::println);

        
        manager.getListaZakupow().stream()
            .max((p1, p2) -> Integer.compare(p1.getWaga(), p2.getWaga()))
            .ifPresent(p -> System.out.println("\nNajcięższy produkt: " + p));

        
        int liczbaSztuk = manager.getListaZakupow().stream()
            .mapToInt(ZakupionyProdukt::getLiczbaSztuk)
            .sum();
        System.out.println("\nŁączna liczba sztuk: " + liczbaSztuk);

        //FILTROWANIE PARZYSTYCH
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        numbers.stream().filter(n -> n % 2 == 0).forEach(System.out::println);
        
        //Zamiana liter + sortowanie
        List<String> words = List.of("adam", "maciek", "krzysztof");
        words.stream()
            .map(String::toUpperCase)
            .sorted() //.sorted(Comparator.reverseOrder())
            .forEach(System.out::println);
        
        //Grupowanie wg parzystości
        Map<String, List<Integer>> grouped = numbers.stream()
            .collect(Collectors.groupingBy(n -> (n % 2 == 0) ? "Parzyste" : "Nieparzyste"));
        System.out.println(grouped);
        
        
        //SET – unikalne imiona + przecięcia zbiorów
        Set<String> imiona = new TreeSet<>();
        imiona.add("Adam");
        imiona.add("Zofia");
        imiona.add("Adam"); // nie doda się ponownie
        System.out.println(imiona); // posortowane alfabetycznie

        Set<Integer> set1 = new HashSet<>(List.of(1, 2, 3, 4, 5));
        Set<Integer> set2 = new HashSet<>(List.of(4, 5, 6, 7));

        Set<Integer> intersection = new HashSet<>(set1);
        intersection.retainAll(set2);
        System.out.println("Przecięcie: " + intersection);
        
        //Używasz Set<String> (np. TreeSet, HashSet) 
        //– dodanie tego samego imienia drugi raz nic nie zmienia, bo Set ignoruje duplikaty.
        //TreeSet dodatkowo sortuje elementy alfabetycznie.
        
        
        //MAP – słownik i ranking graczy
        Map<String, String> slownik = new HashMap<>();
        slownik.put("car", "samochód");

        String slowo = "car";
        System.out.println(slownik.getOrDefault(slowo, "Brak tłumaczenia"));

           
        Map<String, Integer> ranking = new HashMap<>();
        ranking.put("Anna", 120);
        ranking.put("Tomek", 180);

        ranking.entrySet().stream()
            .sorted(Map.Entry.<String, Integer>comparingByValue().reversed()) //Sortuje wpisy mapy malejąco według wartości
            .forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue()));

        
        //Refleksja
        
        Class<?> clazz = ArrayList.class;
        System.out.println("Metody ArrayList:");
        Arrays.stream(clazz.getDeclaredMethods()).forEach(System.out::println);

        Class<?> stringClass = String.class;
        System.out.println("Konstruktory String:");
        Arrays.stream(stringClass.getConstructors()).forEach(System.out::println);

    }
}
