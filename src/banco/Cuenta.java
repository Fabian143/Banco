package banco;

import java.util.ArrayList;

public abstract class Cuenta {

    private static int contador = 1;

    private final String numero;
    private final String titular;
    private double saldo;

    private final ArrayList<Movimiento> movimientos =
            new ArrayList<>();

    public Cuenta(String titular, double saldo) {

        if (titular == null || titular.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "El titular es obligatorio"
            );
        }

        if (saldo < 0) {
            throw new IllegalArgumentException(
                    "El saldo inicial no puede ser negativo"
            );
        }

        this.numero = String.format("%06d", contador++);
        this.titular = titular;
        this.saldo = saldo;
    }

    public void depositar(double monto) {

        validarMonto(monto);

        saldo += monto;

        movimientos.add(
                new Movimiento("DEPÓSITO", monto)
        );
    }

    private void validarMonto(double monto) {

        if (monto <= 0) {
            throw new IllegalArgumentException(
                    "El monto debe ser positivo"
            );
        }
    }

    public String getNumero() {
        return numero;
    }

    public String getTitular() {
        return titular;
    }

    public double getSaldo() {
        return saldo;
    }

    public void retirar(double monto) {

        validarMonto(monto);

        if (!puedeRetirar(monto)) {
            throw new IllegalStateException(
                    "Fondos insuficientes"
            );
        }

        saldo -= monto;

        movimientos.add(
                new Movimiento("RETIRO", monto)
        );
    }

    public ArrayList<Movimiento> getMovimientos() {
        return movimientos;
    }

    protected abstract boolean puedeRetirar(double monto);

    public abstract String getTipo();

    @Override
    public String toString() {
        return "\n Cuenta:"+numero+"\n Tipo: "+getTipo()+"\n Titular: "+titular+" \nSaldo: "+saldo+"\n";
    }
}

