import java.io.*;
import java.util.Arrays;

public class CountingSort {
    public static void main(String[] args) {
        ordenaçaoCounting("partially_sorted_ascending_random_end_200000.txt", "partially_sorted_ascending_random_end_200000_ordenado.txt");
        ordenaçaoCounting("partially_sorted_ascending_random_end_400000.txt", "partially_sorted_ascending_random_end_400000_ordenado.txt");
        ordenaçaoCounting("partially_sorted_ascending_random_end_600000.txt", "partially_sorted_ascending_random_end_600000_ordenado.txt");
        ordenaçaoCounting("partially_sorted_ascending_random_end_800000.txt", "partially_sorted_ascending_random_end_800000_ordenado.txt");
        ordenaçaoCounting("partially_sorted_ascending_random_end_1000000.txt", "partially_sorted_ascending_random_end_1000000_ordenado.txt");
        ordenaçaoCounting("partially_sorted_ascending_random_start_200000.txt", "partially_sorted_ascending_random_start_200000_ordenado.txt");
        ordenaçaoCounting("partially_sorted_ascending_random_start_400000.txt", "partially_sorted_ascending_random_start_400000_ordenado.txt");
        ordenaçaoCounting("partially_sorted_ascending_random_start_600000.txt", "partially_sorted_ascending_random_start_600000_ordenado.txt");
        ordenaçaoCounting("partially_sorted_ascending_random_start_800000.txt", "partially_sorted_ascending_random_start_800000_ordenado.txt");
        ordenaçaoCounting("partially_sorted_ascending_random_start_1000000.txt", "partially_sorted_ascending_random_start_1000000_ordenado.txt");
        ordenaçaoCounting("sorted_ascending_200000.txt", "sorted_ascending_200000_ordenado.txt");
        ordenaçaoCounting("sorted_ascending_400000.txt", "sorted_ascending_400000_ordenado.txt");
        ordenaçaoCounting("sorted_ascending_600000.txt", "sorted_ascending_600000_ordenado.txt");
        ordenaçaoCounting("sorted_ascending_800000.txt", "sorted_ascending_800000_ordenado.txt");
        ordenaçaoCounting("sorted_ascending_1000000.txt", "sorted_ascending_1000000_ordenado.txt");
        ordenaçaoCounting("sorted_descending_200000.txt", "sorted_descending_200000_ordenado.txt");
        ordenaçaoCounting("sorted_descending_400000.txt", "sorted_descending_400000_ordenado.txt");
        ordenaçaoCounting("sorted_descending_600000.txt", "sorted_descending_600000_ordenado.txt");
        ordenaçaoCounting("sorted_descending_800000.txt", "sorted_descending_800000_ordenado.txt");
        ordenaçaoCounting("sorted_descending_1000000.txt", "sorted_descending_1000000_ordenado.txt");
        ordenaçaoCounting("unordered_200000.txt", "unordered_200000_ordenado.txt");
        ordenaçaoCounting("unordered_400000.txt", "unordered_400000_ordenado.txt");
        ordenaçaoCounting("unordered_600000.txt", "unordered_600000_ordenado.txt");
        ordenaçaoCounting("unordered_800000.txt", "unordered_800000_ordenado.txt");
        ordenaçaoCounting("unordered_1000000.txt", "unordered_1000000_ordenado.txt");
    }

    private static void ordenaçaoCounting(String inputFileName, String outputFileName) {
        long startTime, endTime, duration;

        startTime = System.currentTimeMillis();
        countingSortCSV(inputFileName, outputFileName);
        endTime = System.currentTimeMillis();
        duration = endTime - startTime;
        System.out.println("Tempo de execução para " + inputFileName + ": " + duration + " ms");

        // Registra o tempo de execução no arquivo CSV
        writeTimeToCSV(inputFileName, duration);
    }

    private static void countingSortCSV(String inputPath, String outputPath) {
        int[] values = readCSV(inputPath);
        countingSort(values);
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

    private static void countingSort(int[] values) {
        if (values.length <= 1) {
            return;
        }

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int value : values) {
            if (value < min) {
                min = value;
            }
            if (value > max) {
                max = value;
            }
        }

        int[] countArray = new int[max - min + 1];

        for (int value : values) {
            countArray[value - min]++;
        }

        int k = 0;
        for (int i = 0; i < countArray.length; i++) {
            while (countArray[i] > 0) {
                values[k] = i + min;
                k++;
                countArray[i]--;
            }
        }
    }

    private static void writeTimeToCSV(String inputFileName, long duration) {
        String csvFilePath = "/home/breno/Desktop/resultFinal/CountingSortExecution_times.csv";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFilePath, true))) {
            writer.write(inputFileName + "," + duration + " ms");
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
