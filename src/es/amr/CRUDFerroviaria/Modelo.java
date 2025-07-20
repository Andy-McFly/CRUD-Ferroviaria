package es.amr.CRUDFerroviaria;

import java.awt.Choice;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;

public class Modelo
{
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/ferroviaria";
	String login = "adminBD";
	String password = "adminFerroBD1234";
	String sentencia = "";
	Connection connection = null;
	Statement statement = null;
	ResultSet resultset = null;
	
	// Conectar BD
	public Connection conectar()
	{
		try
		{
			Class.forName(driver);
			connection = DriverManager.getConnection(url, login, password);
		} 
		catch (ClassNotFoundException cnfe)
		{
			return null;
		} 
		catch (SQLException sqle)
		{
			return null;
		}
		return connection;
	}
	// Desconectar BD
	public void desconectar(Connection conexion)
	{
		try
		{
			if (conexion != null)
			{
				conexion.close();
			}
		} 
		catch (SQLException sqle){}
	}
	// Login
	public int credenciales(Connection conexion, String usuario, String clave)
	{
		int tipoUser = -1;
		sentencia = "SELECT * FROM usuarios WHERE nombreUsuario = '" + usuario + "' AND claveUsuario = '" + clave + "';";
		try
		{
			statement = connection.createStatement();
			resultset = statement.executeQuery(sentencia);
			if (resultset.next() == true)
			{
				tipoUser = resultset.getInt("tipoUsuario");
				return tipoUser;
			} 
			else
			{
				return tipoUser;
			}
		} 
		catch (SQLException sqle)
		{
			return tipoUser;
		}
	}
//-------------------CRUD Trenes-------------------
	public boolean altaTrenes(Connection connection, String clase, String carga, String precio, String fecha, String nombreUser)
	{
		boolean resultado = false;
		try
		{
			statement = connection.createStatement();
			sentencia = "INSERT INTO trenes VALUES (null, '" + clase + "', '" + carga + "', " + precio + ", '" + fecha + "');";
			statement.executeUpdate(sentencia);
			resultado = true;
			historicoLog(nombreUser, "Alta Tren: "+sentencia);
		} 
		catch (SQLException sqle)
		{
			resultado = false;
		}
		return resultado;
	}
	
	public boolean bajaTrenes(Connection connection, int idTren, String nombreUser)
	{
		boolean resultado = false;
		sentencia = "DELETE FROM trenes WHERE idTren = " + idTren + ";";
		try
		{
			statement = connection.createStatement();
			statement.executeUpdate(sentencia);
			resultado = true;
			historicoLog(nombreUser, "Baja Tren: "+sentencia);
		}
		catch (SQLException sqlex)
		{
			resultado = false;
		}
		return resultado;
	}
	
//-------------------CRUD Ciudades-------------------
	public boolean altaCiudades(Connection connection, String ciudad, String nombreUser)
	{
		boolean resultado = false;
		try
		{
			statement = connection.createStatement();
			sentencia = "INSERT INTO ciudades VALUES (null,'" + ciudad + "');";
			statement.executeUpdate(sentencia);
			resultado = true;
			historicoLog(nombreUser, "Alta Ciudad: "+sentencia);
		} 
		catch (SQLException sqle)
		{
			resultado = false;
		}
		return resultado;
	}
	
	public boolean bajaCiudades(Connection connection, int idCiudad, String nombreUser)
	{
		boolean resultado = false;
		sentencia = "DELETE FROM ciudades WHERE idCiudad = " + idCiudad + ";";
		try
		{
			statement = connection.createStatement();
			statement.executeUpdate(sentencia);
			resultado = true;
			historicoLog(nombreUser, "Baja Ciudad: "+sentencia);
		}
		catch (SQLException sqlex)
		{
			resultado = false;
		}
		return resultado;
	}
	
//-------------------CRUD Estaciones-------------------
	public boolean altaEstaciones(Connection connection, String nombre, String direccion, int ciudadFK, String nombreUser)
	{
		boolean resultado = false;
		try
		{
			statement = connection.createStatement();
			sentencia = "INSERT INTO estaciones VALUES (null,'" + nombre + "', '" + direccion + "', " + ciudadFK + ");";
			statement.executeUpdate(sentencia);
			resultado = true;
			historicoLog(nombreUser, "Alta Estación: "+sentencia);
		} 
		catch (SQLException sqle)
		{
			resultado = false;
		}
		return resultado;
	}
	
