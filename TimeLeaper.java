import javax.swing.*;
import java.io.File;

public class TimeLeaper {
    public static void main(String[] args) {
        String house = "";

        house = JOptionPane.showInputDialog(null, "Para qual diretorio quer ir? ", System.getProperty("user.home"));


        Leaper leaperframe = new Leaper(house);
        leaperframe.setVisible(true);
    }
}