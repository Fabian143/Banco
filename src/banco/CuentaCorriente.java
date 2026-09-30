package banco;

public class CuentaCorriente extends Cuenta implements Rentable {

    private final double sobregiro;
    private final double tasaMensual;

    public CuentaCorriente(
            String titular,
            double saldo,
            double sobregiro) {

        super(titular, saldo);

        if (sobregiro < 0) {
            throw new IllegalArgumentException(
                    "El sobregiro no puede ser negativo"
            );
        }

        this.sobregiro = sobregiro;
        this.tasaMensual = 1.19;
    }

    public CuentaCorriente(String titular) {
        this(titular, 0, 500000);
    }

    @Override
    protected boolean puedeRetirar(double monto) {
        return monto <= getSaldo() + sobregiro;
    }

    @Override
    public double calcularInteres() {
        return getSaldo() * tasaMensual;
    }

    public double getSobregiro() {
        return sobregiro;
    }

    @Override
    public String getTipo() {
        return "Corriente";
    }
}

