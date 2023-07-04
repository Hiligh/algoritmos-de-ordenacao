import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MergeSort {

    public static void main(String[] args) {
        ordenaçaoMerge("partially_sorted_ascending_random_end_200000.txt", "partially_sorted_ascending_random_end_200000_ordenado.txt");
        ordenaçaoMerge("partially_sorted_ascending_random_end_400000.txt", "partially_sorted_ascending_random_end_400000_ordenado.txt");
        ordenaçaoMerge("partially_sorted_ascending_random_end_600000.txt", "partially_sorted_ascending_random_end_600000_ordenado.txt");
        ordenaçaoMerge("partially_sorted_ascending_random_end_800000.txt", "partially_sorted_ascending_random_end_800000_ordenado.txt");
        ordenaçaoMerge("partially_sorted_ascending_random_end_1000000.txt", "partially_sorted_ascending_random_end_1000000_ordenado.txt");
        ordenaçaoMerge("partially_sorted_ascending_random_start_200000.txt", "partially_sorted_ascending_random_start_200000_ordenado.txt");
        ordenaçaoMerge("partially_sorted_ascending_random_start_400000.txt", "partially_sorted_ascending_random_start_400000_ordenado.txt");
        ordenaçaoMerge("partially_sorted_ascending_random_start_600000.txt", "partially_sorted_ascending_random_start_600000_ordenado.txt");
        ordenaçaoMerge("partially_sorted_ascending_random_start_800000.txt", "partially_sorted_ascending_random_start_800000_ordenado.txt");
        ordenaçaoMerge("partially_sorted_ascending_random_start_1000000.txt", "partially_sorted_ascending_random_start_1000000_ordenado.txt");
        ordenaçaoMerge("sorted_ascending_200000.txt", "sorted_ascending_200000_ordenado.txt");
        ordenaçaoMerge("sorted_ascending_400000.txt", "sorted_ascending_400000_ordenado.txt");
        ordenaçaoMerge("sorted_ascending_600000.txt", "sorted_ascending_600000_ordenado.txt");
        ordenaçaoMerge("sorted_ascending_800000.txt", "sorted_ascending_800000_ordenado.txt");
        ordenaçaoMerge("sorted_ascending_1000000.txt", "sorted_ascending_1000000_ordenado.txt");
        ordenaçaoMerge("sorted_descending_200000.txt", "sorted_descending_200000_ordenado.txt");
        ordenaçaoMerge("sorted_descending_400000.txt", "sorted_descending_400000_ordenado.txt");
        ordenaçaoMerge("sorted_descending_600000.txt", "sorted_descending_600000_ordenado.txt");
        ordenaçaoMerge("sorted_descending_800000.txt", "sorted_descending_800000_ordenado.txt");
        ordenaçaoMerge("sorted_descending_1000000.txt", "sorted_descending_1000000_ordenado.txt");
        ordenaçaoMerge("unordered_200000.txt", "unordered_200000_ordenado.txt");
        ordenaçaoMerge("unordered_400000.txt", "unordered_400000_ordenado.txt");
        ordenaçaoMerge("unordered_600000.txt", "unordered_600000_ordenado.txt");
        ordenaçaoMerge("unordered_800000.txt", "unordered_800000_ordenado.txt");
        ordenaçaoMerge("unordered_1000000.txt", "unordered_1000000_ordenado.txt");
    }

    private static void ordenaçaoMerge(String inputFileName, String outputFileName) {
        long startTime, endTime, duration;

        startTime = System.currentTimeMillis();
        int[] array = readDataFromFile(inputFileName);
        mergeSort(array);
        endTime = System.currentTimeMillis();
        duration = endTime - startTime;
        System.out.println("Tempo de execução para " + inputFileName + ": " + duration + " ms");

        // Registra o tempo de execução no arquivo CSV
        writeTimeToCSV(inputFileName, duration);
        writeDataToFile(array, outputFileName);
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

    private static void mergeSort(int[] array) {
        int n = array.length;

        if (n <= 1) {
            return;
        }

        int mid = n / 2;
        int[] left = new int[mid];
        int[] right = new int[n - mid];

        System.arraycopy(array, 0, left, 0, mid);
        System.arraycopy(array, mid, right, 0, n - mid);

        mergeSort(left);
        mergeSort(right);

        merge(array, left, right);
    }

    private static void merge(int[] array, int[] left, int[] right) {
        int i = 0, j = 0, k = 0;
        int leftSize = left.length;
        int rightSize = right.length;

        while (i < leftSize && j < rightSize) {
            if (left[i] <= right[j]) {
                array[k] = left[i];
                i++;
            } else {
                array[k] = right[j];
                j++;
            }
            k++;
        }

        while (i < leftSize) {
            array[k] = left[i];
            i++;
            k++;
        }

        while (j < rightSize) {
            array[k] = right[j];
            j++;
            k++;
        }
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
        String csvFilePath = "/home/breno/Desktop/resultFinal/MergeSortExecution_times.csv";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFilePath, true))) {
            writer.write(inputFileName + "," + duration + " ms");
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
