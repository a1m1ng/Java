import java.util.Scanner;
import java.util.HashSet;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите номер задачи(1-8): ");
        int num = in.nextInt();
        in.nextLine(); // Поглощение символа новой строки

        switch (num) {
            case 1:
                System.out.print("Введите строку: ");
                String s = in.nextLine();
                System.out.println(task1(s));
                break;
            case 2:
                System.out.print("Введите количество элементов в первом массиве: ");
                int size1 = in.nextInt();
                int[] arr1 = Tools.input1DArray(in, size1);

                System.out.print("Введите количество элементов во втором массиве: ");
                int size2 = in.nextInt();
                int[] arr2 = Tools.input1DArray(in, size2);

                Tools.print1DArray(task2(arr1, arr2));
                break;
            case 3:
                System.out.print("Введите количество элементов в массиве: ");
                int size3 = in.nextInt();
                int[] arr3 = Tools.input1DArray(in, size3);
                System.out.println("Максимальная сумма подмассива: " + task3(arr3));
                break;
            case 4:
                System.out.print("Введите количество строк массива: ");
                int rows = in.nextInt();
                System.out.print("Введите количество столбцов массива: ");
                int cols = in.nextInt();
                int[][] matrix = Tools.input2DArray(in, rows, cols);
                Tools.print2DArray(task4(matrix));
                break;
            case 5:
                System.out.print("Введите количество элементов в массиве: ");
                int size5 = in.nextInt();
                int[] nums = Tools.input1DArray(in, size5);

                System.out.print("Введите искомую сумму чисел: ");
                int target = in.nextInt();
                int[] result = task5(nums, target);

                if (result != null) {
                    System.out.println("Пара с суммой " + target + ": " + result[0] + ", " + result[1]);
                } else {
                    System.out.println("Пара не найдена.");
                }
                break;
            case 6:
                System.out.print("Введите количество строк массива: ");
                int rows6 = in.nextInt();
                System.out.print("Введите количество столбцов массива: ");
                int cols6 = in.nextInt();
                int[][] matrix6 = Tools.input2DArray(in, rows6, cols6);
                System.out.println("Сумма всех элементов массива: " + task6(matrix6));
                break;
            case 7:
                System.out.print("Введите количество строк массива: ");
                int rows7 = in.nextInt();
                System.out.print("Введите количество столбцов массива: ");
                int cols7 = in.nextInt();
                int[][] matrix7 = Tools.input2DArray(in, rows7, cols7);
                Tools.print1DArray(task7(matrix7));
                break;
            case 8:
                System.out.print("Введите количество строк массива: ");
                int rows8 = in.nextInt();
                System.out.print("Введите количество столбцов массива: ");
                int cols8 = in.nextInt();
                int[][] matrix8 = Tools.input2DArray(in, rows8, cols8);
                Tools.print2DArray(task8(matrix8));
                break;
        }
    }
    // Найти наибольшую подстроку без повторяющихся символов
    static String task1(String s) {
        int start = 0;
        int maxLength = 0;
        int maxStart = 0;
        HashSet<Character> seen = new HashSet<>();

        for (int end = 0; end < s.length(); end++) {
            char currentChar = s.charAt(end);

            // Если символ уже встречался, смещаем старт до тех пор, пока символ не будет уникален
            while (seen.contains(currentChar)) {
                seen.remove(s.charAt(start));
                start++;
            }

            seen.add(currentChar);

            if (end - start + 1 > maxLength) {
                maxLength = end - start + 1;
                maxStart = start;
            }
        }
        return s.substring(maxStart, maxStart + maxLength);
    }
    // Объединить два отсортированных массива
    static int[] task2(int[] arr1, int[] arr2) {
        int[] mergedArray = new int[arr1.length + arr2.length];
        int i = 0, j = 0, k = 0;

        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] <= arr2[j]) {
                mergedArray[k++] = arr1[i++];
            } else {
                mergedArray[k++] = arr2[j++];
            }
        }

        while (i < arr1.length) {
            mergedArray[k++] = arr1[i++];
        }

        while (j < arr2.length) {
            mergedArray[k++] = arr2[j++];
        }

        return mergedArray;
    }
    // Найти максимальную сумму подмассива
    static int task3(int[] arr){
        int maxSum = arr[0];
        int currentSum = arr[0];

        for (int i = 1; i < arr.length; i++) {
            currentSum = Math.max(arr[i], currentSum + arr[i]);

            maxSum = Math.max(maxSum, currentSum);
        }
        return maxSum;
    }
    // Повернуть массив на 90 градусов по часовой стрелке
    static int[][] task4(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] rotatedMatrix = new int[cols][rows];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                rotatedMatrix[j][rows - 1 - i] = matrix[i][j];
            }
        }
        return rotatedMatrix;
    }
    // Найти пару элементов в массиве, сумма которых равна заданному числу
    static int[] task5(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];

            if (map.containsKey(complement)) {
                return new int[] {complement, nums[i]};
            }

            map.put(nums[i], i);
        }
        return null;
    }
    // Найти сумму всех элементов в двумерном массиве
    static int task6(int[][] matrix) {
        int sum = 0;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                sum += matrix[i][j];
            }
        }
        return sum;
    }
    // Найти максимальный элемент в каждой строке двумерного массива
    static int[] task7(int[][] matrix) {
        int[] maxElements = new int[matrix.length];

        for (int i = 0; i < matrix.length; i++) {
            int max = matrix[i][0];

            for (int j = 1; j < matrix[i].length; j++) {
                max = Math.max(max, matrix[i][j]);
            }

            maxElements[i] = max;
        }
        return maxElements;
    }
    // Повернуть двумерный массив на 90 градусов против часовой стрелке
    static int[][] task8(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] rotatedMatrix = new int[cols][rows];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                rotatedMatrix[cols - 1 - j][i] = matrix[i][j];
            }
        }
        return rotatedMatrix;
    }
    }
