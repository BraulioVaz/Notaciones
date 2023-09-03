package modelos;

public class Prefija extends Notacion{

	public Prefija(String expresion) {
		super(expresion);
	}

	@Override
	public String convertir() {
		String prefija = "", operando = "";
		Pila pila = new Pila(15);
		Character caracterActual, operador;
		
		this.expresionInfija = invertirExpresion(this.expresionInfija);
		
		while((caracterActual = proximoCaracter()) != null) {
			if(esOperando(caracterActual)) {
				operando += caracterActual;
				continue;
			}
			
			prefija += " " + operando;
			operando = "";
			
			if(pila.estaVacio() || caracterActual == '(') {
				pila.push(caracterActual);
				continue;
			}
			
			operador = pila.pop();
			
			if(caracterActual == ')') {
				while(operador != '(') {
					prefija += " " + operador;
					operador = pila.pop();
				}
				
				continue;
			}
			
			if(precedencia(caracterActual) > precedencia(operador)) {
				pila.push(operador);
				pila.push(caracterActual);
				continue;
			}
			
			if(precedencia(caracterActual) < precedencia(operador)) {
				do {
					prefija += " " + operador;
					operador = pila.pop();
				}while(operador != null && precedencia(caracterActual) < precedencia(operador));
				
				if(operador != null) {
					pila.push(operador);
				}
				
				pila.push(caracterActual);
				continue;
			}
			
			if(precedencia(caracterActual) == precedencia(operador)) {
				if(asociatividad(operador) == Notacion.IZQ_DER) {
					pila.push(operador);
					pila.push(caracterActual);
				}
				else {
					do {
						prefija += " " + operador;
						operador = pila.pop();
					}while(operador != null && precedencia(caracterActual) > precedencia(operador));
					
					if(operador != null) {
						pila.push(operador);
					}
					
					pila.push(caracterActual);
				}
			}
		}
		
		if(!operando.isEmpty()) {
			prefija += " " + operando;
		}
		
		while(!pila.estaVacio()) {
			prefija += " " + pila.pop();
		}
		
		prefija = invertirExpresion(prefija);
		
		return prefija;
	}
	
	private String invertirExpresion(String exp) {
		String invertida = "";
		char caracterActual;
		
		for(int i = exp.length() - 1; i >= 0; i--) {
			caracterActual = exp.charAt(i);
			
			if(caracterActual == '(') {
				caracterActual = ')';
			}
			else if(caracterActual == ')'){
				caracterActual = '(';
			}
			
			invertida += caracterActual;
		}
		
		return invertida;
	}
}
