package ar.edu.utn.frbb.tup.Servicio;

import ar.edu.utn.frbb.tup.Presentacion.Entrada.Entradas;

public class Validaciones {
    public static String validDni() {
        String dni;
        int numero;
        boolean valido = false;

        //validacion especial para el dni, solo permite ingresar numeros entre 10000000 y 99999999, no es del todo realista pero es una validacion rapida
        do{
            dni = Entradas.validString();
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

        //validacion especial para el CBU, solo permite ingresar numeros entre 100000 y 999999, tampoco es realista pero es para hacer mas facil de probar el programa y tenga validacion rapida
        do{
            cbu = Entradas.validString();
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
