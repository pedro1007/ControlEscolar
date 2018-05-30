package mx.edu.uaz.SistemaControlEscolar.Design;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.BrowserFrame;
import com.vaadin.ui.Window;

public class VentanaReporte extends Window{
	private BrowserFrame bfConstancia;
	
	public VentanaReporte(int idUsuario) {
		setWidth("700px");
		setHeight("500px");
		setCaption("Constancia");
		setIcon(VaadinIcons.GRID);
		setStyleName("ventana-report");
		
		bfConstancia = new BrowserFrame("", 
        		new ExternalResource("http://localhost:8090/birt/preview?__report=reportes/credenciales.rptdesign&__format=pdf", "application/pdf"));
		bfConstancia.setSizeFull();
		
		setContent(bfConstancia);
		
		
	}

}
