import java.util.Date;


public class Evento {

	private int ID;
	private int ID_recinto;
	private Date fecha_inicio;
	private Date fecha_fin;
	private Date validez;
	private int porcentaje_devolución;
	
	public Evento()
	 {
	     super();
	 }
	
	
	public Evento(int iD, int iD_recinto, Date fecha_inicio, Date fecha_fin,
			Date validez, int porcentaje_devolución) {
		super();
		ID = iD;
		ID_recinto = iD_recinto;
		this.fecha_inicio = fecha_inicio;
		this.fecha_fin = fecha_fin;
		this.validez = validez;
		this.porcentaje_devolución = porcentaje_devolución;
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
	public int getPorcentaje_devolución() {
		return porcentaje_devolución;
	}
	public void setPorcentaje_devolución(int porcentaje_devolución) {
		this.porcentaje_devolución = porcentaje_devolución;
	}
	
	
	
	
}
