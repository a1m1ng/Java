import java.util.Arrays;
import java.util.Scanner;

public class Tools {

    public static int[] input1DArray(Scanner in, int size) {
        int[] array = new int[size];
        System.out.println("Введите элементы массива (через пробел): ");
        for (int i = 0; i < size; i++) {
            array[i] = in.nextInt();
        }
        return array;
    }

    public static void print1DArray(int[] array) {
        System.out.println("Результат: " + Arrays.toString(array));
    }

    public static int[][] input2DArray(Scanner in, int rows, int cols) {
        int[][] array = new int[rows][cols];
        System.out.println("Введите элементы двумерного массива (построчно): ");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                array[i][j] = in.nextInt();
            }
        }
        return array;
    }

    public static void print2DArray(int[][] array) {
        System.out.println("Результат:");
        for (int[] row : array) {
            for (int element : row) {
                System.out.print(element + " ");
            }
            System.out.println();
        }
    }
}