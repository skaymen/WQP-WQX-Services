package gov.usgs.cida.wqp.webservice;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.anyObject;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import gov.usgs.cida.wqp.BaseTest;
import gov.usgs.cida.wqp.dao.BlobDao;
import gov.usgs.cida.wqp.parameter.ResultIdentifier;
import gov.usgs.cida.wqp.service.ILogService;
import gov.usgs.cida.wqp.service.LogServiceTest;
import gov.usgs.cida.wqp.util.HttpConstants;

public class BlobControllerTest extends BaseTest {

	@Mock
	BlobDao blobDao;
	@Mock
	ILogService logService;
	BlobController controller;
	BigDecimal FIFTY_FIVE = BigDecimal.valueOf(55);
	MockHttpServletRequest request;
	MockHttpServletResponse response;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		controller = new BlobController(blobDao, logService);
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		when(logService.logRequest(request, response)).thenReturn(FIFTY_FIVE);
	}

	@Test
	public void setupResponseTest() throws IOException {
		controller.setupResponse(request, response, BlobController.PROJECT_FILE);
		assertTrue(response.containsHeader(HttpConstants.HEADER_CONTENT_DISPOSITION));
		assertEquals("attachment; filename=" + BlobController.PROJECT_FILE, response.getHeader(HttpConstants.HEADER_CONTENT_DISPOSITION));
		assertEquals(FIFTY_FIVE, BlobController.getLogId());
		assertEquals(response.getOutputStream(), BlobController.getOutputStream());
		assertNotNull(BlobController.getZipOutputStream());
		verify(logService).logRequest(request, response);
	}

	@Test
	@SuppressWarnings("unchecked")
	public void finishResponseTest() throws IOException {
		BlobController.setLogId(FIFTY_FIVE);
		BlobController.setOutputStream(response.getOutputStream());
		ZipOutputStream zip = new ZipOutputStream(response.getOutputStream());
		zip.putNextEntry(new ZipEntry("what"));
		zip.write("something".getBytes());
		BlobController.setZipOutputStream(zip);
		controller.finishResponse(response, "test", LogServiceTest.getDownloadDetails());
		verify(logService).logRequestComplete(anyObject(), anyObject(), anyMap());
		assertNull(BlobController.getLogId());
		assertNull(BlobController.getOutputStream());
		assertNull(BlobController.getZipOutputStream());
		assertEquals("something", extractZipContent(response.getContentAsByteArray(), "what"));
	}

	@Test
	@SuppressWarnings("unchecked")
	public void projectTest() throws IOException {
		controller.projectBlobFilesGetRestRequest(request, response, "organization", "projectIdentifier");
		assertTrue(response.containsHeader(HttpConstants.HEADER_CONTENT_DISPOSITION));
		assertEquals("attachment; filename=" + BlobController.PROJECT_FILE, response.getHeader(HttpConstants.HEADER_CONTENT_DISPOSITION));
		assertEquals(0, response.getContentLength());
		verify(logService).logRequest(request, response);
		verify(logService).logRequestComplete(anyObject(), anyObject(), anyMap());
		verify(blobDao).getProjectFiles(any(ZipOutputStream.class), anyString(), anyString());
	}

	@Test
	@SuppressWarnings("unchecked")
	public void stationTest() throws IOException {
		controller.monitoringLocationBlobFilesGetRestRequest(request, response, "organization", "projectIdentifier");
		assertTrue(response.containsHeader(HttpConstants.HEADER_CONTENT_DISPOSITION));
		assertEquals("attachment; filename=" + BlobController.MONITORING_LOCATION_FILE, response.getHeader(HttpConstants.HEADER_CONTENT_DISPOSITION));
		assertEquals(0, response.getContentLength());
		verify(logService).logRequest(request, response);
		verify(logService).logRequestComplete(anyObject(), anyObject(), anyMap());
		verify(blobDao).getMonitoringLocationFiles(any(ZipOutputStream.class), anyString(), anyString());
	}

	@Test
	@SuppressWarnings("unchecked")
	public void resultTest() throws IOException {
		controller.resultBlobFilesGetRestRequest(request, response, "organization", "activity", "result");
		assertTrue(response.containsHeader(HttpConstants.HEADER_CONTENT_DISPOSITION));
		assertEquals("attachment; filename=" + BlobController.RESULT_FILE, response.getHeader(HttpConstants.HEADER_CONTENT_DISPOSITION));
		assertEquals(0, response.getContentLength());
		verify(logService).logRequest(request, response);
		verify(logService).logRequestComplete(anyObject(), anyObject(), anyMap());
		verify(blobDao).getResultFiles(any(ZipOutputStream.class), anyString(), anyString(), any(ResultIdentifier.class));
	}

}
