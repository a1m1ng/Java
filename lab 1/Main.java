import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите номер задачи(1-5): ");
        int num = in.nextInt();
        switch (num){
            case 1:
                task1();
                break;
            case 2:
                task2();
                break;
            case 3:
                task3();
                break;
            case 4:
                task4();
                break;
            case 5:
                task5();
                break;
        }
    }
    static void task1(){
        Scanner in = new Scanner(System.in);
        System.out.print("Введите число: ");
        int num = in.nextInt();
        int count = 0;
        while (num > 1){
            if (num % 2 == 0){
                num /= 2;
            }
            else{
                num *= 3;
                num++;
            }
            count++;
        }
        System.out.println(count);
    }
    static void task2(){
        Scanner in = new Scanner(System.in);
        System.out.print("Введите число: ");
        int n = in.nextInt();
        int sum = 0;
        for (int i = 0; i < n; i++){
            int num = in.nextInt();
            if (i % 2 == 0){
                sum += num;
            }
            else{
                sum -= num;
            }
        }
        System.out.println(sum);
    }
    static void task3(){
        Scanner in = new Scanner(System.in);
        System.out.print("Введите координату x: ");
        int treasure_x = in.nextInt();
        System.out.print("Введите координату y: ");
        int treasure_y = in.nextInt();
        in.nextLine(); // Добавить это для поглощения символа новой строки
        int x = 0; int y = 0; int the_minimum_number_of_instructions = 0; // Текущие координаты
        boolean target = false;
        System.out.print("Введите направление движения: ");
        String direction_of_movement = in.nextLine();
        while (!direction_of_movement.equalsIgnoreCase("стоп")){
            System.out.print("Введите количество шагов: ");
            int step = in.nextInt();
            in.nextLine(); // Добавить это для поглощения символа новой строки
            switch (direction_of_movement.toLowerCase()){
                case "север":
                    y += step;
                    break;
                case "юг":
                    y -= step;
                    break;
                case "запад":
                    x -= step;
                    break;
                case "восток":
                    x += step;
                    break;
                case "стоп":
                    break;
            }
            if (!target){
                the_minimum_number_of_instructions += 1;
            }
            if (treasure_x == x && treasure_y == y){
                target = true;
            }
            System.out.print("Введите направление движения: ");
            direction_of_movement = in.nextLine();
        }
        System.out.println(the_minimum_number_of_instructions);
    }
    static void task4(){
        int maxh = -1; int path = 0;
        Scanner in = new Scanner(System.in);
        System.out.print("Введите количество дорог: ");
        int road = in.nextInt();
        for (int i = 0; i < road; i++){
            System.out.print("Введите количество тоннелей: ");
            int tunnel = in.nextInt();
            int minh = 1000000000;
            for (int j = 0; j < tunnel; j++){
                System.out.print("Введите высоту тоннеля: ");
                int h = in.nextInt();
                if (minh > h){
                    minh = h;
                }
            }
            if (maxh < minh){
                maxh = minh;
                path = i+1;
            }
        }
        System.out.println(path + " " + maxh);
    }
    static void task5(){
        Scanner in = new Scanner(System.in);
        System.out.print("Введите число: ");
        String num = in.nextLine();
        int sum = 0; int prod = 1;
        for (int i = 0; i < num.length(); i++){
            int dig = Integer.parseInt(num.substring(i, i+1));
            sum += dig;
            prod *= dig;
        }
        if (sum % 2 == 0 && prod % 2 == 0){
            System.out.println("Число дважды чётное");
        }
        else{
            System.out.println("Число не дважды чётное");
        }
    }
}
