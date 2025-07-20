package es.amr.CRUDFerroviaria;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;


public class Controlador implements WindowListener, ActionListener, FocusListener, ItemListener, KeyListener
{
	Modelo modelo;
	Vista vista;
	String usuario = null;
	String clase = null;
	String carga = null;
	String precio = null;
	String fecha = null;
	String nombre = null;
	String direccion = null;
	String ciudadFK = null;
	Connection connection = null;
	FocusAdapter faUsuario = new FocusAdapter()
	{
		public void focusGained(FocusEvent eTexto)
		{
			if(vista.txfUsuario.getText().equals("USUARIO")) 
			{
				vista.txfUsuario.setText(null);
				vista.txfUsuario.setForeground(Color.black);
			}
		}
		public void focusLost(FocusEvent eTexto)
		{
			if(vista.txfUsuario.getText().isEmpty()) 
			{
				vista.txfUsuario.setText("USUARIO");
				vista.txfUsuario.setForeground(Color.lightGray);
			}
		}
	};
	FocusAdapter faClave = new FocusAdapter()
	{
		public void focusGained(FocusEvent eTexto)
		{
			if(vista.txfClave.getText().equals("CLAVE")) 
			{
				vista.txfClave.setText(null);
				vista.txfClave.setForeground(Color.black);
				vista.txfClave.setEchoChar('*');
			}
		}
		public void focusLost(FocusEvent eTexto)
		{
			if(vista.txfClave.getText().isEmpty()) 
			{
				vista.txfClave.setText("CLAVE");
				vista.txfClave.setForeground(Color.lightGray);
				vista.txfClave.setEchoChar((char) 0);
			}
		}
	};
	KeyAdapter kaNumeros = new KeyAdapter() 
	{
		public void keyTyped(KeyEvent restriccionTeclas)
		{
			char tecla = restriccionTeclas.getKeyChar();
			if (!Character.isDigit(tecla) && tecla != '.' && tecla != '\b')
			{
				restriccionTeclas.consume();
			}
			
			if (tecla == '.' && vista.txfPrecioT.getText().contains("."))
			{
				restriccionTeclas.consume();
			}
			
			if (tecla == '.' && vista.txfPrecioT.getText().isEmpty())
			{
				restriccionTeclas.consume();
			}
		}
	};
	KeyAdapter kaLetras = new KeyAdapter() 
	{
		public void keyTyped(KeyEvent restriccionTeclas)
		{
			char tecla = restriccionTeclas.getKeyChar();
			if (!Character.isLetter(tecla) && tecla != ' ' && tecla != '\b')
			{
				restriccionTeclas.consume();
			}
		}
	};
	
