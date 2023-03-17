package modelos;

public abstract class Notacion {
	public static final int IZQ_DER = 1;
	public static final int DER_IZQ = 10;
	protected String expresionInfija;
	protected int indice;
	
	public Notacion(String expresion) {
		expresionInfija = eliminarEspacios(expresion);
		indice = -1;
	}
	
	private String eliminarEspacios(String cadena) {
		return cadena.replaceAll("\\s", "");
	}
	
	protected Character proximoCaracter() {
		if((indice + 1) >= expresionInfija.length()) {
			return null;
		}
		
		return expresionInfija.charAt(++indice);
	}
	
	protected boolean esOperando(char c) {
		if(Character.isDigit(c) || Character.isAlphabetic(c)) {
			return true;
		}
		
		return false;
	}
	
	protected int precedencia(char c) {
		switch(c) {
			case '+':
			case '-':
				return 1;
			case '*':
			case '/':
				return 5;
			case '^':
				return 10;
			default:
				return -1;
		}
	}
	
	protected int asociatividad(char c) {
		switch(c) {
			case '+':
			case '-':
			case '*':
			case '/':
				return Notacion.IZQ_DER;
			case '^':
				return Notacion.DER_IZQ;
			default:
				return -1;
		}
	}
	
	public abstract String convertir();
}
