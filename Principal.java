import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map.Entry;


public class Principal {

	/**
	 * @param args
	 */
	static final String USER = "cliente_taquilla";
	static final String PASS = "12345";
	static final String URL = "jdbc:mysql://localhost:3306/";
	static final String DB = "taquilla";
	static Connection conn;
	String DNI;
	
	
	public static void main(String[] args) {
		
		conectar();
	    Principal  p = new Principal();
	    p.lanzar();
	}

	
	/**
	 * Permite conectarse con la base de datos utilizando los parámetros globales de la clase.
	 * Añade el objeto conexión como variable de la clase para que todos los métodos puedan acceder.
	 */
	public static void conectar()
	 {
		conn = null;
		try
		 {
		   Class.forName("com.mysql.jdbc.Driver").newInstance();
		   conn = DriverManager.getConnection(URL+DB+"?noAccessToProcedureBodies=true", USER, PASS);
		   
		 }
		catch(SQLException se)
		 {
			System.out.println("SQLException: " + se.getMessage());
		    System.out.println("SQLState: " + se.getSQLState());
		    System.out.println("VendorError: " + se.getErrorCode());
		 }
		catch(Exception e)
		 {
			e.printStackTrace();
		 }
		
		if (conn != null)
		 {
			System.out.println("Conexión correcta con la base de datos: "+DB+".");
		 }
		else
		 {
			System.out.println("Error de conexión.");
			return;
		 }		 
	 }
	
