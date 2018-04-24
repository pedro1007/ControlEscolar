package mx.edu.uaz.SistemaControlEscolar.Design;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.Label;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;

public class UsuariosTab extends VerticalLayout{
	private TabSheet tab;
	
	public UsuariosTab() {
		tab = new TabSheet();
		tab.setId("tab-usuarios");
		tab.addTab(new UsuarioAltaTab(), "Alta Usuario", VaadinIcons.NEWSPAPER);
		tab.addTab(new UsuariosLista(), "Lista de Usuarios", VaadinIcons.LIST);
		
		addComponent(tab);
		
	}
	

}
