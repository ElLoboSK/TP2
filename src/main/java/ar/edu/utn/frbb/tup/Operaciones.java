package ar.edu.utn.frbb.tup;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

import ar.edu.utn.frbb.tup.Utiles.*;

public class Operaciones {
    static List<Cliente> clientes=new ArrayList<Cliente>();
    static List<CuentaBancaria> cuentasBancarias=new ArrayList<CuentaBancaria>();

    public static void depositar(int posicionCuentaBancaria){
        clientes=Banco.getClientes();
        cuentasBancarias=Banco.getCuentasBancarias();

        int idMovimiento=0;
        if (cuentasBancarias.get(posicionCuentaBancaria).getMovimientos().size()!=0) {
            for(int i=0;i<cuentasBancarias.get(posicionCuentaBancaria).getMovimientos().size();i++) {
                idMovimiento=cuentasBancarias.get(posicionCuentaBancaria).getMovimientos().get(i).getId()+1;
            }
        }

        int idCuentaBancaria = cuentasBancarias.get(posicionCuentaBancaria).getId();
        LocalDate fechaOperacion = LocalDate.now();

        System.out.println("Ingrese el monto a depositar:");
        double monto = Entradas.validDouble();

        Movimiento movimiento = new Movimiento(idMovimiento,idCuentaBancaria,fechaOperacion,monto,"Deposito");
        List<Movimiento> movimientos = cuentasBancarias.get(posicionCuentaBancaria).getMovimientos();
        movimientos.add(movimiento);
        cuentasBancarias.get(posicionCuentaBancaria).setMovimientos(movimientos);

        double saldoFinal = cuentasBancarias.get(posicionCuentaBancaria).getSaldo() + monto;
        cuentasBancarias.get(posicionCuentaBancaria).setSaldo(saldoFinal);

        Consola.limpiarPantalla();
        System.out.println("Deposito realizado");
    }

    public static void retirar(int posicionCuentaBancaria){
        clientes=Banco.getClientes();
        cuentasBancarias=Banco.getCuentasBancarias();

        if (cuentasBancarias.get(posicionCuentaBancaria).getSaldo()==0) {
            Consola.limpiarPantalla();
            System.out.println("No hay saldo");
        }else{
            int idMovimiento=0;
            if (cuentasBancarias.get(posicionCuentaBancaria).getMovimientos().size()!=0) {
                for(int i=0;i<cuentasBancarias.get(posicionCuentaBancaria).getMovimientos().size();i++) {
                    idMovimiento=cuentasBancarias.get(posicionCuentaBancaria).getMovimientos().get(i).getId()+1;
                }
            }
    
            int idCuentaBancaria = cuentasBancarias.get(posicionCuentaBancaria).getId();
            LocalDate fechaOperacion = LocalDate.now();
    
            boolean valido=false;
            double monto;
            do{
                System.out.println("Ingrese el monto a retirar:");
                monto = Entradas.validDouble();
                if (monto<=cuentasBancarias.get(posicionCuentaBancaria).getSaldo()) {
                    valido=true;
                }else {
                    Consola.limpiarPantalla();
                    System.out.println("Error: saldo insuficiente");
                }
    
            }while (!valido);
    
            Movimiento movimiento = new Movimiento(idMovimiento,idCuentaBancaria,fechaOperacion,monto,"Retiro");
            List<Movimiento> movimientos = cuentasBancarias.get(posicionCuentaBancaria).getMovimientos();
            movimientos.add(movimiento);
            cuentasBancarias.get(posicionCuentaBancaria).setMovimientos(movimientos);
    
            double saldoFinal = cuentasBancarias.get(posicionCuentaBancaria).getSaldo() - monto;
            cuentasBancarias.get(posicionCuentaBancaria).setSaldo(saldoFinal);
    
            Consola.limpiarPantalla();
            System.out.println("Retiro realizado");
        }
    }

