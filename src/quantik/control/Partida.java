package quantik.control;

import java.util.Objects;

import quantik.excepcion.CoordenadasIncorrectasException;
import quantik.modelo.Caja;
import quantik.modelo.GestorGrupos;
import quantik.modelo.Tablero;
import quantik.util.Figura;
import quantik.util.Color;

/**
 * Clase que modela las reglas de la partida del Quantik.
 * 
 * @author Christian Núñez Duque
 * @since 1.0
 * @version 2.0
 */

public class Partida {

	/**
	 * Variables privadas de la clase partida: gestor, tablero, turno, cajaBlancas,
	 * cajaNegras, contadorTurno.
	 */
	private GestorGrupos gestor;
	private Tablero tablero;
	private Color turno;
	private Caja cajaBlancas;
	private Caja cajaNegras;
	private int contadorTurno;

	/**
	 * Constructor que define a Partida.
	 * 
	 * @param tablero:     tablero a inicializar.
	 * @param cajaBlancas: caja de piezas blancas a inicializar.
	 * @param cajaNegras:  caja de piezas negras a inicializar.
	 */
	public Partida(Tablero tablero, Caja cajaBlancas, Caja cajaNegras) {
		this.contadorTurno = 0;
		this.turno = Color.BLANCO;
		this.tablero = tablero;
		this.cajaBlancas = cajaBlancas;
		this.cajaNegras = cajaNegras;
		GestorGrupos gestor = new GestorGrupos(tablero);
		this.gestor = gestor;
	}
	
	private Partida(Tablero tablero, Caja cajaBlancas, Caja cajaNegras, int contadorTurno, Color turno) {
		this.tablero = tablero;
		this.gestor = new GestorGrupos(this.tablero);
		this.cajaBlancas = cajaBlancas;
		this.cajaNegras = cajaNegras;
		this.turno = turno;
		this.contadorTurno=contadorTurno;
	}

	/**
	 * Método que cambia el turno al jugador contrario.
	 */
	public void cambiarTurno() {
		this.turno = turno.obtenerContrario();
	}

	public Caja consultarCajaBlancas() {
		return this.cajaBlancas.clonar();
	}
	
	public Caja consultarCajaNegras() {
		return this.cajaNegras.clonar();
	}
	
	public Partida clonar() {
		return new Partida(consultarTablero(), consultarCajaBlancas(), consultarCajaNegras(), consultarNumeroJugada(), consultarTurno());
	}
	
	/**
	 * Método que coloca una pieza de la caja del jugador que tiene el turno en la
	 * celda con las coordenadas pasadas por parámetro.
	 * 
	 * @param fila:    fila en la que se va a colocar la pieza.
	 * @param columna: columna en la que se va a colocar la pieza.
	 * @param figura:  figura que se desea colocar.
	 * @throws CoordenadasIncorrectasException 
	 */
	public void colocarPiezaEnTurnoActual(int fila, int columna, Figura figura) {
		if (tablero.estaEnTablero(fila, columna) == true) {
			if (esJugadaLegalEnTurnoActual(fila, columna, figura) == true) {
				if (consultarTurno() == Color.BLANCO) {
					if (cajaBlancas.estaDisponible(figura) == true) {
						try {
							tablero.colocar(fila, columna, cajaBlancas.retirar(figura));
						}catch(CoordenadasIncorrectasException ex) {
							throw new RuntimeException("Error de coordenadas.", ex);
						}
					}
				} else if (consultarTurno() == Color.NEGRO) {
					if (cajaNegras.estaDisponible(figura) == true) {
						try {
							tablero.colocar(fila, columna, cajaNegras.retirar(figura));
						}catch(CoordenadasIncorrectasException ex) {
							throw new RuntimeException("Error de coordenadas.", ex);
						}
					}
				}
			}
		this.contadorTurno++;
		}
	}

	public Color consultarGanador() {
		if (hayAlgunGrupoCompleto() == true) {
			return consultarTurno();
		} else if (estaBloqueadoTurnoActual() == true) {
			return consultarTurno().obtenerContrario();
		}
		return null;
	}

	public int consultarNumeroJugada() {
		return this.contadorTurno;
	}

	public Tablero consultarTablero() {
		return this.tablero.clonar();
	}

