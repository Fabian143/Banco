package banco;

public class CuentaNomina extends Cuenta {

    public CuentaNomina(String titular, double saldo) {
        super(titular, saldo);
    }

    public CuentaNomina(String titular) {
        this(titular, 0);
    }

    @Override
    protected boolean puedeRetirar(double monto) {
        return monto <= getSaldo();
    }

    @Override
    public String getTipo() {
        return "Nómina";
    }
}
