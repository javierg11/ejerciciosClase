import java.io.Serializable;

public class Mensaje implements Serializable{
	private static final long serialVersionUID = 1L;
	private String orden, archivo;
	

	public String getOrden() {
		return orden;
	}

	public void setOrden(String orden) {
		this.orden = orden;
	}

	public String getArchivo() {
		return archivo;
	}

	public void setArchivo(String archivo) {
		this.archivo = archivo;
	}
	
	@Override
	public String toString() {
		return orden+" "+archivo;
	}
}
