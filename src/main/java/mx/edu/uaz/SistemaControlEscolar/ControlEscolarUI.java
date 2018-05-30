package mx.edu.uaz.SistemaControlEscolar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.JavaScript;
import com.vaadin.annotations.StyleSheet;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Viewport;
import com.vaadin.server.CustomizedSystemMessages;
import com.vaadin.server.SystemMessages;
import com.vaadin.server.SystemMessagesInfo;
import com.vaadin.server.SystemMessagesProvider;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;

import mx.edu.uaz.SistemaControlEscolar.Design.Carousel;
import mx.edu.uaz.SistemaControlEscolar.Design.ConfirmarCuenta;
import mx.edu.uaz.SistemaControlEscolar.Design.FormBootstrap;
import mx.edu.uaz.SistemaControlEscolar.Design.Login;
import mx.edu.uaz.SistemaControlEscolar.Design.Principal;
import mx.edu.uaz.SistemaControlEscolar.Design.PrincipalDesign;
import mx.edu.uaz.SistemaControlEscolar.Design.UsuariosTab;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of an HTML page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Viewport("width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no")
@Theme("TemaControl")
@StyleSheet({
	"vaadin://css/bootstrap.min.css",
})
@JavaScript({
	"vaadin://js/bootstrap.min.js",
	"vaadin://js/jquery-3.3.1.slim.min.js",
	"vaadin://js/popper.min.js",
})
public class ControlEscolarUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
    		String cadena = null;
    		String usuario = null;
    		try {
			cadena = vaadinRequest.getParameter("user");
			usuario = vaadinRequest.getParameter("logs");
		} catch (Exception e) {
			Notification.show("Parámetros no válidos");
		}
    		if (cadena != null && usuario != null) {
			setContent(new ConfirmarCuenta(usuario, cadena));
		}
    		else {
    			VaadinSession.getCurrent().getSession().setMaxInactiveInterval(150);
        		setContent(new Login());
    		}   
//    		setContent(new Carousel());
    }

    @WebServlet(urlPatterns = "/*", name = "ControlEscolarUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = ControlEscolarUI.class, productionMode = false)
    
    
    public static class ControlEscolarUIServlet extends VaadinServlet {
    
	    @Override
	    protected void servletInitialized() throws ServletException {
	    	    	super.servletInitialized();
	    	    	getService().setSystemMessagesProvider(new SystemMessagesProvider() {
						
						@Override
						public SystemMessages getSystemMessages(SystemMessagesInfo systemMessagesInfo) {
							CustomizedSystemMessages mensajes = new CustomizedSystemMessages();
							mensajes.setSessionExpiredCaption("Expiró la sesión");
							mensajes.setSessionExpiredMessage("El tiempo de sesión se terminó, "
									+ "<br> puedes actualizar la página o hacer click <i><b>aqui</b></i>");
							mensajes.setSessionExpiredURL("/SistemaControlEscolar");
							mensajes.setSessionExpiredNotificationEnabled(true);
							
							mensajes.setCommunicationErrorCaption("Error de comunicación con servidor");
							mensajes.setCommunicationErrorMessage("No existe comunicación con el servidor"
									+ "<br> Puedes recargar la página haciendo click <i><b>Aquí</b></i>");
							mensajes.setCommunicationErrorURL("/SistemaControlEscolar");
							mensajes.setCommunicationErrorNotificationEnabled(true);
							
							mensajes.setInternalErrorCaption("Ocurrió un error interno");
							mensajes.setInternalErrorMessage("La aplicación experimentó un error interno"
									+ "<br> Puedes recargar la página haciendo click <i><b>Aquí</b></i>");
							mensajes.setInternalErrorURL("/SistemaControlEscolar");
							mensajes.setInternalErrorNotificationEnabled(true);
							
														
							return mensajes;
						}
					});
	    }
    }
}
