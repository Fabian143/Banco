package banco;

import java.util.Scanner;

public class Main {

    private static final Scanner sc =
            new Scanner(System.in);

    private static final Banco banco =
            new Banco();

    public static void main(String[] args) {

        int opcion;

        do {

            System.out.println("\n=== BANCO POO ===");
            System.out.println("1. Abrir cuenta");
            System.out.println("2. Depositar");
            System.out.println("3. Retirar");
            System.out.println("4. Listar cuentas");
            System.out.println("5. Transferir");
            System.out.println("6. Abonar intereses");
            System.out.println("0. Salir");

            opcion = leerEntero("Opción: ");

            try {

                switch (opcion) {

                    case 1 -> abrirCuenta();

                    case 2 -> depositar();

                    case 3 -> retirar();

                    case 4 -> listar();

                    case 5 -> transferir();

                    case 6 -> abonarIntereses();

                    case 0 ->
                            System.out.println(
                                    "¡Hasta pronto!"
                            );

                    default ->
                            System.out.println(
                                    "Opción no válida"
                            );
                }

            } catch (
                    IllegalArgumentException |
                    IllegalStateException e) {

                System.out.println(
                        "Error: " + e.getMessage()
                );
            }

        } while (opcion != 0);
    }

    private static String leerTexto(String mensaje) {

        System.out.print(mensaje);

        return sc.nextLine().trim();
    }

    private static int leerEntero(String mensaje) {

        while (true) {

            try {
                return Integer.parseInt(
                        leerTexto(mensaje)
                );

            } catch (NumberFormatException e) {

                System.out.println(
                        "Escribe un número entero."
                );
            }
        }
    }

    private static double leerDouble(String mensaje) {

        while (true) {

            try {
                return Double.parseDouble(
                        leerTexto(mensaje)
                );

            } catch (NumberFormatException e) {

                System.out.println(
                        "Escribe un número válido."
                );
            }
        }
    }

    private static void abrirCuenta() {

        String titular =
                leerTexto("Titular: ");

        int tipo = leerEntero(
                "Tipo (1 = Ahorros, 2 = Corriente, 3 = Nómina): "
        );

        double saldo =
                leerDouble("Saldo inicial: ");

        Cuenta cuenta;

        if (tipo == 1) {

            cuenta = new CuentaAhorros(
                    titular,
                    saldo,
                    0.01
            );

        } else if (tipo == 2) {

            cuenta = new CuentaCorriente(
                    titular,
                    saldo,
                    500000
            );

        } else if (tipo == 3) {

            cuenta = new CuentaNomina(
                    titular,
                    saldo
            );

        } else {

            throw new IllegalArgumentException(
                    "Tipo de cuenta no válido"
            );
        }

        banco.agregar(cuenta);

        System.out.println(
                "Cuenta creada: " +
                cuenta.getNumero()
        );
    }

    private static void depositar() {

        String numero =
                leerTexto("Número de cuenta: ");

        double monto =
                leerDouble("Monto a depositar: ");

        Cuenta cuenta =
                banco.buscar(numero);

        cuenta.depositar(monto);

        System.out.printf(
                "Depósito realizado. Nuevo saldo: $%,.0f%n",
                cuenta.getSaldo()
        );
    }

    private static void retirar() {

        String numero =
                leerTexto("Número de cuenta: ");

        double monto =
                leerDouble("Monto a retirar: ");

        Cuenta cuenta =
                banco.buscar(numero);

        cuenta.retirar(monto);

        System.out.printf(
                "Retiro realizado. Nuevo saldo: $%,.0f%n",
                cuenta.getSaldo()
        );
    }

    private static void transferir() {

        String origen =
                leerTexto("Cuenta origen: ");

        String destino =
                leerTexto("Cuenta destino: ");

        double monto =
                leerDouble("Monto a transferir: ");

        banco.transferir(
                origen,
                destino,
                monto
        );

        System.out.println(
                "Transferencia realizada correctamente."
        );
    }

    private static void abonarIntereses() {

        banco.abonarIntereses();

        System.out.println(
                "Intereses abonados correctamente."
        );
    }

    private static void listar() {

        if (banco.getCuentas().isEmpty()) {

            System.out.println(
                    "No hay cuentas registradas."
            );

            return;
        }

        for (Cuenta c : banco.getCuentas()) {

            System.out.println(c);

            if (c instanceof Rentable r) {
            	System.out.println("\n Interés calculado : "+ r.calcularInteres());
            }

            System.out.println(
                    " Movimientos:"
            );

            for (Movimiento m : c.getMovimientos()) {

                System.out.println(
                        "   " + m
                );
            }

            System.out.println();
        }

        System.out.printf(
                "Total en el banco: $%,.0f%n",
                banco.totalDepositado()
        );
    }
}

