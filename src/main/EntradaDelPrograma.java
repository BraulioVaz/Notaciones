package main;

import javax.swing.*;
import vistas.*;

public class EntradaDelPrograma {

	public static void main(String[] args) {
		JFrame ventana = new JFrame();
		VistaInicial vInicio = new VistaInicial();
		
		ventana.setExtendedState(JFrame.MAXIMIZED_BOTH);
		ventana.setTitle("Conversor");
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.add(vInicio);
		
		ventana.setVisible(true);
	}

}
