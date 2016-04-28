import java.util.HashMap;
import java.util.Map.Entry;


public class Grada {

	private String Nombre_grada;
	private int Nº_max_localidades;
	private HashMap<String, Integer> Precios_Usuarios = new HashMap<String, Integer>();
	
	
	public Grada()
	 {
	     super();
	 }
	
	public Grada(String nombre_grada, int nº_max_localidades) {
		super();
		Nombre_grada = nombre_grada;
		Nº_max_localidades = nº_max_localidades;
	}

	
	public void addPU(String Tipo_Usuario, Integer precio)
	 {
		Precios_Usuarios.put(Tipo_Usuario, precio);
	 }
	

	public String getNombre_grada() {
		return Nombre_grada;
	}


	public void setNombre_grada(String nombre_grada) {
		Nombre_grada = nombre_grada;
	}


	public int getNº_max_localidades() {
		return Nº_max_localidades;
	}


	public void setNº_max_localidades(int nº_max_localidades) {
		Nº_max_localidades = nº_max_localidades;
	}


	public HashMap<String, Integer> getPrecios_Usuarios() {
		return Precios_Usuarios;
	}


	public void setPrecios_Usuarios(HashMap<String, Integer> precios_Usuarios) {
		Precios_Usuarios = precios_Usuarios;
	}
	
	public String toString()
	 {
		String imprimir="";
		imprimir=Nombre_grada+" - "+Nº_max_localidades+"\n";
		for(Entry<String, Integer> user : Precios_Usuarios.entrySet())
		 {
			imprimir+="\t\t\t"+user.getKey()+" - "+user.getValue()+"\n";
		 }
		
		return imprimir;
	 }
	
	
}
