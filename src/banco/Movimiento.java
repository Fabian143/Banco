package banco;

import java.time.LocalDateTime;
/** Entidad que maneja los movimientos de las cuentas de un Banco*/
public class Movimiento {

    private final String tipo;
    private final double monto;
    private final LocalDateTime fecha;

    public Movimiento(String tipo, double monto) {
        this.tipo = tipo;
        this.monto = monto;
        this.fecha = LocalDateTime.now();
    }

    public String getTipo() {
        return tipo;
    }

    public double getMonto() {
        return monto;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    @Override/** guarda la información del movimiento en  un String*/
    public String toString() {
        return "\nTipo: "+tipo+"\nMonto :"+monto+"\nFecha"+fecha;
    }
}
