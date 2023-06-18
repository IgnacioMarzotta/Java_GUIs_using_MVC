package mvc.model;

import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Evaluacion
{
    public class Pregunta
    {
        public String seleccion;
        public String enunciado;
        public String respuesta;
        public String[] alternativas;
    }

    public int totalPreguntas;
    public List<Pregunta> preguntas = new ArrayList<>();   // Lista de preguntas

    //Funcion encargada en mostrar la evaluacion por consola, ideal para debugging.
    public void mostrarEvaluacion()
    {
        System.out.println("==================");
        for (Pregunta pregunta : preguntas) {
            System.out.println("Enunciado: " + pregunta.enunciado);
            System.out.println("Respuesta: " + pregunta.respuesta);
            System.out.println("Alternativas:");
            for (String alternativa : pregunta.alternativas) {
                System.out.println(alternativa);
            }
            System.out.println("Selected: " + pregunta.seleccion);
            System.out.println("");
        }
        System.out.println("==================");
    }

    //Funcion encargada de rellenar la evaluacion. Recibe un archivo de texto y lo analiza para obtener la cantidad de preguntas junto con el enunciado, alternativas y respuesta de cada una, para guardarlas en la Lista de preguntas dentro de la evaluacion.
    public Evaluacion llenarEvaluacion()
    {
        File file = new File("prueba.irt");        //
        Evaluacion evaluacion = new Evaluacion();           //
        Evaluacion.Pregunta nuevaPregunta = null;           //
        
        try 
        {
            Scanner sc = new Scanner(file);                     //
            String data;                                        //
            int line = 0;                                       //
            boolean obtuvoPreguntas = false;                    //
            
            while (sc.hasNextLine()) 
            {
                data = sc.nextLine();
                if(!obtuvoPreguntas)
                {
                    totalPreguntas = Integer.parseInt(data);
                    obtuvoPreguntas = true;
                }
                else
                {
                    if(line == 0)
                    {
                        nuevaPregunta = new Evaluacion().new Pregunta();
                        nuevaPregunta.alternativas = new String[]{"","","",""};
                        nuevaPregunta.enunciado = data;
                        nuevaPregunta.seleccion = " ";
                    }
                    if(line >= 1 && line < 5)
                    {
                        if(data.split(" : ", 0 )[0].equals("correcto"))
                        {
                            nuevaPregunta.respuesta = data.split(" : ", 0 )[1];
                        }
                        nuevaPregunta.alternativas[line-1] = data.split(" : ", 0 )[1];
                        if(line == 4)
                        {
                            evaluacion.preguntas.add(nuevaPregunta);
                            line = -1;
                        }
                    }
                    line++;
                }
            }
            sc.close();
        }
        catch (FileNotFoundException e) 
        {
            System.out.println("NO SE ENCUENTRA EL ARCHIVO.");
            e.printStackTrace();
        }

        return evaluacion;

    }

}