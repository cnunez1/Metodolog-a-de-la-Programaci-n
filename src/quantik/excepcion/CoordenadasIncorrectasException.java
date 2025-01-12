package quantik.excepcion;

public class CoordenadasIncorrectasException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public CoordenadasIncorrectasException() {
		
	}
	
	public CoordenadasIncorrectasException(String message){
		System.out.println(message);
	}
	
	public CoordenadasIncorrectasException(Throwable cause) throws Exception{
		super (cause);
	}
	
	public CoordenadasIncorrectasException(String message, Throwable cause) throws Exception {
		super (message, cause);
	}
	
}
