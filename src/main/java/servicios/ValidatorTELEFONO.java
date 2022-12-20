
package servicios;

import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator(value = "validarTelefono")
public class ValidatorTELEFONO implements Validator{

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String telefono = value.toString().trim();
        if (telefono.length() != 0 && telefono.length() < 10) {
            String plantilla = "^9\\d\\d\\d\\d\\d\\d\\d\\d$";
            boolean val = Pattern.matches(plantilla, telefono);
            if (!val) {
                throw new ValidatorException(new FacesMessage("El formato debe ser 9########"));

            }
        }
    }
    
}
