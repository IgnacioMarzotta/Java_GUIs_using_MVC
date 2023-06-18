package mvc.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import mvc.model.Evaluacion.Pregunta;

public class PreguntaView extends JPanel {
    
    public PreguntaView(List<Pregunta> preguntas){
        int cont = 0;

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.black));

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setAutoscrolls(true);
        this.add(panel,BorderLayout.NORTH);

        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setBounds(0,0, 600, 600);

        JPanel contentPane = new JPanel(null);
        contentPane.setPreferredSize(new Dimension(600, 600));
        contentPane.add(scrollPane);

        for(Pregunta preg : preguntas)
        {
            JPanel aux = new JPanel();
            aux.setLayout(new BoxLayout(aux, BoxLayout.Y_AXIS));
            aux.setPreferredSize(new Dimension(520, 300));
            aux.setBorder(new EmptyBorder(10, 0, 10, 0));
            
            JLabel enunLabel = new JLabel(String.format("<html><div style=\"width:%dpx;\">%s</div></html>", 450, String.valueOf(cont+1)+preg.enunciado));

            JRadioButton primeraAlt = new JRadioButton(preg.alternativas[0]);
            primeraAlt.setMnemonic(KeyEvent.VK_B);
            primeraAlt.setActionCommand(preg.alternativas[0]);
    
            JRadioButton segundaAlt = new JRadioButton(preg.alternativas[1]);
            segundaAlt.setMnemonic(KeyEvent.VK_C);
            segundaAlt.setActionCommand(preg.alternativas[1]);
    
            JRadioButton terceraAlt = new JRadioButton(preg.alternativas[2]);
            terceraAlt.setMnemonic(KeyEvent.VK_D);
            terceraAlt.setActionCommand(preg.alternativas[2]);
    
            JRadioButton cuartaAlt = new JRadioButton(preg.alternativas[3]);
            cuartaAlt.setMnemonic(KeyEvent.VK_R);
            cuartaAlt.setActionCommand(preg.alternativas[3]);

            primeraAlt.putClientProperty("parametro", String.valueOf(cont));
            segundaAlt.putClientProperty("parametro", String.valueOf(cont));
            terceraAlt.putClientProperty("parametro", String.valueOf(cont));
            cuartaAlt.putClientProperty("parametro", String.valueOf(cont));

            //Crear el ButtonGroup y agregarle las alternativas
            ButtonGroup group = new ButtonGroup();
            group.add(primeraAlt);
            group.add(segundaAlt);
            group.add(terceraAlt);
            group.add(cuartaAlt);

            //Agregar el ActionListener a cada una de las alternativas
            primeraAlt.addActionListener(e -> {
                JRadioButton source = (JRadioButton) e.getSource();
                String parametro = (String) source.getClientProperty("parametro");
                preguntas.get(Integer.parseInt(parametro)).seleccion = source.getActionCommand();
            });

            segundaAlt.addActionListener(e -> {
                JRadioButton source = (JRadioButton) e.getSource();
                String parametro = (String) source.getClientProperty("parametro");
                preguntas.get(Integer.parseInt(parametro)).seleccion = source.getActionCommand();
            });

            terceraAlt.addActionListener(e -> {
                JRadioButton source = (JRadioButton) e.getSource();
                String parametro = (String) source.getClientProperty("parametro");
                preguntas.get(Integer.parseInt(parametro)).seleccion = source.getActionCommand();
            });

            cuartaAlt.addActionListener(e -> {
                JRadioButton source = (JRadioButton) e.getSource();
                String parametro = (String) source.getClientProperty("parametro");
                preguntas.get(Integer.parseInt(parametro)).seleccion = source.getActionCommand();
            });

            //Agregar el enunciado y las alternativas al panel auxiliar
            aux.add(enunLabel);
            aux.add(primeraAlt);
            aux.add(segundaAlt);
            aux.add(terceraAlt);
            aux.add(cuartaAlt);

            panel.add(aux);

            cont++;

        }

        this.add(contentPane, BorderLayout.CENTER);
    }

}