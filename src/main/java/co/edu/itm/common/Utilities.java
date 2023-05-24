package co.edu.itm.common;

import javax.swing.*;

public class Utilities {

    public int showOption(String message, String title, String... options){
        return JOptionPane.showOptionDialog(null, message, title, JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
    }

    public int showOptionDefault(String message, String title){
        String[] options = {"Derecho y Izquierdo", "Solo Izquierdo", "Solo Derecho", "Terminar nodo"};

        return JOptionPane.showOptionDialog(null, message, title, JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options, options[0]);
    }

    public int showDialogInputInt(int option, String message, String title, int type){
        boolean valid = false;
        int number = 0;

        switch (option) {
            case Constants.OPTION_ROOT -> {
                while (!valid) {
                    String input = JOptionPane.showInputDialog(null, message, title, type);

                    try {
                        if(input !=null){
                            number = Integer.parseInt(input);
                            valid = true;
                        }else{
                            return Constants.CLOSE_APP;
                        }
                    } catch (NumberFormatException e) {
                        showDialogMessage("Debes ingresar el valor del nodo raiz valido (Numero)", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
            case Constants.OPTION_RIGHT_VALUE -> {
                while (!valid) {
                    String input = JOptionPane.showInputDialog(null, message, title, type);

                    try {
                        if(input !=null){
                            number = Integer.parseInt(input);
                            valid = true;
                        }else{
                            return Constants.CLOSE_APP;
                        }
                    } catch (NumberFormatException e) {
                        showDialogMessage("Ingrese el valor del nodo derecho valido (Numero)", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
            case Constants.OPTION_LEFT_VALUE -> {
                while (!valid) {
                    String input = JOptionPane.showInputDialog(null, message, title, type);

                    try {
                        if(input !=null){
                            number = Integer.parseInt(input);
                            valid = true;
                        }else{
                            return Constants.CLOSE_APP;
                        }
                    } catch (NumberFormatException e) {
                        showDialogMessage("Ingrese el valor del nodo izquierdo valido (Numero)", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }

        return number;
    }

    public void showDialogMessage(String message, String title, int type){
        JOptionPane.showMessageDialog(null, message, title, type);
    }

    public String randomMessagesTour(){
        String[] messages = { "Sigue probando recorridos", "Todavia puebes probar otra opcion",
                "Si ya no quieres probar recorridos puedes cerrar esta ventana" };
        int indexRandom = (int) (Math.random() * messages.length);
        return messages[indexRandom];
    }
}
