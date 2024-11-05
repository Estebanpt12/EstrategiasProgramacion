package com.proyectos;

import java.util.HashMap;

public class Punto3a {
    public static void main(String[] args) {
        int n = 5;
        HashMap<Integer,Integer> memo = new HashMap<>();
        int [] tab = new int[6];

        int resultado1 = lucasMemorizacion(n, memo);
        int resultado2 = lucasTabulacion(n,tab);

        System.out.println("resultado memo: "+resultado1);
        System.out.println("resultado tab: "+resultado2);
    }

    private static int lucasTabulacion(int n, int[] tab) {
        tab[0] = 2;
        tab[1] = 1;
        for (int i = 2; i <= n; i++) {
            tab[i] = tab[i-1] + tab[i-2];
        }
        return tab[n];
    }

    private static int lucasMemorizacion(int n, HashMap<Integer,Integer>memo) {
        if (n == 0) {
            return 2;
        }
        if (n == 1) {
            return 1;
        }
        if (memo.containsKey(n)) {
            return memo.get(n);
        }else {
            int res = lucasMemorizacion(n-1, memo) + lucasMemorizacion(n-2,memo);
            memo.put(n,res);
            return res;
        }
    }
}
