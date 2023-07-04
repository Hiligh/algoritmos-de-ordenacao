import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class QuickSort {

    public static void main(String[] args) {
        ordenaQuick("partially_sorted_ascending_random_end_200000.txt", "partially_sorted_ascending_random_end_200000_ordenado.txt");
        ordenaQuick("partially_sorted_ascending_random_end_400000.txt", "partially_sorted_ascending_random_end_400000_ordenado.txt");
        ordenaQuick("partially_sorted_ascending_random_end_600000.txt", "partially_sorted_ascending_random_end_600000_ordenado.txt");
        ordenaQuick("partially_sorted_ascending_random_end_800000.txt", "partially_sorted_ascending_random_end_800000_ordenado.txt");
        ordenaQuick("partially_sorted_ascending_random_end_1000000.txt", "partially_sorted_ascending_random_end_1000000_ordenado.txt");
        ordenaQuick("partially_sorted_ascending_random_start_200000.txt", "partially_sorted_ascending_random_start_200000_ordenado.txt");
        ordenaQuick("partially_sorted_ascending_random_start_400000.txt", "partially_sorted_ascending_random_start_400000_ordenado.txt");
        ordenaQuick("partially_sorted_ascending_random_start_600000.txt", "partially_sorted_ascending_random_start_600000_ordenado.txt");
        ordenaQuick("partially_sorted_ascending_random_start_800000.txt", "partially_sorted_ascending_random_start_800000_ordenado.txt");
        ordenaQuick("partially_sorted_ascending_random_start_1000000.txt", "partially_sorted_ascending_random_start_1000000_ordenado.txt");
        ordenaQuick("sorted_ascending_200000.txt", "sorted_ascending_200000_ordenado.txt");
        ordenaQuick("sorted_ascending_400000.txt", "sorted_ascending_400000_ordenado.txt");
        ordenaQuick("sorted_ascending_600000.txt", "sorted_ascending_600000_ordenado.txt");
        ordenaQuick("sorted_ascending_800000.txt", "sorted_ascending_800000_ordenado.txt");
        ordenaQuick("sorted_ascending_1000000.txt", "sorted_ascending_1000000_ordenado.txt");
        ordenaQuick("sorted_descending_200000.txt", "sorted_descending_200000_ordenado.txt");
        ordenaQuick("sorted_descending_400000.txt", "sorted_descending_400000_ordenado.txt");
        ordenaQuick("sorted_descending_600000.txt", "sorted_descending_600000_ordenado.txt");
        ordenaQuick("sorted_descending_800000.txt", "sorted_descending_800000_ordenado.txt");
        ordenaQuick("sorted_descending_1000000.txt", "sorted_descending_1000000_ordenado.txt");
        ordenaQuick("unordered_200000.txt", "unordered_200000_ordenado.txt");
        ordenaQuick("unordered_400000.txt", "unordered_400000_ordenado.txt");
        ordenaQuick("unordered_600000.txt", "unordered_600000_ordenado.txt");
        ordenaQuick("unordered_800000.txt", "unordered_800000_ordenado.txt");
        ordenaQuick("unordered_1000000.txt", "unordered_1000000_ordenado.txt");
    }

    private static void ordenaQuick(String inputFileName, String outputFileName) {
        long startTime, endTime, duration;

        startTime = System.currentTimeMillis();
        int[] array = readDataFromFile(inputFileName);
        quickSort(array, 0, array.length - 1);
        endTime = System.currentTimeMillis();
        duration = endTime - startTime;

        System.out.println("Tempo de execução para " + inputFileName + ": " + duration + " ms");

        writeDataToFile(array, outputFileName);

        // Registra o tempo de execução no arquivo CSV
        writeTimeToCSV(inputFileName, duration);
    }

    private static int[] readDataFromFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int index = 0;
            int[] array = new int[getFileSize(filePath)];

            while ((line = reader.readLine()) != null) {
                array[index] = Integer.parseInt(line);
                index++;
            }

            return array;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new int[0];
    }

    private static int getFileSize(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            int size = 0;
            while (reader.readLine() != null) {
                size++;
            }
            return size;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return 0;
    }

    private static void quickSort(int[] array, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(array, low, high);
            quickSort(array, low, pivotIndex - 1);
            quickSort(array, pivotIndex + 1, high);
        }
    }

    private static int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (array[j] < pivot) {
                i++;
                swap(array, i, j);
            }
        }

        swap(array, i + 1, high);
        return i + 1;
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private static void writeDataToFile(int[] array, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (int value : array) {
                writer.write(String.valueOf(value));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeTimeToCSV(String inputFileName, long duration) {
        String csvFilePath = "/home/breno/Desktop/resultFinal/QuickSortExecution_times.csv";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFilePath, true))) {
            writer.write(inputFileName + "," + duration + " ms");
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
