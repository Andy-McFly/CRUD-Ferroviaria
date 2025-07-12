package es.amr.CRUDFerroviaria;

import java.awt.BasicStroke;
import java.awt.Button;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Dialog;
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
	Panel pnlAltaT1 = new Panel();
	Panel pnlAltaT2 = new Panel();
	
	Modelo modelo = new Modelo();
	Font fntHe = new Font("Arial", Font.BOLD, 16);
	Font fntMe = new Font("Arial", Font.PLAIN, 15);
	Font fntLi = new Font("Arial", Font.PLAIN, 13);
	Font fntLiB = new Font("Arial", Font.BOLD, 13);
	Font fntLi2 = new Font("Arial", Font.PLAIN, 14);
	Font fntLogin = new Font("Arial", Font.ITALIC, 17);
	Color clrDraws = new Color(148, 49, 38);
	Color clrButtons = new Color(255, 200, 200);
	Color clrError = new Color(254, 100, 100);
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
		vAltaTren.addMouseListener(this); // BORRAR
		lblClaseT.setBounds(40, 50, 95, 20);
		txfClaseT.setBounds(150, 50, 150, 20);
		lblCargaT.setBounds(40, 110, 50, 20);
		choCarga.setBounds(105, 110, 115, 20);
		for(String tipoCarga: cargas) 
		{
			choCarga.add(tipoCarga);
		}
		lblPrecioT.setBounds(40, 170, 150, 20);
		txfPrecioT.setBounds(190, 170, 50, 20);
		lblFechaT.setBounds(40, 230, 220, 20);
		txfFechaT.setBounds(265, 230, 80, 20);
		lblClaseT.setFont(fntLi2);
		txfClaseT.setFont(fntLiB);
		lblCargaT.setFont(fntLi2);
		choCarga.setFont(fntLiB);
		lblPrecioT.setFont(fntLi2);
		txfPrecioT.setFont(fntLiB);
		lblFechaT.setFont(fntLi2);
		txfFechaT.setFont(fntLiB);
		vAltaTren.add(lblClaseT);
		vAltaTren.add(txfClaseT);
		vAltaTren.add(lblCargaT);
		vAltaTren.add(choCarga);
		vAltaTren.add(lblPrecioT);
		vAltaTren.add(txfPrecioT);
		vAltaTren.add(lblFechaT);
		vAltaTren.add(txfFechaT);
		txfFechaT.setText("dd/mm/aaaa");
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
