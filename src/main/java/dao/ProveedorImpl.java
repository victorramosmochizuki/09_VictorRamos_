package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.Proveedor;

public class ProveedorImpl extends Conexion implements ICRUD<Proveedor> {

    @Override
    public void registrar(Proveedor modelo) throws Exception {
                String sql = "INSERT INTO PROVEEDOR (NOMPROV, RUCPROV, TELPROV, EMAPROV, TIPPROV, NCOMPROV, ABREPROV, DIRPROV, IDUBI, ESTPROV) VALUES  (?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, modelo.getNOMPROV());
            ps.setString(2, modelo.getRUCPROV());
            ps.setString(3, modelo.getTELPROV());
            ps.setString(4, modelo.getEMAPROV());
            ps.setString(5, modelo.getTIPPROV());
            ps.setString(6, modelo.getNCOMPROV());
            ps.setString(7, modelo.getABREVPROV());
            ps.setString(8, modelo.getDIRPROV());
            ps.setString(9, modelo.getIDUBI());
            ps.setString(10, "A");
            ps.executeUpdate(); //ejecuta y actualiza
            ps.close(); //cierra ps
        } catch (Exception e) {
            System.out.println("Error al INGRESAR ProveedorImpl " + e.getMessage());
        } finally {
            this.cerrar();
        }
    }

    @Override
    public List<Proveedor> listar() throws Exception {
        List<Proveedor> listado = null; //empieza desde 0= null
        Proveedor prov;
        String sql = "SELECT * FROM vListarv3";
        try {
            listado = new ArrayList();
            Statement st = this.conectar().createStatement();
            ResultSet rs = st.executeQuery(sql); //ejecutamos el query
            while (rs.next()) {  //traemos el resultado de arriba y pasamos al sgt con next
                prov = new Proveedor(); //instanciamos con su modelo
                prov.setIDPROV(rs.getInt("IDPROV"));
                prov.setNOMPROV(rs.getString("NOMPROV"));
                prov.setRUCPROV(rs.getString("RUCPROV"));
                prov.setTELPROV(rs.getString("TELPROV"));
                prov.setEMAPROV(rs.getString("EMAPROV"));
                prov.setTIPPROV(rs.getString("TIPPROV"));
                prov.setNCOMPROV(rs.getString("NCOMPROV"));
                prov.setABREVPROV(rs.getString("ABREPROV"));
                prov.setDIRPROV(rs.getString("DIRPROV"));
              
                listado.add(prov);
            }
            rs.close(); //cerramos lo q hemos abierto arriba resulset y st
            st.close();
        } catch (Exception e) {
            System.out.println("Error en listar ProveedorImpl: " + e.getMessage());
        } finally {
            this.cerrar(); //cerrar la conexion
        }
        return listado;
    }


    @Override
    public void modificar(Proveedor modelo) throws Exception {
        
        String sql = "UPDATE PROVEEDOR SET NOMPROV=?, RUCPROV=?, TELPROV=?, EMAPROV=?, TIPPROV=?, NCOMPROV=?, ABREPROV=?, DIRPROV=?, IDUBI=? WHERE IDPROV=?";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, modelo.getNOMPROV());
            ps.setString(2, modelo.getRUCPROV());
            ps.setString(3, modelo.getTELPROV());
            ps.setString(4, modelo.getEMAPROV());
            ps.setString(5, modelo.getTIPPROV());
            ps.setString(6, modelo.getNCOMPROV());
            ps.setString(7, modelo.getABREVPROV());
            ps.setString(8, modelo.getDIRPROV());
            ps.setString(9, modelo.getIDUBI());
            ps.setInt(10, modelo.getIDPROV());
            ps.executeUpdate();
            ps.close();  
        } catch (Exception e) {
            System.out.println("Error en MODIFICAR ProveedorImpl " + e.getMessage());
        }  
    }

    @Override
    public void eliminar(Proveedor modelo) throws Exception {
        String sql = "UPDATE PROVEEDOR SET ESTPROV=? WHERE IDPROV=? ";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, "I");
            ps.setInt(2, modelo.getIDPROV());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error en ELIMINAR ProveedorImpl" + e.getMessage());
        }
 
    }
    
    public boolean existe(Proveedor modelo, List<Proveedor> listaModelo) {
        for (Proveedor cli : listaModelo) {
            if (modelo.getRUCPROV().equals(cli.getRUCPROV())) {
                return true;
            }
        }
        return false;
    }
}


