/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.lutomski.kolokwium.lutomski.logic;

/**
 *
 * @author User
 */
public class QuadraticEquationSolver {
    public static void solveEquation(double a, double b, double c) {
        if (a == 0) {
            System.out.println("To równanie liniowe. x = " + (-c / b));
            return;
        }

        double delta = b * b - 4 * a * c;

        if (delta > 0) {
            double x1 = (-b - Math.sqrt(delta)) / (2 * a);
            double x2 = (-b + Math.sqrt(delta)) / (2 * a);
            System.out.println("Dwa pierwiastki: x1 = " + x1 + ", x2 = " + x2);
        } else if (delta == 0) {
            double x0 = -b / (2 * a);
            System.out.println("Jeden pierwiastek: x0 = " + x0);
        } else {
            System.out.println("Brak pierwiastków rzeczywistych.");
        }
    }
}
