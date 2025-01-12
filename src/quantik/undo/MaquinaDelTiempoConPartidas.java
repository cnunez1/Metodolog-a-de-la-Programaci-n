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

public class MaquinaDelTiempoConPartidas extends MaquinaDelTiempoAbstracta{
	
	protected int jugadas;
	protected Tablero tablero = new Tablero();
	protected Partida partidaActual;
	protected List <Caja> cajasBlancas = new ArrayList <Caja>(16);
	protected List <Caja> cajasNegras = new ArrayList <Caja>(16);
	protected List <Tablero> tableros = new ArrayList <Tablero>(16);
	protected List <Partida> partidas = new ArrayList <Partida>(16);

	public MaquinaDelTiempoConPartidas(Date fecha, int filas, int columnas) {
		super(fecha, filas, columnas);
		partidaActual=new Partida(new Tablero(), new Caja(Color.BLANCO), new Caja(Color.NEGRO));
	}

	public int consultarNumeroJugadasEnHistorico() {
		return partidas.size();
	}
	
	public Partida consultarPartidaActual() {
		return partidaActual.clonar();
	}
	
	public void deshacerJugada() {
		if(consultarNumeroJugadasEnHistorico()>0) {
			partidas.remove(consultarNumeroJugadasEnHistorico()-1);
		}
	}
	
	public void hacerJugada(int fila, int columna, Figura figura, Color color) throws CoordenadasIncorrectasException {
		if(tablero.estaEnTablero(fila, columna)==false) {
			throw new CoordenadasIncorrectasException("Coordenadas ilegales");
		}else {
			//if(partidas.size()<1) {
				//partidaActual=new Partida(new Tablero(), new Caja(Color.BLANCO), new Caja(Color.NEGRO));
				//partidas.add(partidaActual.clonar());
			//}
			if(partidas.size()<=this.filas*this.columnas) {
				if(color==Color.BLANCO) {
					partidaActual.colocarPiezaEnTurnoActual(fila, columna, partidaActual.consultarCajaBlancas().retirar(figura).consultarFigura());
				}else if(color==Color.NEGRO) {
					partidaActual.colocarPiezaEnTurnoActual(fila, columna, partidaActual.consultarCajaNegras().retirar(figura).consultarFigura());
				}
				partidaActual.cambiarTurno();
				partidas.add(partidaActual.clonar());
			}
		}
	}
	
	public Date obtenerFechaInicio() {
		return this.fecha;
	}
}
