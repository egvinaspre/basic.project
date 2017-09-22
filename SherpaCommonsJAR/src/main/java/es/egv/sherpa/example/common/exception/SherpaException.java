/**
 * 
 */
package es.egv.sherpa.example.common.exception;

/**
 * @author E026733
 *
 */
public class SherpaException extends Exception {

	/** Serial Version UID */
	private static final long serialVersionUID = 5162513976003209540L;

	public SherpaException() {
		super();
	}

	public SherpaException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public SherpaException(String message, Throwable cause) {
		super(message, cause);
	}

	public SherpaException(String message) {
		super(message);
	}

	public SherpaException(Throwable cause) {
		super(cause);
	}

}
