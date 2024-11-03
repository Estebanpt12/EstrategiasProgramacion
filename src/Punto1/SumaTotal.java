package Punto1;

import java.util.ArrayList;

public class SumaTotal{

    public static void main(String[] args) {
        var numeros = new ArrayList<Integer>();
        numeros.add(9);
        numeros.add(7);
        numeros.add(6);
        numeros.add(25);
        numeros.add(5);
        System.out.println("Resultado: " + sumaNumeros(numeros));
    }

    public static int sumaNumeros(ArrayList<Integer> numeros){
        return sumaNumeros(numeros, 0, numeros.size() - 1);
    }

    private static int sumaNumeros(ArrayList<Integer> numeros, int i, int j){
        if(i == j){
            return numeros.get(i);
        }else{
            int mitad = (i + j) / 2;
            int total = sumaNumeros(numeros, i, mitad);
            total += sumaNumeros(numeros, mitad + 1, j);
            return total;
        }
    }
}