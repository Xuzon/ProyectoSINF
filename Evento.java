import java.util.Date;


public class Evento {

	private int ID;
	private int ID_recinto;
	private String nombre_espectaculo;
	private String nombre_recinto;
	private Date fecha_inicio;
	private Date fecha_fin;
	private Date validez;
	private int porcentaje_devolucion;
	private int dur_max_pre_reserva;
	

	public Evento()
	 {
	     super();
	 }
	
	
	public Evento(int iD, int iD_recinto, String nombre_espectaculo, String nombre_recinto, Date fecha_inicio, Date fecha_fin,
			Date validez, int porcentaje_devolucion, int dur_max_pre_reserva) {
		super();
		ID = iD;
		ID_recinto = iD_recinto;
		this.nombre_espectaculo = nombre_espectaculo;
		this.nombre_recinto = nombre_recinto;
		this.fecha_inicio = fecha_inicio;
		this.fecha_fin = fecha_fin;
		this.validez = validez;
		this.porcentaje_devolucion = porcentaje_devolucion;
		this.dur_max_pre_reserva = dur_max_pre_reserva;
	}
	
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public int getID_recinto() {
		return ID_recinto;
	}
	public void setID_recinto(int iD_recinto) {
		ID_recinto = iD_recinto;
	}
	public Date getFecha_inicio() {
		return fecha_inicio;
	}
	public void setFecha_inicio(Date fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
	}
	public Date getFecha_fin() {
		return fecha_fin;
	}
	public void setFecha_fin(Date fecha_fin) {
		this.fecha_fin = fecha_fin;
	}
	public Date getValidez() {
		return validez;
	}
	public void setValidez(Date validez) {
		this.validez = validez;
	}
	public int getPorcentaje_devolucion() {
		return porcentaje_devolucion;
	}
	public void setPorcentaje_devolucion(int porcentaje_devolucion) {
		this.porcentaje_devolucion = porcentaje_devolucion;
	}


	@Override
	public String toString() {
		String tab1="\t\t", tab2="\t\t\t";
		if(nombre_recinto.length()>15) tab1="\t";
		if(nombre_espectaculo.length()>15) tab2="\t";
		return " "+ID+"\t"+nombre_espectaculo+tab2+nombre_recinto+tab1+fecha_inicio+"\t"+validez+"\t\t"+porcentaje_devolucion+"\t "+dur_max_pre_reserva;
	}
	

	
	
	
}
