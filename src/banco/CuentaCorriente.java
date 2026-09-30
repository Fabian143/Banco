package banco;
/** Entidad que permite manejar las cuentas corrientes en un banco */
public class CuentaCorriente extends Cuenta implements Rentable {

    private final double sobregiro;
    private final double tasaMensual;

    public CuentaCorriente(String titular,double saldo,double sobregiro) {
        super(titular, saldo);

        if (sobregiro < 0) {
            throw new IllegalArgumentException("El sobregiro no puede ser negativo");
        }
        this.sobregiro = sobregiro;
        // la tasa mensual queda fija en:
        this.tasaMensual = 0.1;
    }

    public CuentaCorriente(String titular) {
        //llama al constructor principal y deja el saldo inicialen cero y el sobregiro permitido en 50000
        this(titular, 0, 50000);
    }

    @Override /** Permite retirar en cuentas corrientes baja un sobregiro permitido */
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

