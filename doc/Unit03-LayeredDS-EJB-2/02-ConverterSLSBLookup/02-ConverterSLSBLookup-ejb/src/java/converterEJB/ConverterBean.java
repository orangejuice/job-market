/*
 * Sample implementation of a stateless enterprise java bean
 */
package converterEJB;

import javax.ejb.*;


// indicate stateless bean and define lookup name
@Stateless
@EJB(name="converter", beanInterface=Converter.class)
public class ConverterBean implements Converter {

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
    public double gbpToEuro(double gbp) {
        return gbp * gbpRate;
    }

    @Override
    public double euroToGBP(double euro) {
        return euro / gbpRate;
    }

    
}
