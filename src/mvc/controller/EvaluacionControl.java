package mvc.controller;

import mvc.view.*;
import mvc.model.*;

import mvc.model.Evaluacion.Pregunta;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EvaluacionControl extends JFrame
{
    private Evaluacion eval;
    private PreguntaView pregView;
    private EvaluacionView evalView;
    //private JLabel timerLabel = new JLabel("TIEMPO TRANSCURRIDO");

    public EvaluacionControl()
    {
        this.eval = new Evaluacion();
        eval = eval.llenarEvaluacion();
        //eval.mostrarEvaluacion();

        pregView = new PreguntaView(eval.preguntas);
        
        int width = 620, height = 800;
        setTitle("Nueva Evaluacion");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(width, height);
        setLocation(0, 0);
        //add(timerLabel);

        JButton alternarButton = new JButton("Entregar");
        alternarButton.addActionListener(e -> {
            if (getContentPane().equals(pregView)) {
                evalView = new EvaluacionView(eval);
                setContentPane(evalView);
            } else {
                setContentPane(pregView);
            }
            validate();
        });

        setContentPane(pregView);
        add(alternarButton, BorderLayout.SOUTH);

    }
}