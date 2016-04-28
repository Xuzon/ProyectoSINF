
public class Espectaculo {
	
	private int ID;
	private String Nombre;
	private String Descripción;
	private String Tipo;

    public Espectaculo()
     {
         super();
     }

	public Espectaculo(int iD, String nombre, String descripción, String tipo) {
		super();
		ID = iD;
		Nombre = nombre;
		Descripción = descripción;
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

	public String getDescripción() {
		return Descripción;
	}

	public void setDescripción(String descripción) {
		Descripción = descripción;
	}

	public String getTipo() {
		return Tipo;
	}

	public void setTipo(String tipo) {
		Tipo = tipo;
	}
	
	@Override
	public String toString() {
		return "Espectaculo [ID=" + ID + ", Nombre=" + Nombre
				+ ", Descripción=" + Descripción + ", Tipo=" + Tipo + "]";
	}
	
	
}
