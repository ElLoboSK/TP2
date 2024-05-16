package ar.edu.utn.frbb.tup.Utiles;

import java.util.Scanner;

public class Entradas {
    static Scanner scanner = new Scanner(System.in);

    //validaciones de tipos de datos
    public static int validInt() {
        int numero = 0;
        String entrada;
        boolean valido = false;

        do{
            try {
                entrada = scanner.nextLine();
                numero = Integer.valueOf(entrada);
                valido = true;
            } catch (Exception e) {
                System.out.println("Error: tipo de dato invalido");
                valido = false;
            }
        } while (!valido);
        
        return numero;
    }

    public static double validDouble() {
        double numero = 0;
        String entrada;
        boolean valido = false;

        do{
            try {
                entrada = scanner.nextLine();
                numero = Double.valueOf(entrada);
                valido = true;
            } catch (Exception e) {
                System.out.println("Error: tipo de dato invalido");
                valido = false;
            }
        } while (!valido);
        
        double factor = Math.pow(10, 2);
        numero = Math.floor(numero*factor)/factor;

        return numero;
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
                numero = Integer.valueOf(dni);
                if (numero > 10000000 && numero < 99999999) {
                    valido=true;
                }
            }
            if (!valido) {
                System.out.println("Error: DNI invalido");
            }
        }while (!valido);
        return dni;
    }   

    public static String validCBU() {
        String cbu;
        int numero;
        boolean valido = false;

        do{
            cbu = scanner.nextLine();
            if (cbu.length() == 6) {
                numero = Integer.valueOf(cbu);
                if (numero > 100000 && numero < 999999) {
                    valido=true;
                }
            }
            if (!valido) {
                System.out.println("Error: CBU invalido");
            }
        }while (!valido);
        return cbu;
    }
}

