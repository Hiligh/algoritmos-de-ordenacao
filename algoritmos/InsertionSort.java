import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class InsertionSort {

    public static void main(String[] args) {
        ordenaçaoInsertion("partially_sorted_ascending_random_end_200000.txt", "partially_sorted_ascending_random_end_200000_ordenado.txt");
        ordenaçaoInsertion("partially_sorted_ascending_random_end_400000.txt", "partially_sorted_ascending_random_end_400000_ordenado.txt");
        ordenaçaoInsertion("partially_sorted_ascending_random_end_600000.txt", "partially_sorted_ascending_random_end_600000_ordenado.txt");
        ordenaçaoInsertion("partially_sorted_ascending_random_end_800000.txt", "partially_sorted_ascending_random_end_800000_ordenado.txt");
        ordenaçaoInsertion("partially_sorted_ascending_random_end_1000000.txt", "partially_sorted_ascending_random_end_1000000_ordenado.txt");
        ordenaçaoInsertion("partially_sorted_ascending_random_start_200000.txt", "partially_sorted_ascending_random_start_200000_ordenado.txt");
        ordenaçaoInsertion("partially_sorted_ascending_random_start_400000.txt", "partially_sorted_ascending_random_start_400000_ordenado.txt");
        ordenaçaoInsertion("partially_sorted_ascending_random_start_600000.txt", "partially_sorted_ascending_random_start_600000_ordenado.txt");
        ordenaçaoInsertion("partially_sorted_ascending_random_start_800000.txt", "partially_sorted_ascending_random_start_800000_ordenado.txt");
        ordenaçaoInsertion("partially_sorted_ascending_random_start_1000000.txt", "partially_sorted_ascending_random_start_1000000_ordenado.txt");
        ordenaçaoInsertion("sorted_ascending_200000.txt", "sorted_ascending_200000_ordenado.txt");
        ordenaçaoInsertion("sorted_ascending_400000.txt", "sorted_ascending_400000_ordenado.txt");
        ordenaçaoInsertion("sorted_ascending_600000.txt", "sorted_ascending_600000_ordenado.txt");
        ordenaçaoInsertion("sorted_ascending_800000.txt", "sorted_ascending_800000_ordenado.txt");
        ordenaçaoInsertion("sorted_ascending_1000000.txt", "sorted_ascending_1000000_ordenado.txt");
        ordenaçaoInsertion("sorted_descending_200000.txt", "sorted_descending_200000_ordenado.txt");
        ordenaçaoInsertion("sorted_descending_400000.txt", "sorted_descending_400000_ordenado.txt");
        ordenaçaoInsertion("sorted_descending_600000.txt", "sorted_descending_600000_ordenado.txt");
        ordenaçaoInsertion("sorted_descending_800000.txt", "sorted_descending_800000_ordenado.txt");
        ordenaçaoInsertion("sorted_descending_1000000.txt", "sorted_descending_1000000_ordenado.txt");
        ordenaçaoInsertion("unordered_200000.txt", "unordered_200000_ordenado.txt");
        ordenaçaoInsertion("unordered_400000.txt", "unordered_400000_ordenado.txt");
        ordenaçaoInsertion("unordered_600000.txt", "unordered_600000_ordenado.txt");
        ordenaçaoInsertion("unordered_800000.txt", "unordered_800000_ordenado.txt");
        ordenaçaoInsertion("unordered_1000000.txt", "unordered_1000000_ordenado.txt");
    }

    private static void ordenaçaoInsertion(String inputFileName, String outputFileName) {
        long startTime, endTime, duration;

        startTime = System.currentTimeMillis();
        insertionSortCSV(inputFileName, outputFileName);
        endTime = System.currentTimeMillis();
        duration = endTime - startTime;
        System.out.println("Tempo de execução para " + inputFileName + ": " + duration + " ms");

        // Registra o tempo de execução no arquivo CSV
        writeTimeToCSV(inputFileName, duration);
    }

    private static void insertionSortCSV(String inputPath, String outputPath) {
        int[] values = readCSV(inputPath);
        insertionSort(values);
        writeCSV(outputPath, values);
    }

    private static int[] readCSV(String filePath) {
        int[] values = new int[0];

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                values = Arrays.copyOf(values, values.length + 1);
                values[values.length - 1] = Integer.parseInt(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return values;
    }

    private static void writeCSV(String filePath, int[] values) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (int value : values) {
                writer.write(Integer.toString(value));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void insertionSort(int[] values) {
        int n = values.length;

        for (int i = 1; i < n; i++) {
            int key = values[i];
            int j = i - 1;

            while (j >= 0 && values[j] > key) {
                values[j + 1] = values[j];
                j--;
            }

            values[j + 1] = key;
        }
    }

    private static void writeTimeToCSV(String inputFileName, long duration) {
        String csvFilePath = "/home/breno/Desktop/resultFinal/InsertionSortExecution_times.csv";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFilePath, true))) {
            writer.write(inputFileName + "," + duration + " ms");
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
