import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Board board = new Board();
        board.setColorGaming('w');
        board.init();

        boolean game = true;

        Scanner in = new Scanner(System.in);

        while (game) {
            board.print_board();
            System.out.println();
            System.out.println("Команды: ");
            System.out.println("----- exit: Выход из игры");
            System.out.println("------y1 x1 y2 x2: Ход фигуры из клетки x1, y1 в клекту x2, y2");


            System.out.println("Взятые Белые:"+board.getTakeWhite().toString());
            System.out.println("Взятые Черные:"+board.getTakeBlack().toString());

            switch (board.getColorGaming()){
                case 'w': System.out.println("Ход Белых:");break;
                case 'b': System.out.println("Ход Черных:");break;
            }


            String inputLine = in.nextLine();
            if (inputLine.equals("exit")){
                System.out.println("Игра завршена.");
                in.close();
                break;
            }
            int x1, y1, x2, y2;
            String[] coords = inputLine.split(" ");
            y1 = Integer.parseInt(coords[0]);
            x1 = Integer.parseInt(coords[1]);
            y2 = Integer.parseInt(coords[2]);
            x2 = Integer.parseInt(coords[3]);


            while (!board.move_figure(y1,x1, y2,x2)){
                System.out.println("Ошибка хода, повторите ввод хода!");
                inputLine = in.nextLine();
                coords = inputLine.split(" ");
                y1 = Integer.parseInt(coords[0]);
                x1 = Integer.parseInt(coords[1]);
                y2 = Integer.parseInt(coords[2]);
                x2 = Integer.parseInt(coords[3]);
            };
            char oppositeColor = (board.getColorGaming() == 'w') ? 'b' : 'w';
            if (board.isCheck(oppositeColor)) {
                if (board.isMate(oppositeColor)) {
                    System.out.println("Мат! Игра окончена.");
                    game = false;
                    break;
                } else {
                    System.out.println("Шах!");
                }
            }

            switch (board.getColorGaming()){
                case 'w': board.setColorGaming('b'); break;
                case 'b': board.setColorGaming('w'); break;
            }



        }

    }
}