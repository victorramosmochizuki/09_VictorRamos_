
package servicios;

//import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import dao.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator ("validarRucexis")
public class Validatorprueba extends Conexion implements Validator{
    
    
    public static boolean valRuc (String RUCPROV){
        try {
            String sql = "SELECT RUCPROV FROM PROVEEDOR WHERE RUCPROV = '"+ RUCPROV +"' ";
            PreparedStatement ps = Conexion.conectar().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return true;
            }
            rs.close();
            
            
        } catch (Exception e) {
            System.out.println("error en validarRucexis" + e.getMessage());
            
        }
        
        return false;
    }

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        
        String loteRuc = (String) value;
        if (valRuc(loteRuc) == true ){
            FacesMessage msg = new FacesMessage("ESTE RUC YA EXISTE");
            throw new ValidatorException(msg);
            
        }
        
    }
    
    
    
    
}
