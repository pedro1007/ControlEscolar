package mx.edu.uaz.SistemaControlEscolar.Design;

import com.vaadin.data.Binder;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;

import mx.edu.uaz.SistemaControlEscolar.enlacedatos.ADUsuario;
import mx.edu.uaz.SistemaControlEscolar.modelos.Usuario;

public class Login extends CustomLayout{
	
	private TextField tfUsuario;
	private Button btnSiguiente, btnCrear;
	private Binder<Usuario> binder;
	private Usuario usuario;
	private ADUsuario adUsuario;
	public Login() {
		setTemplateName("login");
		setStyleName("contenedor");
		setSizeFull();
		setResponsive(true);
		
		usuario = new Usuario();
		binder = new Binder<Usuario>(Usuario.class);
		binder.setBean(usuario);
		
		tfUsuario = new TextField();
		tfUsuario.setWidth("100%");
		tfUsuario.setStyleName("usuario");
		tfUsuario.setPlaceholder("Correo electrónico, teléfono o Skype");
		binder.forField(tfUsuario)
		.asRequired("El usuario es requerido")
		.bind(Usuario::getUsuario, Usuario::setUsuario);
		
		btnSiguiente = new Button("Siguiente");
		btnSiguiente.setWidth("100%");
		btnSiguiente.setStyleName("btn-siguiente");
		btnSiguiente.addClickListener(new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				adUsuario = new ADUsuario();
				Usuario user = adUsuario.buscaUsuario(usuario);
				if (user != null)
					UI.getCurrent().setContent(new Login2(user));	
				else
					Notification.show("El usuario no se encuentra",Notification.Type.ERROR_MESSAGE);
			}
		});
		
		//btnSiguiente.setStyleName(ValoTheme.BUTTON_PRIMARY);
		
		btnCrear = new Button("Cree una");
		btnCrear.setPrimaryStyleName("btn-crear");
		//btnCrear.setStyleName(ValoTheme.BUTTON_LINK);
		
		addComponent(tfUsuario, "usuario");
		addComponent(btnSiguiente, "siguiente");
		addComponent(btnCrear, "crear");
		
		
		btnCrear.addClickListener(new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				UI.getCurrent().setContent(new UsuarioAlta());
				
			}
		});
		
		
		
	}

}
