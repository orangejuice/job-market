/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myApplication;

import converterEJB.*;
import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JOptionPane;

/**
 *
 * @author reiner.dojen
 */
public class ConverterApplication {

    public static void main(String[] args) {

        Converter conv;
        double euro = 0;
        double dollar = 0;
        String input;
        try {
            // obtain initial context object
            InitialContext context = new InitialContext();

            // obtain reference to bean via lookup
            conv = (Converter) context.lookup("java:global/02-ConverterSLSBLookup/02-ConverterSLSBLookup-ejb/ConverterBean!converterEJB.Converter");

            // invoke EJB method
            // get dollar amount from user
            input = JOptionPane.showInputDialog("Please enter dollar amount: ");
            dollar = Double.parseDouble(input);
            euro = conv.dollarToEuro(dollar);
            JOptionPane.showMessageDialog(null, dollar + " Dollar equals " +
                    euro + " Euro");
        } catch (NamingException ex) {
            JOptionPane.showMessageDialog(null, "Exception\n" + ex.getMessage());
            ex.printStackTrace();

        }

        System.exit(0);
    }

}
