package mx.edu.uaz.SistemaControlEscolar.Design;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.Label;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;

public class MateriasTab extends VerticalLayout{
	private TabSheet tab;
	
	public MateriasTab() {
		tab = new TabSheet();
		tab.setId("tab-materias");
		tab.addTab(new Materias(), "Alta Materia", VaadinIcons.NEWSPAPER);
		tab.addTab(new MateriasLista(), "Lista de Materias", VaadinIcons.LIST);
		
		addComponent(tab);
		
	}
	

}
