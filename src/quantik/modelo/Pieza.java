package quantik.modelo;

import java.util.Objects;

import quantik.util.Color;
import quantik.util.Figura;

/**
 * Clase que modela una pieza del Quantik
 * 
 * @author Christian Núñez Duque
 * @since 1.0
 * @version 1.0
 */

public class Pieza {

	/**
	 * Variables privadas de la clase pieza: color y figura.
	 */
	private Color color;
	private Figura figura;

	/**
	 * Constructor que define a una pieza
	 * 
	 * @param figura: figura de la pieza.
	 * @param color:  color de la pieza.
	 */
	public Pieza(Figura figura, Color color) {
		this.color = color;
		this.figura = figura;
	}

	/**
	 * Método que devuelve una cadena con la figura y el color de una pieza.
	 * 
	 * @return string que contiene la figura y el color en modo texto.
	 */
	public String aTexto() {
		return (figura.aTexto() + color.toChar());
	}

	/**
	 * Método que clona una pieza en profundidad.
	 * 
	 * @return pieza clonada en profundidad.
	 */
	public Pieza clonar() {
		return new Pieza(this.figura, this.color);
	}

	public Color consultarColor() {
		return this.color;
	}

	public Figura consultarFigura() {
		return this.figura;
	}

	@Override
	public int hashCode() {
		return Objects.hash(color, figura);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pieza other = (Pieza) obj;
		return color == other.color && figura == other.figura;
	}

	@Override
	public String toString() {
		return "Pieza [color=" + color + ", figura=" + figura + "]";
	}

}
