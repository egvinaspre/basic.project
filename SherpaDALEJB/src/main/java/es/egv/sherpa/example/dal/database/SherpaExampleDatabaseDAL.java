package es.egv.sherpa.example.dal.database;

import java.util.List;

import es.egv.sherpa.example.common.exception.SherpaException;
import es.egv.sherpa.example.model.Master;

/**
 * <p>
 * The interface SherpaExampleDatabaseDAL contains the methods are needed 
 * to access the database in the Sherpa Example.  
 * </p>
 * @author EGV
 */
public interface SherpaExampleDatabaseDAL {

	/**
	 * Adds a new master record in the database.
	 * @param master The master record to add. 
	 * @see es.egv.sherpa.example.model.Master
	 * @return The master record added.
	 * @throws SherpaException In case of error 
	 */
	Master addMaster(Master master) throws SherpaException;

	/**
	 * Returns the list of all the master records in the database.
	 * @return The List of master records. 
	 * @throws SherpaException In case of error
	 * @see es.egv.sherpa.example.model.Master
	 */
	List<Master> findAllMaster() throws SherpaException;

}