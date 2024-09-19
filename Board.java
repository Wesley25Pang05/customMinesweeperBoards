import java.awt.*;
import java.util.Arrays;

public class Board {
    
    private char[][] bombs;
    private String[][] game;
    private MinesweeperGUI gui;
    private boolean solver;
    private int mines;
    private int found;
    private int r;
    private int c;
    
    public Board(int size) {
        createBoard(size, size);
    }
    
    public Board(int x, int y) {
        createBoard(x, y);
    }
    
    public void createBoard(int x, int y) {
        bombs = new char[x][y];
        game = new String[x][y];
        solver = false;
        found = 0;
        r = x;
        c = y;
    }
    
    public void placeMines() {
        placeMines((int) (r * c / 6.4));
    }
    
    public void placeMines(int mines) {
        this.mines = mines;
        found = 0;
        if (gui == null) {
            gui = new MinesweeperGUI(r, c);
        }
        else {
            gui.resetBoard();
        }
        bombs = new char[r][c];
        
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                bombs[i][j] = ' ';
                String a = (i+"").length() > 1 ? "" + i : "0" + i;
                String b = (j+"").length() > 1 ? "" + j : "0" + j;
                game[i][j] = a  + "|" + b;
            }
        }
        
        for (int i = 0; i < mines; i++) {
            int tempRow = (int) (Math.random() * r);
            int tempCol = (int) (Math.random() * c);
            if (bombs[tempRow][tempCol] != '#') {
                bombs[tempRow][tempCol] = '#';
            }
            else {
                i--;
            }
        }
    }
    
    public boolean hit(int x, int y, boolean user, boolean firstHit) {
        String a = (x+"").length() > 1 ? "" + x : "0" + x;
        String b = (y+"").length() > 1 ? "" + y : "0" + y;
        if (game[x][y].equals(a + "|" + b) && !game[x][y].equals("FLAGS")) {
            if (bombs[x][y] == '#') {
                if (!firstHit) {
                    System.out.println("You lose!");
                    for (int i = 0; i < r; i++) {
                        for (int j = 0; j < c; j++) {
                            if (bombs[i][j] == '#') {
                                gui.editButton(i, j, "LOSE");
                                gui.color(i, j, Color.red);
                            }
                        }
                    }
                    return true;
                }
                else {
                    placeMines(mines);
                    hit(x, y, user, firstHit);
                }
            }
            else {
                int num = 0;
                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        if (x+i>-1 && y+j>-1 && x+i<r && y+j<c
                        && bombs[x+i][y+j] == '#') {
                            num++;
                        }
                    }
                }
                game[x][y] = "  " + num + "  ";
                gui.editButton(x, y, num + "");
                gui.color(x, y, new Color(80 + num * 20, 255 - num * 25, 80));
                if (num == 0) {
                    found++;
                    for (int i = -1; i <= 1; i++) {
                        for (int j = -1; j <= 1; j++) {
                            if (x + i > -1 && y + j > -1 && x + i < r && y + j < c) {
                                hit(x + i, y + j, false, false);
                            }
                        }
                    }
                }
                else if (firstHit) {
                    placeMines(mines);
                    hit(x, y, user, firstHit);
                }
            }
        }
        else {
            if (user) {
                System.out.println("INVALID");
            }
        }
        if (firstHit && found < Math.sqrt(r * c)) {
            placeMines(mines);
            hit(x, y, user, true);
        }
        int spacesFound = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (game[i][j].charAt(0) != ' ') {
                    spacesFound++;
                }
            }
        }
        if (spacesFound == mines) {
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    if (bombs[i][j] == '#') {
                        gui.editButton(i, j, "WIN");
                    }
                }
            }
            return true;
        }
        return false;
    }

    public void flag(int x, int y, boolean firstHit) {
        String a = (x+"").length() > 1 ? "" + x : "0" + x;
        String b = (y+"").length() > 1 ? "" + y : "0" + y;
        if (!firstHit && game[x][y].equals(a + "|" + b)) {
            game[x][y] = "FLAGS";
            gui.editButton(x, y, "<|");
            gui.color(x, y, Color.red);
        }
        else if (game[x][y].equals("FLAGS")) {
            game[x][y] = a + "|" + b;
            gui.editButton(x, y, "");
            gui.color(x, y, Color.LIGHT_GRAY);
        }
    }
    
    public char[][] bombBoard() {
        return bombs;
    }
    
    public void printBombBoard() {
        for (int i = 0; i < r; i++) {
            System.out.println(Arrays.toString(bombs[i]));
        }
    }
    
    public String[][] game() {
        return game;
    }
    
    public void printGame() {
        for (int i = 0; i < r; i++) {
            System.out.println(Arrays.toString(game[i]));
        }
    }
    
    public MinesweeperGUI getGUI() {
        return gui;
    }
}