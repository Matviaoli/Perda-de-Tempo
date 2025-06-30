package time.leaper;

import javax.swing.*;
import java.util.Locale;

public class TimeLeaper {
    public static void main(String[] args) {
        

        Locale.setDefault(new Locale("pt", "BR"));

        UIManager.put("OptionPane.yesButtonText", "Sim");
        UIManager.put("OptionPane.noButtonText", "Não");
        UIManager.put("OptionPane.cancelButtonText", "Cancelar");

        String house = "";

        house = JOptionPane.showInputDialog(null, "Para qual diretorio quer ir? ", System.getProperty("user.home"));


        Leaper leaperframe = new Leaper(house);
        leaperframe.setVisible(true);
    }
}