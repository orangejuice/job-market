package sample;
import java.util.regex.*;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.*;
 
@FacesValidator("myvalidatoranno")
public class MyValidatorAnnotation implements Validator
{
 
    private String[] fromDB = {"me@you.ie", "you@me.ie", "him@you.ie"};
 
    @Override
    public void validate(FacesContext context, UIComponent c, Object val) throws ValidatorException
    {
        String email = (String) val;
        boolean matches = false;
        for (int i=0; i<fromDB.length; i++) {
            if (email.equalsIgnoreCase(fromDB[i])) {
                matches = true;
                break;
            }
        }
 
        if (!matches) {
            FacesMessage message = new FacesMessage();
            message.setDetail("Please enter a valid email");
            message.setSummary("Email not valid");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }
 
    }
}