package es.egv.sherpa.example.ejb;

import javax.ejb.Local;

import es.egv.sherpa.example.common.exception.SherpaException;
import es.egv.sherpa.example.model.Master;

@Local
public interface SherpaExampleBusinessEJBLocal {

	
    /**
     * <p>
     * Add new user in the Sherpa example system.
     * </p>
     * Summary:
     *  <ul>
     *  	<li>Obtain city name using geonames service.</li>
     *  	<li>Persist data in database.</li>
     *  </ul>
     * @param _username User token
     * @param _country Country where the user lives.
     * @param _postalcode Postal code where the user lives.
     * @return Master object with the new object in the database.
     * @throws SherpaException If something fails. 
     */
	public Master addNewUser(String _username, String _country, String _postalcode) 
	throws SherpaException; 

}
