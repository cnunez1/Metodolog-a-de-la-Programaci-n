package quantik.undo;

import java.util.Date;

public abstract class MaquinaDelTiempoAbstracta implements MecanismoDeDeshacer {

	protected int filas;
	protected int columnas;
	protected Date fecha;
	
	public MaquinaDelTiempoAbstracta(Date fecha, int filas, int columnas) {
		this.fecha=fecha;
		this.filas=filas;
		this.columnas=columnas;
	}
	
}