    public static void transferir(int posicionCuentaBancaria){
        clientes=Banco.getClientes();
        cuentasBancarias=Banco.getCuentasBancarias();

        if (cuentasBancarias.get(posicionCuentaBancaria).getSaldo()==0) {
            Consola.limpiarPantalla();
            System.out.println("No hay saldo");
        }else{
            System.out.println("Cuentas bancarias para transferir:");
            if (cuentasBancarias.size()==1) {
                System.out.println("No hay cuentas bancarias para transferir");
            }else{
                for(int i=0;i<cuentasBancarias.size();i++) {
                    if (i!=posicionCuentaBancaria) {
                        System.out.println("ID: "+cuentasBancarias.get(i).getId()+", CBU: "+cuentasBancarias.get(i).getCbu()+", Fecha de apertura: "+cuentasBancarias.get(i).getFechaApertura());
                    }
                }
                System.out.println("\nIngrese el CBU de la cuenta bancaria a transferir:");
                String cbu=Entradas.validCBU();
                
                int posicionCuentaBancariaTransferir=-1;
                for (int i=0;i<cuentasBancarias.size();i++) {
                    if (cuentasBancarias.get(i).getCbu().equals(cbu)) {
                        posicionCuentaBancariaTransferir=i;
                    }
                }
    
                if (posicionCuentaBancariaTransferir==-1) {
                    Consola.limpiarPantalla();
                    System.out.println("La cuenta bancaria no existe");
                }else{
                    int idMovimiento=0;
                    if (cuentasBancarias.get(posicionCuentaBancaria).getMovimientos().size()!=0) {
                        for(int i=0;i<cuentasBancarias.get(posicionCuentaBancaria).getMovimientos().size();i++) {
                            idMovimiento=cuentasBancarias.get(posicionCuentaBancaria).getMovimientos().get(i).getId()+1;
                        }
                    }
                
                    int idCuentaBancaria = cuentasBancarias.get(posicionCuentaBancaria).getId();
                    LocalDate fechaOperacion = LocalDate.now();
                
                    boolean validoMonto=false;
                    double monto;
                    do{
                        System.out.println("Ingrese el monto a transferir:");
                        monto = Entradas.validDouble();
                        if (monto<=cuentasBancarias.get(posicionCuentaBancaria).getSaldo()) {
                            validoMonto=true;
                        }else {
                            Consola.limpiarPantalla();
                            System.out.println("Error: saldo insuficiente");
                        }
                
                    }while (!validoMonto);
                
                    Movimiento movimiento = new Movimiento(idMovimiento,idCuentaBancaria,fechaOperacion,monto,"Transferencia enviada");
                    List<Movimiento> movimientos = cuentasBancarias.get(posicionCuentaBancaria).getMovimientos();
                    movimientos.add(movimiento);
                    cuentasBancarias.get(posicionCuentaBancaria).setMovimientos(movimientos);
                
                    double saldoFinal = cuentasBancarias.get(posicionCuentaBancaria).getSaldo() - monto;
                    cuentasBancarias.get(posicionCuentaBancaria).setSaldo(saldoFinal);
    
                    Movimiento movimientoTransferir = new Movimiento(idMovimiento,cuentasBancarias.get(posicionCuentaBancariaTransferir).getId(),fechaOperacion,monto,"Transferencia recibida");

                    movimientos = cuentasBancarias.get(posicionCuentaBancariaTransferir).getMovimientos();
                    movimientos.add(movimientoTransferir);
                    cuentasBancarias.get(posicionCuentaBancariaTransferir).setMovimientos(movimientos);

                    saldoFinal = cuentasBancarias.get(posicionCuentaBancariaTransferir).getSaldo() + monto;
                    cuentasBancarias.get(posicionCuentaBancariaTransferir).setSaldo(saldoFinal);
                
                    Consola.limpiarPantalla();
                    System.out.println("Transferencia realizada");
                }
            }
        }
    }
}