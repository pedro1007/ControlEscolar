package mx.edu.uaz.SistemaControlEscolar.modelos;

public class Materia {
	private String materia;
	private int idMateria, creditos;
	private Materia materiaAnt;
	private Programa programa;
	
	public Materia() {
		
		
	}

	public String getMateria() {
		return materia;
	}

	public void setMateria(String materia) {
		this.materia = materia;
	}

	public int getIdMateria() {
		return idMateria;
	}

	public void setIdMateria(int idMateria) {
		this.idMateria = idMateria;
	}

	public int getCreditos() {
		return creditos;
	}

	public void setCreditos(int creditos) {
		this.creditos = creditos;
	}

	public Materia getMateriaAnt() {
		return materiaAnt;
	}

	public void setMateriaAnt(Materia materiaAnt) {
		this.materiaAnt = materiaAnt;
	}

	public Programa getPrograma() {
		return programa;
	}

	public void setPrograma(Programa programa) {
		this.programa = programa;
	}
	
	@Override
	public String toString() {		
		return materia;
	}
	
	
}