	public Color consultarTurno() {
		return this.turno;
	}

	/**
	 * Método que comprueba si una jugada es legal en el turno actual.
	 * 
	 * @param fila:    fila en la que se va a comprobar si el movimiento es legal.
	 * @param columna: columna en la que se va a comprobar si el movimiento es
	 *                 legal.
	 * @param figura:  figura que se va a comprobar si puede ser colocada.
	 * @return boolean; true si la jugada es legal en el turno actual y false si no.
	 */
	public boolean esJugadaLegalEnTurnoActual(int fila, int columna, Figura figura) {

		if (tablero.estaEnTablero(fila, columna) == false) {
			return false;
		}
		if ((cajaBlancas.estaDisponible(figura) == false && consultarTurno() == Color.BLANCO)
				|| (cajaNegras.estaDisponible(figura) == false && consultarTurno() == Color.NEGRO)) {
			return false;
		}

		try {
			if (gestor.hayConflictoEnGruposDeCelda(consultarTablero().consultarCelda(fila, columna), figura,
					consultarTurno())) {
				return false;
			}
		}catch(CoordenadasIncorrectasException ex) {
			throw new RuntimeException("Error de coordenadas.", ex);
		}

		return true;
	}

	/**
	 * Método que comprueba si la partida está acabada.
	 * 
	 * @return boolean; true si la partida ha acabado y false si no.
	 */
	public boolean estaAcabadaPartida() {
		if (hayAlgunGrupoCompleto() == true || estaBloqueadoTurnoActual() == true) {
			return true;
		}
		return false;
	}

	/**
	 * Método que comprueba si el jugador que tiene el turno no puede colocar pieza.
	 * 
	 * @return boolean; true si no puede colocar pieza y false en el caso de que
	 *         pueda.
	 */
	public boolean estaBloqueadoTurnoActual() {
		if ((cajaBlancas.contarPiezasActuales() == 0 && consultarTurno() == Color.BLANCO)
				|| (cajaNegras.contarPiezasActuales() == 0 && consultarTurno() == Color.NEGRO)) {
			return true;
		}

		boolean resultado = true;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (consultarTurno() == Color.BLANCO) {
					for (int k = 0; k < cajaBlancas.contarPiezasActuales(); k++) {
						try {
							if (gestor.hayConflictoEnGruposDeCelda(consultarTablero().consultarCelda(i, j),
									cajaBlancas.consultarPiezasDisponibles().get(k).consultarFigura(),
									consultarTurno()) == false) {
								resultado = false;
							}
						}catch(CoordenadasIncorrectasException ex) {
							throw new RuntimeException("Error de coordenadas.", ex);
						}
					}
				} else if (consultarTurno() == Color.NEGRO) {
					for (int k = 0; k < cajaNegras.contarPiezasActuales(); k++) {
						try {
							if (gestor.hayConflictoEnGruposDeCelda(consultarTablero().consultarCelda(i, j),
									cajaNegras.consultarPiezasDisponibles().get(k).consultarFigura(),
									consultarTurno()) == false) {
								resultado = false;
							}
						}catch(CoordenadasIncorrectasException ex) {
							throw new RuntimeException("Error de coordenadas.", ex);
						}
					}
				}
			}
		}
		return resultado;
	}

	/**
	 * Método que comprueba si hay grupos completos.
	 * 
	 * @return boolean; true si hay alguno y false si no.
	 */
	public boolean hayAlgunGrupoCompleto() {
		if (consultarNumeroJugada() == 0) {
			return false;
		}
		if (gestor.hayGrupoGanador() == true) {
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cajaBlancas, cajaNegras, contadorTurno, gestor, tablero, turno);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Partida other = (Partida) obj;
		return Objects.equals(cajaBlancas, other.cajaBlancas) && Objects.equals(cajaNegras, other.cajaNegras)
				&& contadorTurno == other.contadorTurno && Objects.equals(gestor, other.gestor)
				&& Objects.equals(tablero, other.tablero) && turno == other.turno;
	}

	@Override
	public String toString() {
		return "Partida [gestor=" + gestor + ", tablero=" + tablero + ", turno=" + turno + ", cajaBlancas="
				+ cajaBlancas + ", cajaNegras=" + cajaNegras + ", contadorTurno=" + contadorTurno + "]";
	}

}