	public Controlador(Modelo m, Vista v) 
	{
		modelo = m;
		vista = v;
		v.vLogin.addWindowListener(this);
		v.vMPrincipal.addWindowListener(this);
		v.vAltaTren.addWindowListener(this);
		v.vBajaTren.addWindowListener(this);
		
		
		v.vAltaCiudad.addWindowListener(this);
		v.vBajaCiudad.addWindowListener(this);
		
		
		v.vAltaEstacion.addWindowListener(this);
		v.vBajaEstacion.addWindowListener(this);
		
		
		v.vAltaParada.addWindowListener(this);
		v.vBajaParada.addWindowListener(this);
		
		
		v.dlgLogin.addWindowListener(this);
		v.dlgPrincipal.addWindowListener(this);
		v.dlgSesion.addWindowListener(this);
		v.dlgBTren.addWindowListener(this);
		v.dlgBCiudad.addWindowListener(this);
		v.dlgFalloAltaT.addWindowListener(this);
		v.dlgFalloBajaT.addWindowListener(this);
		v.dlgFalloBajaC.addWindowListener(this);
		v.dlgFalloBajaE.addWindowListener(this);
		
		v.dlgExitoAlta.addWindowListener(this);
		v.dlgFalloAlta.addWindowListener(this);
		v.dlgExitoBaja.addWindowListener(this);
		v.miAltaTrenes.addActionListener(this);
		v.miBajaTrenes.addActionListener(this);
		v.miConsultaTrenes.addActionListener(this);
		v.miModificarTrenes.addActionListener(this);
		v.miAltaCiudades.addActionListener(this);
		v.miBajaCiudades.addActionListener(this);
		v.miConsultaCiudades.addActionListener(this);
		v.miModificarCiudades.addActionListener(this);
		v.miAltaEstaciones.addActionListener(this);
		v.miBajaEstaciones.addActionListener(this);
		v.miConsultaEstaciones.addActionListener(this);
		v.miModificarEstaciones.addActionListener(this);
		v.miAltaParadas.addActionListener(this);
		v.miBajaParadas.addActionListener(this);
		v.miConsultaParadas.addActionListener(this);
		v.miModificarParadas.addActionListener(this);
		v.miManual.addActionListener(this);
		v.miSesion.addActionListener(this);
		v.btnAceptarLogin.addActionListener(this);
		v.btnLimpiarLogin.addActionListener(this);
		v.btnSiPrincipal.addActionListener(this);
		v.btnNoPrincipal.addActionListener(this);
		v.btnSiSesion.addActionListener(this);
		v.btnNoSesion.addActionListener(this);
		v.btnRegistroTren.addActionListener(this);
		v.btnOkClase.addActionListener(this);
		v.btnOkCarga.addActionListener(this);
		v.btnOkPrecio.addActionListener(this);
		v.btnOkFecha.addActionListener(this);
		v.btnOkConfTren.addActionListener(this);
		v.btnCancelConfTren.addActionListener(this);
		v.btnBorrarTren.addActionListener(this);
		v.btnOkBTren.addActionListener(this);
		v.btnCancelBTren.addActionListener(this);
		
		v.btnRegistroCiudad.addActionListener(this);
		v.btnOkConfCiudad.addActionListener(this);
		v.btnCancelConfCiudad.addActionListener(this);
		v.btnBorrarCiudad.addActionListener(this);
		v.btnOkBCiudad.addActionListener(this);
		v.btnCancelBCiudad.addActionListener(this);
		
		v.btnRegistroEstacion.addActionListener(this);
		v.btnOkNombre.addActionListener(this);
		v.btnOkDireccion.addActionListener(this);
		v.btnOkNoCiudad.addActionListener(this);
		v.btnOkConfEstacion.addActionListener(this);
		v.btnCancelConfEstacion.addActionListener(this);
		v.btnBorrarEstacion.addActionListener(this);
		v.btnOkBEstacion.addActionListener(this);
		v.btnCancelBEstacion.addActionListener(this);
		
		v.btnRegistroParada.addActionListener(this);
		v.btnOkNoTren.addActionListener(this);
		v.btnOkNoEstacion.addActionListener(this);
		v.btnOkConfParada.addActionListener(this);
		v.btnCancelConfParada.addActionListener(this);
		v.btnBorrarParada.addActionListener(this);
		v.btnOkBParada.addActionListener(this);
		v.btnCancelBParada.addActionListener(this);
		
		v.choCarga.addItemListener(this);
		v.txfPrecioT.addKeyListener(kaNumeros);
		v.txfNombreC.addKeyListener(kaLetras);
		v.txfUsuario.addFocusListener(faUsuario);
		v.txfClave.addFocusListener(faClave);
	}

