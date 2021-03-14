import com.snake.gui.Menu;

import javax.swing.*;

public class Snake {
    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        SwingUtilities.invokeLater(() -> Menu.getInstance((args.length > 0) ? args[0] : null));
    }
}
