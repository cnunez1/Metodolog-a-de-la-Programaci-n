package quantik.undo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import quantik.control.Partida;
import quantik.excepcion.CoordenadasIncorrectasException;
import quantik.modelo.Caja;
import quantik.modelo.Tablero;
import quantik.util.Color;
import quantik.util.Figura;


public class MaquinaDelTiempoConJugadas extends MaquinaDelTiempoAbstracta{

	
	public MaquinaDelTiempoConJugadas(Date fecha, int filas, int columnas) {
		super(fecha, filas, columnas);
		/*jugadas=0;
		this.fecha=fecha;
		cajasBlancas.add(cajaBlancas);
		cajasNegras.add(cajaNegras);
		tableros.add(tablero);
		for(int i=0; i<15; i++) {
			cajasBlancas.add(null);
			cajasNegras.add(null);
			tableros.add(null);
		}*/
	}

	/*
	public int consultarNumeroJugadasEnHistorico() {
		return this.jugadas;
	}

	public Partida consultarPartidaActual() {
		return this.partidaActual;
	}

	public void deshacerJugada() {
		partidaActual=new Partida(tableros.get(jugadas).clonar(), cajasBlancas.get(jugadas), cajasNegras.get(jugadas));
		jugadas--;
	}

	@Override
	public void hacerJugada(int fila, int columna, Figura figura, Color color) throws CoordenadasIncorrectasException {
		if(tablero.estaEnTablero(fila, columna)==false) {
			throw new CoordenadasIncorrectasException("Coordenadas ilegales");
		}else {
			if(color==Color.BLANCO) {
				tablero.colocar(fila, columna, cajaBlancas.retirar(figura));
				tableros.add(tablero.clonar());
				cajasBlancas.add(cajaBlancas.clonar());
			}else if(color==Color.NEGRO) {
				tablero.colocar(fila, columna, cajaNegras.retirar(figura));
				tableros.add(tablero.clonar());
				cajasNegras.add(cajaNegras.clonar());
			}
			jugadas++;
		}
	}
	
	*/
	public Date obtenerFechaInicio() {
		return fecha;
	}

}
