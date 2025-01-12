package quantik.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import quantik.util.Color;
import quantik.util.Figura;

/**
 * Clase que modela la caja del Quantik
 * 
 * @author Christian Núñez Duque
 * @since 1.0
 * @version 2.0
 */

public class Caja {

	/**
	 * Variables de la caja: color y arrayPiezas
	 */
	private Color color;
	private List<Pieza> arrayPiezas = new ArrayList<Pieza>(8);

	/**
	 * Constructor que define a la caja.
	 * 
	 * @param color: color de la caja.
	 */
	public Caja(Color color) {
		this.color = color;
		for (int i = 0; i < Figura.values().length; i++) {
			arrayPiezas.add(new Pieza(Figura.values()[i], color)); 
			arrayPiezas.add(new Pieza(Figura.values()[i], color));	
		}
	}
	
	/**
	 * Constructor privado que define a la caja usado para clonar en profundidad la
	 * caja.
	 * 
	 * @param color: color de la caja.
	 * @param array: piezas de la caja.
	 */
	private Caja(Color color, List<Pieza> lista) {
		this.color = color;
		arrayPiezas.addAll(lista);
	}

	/**
	 * Método que devuelve un clon en profundidad de la caja.
	 * 
	 * @return nueva caja con el mismo color y las piezas clonadas.
	 */
	public Caja clonar() {
		List<Pieza> arrayPiezas = new ArrayList<Pieza>(this.arrayPiezas.size());
		for (Pieza pieza : consultarPiezasDisponibles()) {
			arrayPiezas.add(pieza);
		}
		return new Caja(color, arrayPiezas);
	}

	/**
	 * Método que devuelve el color
	 * 
	 * @return color
	 */
	public Color consultarColor() {
		return this.color;
	}

	/**
	 * Método que devuelve una lista con las piezas disponibles
	 * 
	 * @return arrayPiezas: piezas que hay en la caja.
	 */
	public List<Pieza> consultarPiezasDisponibles() {
		List<Pieza> arrayPiezas = new ArrayList<Pieza>(contarPiezasActuales());
		for (Pieza pieza : this.arrayPiezas) {
			if (pieza != null) {
				arrayPiezas.add(pieza.clonar());
			}
		}
		return arrayPiezas;
	}

	/**
	 * Método que devuelve el número de piezas de la caja.
	 * 
	 * @return contador: número de piezas de la caja.
	 */
	public int contarPiezasActuales() {
		int contador = 0;
		for (int i = 0; i < arrayPiezas.size(); i++) {
			if (arrayPiezas.get(i) != null) {
				contador++;
			}
		}
		return contador;
	}

	/**
	 * Método que devuelve verdadero si la pieza de la figura del parámetro está
	 * disponible y falso si no lo está.
	 * 
	 * @param figura: figura que se va a mirar si está en la caja
	 * @return boolean: true si hay al menos una pieza con la figura pasada por
	 *         parámetro en la caja y false si no.
	 */
	public boolean estaDisponible(Figura figura) {
		for (int i = 0; i < arrayPiezas.size(); i++) {
			if (arrayPiezas.get(i) != null) {
				if (arrayPiezas.get(i).consultarFigura() == figura) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Método que retira una pieza de la caja con la figura del parámetro.
	 * 
	 * @param figura: figura que se va a buscar en la caja.
	 * @return retirada: pieza que se va a retirar de la caja, si no hay piezas con
	 *         la figura del parametro se devuelve null.
	 */
	public Pieza retirar(Figura figura) {
		Pieza retirada = null;
		for (int i = 0; i < arrayPiezas.size(); i++) {
			if (arrayPiezas.get(i) != null) {
				if (arrayPiezas.get(i).consultarFigura() == figura) {
					retirada = arrayPiezas.get(i);
					arrayPiezas.set(i, null);
					break;
				}
			}
		}
		return retirada;
	}

	@Override
	public int hashCode() {
		return Objects.hash(arrayPiezas, color);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Caja other = (Caja) obj;
		return Objects.equals(arrayPiezas, other.arrayPiezas) && color == other.color;
	}

	@Override
	public String toString() {
		return "Caja [color=" + color + ", arrayPiezas=" + arrayPiezas + "]";
	}

}