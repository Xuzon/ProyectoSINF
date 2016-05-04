import java.util.ArrayList;
import java.util.Date;


public class Reserva {
	private int ID;
	private String DNI_cliente;
	private int ID_evento;
	private Date fecha;
	private boolean pagada;
	private int precio;
	private String nombre_evento;
	private String nombre_grada;
	private ArrayList <Entrada> lista_entradas= new ArrayList <Entrada>();
	public Reserva(int id,String dni,int id_evento, String nombre_evento, Date fecha, boolean pagado, int precio)
	{
		ID=id;
		DNI_cliente=dni;
		ID_evento=id_evento;
		this.fecha=fecha;
		pagada=pagado;
		this.nombre_evento=nombre_evento;
		this.precio = precio;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getDNI_cliente() {
		return DNI_cliente;
	}
	public void setDNI_cliente(String dNI_cliente) {
		DNI_cliente = dNI_cliente;
	}
	public int getID_evento() {
		return ID_evento;
	}
	public void setID_evento(int iD_evento) {
		ID_evento = iD_evento;
	}
	public boolean isPagada() {
		return pagada;
	}
	public void setPagada(boolean pagada) {
		this.pagada = pagada;
	}
	public void add_entrada(int iD_entrada, int iD_localidad, String nombre_grada,
			String tipo_usuario)
	 {
		this.nombre_grada=nombre_grada;
		Entrada entrada= new Entrada(iD_entrada,iD_localidad,nombre_grada,tipo_usuario);
		lista_entradas.add(entrada);
	 }
	public ArrayList<Entrada> get_entradas()
	{
		return lista_entradas;
	}
	@Override
	public String toString() {
		 String retorno = "";
		 retorno = "ID=" + ID + ", " +
		 		"Evento=" + nombre_evento + ", Nombre Grada=" + nombre_grada + ", Fecha="+ fecha + ", pagada=" + pagada + ", precio="+precio+"€";
		 		retorno+="\n\t\t\tID\tID localidad\tTipo Usuario";
		 		retorno+="\n\t\t\t------------------------------------";
		 for(int i=0; i<lista_entradas.size(); i++)
		  {
			 retorno+="\n\t\t\t"+lista_entradas.get(i).toString();
		  }
		 retorno += "\n";
		 
		 return retorno;
	}
	public String getNombre_grada() {
		return nombre_grada;
	}
	public void setNombre_grada(String nombre_grada) {
		this.nombre_grada = nombre_grada;
	}
	

}
