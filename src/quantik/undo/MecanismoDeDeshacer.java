package quantik.undo;

import java.util.Date;
import quantik.control.Partida;
import quantik.excepcion.CoordenadasIncorrectasException;
import quantik.util.Color;
import quantik.util.Figura;

public interface MecanismoDeDeshacer {
	
	public int consultarNumeroJugadasEnHistorico();
	public Partida consultarPartidaActual();
	public void deshacerJugada();
	public void hacerJugada(int fila, int columna, Figura figura, Color color) throws CoordenadasIncorrectasException;
	public Date obtenerFechaInicio();
	
}
