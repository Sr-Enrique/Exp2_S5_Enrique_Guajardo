package teatromoroii;

import java.util.ArrayList;
import java.util.Scanner;

public class TeatroMoroII {

    // Clase interna para representar una entrada
    static class Entrada {
        int id;
        int edad;
        String ubicacion;
        String tipoUsuario;
        int tarifaFinal;

        public Entrada(int id, int edad, String ubicacion, String tipoUsuario, int tarifaFinal) {
            this.id = id;
            this.edad = edad;
            this.ubicacion = ubicacion;
            this.tipoUsuario = tipoUsuario;
            this.tarifaFinal = tarifaFinal;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Entrada> entradas = new ArrayList<>();
        int totalIngresos = 0;
        int idContador = 0;
        boolean continuar = true;

        while (continuar) {
            System.out.println("Bienvenido a Teatro Moro");
            System.out.println("\nSeleccione ubicacion:");
            System.out.println("1. VIP..........$30.000");
            System.out.println("2. Platea Baja..$18.000");
            System.out.println("3. Platea Alta..$15.000");
            System.out.println("4. Palco........$ 9.000");
            System.out.println("5. Salir");
            System.out.print("Ingrese Opcion: ");
            int opcion = leerEntero(scanner);

            if (opcion >= 1 && opcion <= 4) {
                String ubicacion = "";
                int tarifaBase = 0;
                switch (opcion) {
                    case 1 -> { ubicacion = "VIP"; tarifaBase = 30000; }
                    case 2 -> { ubicacion = "Platea Baja"; tarifaBase = 18000; }
                    case 3 -> { ubicacion = "Platea Alta"; tarifaBase = 15000; }
                    case 4 -> { ubicacion = "Palco"; tarifaBase = 9000; }
                }

                System.out.print("Ingrese su edad: ");
                int edad = leerEntero(scanner);

                String tipoUsuario = "General";
                int descuento = 0;
                if (edad < 18) {
                    tipoUsuario = "Estudiante";
                    descuento = (int)(tarifaBase * 0.10);
                } else if (edad > 64) {
                    tipoUsuario = "Adulto Mayor";
                    descuento = (int)(tarifaBase * 0.15);
                }
                int tarifaFinal = tarifaBase - descuento;

                Entrada entrada = new Entrada(idContador++, edad, ubicacion, tipoUsuario, tarifaFinal);
                entradas.add(entrada);
                totalIngresos += tarifaFinal;

                System.out.println("Entrada registrada. Precio final: $" + tarifaFinal);
                System.out.print("Desea comprar otra entrada? (s/n): ");
                String resp = scanner.nextLine();
                if (!resp.toLowerCase().equals("s")) {
                    continuar = false;
                }

            } else if (opcion == 5) {
                continuar = false;
            } else {
                System.out.println("Opcion invalida.");
            }
        }

        // Eliminar entradas por ID antes del resumen
        if (!entradas.isEmpty()) {
            System.out.print("\nDesea eliminar alguna entrada antes de continuar? (s/n): ");
            String respuestaEliminar = scanner.nextLine();
            while (respuestaEliminar.equalsIgnoreCase("s") && !entradas.isEmpty()) {
                System.out.println("Entradas registradas:");
                for (Entrada e : entradas) {
                    System.out.println("ID " + e.id + ": " + e.ubicacion + " - " + e.tipoUsuario + " - $" + e.tarifaFinal);
                }
                System.out.print("Ingrese el ID de la entrada a eliminar: ");
                int idEliminar = leerEntero(scanner);

                boolean eliminada = false;
                for (int i = 0; i < entradas.size(); i++) {
                    if (entradas.get(i).id == idEliminar) {
                        System.out.println("Entrada eliminada: " + entradas.get(i).ubicacion + " - $" + entradas.get(i).tarifaFinal);
                        totalIngresos -= entradas.get(i).tarifaFinal;
                        entradas.remove(i);
                        eliminada = true;
                        break;
                    }
                }
                if (!eliminada) {
                    System.out.println("ID no encontrado.");
                }

                if (!entradas.isEmpty()) {
                    System.out.print("Desea eliminar otra entrada? (s/n): ");
                    respuestaEliminar = scanner.nextLine();
                } else {
                    System.out.println("No quedan entradas.");
                }
            }
        }

        // Mostrar resumen final
        System.out.println("\nResumen de compra:");
        for (Entrada e : entradas) {
            System.out.println("ID " + e.id + ": " + e.ubicacion + " - " + e.tipoUsuario + " - $" + e.tarifaFinal);
        }
        System.out.println("Total a pagar: $" + totalIngresos);
        System.out.println("Gracias por su compra. Disfrute la funcion");
    }

    // Control de errores
    public static int leerEntero(Scanner scanner) {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Ingrese un numero valido: ");
            }
        }
    }
}