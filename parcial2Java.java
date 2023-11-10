import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class parcial2Java {

    // Row checker
    public static int rowCheck(List<String> dnaMatrix) {
        int totalMutantAdn = 0;
        for (String row : dnaMatrix) {
            int countEqual = 1;
            char prevElement = row.charAt(0);
            for (char element : row.toCharArray()) {
                if (element == prevElement) {
                    countEqual++;
                    if (countEqual == 4) {
                        totalMutantAdn++;
                        break;
                    }
                } else {
                    countEqual = 1;
                }
                prevElement = element;
            }
        }
        return totalMutantAdn;
    }

    // Column checker
    public static int columnCheck(List<String> dnaMatrix) {
        int totalMutantAdn = 0;
        int size = dnaMatrix.size();
        for (int i = 0; i < size; i++) {
            int countEqual = 1;
            char prevElement = dnaMatrix.get(i).charAt(0);
            for (int j = 0; j < size; j++) {
                char element = dnaMatrix.get(j).charAt(i);
                if (element == prevElement) {
                    countEqual++;
                    if (countEqual == 4) {
                        totalMutantAdn++;
                        break;
                    }
                } else {
                    countEqual = 1;
                }
                prevElement = element;
            }
        }
        return totalMutantAdn;
    }

    // Oblique checker
    public static int obliqueCheck(List<String> dnaMatrix) {
        List<String> obliqueList = new ArrayList<>();
        int rows = 6;
        int columns = 6;

        // Diagonals to the right
        for (int sumValue = 3; sumValue < 2 * rows - 1; sumValue++) {
            StringBuilder oblique = new StringBuilder();
            for (int row = Math.max(0, sumValue - columns + 1); row <= Math.min(sumValue, rows - 1); row++) {
                int column = sumValue - row;
                oblique.append(dnaMatrix.get(row).charAt(column));
            }
            if (oblique.length() >= 4) {
                obliqueList.add(oblique.toString());
            }
        }

        // Diagonals to the left
        for (int sumValue = 3; sumValue < 2 * rows - 1; sumValue++) {
            StringBuilder oblique = new StringBuilder();
            for (int row = Math.max(0, sumValue - columns + 1); row <= Math.min(sumValue, rows - 1); row++) {
                int column = columns - 1 - (sumValue - row);
                oblique.append(dnaMatrix.get(row).charAt(column));
            }
            if (oblique.length() >= 4) {
                obliqueList.add(oblique.toString());
            }
        }

        return rowCheck(obliqueList);
    }

    // Main function, isMutant()
    public static boolean isMutant() {
        System.out.println("\nLos caracteres validos para una secuencia de DNA son  ['A','T','C','G']\n");
        List<String> dnaMatrix = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        while (dnaMatrix.size() < 6) {
            boolean validRow = true;
            System.out.print("Ingresa el DNA del mutante para la fila " + (dnaMatrix.size() + 1) +
                    ", en formato 'ATGCGA' o ingresa '0' para salir:\n");
            String dnaRow = scanner.nextLine().toUpperCase();
            System.out.println();
            if (dnaRow.equals("0")) {
                break;
            }
            for (char c : dnaRow.toCharArray()) {
                if (c != 'A' && c != 'T' && c != 'C' && c != 'G') {
                    validRow = false;
                }
            }
            if (validRow && dnaRow.length() == 6) {
                dnaMatrix.add(dnaRow);
            } else {
                System.out.println("Ingresa un DNA valido, con un largo de 6 letras\n");
            }
        }

        if (dnaMatrix.size() > 1) {
            int totalMutantAdn = rowCheck(dnaMatrix) + columnCheck(dnaMatrix) + obliqueCheck(dnaMatrix);
            return totalMutantAdn > 1;
        }
        System.out.println("Gracias por usar el programa :D");
        return false;
    }

    public static void main(String[] args) {
        System.out.println("""
                 __  __                                            _              _
                \\ \\/ /     _ __ ___   ___ _ __     _ __ ___  _   _| |_ __ _ _ __ | |_
                 \\  /_____| '_ ` _ \\ / _ \\ '_ \\   | '_ ` _ \\| | | | __/ _` | '_ \\| __|
                 /  \\_____| | | | | |  __/ | | |  | | | | | | |_| | || (_| | | | | |_
                /_/\\_\\    |_| |_| |_|\\___|_| |_|  |_| |_| |_|\\__,_|\\__\\__,_|_| |_|\\__|
                                                                                      
                     _               _
                 ___| |__   ___  ___| | _____ _ __
                / __| '_ \\ / _ \\/ __| |/ / _ \\ '__|
               | (__| | | |  __/ (__|   <  __/ |
                \\___|_| |_|\\___|\\___|_|\\_\\___|_|
                        
                  Bienvenido a Mutant Checker!
                """);

        boolean output = isMutant();
        System.out.println();
        if (output) {
            System.out.println("El resultado del mutant checker es \"true\"");
        } else {
            System.out.println("El resultado del mutant checker es \"false\"");
        }
    }
}
