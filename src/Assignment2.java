/**
 * Created by trevorforrey on 1/30/16.
 */


import java.awt.*;       // Using AWT containers and components
import java.awt.event.*; // Using AWT events and listener interfaces
import javax.swing.*;    // Using Swing components and containers
import java.util.*;     // Using java.util.List

// A Swing GUI application that creates a small window for the user to convert hex and binary to decimal
public class Assignment2 extends JFrame {

    private JTextField binaryToDecInput;
    private JTextField binaryToDecOutput;
    private JTextField hexToDecInput;
    private JTextField hexToDecOutput;

    /** Constructor to setup the GUI */
    public Assignment2 () {

        // Creation of the Container
        Container container = getContentPane();
        container.setLayout(new BorderLayout());

        // Creation of the binary -> decimal and hex -> decimal panels
        JPanel binToDecPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel hexToDecPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        // Creating components for the binary -> decimal panel
        JLabel binaryLabel = new JLabel("binary");
        JLabel binDecimalLabel = new JLabel("decimal");
        binaryToDecInput = new JTextField("", 10);
        binaryToDecInput.setEditable(true);
        binaryToDecOutput = new JTextField("",40);
        binaryToDecOutput.setEditable(false);


        // Creating components for the hex -> decimal panels
        JLabel hexLabel = new JLabel("hexadecimal");
        JLabel hexDecimalLabel = new JLabel("decimal");
        hexToDecInput = new JTextField("", 10);
        hexToDecInput.setEditable(true);
        hexToDecOutput = new JTextField("", 40);
        hexToDecOutput.setEditable(false);


        // Creating the button for bin -> decimal and hex -> decimal
        JButton binToDecButton = new JButton("Convert Binary To Decimal");
        JButton hexToDecButton = new JButton("Convert Hexadecimal To Decimal");


        // Adding the binary -> decimal components to their respected panel
        binToDecPanel.add(binaryLabel);
        binToDecPanel.add(binaryToDecInput);
        binToDecPanel.add(binDecimalLabel);
        binToDecPanel.add(binaryToDecOutput);
        binToDecPanel.add(binToDecButton);


        // Adding the hex -> decimal components to their respected panel
        hexToDecPanel.add(hexLabel);
        hexToDecPanel.add(hexToDecInput);
        hexToDecPanel.add(hexDecimalLabel);
        hexToDecPanel.add(hexToDecOutput);
        hexToDecPanel.add(hexToDecButton);


        // Adds both panels to the container
        container.add(binToDecPanel, BorderLayout.NORTH);
        container.add(hexToDecPanel, BorderLayout.SOUTH);


        // Sets the Frame to the center of the scree
        setLocationRelativeTo(null);



        // Convert binary -> decimal button action listener
        binToDecButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    binaryToDecOutput.setText(parseBinary(binaryToDecInput.getText()));
                } catch (BinaryNumberFormatException ex) {

                }
            }
        });


        // Convert hex -> decimal button action listener
        hexToDecButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    hexToDecOutput.setText(parseHex(hexToDecInput.getText()));
                } catch (HexNumberFormatException ex) {

                }

            }
        });



        // Sets title, size, and other basic attributes to the frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Base Converter");
        setSize(1050, 100);
        setVisible(true);
    }







    // Running of the GUI
    public static void main(String[] args) {
        // Run the GUI construction in the Event-Dispatching thread for thread-safety
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Assignment2(); // Constructor does the job
            }
        });
    }







    // Function that takes in a string of binary and returns the string representation of the equivalent decimal value
    public String parseBinary(String binaryString) throws BinaryNumberFormatException {

        double decimal = 0;
        String output = "";

        try {

            // Runs through every character in the binary string
            for (int i = 0; i < binaryString.length(); i++) {

                char currentBinaryChar = binaryString.charAt(i);

                // If the character is a 1
                if (binaryString.charAt(i) == '1') {

                    // Increase the decimal by the value of the 1 (done through taking it's position in the string)
                    decimal += Math.pow(2, binaryString.length() - 1 - i);

                // If the character is a 0
                } else if (binaryString.charAt(i) == '0') {

                    // Keep the decimal the same value
                    decimal += 0;

                // If the character is an invalid binary character
                } else {

                    // Throw a BinaryNumberFormatException
                    throw new BinaryNumberFormatException(currentBinaryChar);
                }

            }

            // Set the output to the string representation of the decimal
            output = String.valueOf(decimal);

        // Catches the BinaryNumberFormatException throw and displays an error message to the user
        } catch (BinaryNumberFormatException ex) {
            output = "Invalid Format for a Binary String - Illegal character: " + ex.getInvalidCharacter();
        }


        return output;

    }






    // Function that takes in a string of hexadecimal and returns the string representation of the equivalent decimal value
    public String parseHex(String hexString) throws HexNumberFormatException {

        double decimal = 0;
        int numberFormOfChar;
        String output = "";
        java.util.List<Character> validCharList = Arrays.asList('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F');
        java.util.List<Character> letterCharList = Arrays.asList('A', 'B', 'C', 'D', 'E', 'F');

        try {

            for (int i = 0; i < hexString.length(); i++) {

                char currentHexChar = hexString.charAt(i);

                // If it's a valid char
                if (validCharList.contains(currentHexChar)) {

                    // If it's a letter char
                    if (letterCharList.contains(currentHexChar)) {

                        // Increment the current decimal value by the proper hexadecimal value, based on the letter and position in the string
                        switch (currentHexChar) {

                            case 'A':
                                decimal += Math.pow(16, hexString.length() - 1 - i ) * 10;
                                break;
                            case 'B':
                                decimal += Math.pow(16, hexString.length() - 1 - i ) * 11;
                                break;
                            case 'C':
                                decimal += Math.pow(16, hexString.length() - 1 - i ) * 12;
                                break;
                            case 'D':
                                decimal += Math.pow(16, hexString.length() - 1 - i ) * 13;
                                break;
                            case 'E':
                                decimal += Math.pow(16, hexString.length() - 1 - i ) * 14;
                                break;
                            case 'F':
                                decimal += Math.pow(16, hexString.length() - 1 - i ) * 15;
                                break;
                        }
                        continue;
                    }

                    // Increment the current decimal value by the proper hexadecimal value, based on the number and position in the string
                    switch (currentHexChar) {

                        case '0':
                            decimal += Math.pow(16, hexString.length() - 1 - i ) * 0;
                            break;
                        case '1':
                            decimal += Math.pow(16, hexString.length() - 1 - i ) * 1;
                            break;
                        case '2':
                            decimal += Math.pow(16, hexString.length() - 1 - i ) * 2;
                            break;
                        case '3':
                            decimal += Math.pow(16, hexString.length() - 1 - i ) * 3;
                            break;
                        case '4':
                            decimal += Math.pow(16, hexString.length() - 1 - i ) * 4;
                            break;
                        case '5':
                            decimal += Math.pow(16, hexString.length() - 1 - i ) * 5;
                            break;
                        case '6':
                            decimal += Math.pow(16, hexString.length() - 1 - i ) * 6;
                            break;
                        case '7':
                            decimal += Math.pow(16, hexString.length() - 1 - i ) * 7;
                            break;
                        case '8':
                            decimal += Math.pow(16, hexString.length() - 1 - i ) * 8;
                            break;
                        case '9':
                            decimal += Math.pow(16, hexString.length() - 1 - i ) * 9;
                            break;
                    }

                // If the current character is not a valid hexadecimal character
                } else {

                    // Throw a HexNumberFormatException
                    throw new HexNumberFormatException((currentHexChar));

                }
            }

            // Set output to the string representation of the summed decimal value
            output = String.valueOf(decimal);


        // Catches a HexNumberFormatException if thrown and displays an error message to the user
        } catch (HexNumberFormatException ex) {
            output = "Invalid Format for a Hexadecimal String - Illegal character: " + ex.getInvalidCharacter();
        }



        return output;

    }
}

























