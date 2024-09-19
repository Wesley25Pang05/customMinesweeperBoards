import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class MinesweeperGUI {
    
    private JButton[][] buttons;
    private JFrame f;
    private int r;
    private int c;
    
    public MinesweeperGUI(int r, int c) {
        f = new JFrame();
        f.setSize(400, 400);
        f.setLayout(new GridLayout(r, c));
        f.setVisible(true);
        buttons = new JButton[r][c];
        this.r = r;
        this.c = c;

        initializeBoard();
    }

    public void resetBoard() {
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                buttons[i][j].setText("");
                buttons[i][j].setBackground(Color.lightGray);
            }
        }
    }
    
    private void initializeBoard() {
        for (int row = 0; row < r; row++) {
            for (int col = 0; col < c; col++) {
                buttons[row][col] = new JButton();
                buttons[row][col].setPreferredSize(new Dimension(5, 5));
                buttons[row][col].setMargin(new Insets(0, 0, 0, 0));
                buttons[row][col].setBackground(Color.lightGray);
                buttons[row][col].addMouseListener(new Bombhit(row, col));
                f.add(buttons[row][col]);
            }
        }
        f.setVisible(true);
    }
    
    public void editButton(int r, int c, String num) {
        buttons[r][c].setText(num);
    }

    public void color(int r, int c, Color color) {
        buttons[r][c].setBackground(color);
    }
}