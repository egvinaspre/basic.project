package es.egv.sherpa.example.ejb;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import es.egv.sherpa.example.common.exception.SherpaException;
import es.egv.sherpa.example.dal.database.SherpaExampleDatabaseDAL;
import es.egv.sherpa.example.dal.database.impl.SherpaExampleDatabaseDALJPAImpl;
import es.egv.sherpa.example.dal.geonames.SherpaExampleGeonamesDAL;
import es.egv.sherpa.example.dal.geonames.impl.SherpaExampleGeonamesDALRESTImpl;
import es.egv.sherpa.example.model.Detail;
import es.egv.sherpa.example.model.Master;

/**
 * Session Bean implementation class SherpaExampleBusiness
 */
@Stateless
@LocalBean
public class SherpaExampleBusinessEJB {

	/** Trace information management variable. */
	private static final Logger log = LogManager.getLogger(SherpaExampleBusinessEJB.class.getName());

	@EJB
	private SherpaExampleGeonamesDAL geonamesdal;
	
	@EJB
	private SherpaExampleDatabaseDAL databasedal;

	
	/**
     * Default constructor. 
     */
    public SherpaExampleBusinessEJB() {
    	super();

    }
    
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
	throws SherpaException 
	{
		log.info("addNewUser():input:username={},country={},postalcode={}",_username,_country,_postalcode);
		
		// 
		this.validateAddNewUserInput(_username, _country, _postalcode);
		log.debug("addNewUser():Method input validation successful.");
		
		// Obtain city name using geonames service
		String city = this.getPostalCodeCity(_username, _country, _postalcode);
		log.debug("addNewUser():input:username={},country={},postalcode={}",_username,_country,_postalcode);
		log.debug("addNewUser():Getting the city from postal code successful.");
		
		// Persist data in database
		Master master = this.persistInformation(_username, _postalcode, city);
		log.debug("addNewUser():input:username={},country={},postalcode={}",_username,_country,_postalcode);
		log.debug("addNewUser():Persisting information in database successful.");
		
		log.info("addNewUser():return:master={}", (master!=null?master.toString():"NULL"));
		return master;
	}
 
	/**
	 * Validating method addnewuser input variables.
	 * @param _username Username of the user to persist.
	 * @param _country Country where the user lives.
	 * @param _postalcode Postal code where the user lives.
	 * @throws SherpaException In case of one of the input variables is invalid.
	 */
	private void validateAddNewUserInput(String _username, String _country, String _postalcode) throws SherpaException {
		if (_username == null || "".equals(_username)) {
			String mes = new String("Username can not be null or blank.");
			log.error("addNewUser():Error:{}",mes);
			throw new SherpaException(mes);
		}

		if (_country == null || "".equals(_country)) {
			String mes = new String("Countre can not be null or blank.");
			log.error("addNewUser():Error:{}",mes);
			throw new SherpaException(mes);
		}

		if (_postalcode == null || "".equals(_postalcode)) {
			String mes = new String("Postalcode can not be null or blank.");
			log.error("addNewUser():Error:{}",mes);
			throw new SherpaException(mes);
		}
	}
	
	/**
	 * <p>
	 * 	 Get city with postal code information using Geonames service.
	 * </p>
	 * @param _username Username of the user to persist.
	 * @param _country Country where the user lives.
	 * @param _postalcode Postal code where the user lives.
	 * @return The name of the city corresponding with country and postal code. 
	 * @throws SherpaException In case of error using geonames service.
	 */
	private String getPostalCodeCity(String _username, String _country, String _postalcode) 
	throws SherpaException 
	{
//    	geonamesdal = new SherpaExampleGeonamesDALRESTImpl();
		String city = geonamesdal.getPostalCodeCity(_username, _country, _postalcode);  
		log.debug("getPostalCodeCity():Geonames geographical service called. City data obtained [{}]",city);
		
		return city;
	}

	/**
	 * <p>
	 * 	Persist information into database.
	 * </p>
	 * @param _username Username of the user to persist.
	 * @param _postalcode Postal code where the user lives.
	 * @param _city City where the user lives. The city is obtained by getPostalCodeCity private method.
	 * @return Master entity with  
	 * @throws SherpaException 
	 * @see es.egv.sherpa.example.model.Master
	 */
	private Master persistInformation(String _username, String _postalcode, String _city) 
	throws SherpaException 
	{
		Detail detail = new Detail();
		detail.setPostalcode(_postalcode);
		detail.setCity(_city);
		
		Master master = new Master();
		master.setUsername(_username);
		master.setDetail(detail);
		
		Master entidadretorno = databasedal.addMaster(master);
		log.debug("addNewUser():Add info to database.");
		
		return entidadretorno;

	}

    
}
