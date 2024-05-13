package TP2;

import TP2.Utiles.*;

import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;

public class App {
    static List<Cliente> clientes=new ArrayList<Cliente>();
    static List<CuentaBancaria> cuentasBancarias=new ArrayList<CuentaBancaria>();
    static List<Movimientos> movimientos=new ArrayList<Movimientos>();

    public static void main(String[] args) {
        int menu;
        do {
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
            System.out.println("\nMenu Clientes:");
            System.out.println("1-Crear Cliente");
            System.out.println("2-Mostrar Clientes");
            System.out.println("3-Modificar Cliente");
            System.out.println("4-Eliminar Cliente");
            System.out.println("0-Volver al Menu Principal");
            menu = Entradas.validInt();
            switch (menu){
                case 1:
                    crearCliente();
                    break;
                case 2:
                    mostrarClientes();
                    break;
                case 3:
                    modificarCliente();
                    break;
                case 4:
                    eliminarCliente();
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
            System.out.println("\nMenu Cuentas Bancarias:");
            System.out.println("1-Crear Cuenta Bancaria");
            System.out.println("2-Mostrar Cuentas Bancarias");
            System.out.println("3-Usar Cuenta Bancaria");
            System.out.println("4-Eliminar Cuenta Bancaria");
            System.out.println("0-Volver al Menu Principal");
            menu = Entradas.validInt();
            switch (menu){
                case 1:
                    crearCuentaBancaria(posicionCliente);
                    break;
                case 2:
                    mostrarCuentasBancarias(posicionCliente);
                    break;
                case 3:
                    String mensaje="\nElija la cuenta bancaria a utilizar usando el ID:";
                    int posicion=Auxiliares.elegirCuentaBancaria(clientes, mensaje, posicionCliente);
                    if (posicion!=-1) {
                        menuUsoCuentaBancaria(posicion);
                    }
                    break;
                case 4:
                    eliminarCuentaBancaria(posicionCliente);
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

    //Metodos de clientes
    static void crearCliente() {
        System.out.println("Ingrese el nombre:");
        String nombre = Entradas.entradaString();
        System.out.println("Ingrese el apellido:");
        String apellido = Entradas.entradaString();
        boolean valido = false;
        System.out.println("Ingrese el DNI (8 digitos):");
        String dni;
        do{
            dni = Entradas.validDni();
            valido = true;
            for (int i=0;i<clientes.size();i++){
                if (clientes.get(i).getDni().equals(dni)){
                    valido = false;
                    System.out.println("Error: ya existe un cliente con ese DNI");
                    break;
                }
            }
        }while (!valido);
        System.out.println("Ingrese la direccion:");
        String direccion = Entradas.entradaString();
        System.out.println("Ingrese el telefono:");
        String telefono = Entradas.entradaString();
        System.out.println("Ingrese el email:");
        String email = Entradas.entradaString();
        int id = 0;
        for (int i=0;i<clientes.size();i++){
            id = clientes.get(i).getId()+1;
        }
    
        Cliente cliente = new Cliente(nombre, apellido, dni, direccion, telefono, email, id);
        clientes.add(cliente);
        System.out.println("Cliente creado");
    }

    static void mostrarClientes() {
        System.out.println("\nClientes:");
        if (clientes.size()==0){
            System.out.println("No hay clientes");
        }else{
            for (int i=0;i<clientes.size();i++){
                System.out.println("ID: "+clientes.get(i).getId()+", Nombre: "+clientes.get(i).getNombre()+", Apellido: "+clientes.get(i).getApellido()+", DNI: "+clientes.get(i).getDni()+", Direccion: "+clientes.get(i).getDireccion()+", Telefono: "+clientes.get(i).getTelefono()+", Email: "+clientes.get(i).getEmail());
            }
        }
    }

    static void modificarCliente() {
        String mensaje="\nElija el cliente a modificar usando el DNI:";
        int posicion=Auxiliares.elegirCliente(clientes, mensaje);
        if (posicion!=-1) {
            clientes.get(posicion).modificarDatos();
        }
    }

    static void eliminarCliente() {
        String mensaje="\nElija el cliente a eliminar usando el DNI:";
        int posicion=Auxiliares.elegirCliente(clientes, mensaje);
        
        boolean valido=true;
        if (posicion!=-1) {
            int respuesta;
            
            do {
                System.out.println("\nEn el caso de que ninguna cuenta bancaria tenga saldo, al eliminar el cliente se eliminaran sus cuentas tambien");
                System.out.println("¿Esta seguro que desea eliminar el cliente? (1-Si/2-No)");
                respuesta = Entradas.validInt();
                if (respuesta==2) {
                    System.out.println("Se cancela la eliminacion de cliente");
                    break;
                }else if (respuesta==1) {
                    if (clientes.get(posicion).getCuentasBancarias().size()!=0) {
                        for (int j=0;j<clientes.get(posicion).getCuentasBancarias().size();j++){
                            if (posicion==j) {
                                if (cuentasBancarias.get(j).getSaldo()>0) {
                                    System.out.println("Error: el cliente no se puede eliminar porque tiene cuentas bancarias con saldo");
                                    valido = false;
                                }
                            }
                        }
                    }
                    if (valido) {
                        System.out.println("Cliente eliminado");
                        clientes.remove(posicion);
                        break;
                    }
                }else {
                    System.out.println("Error: opcion no valida");
                }
            } while (respuesta!=1 && respuesta!=2);
        }
    }

    //Metodos de cuentas bancarias
    static void crearCuentaBancaria(int posicionCliente) {
        int idCliente = posicionCliente;
        LocalDate fechaApertura = LocalDate.now();
        double saldo = 0;

        boolean validCbu;
        String cbu;
        do{
            cbu=Math.round(Math.random()*(999999 - 100000 + 1) + 100000)+"";
            validCbu = true;
            for (int j=0;j<cuentasBancarias.size();j++){
                if (cuentasBancarias.get(j).getCbu().equals(cbu)){
                    validCbu = false;
                }
            }
        }while (!validCbu);
        
        String tipoCuenta="";
        int respuesta;
        do{
            System.out.println("Ingrese el tipo de cuenta bancaria (1-Caja de Ahorros/2-Cuenta Corriente)");
            respuesta = Entradas.validInt();
            if (respuesta==1) {
                tipoCuenta = "Caja de Ahorros";
            }else if (respuesta==2) {
                tipoCuenta = "Cuenta Corriente";
            }else {
                System.out.println("Error: opcion no valida");
            }
        }while (respuesta!=1 && respuesta!=2);
        
        int id=0;
        for (int j=0;j<cuentasBancarias.size();j++){
            id = cuentasBancarias.get(j).getId()+1;
        }
        
        CuentaBancaria cuentaBancaria = new CuentaBancaria(id,idCliente,fechaApertura,saldo,cbu,tipoCuenta);
        cuentasBancarias.add(cuentaBancaria);


        List<CuentaBancaria> cuentasCliente = clientes.get(posicionCliente).getCuentasBancarias();
        cuentasCliente.add(cuentaBancaria);
        clientes.get(posicionCliente).setCuentasBancarias(cuentasCliente);

        System.out.println("Cuenta bancaria creada");
    }

    static void mostrarCuentasBancarias(int posicionCliente) {
        System.out.println("\nCuentas Bancarias del cliente:");
        if (clientes.get(posicionCliente).getCuentasBancarias().size()==0){
            System.out.println("No hay cuentas bancarias");
        }else{
            for (int j=0;j<clientes.get(posicionCliente).getCuentasBancarias().size();j++){
                System.out.println("ID: "+clientes.get(posicionCliente).getCuentasBancarias().get(j).getId()+", CBU: "+clientes.get(posicionCliente).getCuentasBancarias().get(j).getCbu()+", Fecha de apretura: "+clientes.get(posicionCliente).getCuentasBancarias().get(j).getFechaApertura()+", Tipo: "+clientes.get(posicionCliente).getCuentasBancarias().get(j).getTipoCuenta());
            }
        }
    }

    static void menuUsoCuentaBancaria(int idCuentaBancaria) {
        int menu;
        do{
            System.out.println();
            System.out.println("\nMenu Uso Cuenta Bancaria:");
            System.out.println("1-Mostrar Datos");
            System.out.println("2-Mostrar Saldo");
            System.out.println("3-Retirar");
            System.out.println("4-Depositar");
            System.out.println("5-Transferir");
            System.out.println("6-Mostrar Movimientos");
            System.out.println("0-Volver al Menu Principal");
            menu = Entradas.validInt();
            switch (menu){
                case 1:
                    System.out.println("Mostrar Datos");
                    break;
                case 2:
                    System.out.println("Mostrar Saldo");
                    break;
                case 3:
                    System.out.println("Retirar");
                    break;
                case 4:
                    System.out.println("Depositar");
                    break;
                case 5:
                    System.out.println("Transferir");
                    break;
                case 6:
                    System.out.println("Mostrar Movimientos");
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
    
    static void eliminarCuentaBancaria(int posicionCliente) {
        String mensaje="\nElija la cuenta bancaria a eliminar usando el ID:";
        int posicion=Auxiliares.elegirCuentaBancaria(clientes, mensaje, posicionCliente);
        
        int respuesta;
        if (posicion!=-1) {
            if (clientes.get(posicionCliente).getCuentasBancarias().get(posicion).getSaldo()>0) {
                System.out.println("Error: la cuenta bancaria no se puede eliminar porque aun tiene saldo");
            }else{
                do {
                    System.out.println("¿Esta seguro que desea eliminar la cuenta bancaria? (1-Si/2-No)");
                    respuesta = Entradas.validInt();
                    if (respuesta==1) {
                        for (int k=0;k<cuentasBancarias.size();k++){
                            if (cuentasBancarias.get(k).getId()==clientes.get(posicionCliente).getCuentasBancarias().get(posicion).getId()) {
                                cuentasBancarias.remove(k);
                                break;
                            }
                        }
                        clientes.get(posicionCliente).getCuentasBancarias().remove(posicion);
                        System.out.println("Cuenta bancaria eliminada");
                        break;
                    }else if (respuesta==2) {
                        System.out.println("Se cancela la eliminacion de cuenta bancaria");
                        break;
                    }else{
                        System.out.println("Error: opcion no valida");
                    }
                } while (respuesta != 1 && respuesta!= 2);
                System.out.println("Cuenta bancaria eliminada"); 
            }
        }
    }

}