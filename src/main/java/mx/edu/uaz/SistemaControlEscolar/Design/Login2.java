package mx.edu.uaz.SistemaControlEscolar.Design;

import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.themes.ValoTheme;

import mx.edu.uaz.SistemaControlEscolar.logica.Hash;
import mx.edu.uaz.SistemaControlEscolar.modelos.Usuario;

public class Login2 extends CustomLayout{
	
	private PasswordField pfContra;
	private Button btnIniciar, btnAtras, btnRecuperar;
	private CheckBox chkMantener;
	private Label lbUsuario;
	
	public Login2(Usuario user) {
		setTemplateName("login2");
		setStyleName("contenedor");
		setSizeFull();
		setResponsive(true);
		
		pfContra = new PasswordField();
		pfContra.setWidth("100%");
		pfContra.setStyleName("usuario");
		pfContra.setPlaceholder("Contraseña");
		
		lbUsuario = new Label(user.getUsuario());
		lbUsuario.setStyleName("label-user");
		
		btnIniciar = new Button("Siguiente");
		btnIniciar.setWidth("100%");
		btnIniciar.setStyleName("btn-siguiente");
		//btnSiguiente.setStyleName(ValoTheme.BUTTON_PRIMARY);
		
		btnAtras = new Button("Atrás");
		btnAtras.setWidth("100%");
		btnAtras.setStyleName("btn-siguiente btn-atras");
		//btnCrear.setStyleName(ValoTheme.BUTTON_LINK);
		
		chkMantener = new CheckBox("Mantener la sesión iniciada");
		chkMantener.setStyleName("check");
		
		btnRecuperar = new Button("Olvidé mi contraseña");
		btnRecuperar.setPrimaryStyleName("btn-crear");
		
		addComponent(pfContra, "password");
		addComponent(btnIniciar, "siguiente");
		addComponent(btnAtras, "atras");
		addComponent(chkMantener, "check");
		addComponent(lbUsuario, "user");
		addComponent(btnRecuperar, "recuperar");
		
		btnIniciar.addClickListener(new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				if (user.getEstatus() == '0') {
					Notification.show("Tu cuenta aún no está activa, verfica tu correo",Notification.Type.WARNING_MESSAGE);
				}
				else {
					String contra = Hash.sha1(pfContra.getValue().toString());
					if (contra.equals(user.getPassword())) {
						VaadinSession.getCurrent().setAttribute("usuario", user);
						UI.getCurrent().setContent(new Principal());
					}
					else
						Notification.show("La contraseña es incorrecta", Notification.Type.ERROR_MESSAGE);
				}
											
			}
		});
		
	}

}
