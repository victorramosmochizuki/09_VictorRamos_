package servicios;

import dao.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("validarGenerico")
public class ValidatorGenerico extends Conexion implements Validator{

    public static boolean valLote(String generico){
        try {
            String sql="SELECT LOWER(GENMED) FROM MEDICAMENTO WHERE GENMED = '"+ generico +"'";
            PreparedStatement ps = Conexion.conectar().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return true;
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("error validarGenerico" + e.getMessage());
        }
        return false;
    }
    
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String genericoMed = (String) value;
        if (valLote(genericoMed) == true) {
            FacesMessage msg = new FacesMessage("Este medicamento generico ya se ha registrado");
            throw new ValidatorException(msg);
        }
    }

}
