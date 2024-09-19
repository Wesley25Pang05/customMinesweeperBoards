import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Bombhit implements MouseListener {
    
    private int r;
    private int c;
    
    public Bombhit(int row, int col) {
        r = row;
        c = col;
    }
    /*
    public void actionPerformed(ActionEvent e) {
        MyProgram.hitting(r, c);
    }
    */
    @Override
    public void mouseClicked(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e)) {
            MyProgram.hitting(r, c);
        }
        else if (SwingUtilities.isRightMouseButton(e)) {
            MyProgram.flagging(r, c);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}