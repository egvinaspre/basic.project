/**
 * 
 */
package es.egv.sherpa.example.services.rest;

/**
 * POJO used as response in SherpaExampleService REST service.
 * @author EGV
 *
 */
public class SherpaExampleResponse {

	/** OK or FAIL depending the result of the operation. */
	private String code; 
	
	/** Message with interesting information. */
	private String message;
	
	/**
	 * Constructor with parameters.
	 * @param code Response code
	 * @param message Message of the response
	 */
	public SherpaExampleResponse(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}
	

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SherpaExampleResponse [code=");
		builder.append(code);
		builder.append(", message=");
		builder.append(message);
		builder.append("]");
		return builder.toString();
	}
	
	
}
