package TP2;

import TP2.Utiles.*;

import java.util.List;
import java.util.ArrayList;

public class Cliente extends Persona {
    private int id;
    private List<CuentaBancaria> cuentasBancarias;

    public Cliente(String nombre, String apellido, String dni, String direccion, String telefono, String email, int id) {
        super(nombre, apellido, dni, direccion, telefono, email);
        this.id = id;
        this.cuentasBancarias = new ArrayList<CuentaBancaria>();
    }
    
    public void modificarDatos() {
        int menu = 0;
        
        do{
            System.out.println("\nInformacion actual del cliente:");
            System.out.println("Id: "+id);
            System.out.println("Nombre: "+getNombre());
            System.out.println("Apellido: "+getApellido());
            System.out.println("DNI: "+getDni());
            System.out.println("Direccion: "+getDireccion());
            System.out.println("Telefono: "+getTelefono());
            System.out.println("Email: "+getEmail());
            System.out.println("\nMenu:");
            System.out.println("1-Nombre");
            System.out.println("2-Apellido");
            System.out.println("3-Direccion");
            System.out.println("4-Telefono");
            System.out.println("5-Email");
            System.out.println("0-Salir");
            menu = Entradas.validInt();
            switch (menu) {
                case 1:
                    System.out.println("Ingrese el nuevo nombre:");
                    String nombre = Entradas.entradaString();
                    setNombre(nombre);
                    break;
                case 2:
                    System.out.println("Ingrese el nuevo apellido:");
                    String apellido = Entradas.entradaString();
                    setApellido(apellido);
                    break;
                case 3:
                    System.out.println("Ingrese la nueva direccion:");
                    String direccion = Entradas.entradaString();
                    setDireccion(direccion);
                    break;
                case 4:
                    System.out.println("Ingrese el nuevo telefono:");
                    String telefono = Entradas.entradaString();
                    setTelefono(telefono);
                    break;
                case 5:
                    System.out.println("Ingrese el nuevo email:");
                    String email = Entradas.entradaString();
                    setEmail(email);
                    break;
                case 0:
                    System.out.println("Salir");
                    break;
            
                default:
                    System.out.println("Error: opcion no valida");
                    break;
            }
        }while (menu!=0);
    }

    //setters y getters
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public List<CuentaBancaria> getCuentasBancarias() {
        return cuentasBancarias;
    }

    public void setCuentasBancarias(List<CuentaBancaria> cuentasBancarias) {
        this.cuentasBancarias = cuentasBancarias;
    }
}