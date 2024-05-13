package TP2;

import java.time.LocalDate;

public class Movimientos {
    private int id;
    private int idCuentaBancaria;
    private LocalDate fechaCreacion;
    private double monto;
    private int operacion;
    
    public Movimientos(int id, int idCuentaBancaria, LocalDate fechaCreacion, double monto, int operacion) {
        this.id = id;
        this.idCuentaBancaria = idCuentaBancaria;
        this.fechaCreacion = fechaCreacion;
        this.monto = monto;
        this.operacion = operacion;
    }

    //setters y getters
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setIdCuentaBancaria(int idCuentaBancaria) {
        this.idCuentaBancaria = idCuentaBancaria;
    }

    public int getIdCuentaBancaria() {
        return idCuentaBancaria;
    }

    public void setFecha(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDate getFecha() {
        return fechaCreacion;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public double getMonto() {
        return monto;
    }

    public void setOperacion(int operacion) {
        this.operacion = operacion;
    }

    public int getOperacion() {
        return operacion;
    }
}
