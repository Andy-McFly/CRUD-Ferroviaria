package es.amr.CRUDFerroviaria;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Label;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class Vista extends Frame implements MouseListener
{
	private static final long serialVersionUID = 1L;
	String confClase = "    Clase: ";
	String confCarga = "    Tipo de carga: ";
	String confPrecio = "    Precio del billete: ";
	String confFecha = "    Fecha del último mantenimiento: ";
	String confNombre = "    Nombre: ";
	String confDireccion = "    Dirección: ";
	String confCiudadFK = "    Ciudad: ";
//--------------Login--------------
	Frame vLogin;
	TextField txfUsuario = new TextField(20);
	TextField txfClave = new TextField(20);
	Button btnAceptarLogin = new Button("Iniciar Sesión");
	Button btnLimpiarLogin = new Button("Limpiar");
	// DIÁLOGO
	Dialog dlgLogin = new Dialog(vLogin, "Fallo al conectar", true);
	Label infoLogin = new Label("Datos Incorrectos");
//--------------Menú Principal--------------
	Frame vMPrincipal;
	MenuBar mbMenu = new MenuBar();
	Menu mTrenes = new Menu("Trenes");
	Menu mEstaciones = new Menu("Estaciones");
	Menu mCiudades = new Menu("Ciudades");
	Menu mParadas = new Menu("Paradas");
	Menu mAyuda = new Menu("Ayuda");
	MenuItem miAltaTrenes = new MenuItem("Alta Tren");
	MenuItem miBajaTrenes = new MenuItem("Baja Tren");
	MenuItem miConsultaTrenes = new MenuItem("Consultar Trenes");
	MenuItem miModificarTrenes = new MenuItem("Modificar datos");
	MenuItem miAltaEstaciones = new MenuItem("Alta Estación");
	MenuItem miBajaEstaciones = new MenuItem("Baja Estación");
	MenuItem miConsultaEstaciones = new MenuItem("Consultar Estaciones");
	MenuItem miModificarEstaciones = new MenuItem("Modificar datos");
	MenuItem miAltaCiudades = new MenuItem("Alta Ciudad");
	MenuItem miBajaCiudades = new MenuItem("Baja Ciudad");
	MenuItem miConsultaCiudades = new MenuItem("Consultar Ciudades");
	MenuItem miModificarCiudades = new MenuItem("Modificar datos");
	MenuItem miAltaParadas = new MenuItem("Alta Parada");
	MenuItem miBajaParadas = new MenuItem("Baja Parada");
	MenuItem miConsultaParadas = new MenuItem("Consultar trenes Parados");
	MenuItem miModificarParadas = new MenuItem("Modificar datos");
	MenuItem miManual = new MenuItem("Manual de usuario");
	MenuItem miSesion = new MenuItem("Cerrar Sesión");
	// DIÁLOGO
	Dialog dlgPrincipal = new Dialog(vMPrincipal, "Confirmar Salir", true);
	Label infoPrincipal = new Label("¿Cerrar sesión y salir del programa?");
	Button btnSiPrincipal = new Button(" Sí ");
	Button btnNoPrincipal = new Button(" No ");
	Panel pnlPrincipal1 = new Panel();
	// DIÁLOGO 2
	Dialog dlgSesion = new Dialog(vMPrincipal, "Cerrar Sesión", true);
	Label infoSesion = new Label("¿Cerrar sesión y volver a la ventana de acceso?");
	Button btnSiSesion = new Button(" Sí ");
	Button btnNoSesion = new Button(" No ");
	Panel pnlSesion = new Panel();
//--------------Alta Trenes--------------
	Frame vAltaTren = new Frame("Registro de Trenes");
	Label lblClaseT = new Label("Clase de tren:");
	Label lblCargaT = new Label("Carga:");
	Label lblPrecioT = new Label("Precio del billete (€):");
	Label lblFechaT = new Label("Fecha del último mantenimiento:");
	TextField txfClaseT = new TextField(20);
	TextField txfPrecioT = new TextField();
	TextField txfFechaT = new TextField();
	Choice choCarga = new Choice();
	String cargas[] = {"Tipo de carga...", "Pasajeros", "Mercancías"};
	Button btnRegistroTren = new Button("Registrar Tren");
	// DIÁLOGO
	Dialog dlgNoClase = new Dialog(vAltaTren, "Aviso", true);
	Label lblNoClase = new Label("Escriba la clase del tren");
	Button btnOkClase = new Button(" Aceptar ");
	Panel pnlNoClase = new Panel();
	// DIÁLOGO 2
	Dialog dlgNoCarga = new Dialog(vAltaTren, "Aviso", true);
	Label lblNoCarga = new Label("Seleccione el tipo de carga");
	Button btnOkCarga = new Button(" Aceptar ");
	Panel pnlNoCarga = new Panel();
	// DIÁLOGO 3
	Dialog dlgNoPrecio = new Dialog(vAltaTren, "Aviso", true);
	Label lblNoPrecio = new Label("Indique el precio del billete");
	Button btnOkPrecio = new Button(" Aceptar ");
	Panel pnlNoPrecio = new Panel();
	// DIÁLOGO 4
	Dialog dlgNoFecha = new Dialog(vAltaTren, "Aviso", true);
	Label lblNoFecha = new Label("Escriba la fecha de la última revisión");
	Button btnOkFecha = new Button(" Aceptar ");
	Panel pnlNoFecha = new Panel();
	// DIÁLOGO Confirmar Alta
	Dialog dlgConfTren = new Dialog(vAltaTren, "Confirmar Registro", true);
	Label lblConfTren = new Label("Registrar Tren con los siguientes datos:");
	Label lblConfTrenClase = new Label(confClase);
	Label lblConfTrenCarga = new Label(confCarga);
	Label lblConfTrenPrecio = new Label(confPrecio);
	Label lblConfTrenFecha = new Label(confFecha);
	Button btnOkConfTren = new Button(" Aceptar ");
	Button btnCancelConfTren = new Button(" Cancelar ");
	Panel pnlConfTren = new Panel();
	// DIÁLOGO Error Alta Trenes
	Dialog dlgFalloAltaT = new Dialog(vAltaTren, "Error", true);
	Label lblFalloAltaT = new Label("Fallo en el registro");
	Label lblFalloAltaT2 = new Label("Introduzca la fecha en el formato correcto");
//--------------Baja Trenes--------------
	Frame vBajaTren = new Frame("Baja de Trenes");
	Choice choTrenes = new Choice();
	Button btnBorrarTren = new Button("Borrar Tren");
	Panel pnlBajaTren1 = new Panel();
	Panel pnlBajaTren2 = new Panel();
	// DIÁLOGO Confirmar Baja
	Dialog dlgBTren = new Dialog(vBajaTren, "Confirmar Baja", true);
	Label lblBTren = new Label("Eliminar Tren con los siguientes datos:");
	Label lblBTrenClase = new Label(confClase);
	Label lblBTrenCarga = new Label(confCarga);
	Label lblBTrenPrecio = new Label(confPrecio);
	Label lblBTrenFecha = new Label(confFecha);
	Button btnOkBTren = new Button(" Aceptar ");
	Button btnCancelBTren = new Button(" Cancelar ");
	Panel pnlBTren = new Panel();
	// DIÁLOGO Fallo Baja
	Dialog dlgFalloBajaT = new Dialog(vBajaTren, "Error", true);
	Label lblFalloBajaT = new Label("No se puede completar la Baja");
	Label lblFalloBajaT2 = new Label("Tren relacionado con una parada");
	Label lblFalloBajaT3 = new Label("Borrar primero la parada");
//--------------Consultar Trenes--------------
	Frame vConsultarTren = new Frame("Lista de Trenes");
	TextArea txaTrenes = new TextArea(15, 45);
	Button btnExcelTren = new Button(" Exportar EXCEL ");
	Button btnCSVTren = new Button(" Exportar CSV ");
	Panel pnlCTren = new Panel();
//--------------Modificar Trenes--------------
	// Seleccionar
	Frame vSelecTren = new Frame("Modificar datos");
	Choice choSelecTrenes = new Choice();
	Button btnEditarTren = new Button("Editar datos");
	Panel pnlSelecTren1 = new Panel();
	Panel pnlSelecTren2 = new Panel();
	// Editar
	Frame vDatosTren = new Frame("Editando datos");
	Label lblDatosClaseT = new Label("Clase de tren:");
	Label lblDatosCargaT = new Label("Carga:");
	Label lblDatosPrecioT = new Label("Precio del billete (€):");
	Label lblDatosFechaT = new Label("Fecha del último mantenimiento:");
	TextField txfDatosClaseT = new TextField(20);
	TextField txfDatosPrecioT = new TextField();
	TextField txfDatosFechaT = new TextField();
	Choice choDatosCarga = new Choice();
	Button btnModificarTren = new Button("Modificar Tren");
	
	
//--------------Alta Ciudades--------------
	Frame vAltaCiudad = new Frame("Registro de Ciudades");
	Label lblNombreC = new Label("Nombre de la ciudad:");
	TextField txfNombreC = new TextField(20);
	Button btnRegistroCiudad = new Button(" Registrar Ciudad ");
	Panel pnlACiudad = new Panel();
	Panel pnlACiudad2 = new Panel();
	// DIÁLOGO Confirmar Alta
	Dialog dlgConfCiudad = new Dialog(vAltaCiudad, "Confirmar Registro", true);
	Label lblConfCiudad = new Label("Registrar Ciudad con el siguiente nombre:");
	Label lblConfCiudadNombre = new Label("Nombre");
	Button btnOkConfCiudad = new Button(" Aceptar ");
	Button btnCancelConfCiudad = new Button(" Cancelar ");
	Panel pnlConfCiudad = new Panel();
//--------------Baja Ciudades--------------
	Frame vBajaCiudad = new Frame("Baja de Ciudades");
	Choice choCiudades = new Choice();
	Button btnBorrarCiudad = new Button("Borrar Ciudad");
	Panel pnlBajaCiudad1 = new Panel();
	Panel pnlBajaCiudad2 = new Panel();
	// DIÁLOGO Confirmar Baja
	Dialog dlgBCiudad = new Dialog(vBajaCiudad, "Confirmar Baja", true);
	Label lblBCiudad = new Label("Eliminar Ciudad con el siguiente nombre:");
	Label lblBCiudadNombre = new Label("Nombre");
	Button btnOkBCiudad = new Button(" Aceptar ");
	Button btnCancelBCiudad = new Button(" Cancelar ");
	Panel pnlBCiudad = new Panel();
	// DIÁLOGO Fallo Baja
	Dialog dlgFalloBajaC = new Dialog(vBajaCiudad, "Error", true);
	Label lblFalloBajaC = new Label("No se puede completar la Baja");
	Label lblFalloBajaC2 = new Label("Ciudad relacionada con una estación");
	Label lblFalloBajaC3 = new Label("Borrar primero la estación");
//--------------Consultar Ciudades--------------
	Frame vConsultarCiudad = new Frame("Lista de Ciudades");
	TextArea txaCiudad = new TextArea(15, 45);
	Button btnExcelCiudad = new Button(" Exportar EXCEL ");
	Button btnCSVCiudad = new Button(" Exportar CSV ");
	Panel pnlCCiudad = new Panel();
//--------------Modificar Ciudades--------------
	
//--------------Alta Estaciones--------------
	Frame vAltaEstacion = new Frame("Registro de Estaciones");
	Label lblEstacionNombre = new Label("Nombre:");
	Label lblEstacionDireccion = new Label("Dirección:");
	TextField txfEstacionNombre = new TextField(19);
	TextField txfEstacionDireccion = new TextField(20);
	Choice choCiudades2 = new Choice();
	Button btnRegistroEstacion = new Button(" Registrar Estación ");
	Panel pnlAEstacion = new Panel();
	Panel pnlAEstacion2 = new Panel();
	Panel pnlAEstacion3 = new Panel();
	Panel pnlAEstacion4 = new Panel();
	// DIÁLOGO
	Dialog dlgNoNombre = new Dialog(vAltaEstacion, "Aviso", true);
	Label lblNoNombre = new Label("Escriba el nombre de la estación");
	Button btnOkNombre = new Button(" Aceptar ");
	Panel pnlNoNombre = new Panel();
	// DIÁLOGO 2
	Dialog dlgNoDireccion = new Dialog(vAltaEstacion, "Aviso", true);
	Label lblNoDireccion = new Label("Escriba la dirección de la estación");
	Button btnOkDireccion = new Button(" Aceptar ");
	Panel pnlNoDireccion = new Panel();
	// DIÁLOGO 3
	Dialog dlgNoCiudad = new Dialog(vAltaEstacion, "Aviso", true);
	Label lblNoCiudad = new Label("Debe seleccionar una ciudad");
	Button btnOkNoCiudad = new Button(" Aceptar ");
	Panel pnlNoCiudad = new Panel();
	// DIÁLOGO Confirmar Alta
	Dialog dlgConfEstacion = new Dialog(vAltaEstacion, "Confirmar Registro", true);
	Label lblConfEstacion = new Label("Registrar Estación con los siguientes datos:");
	Label lblConfEstacionNombre = new Label(confNombre);
	Label lblConfEstacionDireccion = new Label(confDireccion);
	Label lblConfEstacionCiudadFK = new Label(confCiudadFK);
	Button btnOkConfEstacion = new Button(" Aceptar ");
	Button btnCancelConfEstacion = new Button(" Cancelar ");
	Panel pnlConfEstacion = new Panel();
//--------------Baja Estaciones--------------
	Frame vBajaEstacion = new Frame("Baja de Estaciones");
	Choice choEstaciones = new Choice();
	Button btnBorrarEstacion = new Button(" Borrar Estación ");
	Panel pnlBajaEstacion1 = new Panel();
	Panel pnlBajaEstacion2 = new Panel();
	// DIÁLOGO Confirmar Baja
	Dialog dlgBEstacion = new Dialog(vBajaEstacion, "Confirmar Baja", true);
	Label lblBEstacion = new Label("Eliminar Estación con los siguientes datos:");
	Label lblBEstacionNombre = new Label(confNombre);
	Label lblBEstacionDireccion = new Label(confDireccion);
	Label lblBEstacionCiudadFK = new Label(confCiudadFK);
	Button btnOkBEstacion = new Button(" Aceptar ");
	Button btnCancelBEstacion = new Button(" Cancelar ");
	Panel pnlBEstacion = new Panel();
	// DIÁLOGO Fallo Baja
	Dialog dlgFalloBajaE = new Dialog(vBajaEstacion, "Error", true);
	Label lblFalloBajaE = new Label("No se puede completar la Baja");
	Label lblFalloBajaE2 = new Label("Estación relacionada con una parada");
	Label lblFalloBajaE3 = new Label("Borrar primero la parada");
//--------------Consultar Estaciones--------------
	Frame vConsultarEstacion = new Frame("Lista de Estaciones");
	TextArea txaEstacion = new TextArea(15, 45);
	Button btnExcelEstacion = new Button(" Exportar EXCEL ");
	Button btnCSVEstacion = new Button(" Exportar CSV ");
	Panel pnlCEstacion = new Panel();
//--------------Modificar Estaciones--------------
	
//--------------Alta Paradas--------------
	Frame vAltaParada = new Frame("Registro de estacionamientos");
	Choice choParadaTren = new Choice();
	Choice choParadaEstacion = new Choice();
	Button btnRegistroParada = new Button(" Registrar Parada ");
	Panel pnlAParada = new Panel();
	Panel pnlAParada2 = new Panel();
	Panel pnlAParada3 = new Panel();
	// DIÁLOGO 1
	Dialog dlgNoTren = new Dialog(vAltaParada, "Aviso", true);
	Label lblNoTren = new Label("Debe seleccionar un tren");
	Button btnOkNoTren = new Button(" Aceptar ");
	Panel pnlNoTren = new Panel();
	// DIÁLOGO 2
	Dialog dlgNoEstacion = new Dialog(vAltaParada, "Aviso", true);
	Label lblNoEstacion = new Label("Debe seleccionar una estación");
	Button btnOkNoEstacion = new Button(" Aceptar ");
	Panel pnlNoEstacion = new Panel();
	// DIÁLOGO Confirmar Alta
	Dialog dlgConfParada = new Dialog(vAltaParada, "Confirmar Registro", true);
	Label lblConfParada = new Label("Registrar Parada con los siguientes datos:");
	Label lblConfParadaTren = new Label(" Tren:");
	Label lblConfParadaTrenClase = new Label(confClase);
	Label lblConfParadaTrenCarga = new Label(confCarga);
	Label lblConfParadaEstacion = new Label(" Estación:");
	Label lblConfParadaEstacionNombre = new Label(confNombre);
	Label lblConfParadaEstacionCiudadFK = new Label(confCiudadFK);
	Button btnOkConfParada = new Button(" Aceptar ");
	Button btnCancelConfParada = new Button(" Cancelar ");
	Panel pnlConfParada = new Panel();
//--------------Baja Paradas--------------
	Frame vBajaParada = new Frame("Baja de Paradas");
	Choice choParadas = new Choice();
	Button btnBorrarParada = new Button(" Borrar Parada ");
	Panel pnlBajaParada1 = new Panel();
	Panel pnlBajaParada2 = new Panel();
	// DIÁLOGO Confirmar Baja
	Dialog dlgBParada = new Dialog(vBajaParada, "Confirmar Baja", true);
	Label lblBParada = new Label("Eliminar Parada con los siguientes datos:");
	Label lblBParadaTren = new Label(" Tren:");
	Label lblBParadaTrenClase = new Label(confClase);
	Label lblBParadaTrenCarga = new Label(confCarga);
	Label lblBParadaEstacion = new Label(" Estación:");
	Label lblBParadaEstacionNombre = new Label(confNombre);
	Label lblBParadaEstacionCiudadFK = new Label(confCiudadFK);
	Button btnOkBParada = new Button(" Aceptar ");
	Button btnCancelBParada = new Button(" Cancelar ");
	Panel pnlBParada = new Panel();
//--------------Consultar Paradas--------------
	Frame vConsultarParada = new Frame("Lista de Paradas");
	TextArea txaParada = new TextArea(15, 45);
	Button btnExcelParada = new Button(" Exportar EXCEL ");
	Button btnCSVParada = new Button(" Exportar CSV ");
	Panel pnlCParada = new Panel();
//--------------Modificar Paradas--------------
	
	
//--------------Uso General--------------
	// DIÁLOGO Éxito Alta
	Dialog dlgExitoAlta = new Dialog(vAltaTren, "Éxito", true);
	Label lblExitoAlta = new Label("Alta correcta");
	// DIÁLOGO Fallo Alta
	Dialog dlgFalloAlta = new Dialog(vAltaTren, "Error", true);
	Label lblFalloAlta = new Label("Fallo en el registro");
	// DIÁLOGO Éxito Baja
	Dialog dlgExitoBaja = new Dialog(vAltaTren, "Éxito", true);
	Label lblExitoBaja = new Label("Baja correcta");
	// DIÁLOGO Excel creado
	Dialog dlgExcel = new Dialog(vAltaTren, "Éxito", true);
	Label lblExcel = new Label("Archivo creado correctamente");
	
	Modelo modelo = new Modelo();
	Font fntHe = new Font("Arial", Font.BOLD, 16);
	Font fntMe = new Font("Arial", Font.PLAIN, 15);
	Font fntLi = new Font("Arial", Font.PLAIN, 13);
	Font fntLiB = new Font("Arial", Font.BOLD, 13);
	Font fntLi2 = new Font("Arial", Font.PLAIN, 14);
	Font fntLogin = new Font("Arial", Font.ITALIC, 17);
	Color clrDraws = new Color(148, 49, 38);
	Color clrButtons = new Color(255, 200, 200);
	Color clrAviso = new Color(255, 255, 191);
	Color clrError = new Color(255, 150, 150);
	Color clrExito = new Color(150, 255, 150);
	Color clrExcel = new Color(209, 212, 255);
	Color clrCSV = new Color(255, 202, 202);
	Color clrFntExcel = new Color(0, 0, 169);
	Color clrFntCSV = new Color(193, 0, 0);
	Toolkit herramienta;
	Image logo;
	Image principal;
	
	public Vista() 
	{
		herramienta = getToolkit();
		logo = herramienta.getImage("img\\logo.png");
		principal = herramienta.getImage("img\\principal.png");
//--------------VENTANA Login--------------
		vLogin = new Frame("Iniciar Sesión")
		{
			private static final long serialVersionUID = 1L;
			public void paint(Graphics g) 
			{
				Graphics2D g2 = (Graphics2D) g;
				g.setColor(clrDraws);
				g.fillRect(0, 10, 430, 190);
				g2.setStroke(new BasicStroke(7));
				g.drawRoundRect(30, 230, 370, 140, 5, 5);
				g.setColor(new Color(183-25, 181-25, 227-25));
				g.fillRoundRect(33, 233, 365, 135, 5, 5);
				g.fillRoundRect(95, 380, 240, 43, 5, 5);
				g.setColor(Color.black);
				g2.setStroke(new BasicStroke(5));
				g.drawLine(0, 200, 430, 200);
				g.setFont(fntHe);
				g.setColor(Color.darkGray);
				g.drawString("Datos de Acceso", 153, 260);
				g.drawImage(logo, 25, 40, vLogin);
			}
		};
		vLogin.setLayout(null);
		vLogin.setSize(430, 440);
		vLogin.setResizable(false);
		vLogin.setLocationRelativeTo(null);
		vLogin.setBackground(new Color(183, 181, 227));
		txfUsuario.setBounds(115, 280, 200, 30);
		txfUsuario.setFont(fntLogin);
		txfUsuario.setText("USUARIO");
		txfUsuario.setForeground(Color.lightGray);
		txfClave.setBounds(115, 320, 200, 30);
		txfClave.setFont(fntLogin);
		txfClave.setText("CLAVE");
		txfClave.setForeground(Color.lightGray);
		btnAceptarLogin.setBounds(105, 390, 115, 25);
		btnAceptarLogin.setBackground(clrButtons);
		btnAceptarLogin.setFont(fntMe);
		btnLimpiarLogin.setBounds(255, 390, 70, 25);
		btnLimpiarLogin.setBackground(Color.gray);
		btnLimpiarLogin.setFont(fntMe);
		vLogin.add(txfUsuario);
		vLogin.add(txfClave);
		vLogin.add(btnAceptarLogin);
		vLogin.add(btnLimpiarLogin);
		vLogin.setVisible(true);
		btnLimpiarLogin.requestFocus();
		// DIÁLOGO Login
		dlgLogin.setLayout(new FlowLayout());
		dlgLogin.setSize(190, 90);
		dlgLogin.setResizable(false);
		dlgLogin.add(infoLogin);
		dlgLogin.setLocationRelativeTo(null);
		dlgLogin.setBackground(clrError);
		dlgLogin.setForeground(Color.darkGray);
		dlgLogin.setFont(fntHe);		
//--------------VENTANA Menú Principal--------------
		vMPrincipal = new Frame("Menú Principal")
		{
			private static final long serialVersionUID = 1L;
			public void paint(Graphics g) 
			{
				g.drawImage(principal, 0, 30, vMPrincipal);
			}
		};
		vMPrincipal.setTitle("Menú Principal (Administrador)");
		vMPrincipal.setLayout (null);
		vMPrincipal.setSize(626,364);
		vMPrincipal.setResizable(false);
		vMPrincipal.setLocationRelativeTo(null);
		vMPrincipal.setMenuBar(mbMenu);
		mbMenu.setFont(fntHe);
		miAltaTrenes.setFont(fntMe);
		miBajaTrenes.setFont(fntMe);
		miConsultaTrenes.setFont(fntMe);
		miModificarTrenes.setFont(fntMe);
		mTrenes.add(miAltaTrenes);
		mTrenes.add(miBajaTrenes);
		mTrenes.add(miConsultaTrenes);
		mTrenes.add(miModificarTrenes);
		mbMenu.add(mTrenes);
		miAltaCiudades.setFont(fntMe);
		miBajaCiudades.setFont(fntMe);
		miConsultaCiudades.setFont(fntMe);
		miModificarCiudades.setFont(fntMe);
		mCiudades.add(miAltaCiudades);
		mCiudades.add(miBajaCiudades);
		mCiudades.add(miConsultaCiudades);
		mCiudades.add(miModificarCiudades);
		mbMenu.add(mCiudades);
		miAltaEstaciones.setFont(fntMe);
		miBajaEstaciones.setFont(fntMe);
		miConsultaEstaciones.setFont(fntMe);
		miModificarEstaciones.setFont(fntMe);
		mEstaciones.add(miAltaEstaciones);
		mEstaciones.add(miBajaEstaciones);
		mEstaciones.add(miConsultaEstaciones);
		mEstaciones.add(miModificarEstaciones);
		mbMenu.add(mEstaciones);
		miAltaParadas.setFont(fntMe);
		miBajaParadas.setFont(fntMe);
		miConsultaParadas.setFont(fntMe);
		miModificarParadas.setFont(fntMe);
		mParadas.add(miAltaParadas);
		mParadas.add(miBajaParadas);
		mParadas.add(miConsultaParadas);
		mParadas.add(miModificarParadas);
		mbMenu.add(mParadas);
		miManual.setFont(fntLi);
		miSesion.setFont(fntLi);
		mAyuda.add(miManual);
		mAyuda.add(miSesion);
		mAyuda.setFont(fntLi);
		mbMenu.add(mAyuda);
		// DIÁLOGO Menú Principal (Confirmar salir)
		dlgPrincipal.setLayout(new GridLayout(2, 0));
		dlgPrincipal.setSize(290, 120);
		dlgPrincipal.setResizable(false);
		dlgPrincipal.setLocationRelativeTo(null);
		dlgPrincipal.setBackground(Color.lightGray);
		infoPrincipal.setFont(fntMe);
		infoPrincipal.setAlignment(Label.CENTER);
		dlgPrincipal.add(infoPrincipal);
		btnSiPrincipal.setFont(fntHe);
		btnSiPrincipal.setForeground(Color.darkGray);
		btnNoPrincipal.setFont(fntHe);
		btnNoPrincipal.setForeground(Color.darkGray);
		pnlPrincipal1.add(btnSiPrincipal);
		pnlPrincipal1.add(btnNoPrincipal);
		dlgPrincipal.add(pnlPrincipal1);
		// DIÁLOGO Menú Principal (Cerrar sesión)
		dlgSesion.setLayout(new GridLayout(2,0));
		dlgSesion.setSize(370, 120);
		dlgSesion.setResizable(false);
		dlgSesion.setLocationRelativeTo(null);
		dlgSesion.setBackground(Color.lightGray);
		infoSesion.setFont(fntMe);
		infoSesion.setAlignment(Label.CENTER);
		dlgSesion.add(infoSesion);
		btnSiSesion.setFont(fntHe);
		btnSiSesion.setForeground(Color.darkGray);
		btnNoSesion.setFont(fntHe);
		btnNoSesion.setForeground(Color.darkGray);
		pnlSesion.add(btnSiSesion);
		pnlSesion.add(btnNoSesion);
		dlgSesion.add(pnlSesion);
//--------------VENTANA Alta Trenes--------------
		vAltaTren.setLayout(null);
		vAltaTren.setSize(390, 400);
		vAltaTren.setResizable(false);
		vAltaTren.setLocationRelativeTo(null);
		vAltaTren.setBackground(Color.lightGray);
		lblClaseT.setBounds(40, 50, 95, 20);
		lblClaseT.setFont(fntLi2);
		txfClaseT.setBounds(150, 50, 150, 20);
		txfClaseT.setFont(fntLiB);
		lblCargaT.setBounds(40, 110, 50, 20);
		lblCargaT.setFont(fntLi2);
		choCarga.setBounds(105, 110, 115, 20);
		for(String tipoCarga: cargas) 
		{
			choCarga.add(tipoCarga);
		}
		choCarga.setFont(fntLiB);
		lblPrecioT.setBounds(40, 170, 150, 20);
		lblPrecioT.setFont(fntLi2);
		txfPrecioT.setBounds(190, 170, 50, 20);
		txfPrecioT.setFont(fntLiB);
		lblFechaT.setBounds(40, 230, 220, 20);
		lblFechaT.setFont(fntLi2);
		txfFechaT.setBounds(265, 230, 80, 20);
		txfFechaT.setFont(fntLiB);
		btnRegistroTren.setBounds(135, 300, 120, 30);
		btnRegistroTren.setFont(fntMe);
		btnRegistroTren.setForeground(Color.darkGray);
		vAltaTren.add(lblClaseT);
		vAltaTren.add(txfClaseT);
		vAltaTren.add(lblCargaT);
		vAltaTren.add(choCarga);
		vAltaTren.add(lblPrecioT);
		vAltaTren.add(txfPrecioT);
		vAltaTren.add(lblFechaT);
		vAltaTren.add(txfFechaT);
		vAltaTren.add(btnRegistroTren);
		txfFechaT.setText("dd/mm/aaaa");
		// DIÁLOGO Alta Trenes (Clase vacía)
		dlgNoClase.setLayout(new GridLayout(2,1));
		dlgNoClase.setSize(270, 120);
		dlgNoClase.setResizable(false);
		dlgNoClase.setLocationRelativeTo(null);
		dlgNoClase.setBackground(clrAviso);
		lblNoClase.setForeground(Color.darkGray);
		lblNoClase.setFont(fntHe);
		lblNoClase.setAlignment(Label.CENTER);
		btnOkClase.setFont(fntMe);
		btnOkClase.setForeground(Color.darkGray);
		dlgNoClase.add(lblNoClase);
		pnlNoClase.add(btnOkClase);
		dlgNoClase.add(pnlNoClase);
		// DIÁLOGO Alta Trenes (Carga no seleccionada)
		dlgNoCarga.setLayout(new GridLayout(2,1));
		dlgNoCarga.setSize(270, 120);
		dlgNoCarga.setResizable(false);
		dlgNoCarga.setLocationRelativeTo(null);
		dlgNoCarga.setBackground(clrAviso);
		lblNoCarga.setForeground(Color.darkGray);
		lblNoCarga.setFont(fntHe);
		lblNoCarga.setAlignment(Label.CENTER);
		btnOkCarga.setFont(fntMe);
		btnOkCarga.setForeground(Color.darkGray);
		dlgNoCarga.add(lblNoCarga);
		pnlNoCarga.add(btnOkCarga);
		dlgNoCarga.add(pnlNoCarga);
		// DIÁLOGO Alta Trenes (Precio vacío)
		dlgNoPrecio.setLayout(new GridLayout(2,1));
		dlgNoPrecio.setSize(270, 120);
		dlgNoPrecio.setResizable(false);
		dlgNoPrecio.setLocationRelativeTo(null);
		dlgNoPrecio.setBackground(clrAviso);
		lblNoPrecio.setForeground(Color.darkGray);
		lblNoPrecio.setFont(fntHe);
		lblNoPrecio.setAlignment(Label.CENTER);
		btnOkPrecio.setFont(fntMe);
		btnOkPrecio.setForeground(Color.darkGray);
		dlgNoPrecio.add(lblNoPrecio);
		pnlNoPrecio.add(btnOkPrecio);
		dlgNoPrecio.add(pnlNoPrecio);
		// DIÁLOGO Alta Trenes (Fecha vacía)
		dlgNoFecha.setLayout(new GridLayout(2,1));
		dlgNoFecha.setSize(270, 120);
		dlgNoFecha.setResizable(false);
		dlgNoFecha.setLocationRelativeTo(null);
		dlgNoFecha.setBackground(clrAviso);
		lblNoFecha.setForeground(Color.darkGray);
		lblNoFecha.setFont(fntHe);
		lblNoFecha.setAlignment(Label.CENTER);
		btnOkFecha.setFont(fntMe);
		btnOkFecha.setForeground(Color.darkGray);
		dlgNoFecha.add(lblNoFecha);
		pnlNoFecha.add(btnOkFecha);
		dlgNoFecha.add(pnlNoFecha);
		// DIÁLOGO Alta Trenes (Confirmar Alta)
		dlgConfTren.setLayout(new GridLayout(6,1));
		dlgConfTren.setSize(330, 320);
		dlgConfTren.setResizable(false);
		dlgConfTren.setLocationRelativeTo(null);
		dlgConfTren.setBackground(clrAviso);
		dlgConfTren.setForeground(Color.darkGray);
		lblConfTren.setFont(fntLiB);
		lblConfTren.setAlignment(Label.CENTER);
		lblConfTrenClase.setFont(fntLi);
		lblConfTrenCarga.setFont(fntLi);
		lblConfTrenPrecio.setFont(fntLi);
		lblConfTrenFecha.setFont(fntLi);
		btnOkConfTren.setFont(fntMe);
		btnCancelConfTren.setFont(fntMe);
		dlgConfTren.add(lblConfTren);
		dlgConfTren.add(lblConfTrenClase);
		dlgConfTren.add(lblConfTrenCarga);
		dlgConfTren.add(lblConfTrenPrecio);
		dlgConfTren.add(lblConfTrenFecha);
		pnlConfTren.add(btnOkConfTren);
		pnlConfTren.add(btnCancelConfTren);
		dlgConfTren.add(pnlConfTren);
		// DIÁLOGO Alta Trenes (Fallo Alta)
		dlgFalloAltaT.setLayout(new GridLayout(2,1));
		dlgFalloAltaT.setSize(360, 120);
		dlgFalloAltaT.setResizable(false);
		dlgFalloAltaT.add(lblFalloAltaT);
		dlgFalloAltaT.add(lblFalloAltaT2);
		lblFalloAltaT.setAlignment(Label.CENTER);
		lblFalloAltaT2.setAlignment(Label.CENTER);
		dlgFalloAltaT.setLocationRelativeTo(null);
		dlgFalloAltaT.setBackground(clrError);
		dlgFalloAltaT.setForeground(Color.darkGray);
		dlgFalloAltaT.setFont(fntHe);
//--------------VENTANA Baja Trenes--------------
		vBajaTren.setLayout(new GridLayout(2, 1));
		vBajaTren.setSize(300, 180);
		vBajaTren.setResizable(false);
		vBajaTren.setLocationRelativeTo(null);
		vBajaTren.setBackground(Color.lightGray);
		btnBorrarTren.setFont(fntMe);
		btnBorrarTren.setForeground(Color.darkGray);
		choTrenes.setFont(fntLi);
		Dimension dmChoTrenes = new Dimension(230,30);
		choTrenes.setPreferredSize(dmChoTrenes);
		pnlBajaTren1.add(choTrenes);
		vBajaTren.add(pnlBajaTren1);
		pnlBajaTren2.add(btnBorrarTren);
		vBajaTren.add(pnlBajaTren2);
		// DIÁLOGO Baja Trenes (Confirmar Baja)
		dlgBTren.setLayout(new GridLayout(6,1));
		dlgBTren.setSize(330, 320);
		dlgBTren.setResizable(false);
		dlgBTren.setLocationRelativeTo(null);
		dlgBTren.setBackground(clrAviso);
		dlgBTren.setForeground(Color.darkGray);
		lblBTren.setFont(fntLiB);
		lblBTren.setAlignment(Label.CENTER);
		lblBTrenClase.setFont(fntLi);
		lblBTrenCarga.setFont(fntLi);
		lblBTrenPrecio.setFont(fntLi);
		lblBTrenFecha.setFont(fntLi);
		btnOkBTren.setFont(fntMe);
		btnCancelBTren.setFont(fntMe);
		dlgBTren.add(lblBTren);
		dlgBTren.add(lblBTrenClase);
		dlgBTren.add(lblBTrenCarga);
		dlgBTren.add(lblBTrenPrecio);
		dlgBTren.add(lblBTrenFecha);
		pnlBTren.add(btnOkBTren);
		pnlBTren.add(btnCancelBTren);
		dlgBTren.add(pnlBTren);
		// DIÁLOGO Baja Trenes (Fallo Baja)
		dlgFalloBajaT.setLayout(new GridLayout(3, 1));
		dlgFalloBajaT.setSize(360, 170);
		dlgFalloBajaT.setResizable(false);
		dlgFalloBajaT.setLocationRelativeTo(null);
		dlgFalloBajaT.setBackground(clrError);
		dlgFalloBajaT.setForeground(Color.darkGray);
		dlgFalloBajaT.setFont(fntHe);
		lblFalloBajaT.setAlignment(Label.CENTER);
		lblFalloBajaT2.setAlignment(Label.CENTER);
		lblFalloBajaT3.setAlignment(Label.CENTER);
		dlgFalloBajaT.add(lblFalloBajaT);
		dlgFalloBajaT.add(lblFalloBajaT2);
		dlgFalloBajaT.add(lblFalloBajaT3);
//--------------VENTANA Consultar Trenes--------------
		vConsultarTren.setLayout(new BorderLayout());
		vConsultarTren.setSize(520, 310);
		vConsultarTren.setResizable(true);
		vConsultarTren.setLocationRelativeTo(null);
		vConsultarTren.setBackground(Color.lightGray);
		txaTrenes.setFont(fntLiB);
		btnExcelTren.setFont(fntMe);
		btnCSVTren.setFont(fntMe);
		btnExcelTren.setForeground(clrFntExcel);
		btnCSVTren.setForeground(clrFntCSV);
		txaTrenes.setEditable(false);
		txaTrenes.setBackground(new Color(225, 225, 225));
		btnExcelTren.setBackground(clrExcel);
		btnCSVTren.setBackground(clrCSV);
		vConsultarTren.add(txaTrenes, BorderLayout.CENTER);
		pnlCTren.add(btnExcelTren);
		pnlCTren.add(btnCSVTren);
		vConsultarTren.add(pnlCTren, BorderLayout.SOUTH);
//--------------VENTANA Modificar Trenes--------------
		// Seleccionar
		vSelecTren.setLayout(new GridLayout(2, 1));
		vSelecTren.setSize(300, 180);
		vSelecTren.setResizable(false);
		vSelecTren.setLocationRelativeTo(null);
		vSelecTren.setBackground(Color.lightGray);
		btnEditarTren.setFont(fntMe);
		btnEditarTren.setForeground(Color.darkGray);
		choSelecTrenes.setFont(fntLi);
		choSelecTrenes.setPreferredSize(dmChoTrenes);
		pnlSelecTren1.add(choSelecTrenes);
		vSelecTren.add(pnlSelecTren1);
		pnlSelecTren2.add(btnEditarTren);
		vSelecTren.add(pnlSelecTren2);
		// Editar
		vDatosTren.setLayout(null);
		vDatosTren.setSize(390, 400);
		vDatosTren.setResizable(false);
		vDatosTren.setLocationRelativeTo(null);
		vDatosTren.setBackground(Color.lightGray);
		lblDatosClaseT.setBounds(40, 50, 95, 20);
		lblDatosClaseT.setFont(fntLi2);
		txfDatosClaseT.setBounds(150, 50, 150, 20);
		txfDatosClaseT.setFont(fntLiB);
		lblDatosCargaT.setBounds(40, 110, 50, 20);
		lblDatosCargaT.setFont(fntLi2);
		choDatosCarga.setBounds(105, 110, 115, 20);
		for(String tipoCarga: cargas) 
		{
			choDatosCarga.add(tipoCarga);
		}
		choDatosCarga.setFont(fntLiB);
		lblDatosPrecioT.setBounds(40, 170, 150, 20);
		lblDatosPrecioT.setFont(fntLi2);
		txfDatosPrecioT.setBounds(190, 170, 50, 20);
		txfDatosPrecioT.setFont(fntLiB);
		lblDatosFechaT.setBounds(40, 230, 220, 20);
		lblDatosFechaT.setFont(fntLi2);
		txfDatosFechaT.setBounds(265, 230, 80, 20);
		txfDatosFechaT.setFont(fntLiB);
		btnModificarTren.setBounds(135, 300, 120, 30);
		btnModificarTren.setFont(fntMe);
		btnModificarTren.setForeground(Color.darkGray);
		vDatosTren.add(lblDatosClaseT);
		vDatosTren.add(txfDatosClaseT);
		vDatosTren.add(lblDatosCargaT);
		vDatosTren.add(choDatosCarga);
		vDatosTren.add(lblDatosPrecioT);
		vDatosTren.add(txfDatosPrecioT);
		vDatosTren.add(lblDatosFechaT);
		vDatosTren.add(txfDatosFechaT);
		vDatosTren.add(btnModificarTren);
//--------------VENTANA Alta Ciudades--------------
		vAltaCiudad.setLayout(new GridLayout(3,1));
		vAltaCiudad.setSize(300, 180);
		vAltaCiudad.setResizable(false);
		vAltaCiudad.setLocationRelativeTo(null);
		vAltaCiudad.setBackground(Color.lightGray);
		lblNombreC.setAlignment(Label.CENTER);
		lblNombreC.setFont(fntLi2);
		txfNombreC.setFont(fntLiB);
		btnRegistroCiudad.setFont(fntMe);
		btnRegistroCiudad.setForeground(Color.darkGray);
		pnlACiudad.add(txfNombreC);
		pnlACiudad2.add(btnRegistroCiudad);
		vAltaCiudad.add(lblNombreC);
		vAltaCiudad.add(pnlACiudad);
		vAltaCiudad.add(pnlACiudad2);
		// DIÁLOGO Alta Ciudades (Confrimar Alta)
		dlgConfCiudad.setLayout(new GridLayout(3,1));
		dlgConfCiudad.setSize(330, 200);
		dlgConfCiudad.setResizable(false);
		dlgConfCiudad.setLocationRelativeTo(null);
		dlgConfCiudad.setBackground(clrAviso);
		dlgConfCiudad.setForeground(Color.darkGray);
		lblConfCiudad.setFont(fntLiB);
		lblConfCiudad.setAlignment(Label.CENTER);
		lblConfCiudadNombre.setFont(fntHe);
		lblConfCiudadNombre.setAlignment(Label.CENTER);
		btnOkConfCiudad.setFont(fntMe);
		btnCancelConfCiudad.setFont(fntMe);
		dlgConfCiudad.add(lblConfCiudad);
		dlgConfCiudad.add(lblConfCiudadNombre);
		pnlConfCiudad.add(btnOkConfCiudad);
		pnlConfCiudad.add(btnCancelConfCiudad);
		dlgConfCiudad.add(pnlConfCiudad);
//--------------VENTANA Baja Ciudades--------------
		vBajaCiudad.setLayout(new GridLayout(2, 1));
		vBajaCiudad.setSize(300, 180);
		vBajaCiudad.setResizable(false);
		vBajaCiudad.setLocationRelativeTo(null);
		vBajaCiudad.setBackground(Color.lightGray);
		btnBorrarCiudad.setFont(fntMe);
		btnBorrarCiudad.setForeground(Color.darkGray);
		choCiudades.setFont(fntLi);
		Dimension dmChoCiudades = new Dimension(230,30);
		choCiudades.setPreferredSize(dmChoCiudades);
		pnlBajaCiudad1.add(choCiudades);
		vBajaCiudad.add(pnlBajaCiudad1);
		pnlBajaCiudad2.add(btnBorrarCiudad);
		vBajaCiudad.add(pnlBajaCiudad2);
		//DIÁLOGO Baja Ciudades (Confirmar Baja)
		dlgBCiudad.setLayout(new GridLayout(3,1));
		dlgBCiudad.setSize(330, 200);
		dlgBCiudad.setResizable(false);
		dlgBCiudad.setLocationRelativeTo(null);
		dlgBCiudad.setBackground(clrAviso);
		dlgBCiudad.setForeground(Color.darkGray);
		lblBCiudad.setFont(fntLiB);
		lblBCiudad.setAlignment(Label.CENTER);
		lblBCiudadNombre.setFont(fntHe);
		lblBCiudadNombre.setAlignment(Label.CENTER);
		btnOkBCiudad.setFont(fntMe);
		btnCancelBCiudad.setFont(fntMe);
		dlgBCiudad.add(lblBCiudad);
		dlgBCiudad.add(lblBCiudadNombre);
		pnlBCiudad.add(btnOkBCiudad);
		pnlBCiudad.add(btnCancelBCiudad);
		dlgBCiudad.add(pnlBCiudad);
		// DIÁLOGO Baja Ciudades (Fallo Baja)
		dlgFalloBajaC.setLayout(new GridLayout(3, 1));
		dlgFalloBajaC.setSize(360, 170);
		dlgFalloBajaC.setResizable(false);
		dlgFalloBajaC.setLocationRelativeTo(null);
		dlgFalloBajaC.setBackground(clrError);
		dlgFalloBajaC.setForeground(Color.darkGray);
		dlgFalloBajaC.setFont(fntHe);
		lblFalloBajaC.setAlignment(Label.CENTER);
		lblFalloBajaC2.setAlignment(Label.CENTER);
		lblFalloBajaC3.setAlignment(Label.CENTER);
		dlgFalloBajaC.add(lblFalloBajaC);
		dlgFalloBajaC.add(lblFalloBajaC2);
		dlgFalloBajaC.add(lblFalloBajaC3);
//--------------VENTANA Consultar Ciudades--------------
		vConsultarCiudad.setLayout(new BorderLayout());
		vConsultarCiudad.setSize(320, 310);
		vConsultarCiudad.setResizable(true);
		vConsultarCiudad.setLocationRelativeTo(null);
		vConsultarCiudad.setBackground(Color.lightGray);
		txaCiudad.setFont(fntLiB);
		btnExcelCiudad.setFont(fntMe);
		btnCSVCiudad.setFont(fntMe);
		btnExcelCiudad.setForeground(clrFntExcel);
		btnCSVCiudad.setForeground(clrFntCSV);
		txaCiudad.setEditable(false);
		txaCiudad.setBackground(new Color(225, 225, 225));
		btnExcelCiudad.setBackground(clrExcel);
		btnCSVCiudad.setBackground(clrCSV);
		vConsultarCiudad.add(txaCiudad, BorderLayout.CENTER);
		pnlCCiudad.add(btnExcelCiudad);
		pnlCCiudad.add(btnCSVCiudad);
		vConsultarCiudad.add(pnlCCiudad, BorderLayout.SOUTH);
//--------------VENTANA Modificar Ciudades--------------
		
//--------------VENTANA Alta Estaciones--------------
		vAltaEstacion.setLayout(new GridLayout(4, 1));
		vAltaEstacion.setSize(420, 250);
		vAltaEstacion.setResizable(false);
		vAltaEstacion.setLocationRelativeTo(null);
		vAltaEstacion.setBackground(Color.lightGray);
		lblEstacionNombre.setFont(fntLi2);
		txfEstacionNombre.setFont(fntLiB);
		lblEstacionDireccion.setFont(fntLi2);
		txfEstacionDireccion.setFont(fntLiB);
		btnRegistroEstacion.setFont(fntMe);
		btnRegistroEstacion.setForeground(Color.darkGray);
		choCiudades2.setPreferredSize(dmChoCiudades);
		pnlAEstacion.add(lblEstacionNombre);
		pnlAEstacion.add(txfEstacionNombre);
		pnlAEstacion2.add(lblEstacionDireccion);
		pnlAEstacion2.add(txfEstacionDireccion);
		pnlAEstacion3.add(choCiudades2);
		pnlAEstacion4.add(btnRegistroEstacion);
		vAltaEstacion.add(pnlAEstacion);
		vAltaEstacion.add(pnlAEstacion2);
		vAltaEstacion.add(pnlAEstacion3);
		vAltaEstacion.add(pnlAEstacion4);
		// DIÁLOGO Alta Estaciones (Nombre vacío)
		dlgNoNombre.setLayout(new GridLayout(2,1));
		dlgNoNombre.setSize(300, 120);
		dlgNoNombre.setResizable(false);
		dlgNoNombre.setLocationRelativeTo(null);
		dlgNoNombre.setBackground(clrAviso);
		lblNoNombre.setForeground(Color.darkGray);
		lblNoNombre.setFont(fntHe);
		lblNoNombre.setAlignment(Label.CENTER);
		btnOkNombre.setFont(fntMe);
		btnOkNombre.setForeground(Color.darkGray);
		dlgNoNombre.add(lblNoNombre);
		pnlNoNombre.add(btnOkNombre);
		dlgNoNombre.add(pnlNoNombre);
		// DIÁLOGO Alta Estaciones (Dirección vacía)
		dlgNoDireccion.setLayout(new GridLayout(2,1));
		dlgNoDireccion.setSize(310, 120);
		dlgNoDireccion.setResizable(false);
		dlgNoDireccion.setLocationRelativeTo(null);
		dlgNoDireccion.setBackground(clrAviso);
		lblNoDireccion.setForeground(Color.darkGray);
		lblNoDireccion.setFont(fntHe);
		lblNoDireccion.setAlignment(Label.CENTER);
		btnOkDireccion.setFont(fntMe);
		btnOkDireccion.setForeground(Color.darkGray);
		dlgNoDireccion.add(lblNoDireccion);
		pnlNoDireccion.add(btnOkDireccion);
		dlgNoDireccion.add(pnlNoDireccion);
		// DIÁLOGO Alta Estaciones (Ciudad no seleccionada)
		dlgNoCiudad.setLayout(new GridLayout(2,1));
		dlgNoCiudad.setSize(310, 120);
		dlgNoCiudad.setResizable(false);
		dlgNoCiudad.setLocationRelativeTo(null);
		dlgNoCiudad.setBackground(clrAviso);
		lblNoCiudad.setForeground(Color.darkGray);
		lblNoCiudad.setFont(fntHe);
		lblNoCiudad.setAlignment(Label.CENTER);
		btnOkNoCiudad.setFont(fntMe);
		btnOkNoCiudad.setForeground(Color.darkGray);
		dlgNoCiudad.add(lblNoCiudad);
		pnlNoCiudad.add(btnOkNoCiudad);
		dlgNoCiudad.add(pnlNoCiudad);
		// DIÁLOGO Alta Estaciones (Confrimar Alta)
		dlgConfEstacion.setLayout(new GridLayout(5,1));
		dlgConfEstacion.setSize(340, 270);
		dlgConfEstacion.setResizable(false);
		dlgConfEstacion.setLocationRelativeTo(null);
		dlgConfEstacion.setBackground(clrAviso);
		dlgConfEstacion.setForeground(Color.darkGray);
		lblConfEstacion.setFont(fntLiB);
		lblConfEstacion.setAlignment(Label.CENTER);
		lblConfEstacionNombre.setFont(fntLi);
		lblConfEstacionDireccion.setFont(fntLi);
		lblConfEstacionCiudadFK.setFont(fntLi);
		btnOkConfEstacion.setFont(fntMe);
		btnCancelConfEstacion.setFont(fntMe);
		dlgConfEstacion.add(lblConfEstacion);
		dlgConfEstacion.add(lblConfEstacionNombre);
		dlgConfEstacion.add(lblConfEstacionDireccion);
		dlgConfEstacion.add(lblConfEstacionCiudadFK);
		pnlConfEstacion.add(btnOkConfEstacion);
		pnlConfEstacion.add(btnCancelConfEstacion);
		dlgConfEstacion.add(pnlConfEstacion);
//--------------VENTANA Baja Estaciones--------------
		vBajaEstacion.setLayout(new GridLayout(2, 1));
		vBajaEstacion.setSize(300, 180);
		vBajaEstacion.setResizable(false);
		vBajaEstacion.setLocationRelativeTo(null);
		vBajaEstacion.setBackground(Color.lightGray);
		btnBorrarEstacion.setFont(fntMe);
		btnBorrarEstacion.setForeground(Color.darkGray);
		choEstaciones.setFont(fntLi);
		Dimension dmChoEstaciones = new Dimension(230,30);
		choEstaciones.setPreferredSize(dmChoEstaciones);
		pnlBajaEstacion1.add(choEstaciones);
		vBajaEstacion.add(pnlBajaEstacion1);
		pnlBajaEstacion2.add(btnBorrarEstacion);
		vBajaEstacion.add(pnlBajaEstacion2);
		// DIÁLOGO Baja Estaciones (Confirmar Baja)
		dlgBEstacion.setLayout(new GridLayout(5,1));
		dlgBEstacion.setSize(340, 270);
		dlgBEstacion.setResizable(false);
		dlgBEstacion.setLocationRelativeTo(null);
		dlgBEstacion.setBackground(clrAviso);
		dlgBEstacion.setForeground(Color.darkGray);
		lblBEstacion.setFont(fntLiB);
		lblBEstacion.setAlignment(Label.CENTER);
		lblBEstacionNombre.setFont(fntLi);
		lblBEstacionDireccion.setFont(fntLi);
		lblBEstacionCiudadFK.setFont(fntLi);
		btnOkBEstacion.setFont(fntMe);
		btnCancelBEstacion.setFont(fntMe);
		dlgBEstacion.add(lblBEstacion);
		dlgBEstacion.add(lblBEstacionNombre);
		dlgBEstacion.add(lblBEstacionDireccion);
		dlgBEstacion.add(lblBEstacionCiudadFK);
		pnlBEstacion.add(btnOkBEstacion);
		pnlBEstacion.add(btnCancelBEstacion);
		dlgBEstacion.add(pnlBEstacion);
		//DIÁLOGO Baja Estaciones (Fallo Baja)
		dlgFalloBajaE.setLayout(new GridLayout(3, 1));
		dlgFalloBajaE.setSize(360, 170);
		dlgFalloBajaE.setResizable(false);
		dlgFalloBajaE.setLocationRelativeTo(null);
		dlgFalloBajaE.setBackground(clrError);
		dlgFalloBajaE.setForeground(Color.darkGray);
		dlgFalloBajaE.setFont(fntHe);
		lblFalloBajaE.setAlignment(Label.CENTER);
		lblFalloBajaE2.setAlignment(Label.CENTER);
		lblFalloBajaE3.setAlignment(Label.CENTER);
		dlgFalloBajaE.add(lblFalloBajaE);
		dlgFalloBajaE.add(lblFalloBajaE2);
		dlgFalloBajaE.add(lblFalloBajaE3);
//--------------VENTANA Consultar Estaciones--------------
		vConsultarEstacion.setLayout(new BorderLayout());
		vConsultarEstacion.setSize(520, 310);
		vConsultarEstacion.setResizable(true);
		vConsultarEstacion.setLocationRelativeTo(null);
		vConsultarEstacion.setBackground(Color.lightGray);
		txaEstacion.setFont(fntLiB);
		btnExcelEstacion.setFont(fntMe);
		btnCSVEstacion.setFont(fntMe);
		btnExcelEstacion.setForeground(clrFntExcel);
		btnCSVEstacion.setForeground(clrFntCSV);
		txaEstacion.setEditable(false);
		txaEstacion.setBackground(new Color(225, 225, 225));
		btnExcelEstacion.setBackground(clrExcel);
		btnCSVEstacion.setBackground(clrCSV);
		vConsultarEstacion.add(txaEstacion, BorderLayout.CENTER);
		pnlCEstacion.add(btnExcelEstacion);
		pnlCEstacion.add(btnCSVEstacion);
		vConsultarEstacion.add(pnlCEstacion, BorderLayout.SOUTH);
//--------------VENTANA Modificar Estaciones--------------
		
//--------------VENTANA Alta Paradas--------------
		vAltaParada.setLayout(new GridLayout(3, 1));
		vAltaParada.setSize(350, 250);
		vAltaParada.setResizable(false);
		vAltaParada.setLocationRelativeTo(null);
		vAltaParada.setBackground(Color.lightGray);
		pnlAParada.add(choParadaTren);
		pnlAParada2.add(choParadaEstacion);
		pnlAParada3.add(btnRegistroParada);
		vAltaParada.add(pnlAParada);
		vAltaParada.add(pnlAParada2);
		vAltaParada.add(pnlAParada3);
		// DIÁLOGO Alta Paradas (Tren no seleccionado)
		dlgNoTren.setLayout(new GridLayout(2, 1));
		dlgNoTren.setSize(310, 120);
		dlgNoTren.setResizable(false);
		dlgNoTren.setLocationRelativeTo(null);
		dlgNoTren.setBackground(clrAviso);
		lblNoTren.setForeground(Color.darkGray);
		lblNoTren.setFont(fntHe);
		lblNoTren.setAlignment(Label.CENTER);
		btnOkNoTren.setFont(fntMe);
		btnOkNoTren.setForeground(Color.darkGray);
		dlgNoTren.add(lblNoTren);
		pnlNoTren.add(btnOkNoTren);
		dlgNoTren.add(pnlNoTren);
		// DIÁLOGO Alta Paradas (Estación no seleccionada)
		dlgNoEstacion.setLayout(new GridLayout(2, 1));
		dlgNoEstacion.setSize(310, 120);
		dlgNoEstacion.setResizable(false);
		dlgNoEstacion.setLocationRelativeTo(null);
		dlgNoEstacion.setBackground(clrAviso);
		lblNoEstacion.setForeground(Color.darkGray);
		lblNoEstacion.setFont(fntHe);
		lblNoEstacion.setAlignment(Label.CENTER);
		btnOkNoEstacion.setFont(fntMe);
		btnOkNoEstacion.setForeground(Color.darkGray);
		dlgNoEstacion.add(lblNoEstacion);
		pnlNoEstacion.add(btnOkNoEstacion);
		dlgNoEstacion.add(pnlNoEstacion);
		// DIÁLOGO Alta Paradas (Confirmar Alta)
		dlgConfParada.setLayout(new GridLayout(8,1));
		dlgConfParada.setSize(340, 350);
		dlgConfParada.setResizable(false);
		dlgConfParada.setLocationRelativeTo(null);
		dlgConfParada.setBackground(clrAviso);
		dlgConfParada.setForeground(Color.darkGray);
		lblConfParada.setFont(fntLiB);
		lblConfParada.setAlignment(Label.CENTER);
		lblConfParadaTren.setFont(fntLiB);
		lblConfParadaEstacion.setFont(fntLiB);
		lblConfParadaTrenClase.setFont(fntLi);
		lblConfParadaTrenCarga.setFont(fntLi);
		lblConfParadaEstacionNombre.setFont(fntLi);
		lblConfParadaEstacionCiudadFK.setFont(fntLi);
		btnOkConfParada.setFont(fntMe);
		btnCancelConfParada.setFont(fntMe);
		dlgConfParada.add(lblConfParada);
		dlgConfParada.add(lblConfParadaTren);
		dlgConfParada.add(lblConfParadaTrenClase);
		dlgConfParada.add(lblConfParadaTrenCarga);
		dlgConfParada.add(lblConfParadaEstacion);
		dlgConfParada.add(lblConfParadaEstacionNombre);
		dlgConfParada.add(lblConfParadaEstacionCiudadFK);
		pnlConfParada.add(btnOkConfParada);
		pnlConfParada.add(btnCancelConfParada);
		dlgConfParada.add(pnlConfParada);
//--------------VENTANA Baja Paradas--------------
		vBajaParada.setLayout(new GridLayout(2, 1));
		vBajaParada.setSize(300, 180);
		vBajaParada.setResizable(false);
		vBajaParada.setLocationRelativeTo(null);
		vBajaParada.setBackground(Color.lightGray);
		btnBorrarParada.setFont(fntMe);
		btnBorrarParada.setForeground(Color.darkGray);
		choParadas.setFont(fntLi);
		Dimension dmChoParadas = new Dimension(230,30);
		choParadas.setPreferredSize(dmChoParadas);
		pnlBajaParada1.add(choParadas);
		vBajaParada.add(pnlBajaParada1);
		pnlBajaParada2.add(btnBorrarParada);
		vBajaParada.add(pnlBajaParada2);
		// DIÁLOGO Baja Paradas (Confirmar Baja)
		dlgBParada.setLayout(new GridLayout(8,1));
		dlgBParada.setSize(340, 350);
		dlgBParada.setResizable(false);
		dlgBParada.setLocationRelativeTo(null);
		dlgBParada.setBackground(clrAviso);
		dlgBParada.setForeground(Color.darkGray);
		lblBParada.setFont(fntLiB);
		lblBParada.setAlignment(Label.CENTER);
		lblBParadaTren.setFont(fntLiB);
		lblBParadaEstacion.setFont(fntLiB);
		lblBParadaTrenClase.setFont(fntLi);
		lblBParadaTrenCarga.setFont(fntLi);
		lblBParadaEstacionNombre.setFont(fntLi);
		lblBParadaEstacionCiudadFK.setFont(fntLi);
		btnOkBParada.setFont(fntMe);
		btnCancelBParada.setFont(fntMe);
		dlgBParada.add(lblBParada);
		dlgBParada.add(lblBParadaTren);
		dlgBParada.add(lblBParadaTrenClase);
		dlgBParada.add(lblBParadaTrenCarga);
		dlgBParada.add(lblBParadaEstacion);
		dlgBParada.add(lblBParadaEstacionNombre);
		dlgBParada.add(lblBParadaEstacionCiudadFK);
		pnlBParada.add(btnOkBParada);
		pnlBParada.add(btnCancelBParada);
		dlgBParada.add(pnlBParada);
//--------------VENTANA Consultar Paradas--------------
		vConsultarParada.setLayout(new BorderLayout());
		vConsultarParada.setSize(320, 310);
		vConsultarParada.setResizable(true);
		vConsultarParada.setLocationRelativeTo(null);
		vConsultarParada.setBackground(Color.lightGray);
		txaParada.setFont(fntLiB);
		btnExcelParada.setFont(fntMe);
		btnCSVParada.setFont(fntMe);
		btnExcelParada.setForeground(clrFntExcel);
		btnCSVParada.setForeground(clrFntCSV);
		txaParada.setEditable(false);
		txaParada.setBackground(new Color(225, 225, 225));
		btnExcelParada.setBackground(clrExcel);
		btnCSVParada.setBackground(clrCSV);
		vConsultarParada.add(txaParada, BorderLayout.CENTER);
		pnlCParada.add(btnExcelParada);
		pnlCParada.add(btnCSVParada);
		vConsultarParada.add(pnlCParada, BorderLayout.SOUTH);
//--------------VENTANA Modificar Paradas--------------
		
//--------------Varios--------------
		// DIÁLOGO Éxito Alta
		dlgExitoAlta.setLayout(new FlowLayout());
		dlgExitoAlta.setSize(200, 100);
		dlgExitoAlta.setResizable(false);
		dlgExitoAlta.add(lblExitoAlta);
		dlgExitoAlta.setLocationRelativeTo(null);
		dlgExitoAlta.setBackground(clrExito);
		dlgExitoAlta.setForeground(Color.darkGray);
		dlgExitoAlta.setFont(fntHe);
		// DIÁLOGO Fallo Alta
		dlgFalloAlta.setLayout(new FlowLayout());
		dlgFalloAlta.setSize(200, 100);
		dlgFalloAlta.setResizable(false);
		dlgFalloAlta.add(lblFalloAlta);
		dlgFalloAlta.setLocationRelativeTo(null);
		dlgFalloAlta.setBackground(clrError);
		dlgFalloAlta.setForeground(Color.darkGray);
		dlgFalloAlta.setFont(fntHe);
		// DIÁLOGO Éxito Baja
		dlgExitoBaja.setLayout(new FlowLayout());
		dlgExitoBaja.setSize(200, 100);
		dlgExitoBaja.setResizable(false);
		dlgExitoBaja.add(lblExitoBaja);
		dlgExitoBaja.setLocationRelativeTo(null);
		dlgExitoBaja.setBackground(clrExito);
		dlgExitoBaja.setForeground(Color.darkGray);
		dlgExitoBaja.setFont(fntHe);
		// DIÁLOGO Excel/CSV creado
		dlgExcel.setLayout(new FlowLayout());
		dlgExcel.setSize(300, 100);
		dlgExcel.setResizable(false);
		lblExcel.setAlignment(Label.CENTER);
		dlgExcel.add(lblExcel);
		dlgExcel.setLocationRelativeTo(null);
		dlgExcel.setBackground(clrExito);
		dlgExcel.setForeground(Color.darkGray);
		dlgExcel.setFont(fntHe);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) //BORRAR
	{
		int clickX, clickY;
		clickX = e.getX();
		clickY = e.getY();
		System.out.println("Clic en X: " + clickX + ", Y: " + clickY);
	}
	@Override public void mousePressed(MouseEvent e){}@Override public void mouseReleased(MouseEvent e) {}
	@Override public void mouseEntered(MouseEvent e){}@Override public void mouseExited(MouseEvent e){}
}
