package quantik.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import quantik.excepcion.CoordenadasIncorrectasException;
import quantik.util.Color;
import quantik.util.Figura;

/**
 * Clase que modela el gestor de grupos del Quantik.
 * 
 * @author Christian Núñez Duque
 * @since 1.0
 * @version 2.0
 */

public class GestorGrupos {

	/**
	 * Variable del gestor de grupos: grupos.
	 */
	private List<Grupo> grupos = new ArrayList<Grupo>(12);

	/**
	 * Constructor que define al gestor de grupos.
	 * 
	 * @param tablero: tablero del que se van a obtener las celdas para el gestor.
	 * @throws CoordenadasIncorrectasException
	 */
	public GestorGrupos(Tablero tablero) {
		List<Celda> celdasGrupo = new ArrayList<Celda>(4);
		// Grupos horizontales
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				try {
					celdasGrupo.add(tablero.obtenerCelda(i, j));
				} catch (CoordenadasIncorrectasException ex) {
					throw new RuntimeException("Error de coordenadas.", ex);
				}
			}
			grupos.add(new Grupo(celdasGrupo));
			celdasGrupo = new ArrayList<Celda>(4);
		}

		// Grupos verticales
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				try {
					celdasGrupo.add(tablero.obtenerCelda(j, i));
				} catch (CoordenadasIncorrectasException ex) {
					throw new RuntimeException("Error de coordenadas.", ex);
				}
			}
			grupos.add(i + 4, new Grupo(celdasGrupo));
			celdasGrupo = new ArrayList<Celda>(4);
		}

		// Grupos cuadrados
		try {
			celdasGrupo.add(tablero.obtenerCelda(0, 0));
			celdasGrupo.add(tablero.obtenerCelda(0, 1));
			celdasGrupo.add(tablero.obtenerCelda(1, 0));
			celdasGrupo.add(tablero.obtenerCelda(1, 1));

			grupos.add(8, new Grupo(celdasGrupo));
			celdasGrupo = new ArrayList<Celda>(4);
		
			celdasGrupo.add(tablero.obtenerCelda(0, 2));
			celdasGrupo.add(tablero.obtenerCelda(0, 3));
			celdasGrupo.add(tablero.obtenerCelda(1, 2));
			celdasGrupo.add(tablero.obtenerCelda(1, 3));
		
			grupos.add(9, new Grupo(celdasGrupo));
			celdasGrupo = new ArrayList<Celda>(4);

			celdasGrupo.add(tablero.obtenerCelda(2, 0));
			celdasGrupo.add(tablero.obtenerCelda(2, 1));
			celdasGrupo.add(tablero.obtenerCelda(3, 0));
			celdasGrupo.add(tablero.obtenerCelda(3, 1));

			grupos.add(10, new Grupo(celdasGrupo));
			celdasGrupo = new ArrayList<Celda>(4);
		
			celdasGrupo.add(tablero.obtenerCelda(2, 2));
			celdasGrupo.add(tablero.obtenerCelda(2, 3));
			celdasGrupo.add(tablero.obtenerCelda(3, 2));
			celdasGrupo.add(tablero.obtenerCelda(3, 3));
		}catch(CoordenadasIncorrectasException ex) {
			throw new RuntimeException("Error de coordenadas.", ex);
		}
		grupos.add(11, new Grupo(celdasGrupo));
		celdasGrupo = new ArrayList<Celda>(4);
	}

	/**
	 * Método que comprueba si se puede colocar una pieza en una celda respecto a
	 * las piezas colocadas en los grupos en común a la celda.
	 * 
	 * @param celda:  celda en la que se intenta colocar la pieza.
	 * @param figura: figura que va a ser comparada con las de los grupos comunes a
	 *                la celda.
	 * @param turno:  color que va a ser comparado a los colores de las figuras de
	 *                los grupos comunes a la celda.
	 * @return boolean; true si hay un conflicto con los grupos de la celda y false
	 *         si no.
	 */
	public boolean hayConflictoEnGruposDeCelda(Celda celda, Figura figura, Color turno) {
		List<Grupo> contenedores = obtenerGruposConteniendoCelda(celda);
		if (celda.estaVacia() == false) {
			return true;
		}
		for (int i = 0; i < contenedores.size(); i++) {
			if (contenedores.get(i).existeMismaPiezaDelColorContrario(figura, turno)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Método que comprueba si hay un grupo con la condición de victoria.
	 * 
	 * @return boolean: true si hay algún grupo con la combinación de piezas
	 *         ganadora y false si no
	 */
	public boolean hayGrupoGanador() {
		for (int i = 0; i < grupos.size(); i++) {
			if (grupos.get(i).estaCompletoConFigurasDiferentes() == true) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Método que busca los grupos que contienen una celda.
	 * 
	 * @param celda: la celda de la que se van a buscar los grupos en los que está
	 *               contenida.
	 * @return contenedores: los grupos en los que está contenida la celda.
	 */
	public List<Grupo> obtenerGruposConteniendoCelda(Celda celda) {
		List<Grupo> contenedores = new ArrayList<Grupo>(3);
		int k = 0;
	
		for (int i = 0; i < grupos.size(); i++) {
			if (grupos.get(i).contieneCelda(celda)) {
				contenedores.add(k, grupos.get(i));
				k++;
			}
		}
		return contenedores;
	}

	@Override
	public int hashCode() {
		return Objects.hash(grupos);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GestorGrupos other = (GestorGrupos) obj;
		return Objects.equals(grupos, other.grupos);
	}

	@Override
	public String toString() {
		return "GestorGrupos [grupos=" + grupos + "]";
	}
	
}
