package es.egv.sherpa.example.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class EntityManagerUtil {
	/** Trace information management variable. */
	private static final Logger log = LogManager.getLogger(EntityManagerUtil.class.getName());

	private static final EntityManagerFactory entityManagerFactory;
	static {
		try {
			log.debug("static instantiation:Trying to obtain an EntityManagerFactory instance.");
			entityManagerFactory = Persistence.createEntityManagerFactory("SherpaJPAJAR");
			log.debug("static:EntityManagerFactory instance obtained.");

		} catch (Throwable ex) {
			log.error("static instantiation:Initial SessionFactory creation failed [{}]", ex.getMessage());
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static EntityManager getEntityManager() {
		log.info("getEntityManager():input:no input params");
		log.info("getEntityManager():output:return entity manager.");
		return entityManagerFactory.createEntityManager();

	}
}