package modelos;

public abstract class Notacion {
	protected String expresionInfija;
	protected int indice;
	
	public Notacion(String expresion) {
		expresionInfija = eliminarEspacios(expresion);
		indice = -1;
	}
	
	private String eliminarEspacios(String cadena) {
		return cadena.replaceAll("\\s", cadena);
	}
	
	protected Character proximoCaracter() {
		if((indice + 1) >= expresionInfija.length()) {
			return null;
		}
		
		return expresionInfija.charAt(++indice);
	}
	
	protected boolean esOperador(char c) {
		switch(c) {
			case '+':
			case '-':
			case '*':
			case '/':
				return true;
			default:
				return false;
		}
	}
	
	protected boolean esOperando(char c) {
		if(Character.isDigit(c) || Character.isAlphabetic(c)) {
			return true;
		}
		
		return false;
	}
	
	public abstract String convertir();
}
