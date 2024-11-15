import Figures.Bishop;
import Figures.Figure;
import Figures.King;
import Figures.Knight;
import Figures.Pawn;
import Figures.Queen;
import Figures.Rook;

import java.util.ArrayList;

public class Board {
    //TODO: Список фигур и начальное положение всех фигур
    private Figure  fields[][] = new Figure[8][8];
    private ArrayList<String> takeWhite = new ArrayList(16);
    private ArrayList<String> takeBlack = new ArrayList(16);

    public char getColorGaming() {
        return colorGaming;
    }

    public void setColorGaming(char colorGaming) {
        this.colorGaming = colorGaming;
    }

    private char colorGaming;

    public void init(){
        this.fields[0] = new Figure[]{
                new Rook("R", 'w'), new Knight("N", 'w'),
                new Bishop("B", 'w'), new Queen("Q", 'w'),
                new King("K", 'w'), new Bishop("B", 'w'),
                new Knight("N", 'w'),new Rook("R", 'w')
        };
        this.fields[1] = new Figure[]{
                new Pawn("P", 'w'),    new Pawn("P", 'w'),
                new Pawn("P", 'w'),    new Pawn("P", 'w'),
                new Pawn("P", 'w'),    new Pawn("P", 'w'),
                new Pawn("P", 'w'),    new Pawn("P", 'w'),
        };

        this.fields[7] = new Figure[]{
                new Rook("R", 'b'), new Knight("N", 'b'),
                new Bishop("B", 'b'), new Queen("Q", 'b'),
                new King("K", 'b'), new Bishop("B", 'b'),
                new Knight("N", 'b'),new Rook("R", 'b')
        };
        this.fields[6] = new Figure[]{
                new Pawn("P", 'b'),    new Pawn("P", 'b'),
                new Pawn("P", 'b'),    new Pawn("P", 'b'),
                new Pawn("P", 'b'),    new Pawn("P", 'b'),
                new Pawn("P", 'b'),    new Pawn("P", 'b'),
        };
    }

    public String getCell(int row, int col){
        Figure figure = this.fields[row][col];
        if (figure == null){
            return "    ";
        }
        return " "+figure.getColor()+figure.getName()+" ";
    }

    public ArrayList<String> getTakeWhite() {
        return takeWhite;
    }

    public ArrayList<String> getTakeBlack() {
        return takeBlack;
    }

    public boolean move_figure(int row1, int col1, int row2, int col2 ){

        Figure figure =  this.fields[row1][col1];

        if (figure.canMove(row1, col1, row2, col2) && this.fields[row2][col2]==null){
            System.out.println("move");
            this.fields[row2][col2] = figure;
            this.fields[row1][col1] = null;
            return true;
        } else if (figure.canAttack(row1, col1, row2, col2) && this.fields[row2][col2] != null && this.fields[row2][col2].getColor() != this.fields[row1][col1].getColor() ){
            System.out.println("attack");
            switch (this.fields[row2][col2].getColor()){
                case 'w': this.takeWhite.add(this.fields[row2][col2].getColor()+this.fields[row2][col2].getName());break;
                case 'b': this.takeBlack.add(this.fields[row2][col2].getColor()+this.fields[row2][col2].getName());break;
            }
            this.fields[row2][col2] = figure;
            this.fields[row1][col1] = null;
            return true;
        }
        return false;
    }

    public boolean isCheck(char kingColor) {
        // Находим позицию короля
        int kingRow = -1, kingCol = -1;
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Figure figure = fields[row][col];
                if (figure instanceof King && figure.getColor() == kingColor) {
                    kingRow = row;
                    kingCol = col;
                    break;
                }
            }
            if (kingRow != -1) break;
        }

        // Проверяем все фигуры противника
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Figure figure = fields[row][col];
                if (figure != null && figure.getColor() != kingColor) {
                    if (figure.canMove(row, col, kingRow, kingCol) &&
                            isPathClear(row, col, kingRow, kingCol)) {
                        return true; // Король под шахом
                    }
                }
            }
        }
        return false; // Король не под шахом
    }

    public boolean isMate(char kingColor) {
        if (!isCheck(kingColor)) return false; // Если не шах, то не мат

        // Проверяем все возможные ходы всех фигур
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Figure figure = fields[row][col];
                if (figure != null && figure.getColor() == kingColor) {
                    // Проверяем все возможные клетки для хода
                    for (int newRow = 0; newRow < 8; newRow++) {
                        for (int newCol = 0; newCol < 8; newCol++) {
                            // Пропускаем клетки с фигурами своего цвета
                            if (fields[newRow][newCol] != null &&
                                    fields[newRow][newCol].getColor() == kingColor) {
                                continue;
                            }

                            // Проверяем возможность хода
                            if (figure.canMove(row, col, newRow, newCol) &&
                                    isPathClear(row, col, newRow, newCol)) {

                                // Пробуем сделать ход
                                Figure target = fields[newRow][newCol];
                                fields[newRow][newCol] = figure;
                                fields[row][col] = null;

                                boolean stillInCheck = isCheck(kingColor);

                                // Возвращаем фигуры обратно
                                fields[row][col] = figure;
                                fields[newRow][newCol] = target;

                                // Если нашёлся ход, спасающий от шаха
                                if (!stillInCheck) return false; // Если есть ход, значит не мат
                            }
                        }
                    }
                }
            }
        }
        return true; // Если нет доступных ходов, это мат
    }

    private boolean isPathClear(int row, int col, int row1, int col1) {
        // Для коня путь всегда чист
        if (fields[row][col] instanceof Knight) return true;

        int deltaRow = Integer.signum(row1 - row);
        int deltaCol = Integer.signum(col1 - col);

        int currRow = row + deltaRow;
        int currCol = col + deltaCol;

        while (currRow != row1 || currCol != col1) {
            if (fields[currRow][currCol] != null) return false; // Путь заблокирован
            currRow += deltaRow;
            currCol += deltaCol;
        }
        return true; // Путь свободен
    }

    public void print_board(){
        System.out.println(" +----+----+----+----+----+----+----+----+");
        for(int row = 7; row > -1; row--){
            System.out.print(row);
            for(int col = 0; col< 8; col++){
                System.out.print("|"+getCell(row, col));
            }
            System.out.println("|");
            System.out.println(" +----+----+----+----+----+----+----+----+");
        }

        for(int col = 0; col < 8; col++){
            System.out.print("    "+col);
        }


    }


}