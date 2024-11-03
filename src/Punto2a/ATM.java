package Punto2a;

import java.util.Comparator;
import java.util.HashMap;
import java.util.TreeMap;

public class ATM {
    
    private TreeMap<Integer, Integer> billetes;

    public static void main(String[] args) {
        var atm = new ATM();
        atm.sacarDinero(80000).forEach((key, value) ->{
            System.out.println("Billete: " + key + "\tcantidad: " + value);
        });
    }

    public ATM() {
        billetes = new TreeMap<>(Comparator.reverseOrder());
        billetes.put(10000, 300);
        billetes.put(20000, 200);
        billetes.put(50000, 100);
        billetes.put(100000, 500);
    }

    public HashMap<Integer,Integer> sacarDinero(int valor){
        int aux = valor;
        var resultado = new HashMap<Integer, Integer>();

        for (int billete : billetes.keySet()){
            while(aux >= billete){
                int cantidad = billetes.get(billete); 
                if(cantidad > 0){
                    billetes.put(billete, cantidad - 1);
                    if (resultado.containsKey(billete)){
                        resultado.put(billete, resultado.get(billete) + 1);
                    }else{
                        resultado.put(billete, 1);
                    }
                    aux -= billete;
                }else{
                    break;
                }
            }
        }

        if(aux > 0){
            System.out.println("No habian suficientes billetes para devolver");
        }

        return resultado;
    }


}
