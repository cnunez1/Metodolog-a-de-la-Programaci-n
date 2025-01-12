package quantik.util;

/**
 * Enumerado que modela cada figura.
 * 
 * @author Christian Núñez Duque
 * @since 1.0
 * @version 1.0
 */

public enum Figura {
	/**
	 *Variables del enumerado figura: CILINDRO, CONO, CUBO y ESFERA. 
	 */
	CILINDRO("CL"), CONO("CN"), CUBO("CB"), ESFERA("ES");

	/**
	 * Variable privada del enumerado Figura.
	 */
	private String figuras;

	private Figura(String cadena) {
		figuras = cadena;
	}

	/**
	 * Método que devuelve una String a partir de una figura.
	 * 
	 * @return figuras: string de los elementos del enumerado en modo texto.
	 */
	public String aTexto() {
		return figuras;
	}
}
