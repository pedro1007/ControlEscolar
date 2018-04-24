package mx.edu.uaz.SistemaControlEscolar.Design;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.data.Binder;
import com.vaadin.data.converter.StringToIntegerConverter;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TabSheet;

import mx.edu.uaz.SistemaControlEscolar.enlacedatos.ADEstado;
import mx.edu.uaz.SistemaControlEscolar.enlacedatos.ADMateria;
import mx.edu.uaz.SistemaControlEscolar.enlacedatos.ADPrograma;
import mx.edu.uaz.SistemaControlEscolar.enlacedatos.ADUsuario;
import mx.edu.uaz.SistemaControlEscolar.modelos.Estado;
import mx.edu.uaz.SistemaControlEscolar.modelos.Materia;
import mx.edu.uaz.SistemaControlEscolar.modelos.Usuario;

public class Materias extends MateriasDesign {
	private Materia materia;
	private Binder<Materia> binder;
	private String caption;
	private boolean edicion;
	private ADMateria adMateria;
		
	public Materias(Materia materia) {
		caption = "Actualizar";
		this.materia = materia;
		edicion = true;
		
		enlaceDatos();
	}
	
	public Materias(){
		caption = "Registrar";
		materia = new Materia();
		edicion = false;
		
		enlaceDatos();
		
	}
		
	private void enlaceDatos() {
		binder = new Binder<Materia>(Materia.class);
		
		
		binder.setBean(materia);
		
		binder.forField(tfAsignatura)
			.asRequired("El nombre de la asignatura es requerido")
			.bind(Materia::getMateria, Materia::setMateria);
		
		binder.forField(tfCreditos)
			.asRequired("El número de créditos es requerido")
			.withConverter(
				    new StringToIntegerConverter("Debe ser numérico"))
			.bind(Materia::getCreditos, Materia::setCreditos);
		
		adMateria = new ADMateria();
		cboMateriaAnt.setItems(adMateria.obtenerTodasMaterias());
		
		binder.forField(cboMateriaAnt)
			.bind(Materia::getMateriaAnt, Materia::setMateriaAnt);
		
		ADPrograma adPrograma = new ADPrograma();
		cboPrograma.setItems(adPrograma.obtenerTodos());
		binder.forField(cboPrograma)
			.asRequired("El programa acadmémico es requerido")
			
			.bind(Materia::getPrograma, Materia::setPrograma);
		
		
		
		
		
		btnGuardar.setCaption(caption);
		
		btnGuardar.addClickListener(new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				if(binder.validate().isOk()) {
					adMateria = new ADMateria();
					boolean ok;
					String mensaje="Materia registrada con éxito";
					if (edicion) {
						ok = adMateria.modificarMateria(materia);
						mensaje="Usuario modificado con éxito";
					}
					else {
						ok = adMateria.altaMateria(materia);
					}
					if (ok) {
						TabSheet tab = (TabSheet) getParent();	
						if (edicion) {
							tab.setSelectedTab(1);
						}
						else {
							materia = new Materia();
							binder.setBean(materia);
							tab.replaceComponent(tab.getTab(1).getComponent(), new MateriasLista());
						}
						cboMateriaAnt.setItems(adMateria.obtenerTodasMaterias());
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
