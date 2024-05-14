package TP2.Utiles;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import TP2.Banco;
import TP2.Cliente;
import TP2.CuentaBancaria;

public class MetodosCuentasBancarias {
    static List<Cliente> clientes=new ArrayList<Cliente>();
    static List<CuentaBancaria> cuentasBancarias=new ArrayList<CuentaBancaria>();

    public static void crearCuentaBancaria(int posicionCliente) {
        clientes=Banco.getClientes();
        cuentasBancarias=Banco.getCuentasBancarias();

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

        Banco.setCuentasBancarias(cuentasBancarias);
        Banco.setClientes(clientes);
    }

    public static void mostrarCuentasBancarias(int posicionCliente) {
        clientes=Banco.getClientes();
        cuentasBancarias=Banco.getCuentasBancarias();

        System.out.println("\nCuentas Bancarias del cliente:");
        if (clientes.get(posicionCliente).getCuentasBancarias().size()==0){
            System.out.println("No hay cuentas bancarias");
        }else{
            for (int j=0;j<clientes.get(posicionCliente).getCuentasBancarias().size();j++){
                System.out.println("ID: "+clientes.get(posicionCliente).getCuentasBancarias().get(j).getId()+", CBU: "+clientes.get(posicionCliente).getCuentasBancarias().get(j).getCbu()+", Fecha de apretura: "+clientes.get(posicionCliente).getCuentasBancarias().get(j).getFechaApertura()+", Tipo: "+clientes.get(posicionCliente).getCuentasBancarias().get(j).getTipoCuenta());
            }
        }
    }

    public static void menuAdministrarCuentaBancaria(int idCuentaBancaria) {
        clientes=Banco.getClientes();
        cuentasBancarias=Banco.getCuentasBancarias();
        
        int menu;
        do{
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

    public static void eliminarCuentaBancaria(int posicionCliente) {
        clientes=Banco.getClientes();
        cuentasBancarias=Banco.getCuentasBancarias();
        
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

                        Banco.setCuentasBancarias(cuentasBancarias);
                        Banco.setClientes(clientes);
                        break;
                    }else if (respuesta==2) {
                        System.out.println("Se cancela la eliminacion de cuenta bancaria");
                        break;
                    }else{
                        System.out.println("Error: opcion no valida");
                    }
                } while (respuesta != 1 && respuesta!= 2);
            }
        }
    }
}
