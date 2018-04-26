package mx.edu.uaz.SistemaControlEscolar.Design;

import com.vaadin.data.Binder;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;

import mx.edu.uaz.SistemaControlEscolar.enlacedatos.ADUsuario;
import mx.edu.uaz.SistemaControlEscolar.modelos.Usuario;

public class ConfirmarCuenta extends CustomLayout{
	
	private Label lblRespuesta;
	private Button btnIngresar;
	private Usuario usuario;
	private ADUsuario adUsuario;
	public ConfirmarCuenta(String usuarioParam, String cadena) {
		setTemplateName("confirmar_cuenta");
		setStyleName("contenedor");
		setSizeFull();
		setResponsive(true);
		
		usuario = new Usuario();
		usuario.setCadena(cadena);
		usuario.setUsuario(usuarioParam);
		
		lblRespuesta = new Label();
		
		adUsuario = new ADUsuario();
		boolean ok = adUsuario.confirmaCuenta(usuario);
		if (ok) {
			Notification.show("Cuenta activada con éxito", Notification.Type.WARNING_MESSAGE);
			lblRespuesta.setValue("Cuenta activada con éxito");
		}
		else {
			Notification.show("No se pudo activar tu cuenta",
					"Puede ser que el link no sea correcto, contacta al administrador", 
					Notification.Type.WARNING_MESSAGE);
			lblRespuesta.setValue("No se pudo activar tu cuenta");
		}
		
		
		btnIngresar = new Button("Ingresar");
		btnIngresar.setWidth("100%");
		btnIngresar.setStyleName("btn-siguiente");
		btnIngresar.addClickListener(new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				UI.getCurrent().setContent(new Login());
			}
		});	
		
		addComponent(lblRespuesta, "respuesta");
		addComponent(btnIngresar, "ingresar");		
		
	}

}
