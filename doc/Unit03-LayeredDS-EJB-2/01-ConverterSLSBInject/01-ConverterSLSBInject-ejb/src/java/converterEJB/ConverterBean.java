/*
 * Sample implementation of a stateless enterprise java bean
 */
package converterEJB;

import javax.ejb.*;


// indicate stateless bean 
@Stateless
public class ConverterBean 
        implements ConverterInj {

    // assume up-to-date exchange rates are 
    // obtained via web service ...
    // these are taken on 29/02/2016
    private final double gbpRate = 1.26809; // 1GBP = 1.268...€
    private final double dollarRate = 0.91435; // 1$ == 0.76...€

    @Override
    public double dollarToEuro(double dollar) {
        return dollar * dollarRate;
    }

    @Override
    public double euroToDollar(double euro) {
        return euro / dollarRate;
    }

    @Override
    @Remove
    public double gbpToEuro(double gbp) {
        return gbp * gbpRate;
    }

    @Override
    public double euroToGBP(double euro) {
        return euro / gbpRate;
    }

    
}
