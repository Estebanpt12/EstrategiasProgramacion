package Punto2c;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class Conexion {
    int municipio1, municipio2, costo;

    public Conexion(int municipio1, int municipio2, int costo) {
        this.municipio1 = municipio1;
        this.municipio2 = municipio2;
        this.costo = costo;
    }
}

class UnionFind {
    private int[] parent;
    private int[] rank;

    public UnionFind(int n) {
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }

    public int find(int node) {
        if (parent[node] != node) {
            parent[node] = find(parent[node]); // Path compression
        }
        return parent[node];
    }

    public boolean union(int node1, int node2) {
        int root1 = find(node1);
        int root2 = find(node2);

        if (root1 == root2) return false;

        // Union by rank
        if (rank[root1] > rank[root2]) {
            parent[root2] = root1;
        } else if (rank[root1] < rank[root2]) {
            parent[root1] = root2;
        } else {
            parent[root2] = root1;
            rank[root1]++;
        }
        return true;
    }
}

class RedInternet {
    private int numMunicipios;
    private List<Conexion> conexiones;

    public RedInternet(int numMunicipios) {
        this.numMunicipios = numMunicipios;
        this.conexiones = new ArrayList<>();
    }

    public void agregarConexion(int municipio1, int municipio2, int costo) {
        conexiones.add(new Conexion(municipio1, municipio2, costo));
    }

    public List<Conexion> encontrarMST() {
        List<Conexion> mst = new ArrayList<>();
        UnionFind uf = new UnionFind(numMunicipios);

        // Ordenar conexiones por costo ascendente
        conexiones.sort(Comparator.comparingInt(c -> c.costo));

        for (Conexion conexion : conexiones) {
            // Intentar unir los municipios. Si no están en el mismo grupo, los unimos.
            if (uf.union(conexion.municipio1, conexion.municipio2)) {
                mst.add(conexion);
            }
            // Si el MST tiene numMunicipios - 1 aristas, hemos conectado todos los municipios.
            if (mst.size() == numMunicipios - 1) break;
        }

        return mst;
    }

    public int calcularCostoTotal(List<Conexion> mst) {
        return mst.stream().mapToInt(c -> c.costo).sum();
    }
}

public class Red {
    public static void main(String[] args) {
        int numMunicipios = 5;
        RedInternet redInternet = new RedInternet(numMunicipios);

        // Agregar conexiones posibles con su costo
        redInternet.agregarConexion(0, 1, 10);
        redInternet.agregarConexion(0, 2, 15);
        redInternet.agregarConexion(1, 2, 5);
        redInternet.agregarConexion(1, 3, 20);
        redInternet.agregarConexion(2, 3, 30);
        redInternet.agregarConexion(3, 4, 8);

        List<Conexion> mst = redInternet.encontrarMST();
        int costoTotal = redInternet.calcularCostoTotal(mst);

        System.out.println("Conexiones seleccionadas para el MST:");
        for (Conexion conexion : mst) {
            System.out.println("Municipio " + conexion.municipio1 + " - Municipio " + conexion.municipio2 + " (Costo: " + conexion.costo + ")");
        }
        System.out.println("Costo total de la instalación de la red: " + costoTotal);
    }
}
