package Punto2b;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class Objeto {
    double peso;
    double valor;

    public Objeto(double peso, double valor) {
        this.peso = peso;
        this.valor = valor;
    }

    public double getValorPorPeso() {
        return valor / peso;
    }
}

class Container {
    private double pesoMaximo;
    private List<Objeto> objetos;

    public Container(double pesoMaximo) {
        this.pesoMaximo = pesoMaximo;
        this.objetos = new ArrayList<>();
    }

    public void agregarObjeto(double peso, double valor) {
        objetos.add(new Objeto(peso, valor));
    }

    public List<Objeto> maximizarValor() {
        return seleccionarObjetos(Comparator.comparingDouble(o -> -o.valor));
    }

    public List<Objeto> minimizarPeso() {
        return seleccionarObjetos(Comparator.comparingDouble(o -> o.peso));
    }

    public List<Objeto> optimizarValorPorPeso() {
        return seleccionarObjetos(Comparator.comparingDouble(Objeto::getValorPorPeso).reversed());
    }

    private List<Objeto> seleccionarObjetos(Comparator<Objeto> comparador) {
        objetos.sort(comparador);
        List<Objeto> seleccionados = new ArrayList<>();
        double pesoActual = 0;
        double valorTotal = 0;

        for (Objeto obj : objetos) {
            if (pesoActual + obj.peso <= pesoMaximo) {
                seleccionados.add(obj);
                pesoActual += obj.peso;
                valorTotal += obj.valor;
            } else {
                double fraccion = (pesoMaximo - pesoActual) / obj.peso;
                seleccionados.add(new Objeto(obj.peso * fraccion, obj.valor * fraccion));
                valorTotal += obj.valor * fraccion;
                break;
            }
        }

        System.out.println("Valor total de la carga: " + valorTotal);
        return seleccionados;
    }
}

public class MochilaFraccionaria {
    public static void main(String[] args) {
        Container container = new Container(50);  // Peso máximo del container
        
        container.agregarObjeto(10, 60);  // Agregar objetos (peso, valor)
        container.agregarObjeto(20, 100);
        container.agregarObjeto(30, 120);

        System.out.println("Maximización de valor:");
        container.maximizarValor().forEach(obj -> 
            System.out.println("Peso: " + obj.peso + ", Valor: " + obj.valor)
        );

        System.out.println("\nMinimización de peso:");
        container.minimizarPeso().forEach(obj -> 
            System.out.println("Peso: " + obj.peso + ", Valor: " + obj.valor)
        );

        System.out.println("\nOptimización por valor por unidad de peso:");
        container.optimizarValorPorPeso().forEach(obj -> 
            System.out.println("Peso: " + obj.peso + ", Valor: " + obj.valor)
        );
    }
}