	/**
	 * Método para realizar temporalmente las pruebas de los diferentes métodos del sistema.
	 */
	@SuppressWarnings("deprecation")
	public void lanzar()
	 {
		 		
		DNI="12345678A";
		
	    HashMap<Integer, String> mapa = new  HashMap<Integer, String>();
		
		mapa.put(4, "Adulto");
		mapa.put(12, "Adulto");
		mapa.put(3, "Adulto");
		mapa.put(1, "Adulto");
		mapa.put(2, "Adulto");
		mapa.put(5, "Adulto");
		mapa.put(6, "Adulto");
		
		
		//System.out.println(reservarEntradas(8, "Patio butacas", mapa));
		 
		
		//System.out.println(pagarReserva(37));
		/*
		ArrayList<Grada> gradas = new ArrayList<Grada>();
		gradas = listarGradas(4);
		
		for(int i=0; i<gradas.size(); i++)
			System.out.println(gradas.get(i));
			*/
		
		//System.out.println(modificarEntrada(17, 0, ""));
		/*
		ArrayList<Evento> eventos = new ArrayList<Evento>();
		Date f = new Date();
		f.setDate(30);
		f.setHours(16);
		f.setMinutes(00);
		f.setSeconds(05);
		eventos = listarEventos(0,"",0, f);
		for(int i=0; i<eventos.size(); i++)
			System.out.println(eventos.get(i));
		
		*/
		
		ArrayList<Reserva> reservas= new ArrayList<Reserva>();
		reservas = listarReserva();
		
		for(int i=0; i<reservas.size(); i++)
			System.out.println(reservas.get(i));
		
		/*
		ArrayList<Espectaculo> espectaculos= new ArrayList<Espectaculo>();
		espectaculos = listarEspectaculos("El Rey León", "");
		
		for(int i=0; i<espectaculos.size(); i++)
			System.out.println(espectaculos.get(i).toString());
		*/
		
		
		System.out.println(anularReserva(30));
		
	 }
	
	
	/**
	 * Este método realiza una búsqueda de espectáculos y los lista.
	 * @param Nombre -> Nombre del espectáculo (En caso de pasar una cadena vacia, busca uno cualquiera).
	 * @param tipo -> Tipo de espectáculo (En caso de pasar una cadena vacia, busca uno cualquiera).
	 * @return -> Lista de espectáculos encontrados.
	 */
	public ArrayList<Espectaculo> listarEspectaculos(String Nombre, String tipo)
	 {
		ArrayList<Espectaculo> lista = new ArrayList<Espectaculo>();
		Statement stt;
		int ID;
		String nombre, Descripcion;
		String name=Nombre, type=tipo;
		ResultSet cursor;
		Espectaculo espe;
		
		if(Nombre==null || Nombre.equals(""))
			name = "%";
		if(tipo==null || tipo.equals(""))
			type = "%";
		try
		 {
			
			stt=conn.createStatement();
			cursor=stt.executeQuery("Select * from Espectaculos where Nombre like '"+name+"' and Tipo like '"+type+"';");
			
			while(cursor.next())
			{
				ID=cursor.getInt("ID");
				nombre=cursor.getString("Nombre");
				Descripcion=cursor.getString("Descripcion");
				tipo=cursor.getString("Tipo");
				espe=new Espectaculo(ID,nombre,Descripcion,tipo);
				lista.add(espe);
			}
		 }
		catch(SQLException se)
		 {
			System.out.println("SQLException: " + se.getMessage());
		    System.out.println("SQLState: " + se.getSQLState());
		    System.out.println("VendorError: " + se.getErrorCode());
		 }
		catch(Exception e)
		 {
			e.printStackTrace();
		 }
		return lista;
	 }
	
	
	/**
	 * Busca los eventos existentes que coincidan con los parámetros que se le pasan.
	 * @param ID_espectaculo -> Identificador del espectáculo al que corresponde el evento (Si es 0, se busca cualquiera).
	 * @param ciudad -> Ciudad donde se celebra el evento (En caso de pasar una cadena vacia, busca uno cualquiera).
	 * @param id_recinto -> Identificador del recinto donde se celebra el espectáculo (Si es 0, se busca cualquiera).
	 * @param fecha -> Fecha en la que se celebra el evento. (Si se pasa a null, busca cualquiera). (En caso de pasar los segundos a 0, este buscara la fecha y la hora exacata, en otro caso solo la fecha exacta.)
	 * @return -> Listado de eventos.
	 */
	@SuppressWarnings("deprecation")
	public ArrayList<Evento> listarEventos(int ID_espectaculo, String ciudad, int id_recinto, Date fecha)
	 {
		ArrayList<Evento> lista = new ArrayList<Evento>();
		try
		 {
			int id_e,id_r,porc_d;
			Date f_i,f_f,f_v;
			
			String i_e;
			String c = "%";
			String recinto;
			String f = "%";
			if(ID_espectaculo != 0) i_e = "="+Integer.toString(ID_espectaculo); else i_e = "";
			if(id_recinto != 0) recinto = "="+Integer.toString(id_recinto); else recinto="";
			if(ciudad != null && !ciudad.equals("")) c = ciudad;
			if(fecha != null)
			 {
				SimpleDateFormat formatter = null;
				if(fecha.getSeconds()==0)
					formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				else
					formatter = new SimpleDateFormat("yyyy-MM-dd");
				
				f = formatter.format(fecha)+"%";
			 }
			Statement st = conn.createStatement();
			String consulta = "select Eventos.* from Recintos,Eventos where Eventos.ID_recinto = Recintos.ID and poblacion LIKE '"+c+"' and ID_recinto"+recinto+" and Fecha_inicio LIKE '"+f+"' and ID_espectaculo"+i_e+" ORDER BY ID;";
			ResultSet rs = st.executeQuery(consulta);
			while(rs.next())
			{
				id_e = rs.getInt("ID");
				id_r = rs.getInt("ID_recinto");
				f_i = rs.getTimestamp("Fecha_inicio");
				f_f = rs.getTimestamp("Fecha_fin");
				f_v = rs.getTimestamp("Validez");
				porc_d = rs.getInt("Porcentaje_devolucion");
				lista.add(new Evento(id_e,id_r,f_i,f_f,f_v,porc_d));
			}
		}
		catch(SQLException se)
		 {
			System.out.println("SQLException: " + se.getMessage());
		    System.out.println("SQLState: " + se.getSQLState());
		    System.out.println("VendorError: " + se.getErrorCode());
		 }
		catch(Exception e)
		 {
			System.out.println("Error: "+e.getMessage());
		 }
		
		return lista;
	 }
	
	
	
