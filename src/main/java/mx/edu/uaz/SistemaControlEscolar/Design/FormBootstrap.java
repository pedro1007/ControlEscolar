package mx.edu.uaz.SistemaControlEscolar.Design;

import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;

public class FormBootstrap extends CustomLayout{
	private TextField tfMail;
	private PasswordField pfPassword;
	private CheckBox check;
	private Button btnEnviar;
	
	public FormBootstrap() {
		setTemplateName("formulario");
		tfMail = new TextField();
		tfMail.setPrimaryStyleName("form-control");
		tfMail.setPlaceholder("Enter email");
		
		pfPassword = new PasswordField();
		pfPassword.setPrimaryStyleName("form-control");
		pfPassword.setPlaceholder("Password");
		
		check = new CheckBox();
		check.setPrimaryStyleName("form-check-input");
		
		btnEnviar = new Button("Submit");
		btnEnviar.setPrimaryStyleName("btn btn-success");
		
		
		addComponent(tfMail, "mail");
		addComponent(pfPassword, "password");
		addComponent(check, "check");
		addComponent(btnEnviar, "enviar");
	}

}
