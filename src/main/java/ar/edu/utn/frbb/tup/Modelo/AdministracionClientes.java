package ar.edu.utn.frbb.tup.Modelo;

import java.util.ArrayList;
import java.util.List;

import ar.edu.utn.frbb.tup.Modelo.Entidades.Banco;
import ar.edu.utn.frbb.tup.Modelo.Entidades.Cliente;
import ar.edu.utn.frbb.tup.Modelo.Entidades.CuentaBancaria;
import ar.edu.utn.frbb.tup.Utiles.Consola;
import ar.edu.utn.frbb.tup.Utiles.Entradas;

public class AdministracionClientes {
    static List<Cliente> clientes=new ArrayList<Cliente>();
    static List<CuentaBancaria> cuentasBancarias=new ArrayList<CuentaBancaria>();

    public static void crearCliente() {
        clientes=Banco.getClientes();

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

        Consola.limpiarPantalla();
        System.out.println("Cliente creado");
        
        Banco.setClientes(clientes);
    }

    public static void mostrarClientes() {
        clientes=Banco.getClientes();
        
        System.out.println("Clientes:");
        if (clientes.size()==0){
            System.out.println("No hay clientes");
        }else{
            for (int i=0;i<clientes.size();i++){
                System.out.println("ID: "+clientes.get(i).getId()+", Nombre: "+clientes.get(i).getNombre()+", Apellido: "+clientes.get(i).getApellido()+", DNI: "+clientes.get(i).getDni()+", Direccion: "+clientes.get(i).getDireccion()+", Telefono: "+clientes.get(i).getTelefono()+", Email: "+clientes.get(i).getEmail());
            }
        }
    }

    public static void modificarCliente(int posicionCliente) {
        clientes=Banco.getClientes();

        int menu = 0;
        
        do{
            System.out.println("------------------------------");
            System.out.println("Informacion actual del cliente:");
            System.out.println("Id: "+clientes.get(posicionCliente).getId());
            System.out.println("Nombre: "+clientes.get(posicionCliente).getNombre());
            System.out.println("Apellido: "+clientes.get(posicionCliente).getApellido());
            System.out.println("DNI: "+clientes.get(posicionCliente).getDni());
            System.out.println("Direccion: "+clientes.get(posicionCliente).getDireccion());
            System.out.println("Telefono: "+clientes.get(posicionCliente).getTelefono());
            System.out.println("Email: "+clientes.get(posicionCliente).getEmail());
            System.out.println("\nMenu de modificacion:");
            System.out.println("1-Nombre");
            System.out.println("2-Apellido");
            System.out.println("3-Direccion");
            System.out.println("4-Telefono");
            System.out.println("5-Email");
            System.out.println("0-Volver");
            System.out.println("------------------------------");
            menu = Entradas.validInt();
            switch (menu) {
                case 1:
                    Consola.limpiarPantalla();
                    System.out.println("Ingrese el nuevo nombre:");
                    String nombre = Entradas.entradaString();
                    clientes.get(posicionCliente).setNombre(nombre);
                    Consola.limpiarPantalla();
                    break;
                case 2:
                    Consola.limpiarPantalla();
                    System.out.println("Ingrese el nuevo apellido:");
                    String apellido = Entradas.entradaString();
                    clientes.get(posicionCliente).setApellido(apellido);
                    Consola.limpiarPantalla();
                    break;
                case 3:
                    Consola.limpiarPantalla();
                    System.out.println("Ingrese la nueva direccion:");
                    String direccion = Entradas.entradaString();
                    clientes.get(posicionCliente).setDireccion(direccion);
                    Consola.limpiarPantalla();
                    break;
                case 4:
                    Consola.limpiarPantalla();
                    System.out.println("Ingrese el nuevo telefono:");
                    String telefono = Entradas.entradaString();
                    clientes.get(posicionCliente).setTelefono(telefono);
                    Consola.limpiarPantalla();
                    break;
                case 5:
                    Consola.limpiarPantalla();
                    System.out.println("Ingrese el nuevo email:");
                    String email = Entradas.entradaString();
                    clientes.get(posicionCliente).setEmail(email);
                    Consola.limpiarPantalla();
                    break;
                case 0:
                    Consola.limpiarPantalla();
                    System.out.println("Volver");
                    break;
            
                default:
                    Consola.limpiarPantalla();
                    System.out.println("Error: opcion no valida");
                    break;
            }
        }while (menu!=0);
    }

    public static void eliminarCliente() {
        clientes=Banco.getClientes();
        cuentasBancarias=Banco.getCuentasBancarias();

        String mensaje="\nElija el cliente a eliminar usando el DNI:";
        int posicion=elegirCliente(clientes, mensaje);
        
        boolean valido=true;
        if (posicion!=-1) {
            int respuesta;

            Consola.limpiarPantalla();
            do {
                System.out.println("En el caso de que ninguna cuenta bancaria tenga saldo, al eliminar el cliente se eliminaran sus cuentas tambien");
                System.out.println("¿Esta seguro que desea eliminar el cliente? (1-Si/2-No)");
                respuesta = Entradas.validInt();
                if (respuesta==2) {
                    Consola.limpiarPantalla();
                    System.out.println("Se cancela la eliminacion de cliente");
                    break;
                }else if (respuesta==1) {
                    if (clientes.get(posicion).getCuentasBancarias().size()!=0) {
                        for (int j=0;j<clientes.get(posicion).getCuentasBancarias().size();j++){
                            if (posicion==j) {
                                if (cuentasBancarias.get(j).getSaldo()>0) {
                                    Consola.limpiarPantalla();
                                    System.out.println("Error: el cliente no se puede eliminar porque tiene cuentas bancarias con saldo");
                                    valido = false;
                                }
                            }
                        }
                    }
                    if (valido) {
                        Consola.limpiarPantalla();
                        System.out.println("Cliente eliminado");
                        clientes.remove(posicion);

                        Banco.setClientes(clientes);
                        break;
                    }
                }else {
                    Consola.limpiarPantalla();
                    System.out.println("Error: opcion no valida");
                }
            } while (respuesta!=1 && respuesta!=2);
        }
    }

    //Metodos auxiliares
    public static int elegirCliente(List<Cliente> clientes, String mensaje) {
        int posicion=-1;

        System.out.println("Clientes:");
        if (clientes.size()==0){
            System.out.println("No hay clientes");
        }else{
            for (int i=0;i<clientes.size();i++){
                System.out.println("ID: "+clientes.get(i).getId()+", Nombre: "+clientes.get(i).getNombre()+", Apellido: "+clientes.get(i).getApellido()+", DNI:"+clientes.get(i).getDni());
            }
            System.out.println(mensaje);
            String dni = Entradas.validDni();

            for (int i=0;i<clientes.size();i++){
                if (clientes.get(i).getDni().equals(dni)){
                    posicion = i;
                }
            }
            if (posicion==-1){
                Consola.limpiarPantalla();
                System.out.println("El cliente no existe");
            }
        }
        Consola.limpiarPantalla();
        return posicion;
    }
}
