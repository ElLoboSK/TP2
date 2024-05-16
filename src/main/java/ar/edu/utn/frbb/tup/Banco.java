package ar.edu.utn.frbb.tup;

import java.util.ArrayList;
import java.util.List;

public class Banco {
    static List<Cliente> clientes=new ArrayList<Cliente>();
    static List<CuentaBancaria> cuentasBancarias=new ArrayList<CuentaBancaria>();

    //setters y getters
    public static void setClientes(List<Cliente> clientes) {
        Banco.clientes = clientes;
    }

    public static List<Cliente> getClientes() {
        return clientes;
    }

    public static void setCuentasBancarias(List<CuentaBancaria> cuentasBancarias) {
        Banco.cuentasBancarias = cuentasBancarias;
    }

    public static List<CuentaBancaria> getCuentasBancarias() {
        return cuentasBancarias;
    }
}
