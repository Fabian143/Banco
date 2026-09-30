package banco;

import java.util.ArrayList;
/** Entidad que maneja las cuentas de un banco */
public abstract class Cuenta {
    \\ el contador es un atributo static ya que este solo va ser usado para hacer operaciones dentro de la clase
    private static int contador = 1;

    //el numero de cuenta es autoasignable y utiliza a la variable contador
    private final String numero;
    private final String titular;
    private double saldo;

    private final ArrayList<Movimiento> movimientos =new ArrayList<>();

    public Cuenta(String titular, double saldo) {
        
        if (titular == null || titular.trim().isEmpty()) {
            throw new IllegalArgumentException("El titular es obligatorio");
        }
        
        if (saldo < 0) {
            throw new IllegalArgumentException("El saldo inicial no puede ser negativo");
        }
        
        this.numero = String.valueOf(contador);
        contador++;
        this.titular = titular;
        this.saldo = saldo;
    }

    /** Método que le permite a una cuenta ingresar dinero*/
    public void depositar(double monto) {
        validarMonto(monto);
        saldo += monto;
        movimientos.add(new Movimiento("DEPÓSITO", monto));
    }
    
    /** método que le permite a una cuenta validar que el monto no es negativo */
    private void validarMonto(double monto) {
        if (monto <= 0) {
            throw new IllegalArgumentException( "El monto debe ser positivo");
        }
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
    /** Método que le permite a las clases hijas especificas de Cuenta poder retirar dinero cada una a su manera */
    protected abstract boolean puedeRetirar(double monto);

    /** Método que permite obtener el tipo de cuenta de las clases hijas */
    public abstract String getTipo();

    @Override /** Método que permite obtener la información de un objeto de la clase en un String */
    public String toString() {
        return "\n Cuenta:"+numero+"\n Tipo: "+getTipo()+"\n Titular: "+titular+" \nSaldo: "+saldo+"\n";
    }

    
    public ArrayList<Movimiento> getMovimientos() {
        return movimientos;
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
}

