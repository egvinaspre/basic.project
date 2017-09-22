package es.egv.sherpa.example.ejb;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import es.egv.sherpa.example.common.exception.SherpaException;
import es.egv.sherpa.example.dal.database.impl.SherpaExampleDatabaseDALJPAImpl;
import es.egv.sherpa.example.dal.geonames.impl.SherpaExampleGeonamesDALRESTImpl;
import es.egv.sherpa.example.model.Detail;
import es.egv.sherpa.example.model.Master;

@RunWith(MockitoJUnitRunner.class)
public class SherpaExampleBusinessEJBTest {

	@Mock
	private SherpaExampleGeonamesDALRESTImpl geonamesmock; 

	@Mock
	private SherpaExampleDatabaseDALJPAImpl databasemock; 

	@InjectMocks
	private SherpaExampleBusinessEJB ejb = new SherpaExampleBusinessEJB();
	
	@Before
	public void init() throws SherpaException {

		Detail detailmock = new Detail();
		detailmock.setCity("Vitoria-Gasteiz");
		detailmock.setPostalcode("01007");
		detailmock.setIddetail(new Integer("10"));
		
		Master mastermock = new Master();
		mastermock.setDetail(detailmock);
		mastermock.setIdmaster(new Integer("10"));
		mastermock.setUsername(new String("eduardo"));

		when(databasemock.addMaster(any(Master.class))).thenReturn(mastermock);
		when(geonamesmock.getPostalCodeCity("eduardo", "ES", "01007")).thenReturn("Vitoria-Gasteiz");
		when(geonamesmock.getPostalCodeCity("eduardo", "SP", "01007")).thenThrow(SherpaException.class);

	}
	
	/**
	 * This test checks the positive use case of the addNewUser method. 
	 */
	@Test
	public void addNewUserTestOK() 
	{
		try {
			Master resultado = ejb.addNewUser("eduardo", "ES", "01007");
			assertEquals(resultado.getUsername(), "eduardo");
		} catch (SherpaException e) {
			fail("Expected all goes ok.");
		}
	}

	/**
	 * This test checks when addNewUser fails with a SherpaException. 
	 * @throws SherpaException In case of error.
	 */
	@Test(expected=SherpaException.class)
	public void addNewUserTestSherpaException() throws SherpaException
	{
		ejb.addNewUser("eduardo", "SP", "01007");
		fail("Expected an SherpaException to be thrown");
	}
	
	
}
