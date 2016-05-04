public class Entrada {
private int ID;
private int ID_localidad;
private String nombre_grada;
private String tipo_usuario;
public Entrada(int iD, int iD_localidad, String nombre_grada,
		String tipo_usuario) {
	super();
	ID = iD;
	ID_localidad = iD_localidad;
	this.nombre_grada = nombre_grada;
	this.tipo_usuario = tipo_usuario;
}
public Entrada() {
	super();
}

public int getID() {
	return ID;
}
public void setID(int iD) {
	ID = iD;
}
public int getID_localidad() {
	return ID_localidad;
}
public void setID_localidad(int iD_localidad) {
	ID_localidad = iD_localidad;
}
public String getNombre_grada() {
	return nombre_grada;
}
public void setNombre_grada(String nombre_grada) {
	this.nombre_grada = nombre_grada;
}
public String getTipo_usuario() {
	return tipo_usuario;
}
public void setTipo_usuario(String tipo_usuario) {
	this.tipo_usuario = tipo_usuario;
}

@Override
public String toString() {
	return ID + "\t   " + ID_localidad + "\t\t   " + tipo_usuario ;
}
}