	public boolean bajaEstaciones(Connection connection, int idEstacion, String nombreUser)
	{
		boolean resultado = false;
		sentencia = "DELETE FROM estaciones WHERE idEstacion = " + idEstacion + ";";
		try
		{
			statement = connection.createStatement();
			statement.executeUpdate(sentencia);
			resultado = true;
			historicoLog(nombreUser, "Baja Estación: "+sentencia);
		}
		catch (SQLException sqlex)
		{
			resultado = false;
		}
		return resultado;
	}
	
//-------------------CRUD Paradas-------------------
	public boolean altaParadas(Connection connection, int trenFK, int estacionFK, String nombreUser)
	{
		boolean resultado = false;
		try
		{
			statement = connection.createStatement();
			sentencia = "INSERT INTO parar VALUES (null, " + trenFK + ", " + estacionFK + ");";
			statement.executeUpdate(sentencia);
			resultado = true;
			historicoLog(nombreUser, "Alta Parada: "+sentencia);
		} 
		catch (SQLException sqle)
		{
			resultado = false;
		}
		return resultado;
	}
	
	public boolean bajaParadas(Connection connection, int idParada, String nombreUser)
	{
		boolean resultado = false;
		sentencia = "DELETE FROM parar WHERE idParada = " + idParada + ";";
		try
		{
			statement = connection.createStatement();
			statement.executeUpdate(sentencia);
			resultado = true;
			historicoLog(nombreUser, "Baja Parada: "+sentencia);
		}
		catch (SQLException sqlex)
		{
			resultado = false;
		}
		return resultado;
	}
	
//-------------------Herramientas Varias-------------------
	public boolean rellenarChoiceTrenes(Connection connection, Choice choTrenes)
	{
		boolean resultado = false;
		choTrenes.removeAll();
		choTrenes.add("Seleccionar un tren...");
		sentencia = "SELECT * FROM trenes";
		try
		{
			statement = connection.createStatement();
			resultset = statement.executeQuery(sentencia);
			while (resultset.next())
			{
				choTrenes.add(resultset.getInt("idTren") + "--" + resultset.getString("claseTren")+ "--" + resultset.getString("cargaTren"));
			}
			if (connection != null)
			{
				resultado = true;
				connection.close();
			}
		} catch (SQLException sqle)
		{
			resultado = false;
		}
		return resultado;
	}
	
	public boolean rellenarChoiceCiudades(Connection connection, Choice choCiudades)
	{
		boolean resultado = false;
		choCiudades.removeAll();
		choCiudades.add("Seleccionar una ciudad...");
		sentencia = "SELECT * FROM ciudades";
		try
		{
			statement = connection.createStatement();
			resultset = statement.executeQuery(sentencia);
			while (resultset.next())
			{
				choCiudades.add(resultset.getInt("idCiudad") + "--" + resultset.getString("nombreCiudad"));
			}
			if (connection != null)
			{
				resultado = true;
				connection.close();
			}
		} catch (SQLException sqle)
		{
			resultado = false;
		}
		return resultado;
	}
	
	public boolean rellenarChoiceEstaciones(Connection connection, Choice choEstaciones)
	{
		boolean resultado = false;
		choEstaciones.removeAll();
		choEstaciones.add("Seleccionar una estación...");
		sentencia = "SELECT idEstacion, nombreEstacion, nombreCiudad FROM estaciones JOIN ciudades ON ciudades.idCiudad = estaciones.idCiudadFK";
		try
		{
			statement = connection.createStatement();
			resultset = statement.executeQuery(sentencia);
			while (resultset.next())
			{
				choEstaciones.add(resultset.getInt("idEstacion") + "--" + resultset.getString("nombreEstacion") + "--" + resultset.getString("nombreCiudad"));
			}
			if (connection != null)
			{
				resultado = true;
				connection.close();
			}
		} catch (SQLException sqle)
		{
			resultado = false;
		}
		return resultado;
	}
	
