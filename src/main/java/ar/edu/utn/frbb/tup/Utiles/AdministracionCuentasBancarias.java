package ar.edu.utn.frbb.tup.Utiles;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ar.edu.utn.frbb.tup.Banco;
import ar.edu.utn.frbb.tup.Cliente;
import ar.edu.utn.frbb.tup.CuentaBancaria;
import ar.edu.utn.frbb.tup.Operaciones;

public class AdministracionCuentasBancarias {
    static List<Cliente> clientes=new ArrayList<Cliente>();
    static List<CuentaBancaria> cuentasBancarias=new ArrayList<CuentaBancaria>();

    public static void crearCuentaBancaria(int posicionCliente) {
        clientes=Banco.getClientes();
        cuentasBancarias=Banco.getCuentasBancarias();

        int idCliente = clientes.get(posicionCliente).getId();
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
                Consola.limpiarPantalla();
                tipoCuenta = "Caja de Ahorros";
            }else if (respuesta==2) {
                Consola.limpiarPantalla();
                tipoCuenta = "Cuenta Corriente";
            }else {
                Consola.limpiarPantalla();
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


        Consola.limpiarPantalla();
        System.out.println("Cuenta bancaria creada");

        Banco.setCuentasBancarias(cuentasBancarias);
        Banco.setClientes(clientes);
    }

    public static void mostrarCuentasBancarias(int posicionCliente) {
        clientes=Banco.getClientes();
        cuentasBancarias=Banco.getCuentasBancarias();

        System.out.println("Cuentas Bancarias del cliente:");
        if (clientes.get(posicionCliente).getCuentasBancarias().size()==0){
            System.out.println("No hay cuentas bancarias");
        }else{
            for (int j=0;j<clientes.get(posicionCliente).getCuentasBancarias().size();j++){
                System.out.println("ID: "+clientes.get(posicionCliente).getCuentasBancarias().get(j).getId()+", CBU: "+clientes.get(posicionCliente).getCuentasBancarias().get(j).getCbu()+", Fecha de apretura: "+clientes.get(posicionCliente).getCuentasBancarias().get(j).getFechaApertura()+", Tipo: "+clientes.get(posicionCliente).getCuentasBancarias().get(j).getTipoCuenta());
            }
        }
    }

    public static void menuAdministrarCuentaBancaria(int posicionCuentaBancaria) {
        clientes=Banco.getClientes();
        cuentasBancarias=Banco.getCuentasBancarias();
        
        int menu;
        do{
            System.out.println("------------------------------");
            System.out.println("Menu de administracion de cuentas bancaria:");
            System.out.println("1-Mostrar Datos");
            System.out.println("2-Depositar");
            System.out.println("3-Retirar");
            System.out.println("4-Transferir");
            System.out.println("0-Volver");
            System.out.println("------------------------------");
            menu = Entradas.validInt();
            switch (menu){
                case 1:
                    Consola.limpiarPantalla();
                    cuentasBancarias.get(posicionCuentaBancaria).mostrarDatos();
                    break;
                case 2:
                    Consola.limpiarPantalla();
                    Operaciones.depositar(posicionCuentaBancaria);
                    break;
                case 3:
                    Consola.limpiarPantalla();
                    Operaciones.retirar(posicionCuentaBancaria);
                    break;
                case 4:
                    Consola.limpiarPantalla();
                    Operaciones.transferir(posicionCuentaBancaria);
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

    public static void eliminarCuentaBancaria(int posicionCliente) {
        clientes=Banco.getClientes();
        cuentasBancarias=Banco.getCuentasBancarias();
        
        String mensaje="\nElija la cuenta bancaria a eliminar usando el ID:";
        int posicion=elegirCuentaBancaria(clientes, mensaje, posicionCliente);
        
        int respuesta;
        if (posicion!=-1) {
            if (clientes.get(posicionCliente).getCuentasBancarias().get(posicion).getSaldo()>0) {
                Consola.limpiarPantalla();
                System.out.println("Error: la cuenta bancaria no se puede eliminar porque aun tiene saldo");
            }else{
                Consola.limpiarPantalla();
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

                        Consola.limpiarPantalla();
                        System.out.println("Cuenta bancaria eliminada");

                        Banco.setCuentasBancarias(cuentasBancarias);
                        Banco.setClientes(clientes);
                        break;
                    }else if (respuesta==2) {
                        Consola.limpiarPantalla();
                        System.out.println("Se cancela la eliminacion de cuenta bancaria");
                        break;
                    }else{
                        Consola.limpiarPantalla();
                        System.out.println("Error: opcion no valida");
                    }
                } while (respuesta != 1 && respuesta!= 2);
            }
        }
    }

    //Metodos auxiliares
    public static int elegirCuentaBancaria(List<Cliente> clientes, String mensaje, int posicionCliente) {
        int posicion=-1;
        
        System.out.println("Cuentas Bancarias del cliente:");
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
                Consola.limpiarPantalla();
                System.out.println("La cuenta bancaria no existe");    
            }
        }
        Consola.limpiarPantalla();
        return posicion;
    }
}
