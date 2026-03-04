/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaejercicio5;

import javax.swing.JOptionPane;

/**
 *
 * @author caste
 */
public class JavaEjercicio5 {
    public static void main(String[] args) {
        int numberOfStudents = askStudents(); //Uso el metodo askStudents para pedir el numero de estudiantes
        
        if (numberOfStudents == -1) {
            return;
        }
        
        String[] names = new String[numberOfStudents];
        float[][] grades = new float[numberOfStudents][3];
        float[] finalGrades = new float[numberOfStudents];
        int[] countApproveds = new int[1];
        
        loadStudentsData(names, grades); //Uso el metodo loadStudentsData para pedir datos de los estudiantes y guardar en los arrays
        
        
        if (names[0] == null) {
            return;
        }
        
        float[] weightings = requestWeighting(); //Uso el metodo que me retorna los valores de las ponderaciones
        
        if (weightings[0] == -1) {
            return;
        }
        
        gradeCalculation(weightings, grades, finalGrades, countApproveds);
        
        int approveds = countApproveds[0];
        
        displayResults(names, finalGrades, approveds);
        
    }
    
    public static int askStudents() {
        int n = askInt("Cantidad de estudiantes a evaluar: ");
        return n;
    } //Método para pedir el numero de estudiantes
    
    public static void loadStudentsData(String[] names, float[][] grades) {
        for (int i = 0; i < names.length; i++) {
            int posicion = i + 1;
            names[i] = JOptionPane.showInputDialog("Nombre del estudiante No" + posicion + " :");
            if (names[i] == null) {
                names[0] = null;
                break;
            }
            grades[i][0] = askFloat("Calificación proyecto (0.0 - 5.0): ");
            if (grades[i][0] == -1) {
                names[0] = null;
                break;
            }
            grades[i][1] = askFloat("Calificación examen parcial (0.0 - 5.0): ");
            if (grades[i][1] == -1) {
                names[0] = null;
                break;
            }
            grades[i][2] = askFloat("Calificación examen final (0.0 - 5.0): ");
            if (grades[i][2] == -1) {
                names[0] = null;
                break;
            }
        } 
    } //Metodo para pedir datos de los estudiantes
    
    public static int askInt(String mensaje) {
        while (true) {
            try {
                String numero = JOptionPane.showInputDialog(mensaje);
                if (numero == null) {
                    break;
                }
                return Integer.parseInt(numero);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null,"Ingrese un tipo de dato valido!");
            }
        }
        return -1;
    } //Método para pedir enteros de forma segura
    
    public static float askFloat(String mensaje) {
        while (true) {
            try {
                String inputNumero = JOptionPane.showInputDialog(mensaje);
                if (inputNumero == null) {
                    break;
                }
                float numero = Float.parseFloat(inputNumero);
                
                if (numero < 0 || numero > 5) {
                    JOptionPane.showMessageDialog(null,"Calficicación fuera de rango (0.0 - 5.0)");
                } else {
                    return numero;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null,"Ingrese un tipo de dato valido!");
            }
        }
        return -1;
    } //Método para pedir flotantes de forma segura
    
    public static float[] requestWeighting() {
        int optionWeighting = 0;
        float[] weightings = new float[3];
        
        while (true) {
            optionWeighting = askInt(
                    "Ponderación a elegir:\n1.Proyecto (30%), Parcial (30%), Final (40%).\n2.Proyecto (40%), Parcial (40%), Final (20%).");
            if (optionWeighting < 1 || optionWeighting > 2) {
                JOptionPane.showMessageDialog(null,"Opción fuera de rango!");
            } else if (optionWeighting == -1) {
                weightings[0] = -1;
                return weightings;
            } else {
                break;
            }
        }
        
        
        switch (optionWeighting) {
            case 1 -> {
                weightings[0] = 0.3f;
                weightings[1] = 0.3f;
                weightings[2] = 0.4f;
            }
            case 2 -> {
                weightings[0] = 0.4f;
                weightings[1] = 0.4f;
                weightings[2] = 0.2f;
            }
            default -> {
                JOptionPane.showMessageDialog(null,"Error inesperado!");
            }
        }
        return weightings;
    } // Metodo para pedir ponderación al usuario
    
    public static void gradeCalculation(float[] weightings, float[][] grades, float[] finalGrades, int[] countApproveds) {
        for (int i = 0; i < finalGrades.length; i++) {
            float accumulatorNote = 0;
            
            for (int n = 0; n < 3; n++) {
                float finalGradess = grades[i][n] * weightings[n];
                
                accumulatorNote += finalGradess; //Acumulador de las notas de un estudiante segun la ponderacion elegida 
            }
            finalGrades[i] = accumulatorNote;
            
                if (accumulatorNote >= 3.0) {
                    countApproveds[0] ++; //Acumulador de aprovados con una lista
                }
        }
    }
    
    public static void displayResults(String names[],float finalGrades[],int approveds) {
        String messageList = "Listado de estudiantes: ";
        
        for (int i = 0; i < names.length; i++) {
            String message;
            if (finalGrades[i] >= 3.0) {
                message = "Aprobado!";
            } else {
                message = "Reprobado!";
            }
            messageList += "\n" + names[i] + ": " + finalGrades[i] + " --> " + message;
        }
        messageList += "\n\nTotal de estudiantes aprobados: " + approveds;
        
        JOptionPane.showMessageDialog(null,messageList); //Mostramos la lista de los estudiantes, sus notas y si aprobo o no
    }
}

