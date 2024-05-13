package TP2.Utiles;

import TP2.*;

import java.util.List;

public class Auxiliares {
    
    //Clientes
    public static int elegirCliente(List<Cliente> clientes, String mensaje) {
        int posicion=-1;

        System.out.println("\nClientes:");
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
                System.out.println("El cliente no existe");
            }
        }
        return posicion;
    }

    //Cuentas Bancarias
    public static int elegirCuentaBancaria(List<Cliente> clientes, String mensaje, int posicionCliente) {
        int posicion=-1;
        
        System.out.println("\nCuentas Bancarias del cliente:");
        if (clientes.get(posicionCliente).getCuentasBancarias().size()==0){
            System.out.println("No hay cuentas bancarias");
        }else{
            for (int i=0;i<clientes.get(posicionCliente).getCuentasBancarias().size();i++){
                System.out.println("ID: "+clientes.get(posicionCliente).getCuentasBancarias().get(i).getId()+", CBU: "+clientes.get(posicionCliente).getCuentasBancarias().get(i).getCbu()+", Fecha de apretura: "+clientes.get(posicionCliente).getCuentasBancarias().get(i).getFechaApertura()+", Tipo: "+clientes.get(posicionCliente).getCuentasBancarias().get(i).getTipoCuenta());
            }
            System.out.println(mensaje);
            int id = Entradas.validInt();
            
            for (int i=0;i<clientes.get(posicionCliente).getCuentasBancarias().size();i++){
                if (clientes.get(posicionCliente).getCuentasBancarias().get(i).getId()==id){
                    posicion=i;
                }
            }
            if (posicion==-1) {
                System.out.println("La cuenta bancaria no existe");    
            }
        }
        return posicion;
    }

    //Otros
}