	@Override
	public void actionPerformed(ActionEvent btn)
	{
//-------------------Login-------------------
		if(btn.getSource().equals(vista.btnAceptarLogin)) 
		{
			if ((!vista.txfUsuario.getText().isEmpty()) && (!vista.txfClave.getText().isEmpty()))
			{
				usuario = vista.txfUsuario.getText();
				connection = modelo.conectar();
				int tipoUser = modelo.credenciales(connection, usuario, vista.txfClave.getText());
				if (tipoUser!=-1)
				{
					modelo.historicoLog(usuario, "Acceso al Sistema");
					vista.vLogin.dispose();
					vista.vMPrincipal.setVisible(true);
				} 
				else
				{
					vista.dlgLogin.setVisible(true);
				}
				modelo.desconectar(connection);
			}
		}
		else if(btn.getSource().equals(vista.btnLimpiarLogin)) 
		{
			vista.txfUsuario.setText("USUARIO");
			vista.txfUsuario.setForeground(Color.lightGray);
			vista.txfClave.setText("CLAVE");
			vista.txfClave.setForeground(Color.lightGray);
			vista.txfClave.setEchoChar((char) 0);
		}
//-------------------Menú Principal-------------------
		else if(btn.getSource().equals(vista.miAltaTrenes)) 
		{
			vista.vAltaTren.setVisible(true);
		}
		else if(btn.getSource().equals(vista.miBajaTrenes)) 
		{
			connection = modelo.conectar();
			modelo.rellenarChoiceTrenes(connection, vista.choTrenes);
			modelo.desconectar(connection);
			vista.vBajaTren.setVisible(true);
		}
		// Consultar Trenes
		// Modificar Trenes
		
		else if(btn.getSource().equals(vista.miAltaCiudades)) 
		{
			vista.vAltaCiudad.setVisible(true);
		}
		else if(btn.getSource().equals(vista.miBajaCiudades)) 
		{
			connection = modelo.conectar();
			modelo.rellenarChoiceCiudades(connection, vista.choCiudades);
			modelo.desconectar(connection);
			vista.vBajaCiudad.setVisible(true);
		}
		// Consultar Ciudades
		// Modificar Ciudades
		
		else if(btn.getSource().equals(vista.miAltaEstaciones)) 
		{
			connection = modelo.conectar();
			modelo.rellenarChoiceCiudades(connection, vista.choCiudades2);
			modelo.desconectar(connection);
			vista.vAltaEstacion.setVisible(true);
		}
		else if(btn.getSource().equals(vista.miBajaEstaciones)) 
		{
			connection = modelo.conectar();
			modelo.rellenarChoiceEstaciones(connection, vista.choEstaciones);
			modelo.desconectar(connection);
			vista.vBajaEstacion.setVisible(true);
		}
		// Consultar Estaciones
		// Modificar Estaciones
		
		else if(btn.getSource().equals(vista.miAltaParadas)) 
		{
			connection = modelo.conectar();
			modelo.rellenarChoiceTrenes(connection, vista.choParadaTren);
			connection = modelo.conectar();
			modelo.rellenarChoiceEstaciones(connection, vista.choParadaEstacion);
			modelo.desconectar(connection);
			vista.vAltaParada.setVisible(true);
		}
		else if(btn.getSource().equals(vista.miBajaParadas)) 
		{
			connection = modelo.conectar();
			modelo.rellenarChoiceParadas(connection, vista.choParadas);
			modelo.desconectar(connection);
			vista.vBajaParada.setVisible(true);
		}
		
		// Consultar Paradas
		// Modificar Paradas
		
		else if(btn.getSource().equals(vista.miSesion)) 
		{
			vista.dlgSesion.setVisible(true);
		}
		else if(btn.getSource().equals(vista.btnSiPrincipal)) 
		{
			modelo.historicoLog(usuario, "Salida del sistema");
			System.exit(0);
		}
		else if(btn.getSource().equals(vista.btnNoPrincipal)) 
		{
			vista.dlgPrincipal.dispose();
		}
		else if(btn.getSource().equals(vista.btnSiSesion)) 
		{
			modelo.historicoLog(usuario, "Salida del sistema");
			vista.vMPrincipal.dispose();
			vista.dlgSesion.dispose();
			vista.vLogin.setVisible(true);
			vista.btnLimpiarLogin.dispatchEvent(new ActionEvent(vista.btnLimpiarLogin, ActionEvent.ACTION_PERFORMED, "Limpiar"));
			vista.btnLimpiarLogin.requestFocus();
		}
		else if(btn.getSource().equals(vista.btnNoSesion)) 
		{
			vista.dlgSesion.dispose();
		}
//-------------------CRUD Trenes-------------------
		// Alta Tren
		else if(btn.getSource().equals(vista.btnRegistroTren)) 
		{
			if(!vista.txfClaseT.getText().isEmpty()) 
			{
				vista.txfClaseT.setBackground(Color.white);
				if(vista.choCarga.getSelectedIndex() != 0) 
				{
					if(vista.choCarga.getSelectedIndex() == 1) 
					{
						if(!vista.txfPrecioT.getText().isEmpty() 
							&& vista.txfPrecioT.getText().charAt(vista.txfPrecioT.getText().length() - 1) != '.') 
						{
							vista.txfPrecioT.setBackground(Color.white);
							clase = vista.txfClaseT.getText();
							carga = vista.choCarga.getSelectedItem();
							precio = vista.txfPrecioT.getText();
							fecha = vista.txfFechaT.getText();
							vista.lblConfTrenClase.setText(vista.confClase + clase);
							vista.lblConfTrenCarga.setText(vista.confCarga + carga);
							vista.lblConfTrenPrecio.setText(vista.confPrecio + precio + "€");
							vista.lblConfTrenFecha.setText(vista.confFecha + fecha);
							vista.dlgConfTren.setVisible(true);
						}
						else
						{
							vista.dlgNoPrecio.setVisible(true);
						}
					}
					else if(vista.choCarga.getSelectedIndex() == 2) 
					{
						clase = vista.txfClaseT.getText();
						carga = vista.choCarga.getSelectedItem();
						precio = "null";
						fecha = vista.txfFechaT.getText();
						vista.lblConfTrenClase.setText(vista.confClase + clase);
						vista.lblConfTrenCarga.setText(vista.confCarga + carga);
						vista.lblConfTrenPrecio.setText(vista.confPrecio + precio);
						vista.lblConfTrenFecha.setText(vista.confFecha + fecha);
						vista.dlgConfTren.setVisible(true);
					}
				}
				else 
				{
					vista.dlgNoCarga.setVisible(true);
				}
			}
			else 
			{
				vista.dlgNoClase.setVisible(true);
			}
		}
		else if(btn.getSource().equals(vista.btnOkClase)) 
		{
			vista.dlgNoClase.dispose();
			vista.txfClaseT.setBackground(vista.clrError);
			vista.txfClaseT.requestFocus();
		}
		else if(btn.getSource().equals(vista.btnOkCarga)) 
		{
			vista.dlgNoCarga.dispose();
			vista.choCarga.requestFocus();
		}
		else if(btn.getSource().equals(vista.btnOkPrecio)) 
		{
			vista.dlgNoPrecio.dispose();
			vista.txfPrecioT.setBackground(vista.clrError);
			vista.txfPrecioT.requestFocus();
		}
		else if(btn.getSource().equals(vista.btnOkFecha)) 
		{
			vista.dlgNoFecha.dispose();
			vista.txfFechaT.setBackground(vista.clrError);
			vista.txfFechaT.requestFocus();
		}
		else if(btn.getSource().equals(vista.btnOkConfTren)) 
		{
			String fechaSql = modelo.obtenerFechaMysql(fecha);
			connection = modelo.conectar();
			if(modelo.altaTrenes(connection, clase, carga, precio, fechaSql, usuario)) 
			{
				vista.txfClaseT.setText(null);
				vista.txfClaseT.setBackground(Color.white);
				vista.choCarga.select(0);
				vista.choCarga.setBackground(Color.white);
				vista.txfPrecioT.setText(null);
				vista.txfPrecioT.setBackground(Color.white);
				vista.txfFechaT.setText("dd/mm/aaaa");
				vista.txfFechaT.setBackground(Color.white);
				vista.dlgConfTren.dispose();
				vista.dlgExitoAlta.setVisible(true);
			}
			else 
			{
				vista.dlgConfTren.dispose();
				vista.dlgFalloAltaT.setVisible(true);
			}
			modelo.desconectar(connection);
			
		}
		else if(btn.getSource().equals(vista.btnCancelConfTren)) 
		{
			vista.dlgConfTren.dispose();
		}
		// Baja Tren
		else if(btn.getSource().equals(vista.btnBorrarTren)) 
		{
			if (vista.choTrenes.getSelectedIndex()!=0) 
			{
				int idTren = Integer.parseInt(vista.choTrenes.getSelectedItem().split("--")[0]);
				connection = modelo.conectar();
				String[] datos = modelo.datosBajaTrenes(idTren);
				modelo.desconectar(connection);
				vista.lblBTrenClase.setText(vista.confClase + datos[0]);
				vista.lblBTrenCarga.setText(vista.confCarga + datos[1]);
				if(datos[2] == null) 
				{
					vista.lblBTrenPrecio.setText(vista.confPrecio + datos[2]);
				}
				else 
				{
					vista.lblBTrenPrecio.setText(vista.confPrecio + datos[2] + "€");
				}
				String fechaEuropea = modelo.obtenerFechaEuropea(datos[3]);
				vista.lblBTrenFecha.setText(vista.confFecha + fechaEuropea);
				vista.dlgBTren.setVisible(true);
			}
		}
		else if(btn.getSource().equals(vista.btnOkBTren)) 
		{
			int idTren = Integer.parseInt(vista.choTrenes.getSelectedItem().split("--")[0]);
			connection = modelo.conectar();
			if(modelo.bajaTrenes(connection, idTren, usuario)) 
			{
				modelo.rellenarChoiceTrenes(connection, vista.choTrenes);
				vista.dlgExitoBaja.setVisible(true);
			}
			else 
			{
				vista.dlgFalloBajaT.setVisible(true);
			}
			modelo.desconectar(connection);
			vista.dlgBTren.dispose();
		}
		else if(btn.getSource().equals(vista.btnCancelBTren)) 
		{
			vista.dlgBTren.dispose();
		}
		// Consultar trenes
		// Modificar trenes
//-------------------CRUD Ciudades-------------------
		// Alta Ciudad
		else if(btn.getSource().equals(vista.btnRegistroCiudad)) 
		{
			if(!vista.txfNombreC.getText().isEmpty()) 
			{
				vista.lblConfCiudadNombre.setText(vista.txfNombreC.getText());
				vista.dlgConfCiudad.setVisible(true);
			}
		}
		else if(btn.getSource().equals(vista.btnOkConfCiudad)) 
		{
			connection = modelo.conectar();
			if(modelo.altaCiudades(connection, vista.txfNombreC.getText(), usuario)) 
			{
				vista.txfNombreC.setText(null);
				vista.txfNombreC.requestFocus();
				vista.dlgExitoAlta.setVisible(true);
			}
			else 
			{
				vista.dlgFalloAlta.setVisible(true);
			}
			modelo.desconectar(connection);
			vista.dlgConfCiudad.dispose();
		}
		else if(btn.getSource().equals(vista.btnCancelConfCiudad)) 
		{
			vista.dlgConfCiudad.dispose();
		}
		// Baja Ciudad
		else if(btn.getSource().equals(vista.btnBorrarCiudad)) 
		{
			if (vista.choCiudades.getSelectedIndex()!=0) 
			{
				int idCiudad = Integer.parseInt(vista.choCiudades.getSelectedItem().split("--")[0]);
				connection = modelo.conectar();
				String datos = modelo.datosBajaCiudades(idCiudad);
				modelo.desconectar(connection);
				vista.lblBCiudadNombre.setText(datos);
				vista.dlgBCiudad.setVisible(true);
			}
		}
		else if(btn.getSource().equals(vista.btnOkBCiudad)) 
		{
			int idCiudad = Integer.parseInt(vista.choCiudades.getSelectedItem().split("--")[0]);
			connection = modelo.conectar();
			if(modelo.bajaCiudades(connection, idCiudad, usuario)) 
			{
				modelo.rellenarChoiceCiudades(connection, vista.choCiudades);
				vista.dlgExitoBaja.setVisible(true);
			}
			else 
			{
				vista.dlgFalloBajaC.setVisible(true);
			}
			modelo.desconectar(connection);
			vista.dlgBCiudad.dispose();
		}
		else if(btn.getSource().equals(vista.btnCancelBCiudad)) 
		{
			vista.dlgBCiudad.dispose();
		}
		// Consultar Ciudades
		// Modificar Ciudad
//-------------------CRUD Estaciones-------------------
		// Alta Estación
		else if(btn.getSource().equals(vista.btnRegistroEstacion)) 
		{
			if(!vista.txfEstacionNombre.getText().isEmpty()) 
			{
				vista.txfEstacionNombre.setBackground(Color.white);
				if(!vista.txfEstacionDireccion.getText().isEmpty()) 
				{
					vista.txfEstacionDireccion.setBackground(Color.white);
					if (vista.choCiudades2.getSelectedIndex() != 0)
					{
						nombre = vista.txfEstacionNombre.getText();
						direccion = vista.txfEstacionDireccion.getText();
						ciudadFK = vista.choCiudades2.getSelectedItem().split("--")[1];
						vista.lblConfEstacionNombre.setText(vista.confNombre + nombre);
						vista.lblConfEstacionDireccion.setText(vista.confDireccion + direccion);
						vista.lblConfEstacionCiudadFK.setText(vista.confCiudadFK + ciudadFK);
						vista.dlgConfEstacion.setVisible(true);
					}
					else 
					{
						vista.dlgNoCiudad.setVisible(true);
					}
				}
				else 
				{
					vista.dlgNoDireccion.setVisible(true);
				}
			}
			else 
			{
				vista.dlgNoNombre.setVisible(true);
			}
			
		}
		else if(btn.getSource().equals(vista.btnOkNombre)) 
		{
			vista.dlgNoNombre.dispose();
			vista.txfEstacionNombre.setBackground(vista.clrError);
			vista.txfEstacionNombre.requestFocus();
		}
		else if(btn.getSource().equals(vista.btnOkDireccion)) 
		{
			vista.dlgNoDireccion.dispose();
			vista.txfEstacionDireccion.setBackground(vista.clrError);
			vista.txfEstacionDireccion.requestFocus();
		}
		else if(btn.getSource().equals(vista.btnOkNoCiudad)) 
		{
			vista.choCiudades2.requestFocus();
			vista.dlgNoCiudad.dispose();
		}
		else if(btn.getSource().equals(vista.btnOkConfEstacion)) 
		{
			int ciudadFK = Integer.parseInt(vista.choCiudades2.getSelectedItem().split("--")[0]);
			connection = modelo.conectar();
			if(modelo.altaEstaciones(connection, nombre, direccion, ciudadFK, usuario)) 
			{
				vista.txfEstacionNombre.setText(null);
				vista.txfEstacionDireccion.setText(null);
				vista.choCiudades2.select(0);
				vista.txfEstacionNombre.requestFocus();
				vista.dlgExitoAlta.setVisible(true);
			}
			else 
			{
				vista.dlgFalloAlta.setVisible(true);
			}
			modelo.desconectar(connection);
			vista.dlgConfEstacion.dispose();
		}
		else if(btn.getSource().equals(vista.btnCancelConfEstacion)) 
		{
			vista.dlgConfEstacion.dispose();
		}
		// Baja Estación
		else if(btn.getSource().equals(vista.btnBorrarEstacion)) 
		{
			if (vista.choEstaciones.getSelectedIndex()!=0) 
			{
				int idEstacion = Integer.parseInt(vista.choEstaciones.getSelectedItem().split("--")[0]);
				connection = modelo.conectar();
				String[] datos = modelo.datosBajaEstaciones(idEstacion);
				modelo.desconectar(connection);
				vista.lblBEstacionNombre.setText(vista.confNombre + datos[0]);
				vista.lblBEstacionDireccion.setText(vista.confDireccion + datos[1]);
				vista.lblBEstacionCiudadFK.setText(vista.confCiudadFK + datos[2]);
				vista.dlgBEstacion.setVisible(true);
			}
		}
		else if(btn.getSource().equals(vista.btnOkBEstacion)) 
		{
			int idEstacion = Integer.parseInt(vista.choEstaciones.getSelectedItem().split("--")[0]);
			connection = modelo.conectar();
			if(modelo.bajaEstaciones(connection, idEstacion, usuario)) 
			{
				modelo.rellenarChoiceEstaciones(connection, vista.choEstaciones);
				vista.dlgExitoBaja.setVisible(true);
			}
			else 
			{
				vista.dlgFalloBajaE.setVisible(true);
			}
			modelo.desconectar(connection);
			vista.dlgBEstacion.dispose();
		}
		else if(btn.getSource().equals(vista.btnCancelBEstacion)) 
		{
			vista.dlgBEstacion.dispose();
		}
		// Consultar Estaciones
		// Modificar Estación
		
//-------------------CRUD Paradas-------------------
		// Alta Parada
		else if(btn.getSource().equals(vista.btnRegistroParada)) 
		{
			if(vista.choParadaTren.getSelectedIndex() != 0) 
			{
				if(vista.choParadaEstacion.getSelectedIndex() != 0) 
				{
					clase = vista.choParadaTren.getSelectedItem().split("--")[1];
					carga = vista.choParadaTren.getSelectedItem().split("--")[2];
					nombre = vista.choParadaEstacion.getSelectedItem().split("--")[1];
					ciudadFK = vista.choParadaEstacion.getSelectedItem().split("--")[2];
					vista.lblConfParadaTrenClase.setText(vista.confClase + clase);
					vista.lblConfParadaTrenCarga.setText(vista.confCarga + carga);
					vista.lblConfParadaEstacionNombre.setText(vista.confNombre + nombre);
					vista.lblConfParadaEstacionCiudadFK.setText(vista.confCiudadFK + ciudadFK);
					vista.dlgConfParada.setVisible(true);
				}
				else 
				{
					vista.dlgNoEstacion.setVisible(true);
				}
			}
			else 
			{
				vista.dlgNoTren.setVisible(true);
			}
		}
		else if(btn.getSource().equals(vista.btnOkNoTren)) 
		{
			vista.choParadaTren.requestFocus();
			vista.dlgNoTren.setVisible(false);
		}
		else if(btn.getSource().equals(vista.btnOkNoEstacion)) 
		{
			vista.choParadaEstacion.requestFocus();
			vista.dlgNoEstacion.setVisible(false);
		}
		else if(btn.getSource().equals(vista.btnOkConfParada)) 
		{
			int idTren = Integer.parseInt(vista.choParadaTren.getSelectedItem().split("--")[0]);
			int idEstacion = Integer.parseInt(vista.choParadaEstacion.getSelectedItem().split("--")[0]);
			connection = modelo.conectar();
			if(modelo.altaParadas(connection, idTren, idEstacion, usuario)) 
			{
				modelo.rellenarChoiceTrenes(connection, vista.choParadaTren);
				connection = modelo.conectar();
				modelo.rellenarChoiceEstaciones(connection, vista.choParadaEstacion);
				vista.dlgExitoAlta.setVisible(true);
			}
			modelo.desconectar(connection);
			vista.dlgConfParada.setVisible(false);
		}
		else if(btn.getSource().equals(vista.btnCancelConfParada)) 
		{
			vista.dlgConfParada.setVisible(false);
		}
		// Baja Parada
		else if(btn.getSource().equals(vista.btnBorrarParada)) 
		{
			if(vista.choParadas.getSelectedIndex() != 0) 
			{
				int idParada = Integer.parseInt(vista.choParadas.getSelectedItem().split("--")[0]);
				connection = modelo.conectar();
				String[] datos = modelo.datosBajaParadas(idParada);
				modelo.desconectar(connection);
				vista.lblBParadaTrenClase.setText(vista.confClase + datos[0]);
				vista.lblBParadaTrenCarga.setText(vista.confCarga + datos[1]);
				vista.lblBParadaEstacionNombre.setText(vista.confNombre + datos[2]);
				vista.lblBParadaEstacionCiudadFK.setText(vista.confCiudadFK + datos[3]);
				vista.dlgBParada.setVisible(true);
			}
		}
		else if(btn.getSource().equals(vista.btnOkBParada)) 
		{
			int idParada = Integer.parseInt(vista.choParadas.getSelectedItem().split("--")[0]);
			connection = modelo.conectar();
			if(modelo.bajaParadas(connection, idParada, usuario)) 
			{
				modelo.rellenarChoiceParadas(connection, vista.choParadas);
				vista.dlgExitoBaja.setVisible(true);
			}
			modelo.desconectar(connection);
			vista.dlgBParada.setVisible(false);
		}
		else if(btn.getSource().equals(vista.btnCancelBParada)) 
		{
			vista.dlgBParada.setVisible(false);
		}
		
		// Consultar Paradas
		
		// Modificar Paradas
	}
	
