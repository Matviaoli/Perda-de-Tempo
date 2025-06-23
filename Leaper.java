import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class Leaper extends JFrame {

    private JPanel pipoca, capitao;
    private JLabel pacoca, batata;
    private JTextField pacoquinha;
    private JScrollPane xstep;

    private File alcacuz;
    private File[] regaliz;

    private ImageIcon frutiger, aero, exe;

    public Leaper(String slaughter) {
        super("Time Leaper");

        if(slaughter.isEmpty() || slaughter == null){
            alcacuz = new File (System.getProperty("user.home"));
        }else{
            alcacuz = new File (slaughter);
        }

        if(!alcacuz.exists()){
            alcacuz = new File(System.getProperty("user.home"));
        }

        frutiger = Demise("PastaAero.png");
        aero = Demise("IconeAero.png");
        exe = Demise("Aeroexe.png");

        setSize(700, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());



        capitao = new JPanel(new FlowLayout(FlowLayout.LEFT));

        batata = new JLabel("Caminho: ");

        pacoca = new JLabel("⬅ Voltar");
        pacoca.setBackground(Color.LIGHT_GRAY);
        pacoca.setOpaque(true);
        pacoca.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        pacoca.setCursor(new Cursor(Cursor.HAND_CURSOR));

        pacoquinha = new JTextField("", 30);

        capitao.add(pacoca);
        capitao.add(batata);
        capitao.add(pacoquinha);

        add(capitao, BorderLayout.NORTH);



        pipoca = new JPanel();
        pipoca.setLayout(new FlowerLayout(FlowLayout.LEFT, 10, 10));

        xstep= new JScrollPane(pipoca);
        xstep.setPreferredSize(new Dimension(780, 500));
        xstep.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        xstep.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        add(xstep, BorderLayout.CENTER);



        pacoca.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(alcacuz != null && alcacuz.getParentFile() != null){
                    Ispy(alcacuz.getParentFile());
                }
            }

            @Override
            public void mouseEntered(MouseEvent e){
                pacoca.setBackground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e){
                pacoca.setBackground(Color.LIGHT_GRAY);
            }
        });

        pacoquinha.getDocument().addDocumentListener(new DocumentListener(){

            @Override
            public void insertUpdate(DocumentEvent e) {
                Aura();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                Aura();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });

        Ispy(alcacuz);

    }

    private void Ispy (File wespy){

        if(!wespy.isDirectory()){
            return;
        }

        alcacuz = wespy;
        batata.setText("Caminho: "+wespy.getAbsolutePath());
        regaliz = wespy.listFiles();
        pacoquinha.setText("");
        Newspy(regaliz);

    }

    private  void Newspy(File[] anis){

        pipoca.removeAll();

        if(anis == null || anis.length == 0){
            pipoca.add(new Label("Essa pasta está vazia."));
            JOptionPane.showMessageDialog(null, "Essa pasta está vazia","Crystal Tokyo", JOptionPane.ERROR_MESSAGE);
        }else{
            for(int i=0; i < anis.length; i++){
                File fortress = anis[i];
                pipoca.add(Teeth(fortress));
            }
        }

        pipoca.revalidate();
        pipoca.repaint();
    }

    private JLabel Teeth(File fortress){

        JLabel ii;

        if(fortress.isDirectory()){
            ii = new JLabel(fortress.getName(), frutiger, JLabel.CENTER );
        } else if(fortress.getName().toLowerCase().endsWith(".exe")) {
            ii = new JLabel(fortress.getName(), exe, JLabel.CENTER);
        }else{
            ii = new JLabel(fortress.getName(), aero, JLabel.CENTER );
        }

        ii.setVerticalTextPosition(SwingConstants.BOTTOM);
        ii.setHorizontalTextPosition(SwingConstants.CENTER);
        ii.setPreferredSize(new Dimension(100, 100));
        ii.setOpaque(true);
        ii.setBackground(Color.WHITE);
        ii.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        ii.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                if(fortress.isDirectory()){
                    Ispy(fortress);
                } else if (fortress.getName().toLowerCase().endsWith(".exe")) {

                    try{
                        int okko = JOptionPane.showConfirmDialog(null, "Arquivo: " + fortress.getName() + "\nTamanho: " + fortress.length() + " bytes\nDeseja executar esse arquivo?", "Vírus?", JOptionPane.YES_NO_OPTION);
                        if(okko == JOptionPane.YES_OPTION){
                            Runtime.getRuntime().exec(fortress.getAbsolutePath());
                        }
                    }catch (Exception ex){
                        JOptionPane.showMessageDialog(null, "Erro ao tentar executar o arquivo: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                    
                }else{
                    JOptionPane.showMessageDialog(null,"Arquivo: " + fortress.getName() + "\nTamanho: " + fortress.length() + " bytes", fortress.getName(), JOptionPane.PLAIN_MESSAGE);
                }

            }

            @Override
            public void mouseEntered(MouseEvent e) {

                ii.setBackground(new Color(220, 220, 255));

            }

            @Override
            public void mouseExited(MouseEvent e) {

                ii.setBackground(Color.WHITE);

            }

        });

        return ii;
    }

    private void Aura (){

        String aurafarm = pacoquinha.getText().toLowerCase();

        if(regaliz == null){
            return;
        }

        List<File> farmados = new ArrayList<>();
        for(int i = 0; i < regaliz.length;  i++){

            File fortress = regaliz[i];
            String noom = fortress.getName().toLowerCase();

            if(noom.contains(aurafarm)){
                farmados.add(fortress);
            }
        }

        Newspy(farmados.toArray(new File[0]));

    }

    private ImageIcon Demise(String caminho){

        ImageIcon fluid = new ImageIcon(getClass().getResource(caminho));
        Image glass = fluid.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);

        return new ImageIcon(glass);
    }

}
