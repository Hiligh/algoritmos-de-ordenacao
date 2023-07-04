import java.io.*;
import java.util.Arrays;

public class HeapSort {
    public static void main(String[] args) {
        ordenaçaoHeap("partially_sorted_ascending_random_end_200000.txt", "partially_sorted_ascending_random_end_200000_ordenado.txt");
        ordenaçaoHeap("partially_sorted_ascending_random_end_400000.txt", "partially_sorted_ascending_random_end_400000_ordenado.txt");
        ordenaçaoHeap("partially_sorted_ascending_random_end_600000.txt", "partially_sorted_ascending_random_end_600000_ordenado.txt");
        ordenaçaoHeap("partially_sorted_ascending_random_end_800000.txt", "partially_sorted_ascending_random_end_800000_ordenado.txt");
        ordenaçaoHeap("partially_sorted_ascending_random_end_1000000.txt", "partially_sorted_ascending_random_end_1000000_ordenado.txt");
        ordenaçaoHeap("partially_sorted_ascending_random_start_200000.txt", "partially_sorted_ascending_random_start_200000_ordenado.txt");
        ordenaçaoHeap("partially_sorted_ascending_random_start_400000.txt", "partially_sorted_ascending_random_start_400000_ordenado.txt");
        ordenaçaoHeap("partially_sorted_ascending_random_start_600000.txt", "partially_sorted_ascending_random_start_600000_ordenado.txt");
        ordenaçaoHeap("partially_sorted_ascending_random_start_800000.txt", "partially_sorted_ascending_random_start_800000_ordenado.txt");
        ordenaçaoHeap("partially_sorted_ascending_random_start_1000000.txt", "partially_sorted_ascending_random_start_1000000_ordenado.txt");
        ordenaçaoHeap("sorted_ascending_200000.txt", "sorted_ascending_200000_ordenado.txt");
        ordenaçaoHeap("sorted_ascending_400000.txt", "sorted_ascending_400000_ordenado.txt");
        ordenaçaoHeap("sorted_ascending_600000.txt", "sorted_ascending_600000_ordenado.txt");
        ordenaçaoHeap("sorted_ascending_800000.txt", "sorted_ascending_800000_ordenado.txt");
        ordenaçaoHeap("sorted_ascending_1000000.txt", "sorted_ascending_1000000_ordenado.txt");
        ordenaçaoHeap("sorted_descending_200000.txt", "sorted_descending_200000_ordenado.txt");
        ordenaçaoHeap("sorted_descending_400000.txt", "sorted_descending_400000_ordenado.txt");
        ordenaçaoHeap("sorted_descending_600000.txt", "sorted_descending_600000_ordenado.txt");
        ordenaçaoHeap("sorted_descending_800000.txt", "sorted_descending_800000_ordenado.txt");
        ordenaçaoHeap("sorted_descending_1000000.txt", "sorted_descending_1000000_ordenado.txt");
        ordenaçaoHeap("unordered_200000.txt", "unordered_200000_ordenado.txt");
        ordenaçaoHeap("unordered_400000.txt", "unordered_400000_ordenado.txt");
        ordenaçaoHeap("unordered_600000.txt", "unordered_600000_ordenado.txt");
        ordenaçaoHeap("unordered_800000.txt", "unordered_800000_ordenado.txt");
        ordenaçaoHeap("unordered_1000000.txt", "unordered_1000000_ordenado.txt");
    }
    private static void ordenaçaoHeap(String inputFileName, String outputFileName) {
        long startTime, endTime, duration;

        startTime = System.currentTimeMillis();
        int[] values = readCSV(inputFileName);
        heapSort(values);
        endTime = System.currentTimeMillis();
        duration = endTime - startTime;
        System.out.println("Tempo de execução para " + inputFileName + ": " + duration + " ms");

        // Registra o tempo de execução no arquivo CSV
        writeTimeToCSV(inputFileName, duration);
        writeCSV(outputFileName, values);
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

    private static void heapSort(int[] values) {
        int n = values.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(values, n, i);
        }

        for (int i = n - 1; i > 0; i--) {
            int temp = values[0];
            values[0] = values[i];
            values[i] = temp;

            heapify(values, i, 0);
        }
    }

    private static void heapify(int[] values, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && values[left] > values[largest]) {
            largest = left;
        }

        if (right < n && values[right] > values[largest]) {
            largest = right;
        }

        if (largest != i) {
            int swap = values[i];
            values[i] = values[largest];
            values[largest] = swap;

            heapify(values, n, largest);
        }
    }

    private static void writeTimeToCSV(String inputFileName, long duration) {
        String csvFilePath = "/home/breno/Desktop/resultFinal/HeapSortExecution_times.csv";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFilePath, true))) {
            writer.write(inputFileName + "," + duration + " ms");
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
