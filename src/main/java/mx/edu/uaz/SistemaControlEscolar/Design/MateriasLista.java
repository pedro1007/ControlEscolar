package mx.edu.uaz.SistemaControlEscolar.Design;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.omg.PortableInterceptor.USER_EXCEPTION;
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

import mx.edu.uaz.SistemaControlEscolar.enlacedatos.ADMateria;
import mx.edu.uaz.SistemaControlEscolar.enlacedatos.ADUsuario;
import mx.edu.uaz.SistemaControlEscolar.modelos.Estado;
import mx.edu.uaz.SistemaControlEscolar.modelos.Materia;
import mx.edu.uaz.SistemaControlEscolar.modelos.Usuario;
import mx.edu.uaz.SistemaControlEscolar.utils.BuscaComponentes;



public class MateriasLista extends VerticalLayout {
	
	private Grid<Materia> grid;
	private Button btnEliminar;
	
	public MateriasLista() {
		grid = new Grid<Materia>(Materia.class);
		grid.setWidth("100%");
		
		ADMateria adMateria = new ADMateria();
		
		grid.setItems(adMateria.obtenerTodasMaterias());
		
		grid.setSelectionMode(SelectionMode.MULTI);
		grid.setColumns("materia","creditos","programa","materiaAnt");
		grid.getColumn("creditos").setCaption("Créditos");
		grid.getColumn("materiaAnt").setCaption("Materia Antecesora");
				
		grid.addColumn(materia -> "Editar",
			      new ButtonRenderer(clickEvent -> {
			    	  	Materia materia = (Materia) clickEvent.getItem();
			    	  	Notification.show(String.valueOf(materia.getMateriaAnt().getIdMateria()));
//			    	  	TabSheet tab = (TabSheet) BuscaComponentes.findComponentById(UI.getCurrent(), "tab-materias");
//			    	  	tab.setSelectedTab(0);
//			    	  	tab.replaceComponent(tab.getTab(0).getComponent(), new Materias(materia));
//				    	  
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
					                		Set<Materia> usuarios = grid.getSelectedItems();
										List<Materia> mat = new ArrayList<Materia>();
										mat.addAll(usuarios);
										ADMateria adMateria = new ADMateria();
										boolean ok = adMateria.eliminarMaterias(mat);
										if (ok){
											grid.setItems(adMateria.obtenerTodasMaterias());
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
		
		addComponent(grid);
		addComponent(btnEliminar);
		
	}
	

}