	public boolean rellenarChoiceParadas(Connection connection, Choice choParadas)
	{
		boolean resultado = false;
		choParadas.removeAll();
		choParadas.add("Seleccionar una parada...");
		sentencia = "SELECT idParada, claseTren, nombreEstacion FROM parar JOIN trenes ON trenes.idTren = parar.idTrenFK JOIN estaciones ON estaciones.idEstacion = parar.idEstacionFK";
		try
		{
			statement = connection.createStatement();
			resultset = statement.executeQuery(sentencia);
			while (resultset.next())
			{
				choParadas.add(resultset.getInt("idParada") + "--" + resultset.getString("claseTren")+ "--" + resultset.getString("nombreEstacion"));
			}
			if (connection != null)
			{
				resultado = true;
				connection.close();
			}
		} catch (SQLException sqle)
		{
			resultado = false;
		}
		return resultado;
	}
	
	public String[] datosBajaTrenes(int id) 
	{
		String[] datos = new String[4];
		sentencia = "SELECT * FROM trenes WHERE idTren = " + id + ";";
		try 
		{
			statement = connection.createStatement();
			resultset = statement.executeQuery(sentencia);
			if(resultset.next()) 
			{
				datos[0] = resultset.getString("claseTren");
				datos[1] = resultset.getString("cargaTren");
				datos[2] = resultset.getString("precioBilleteTren");
				datos[3] = resultset.getString("fechaMantenimientoTren");
			}
		}
		catch(SQLException sqle) {}
		
		return datos;
	}
	
	public String datosBajaCiudades(int id) 
	{
		String datos = "";
		sentencia = "SELECT * FROM ciudades WHERE idCiudad = " + id + ";";
		try 
		{
			statement = connection.createStatement();
			resultset = statement.executeQuery(sentencia);
			if(resultset.next()) 
			{
				datos = resultset.getString("nombreCiudad");
			}
		}
		catch(SQLException sqle) {}
		
		return datos;
	}
	
	public String[] datosBajaEstaciones(int id) 
	{
		String[] datos = new String[3];
		sentencia = "SELECT nombreEstacion, direccionEstacion, nombreCiudad FROM estaciones JOIN ciudades ON ciudades.idCiudad = estaciones.idCiudadFK WHERE idEstacion = " + id + ";";
		try 
		{
			statement = connection.createStatement();
			resultset = statement.executeQuery(sentencia);
			if(resultset.next()) 
			{
				datos[0] = resultset.getString("nombreEstacion");
				datos[1] = resultset.getString("direccionEstacion");
				datos[2] = resultset.getString("nombreCiudad");
			}
		}
		catch(SQLException sqle) {}
		
		return datos;
	}
	
	public String[] datosBajaParadas(int id) 
	{
		String[] datos = new String[4];
		sentencia = "SELECT claseTren, cargaTren, nombreEstacion, nombreCiudad FROM parar "
				+ "JOIN trenes ON trenes.idTren = parar.idTrenFK "
				+ "JOIN estaciones ON estaciones.idEstacion = parar.idEstacionFK "
				+ "JOIN ciudades ON ciudades.idCiudad = estaciones.idCiudadFK WHERE idParada = " + id + ";";
		try 
		{
			statement = connection.createStatement();
			resultset = statement.executeQuery(sentencia);
			if(resultset.next()) 
			{
				datos[0] = resultset.getString("claseTren");
				datos[1] = resultset.getString("cargaTren");
				datos[2] = resultset.getString("nombreEstacion");
				datos[3] = resultset.getString("nombreCiudad");
			}
		}
		catch(SQLException sqle) {}
		
		return datos;
	}
	
	public String obtenerFechaEuropea(String fechaMySQL)
	{
		String[] resultado = fechaMySQL.split("-");
		return resultado[2] + "/" + resultado[1] + "/" + resultado[0];
	}

	public String obtenerFechaMysql(String fechaEuropea)
	{
		String[] resultado = fechaEuropea.split("/");
		return resultado[2] + "-" + resultado[1] + "-" + resultado[0];
	}
	
	public void historicoLog(String usuario, String mensaje) 
	{
		LocalDateTime fecha= LocalDateTime.now();
		
		try
		{
			FileWriter fw = new FileWriter("movimientos.log", true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter salida = new PrintWriter(bw);
			salida.println("["+fecha.getDayOfMonth()+"-"+fecha.getMonthValue()+"-"+fecha.getYear()+" "+fecha.getHour()+":"+fecha.getMinute()+":"+fecha.getSecond()+"]["+usuario+"]["+mensaje+"]");
			salida.close();
			bw.close();
			fw.close();
		} 
		catch (IOException i)
		{
			System.out.println("Se produjo un error de Archivo");
		}
	}
}
