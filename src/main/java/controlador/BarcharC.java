package controlador;

import dao.CharUsuarioImpl;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import lombok.Data;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.pie.PieChartDataSet;
import org.primefaces.model.charts.pie.PieChartModel;

@Data
@Named(value = "barcharC")
@SessionScoped
public class BarcharC implements Serializable {

    private List<Number> lstpersona;
    private CharUsuarioImpl dao = new CharUsuarioImpl();
    private PieChartModel pieModel = new PieChartModel();

    public BarcharC() {
    }

    @PostConstruct
    public void init() {
        try {
            lstpersona = dao.graficoPer();
            createPieModel();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void createPieModel() throws Exception {
        ChartData data = new ChartData();
        PieChartDataSet dataSet = new PieChartDataSet();
        List<Number> values = lstpersona;
        dataSet.setData(values);

        List<String> bgColors = new ArrayList<>();
        bgColors.add("rgb(255, 99, 132)");
        bgColors.add("rgb(54, 162, 235)");
        dataSet.setBackgroundColor(bgColors);

        data.addChartDataSet(dataSet);
        List<String> labels = new ArrayList<>();
        labels.add("Hombres");
        labels.add("Mujeres");
        data.setLabels(labels);

        pieModel.setData(data);
    }


    private int number = 20;

    public void activarSesion() {
        number = 20;
    }

    public void decrementoNumero() throws IOException {
        if (number > 0) {
            number--;
        } else if (number == 0) {
            System.out.println("salir de sesion");
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
            FacesContext.getCurrentInstance().getExternalContext().redirect("http://localhost:8080/FARMAVIC_ODAO/");
        }
    }

}
