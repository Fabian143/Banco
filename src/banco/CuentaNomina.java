package banco;
/** Entidad que maneja las cuentas de Nomina de un banco estas no tienen ni sobregiro ni una tasa mensual */
public class CuentaNomina extends Cuenta {

    public CuentaNomina(String titular, double saldo) {
        super(titular, saldo);
    }

    public CuentaNomina(String titular) {
        //llama al constructor principal y deja el saldo inicial en 0
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
