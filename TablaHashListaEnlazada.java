package tablahashlistaenlazada;

import java.util.LinkedList;
import java.util.Scanner;

public class TablaHashListaEnlazada {

 private static final int CAPACIDAD_POR_DEFECTO = 10;

    private LinkedList<Entrada>[] tabla;
    private int capacidad;

    public TablaHashListaEnlazada() {
        this(CAPACIDAD_POR_DEFECTO);
    }

    public TablaHashListaEnlazada(int capacidad) {
        this.capacidad = capacidad;
        tabla = new LinkedList[capacidad];
        for (int i = 0; i < capacidad; i++) {
            tabla[i] = new LinkedList<>();
        }
    }

    private int hash(Object clave) {
        return clave.hashCode() % capacidad;
    }

    public void put(Object clave, Object valor) {
        int indice = hash(clave);
        LinkedList<Entrada> lista = tabla[indice];
        for (Entrada entrada : lista) {
            if (entrada.clave.equals(clave)) {
                entrada.valor = valor;
                return;
            }
        }
        lista.add(new Entrada(clave, valor));
    }

    public Object get(Object clave) {
        int indice = hash(clave);
        LinkedList<Entrada> lista = tabla[indice];
        for (Entrada entrada : lista) {
            if (entrada.clave.equals(clave)) {
                return entrada.valor;
            }
        }
        return null;
    }

    private static class Entrada {
        Object clave;
        Object valor;

        Entrada(Object clave, Object valor) {
            this.clave = clave;
            this.valor = valor;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TablaHashListaEnlazada tablaHash = new TablaHashListaEnlazada();

        System.out.println("Ingrese los pares clave-valor (ingrese 'fin' para terminar):");
        while (true) {
            System.out.print("Clave: ");
            String clave = scanner.nextLine();
            if (clave.equals("fin")) break;
            System.out.print("Valor: ");
            Object valor = scanner.nextLine();
            tablaHash.put(clave, valor);
        }

        System.out.println("\nTabla Hash:");
        for (int i = 0; i < tablaHash.tabla.length; i++) {
            LinkedList<Entrada> lista = tablaHash.tabla[i];
            System.out.print("[" + i + "]: ");
            for (Entrada entrada : lista) {
                System.out.print("(" + entrada.clave + " -> " + entrada.valor + ") ");
            }
            System.out.println();
        }

        System.out.println("\nIngrese la clave para buscar su valor (ingrese 'fin' para terminar):");
        while (true) {
            System.out.print("Clave: ");
            String clave = scanner.nextLine();
            if (clave.equals("fin")) break;
            Object valor = tablaHash.get(clave);
            System.out.println("Valor para la clave '" + clave + "': " + valor);
        }

        scanner.close();
    }
}
