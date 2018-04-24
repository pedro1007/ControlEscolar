package mx.edu.uaz.SistemaControlEscolar.Design;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.vaadin.dialogs.ConfirmDialog;

import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

import mx.edu.uaz.SistemaControlEscolar.enlacedatos.ADUsuario;
import mx.edu.uaz.SistemaControlEscolar.modelos.Usuario;

public class Principal extends PrincipalDesign{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Principal() {
		btnUsuarios.addClickListener(new ClickListener() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				contenedor.removeAllComponents();
				contenedor.addComponent(new UsuariosTab());
				
			}
		});
		Usuario usuario = (Usuario) VaadinSession.getCurrent().getAttribute("usuario");
		lblUsuario.setValue(usuario.getNombre());
		
		btnSalir.addClickListener(new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				ConfirmDialog.show(
					UI.getCurrent(), 
					"Confirmar Salir del Sistema:", 
					"¿Deseas salir del sistema?",
				    "Salir", "Cancelar", 
				    new ConfirmDialog.Listener() {
				        public void onClose(ConfirmDialog dialog) {
			                if (dialog.isConfirmed()) {
			                		VaadinSession.getCurrent().close();
			                		UI.getCurrent().setContent(new Login());
				              } 
		            }
		        });				
			}
		});
		
		btnMaterias.addClickListener(new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				contenedor.removeAllComponents();
				contenedor.addComponent(new MateriasTab());
				
			}
		});
		
	}
}
