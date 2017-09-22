/**
 * 
 */
package es.egv.sherpa.example.dal.database.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import es.egv.sherpa.example.common.exception.SherpaException;
import es.egv.sherpa.example.dal.database.SherpaExampleDatabaseDAL;
import es.egv.sherpa.example.model.Master;

/**
 * <p>
 * This is the implementations class related to SherpaExampleDatabaseDAL.
 * </p>
 * <p>
 * This implementation is based on Java EE standard Java Persistence API (aka JPA).
 * </p>
 * @author EGV
 *
 */ 
@Stateless
public class SherpaExampleDatabaseDALJPAImpl implements SherpaExampleDatabaseDAL {

	/** Trace information management variable. */
	private static final Logger log = LogManager.getLogger(SherpaExampleDatabaseDALJPAImpl.class.getName());
	
	/**
	 * <i>entityManager</i> represents an instance of the EntityManager to allow access to 
	 * the database.  
	 */
	@PersistenceContext(unitName = "SherpaJPAJAR", type = PersistenceContextType.TRANSACTION)
	private EntityManager entityManager;	
	
	/* (non-Javadoc)
	 * @see es.egv.sherpa.example.dal.database.SherpaExampleDAL#addMaster(es.egv.sherpa.example.model.Master)
	 */
	@Override
	public Master addMaster(Master _master) throws SherpaException {
		log.info("addMaster():input:master={}", (_master!=null?_master.toString():"NULL") );

		Master returnobject = null;
		try {
			returnobject = entityManager.merge(_master);		
			log.info("addMaster():return:returnobject={}", (returnobject!=null?returnobject.toString():"NULL") );
		} catch (Exception e) {
			StringBuffer sExMessage = new StringBuffer();
			sExMessage.append("Database service fail.");
			log.error("addMaster():Error:{}:{}", sExMessage.toString(), e.getMessage());
			throw new SherpaException(sExMessage.toString(), e);
			
		}
		return returnobject;
	}
	
	
	/* (non-Javadoc)
	 * @see es.egv.sherpa.example.dal.database.SherpaExampleDAL#findAllMaster()
	 */
	@Override
	public List<Master> findAllMaster() throws SherpaException {
		log.info("findAllMaster():input:N/A");
		@SuppressWarnings("unchecked")

		List<Master> listaMasters = null;
		try {
			listaMasters = entityManager.createQuery("SELECT m FROM Master m").getResultList();
		} catch (Exception e) {
			StringBuffer sExMessage = new StringBuffer();
			sExMessage.append("Database service fail.");
			log.error("findAllMaster():Error:{}:{}", sExMessage.toString(), e.getMessage());
			throw new SherpaException(sExMessage.toString(), e);
			
		}
		
		log.info("findAllMaster():return:listaMasters={}", (listaMasters!=null?listaMasters.toString():"NULL") );
		return listaMasters;
	}	
	


}
