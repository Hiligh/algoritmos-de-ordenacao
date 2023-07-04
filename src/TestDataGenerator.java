import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class TestDataGenerator {

    private static final int MIN_SIZE = 200000;
    private static final int MAX_SIZE = 1000000;
    private static final int SIZE_INCREMENT = 200000;
    private static final String FILE_PATH = "/home/breno/Desktop/arquivos/"; // Especifique o caminho desejado para salvar os arquivos

    public static void main(String[] args) {
        generateTestData(DataMassType.UNORDERED);
        generateTestData(DataMassType.SORTED_ASCENDING);
        generateTestData(DataMassType.SORTED_DESCENDING);
        generateTestData(DataMassType.CONSTANT);
        generateTestData(DataMassType.PARTIALLY_SORTED_ASCENDING_RANDOM_END);
        generateTestData(DataMassType.PARTIALLY_SORTED_ASCENDING_RANDOM_START);
    }

    private static void generateTestData(DataMassType massType) {
        for (int size = MIN_SIZE; size <= MAX_SIZE; size += SIZE_INCREMENT) {
            int[] data = generateData(massType, size);
            String filePath = FILE_PATH + massType.getFileName() + "_" + size + ".txt";
            saveDataToFile(data, filePath);
        }
    }

    private static int[] generateData(DataMassType massType, int size) {
        int[] data = new int[size];
        Random random = new Random();

        switch (massType) {
            case UNORDERED:
                for (int i = 0; i < size; i++) {
                    data[i] = random.nextInt();
                }
                break;
            case SORTED_ASCENDING:
                for (int i = 0; i < size; i++) {
                    data[i] = i;
                }
                break;
            case SORTED_DESCENDING:
                for (int i = 0; i < size; i++) {
                    data[i] = size - i;
                }
                break;
            case CONSTANT:
                Arrays.fill(data, 42); // Preenche todo o array com o valor 42
                break;
            case PARTIALLY_SORTED_ASCENDING_RANDOM_END:
                int partialSize = (int) (size * 0.9);
                for (int i = 0; i < partialSize; i++) {
                    data[i] = i;
                }
                for (int i = partialSize; i < size; i++) {
                    data[i] = random.nextInt();
                }
                break;
            case PARTIALLY_SORTED_ASCENDING_RANDOM_START:
                partialSize = (int) (size * 0.9);
                for (int i = 0; i < partialSize; i++) {
                    data[i] = random.nextInt();
                }
                for (int i = partialSize; i < size; i++) {
                    data[i] = i;
                }
                break;
        }

        return data;
    }

    private static void saveDataToFile(int[] data, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (int i = 0; i < data.length; i++) {
                writer.write(Integer.toString(data[i]));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private enum DataMassType {
        UNORDERED("unordered"),
        SORTED_ASCENDING("sorted_ascending"),
        SORTED_DESCENDING("sorted_descending"),
        CONSTANT("constant"),
        PARTIALLY_SORTED_ASCENDING_RANDOM_END("partially_sorted_ascending_random_end"),
        PARTIALLY_SORTED_ASCENDING_RANDOM_START("partially_sorted_ascending_random_start");

        private final String fileName;

        DataMassType(String fileName) {
            this.fileName = fileName;
        }

        public String getFileName() {
            return fileName;
        }
    }
}