	/**
	 * Listado de las gradas disponibles para un evento. En cada grada se puede obtener el precio por cada tipo de usuario distinto.
	 * @param ID_evento -> Identificador del evento.
	 * @return -> Listado de gradas.
	 */
	public ArrayList<Grada> listarGradas(int ID_evento)
	 {
		ArrayList<Grada> lista = new ArrayList<Grada>();
		
		ResultSet result;
		String nombre="", nombre_ant="";
		Grada grada = null;
		try
		 {
			Statement stmt = conn.createStatement();
			result = stmt.executeQuery("SELECT G.*, PU.Tipo_Usuario, PU.Precio  FROM Precios_Usuarios AS PU, Eventos AS E, Gradas AS G WHERE ID_Evento=E.ID AND G.Nombre=PU.Nombre_grada AND E.ID_recinto=G.ID_recinto AND ID_evento="+ID_evento+" ORDER BY PU.Nombre_grada;");
			while(result.next())
			 {
				nombre = result.getString("Nombre");
				
				if(!nombre.equals(nombre_ant))
				 {	
					grada = new Grada(nombre, result.getInt("Nº_max_localidades"));
				 }
				
				grada.addPU(result.getString("Tipo_usuario"), result.getInt("Precio"));
				
				if(!nombre.equals(nombre_ant))
				 {	
					nombre_ant = nombre;
					lista.add(grada);
				 }
				
			 }
	     }
		catch(SQLException se)
		 {
			System.out.println("SQLException: " + se.getMessage());
		    System.out.println("SQLState: " + se.getSQLState());
		    System.out.println("VendorError: " + se.getErrorCode());
		 }
		catch(Exception e)
		 {
			System.out.println("Error: "+e.getMessage());
		 }
		 
		return lista;
	 }
	
	
	/**
	 * Listado de localidades (disponibles) de un evento en concreto para una grada
	 * Disponibles: Solo lista las localidades  que no están ocupadas para ese evento, que están en una grada asignada para ese evento y que no estén defectuosas.
	 * Además, en caso de encontrar una grada pre-reservada pero que su plazo de como pre-reserva esté espirado, esta también se mostrará como libre para tener la posivilidad de reservarla.
	 * @param ID_evento -> Identificador del evento al que queremos asistir.
	 * @param Nombre_grada -> Nombre de la grada de donde queremos ver las localidades.
	 * @return -> Listado de localidades.
	 */
	public ArrayList<Localidad> listarLocalidades(int ID_evento, String Nombre_grada)
	 {
		ArrayList<Localidad> lista = new ArrayList<Localidad>();
		
		ResultSet result;
		String Nombre_g="";
		int id=0, id_r=0;
		
		String query = "";
		query = "SELECT L.* FROM Localidades as L, Precios_Usuarios as PU" +
				" WHERE PU.Nombre_grada=L.Nombre_grada AND L.Nombre_grada="+Nombre_grada+" AND PU.ID_evento="+ID_evento+
				" AND L.Estado=1 AND L.ID NOT IN (SELECT E.ID_localidad FROM Entradas as E, Reservas as R, Eventos as EV" +
					" WHERE E.ID_reserva=R.ID AND R.ID_evento=EV.ID AND ID_evento="+ID_evento+" AND Nombre_grada="+Nombre_grada+
					" AND TIMESTAMPDIFF(MINUTE, R.Fecha, NOW())/60<Duracion_max_pre_reserva)" +
				" GROUP BY ID;";
		
		try
		 {
			Statement stmt = conn.createStatement();
			result = stmt.executeQuery(query);
			while(result.next())
			 {
				id = result.getInt("ID");
				Nombre_g = result.getString("Nombre_grada");
				id_r = result.getInt("ID_recinto");
				lista.add(new Localidad(id, Nombre_g, id_r));
				
			 }
	     }
		catch(SQLException se)
		 {
			System.out.println("SQLException: " + se.getMessage());
		    System.out.println("SQLState: " + se.getSQLState());
		    System.out.println("VendorError: " + se.getErrorCode());
		 }
		catch(Exception e)
		 {
			System.out.println("Error: "+e.getMessage());
		 }
			
		return lista;
	 }
	
