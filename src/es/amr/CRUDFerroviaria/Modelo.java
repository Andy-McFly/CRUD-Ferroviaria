package es.amr.CRUDFerroviaria;

import java.awt.Choice;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

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
	
	public String consultarTrenes(Connection connection, String nombreUser)
	{
		StringBuilder contenidoTextarea = new StringBuilder();
		try
		{
			sentencia = "SELECT * FROM trenes ORDER BY claseTren";
			statement = connection.createStatement();
			resultset = statement.executeQuery(sentencia);
			historicoLog(nombreUser, "Consulta lista de Trenes: "+sentencia);
			contenidoTextarea.append(String.format("%-5s \t %-15s \t %-10s \t %-15s \t %-10s \n", 
					"ID", "Clase", "Carga", "Precio", "Fecha Mantenimiento"));
			contenidoTextarea.append("--------------------------------------------------------------------"
					+ "----------------------------------------------------\n");
			while (resultset.next())
			{
				String fechaEur = obtenerFechaEuropea(resultset.getString("fechaMantenimientoTren"));
				String precio = resultset.getString("precioBilleteTren");
				if(precio != null) 
				{
					precio += "€";
				}
				contenidoTextarea.append(String.format("%-5s \t %-15s \t %-10s \t %-15s \t %-10s \n",
						resultset.getString("idTren"), 
						resultset.getString("claseTren"), 
						resultset.getString("cargaTren"), 
						precio, fechaEur));
			}
		} 
		catch (SQLException e)
		{
			System.out.println("Error en la sentencia SQL");
		}
		return contenidoTextarea.toString();
	}
	
	public boolean modificarTrenes(Connection connection, int idTren, String claseTren, String cargaTren, String precioTren, String fechaTren, String nombreUser)
	{
		boolean resultado = false;
		sentencia = "UPDATE trenes SET claseTren = '" + claseTren + "', cargaTren = '" + cargaTren + "', precioBilleteTren = " 
				+ precioTren + ", fechaMantenimientoTren = '" + fechaTren + "' WHERE idTren = " + idTren + ";";
		try
		{
			statement = connection.createStatement();
			statement.executeUpdate(sentencia);
			resultado = true;
			historicoLog(nombreUser, "Modificar Tren: "+sentencia);
		} 
		catch (SQLException sqle)
		{
			System.out.println(sentencia);
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
	
	public String consultarCiudades(Connection connection, String nombreUser)
	{
		StringBuilder contenidoTextarea = new StringBuilder();
		try
		{
			sentencia = "SELECT * FROM ciudades ORDER BY nombreCiudad";
			statement = connection.createStatement();
			resultset = statement.executeQuery(sentencia);
			historicoLog(nombreUser, "Consulta lista de Ciudades: "+sentencia);
			contenidoTextarea.append(String.format("%-5s \t %-15s \n", "ID", "Ciudad"));
			contenidoTextarea.append("----------------------------------------------------------------------\n");
			while (resultset.next())
			{
				contenidoTextarea.append(String.format("%-5s \t %-15s \n",
						resultset.getString("idCiudad"), 
						resultset.getString("nombreCiudad")));
			}
		} 
		catch (SQLException e)
		{
			System.out.println("Error en la sentencia SQL");
		}
		return contenidoTextarea.toString();
	}
	
	public boolean modificarCiudades(Connection connection, int idCiudad, String nombreCiudad, String nombreUser)
	{
		boolean resultado = false;
		sentencia = "UPDATE ciudades SET nombreCiudad = '" + nombreCiudad + "' WHERE idCiudad = " + idCiudad + ";";
		try
		{
			statement = connection.createStatement();
			statement.executeUpdate(sentencia);
			resultado = true;
			historicoLog(nombreUser, "Modificar Ciudad: "+sentencia);
		} catch (SQLException sqle)
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
	
	public String consultarEstaciones(Connection connection, String nombreUser)
	{
		StringBuilder contenidoTextarea = new StringBuilder();
		try
		{
			sentencia = "SELECT idEstacion, nombreEstacion, direccionEstacion, nombreCiudad FROM estaciones "
					+ "JOIN ciudades ON ciudades.idCiudad = estaciones.idCiudadFK ORDER BY nombreEstacion";
			statement = connection.createStatement();
			resultset = statement.executeQuery(sentencia);
			historicoLog(nombreUser, "Consulta lista de Estaciones: "+sentencia);
			contenidoTextarea.append(String.format("%-5s \t %-20s \t %-20s \t %-20s \n", 
					"ID", "Nombre", "Dirección", "Ciudad"));
			contenidoTextarea.append("--------------------------------------------------------------------"
					+ "----------------------------------------------------\n");
			while (resultset.next())
			{
				contenidoTextarea.append(String.format("%-5s \t %-20s \t %-20s \t %-20s \n",
						resultset.getString("idEstacion"), 
						resultset.getString("nombreEstacion"), 
						resultset.getString("direccionEstacion"), 
						resultset.getString("nombreCiudad")));
			}
		} 
		catch (SQLException e)
		{
			System.out.println("Error en la sentencia SQL");
		}
		return contenidoTextarea.toString();
	}
	
	public boolean modificarEstaciones(Connection connection, int idEstacion, String nombreEstacion,
			String direccionEstacion, int idCiudadFK, String nombreUser)
	{
		boolean resultado = false;
		sentencia = "UPDATE estaciones SET nombreEstacion = '" + nombreEstacion + "', direccionEstacion = '"
				+ direccionEstacion + "', idCiudadFK = " + idCiudadFK + " WHERE idEstacion = " + idEstacion + ";";
		try
		{
			statement = connection.createStatement();
			statement.executeUpdate(sentencia);
			resultado = true;
			historicoLog(nombreUser, "Modificar Estación: "+sentencia);
		} 
		catch (SQLException sqle)
		{
			System.out.println(sentencia);
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
	
	public String consultarParadas(Connection connection, String nombreUser)
	{
		StringBuilder contenidoTextarea = new StringBuilder();
		try
		{
			sentencia = "SELECT idParada, claseTren, nombreEstacion FROM parar "
					+ "JOIN trenes ON trenes.idTren = parar.idTrenFK "
					+ "JOIN estaciones ON estaciones.idEstacion = parar.idEstacionFK";
			statement = connection.createStatement();
			resultset = statement.executeQuery(sentencia);
			historicoLog(nombreUser, "Consulta lista de Paradas: "+sentencia);
			contenidoTextarea.append(String.format("%-5s \t %-25s \t %-20s \n", 
					"ID", "Tren", "Estación"));
			contenidoTextarea.append("-------------------------------------------------------------"
					+ "-------------------------\n");
			while (resultset.next())
			{
				contenidoTextarea.append(String.format("%-5s \t %-25s \t %-20s \n",
						resultset.getString("idParada"), 
						resultset.getString("claseTren"), 
						resultset.getString("nombreEstacion")));
			}
		} 
		catch (SQLException e)
		{
			System.out.println("Error en la sentencia SQL");
		}
		return contenidoTextarea.toString();
	}
	
	public boolean modificarParadas(Connection connection, int idParada, int idTrenFK, int idEstacionFK, String nombreUser)
	{
		boolean resultado = false;
		sentencia = "UPDATE parar SET idTrenFK = " + idTrenFK + ", idEstacionFK = " + idEstacionFK + " WHERE idParada = " + idParada + ";";
		historicoLog(nombreUser, "Modificar Parada: "+sentencia);
		try
		{
			statement = connection.createStatement();
			statement.executeUpdate(sentencia);
			resultado = true;
		} 
		catch (SQLException sqle)
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
		sentencia = "SELECT * FROM trenes ORDER BY claseTren";
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
		sentencia = "SELECT * FROM ciudades ORDER BY nombreCiudad";
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
		sentencia = "SELECT idEstacion, nombreEstacion, nombreCiudad FROM estaciones "
				+ "JOIN ciudades ON ciudades.idCiudad = estaciones.idCiudadFK ORDER BY nombreEstacion";
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
		sentencia = "SELECT idParada, claseTren, nombreEstacion FROM parar "
				+ "JOIN trenes ON trenes.idTren = parar.idTrenFK "
				+ "JOIN estaciones ON estaciones.idEstacion = parar.idEstacionFK";
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
		String[] datos = new String[4];
		sentencia = "SELECT * FROM estaciones "
				+ "JOIN ciudades ON ciudades.idCiudad = estaciones.idCiudadFK WHERE idEstacion = " + id + ";";
		try 
		{
			statement = connection.createStatement();
			resultset = statement.executeQuery(sentencia);
			if(resultset.next()) 
			{
				datos[0] = resultset.getString("nombreEstacion");
				datos[1] = resultset.getString("direccionEstacion");
				datos[2] = resultset.getString("nombreCiudad");
				datos[3] = resultset.getString("idCiudad");
			}
		}
		catch(SQLException sqle) {}
		
		return datos;
	}
	
	public String[] datosBajaParadas(int id) 
	{
		String[] datos = new String[6];
		sentencia = "SELECT claseTren, cargaTren, nombreEstacion, nombreCiudad, idTren, idEstacion FROM parar "
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
				datos[4] = resultset.getString("idTren");
				datos[5] = resultset.getString("idEstacion");
			}
		}
		catch(SQLException sqle) {}
		
		return datos;
	}
	
	public boolean excelTrenes(Connection connection)
	{
		boolean resultado = false;
		try
		{
			String nombreArchivo = "Listado Trenes.xlsx";
			String rutaArchivo = "Consultas\\" + nombreArchivo;
			String hoja = "Hoja1";
			XSSFWorkbook libro = new XSSFWorkbook();
			XSSFSheet hoja1 = libro.createSheet(hoja);
			String[] header = new String[]{ "Código ID", "Clase", "Tipo de carga", 
											"Precio del billete", "Último Mantenimiento" };
			sentencia = "SELECT * FROM trenes";
			statement = connection.createStatement();
			resultset = statement.executeQuery(sentencia);
			int numeroFilas = 0;
			while (resultset.next())
			{
				numeroFilas++;
			}
			String[][] document = new String[numeroFilas][5];
			sentencia = "SELECT * FROM trenes ORDER BY claseTren";
			statement = connection.createStatement();
			resultset = statement.executeQuery(sentencia);
			int fila = 0;
			while (resultset.next())
			{
				document[fila][0] = resultset.getString("idTren");
				document[fila][1] = resultset.getString("claseTren");
				document[fila][2] = resultset.getString("cargaTren");
				document[fila][3] = resultset.getString("precioBilleteTren") + " €";
				document[fila][4] = obtenerFechaEuropea(resultset.getString("fechaMantenimientoTren"));
				fila++;
			}
			CellStyle style = libro.createCellStyle();
			XSSFFont font = libro.createFont();
			font.setBold(true);
			style.setFont(font);
			for (int i = 0; i <= document.length; i++)
			{
				XSSFRow row = hoja1.createRow(i);
				for (int j = 0; j < header.length; j++)
				{
					if (i == 0)
					{
						XSSFCell cell = row.createCell(j); 
															
						cell.setCellStyle(style);
						cell.setCellValue(header[j]);
					} else
					{
						XSSFCell cell = row.createCell(j); 
															
						cell.setCellValue(document[i - 1][j]); 
					}
				}
			}
			File file;
			file = new File(rutaArchivo);
			try (FileOutputStream fileOuS = new FileOutputStream(file))
			{
				if (file.exists())
				{
					file.delete();
					System.out.println("Archivo eliminado");
				}
				libro.write(fileOuS);
				fileOuS.flush();
				fileOuS.close();
				System.out.println("Archivo Creado");
				libro.close();
			} 
			catch (FileNotFoundException e)
			{
				System.out.println("El archivo no se encuentra o está en uso...");
			} 
			catch (IOException e)
			{
				e.printStackTrace();
			}
			resultado = true;
		} 
		catch (SQLException e)
		{
			System.out.println("Error en la sentencia SQL");
			resultado = false;
		}
		return resultado;
	}

	public boolean excelCiudades(Connection connection) 
	{
		boolean resultado = false;
		try
		{
			String nombreArchivo = "Listado Ciudades.xlsx";
			String rutaArchivo = "Consultas\\" + nombreArchivo;
			String hoja = "Hoja1";
			XSSFWorkbook libro = new XSSFWorkbook();
			XSSFSheet hoja1 = libro.createSheet(hoja);
			String[] header = new String[]{ "Código ID", "Nombre Ciudad" };
			sentencia = "SELECT * FROM ciudades";
			statement = connection.createStatement();
			resultset = statement.executeQuery(sentencia);
			int numeroFilas = 0;
			while (resultset.next())
			{
				numeroFilas++;
			}
			String[][] document = new String[numeroFilas][2];
			sentencia = "SELECT * FROM ciudades ORDER BY nombreCiudad";
			statement = connection.createStatement();
			resultset = statement.executeQuery(sentencia);
			int fila = 0;
			while (resultset.next())
			{
				document[fila][0] = resultset.getString("idCiudad");
				document[fila][1] = resultset.getString("nombreCiudad");
				fila++;
			}
			CellStyle style = libro.createCellStyle();
			XSSFFont font = libro.createFont();
			font.setBold(true);
			style.setFont(font);
			for (int i = 0; i <= document.length; i++)
			{
				XSSFRow row = hoja1.createRow(i);
				for (int j = 0; j < header.length; j++)
				{
					if (i == 0)
					{
						XSSFCell cell = row.createCell(j); 
						cell.setCellStyle(style); 
						cell.setCellValue(header[j]);
					} 
					else
					{
						XSSFCell cell = row.createCell(j); 
						cell.setCellValue(document[i - 1][j]);
					}
				}
			}
			File file;
			file = new File(rutaArchivo);
			try (FileOutputStream fileOuS = new FileOutputStream(file))
			{
				if (file.exists())
				{
					file.delete();
					System.out.println("Archivo eliminado");
				}
				libro.write(fileOuS);
				fileOuS.flush();
				fileOuS.close();
				System.out.println("Archivo Creado");
				libro.close();
			} 
			catch (FileNotFoundException e)
			{
				System.out.println("El archivo no se encuentra o está en uso...");
			} 
			catch (IOException e)
			{
				e.printStackTrace();
			}
			resultado = true;
		} 
		catch (SQLException e)
		{
			System.out.println("Error en la sentencia SQL");
			resultado = false;
		}
		return resultado;
	}
	
	public boolean excelEstaciones(Connection connection)
	{
		boolean resultado = false;
		try
		{
			String nombreArchivo = "Listado Estaciones.xlsx";
			String rutaArchivo = "Consultas\\" + nombreArchivo;
			String hoja = "Hoja1";
			XSSFWorkbook libro = new XSSFWorkbook();
			XSSFSheet hoja1 = libro.createSheet(hoja);
			String[] header = new String[]{ "Código ID", "Nombre", "Direccion", 
											"Ciudad"};
			sentencia = "SELECT * FROM estaciones";
			statement = connection.createStatement();
			resultset = statement.executeQuery(sentencia);
			int numeroFilas = 0;
			while (resultset.next())
			{
				numeroFilas++;
			}
			String[][] document = new String[numeroFilas][4];
			sentencia = "SELECT idEstacion, nombreEstacion, direccionEstacion, nombreCiudad FROM estaciones "
					+ "JOIN ciudades ON ciudades.idCiudad = estaciones.idCiudadFK ORDER BY nombreEstacion";
			statement = connection.createStatement();
			resultset = statement.executeQuery(sentencia);
			int fila = 0;
			while (resultset.next())
			{
				document[fila][0] = resultset.getString("idEstacion");
				document[fila][1] = resultset.getString("nombreEstacion");
				document[fila][2] = resultset.getString("direccionEstacion");
				document[fila][3] = resultset.getString("nombreCiudad");
				fila++;
			}
			CellStyle style = libro.createCellStyle();
			XSSFFont font = libro.createFont();
			font.setBold(true);
			style.setFont(font);
			for (int i = 0; i <= document.length; i++)
			{
				XSSFRow row = hoja1.createRow(i);
				for (int j = 0; j < header.length; j++)
				{
					if (i == 0)
					{
						XSSFCell cell = row.createCell(j); 
															
						cell.setCellStyle(style);
						cell.setCellValue(header[j]);
					} else
					{
						XSSFCell cell = row.createCell(j); 
															
						cell.setCellValue(document[i - 1][j]); 
					}
				}
			}
			File file;
			file = new File(rutaArchivo);
			try (FileOutputStream fileOuS = new FileOutputStream(file))
			{
				if (file.exists())
				{
					file.delete();
					System.out.println("Archivo eliminado");
				}
				libro.write(fileOuS);
				fileOuS.flush();
				fileOuS.close();
				System.out.println("Archivo Creado");
				libro.close();
			} 
			catch (FileNotFoundException e)
			{
				System.out.println("El archivo no se encuentra o está en uso...");
			} 
			catch (IOException e)
			{
				e.printStackTrace();
			}
			resultado = true;
		} 
		catch (SQLException e)
		{
			System.out.println("Error en la sentencia SQL");
			resultado = false;
		}
		return resultado;
	}
	
	public boolean excelParadas(Connection connection)
	{
		boolean resultado = false;
		try
		{
			String nombreArchivo = "Listado Paradas.xlsx";
			String rutaArchivo = "Consultas\\" + nombreArchivo;
			String hoja = "Hoja1";
			XSSFWorkbook libro = new XSSFWorkbook();
			XSSFSheet hoja1 = libro.createSheet(hoja);
			String[] header = new String[]{ "Código ID", "Tren", "Estación"};
			sentencia = "SELECT * FROM parar";
			statement = connection.createStatement();
			resultset = statement.executeQuery(sentencia);
			int numeroFilas = 0;
			while (resultset.next())
			{
				numeroFilas++;
			}
			String[][] document = new String[numeroFilas][3];
			sentencia = "SELECT idParada, claseTren, nombreEstacion FROM parar "
					+ "JOIN trenes ON trenes.idTren = parar.idTrenFK "
					+ "JOIN estaciones ON estaciones.idEstacion = parar.idEstacionFK";
			statement = connection.createStatement();
			resultset = statement.executeQuery(sentencia);
			int fila = 0;
			while (resultset.next())
			{
				document[fila][0] = resultset.getString("idParada");
				document[fila][1] = resultset.getString("claseTren");
				document[fila][2] = resultset.getString("nombreEstacion");
				fila++;
			}
			CellStyle style = libro.createCellStyle();
			XSSFFont font = libro.createFont();
			font.setBold(true);
			style.setFont(font);
			for (int i = 0; i <= document.length; i++)
			{
				XSSFRow row = hoja1.createRow(i);
				for (int j = 0; j < header.length; j++)
				{
					if (i == 0)
					{
						XSSFCell cell = row.createCell(j); 
															
						cell.setCellStyle(style);
						cell.setCellValue(header[j]);
					} else
					{
						XSSFCell cell = row.createCell(j); 
															
						cell.setCellValue(document[i - 1][j]); 
					}
				}
			}
			File file;
			file = new File(rutaArchivo);
			try (FileOutputStream fileOuS = new FileOutputStream(file))
			{
				if (file.exists())
				{
					file.delete();
					System.out.println("Archivo eliminado");
				}
				libro.write(fileOuS);
				fileOuS.flush();
				fileOuS.close();
				System.out.println("Archivo Creado");
				libro.close();
			} 
			catch (FileNotFoundException e)
			{
				System.out.println("El archivo no se encuentra o está en uso...");
			} 
			catch (IOException e)
			{
				e.printStackTrace();
			}
			resultado = true;
		} 
		catch (SQLException e)
		{
			System.out.println("Error en la sentencia SQL");
			resultado = false;
		}
		return resultado;
	}
	
	public boolean CSVTrenes(Connection connection) 
	{
		boolean resultado = false;
		try
		{
			sentencia = "SELECT * FROM trenes";
			statement = connection.createStatement();
			resultset = statement.executeQuery(sentencia);
			FileWriter fw = new FileWriter("Consultas\\CSVTrenes.csv", false);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter salida = new PrintWriter(bw);
			salida.println("id, clase, carga, precioBillete, fechaMantenimineto");
			while (resultset.next())
			{
				salida.println(resultset.getInt("idTren") + ", " +
						resultset.getString("claseTren") + ", " +
						resultset.getString("cargaTren") + ", " +
						resultset.getString("precioBilleteTren") + ", " +
						resultset.getString("fechaMantenimientoTren"));
			}
			salida.close();
			bw.close();
			fw.close();
			resultado = true;
		} 
		catch (IOException i)
		{
			System.out.println("Error al crear el archivo");
			resultado = false;
		}
		catch (SQLException e)
		{
			System.out.println("Error en la sentencia SQL");
			resultado = false;
		}
		return resultado;
	}
	
	public boolean CSVCiudades(Connection connection) 
	{
		boolean resultado = false;
		try
		{
			sentencia = "SELECT * FROM ciudades";
			statement = connection.createStatement();
			resultset = statement.executeQuery(sentencia);
			FileWriter fw = new FileWriter("Consultas\\CSVCiudades.csv", false);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter salida = new PrintWriter(bw);
			salida.println("id, nombre");
			while (resultset.next())
			{
				salida.println(resultset.getInt("idCiudad") + ", " +
						resultset.getString("nombreCiudad"));
			}
			salida.close();
			bw.close();
			fw.close();
			resultado = true;
		} 
		catch (IOException i)
		{
			System.out.println("Error al crear el archivo");
			resultado = false;
		}
		catch (SQLException e)
		{
			System.out.println("Error en la sentencia SQL");
			resultado = false;
		}
		return resultado;
	}
	
	public boolean CSVEstaciones(Connection connection) 
	{
		boolean resultado = false;
		try
		{
			sentencia = "SELECT * FROM estaciones";
			statement = connection.createStatement();
			resultset = statement.executeQuery(sentencia);
			FileWriter fw = new FileWriter("Consultas\\CSVEstaciones.csv", false);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter salida = new PrintWriter(bw);
			salida.println("id, nombre, direccion, ciudadFK");
			while (resultset.next())
			{
				salida.println(resultset.getInt("idEstacion") + ", " +
						resultset.getString("nombreEstacion") + ", " +
						resultset.getString("direccionEstacion") + ", " +
						resultset.getString("idCiudadFK"));
			}
			salida.close();
			bw.close();
			fw.close();
			resultado = true;
		} 
		catch (IOException i)
		{
			System.out.println("Error al crear el archivo");
			resultado = false;
		}
		catch (SQLException e)
		{
			System.out.println("Error en la sentencia SQL");
			resultado = false;
		}
		return resultado;
	}
	
	public boolean CSVParadas(Connection connection) 
	{
		boolean resultado = false;
		try
		{
			sentencia = "SELECT * FROM parar";
			statement = connection.createStatement();
			resultset = statement.executeQuery(sentencia);
			FileWriter fw = new FileWriter("Consultas\\CSVParadas.csv", false);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter salida = new PrintWriter(bw);
			salida.println("id, tren, estacion");
			while (resultset.next())
			{
				salida.println(resultset.getInt("idParada") + ", " +
						resultset.getString("idTrenFK") + ", " +
						resultset.getString("idEstacionFK"));
			}
			salida.close();
			bw.close();
			fw.close();
			resultado = true;
		} 
		catch (IOException i)
		{
			System.out.println("Error al crear el archivo");
			resultado = false;
		}
		catch (SQLException e)
		{
			System.out.println("Error en la sentencia SQL");
			resultado = false;
		}
		return resultado;
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
