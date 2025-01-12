package quantik.modelo;

import quantik.util.Figura;
import quantik.util.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Clase que modela un grupo del Quantik.
 * 
 * @author Christian Núñez Duque
 * @since 1.0
 * @version 2.0
 */

public class Grupo {

	/**
	 * Variable privada de la clase grupo: celdas.
	 */
	private List<Celda> celdas = new ArrayList<Celda>(4);

	/**
	 * Constructor público para un grupo.
	 * 
	 * @param celdas: Es una lista de celdas que forma el grupo.
	 *
	 */
	public Grupo(List<Celda> celdas) {
		this.celdas.addAll(celdas);
	}

	/**
	 * Método que devuelve un clon en profundidad del grupo.
	 * 
	 * @return Grupo clonado en profundidad.
	 */
	public Grupo clonar() {
		Grupo nuevo = new Grupo(this.celdas);
		nuevo.celdas = new ArrayList<Celda>(4);
		for (int i = 0; i < celdas.size(); i++) {
			nuevo.celdas.add(celdas.get(i).clonar());
		}
		return nuevo;
	}

	/**
	 * Método que devuelve el número de celdas de la lista.
	 * 
	 * @return tamaño de la lista celdas.
	 */
	public int consultarNumeroCeldas() {
		return celdas.size();
	}

	/**
	 * Método que devuelve el número de piezas que hay en el grupo.
	 * 
	 * @return contador: contador con el número de piezas.
	 */
	public int consultarNumeroPiezas() {
		int contador = 0;
		for (int i = 0; i < celdas.size(); i++) {
			if (celdas.get(i).estaVacia() == false) {
				contador++;
			}
		}
		return contador;
	}

	/**
	 * Método que comprueba si una celda pasada por parámetro pertenece a un grupo.
	 * 
	 * @param celdaABuscar: Celda buscada en un grupo.
	 * @return boolean; true si la celda está en el grupo y false si no.
	 */
	public boolean contieneCelda(Celda celdaABuscar) {
		for (int i = 0; i < celdas.size(); i++) {
			if (celdas.get(i).equals(celdaABuscar)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Método que comprueba si un grupo está lleno de piezas con figuras diferentes.
	 * 
	 * @return boolean; true si el grupo está lleno de piezas con figuras diferentes
	 *         y false si no.
	 */
	public boolean estaCompletoConFigurasDiferentes() {
		int Figuras[] = { 0, 0, 0, 0 };
		for (int i = 0; i < this.celdas.size(); i++) {
			for (int j = 0; j < Figura.values().length; j++) {
				if (celdas.get(i).consultarPieza() != null) {
					if (Figuras[j] == 1 && Figura.values()[j] == celdas.get(i).consultarPieza().consultarFigura()) {
						return false; // Se repite una figura
					} else if (Figura.values()[j] == celdas.get(i).consultarPieza().consultarFigura()) {
						Figuras[j] = 1;
					}
				} else {
					return false; // No está completo
				}
			}
		}
		return true;
	}

	/**
	 * Método que comprueba si en un grupo hay una pieza con la figura pasada por
	 * parámetro y el color contrario al pasado por parámetro.
	 * 
	 * @param figura: figura que va a ser buscada en el grupo.
	 * @param color:  color contrario al que va a ser buscado en el grupo.
	 * @return boolean; true si hay una pieza con la figura y el color contrario
	 *         pasados o false si no.
	 */
	public boolean existeMismaPiezaDelColorContrario(Figura figura, Color color) {
		Pieza contaria = new Pieza(figura, color.obtenerContrario());
		for (int i = 0; i < this.celdas.size(); i++) {
			if (celdas.get(i).estaVacia() == false) {
				if (celdas.get(i).consultarPieza() != null) {
					if (celdas.get(i).consultarPieza().equals(contaria)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hash(celdas);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Grupo other = (Grupo) obj;
		return Objects.equals(celdas, other.celdas);
	}

	@Override
	public String toString() {
		return "Grupo [celdas=" + celdas + "]";
	}

}
