package quantik.util;

/**
 * Enumerado que modela cada color
 * 
 * @author Christian Núñez Duque
 * @since 1.0
 * @version 1.0
 */

public enum Color {

	/**
	 * Variables del enumerado color: BLANCO y NEGRO
	 */
	BLANCO('B'), NEGRO('N');

	/**
	 * Variable privada de tipo char que representa a los elementos del enumerado
	 * color.
	 */
	private char caracter;

	/**
	 * Constructor que define a Color.
	 * 
	 * @param caracterParam: caracter que representa a un color.
	 */
	private Color(char caracterParam) {
		caracter = caracterParam;
	}

	/**
	 * Método que devuelve un carácter que representa el color.
	 * 
	 * @return caracter: caracter que representa a un color.
	 */
	public char toChar() {
		return caracter;
	}

	/**
	 * Método que devuelve el color contrario al obtenido.
	 * 
	 * @return Color.NEGRO o Color.BLANCO
	 */
	public Color obtenerContrario() {
		if (this == BLANCO) {
			return Color.NEGRO;
		} else {
			return Color.BLANCO;
		}

	}

}
