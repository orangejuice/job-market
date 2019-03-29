/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package converterEJB;
import javax.ejb.*;

/**
 * Defines a remote interface 
 * @author Reiner
 */
@Remote
public interface ConverterInj {
    
    /**
     * Converts the given dollar amount into euro
     * @param dollar amount of dollars to be converted
     * @return euro equivalent to passed dollar amount
     */
    public double dollarToEuro(double dollar);
    
    /**
     * Converts the given euro amount into dollar
     * @param euro amount of euro to be converted
     * @return dollar equivalent to passed euro amount
     */
    public double euroToDollar(double euro);

    /**
     * Converts the given british pound amount into euro
     * @param gbp amount of british pound to be converted
     * @return euro equivalent to passed british pound amount
     */
    public double gbpToEuro(double gbp);
    
    /**
     * Converts the given euro amount into british pound
     * @param euro amount of euro to be converted
     * @return british pound equivalent to passed euro amount
     */
    public double euroToGBP(double euro);
}