	/**
	 * Permite reservar un grupo de entradas (localidades), en una grada para un evento.
	 * @param ID_evento -> Identificador del evento para el que se quiere reservar.
	 * @param Nombre_grada -> Nombre de la grada en la que se quieren reservar las localidades.
	 * @param Localidad_Usuario -> Mapa con las localidades que se desean reservar:
	 * 							   Key -> Identificador de la localidad que se quiere reservar en la grada.
	 * 							 Value -> Tipo del usuario para el que se quiere reservar la localidad correspondiente.
	 * @return  -> Identificador de la reserva en caso de que todo fuese correcto.
	 * 		-1  -> El DNI introducido no existe para ningún cliente-
	 *		-2  -> No existe el evento introducido.
	 *		-3  -> No existe la reserva introducida.
	 *		-4  -> No se pueden añadir entrada a una reserva (está pagada)
	 *		-5  -> Error de SQL.
     *		-11 -> La localidad está ocupada.
	 *		-12 -> La grada donde se situa esta localidad no está disponible en este evento para el usuario indicado.
	 *		-13 -> La localidad está deteriorada.
	 *		-14 -> La localidad no existe para esta grada.
	 */
	public int reservarEntradas(int ID_evento, String Nombre_grada, HashMap<Integer, String> Localidad_Usuario)
	 {
		int id_r=0;
		try
		 {
			conn.setAutoCommit(false);

			CallableStatement cstmt = conn.prepareCall("{CALL Reservar_entradas(?, ?, ?, ?, ?, ?, ?)}");
			for(Entry<Integer, String> l_u : Localidad_Usuario.entrySet())
			 {
				cstmt.setInt(1, id_r);
				cstmt.setString(2, DNI);
				cstmt.setInt(3, ID_evento);
				cstmt.setString(4, l_u.getValue());//Tipo
				cstmt.setInt(5, l_u.getKey());//Localidad
				cstmt.setString(6, Nombre_grada);
				cstmt.setInt(7, 0);//Esto anula la transacción del propio procedure y solo utiliza la transacción de la función java.
		
				cstmt.registerOutParameter(1, java.sql.Types.INTEGER);
				cstmt.executeUpdate();
				id_r = cstmt.getInt(1);
				if(id_r<0)
				 {
					conn.rollback();
					break;
				 }
			 }
			if(id_r>0)
				conn.commit();
						
		 }
		catch(SQLException se)
		 {
			System.out.println("SQLException: " + se.getMessage());
		    System.out.println("SQLState: " + se.getSQLState());
		    System.out.println("VendorError: " + se.getErrorCode());
		 }
		catch(Exception e)
		 {
			System.out.println("Error: "+e.getMessage());
		 }
		finally
		 {
			try {
				conn.setAutoCommit(true);
			}
			catch(Exception e)
			 {
				e.printStackTrace();
			 }
		 }
		
		return id_r;
	 }
	

	/**
	 * Lista todas las reservas de un cliente.
	 * @return -> Lista de reservas, con sus correspondientes entradas.
	 */
	public ArrayList<Reserva> listarReserva()
	 {
		ArrayList<Reserva> lista = new ArrayList<Reserva>();
		Reserva res=null;
		Statement stt;
		ResultSet cursor;
		String DNI_cliente,nombre_grada,tipo_usuario;
		int ID,ID_evento,ID_entrada,ID_localidad,ID_ant=0;
		boolean pagada;
		try
		{
			stt=conn.createStatement();
			cursor=stt.executeQuery("Select * from Reservas as r,Entradas as e where DNI_cliente='"+DNI+"' and r.ID=e.ID_reserva order by r.ID;");
			while(cursor.next())
			{
				ID=cursor.getInt("ID");
				if(ID!=ID_ant)
				{
					DNI_cliente=cursor.getString("DNI_cliente");
					ID_evento=cursor.getInt("ID_evento");
					pagada=cursor.getBoolean("pagada");
					res=new Reserva(ID,DNI_cliente,ID_evento,pagada);
				}
				ID_entrada=cursor.getInt("e.ID");
				ID_localidad=cursor.getInt("ID_localidad");
				nombre_grada=cursor.getString("Nombre_grada");
				tipo_usuario=cursor.getString("Tipo_usuario");
				res.add_entrada(ID_entrada, ID_localidad, nombre_grada, tipo_usuario);
				if(ID!=ID_ant)
				{
					ID_ant=ID;
					lista.add(res);
				}
			}
		}
		catch(SQLException se)
		 {
			System.out.println("SQLException: " + se.getMessage());
		    System.out.println("SQLState: " + se.getSQLState());
		    System.out.println("VendorError: " + se.getErrorCode());
		 }
		catch(Exception e)
		 {
			e.printStackTrace();
		 }
		return lista;
	 }
	
	
	
