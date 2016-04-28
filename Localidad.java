
public class Localidad {

	private int ID;
	private String Nombre_Grada;
	private int ID_recinto;
	
	public Localidad()
	 {
	     super();
	 }
	
	public Localidad(int iD, String nombre_Grada, int iD_recinto) {
		super();
		ID = iD;
		Nombre_Grada = nombre_Grada;
		ID_recinto = iD_recinto;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getNombre_Grada() {
		return Nombre_Grada;
	}
	public void setNombre_Grada(String nombre_Grada) {
		Nombre_Grada = nombre_Grada;
	}
	public int getID_recinto() {
		return ID_recinto;
	}
	public void setID_recinto(int iD_recinto) {
		ID_recinto = iD_recinto;
	}
	@Override
	public String toString() {
		return "Localidad [ID=" + ID + ", Nombre_Grada=" + Nombre_Grada
				+ ", ID_recinto=" + ID_recinto + "]";
	}
	
	
	
}
