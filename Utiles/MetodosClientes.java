package TP2.Utiles;

import java.util.ArrayList;
import java.util.List;

import TP2.Banco;
import TP2.Cliente;
import TP2.CuentaBancaria;

public class MetodosClientes {
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
        System.out.println("Cliente creado");
        
        Banco.setClientes(clientes);
    }

    public static void mostrarClientes() {
        clientes=Banco.getClientes();
        
        System.out.println("\nClientes:");
        if (clientes.size()==0){
            System.out.println("No hay clientes");
        }else{
            for (int i=0;i<clientes.size();i++){
                System.out.println("ID: "+clientes.get(i).getId()+", Nombre: "+clientes.get(i).getNombre()+", Apellido: "+clientes.get(i).getApellido()+", DNI: "+clientes.get(i).getDni()+", Direccion: "+clientes.get(i).getDireccion()+", Telefono: "+clientes.get(i).getTelefono()+", Email: "+clientes.get(i).getEmail());
            }
        }
    }

    public static void modificarCliente() {
        clientes=Banco.getClientes();
        
        String mensaje="\nElija el cliente a modificar usando el DNI:";
        int posicion=Auxiliares.elegirCliente(clientes, mensaje);
        if (posicion!=-1) {
            clientes.get(posicion).modificarDatos();
        }
    }

    public static void eliminarCliente() {
        clientes=Banco.getClientes();
        cuentasBancarias=Banco.getCuentasBancarias();

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

                        Banco.setClientes(clientes);
                        break;
                    }
                }else {
                    System.out.println("Error: opcion no valida");
                }
            } while (respuesta!=1 && respuesta!=2);
        }
    }
}
