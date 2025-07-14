package es.amr.CRUDFerroviaria;

import java.awt.BasicStroke;
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
	Dialog dlgFalloBajaT = new Dialog(vAltaTren, "Error", true);
	Label lblFalloBajaT = new Label("No se puede completar la Baja");
	Label lblFalloBajaT2 = new Label("Tren relacionado con una parada");
	Label lblFalloBajaT3 = new Label("Borrar primero la parada");
	
	
//--------------Alta Ciudades--------------
	Frame vAltaCiudad = new Frame("Registro de ciudades");
	Label lblNombreC = new Label("Nombre de la ciudad:");
	TextField txfNombreC = new TextField(20);
	Button btnRegistroCiudad = new Button("Registrar Ciudad");
	Panel pnlACiudad = new Panel();
	Panel pnlACiudad2 = new Panel();
	// DIÁLOGO Confirmar Alta
	Dialog dlgConfCiudad = new Dialog(vAltaTren, "Confirmar Registro", true);
	Label lblConfCiudad = new Label("Registrar Ciudad con el siguiente nombre:");
	Label lblConfCiudadNombre = new Label("Nombre");
	Button btnOkConfCiudad = new Button(" Aceptar ");
	Button btnCancelConfCiudad = new Button(" Cancelar ");
	Panel pnlConfCiudad = new Panel();
	
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
		
//--------------VENTANA Modificar Trenes--------------
		
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
		// DIÁLOGO Éxito Baja
		dlgExitoBaja.setLayout(new FlowLayout());
		dlgExitoBaja.setSize(200, 100);
		dlgExitoBaja.setResizable(false);
		dlgExitoBaja.add(lblExitoBaja);
		dlgExitoBaja.setLocationRelativeTo(null);
		dlgExitoBaja.setBackground(clrExito);
		dlgExitoBaja.setForeground(Color.darkGray);
		dlgExitoBaja.setFont(fntHe);
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
