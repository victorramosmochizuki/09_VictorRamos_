
package servicios;

import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator(value = "validarRuc")
public class ValidatorRUC implements Validator {
    

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String ruc = value.toString().trim();
        if (ruc.length() != 0 && ruc.length() < 11) {
            String plantilla = "^\\d\\d\\d\\d\\d\\d\\d\\d\\d\\d\\d$";
            boolean vali = Pattern.matches(plantilla, ruc);
            if (!vali) {
                throw new ValidatorException(new FacesMessage("El formato debe ser de 11 carácteres"));

            }
        }
    }
}


