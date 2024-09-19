import javax.swing.*;
import java.util.Scanner;
import java.util.Arrays;

public class MyProgram
{
    private static boolean lost = false;
    private static boolean firstHit = true;
    private static Board game;
    private static MinesweeperGUI gui;
    
        
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        
        String userAnswer = "";
        
        while (!(userAnswer.equals("Custom")||userAnswer.equals("Easy")||
        userAnswer.equals("Medium")||userAnswer.equals("Hard"))) {
            System.out.println("Which board do you want?"
                    + " Custom, Easy, Medium, or Hard");
            userAnswer = input.nextLine();

            if (userAnswer.equalsIgnoreCase("Easy")) {
                game = new Board(8, 8);
                game.placeMines(10);
            } else if (userAnswer.equalsIgnoreCase("Medium")) {
                game = new Board(16, 16);
                game.placeMines(40);
            } else if (userAnswer.equalsIgnoreCase("Hard")) {
                game = new Board(30, 16);
                game.placeMines(99);
            } else if (userAnswer.equalsIgnoreCase("Custom")) {
                System.out.println("How many rows?");
                int x = input.nextInt();
                System.out.println("How many columns?");
                int y = input.nextInt();
                int mines = 0;
                while (mines < 1 || mines > Math.sqrt(x * y)) {
                    System.out.println("How many mines?");
                    mines = input.nextInt();
                }
                game = new Board(x, y);
                game.placeMines(mines);
            }
        }
    }
    
    public static void hitting(int x, int y) {
        if (!lost) {
            lost = game.hit(x, y, true, firstHit);
            firstHit = false;
        }
    }

    public static void flagging(int x, int y) {
        if (!lost) {
            game.flag(x, y, firstHit);
        }
    }
    
    public static void setGUI(MinesweeperGUI GUI) {
        gui = GUI;
    }
}