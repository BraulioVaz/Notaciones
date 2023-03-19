package vistas;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import modelos.*;

public class VistaInicial extends JPanel{
	private static final long serialVersionUID = 1L;
	private JTextField txtExpresionInfija;
	private JButton btnConvertir;
	private JLabel lblExpresionPrefija;
	private JLabel lblExpresionPostfija;
	
	public VistaInicial() {
		txtExpresionInfija = new JTextField(15);
		btnConvertir = new JButton("Convertir");
		lblExpresionPrefija = crearEtiqueta("", 18);
		lblExpresionPostfija = crearEtiqueta("", 18);
		Box contenedor = null;
		
		txtExpresionInfija.setMaximumSize(new Dimension(500,40));
		
		definirEventos();
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		//Seccion de exp. infija
		contenedor = Box.createHorizontalBox();
		contenedor.add(crearEtiqueta("Expresion Infija:", 16));
		contenedor.add(Box.createHorizontalStrut(10));
		contenedor.add(txtExpresionInfija);
		contenedor.add(Box.createHorizontalStrut(10));
		contenedor.add(btnConvertir);
		this.add(Box.createVerticalGlue());
		this.add(contenedor);
		this.add(Box.createVerticalGlue());
		
		//Seccion de exp. prefija
		contenedor = Box.createHorizontalBox();
		contenedor.add(crearEtiqueta("Expresion Prefija: ", 16));
		contenedor.add(Box.createHorizontalStrut(10));
		contenedor.add(lblExpresionPrefija);
		this.add(contenedor);
		this.add(Box.createVerticalGlue());
		
		//Seccion de exp. postfija
		contenedor = Box.createHorizontalBox();
		contenedor.add(crearEtiqueta("Expresion Postfija: ", 16));
		contenedor.add(Box.createHorizontalStrut(10));
		contenedor.add(lblExpresionPostfija);
		this.add(contenedor);
		this.add(Box.createVerticalGlue());
	}
	
	private void definirEventos() {
		btnConvertir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Postfija postfija = null;
				Prefija prefija = null;
				
				if(!validarEntrada()) {
					return;
				}
				
				postfija = new Postfija(txtExpresionInfija.getText());
				prefija = new Prefija(txtExpresionInfija.getText());
				
				lblExpresionPostfija.setText(postfija.convertir());
				lblExpresionPrefija.setText(prefija.convertir());
			}
		});
	}
	
	private boolean validarEntrada() {
		if(txtExpresionInfija.getText().isEmpty()) {
			mostrarAlerta("Entrada invalida", "Debe ingresar una expresion.");
			return false;
		}
		
		return true;
	}
	
	private void mostrarAlerta(String titulo, String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje, titulo, JOptionPane.WARNING_MESSAGE);
	}
	
	private JLabel crearEtiqueta(String mensaje, float tam) {
		JLabel etiqueta = new JLabel(mensaje);
		Font fuente = null;
		
		fuente = etiqueta.getFont();
		etiqueta.setFont(fuente.deriveFont(tam));
		
		return etiqueta;
	}
}
