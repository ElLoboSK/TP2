package TP2;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

public class CuentaBancaria {
    private int id;
    private int idCliente;
    private LocalDate fechaApertura;
    private double saldo;
    private String cbu;
    private String tipoCuenta;
    private List<Movimientos> movimientos;

    public CuentaBancaria(int id, int idCliente, LocalDate fechaApertura, double saldo, String cbu, String tipoCuenta) {
        this.id = id;
        this.idCliente = idCliente;
        this.fechaApertura = fechaApertura;
        this.saldo = saldo;
        this.cbu = cbu;
        this.tipoCuenta = tipoCuenta;
        this.movimientos = new ArrayList<Movimientos>();
    }

    //setters y getters
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setFechaApertura(LocalDate fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    public LocalDate getFechaApertura() {
        return fechaApertura;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setCbu(String cbu) {
        this.cbu = cbu;
    }

    public String getCbu() {
        return cbu;
    }
    
    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public void setMovimientos(List<Movimientos> movimientos) {
        this.movimientos = movimientos;
    }

    public List<Movimientos> getMovimientos() {
        return movimientos;
    }
}
