package mvc.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import mvc.model.Evaluacion;
import mvc.model.Evaluacion.Pregunta;

public class EvaluacionView extends JPanel
{
    
    public EvaluacionView(Evaluacion eval)
    {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.black));

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setAutoscrolls(true);
        this.add(panel,BorderLayout.WEST);

        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setBounds(0,0, 600, 600);

        JPanel contentPane = new JPanel(null);
        contentPane.setPreferredSize(new Dimension(600, 600));
        contentPane.add(scrollPane);
        
        int cont = 0;
        int corr = 0;

        for (Pregunta preg : eval.preguntas) {

            JLabel enunciadoLabel = new JLabel(preg.enunciado);

            JLabel resultLabel = new JLabel("Respuesta correcta: "+preg.respuesta);
            JLabel selectLabel = new JLabel("Usted selecciono: "+preg.seleccion);

            if(preg.seleccion.equals(preg.respuesta))
            {
                corr++;
            }

            enunciadoLabel.setBorder(new EmptyBorder(10, 0, 0, 0));

            panel.add(enunciadoLabel);
            panel.add(resultLabel);
            panel.add(selectLabel);

            cont++;
        }

        JLabel totalPreguntas = new JLabel("TOTAL: "+ String.valueOf(cont));
        JLabel totalCorrectas = new JLabel("CORRECTAS: "+ String.valueOf(corr));
        totalCorrectas.setBorder(new EmptyBorder(20, 60, 0, 0));
        totalPreguntas.setBorder(new EmptyBorder(0, 60, 20, 0));
        panel.add(totalCorrectas);
        panel.add(totalPreguntas);
        
        this.add(contentPane, BorderLayout.WEST);
    }
}