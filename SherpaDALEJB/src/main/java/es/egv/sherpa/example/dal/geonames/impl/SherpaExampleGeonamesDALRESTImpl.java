/**
 * 
 */
package es.egv.sherpa.example.dal.geonames.impl;

import java.util.List;

import javax.ejb.Stateless;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.geonames.InvalidParameterException;
import org.geonames.PostalCode;
import org.geonames.PostalCodeSearchCriteria;
import org.geonames.WebService;

import es.egv.sherpa.example.common.exception.SherpaException;
import es.egv.sherpa.example.dal.geonames.SherpaExampleGeonamesDAL;

/**
 * <p>
 * This is SherpaExampleGeonamesDAL implementation using geonames REST library.
 * </p>
 * @see es.egv.sherpa.example.dal.geonames.SherpaExampleGeonamesDAL 
 * @author EGV
 *
 */
@Stateless
public class SherpaExampleGeonamesDALRESTImpl implements SherpaExampleGeonamesDAL {

	/** Trace information management variable. */
	private static final Logger log = LogManager.getLogger(SherpaExampleGeonamesDALRESTImpl.class.getName());

	@Override
	public String getPostalCodeCity(String _username, 
									String _country, 
									String _postalcode) 
	throws SherpaException {
		
		log.info("getPostalCodeCity():input:username={},country={},postalcode={}",_username,_country,_postalcode);
		String returnvalue = null;
		
		// Fill geonames library needed information. 
		WebService.setUserName(_username);
		log.debug("getPostalCodeCity():Geonames library needed information filled.");

		// Fill postal code search criteria
		// Country is necessary to identify the city correctly. 
		PostalCodeSearchCriteria searchCriteria = new PostalCodeSearchCriteria();
		searchCriteria.setPostalCode(_postalcode);
		
		try {
			searchCriteria.setCountryCode(_country);
		} catch (InvalidParameterException e) {
			StringBuffer sExMessage = new StringBuffer();
			sExMessage.append("Geonames service fail.");
			sExMessage.append("The country [").append(_country).append("] is not a valid country.");
			log.error("getPostalCodeCity():Error:{}:{}", sExMessage.toString(), e.getMessage());
			throw new SherpaException(sExMessage.toString(), e);
		}
		log.debug("getPostalCodeCity():Geonames service search criteria filled.");

		// Call geonames web service.
		List<PostalCode> postalCodeInfoList;
		try {
			postalCodeInfoList = WebService.postalCodeSearch(searchCriteria);
		} catch (Exception e) {
			StringBuffer sExMessage = new StringBuffer();
			sExMessage.append("Geonames service fail.");
			sExMessage.append("Something goes wrong in the external service call.");
			log.error("getPostalCodeCity():Error:{}:{}", sExMessage.toString(), e.getMessage());
			throw new SherpaException(sExMessage.toString(), e);
		}

		// Extract postal code information. 
		PostalCode postalCodeInfo = null; 
		if (postalCodeInfoList.isEmpty()) {
			StringBuffer sExMessage = new StringBuffer();
			sExMessage.append("Geonames service fail.");
			sExMessage.append("The Service doesn't returns data for postal code information:");
			sExMessage.append("[");
			sExMessage.append("username=").append(_username).append(",");
			sExMessage.append("country=").append(_country).append(",");
			sExMessage.append("postalcode=").append(_postalcode);			
			sExMessage.append("]");
			log.error("getPostalCodeCity():Error:{}", sExMessage.toString());
			throw new SherpaException(sExMessage.toString());
		} 
		log.debug("getPostalCodeCity():Search done. The result of the search is: {}", postalCodeInfoList.toString());

		postalCodeInfo = postalCodeInfoList.get(0);
		returnvalue = postalCodeInfo.getPlaceName();

		log.info("getPostalCodeCity():return:returnvalue={}",returnvalue);

		return returnvalue;
	}

}
