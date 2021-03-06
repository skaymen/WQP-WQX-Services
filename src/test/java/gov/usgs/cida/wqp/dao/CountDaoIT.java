package gov.usgs.cida.wqp.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import gov.usgs.cida.wqp.parameter.FilterParameters;
import gov.usgs.cida.wqp.springinit.DBTestConfig;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.NONE,
	classes={DBTestConfig.class, CountDao.class})
public class CountDaoIT {

	@Autowired
	CountDao countDao;

	FilterParameters filter;
	String one = "1";
	String five = "5";
	String twelve = "12";

	@Before
	public void setUp() throws Exception {
		filter = new FilterParameters();
	}

	@Test
	public void adjustMinCountNullTest() {
		FilterParameters resultFilter = countDao.adjustMinCount(null, null);
		assertNull(resultFilter);

		resultFilter = countDao.adjustMinCount(NameSpace.ACTIVITY, null);
		assertNotNull(resultFilter);
		assertEquals(one, resultFilter.getMinactivities());
	}

	@Test
	public void adjustMinCountNoChangeTest() {
		//Nothing changed with Station Namespace
		FilterParameters resultFilter = countDao.adjustMinCount(NameSpace.STATION, filter);
		assertEquals(filter, resultFilter);

		//Don't change existing minimum count values
		filter.setMinactivities(five);
		filter.setMinresults(twelve);
		resultFilter = countDao.adjustMinCount(NameSpace.ACTIVITY, filter);
		assertEquals(five, resultFilter.getMinactivities());
		assertEquals(twelve, resultFilter.getMinresults());

		resultFilter = countDao.adjustMinCount(NameSpace.BIOLOGICAL_RESULT, filter);
		assertEquals(five, resultFilter.getMinactivities());
		assertEquals(twelve, resultFilter.getMinresults());
	}

	@Test
	public void adjustMinCountActivityTest() {
		//Add the minimum activity count
		FilterParameters resultFilter = countDao.adjustMinCount(NameSpace.ACTIVITY, filter);
		assertEquals(one, resultFilter.getMinactivities());
		assertTrue(StringUtils.isBlank(resultFilter.getMinresults()));

		//Don't change existing minimum count values
		filter.setMinactivities(five);
		filter.setMinresults(twelve);
		resultFilter = countDao.adjustMinCount(NameSpace.ACTIVITY, filter);
		assertEquals(five, resultFilter.getMinactivities());
		assertEquals(twelve, resultFilter.getMinresults());
	}

	@Test
	public void adjustMinCountBiologicalTest() {
		//Add the minimum result count - Biological
		FilterParameters resultFilter = countDao.adjustMinCount(NameSpace.BIOLOGICAL_RESULT, filter);
		assertTrue(StringUtils.isBlank(resultFilter.getMinactivities()));
		assertEquals(one, resultFilter.getMinresults());

		//Don't change existing minimum count values
		filter.setMinactivities(five);
		filter.setMinresults(twelve);
		resultFilter = countDao.adjustMinCount(NameSpace.ACTIVITY, filter);
		assertEquals(five, resultFilter.getMinactivities());
		assertEquals(twelve, resultFilter.getMinresults());
	}

	@Test
	public void adjustMinCountPCTest() {
		//Add the minimum result count - Result
		FilterParameters resultFilter = countDao.adjustMinCount(NameSpace.RESULT, filter);
		assertTrue(StringUtils.isBlank(resultFilter.getMinactivities()));
		assertEquals(one, resultFilter.getMinresults());

		//Don't change existing minimum count values
		filter.setMinactivities(five);
		filter.setMinresults(twelve);
		resultFilter = countDao.adjustMinCount(NameSpace.ACTIVITY, filter);
		assertEquals(five, resultFilter.getMinactivities());
		assertEquals(twelve, resultFilter.getMinresults());
	}

}
