
public class Espectaculo {
	
	private int ID;
	private String Nombre;
	private String Descripcion;
	private String Tipo;

    public Espectaculo()
     {
         super();
     }

	public Espectaculo(int iD, String nombre, String descripcion, String tipo) {
		super();
		ID = iD;
		Nombre = nombre;
		Descripcion = descripcion;
		Tipo = tipo;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public String getDescripcion() {
		return Descripcion;
	}

	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}

	public String getTipo() {
		return Tipo;
	}

	public void setTipo(String tipo) {
		Tipo = tipo;
	}
	
	@Override
	public String toString() {
		String tab="\t\t\t";
		if(Nombre.length()>15) tab="\t";
		return "\t"+ID+"\t"+Nombre+tab+Tipo+"\t\t"+Descripcion;
	}
	
	
}
