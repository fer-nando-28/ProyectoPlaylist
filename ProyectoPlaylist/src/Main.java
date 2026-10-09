import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ListaDobleCircular playlist = new ListaDobleCircular();
        int opcion = 0;

        while (opcion != 13) {
            System.out.println("\n=== GESTOR DE PLAYLIST CIRCULAR ===");
            System.out.println("1. Agregar al inicio");
            System.out.println("2. Agregar al final");
            System.out.println("3. Buscar por ID");
            System.out.println("4. Seleccionar actual por ID");
            System.out.println("5. Eliminar por ID");
            System.out.println("6. Eliminar canción actual");
            System.out.println("7. Consultar actual");
            System.out.println("8. Avanzar canción");
            System.out.println("9. Retroceder canción");
            System.out.println("10. Mostrar lista (Adelante y Atrás)");
            System.out.println("11. Consultar cantidad de canciones");
            System.out.println("12. Simular reproducción (k veces)");
            System.out.println("13. Salir");
            System.out.print("Elige una opción: ");
            
            try {
                opcion = Integer.parseInt(scanner.nextLine());
                procesarOpcion(opcion, playlist, scanner);
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingresa un número válido.");
            }
        }
        scanner.close();
    }

    private static void procesarOpcion(int opcion, ListaDobleCircular playlist, Scanner scanner) {
        switch (opcion) {
            case 1:
            case 2:
                System.out.print("Título: "); String t = scanner.nextLine();
                System.out.print("Artista: "); String a = scanner.nextLine();
                System.out.print("Duración (s): "); int d = Integer.parseInt(scanner.nextLine());
                if (opcion == 1) playlist.agregarInicio(new Cancion(t, a, d));
                else playlist.agregarFinal(new Cancion(t, a, d));
                break;
            case 3:
                System.out.print("ID a buscar: ");
                playlist.buscarPorId(Integer.parseInt(scanner.nextLine()));
                break;
            case 4:
                System.out.print("ID a seleccionar como actual: ");
                playlist.seleccionarPorId(Integer.parseInt(scanner.nextLine()));
                break;
            case 5:
                System.out.print("ID a eliminar: ");
                playlist.eliminarPorId(Integer.parseInt(scanner.nextLine()));
                break;
            case 6:
                playlist.eliminarActual();
                break;
            case 7:
                playlist.mostrarActual();
                break;
            case 8:
                playlist.avanzar();
                break;
            case 9:
                playlist.retroceder();
                break;
            case 10:
                playlist.mostrarAdelante();
                System.out.println();
                playlist.mostrarAtras();
                break;
            case 11:
                System.out.println("Total de canciones: " + playlist.getTamano());
                break;
            case 12:
                System.out.print("Cantidad de reproducciones (k): ");
                int k = Integer.parseInt(scanner.nextLine());
                if(k > 0) playlist.simularReproduccion(k);
                break;
            case 13:
                System.out.println("Cerrando reproductor...");
                break;
            default:
                System.out.println("Opción no válida.");
        }
    }
}