	@Override
	public void itemStateChanged(ItemEvent eSeleccion)
	{
		if(vista.choCarga.getSelectedIndex() == 2) 
		{
			vista.txfPrecioT.setText(null);
			vista.txfPrecioT.setFocusable(false);
			vista.txfPrecioT.setBackground(null);
		}
		else 
		{
			vista.txfPrecioT.setText(null);
			vista.txfPrecioT.setFocusable(true);
			vista.txfPrecioT.setBackground(Color.white);
		}
		if(vista.choCarga.getSelectedIndex() == 0) 
		{
			vista.choCarga.setBackground(Color.white);
		}
		else 
		{
			vista.choCarga.setBackground(new Color(211, 255, 212));
		}
	}

	@Override
	public void windowClosing(WindowEvent eCerrar)
	{
		// Login
		if (eCerrar.getSource().equals(vista.vLogin))
		{
			System.exit(0);
		}
		else if (eCerrar.getSource().equals(vista.dlgLogin))
		{
			vista.dlgLogin.dispose();
		}
		// Menú Principal
		else if (eCerrar.getSource().equals(vista.vMPrincipal))
		{
			vista.dlgPrincipal.setVisible(true);
		}
		else if (eCerrar.getSource().equals(vista.dlgPrincipal))
		{
			vista.dlgPrincipal.dispose();
		}
		else if (eCerrar.getSource().equals(vista.dlgSesion))
		{
			vista.dlgSesion.dispose();
		}
		// Alta Trenes
		else if (eCerrar.getSource().equals(vista.vAltaTren))
		{
			vista.txfClaseT.setText(null);
			vista.txfClaseT.setBackground(Color.white);
			vista.choCarga.select(0);
			vista.choCarga.setBackground(Color.white);
			vista.txfPrecioT.setText(null);
			vista.txfPrecioT.setBackground(Color.white);
			vista.txfFechaT.setText("dd/mm/aaaa");
			vista.txfFechaT.setBackground(Color.white);
			vista.vAltaTren.dispose();
		}
		else if (eCerrar.getSource().equals(vista.dlgFalloAltaT))
		{
			vista.txfFechaT.setBackground(vista.clrError);
			vista.txfFechaT.requestFocus();
			vista.dlgFalloAltaT.dispose();
		}
		// Baja Trenes
		else if (eCerrar.getSource().equals(vista.vBajaTren))
		{
			vista.vBajaTren.dispose();
		}
		else if (eCerrar.getSource().equals(vista.dlgFalloBajaT))
		{
			vista.dlgFalloBajaT.dispose();
		}
		
		// Alta Ciudades
		else if (eCerrar.getSource().equals(vista.vAltaCiudad))
		{
			vista.txfNombreC.setText(null);
			vista.vAltaCiudad.dispose();
		}
		// Baja Ciudades
		else if (eCerrar.getSource().equals(vista.vBajaCiudad))
		{
			vista.vBajaCiudad.dispose();
		}
		else if (eCerrar.getSource().equals(vista.dlgFalloBajaC))
		{
			vista.dlgFalloBajaC.dispose();
		}
		
		// Alta Estaciones
		else if (eCerrar.getSource().equals(vista.vAltaEstacion))
		{
			vista.txfEstacionNombre.setText(null);
			vista.txfEstacionDireccion.setText(null);
			vista.txfEstacionNombre.setBackground(Color.white);
			vista.txfEstacionDireccion.setBackground(Color.white);
			vista.vAltaEstacion.dispose();
		}
		// Baja Estaciones
		else if (eCerrar.getSource().equals(vista.vBajaEstacion))
		{
			vista.vBajaEstacion.dispose();
		}
		else if (eCerrar.getSource().equals(vista.dlgFalloBajaE))
		{
			vista.dlgFalloBajaE.dispose();
		}
		// Alta Paradas
		else if(eCerrar.getSource().equals(vista.vAltaParada)) 
		{
			vista.vAltaParada.dispose();
		}
		// Baja Paradas
		else if(eCerrar.getSource().equals(vista.vBajaParada)) 
		{
			vista.vBajaParada.dispose();
		}
		
		// Generales
		else if (eCerrar.getSource().equals(vista.dlgExitoAlta))
		{
			vista.dlgExitoAlta.dispose();
		}
		else if (eCerrar.getSource().equals(vista.dlgFalloAlta))
		{
			vista.dlgFalloAlta.dispose();
		}
		else if (eCerrar.getSource().equals(vista.dlgExitoBaja))
		{
			vista.dlgExitoBaja.dispose();
		}
	}
	
	@Override public void windowOpened(WindowEvent e){}@Override public void windowClosed(WindowEvent e){}
	@Override public void windowIconified(WindowEvent e){}@Override public void windowDeiconified(WindowEvent e){}
	@Override public void windowActivated(WindowEvent e){}@Override public void windowDeactivated(WindowEvent e){}
	@Override public void focusGained(FocusEvent e){}@Override public void focusLost(FocusEvent e){}
	@Override public void keyPressed(KeyEvent e){}@Override public void keyReleased(KeyEvent e){}
	@Override public void keyTyped(KeyEvent e){}
}
