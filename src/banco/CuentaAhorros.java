package banco;

public class CuentaAhorros extends Cuenta implements Rentable {

    private final double tasaMensual;

    public CuentaAhorros(
            String titular,
            double saldo,
            double tasaMensual) {

        super(titular, saldo);
        this.tasaMensual = tasaMensual;
    }

    public CuentaAhorros(String titular) {
        this(titular, 0, 0.01);
    }

    @Override
    protected boolean puedeRetirar(double monto) {
        return monto <= getSaldo();
    }

    @Override
    public double calcularInteres() {
        return getSaldo() * tasaMensual;
    }

    @Override
    public String getTipo() {
        return "Ahorros";
    }
}
