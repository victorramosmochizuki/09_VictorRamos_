
package controlador;

import dao.ConsultaD;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import modelo.Consulta;
import org.primefaces.event.SelectEvent;


@Named(value = "consultasC")
@SessionScoped
public class ConsultasC implements Serializable {

    private ConsultaD dao;
    private Consulta consulta;
    private List<Consulta> lstConsulta;

    public ConsultaD getDao() {
        return dao;
    }

    public void setDao(ConsultaD dao) {
        this.dao = dao;
    }

    public Consulta getConsulta() {
        return consulta;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }

    public List<Consulta> getLstConsulta() {
        return lstConsulta;
    }

    public void setLstConsulta(List<Consulta> lstConsulta) {
        this.lstConsulta = lstConsulta;
    }

    public List<Consulta> getSelectedCta() {
        return selectedCta;
    }

    public void setSelectedCta(List<Consulta> selectedCta) {
        this.selectedCta = selectedCta;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
    List <Consulta> selectedCta;
    double total =0.0;
    
    
    public ConsultasC() {
        
        consulta = new Consulta();
        dao = new ConsultaD();
        lstConsulta = new ArrayList();
        
    }
    
    public void listar(){
        try {
            lstConsulta = dao.listar();
        } catch (Exception e) {
            System.err.println("Error en listar consultasC " + e.getMessage());
            
        }
    }
    
    
    
    public void listarBD(int codigo) throws Exception {
        
        try {
            lstConsulta = dao.listarBD(codigo);
            for (Consulta con: lstConsulta){
                total += con.getSUBTOT();
            }
            
            
        } catch (Exception e) {
            
            System.out.println("Error en CONSULTASC/listarBD " + e.getMessage());
        }
    }
    
    public void onRowSelect(SelectEvent event) throws Exception {
        
        Consulta con = (Consulta) event.getObject(); 
        lstConsulta = dao.listarBD(con.getIDBODE());
        
        
        
        
    }
    
    
    
    
    
    @PostConstruct
    public void construir (){
        listar();
    }
    
    
    
    public void limpiar (){
        consulta = new Consulta();
    }

    
    
    
    
    

    
    
    
    
}
