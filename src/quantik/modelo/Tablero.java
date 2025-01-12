package quantik.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import quantik.excepcion.CoordenadasIncorrectasException;

/**
 * Clase que modela el tablero del Quantik.
 * 
 * @author Christian Núñez Duque
 * @since 1.0
 * @version 2.0
 */

public class Tablero {

	/**
	 * Variable privada de tipo List: celdasTablero.
	 */
	private List<List<Celda>> celdasTablero = new ArrayList<List<Celda>>(4);

	/**
	 * Constructor que define a tablero.
	 */
	public Tablero() {
		celdasTablero = new ArrayList<List<Celda>>(4);
		for (int i = 0; i < 4; i++) {
			celdasTablero.add(new ArrayList<Celda>());
			for (int j = 0; j < 4; j++) {
				celdasTablero.get(i).add(new Celda(i, j));
			}
		}
	}

	/**
	 * Método que devuelve la representación del tablero en modo texto.
	 * 
	 * @return cadena: string que contiene la representación del tablero en modo
	 *         texto.
	 */
	public String aTexto() {
		String cadena = "\n";
		cadena += "       0     1     2     3\n";
		for (int i = 0; i < consultarNumeroFilas(); i++) {
			cadena += i + "     ";
			for (int j = 0; j < consultarNumeroColumnas(); j++) {
				if (celdasTablero.get(i).get(j).estaVacia() == true) {
					cadena += "----- ";
				} else {
					cadena += "-" + celdasTablero.get(i).get(j).consultarPieza().aTexto() + "- ";
				}
			}
			cadena += "\n";
		}
		return cadena;
	}

	/**
	 * Método que clona un tablero en profundidad.
	 * 
	 * @return nuevo: tablero clonado en profundidad.
	 */
	public Tablero clonar() {
		Tablero nuevo = new Tablero();
		nuevo.celdasTablero = new ArrayList<List<Celda>>(4);
		for (int i = 0; i < 4; i++) {
			nuevo.celdasTablero.add(new ArrayList<Celda>(4));
			for (int j = 0; j < 4; j++) {
				nuevo.celdasTablero.get(i).add(celdasTablero.get(i).get(j).clonar());
			}
		}
		return nuevo;
	}

	/**
	 * Método que coloca una pieza en una celda.
	 * 
	 * @param fila:    fila de la celda en la que se va a colocar la pieza.
	 * @param columna: columna de la celda en la que se va a colocar la pieza.
	 * @param pieza:   pieza que va a ser colocada.
	 * @throws CoordenadasIncorrectasException: excepción lanzada si las coordenadas
	 *                                          no están en el tablero.
	 */
	public void colocar(int fila, int columna, Pieza pieza) throws CoordenadasIncorrectasException {
		if (estaEnTablero(fila, columna) == false) {
			throw new CoordenadasIncorrectasException("Coordenadas ilegales");
		} else {
			celdasTablero.get(fila).get(columna).colocar(pieza);
		}
	}

	public Celda consultarCelda(int fila, int columna) throws CoordenadasIncorrectasException {
		if (estaEnTablero(fila, columna) == false) {
			throw new CoordenadasIncorrectasException("Coordenadas ilegales");
		} else {
			return celdasTablero.get(fila).get(columna).clonar();
		}
	}

	/**
	 * Método que devuelve el número de columnas del tablero.
	 * 
	 * @return número de columnas del tablero.
	 */
	public int consultarNumeroColumnas() {
		return celdasTablero.get(0).size();
	}

	/**
	 * Método que devuelve el número de filas del tablero.
	 * 
	 * @return número de filas del tablero.
	 */
	public int consultarNumeroFilas() {
		return celdasTablero.size();
	}

	/**
	 * Método que comprueba si una celda está en el tablero.
	 * 
	 * @param fila:    fila a la que pertenece la celda.
	 * @param columna: columna a la que pertenece la celda.
	 * @return boolean; devuelve true si la celda con la fila y columna pasada por
	 *         parámetro está en el tablero y false si no.
	 */
	public boolean estaEnTablero(int fila, int columna) {
		if (fila >= 0 && celdasTablero.size() > fila && columna >= 0 && celdasTablero.get(0).size() > columna) {
			return true;
		}
		return false;
	}

	/**
	 * Método que devuelve una celda con las coordenadas pasadas por parámetro.
	 * 
	 * @param fila:    fila de la celda a buscar.
	 * @param columna: columna de la celda a buscar.
	 * @return celda buscada.
	 * @throws CoordenadasIncorrectasException: excepción de coordenadas fuera del
	 *                                          tablero.
	 */
	Celda obtenerCelda(int fila, int columna) throws CoordenadasIncorrectasException {
		if (estaEnTablero(fila, columna) == false) {
			throw new CoordenadasIncorrectasException("Coordenadas ilegales");
		} else {
			return celdasTablero.get(fila).get(columna);
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(celdasTablero);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tablero other = (Tablero) obj;
		return Objects.equals(celdasTablero, other.celdasTablero);
	}

	@Override
	public String toString() {
		return "Tablero [celdasTablero=" + celdasTablero + "]";
	}

}
