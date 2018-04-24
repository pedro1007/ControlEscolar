package mx.edu.uaz.SistemaControlEscolar.modelos;

public class Estado {
	private int idEstado;
	private String estado;
	
	public Estado() {}

	public int getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(int idEstado) {
		this.idEstado = idEstado;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	@Override
	public String toString() {
		
		return estado;
	}

}
