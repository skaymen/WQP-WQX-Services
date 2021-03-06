package gov.usgs.cida.wqp.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import gov.usgs.cida.wqp.util.HttpConstants;

public class OrganizationCountJson {

	public static final String HEADER_NWIS_ORGANIZATION_COUNT = StationCountJson.NWIS + HttpConstants.HEADER_DELIMITER + HttpConstants.HEADER_ORGANIZATION_COUNT;
	public static final String HEADER_STEWARDS_ORGANIZATION_COUNT = StationCountJson.STEWARDS + HttpConstants.HEADER_DELIMITER + HttpConstants.HEADER_ORGANIZATION_COUNT;
	public static final String HEADER_STORET_ORGANIZATION_COUNT = StationCountJson.STORET + HttpConstants.HEADER_DELIMITER + HttpConstants.HEADER_ORGANIZATION_COUNT;
	public static final String HEADER_BIODATA_ORGANIZATION_COUNT = StationCountJson.BIODATA + HttpConstants.HEADER_DELIMITER + HttpConstants.HEADER_ORGANIZATION_COUNT;

	@JsonProperty(HttpConstants.HEADER_TOTAL_ORGANIZATION_COUNT)
	String totalProjectCount;
	@JsonProperty(HEADER_NWIS_ORGANIZATION_COUNT)
	String nwisProjectCount;
	@JsonProperty(HEADER_STEWARDS_ORGANIZATION_COUNT)
	String stewardsProjectCount;
	@JsonProperty(HEADER_STORET_ORGANIZATION_COUNT)
	String storetProjectCount;
	@JsonProperty(HEADER_BIODATA_ORGANIZATION_COUNT)
	String biodataProjectCount;

}
