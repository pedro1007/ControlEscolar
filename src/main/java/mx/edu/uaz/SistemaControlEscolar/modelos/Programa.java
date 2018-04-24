package mx.edu.uaz.SistemaControlEscolar.modelos;

public class Programa {
	private int idPrograma;
	private String programa;
	
	public Programa() {
		
	}
	
	public int getIdPrograma() {
		return idPrograma;
	}
	public void setIdPrograma(int idPrograma) {
		this.idPrograma = idPrograma;
	}
	public String getPrograma() {
		return programa;
	}
	public void setPrograma(String programa) {
		this.programa = programa;
	}
	
	@Override
	public String toString() {
		return programa;
	}
	
}
