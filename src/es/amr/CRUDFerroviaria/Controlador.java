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
	
	public Controlador(Modelo m, Vista v) 
	{
		modelo = m;
		vista = v;
		v.vLogin.addWindowListener(this);
		v.dlgLogin.addWindowListener(this);
		v.vMPrincipal.addWindowListener(this);
		v.dlgPrincipal.addWindowListener(this);
		v.dlgSesion.addWindowListener(this);
		v.vAltaTren.addWindowListener(this);
		v.txfUsuario.addFocusListener(faUsuario);
		v.txfClave.addFocusListener(faClave);
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
		v.choCarga.addItemListener(this);
		v.txfPrecioT.addKeyListener(kaNumeros);
	}

	@Override
	public void actionPerformed(ActionEvent btn)
	{
		// Login
		if(btn.getSource().equals(vista.btnAceptarLogin)) 
		{
			vista.vLogin.dispose();
			vista.vMPrincipal.setVisible(true);
		}
		else if(btn.getSource().equals(vista.btnLimpiarLogin)) 
		{
			vista.txfUsuario.setText("USUARIO");
			vista.txfUsuario.setForeground(Color.lightGray);
			vista.txfClave.setText("CLAVE");
			vista.txfClave.setForeground(Color.lightGray);
			vista.txfClave.setEchoChar((char) 0);
		}
		// Menú Principal
		else if(btn.getSource().equals(vista.miAltaTrenes)) 
		{
			vista.vAltaTren.setVisible(true);
		}
		
		else if(btn.getSource().equals(vista.miSesion)) 
		{
			vista.dlgSesion.setVisible(true);
		}
		else if(btn.getSource().equals(vista.btnSiPrincipal)) 
		{
			System.exit(0);
		}
		else if(btn.getSource().equals(vista.btnNoPrincipal)) 
		{
			vista.dlgPrincipal.dispose();
		}
		else if(btn.getSource().equals(vista.btnSiSesion)) 
		{
			vista.vMPrincipal.dispose();
			vista.dlgSesion.dispose();
			vista.vLogin.setVisible(true);
			vista.btnLimpiarLogin.requestFocus();
		}
		else if(btn.getSource().equals(vista.btnNoSesion)) 
		{
			vista.dlgSesion.dispose();
		}
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
		else if (eCerrar.getSource().equals(vista.vAltaTren))
		{
			vista.vAltaTren.dispose();
		}
	}
	
	@Override public void windowOpened(WindowEvent e){}@Override public void windowClosed(WindowEvent e){}
	@Override public void windowIconified(WindowEvent e){}@Override public void windowDeiconified(WindowEvent e){}
	@Override public void windowActivated(WindowEvent e){}@Override public void windowDeactivated(WindowEvent e){}
	@Override public void focusGained(FocusEvent e){}@Override public void focusLost(FocusEvent e){}
	@Override public void keyPressed(KeyEvent e){}@Override public void keyReleased(KeyEvent e){}
	@Override public void keyTyped(KeyEvent e){}
}
