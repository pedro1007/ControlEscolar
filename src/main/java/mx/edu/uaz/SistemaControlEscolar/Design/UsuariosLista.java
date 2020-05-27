package mx.edu.uaz.SistemaControlEscolar.Design;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.vaadin.dialogs.ConfirmDialog;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.renderers.ButtonRenderer;

import mx.edu.uaz.SistemaControlEscolar.enlacedatos.ADUsuario;
import mx.edu.uaz.SistemaControlEscolar.modelos.Usuario;
import mx.edu.uaz.SistemaControlEscolar.utils.BuscaComponentes;



public class UsuariosLista extends VerticalLayout {
	
	private Grid<Usuario> grid;
	private Button btnEliminar, btnReporte;
	
	public UsuariosLista() {
		grid = new Grid<Usuario>(Usuario.class);
		grid.setWidth("100%");
		
		ADUsuario adUsuario = new ADUsuario();
		
		grid.setItems(adUsuario.obtenerTodosUsuarios());
		
		grid.setSelectionMode(SelectionMode.MULTI);
		grid.setColumns("nombre","apellidoPaterno","apellidoMaterno","mail","fechaNac","usuario","estado");
		grid.getColumn("fechaNac").setCaption("Fecha de Nacimiento");
		grid.getColumn("mail").setCaption("E-mail");
		grid.getColumn("usuario").setCaption("Nombre de Usuario");
		
		grid.addColumn(usuario -> "Editar",
			      new ButtonRenderer(clickEvent -> {
			    	  	Usuario user = (Usuario) clickEvent.getItem();
			    	  	
			    	  	TabSheet tab = (TabSheet) BuscaComponentes.findComponentById(UI.getCurrent(), "tab-usuarios");
			    	  	tab.setSelectedTab(0);
			    	  	tab.replaceComponent(tab.getTab(0).getComponent(), new UsuarioAlta(user));
				    	  
			    	  	//Notification.show(user.getNombre());
			    }));
		
		
		btnEliminar = new Button("Eliminar", VaadinIcons.DEL);
		btnEliminar.addClickListener(new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				if	(!grid.getSelectedItems().isEmpty()) {
					ConfirmDialog.show(
							UI.getCurrent(), 
							"Confirmar eliminación:", 
							"¿Deseas relamente eliminar los registros?",
						    "Eliminar", "Cancelar", 
						    new ConfirmDialog.Listener() {
						        public void onClose(ConfirmDialog dialog) {
					                if (dialog.isConfirmed()) {
					                	Set<Usuario> usuarios = grid.getSelectedItems();
										List<Usuario> users = new ArrayList<Usuario>();
										users.addAll(usuarios);
										ADUsuario adUsuario = new ADUsuario();
										boolean ok = adUsuario.eliminarUsuarios(users);
										if (ok){
											grid.setItems(adUsuario.obtenerTodosUsuarios());
											Notification.show("Registros eliminados...",Notification.Type.WARNING_MESSAGE);
										}
						              } 
						            }
						        });
					}
					else
						Notification.show("Selecciona al menos un usuario para eliminar",Notification.Type.WARNING_MESSAGE);
				}
		});
		
		btnReporte = new Button("Generar constancia");
		btnReporte.addClickListener(new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				UI.getCurrent().addWindow(new VentanaReporte(0));
				
			}
		});
		
		addComponent(grid);
		addComponent(btnEliminar);
		addComponent(btnReporte);
		
	}
	

}
