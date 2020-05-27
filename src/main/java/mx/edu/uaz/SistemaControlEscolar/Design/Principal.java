package mx.edu.uaz.SistemaControlEscolar.Design;

import org.vaadin.dialogs.ConfirmDialog;

import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.JavaScript;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;

import mx.edu.uaz.SistemaControlEscolar.modelos.Usuario;
import uaz.edu.mx.SistemaControlEscolar.utils.CadenaAleatoria;
import uaz.edu.mx.SistemaControlEscolar.utils.SubirFoto;

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
		
		btnMaterias.addClickListener(new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				contenedor.removeAllComponents();
				contenedor.addComponent(new MateriasTab());
				
			}
		});
		
		SubirFoto subirFoto = new SubirFoto(imgUsuario, usuario);
		imgUsuario.setWidth("50px");
		imgUsuario.setSource(subirFoto.getFoto());
		imgUsuario.setId("userImg");
		
		String js = "var img=document.getElementById('userImg');"
				+ "img.src=img.src+'?id="+new CadenaAleatoria().getCadenaAleatoria(20)+"';";
		JavaScript.getCurrent().execute(js);
		
		
		MenuBar.Command command = new MenuBar.Command() {
			
			@Override
			public void menuSelected(MenuItem selectedItem) {
				if (selectedItem.getText() == "Salir") {
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
				else {
					contenedor.removeAllComponents();
					contenedor.addComponent(new UsuarioAlta(usuario));
					
				}
				
			}
		};
		MenuItem cuenta = menuPerfil.addItem(usuario.getUsuario(), null);
		MenuItem editar = cuenta.addItem("Editar Perfil", command);
		MenuItem salir = cuenta.addItem("Salir", command);
		
		
	}
}
