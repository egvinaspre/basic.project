/**
 * 
 */
package es.egv.sherpa.example.dal.geonames;

import es.egv.sherpa.example.common.exception.SherpaException;

/**
 * <p>
 * This interface represents the access to geonames service to obtain
 * geographical information using user's postal code information.
 * </p>
 * @author EGV
 *
 */
public interface SherpaExampleGeonamesDAL {

	/**
	 * Returns the city of a postal code.
	 * @param username the username token. 
	 * @param country the country where the user lives. Is very important to desambiguation. 
	 * @param postalCode the postal code where the user lives.
	 * @return java.lang.String with the name of the city. 
	 * @throws SherpaException If the external service is not available, if the input data are not correct.
	 */
	public String getPostalCodeCity(String username, String country, String postalCode) throws SherpaException;
	
}
