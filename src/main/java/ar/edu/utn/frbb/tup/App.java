package ar.edu.utn.frbb.tup;

import java.util.List;
import java.util.ArrayList;

import ar.edu.utn.frbb.tup.Utiles.*;

public class App {
    static List<Cliente> clientes=new ArrayList<Cliente>();
    static List<CuentaBancaria> cuentasBancarias=new ArrayList<CuentaBancaria>();

    public static void main(String[] args) {
        int menu;

        Consola.limpiarPantalla();
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
                    int posicionCliente = AdministracionClientes.elegirCliente(clientes,mensaje);
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

            System.out.println("Menu Clientes:");
            System.out.println("1-Crear Cliente");
            System.out.println("2-Mostrar Clientes");
            System.out.println("3-Modificar Cliente");
            System.out.println("4-Eliminar Cliente");
            System.out.println("0-Volver al Menu Principal");
            menu = Entradas.validInt();
            switch (menu){
                case 1:
                    AdministracionClientes.crearCliente();
                    break;
                case 2:
                    AdministracionClientes.mostrarClientes();
                    break;
                case 3:
                    String mensaje="\nElija el cliente a modificar usando el DNI:";
                    int posicion=AdministracionClientes.elegirCliente(clientes, mensaje);
                    if (posicion!=-1) {
                        AdministracionClientes.modificarCliente(posicion);
                    }
                    break;
                case 4:
                    AdministracionClientes.eliminarCliente();
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

            System.out.println("Menu Cuentas Bancarias:");
            System.out.println("1-Crear Cuenta Bancaria");
            System.out.println("2-Mostrar Cuentas Bancarias");
            System.out.println("3-Administrar Cuenta Bancaria");
            System.out.println("4-Eliminar Cuenta Bancaria");
            System.out.println("0-Volver al Menu Principal");
            menu = Entradas.validInt();
            switch (menu){
                case 1:
                    AdministracionCuentasBancarias.crearCuentaBancaria(posicionCliente);
                    break;
                case 2:
                    AdministracionCuentasBancarias.mostrarCuentasBancarias(posicionCliente);
                    break;
                case 3:
                    String mensaje="\nElija la cuenta bancaria a utilizar usando el ID:";
                    int posicionCuentaCliente=AdministracionCuentasBancarias.elegirCuentaBancaria(clientes, mensaje, posicionCliente);
                    int posicionCuentaBancaria=-1;
                    if (posicionCuentaCliente!=-1) {
                        for (int i=0;i<cuentasBancarias.size();i++) {
                            if (clientes.get(posicionCliente).getCuentasBancarias().get(posicionCuentaCliente).getId()==cuentasBancarias.get(i).getId()) {
                                posicionCuentaBancaria=i;
                                break;
                            }
                        }
                        if (posicionCuentaBancaria!=-1) {
                            AdministracionCuentasBancarias.menuAdministrarCuentaBancaria(posicionCuentaBancaria);
                        }
                    }
                    break;
                case 4:
                    AdministracionCuentasBancarias.eliminarCuentaBancaria(posicionCliente);
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