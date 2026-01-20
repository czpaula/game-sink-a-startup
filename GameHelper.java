import java.util.*;

public class GameHelper {
    private static final String alphabet = "ABCDEFG";
    private static final int gridLength = 7;
    private static final int gridSize = 49;
    private static final int maxAttempts = 200;
    static final int horizontalIncrement = 1;
    static final int verticalIncrement = gridLength;

    private int[] grid = new int[gridSize];
    private final Random random = new Random();
    private int startupCount = 0;

    public String getUserInput(String prompt) {
        System.out.print(prompt + " ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine().toUpperCase();
    }
    public ArrayList<String> placeStartup(int startupSize) {
        //Armazena o índice para a grade (0-48)
        int[] startupCoords = new int[startupSize];
        int attempts = 0;
        boolean success = false;

        startupCount++;
        int increment = getIncrement();

        while (!success & attempts++ < maxAttempts) {
            int location = random.nextInt(gridSize);

            for (int i = 0; i < startupCoords.length; i++) {
                startupCoords[i] = location;
                location += increment;
            }

            // System.out.println("Tentando a localização: " + Arrays.toString(startupCoords));

            if (startupFits(startupCoords, increment)) {
                success = coordsAvailable(startupCoords);
            }
        }
        savePositionToGrid(startupCoords);
        ArrayList<String> alphaCells = convertCoordsToAlphaFormat(startupCoords);
        System.out.println("Localização atribuída: " + alphaCells);
        return alphaCells;
        }

        private boolean startupFits(int[] startuCoords, int increment) {
            int finalLocation = startuCoords[startuCoords.length - 1];
            if (increment == horizontalIncrement) {
                // Verifica se está na mesma linha
                return calcRowFromIndex(startuCoords[0]) == calcRowFromIndex(finalLocation);
            } else {
                return finalLocation < gridSize; // Verifica se está dentro dos limites verticais
            }
               
        }

        private boolean coordsAvailable(int[] startupCoords) {
            for (int coord : startupCoords) {
                if (grid[coord] != 0) {
                    return false;
                }
            }
            return true;
        }

        private void savePositionToGrid(int[] startupCoords) {
            for (int index : startupCoords) {
                grid[index] = 1;
            }
        }

        private ArrayList<String> convertCoordsToAlphaFormat(int[] startupCoords) {
            ArrayList<String> alphaCells = new ArrayList<String>();
            for (int index : startupCoords) {
                String alphaCoords = getAlphaCoordFromIndex(index);
                alphaCells.add(alphaCoords);
            }
            return alphaCells;
        }

        private String getAlphaCoordFromIndex(int index) {
            int row = calcRowFromIndex(index);
            int column = index % gridLength;
            String letter = alphabet.substring(column, column + 1);
            return letter + row;
        }

        private int calcRowFromIndex(int index) {
            return index / gridLength;
        
        }

        private int getIncrement() {
            if ((startupCount % 2) == 0) {
                return horizontalIncrement; // Horizontal
            } else {
                return verticalIncrement; // Vertical
            }
        }
    
}
