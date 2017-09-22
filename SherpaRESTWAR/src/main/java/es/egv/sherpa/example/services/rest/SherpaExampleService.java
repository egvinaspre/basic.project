/**
 * 
 */
package es.egv.sherpa.example.services.rest;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import es.egv.sherpa.example.common.exception.SherpaException;
import es.egv.sherpa.example.ejb.SherpaExampleBusinessEJB;

/**
 * RESTful service to give functionality to the Sher.pa's example.
 * @author EGV
 *
 */
@Path("/sherpa")
@Stateless
public class SherpaExampleService {

	/** Trace information management variable. */
	private static final Logger log = LogManager.getLogger(SherpaExampleService.class.getName());
	
	@EJB
	/** SherpaExampleBusinessEJB instance. Injected by container. */
	private SherpaExampleBusinessEJB sherpaexampleejb;

	@GET
	@Path("/addnewuser/{username}/{country}/{postalcode}")
	@Produces(MediaType.APPLICATION_JSON)
	/**
	 * <p>
	 * Adds new username with geographical information in corporate database. 
	 * </p>
	 * @param _username the name of the users we want to add in the database.
	 * @param _country the country where the user leaves.
	 * @param _postalcode the postal code where the user leaves.
	 * @return http 200 if everything its ok. 500 if there is any problem.
	 */
	public Response addNewUser(
			@PathParam("username") String _username, 
			@PathParam("country") String _country,
			@PathParam("postalcode") String _postalcode) 
	{
		log.info("addNewUser():input:username={},country={},postalcode={}", _username, _country, _postalcode);
		try {
			sherpaexampleejb.addNewUser(_username, _country, _postalcode);
			SherpaExampleResponse res = new SherpaExampleResponse("OK", "User is saved into sherpa example system.");
			log.info("addNewUser():return:response={}", (res!=null?res.toString():"NULL"));
			return Response.status(200).entity(res).build();

		} catch (SherpaException e) {
			log.error("addNewUser():Error:{}", e.getMessage());
			StringBuffer mes = new StringBuffer();
			mes.append(e.getMessage()).append((e.getCause()!=null?e.getCause().getMessage():""));
			SherpaExampleResponse res = new SherpaExampleResponse("FAIL", mes.toString());
			return Response.status(200).entity(res).build();
		}

	}
}
