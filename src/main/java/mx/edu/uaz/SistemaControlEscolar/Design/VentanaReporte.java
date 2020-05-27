package mx.edu.uaz.SistemaControlEscolar.Design;

import java.io.File;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinService;
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
		
		String basepath = VaadinService.getCurrent()
                .getBaseDirectory().getAbsolutePath();

//Image as a file resource
//FileResource resource = new FileResource(new File(basepath +
//                      "/pdf/doc1.doc"));

//		bfConstancia = new BrowserFrame("", resource);
		bfConstancia.setSizeFull();
		
		setContent(bfConstancia);
		
		
	}

}
