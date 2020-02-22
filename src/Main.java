import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static char[][] field = {{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}};
    private static int count = 0;      // need this int to change player in chooseTurn()
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        boolean endGame = false;
        while(!endGame){
            char ch = chooseTurn();
            move(ch, sc) ;
            if(checkWinner(ch)){
                endGame = true;
                printField();
                System.out.println(ch + " wins");
            } else if(checkEmptyCells() && !checkWinner(ch)){
                printField();
                System.out.println("Draw");
                endGame = true;
            }
        }
        sc.close();
    }

    //return 'X' or 'O'
    private static char chooseTurn(){
        return ++count % 2 == 0 ? 'O' : 'X';
    }

    //place 'X' or 'O' in the field
    private static void move(char ch, Scanner sc){
        while (true){
            printField();
            System.out.print("Enter coordinates: ");
            try {
                int column = sc.nextInt() - 1;
                int line = 3 -  sc.nextInt();
                if (field[line][column] == '_' || field[line][column] == ' '){
                    field[line][column] = ch;
                    break;
                } else {
                    System.out.println("This cell is occupied! Choose another one!");
                }
            } catch (InputMismatchException e) {
                sc.next();      //this two lines to avoid endless loop
                sc.next();
                System.out.println("You should enter numbers!");
            } catch (ArrayIndexOutOfBoundsException e){
                System.out.println("Coordinates should be from 1 to 3!");
            }
        }

    }

    //check field for winner
    private static boolean checkWinner(char ch){

        for (int i = 0; i < 3; i++) {       //check lines for winner
            if (field[i][0] == ch && field[i][1] == ch && ch == field[i][2]) {
                return true;
            }
        }
        for (int i = 0; i < 3; i++) {       //check columns for winner
            if(field[0][i] == ch && field[1][i] == ch && field[2][i] == ch){
                return true;

            }
        }

        return (field[1][1] == ch) && (((field[0][0] == ch) &&  //check diagonals for winner
                (field[2][2] == ch)) || ((field[0][2] == ch) && (field[2][0] == ch)));
    }

    // return 'true' if there are NO empty cell in the field
    private static boolean checkEmptyCells(){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(field[i][j] == ' ' || field[i][j] == '_'){
                    return false;
                }
            }
        }

        return true;
    }

    private static void printField(){
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(field[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

}
