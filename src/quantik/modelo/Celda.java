package quantik.modelo;

import java.util.Objects;

/**
 * Clase que modela una celda del Quantik.
 * 
 * @author Christian Núñez Duque
 * @since 1.0
 * @version 1.0
 */

public class Celda {

	/**
	 * Variables privadas de la clase celda: fila, columna y pieza
	 */
	private int fila, columna;
	private Pieza pieza;

	/**
	 * Constructor privado que define a una celda usado para clonar en profundidad.
	 * 
	 * @param fila:    fila de la celda.
	 * @param columna: columna de la celda.
	 * @param pieza:   pieza contenida en la celda.
	 */
	private Celda(int fila, int columna, Pieza pieza) {
		this.fila = fila;
		this.columna = columna;
		this.pieza = pieza;
	}

	/**
	 * Constructor que define a una celda.
	 * 
	 * @param fila:    fila de la celda.
	 * @param columna: columna de la celda.
	 */
	public Celda(int fila, int columna) {
		this.fila = fila;
		this.columna = columna;
	}

	/**
	 * Método que devuelve un clon en profundidad de una celda.
	 * 
	 * @return nueva celda con la misma fila y columna y la pieza clonada si la hay.
	 */
	public Celda clonar() {
		if (estaVacia() == false) {
			return new Celda(this.fila, this.columna, pieza.clonar());
		} else {
			return new Celda(this.fila, this.columna, null);
		}
	}

	public void colocar(Pieza pieza) {
		this.pieza = pieza;
	}

	public int consultarColumna() {
		return this.columna;
	}

	public int consultarFila() {
		return this.fila;
	}

	public Pieza consultarPieza() {
		if (estaVacia() == false) {
			return pieza.clonar();
		} else {
			return null;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Celda other = (Celda) obj;
		return columna == other.columna && fila == other.fila && Objects.equals(pieza, other.pieza);
	}

	public boolean estaVacia() {
		if (this.pieza == null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(columna, fila, pieza);
	}

	@Override
	public String toString() {
		return "Celda [fila=" + fila + ", columna=" + columna + ", pieza=" + pieza + "]";
	}

}
