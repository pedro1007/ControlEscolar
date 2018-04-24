package mx.edu.uaz.SistemaControlEscolar.Design;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.data.Binder;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TabSheet;

import mx.edu.uaz.SistemaControlEscolar.enlacedatos.ADEstado;
import mx.edu.uaz.SistemaControlEscolar.enlacedatos.ADUsuario;
import mx.edu.uaz.SistemaControlEscolar.modelos.Estado;
import mx.edu.uaz.SistemaControlEscolar.modelos.Usuario;

public class UsuarioAltaTab extends UsuarioAltaDesign {
	private Usuario usuario;
	private Binder<Usuario> binder;
	private String caption;
	private boolean edicion;
		
	public UsuarioAltaTab(Usuario usuario) {
		caption = "Actualizar";
		this.usuario = usuario;
		edicion = true;
		
		enlaceDatos();
	}
	
	public UsuarioAltaTab(){
		caption = "Registrar";
		usuario = new Usuario();
		edicion = false;
		
		enlaceDatos();
		
	}
		
	private void enlaceDatos() {
		binder = new Binder<>(Usuario.class);
		
		
		binder.setBean(usuario);
		
		binder.forField(tfNombre)
			.asRequired("El nombre es requerido")
			.bind(Usuario::getNombre, Usuario::setNombre);
		
		binder.forField(tfApellidoPaterno)
			.asRequired("El apellido paterno es requerido")
			.bind("apellidoPaterno");
		
		binder.forField(tfApellidoMaterno)
			.asRequired("El apellido materno es requerido")
			.bind("apellidoMaterno");
		
		binder.forField(tfMail)
			.asRequired("El correo es requerido")
			.withValidator(
					new EmailValidator("El mail {0} no tiene un formato correcto") )
			.bind("mail");
		
		binder.forField(tfUsuario)
			.asRequired("El usuario es requerido")
			.bind("usuario");
		
		binder.forField(pfPassword)
			.asRequired("La contraseña es requerida")
			.withValidator(
					new RegexpValidator(
							"Formato de contraseña no válido, debe contener una mayúscula, una minúscula y un dígito", 
							"^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$"
					)
			)
			.bind("password");
		
		binder.forField(dfFechaNac)
			.asRequired("La fecha de nacimiento es requerida")
			
			.bind(Usuario::getFechaNac, Usuario::setFechaNac);
		
		ADEstado adEstado = new ADEstado();
		cboEstado.setItems(adEstado.obtenerTodos());
		binder.forField(cboEstado)
			.asRequired("El estado es requerido")
			.bind(Usuario::getEstado, Usuario::setEstado);
		
		btnRegistrar.setCaption(caption);
		
		btnRegistrar.addClickListener(new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				if(binder.validate().isOk()) {
					ADUsuario adUsuario = new ADUsuario();
					usuario.setPerfil(1);
					boolean ok;
					String mensaje="Usuario registrado con éxito";
					if (edicion) {
						ok = adUsuario.modificarUsuario(usuario);
						mensaje="Usuario modificado con éxito";
					}
					else {
						ok = adUsuario.altaUsuario(usuario);
						
					}
					if (ok) {
						TabSheet tab = (TabSheet) getParent();	
						if (edicion) {
							tab.setSelectedTab(1);
						}
						else {
							usuario = new Usuario();
							binder.setBean(usuario);
							tab.replaceComponent(tab.getTab(1).getComponent(), new UsuariosLista());
						}
						Notification.show(mensaje,Notification.Type.HUMANIZED_MESSAGE);
					}
					
				}
				else{
					Notification.show(
							"Verifica la información",
							Notification.Type.ERROR_MESSAGE
					);
				}
				
				
			}
		});
	}
}
