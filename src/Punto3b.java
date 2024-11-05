package com.proyectos;

public class Punto3b {
    public static void main(String[] args) {
        //coeficientes diapositiva 23

        int n = 4;

        int[][] coeficientes = calcularCoeficientes(n);
        imprimirCoeficientes(coeficientes);

    }

    private static void imprimirCoeficientes(int[][] coeficientes) {
        for (int[] coeficiente : coeficientes) {
            for (int i : coeficiente) {
                System.out.println(i + " ");
            }
            System.out.println();
        }
    }

    private static int[][] calcularCoeficientes(int n) {
        int[][] coeficientes = new int[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            coeficientes[i][0] = 4;
            coeficientes[i][i] = 4;
        }
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                coeficientes[i][j] = coeficientes[i][j - 1] + coeficientes[i - 1][j];
            }
        }
        return coeficientes;
    }
}
