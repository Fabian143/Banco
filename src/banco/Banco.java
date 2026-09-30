package banco;

import java.util.ArrayList;
/** Entidad que maneja el Banco en si aqui es donde estan todas las cuentas sin importar su tipo */
public class Banco {

    private final ArrayList<Cuenta> cuentas = new ArrayList<>();
    /** Metodo que permite agregar una cuenta al ArrayList de cuentas */
    public void agregar(Cuenta cuenta) {
        if (cuenta == null) {
            throw new IllegalArgumentException("La cuenta no puede ser null");
        }
        cuentas.add(cuenta);
    }
/** Metodo que permite buscar una cuenta en el ArrayList de cuentas */
    public Cuenta buscar(String numero) {
        for (Cuenta c : cuentas) {
            if (c.getNumero().equalsIgnoreCase(numero)) {
                return c;
            }
        }
        throw new IllegalArgumentException( "No existe la cuenta " + numero);
    }

    public ArrayList<Cuenta> getCuentas() {
        return cuentas;
    }

    public double totalDepositado() {
        double total = 0;

        for (Cuenta c : cuentas) {
            total += c.getSaldo();
        }

        return total;
    }
    /** Método que permite transferir de una cuenta a otra*/
    public void transferir(String origen,String destino,double monto) {

        Cuenta cuentaOrigen = buscar(origen);
        Cuenta cuentaDestino = buscar(destino);

        if (origen.equalsIgnoreCase(destino)) {
            throw new IllegalArgumentException("La cuenta de origen y destino deben ser diferentes");
        }

        cuentaOrigen.retirar(monto);
        cuentaDestino.depositar(monto);

        cuentaOrigen.getMovimientos().add(new Movimiento( "TRANSFERENCIA ENVIADA -> " + destino, monto));

        cuentaDestino.getMovimientos().add(new Movimiento(TRANSFERENCIA RECIBIDA <- " + origen,monto));
    }
    /** Cuenta que permite aplicar el interes de las cuentas*/
    public void abonarIntereses() {

        for (Cuenta cuenta : cuentas) {

            if (cuenta instanceof Rentable rentable) {

                double interes = rentable.calcularInteres();

                if (interes > 0) {
                    cuenta.depositar(interes);
                    cuenta.getMovimientos().add(new Movimiento("INTERÉS ABONADO",interes
                            )
                    );
                }
            }
        }
    }
}
