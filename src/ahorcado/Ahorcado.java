package ahorcado;

import java.util.Random;

public class Ahorcado {
	
	private String [] lista = {
			"CASA",
			"PALABRA",
			"JOSE",
			"NICO",
			"LUISMI",
			"PACO",
			"DAVID",
			"ALVARO"
		}; 
	
	private String palabra;
	private String acertadas;
	
	private char [] rayas;
	private char [] falladas = new char [7];
	
	private int fallos;
	private int aciertos;
	private int tamaño;		//tamaño de la palabra
	private int aleatorio; //Número del random
	
	private Random rd;
	
	public Ahorcado() {
		rd = new Random();
		fallos = aciertos = 0;
		acertadas = "";
	}
	
	private void inicializar () {
		
		int i;
		rayas = new char [tamaño];
		for (i=0; i<tamaño; i++) {
			rayas[i] = '_';
		}
	}
	
	private void seleccionar() {
		
		aleatorio = rd.nextInt(lista.length);
	}
	
	private boolean comprobar(String letra) {
		
		if (acertadas.contains(letra)) {
			return true;
		}
		return false;
	}
	
	public int getFallos() {
		return fallos;
	}

	public int getAciertos() {
		return aciertos;
	}
	
	public int getTamaño() {
		return tamaño;
	}

	public char[] getRayas() {
		return rayas;
	}

	public String getPalabra() {
		return palabra;
	}

	public void setPalabra() {

		seleccionar();
		palabra = lista[aleatorio];
		tamaño = palabra.length();
		inicializar();
	}

	public void setAcertadas() {
		this.acertadas = "";
	}

	public void setFallos(int fallos) {
		this.fallos = fallos;
	}

	public void setAciertos(int aciertos) {
		this.aciertos = aciertos;
	}

	public void partida(String letra) {
		
		int i;
		
		if (palabra.contains(letra) && !comprobar(letra))
		{
			for(i=0; i<tamaño; i++)
			{
				if (palabra.charAt(i) == letra.charAt(0))
				{
					if (!acertadas.contains(letra))
						acertadas += letra;
					aciertos++;
					rayas[i] = letra.charAt(0);
				}
			}
		}
		else if (!comprobar(letra))
		{
			falladas[fallos++] = letra.charAt(0);
		}
	}
}