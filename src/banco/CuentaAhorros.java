package banco;

/** Entidad que maneja las cuentas de ahorros en un banco */
public class CuentaAhorros extends Cuenta implements Rentable {

    private final double tasaMensual;

    public CuentaAhorros(String titular,double saldo,double tasaMensual) {
        super(titular, saldo);
        this.tasaMensual = tasaMensual;
    }

    public CuentaAhorros(String titular) {
        // utiliza el constructor principal el saldo mensual es igual a 0 y la tasa mensual es 0.01
        this(titular, 0, 0.01);
    }

    @Override /** Le permite a las Cuentas de ahorro poder retirar solo si el dinero esta disponible en la cuenta */
    protected boolean puedeRetirar(double monto) {
        return monto <= getSaldo();
    }

    @Override/** Calcula el interes de una cuenta de ahorros en base a su saldo y la cantidad de la tasaMensual de interes*/
    public double calcularInteres() {
        return getSaldo() * tasaMensual;
    }

    @Override
    public String getTipo() {
        return "Ahorros";
    }
}
