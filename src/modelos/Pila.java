package modelos;

public class Pila {
	private String[] arreglo;
	private int indice;
	
	public Pila(int tam) {
		arreglo = new String[tam];
		indice = 0;
	}
	
	public void push(String s) {
		if(!estaLleno()) {
			arreglo[indice++] = s;
		}
	}
	
	public String pop() {
		if(!estaVacio()) {
			return arreglo[--indice];
		}
		
		return null;
	}
	
	public boolean estaLleno() {
		if(indice == (arreglo.length - 1)) {
			return true;
		}
		
		return false;
	}
	
	public boolean estaVacio() {
		if(indice == 0) {
			return true;
		}
		
		return false;
	}
}