	/**
	 * Esta función permite realizar el pago de una reserva del cliente con el DNI introducido en el sistema.
	 * @param ID_reserva -> Identificador de la reserva que deseamos pagar.
	 * @return ->  1 En caso de realizar el pago de forma correcta.
	 * 		   -> -1 En caso de no existir la reserva con ese identificador para el cliente con ese DNI.
	 *         -> -2 En caso de que la reserva seleccionada ya estea pagada.
	 *         -> -3 En caso de error directo al realizar el cambio en la base de datos a pagado.
	 *		 -100 -> Excepción
	 */ 
	public int pagarReserva(int ID_reserva)
	 {
		int precio=0, sal=0;
		ResultSet result;
		try
		 {
			Statement stmt = conn.createStatement();
			result = stmt.executeQuery("SELECT BIN(Pagada) AS Pagada FROM Reservas WHERE ID="+ID_reserva+" AND DNI_cliente='"+DNI+"';");
			if(result.next())
			 {
				if(result.getInt("Pagada")==1)
				 {
					return -2;
				 }
			 }
			else
			 {
				return -1;
			 }
			CallableStatement cstmt = conn.prepareCall("{CALL Precio_reserva(?, ?)}");
			cstmt.setInt(1, ID_reserva);
			
			cstmt.registerOutParameter(2, java.sql.Types.INTEGER);
			cstmt.executeUpdate();
			precio = cstmt.getInt(2);
			
			System.out.println("\n\nEstamos realizando el pago de la reserva: "+ID_reserva+".");
			System.out.println("\tTotal: "+precio+"\n");
			
			for(int i=0; i<5; i++)
			 {
				System.out.print(".");
				Thread.sleep(1000);
			 }
			
			
			sal = stmt.executeUpdate("UPDATE Reservas SET Pagada=1 WHERE ID="+ID_reserva+";");
			if(sal<0)
			 {
				return -3;
			 }
			else
			 {
				System.out.println(" Pago OK.");
			 }
		 }
		catch(SQLException se)
		 {
			System.out.println("SQLException: " + se.getMessage());
		    System.out.println("SQLState: " + se.getSQLState());
		    System.out.println("VendorError: " + se.getErrorCode());
		    return -100;
		 }
		catch(Exception e)
		 {
			System.out.println("Error: "+e.getMessage());
			return -100;
		 }
		
		return 1;
	 }
	
	
	
/**
	 * Permite anular una reserva. En caso de no estar pagada (pre-reserva), simplemente se eliminará la reserva con sus correspondientes entradas.
	 * Si la reserva ya ha sido pagada se le devolverá el importe total de la reserva, siempre que no pase del periodo de validez,
	 * en este caso el importe que se le devolverá será una parte propocional del total dependiente del evento.
	 * @param ID_reserva -> Identificador de la reserva que se desea anular.
	 * @return
	 * 		    1 -> Correcto. La reserva se ha anulado corrrectamente. (Se devuelve el importe íntegro de la reserva, no ha superado la fecha de validez)
	 * 	 	    2 -> Correcto. La reserva se ha anulado corrrectamente. (Se devuelve un porcentaje del importe de la reserva dependiendo del evento, se ha superado la fecha de validez)
	 * 			3 -> Correcto. La pre-reserva se ha anulado correctamente. (No se devuelve dinero porque no habia sido pagada).
	 * 		   -1 -> Error. No tiene ninguna reserva con este ID.
	 *	 	   -2 -> Error. No se puede eliminar porque el evento ya se ha celebrado.
	 *		   -3 -> Error al eliminar la reserva.
	 *		 -100 -> Excepción
	 */
	public int anularReserva(int ID_reserva)
	 {
		Statement stt;
		ResultSet result;
		int precio,retencion;
		try
		 {
				stt=conn.createStatement();
				result = stt.executeQuery("SELECT ID FROM Reservas WHERE ID="+ID_reserva+" AND DNI_cliente='"+DNI+"';");
				if(result.next())
				 {
					CallableStatement cstmt = conn.prepareCall("{CALL calcula_devolucion(?,?,?)}");
					cstmt.setInt(1, ID_reserva);
					cstmt.registerOutParameter(2, java.sql.Types.INTEGER);
					cstmt.registerOutParameter(3, java.sql.Types.INTEGER);
					cstmt.executeUpdate();
					precio=cstmt.getInt(2);
					retencion=cstmt.getInt(3);
					if(precio==-2 || precio==-1)
						return precio;
					else
					 {
						int error =stt.executeUpdate("Delete from Reservas where ID="+ID_reserva+" and DNI_cliente='"+DNI+"';");
						if(error<0)
							return -3;
						
						if(precio>0)
						 {
							System.out.println("\n\nEstamos abonando el importe de la reserva: "+ID_reserva+".");
							System.out.println("\tTotal: "+precio+"\n");
							for(int i=0; i<5; i++)
							 {
								System.out.print(".");
								Thread.sleep(1000);
							 }
							if(retencion==1)
								return 2;
						 }
						else if(precio==-3)
							return 3;
					 }
				 }
				else
				 {
					return -1;
				 }
		 }
		catch(SQLException se)
		 {
			System.out.println("SQLException: " + se.getMessage());
		    System.out.println("SQLState: " + se.getSQLState());
		    System.out.println("VendorError: " + se.getErrorCode());
		    return -100;
		 }
		catch(Exception e)
		 {
			e.printStackTrace();
			return -100;
		 }
		return 1;
	 }
	
	
	
	
	/**
	 * Permite modificar una entrada perteneciente a un pre-reserva (No puede estar pagada).
	 * @param ID_entrada -> Identificador de la entrada que se desea modificar.
	 * @param ID_localidad_nueva -> Identificador de la localidad nueva a la que se quiere modificar (En caso de 0, no se modificará)
	 * @param Tipo_usuario_nuevo -> Tipo de usuario por el que se quiere modificar la entrada (En caso de cadena vacia, no se modificará)
	 * @return  1 -> Modificación de la entrada realizada correctamente.
	 * 		   -1 -> La entrada no existe
	 *		   -2 -> No se pueden modificar entradas de una reserva (está pagada)
	 *        -11 -> La localidad está ocupada.
	 *		  -12 -> La grada donde se situa esta localidad no está disponible en este evento para el usuario indicado.
	 *		  -13 -> La localidad está deteriorada.
	 *		  -14 -> La localidad no existe para esta grada.
	 *		 -100 -> Excepción
	 */
	public int modificarEntrada(int ID_entrada, int ID_localidad_nueva, String Tipo_usuario_nuevo)
	 {
	    int salida=0;
	    ResultSet error;
		try
		 {
		     
		     String query = "SELECT DNI FROM Entradas,Reservas WHERE Entradas.ID="+ID_entrada+" AND Entradas.ID_reserva=Reservas.ID AND Reservas.DNI="+DNI+";";
		     Statement st = conn.createStatement();
		     error = st.executeQuery(query);
		     if(!error.next()) return -1;
		     
			CallableStatement cstmt = conn.prepareCall("{CALL Modificar_entrada(?, ?, ?)}");
			
			cstmt.setInt(1,ID_entrada);
			cstmt.setInt(2,ID_localidad_nueva);
			
			if(Tipo_usuario_nuevo.equals(""))
				cstmt.setNull(3, Types.NULL);
			else
				cstmt.setString(3, Tipo_usuario_nuevo);

			cstmt.registerOutParameter(1, java.sql.Types.INTEGER);
			cstmt.executeUpdate();
			salida = cstmt.getInt(1);
		 }
		 
		catch(SQLException se)
		 {
			System.out.println("SQLException: " + se.getMessage());
		    System.out.println("SQLState: " + se.getSQLState());
		    System.out.println("VendorError: " + se.getErrorCode());
		    return -100;
		 }
		catch(Exception e)
		 {
			System.out.println("Error: "+e.getMessage());
			return -100;
		 }
		
		if(salida<=0)
		    return salida;
		else
		    return 1;
	 }
	
	
}