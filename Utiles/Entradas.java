package TP2.Utiles;

import java.util.Scanner;

public class Entradas {
    static Scanner scanner = new Scanner(System.in);

    //validaciones de tipos de datos
    public static int validInt() {
        int entero = 0;
        String entrada;
        boolean valido = false;

        do{
            try {
                entrada = scanner.nextLine();
                entero = Integer.parseInt(entrada);
                valido = true;
            } catch (Exception e) {
                System.out.println("Error: tipo de dato invalido");
                valido = false;
            }
        } while (!valido);
        return entero;
    }

    public static String entradaString() {
        String cadena = "";
        do{
            cadena = scanner.nextLine();
            if (cadena.length() == 0) {
                System.out.println("Error: cadena invalida");
            }
        }while (cadena.length() == 0);
        return cadena;
    }

    //validaciones especificas
    public static String validDni() {
        String dni;
        int numero;
        boolean valido = false;

        do{
            dni = scanner.nextLine();
            if (dni.length() == 8) {
                numero = Integer.parseInt(dni);
                if (numero > 1000000 && numero < 99999999) {
                    valido=true;
                }
            }
            if (!valido) {
                System.out.println("Error: DNI invalido");
            }
        }while (!valido);
        return dni;
    }   

}

