package TP2;

import java.util.List;
import java.util.ArrayList;

import TP2.Utiles.*;

public class App {
    static List<Cliente> clientes=new ArrayList<Cliente>();
    static List<CuentaBancaria> cuentasBancarias=new ArrayList<CuentaBancaria>();

    public static void main(String[] args) {
        int menu;
        do {
            clientes=Banco.getClientes();
            cuentasBancarias=Banco.getCuentasBancarias();

            System.out.println("Menu Principal:");
            System.out.println("1-Clientes");
            System.out.println("2-Cuentas Bancarias");
            System.out.println("0-Salir");
            menu = Entradas.validInt();
            switch (menu) {
                case 1:
                    menuClientes();
                    break;
                case 2:
                    String mensaje="\nElija el cliente del que desea administrar las cuentas bancarias usando el DNI:";
                    int posicionCliente = Auxiliares.elegirCliente(clientes,mensaje);
                    if (posicionCliente!=-1) {
                        menuCuentasBancarias(posicionCliente);
                    }
                    break;
                case 0:
                    System.out.println("Salir");
                    break;
                default:
                    System.out.println("Error: opcion no valida");
            }
            System.out.println();
        } while (menu!= 0);
    }

    static void menuClientes() {
        int menu;

        do{
            clientes=Banco.getClientes();
            cuentasBancarias=Banco.getCuentasBancarias();

            System.out.println("\nMenu Clientes:");
            System.out.println("1-Crear Cliente");
            System.out.println("2-Mostrar Clientes");
            System.out.println("3-Modificar Cliente");
            System.out.println("4-Eliminar Cliente");
            System.out.println("0-Volver al Menu Principal");
            menu = Entradas.validInt();
            switch (menu){
                case 1:
                    MetodosClientes.crearCliente();
                    break;
                case 2:
                    MetodosClientes.mostrarClientes();
                    break;
                case 3:
                    String mensaje="\nElija el cliente a modificar usando el DNI:";
                    int posicion=Auxiliares.elegirCliente(clientes, mensaje);
                    if (posicion!=-1) {
                        MetodosClientes.modificarCliente(posicion);
                    }
                    break;
                case 4:
                    MetodosClientes.eliminarCliente();
                    break;
                case 0:
                    System.out.println("Volver al Menu Principal");
                    break;
                default:
                    System.out.println("Error: opcion no valida");
            }
        }while (menu!=0);
    }

    static void menuCuentasBancarias(int posicionCliente) {
        int menu;

        do{
            clientes=Banco.getClientes();
            cuentasBancarias=Banco.getCuentasBancarias();

            System.out.println("\nMenu Cuentas Bancarias:");
            System.out.println("1-Crear Cuenta Bancaria");
            System.out.println("2-Mostrar Cuentas Bancarias");
            System.out.println("3-Administrar Cuenta Bancaria");
            System.out.println("4-Eliminar Cuenta Bancaria");
            System.out.println("0-Volver al Menu Principal");
            menu = Entradas.validInt();
            switch (menu){
                case 1:
                    MetodosCuentasBancarias.crearCuentaBancaria(posicionCliente);
                    break;
                case 2:
                    MetodosCuentasBancarias.mostrarCuentasBancarias(posicionCliente);
                    break;
                case 3:
                    String mensaje="\nElija la cuenta bancaria a utilizar usando el ID:";
                    int posicion=Auxiliares.elegirCuentaBancaria(clientes, mensaje, posicionCliente);
                    int posicionCuentasBancarias=-1;
                    if (posicion!=-1) {
                        for (int i=0;i<cuentasBancarias.size();i++) {
                            if (clientes.get(posicionCliente).getCuentasBancarias().get(posicion).getId()==cuentasBancarias.get(i).getId()) {
                                posicionCuentasBancarias=i;
                                break;
                            }
                        }
                        if (posicionCuentasBancarias!=-1) {
                            MetodosCuentasBancarias.menuAdministrarCuentaBancaria(posicionCuentasBancarias);
                        }
                    }
                    break;
                case 4:
                    MetodosCuentasBancarias.eliminarCuentaBancaria(posicionCliente);
                    break;
                case 0:
                    System.out.println("Volver al Menu Principal");
                    break;
                default:
                    System.out.println("Error: opcion no valida");
                    break;
            }
        }while (menu!=0);
    }
}