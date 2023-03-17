package modelos;

public class Postfija extends Notacion{
	
	public Postfija(String expresion) {
		super(expresion);
	}
	
	@Override
	public String convertir() {
		Pila operadores = new Pila(15);
		String postfija = "", operando = "";
		Character caracterActual;
		Character operador;
		
		while((caracterActual = proximoCaracter()) != null) {
			if(esOperando(caracterActual)) {
				operando += caracterActual;
				continue;
			}
			
			postfija += operando;
			operando = "";
				
			if(operadores.estaVacio() || caracterActual == '(') {
				operadores.push(caracterActual);
				continue;
			}
			
			operador = operadores.pop();
			
			if(operador == '(') {
				operadores.push(operador);
				operadores.push(caracterActual);
				continue;
			}
			
			if(caracterActual == ')') {
				while(operador != '(') {
					postfija += operador;
					operador = operadores.pop();
				}
				
				continue;
			}
			
			if(precedencia(caracterActual) > precedencia(operador)) {
				operadores.push(operador);
				operadores.push(caracterActual);
			}
			else if(precedencia(caracterActual) < precedencia(operador)){
				do {
					postfija += operador;
					operador = operadores.pop();
				}while(operador != null && precedencia(caracterActual) < precedencia(operador));
				
				if(operador != null) {
					operadores.push(operador);
				}
				
				operadores.push(caracterActual);
			}
			else {
				if(asociatividad(caracterActual) == Notacion.IZQ_DER) {
					postfija += operador;
					operadores.push(caracterActual);
				}
				else {
					operadores.push(operador);
					operadores.push(caracterActual);
				}
			}
		}
		
		if(!operando.isEmpty()) {
			postfija += operando;
		}
		
		while(!operadores.estaVacio()) {
			postfija += operadores.pop();
		}
		
		return postfija;
	}
	
	
}
