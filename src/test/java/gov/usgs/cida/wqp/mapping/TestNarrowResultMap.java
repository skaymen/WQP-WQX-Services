package gov.usgs.cida.wqp.mapping;

import static gov.usgs.cida.wqp.mapping.ResultColumn.KEY_ANALYSIS_END_DATE;
import static gov.usgs.cida.wqp.mapping.ResultColumn.KEY_ANALYSIS_END_TIME;
import static gov.usgs.cida.wqp.mapping.ResultColumn.KEY_ANALYSIS_END_TIMEZONE;
import static gov.usgs.cida.wqp.mapping.ResultColumn.KEY_ANALYSIS_START_DATE;
import static gov.usgs.cida.wqp.mapping.ResultColumn.KEY_ANALYSIS_START_TIME;
import static gov.usgs.cida.wqp.mapping.ResultColumn.KEY_ANALYSIS_START_TIMEZONE;
import static gov.usgs.cida.wqp.mapping.ResultColumn.KEY_ANALYTICAL_METHOD;
import static gov.usgs.cida.wqp.mapping.ResultColumn.KEY_ANALYTICAL_METHOD_CITATION;
import static gov.usgs.cida.wqp.mapping.ResultColumn.KEY_ANALYTICAL_METHOD_NAME;
import static gov.usgs.cida.wqp.mapping.ResultColumn.KEY_ANALYTICAL_PROCEDURE_ID;
import static gov.usgs.cida.wqp.mapping.ResultColumn.KEY_ANALYTICAL_PROCEDURE_SOURCE;
import static gov.usgs.cida.wqp.mapping.ResultColumn.KEY_ANLMTH_QUAL_TYPE;
import static gov.usgs.cida.wqp.mapping.ResultColumn.KEY_BIOLOGICAL_INTENT;
import static gov.usgs.cida.wqp.mapping.ResultColumn.KEY_CELL_FORM_NAME;
import static gov.usgs.cida.wqp.mapping.ResultColumn.KEY_CELL_SHAPE_NAME;
import static gov.usgs.cida.wqp.mapping.ResultColumn.KEY_CHARACTERISTIC_NAME;
import static gov.usgs.cida.wqp.mapping.ResultColumn.KEY_CHARACTERISTIC_TYPE;
import static gov.usgs.cida.wqp.mapping.ResultColumn.KEY_DURATION_BASIS;
import static gov.usgs.cida.wqp.mapping.ResultColumn.KEY_FCDSC_URL;
import static gov.usgs.cida.wqp.mapping.ResultColumn.KEY_HABIT_NAME;
import static gov.usgs.cida.wqp.mapping.ResultColumn.KEY_LAB_NAME;
import static gov.usgs.cida.wqp.mapping.ResultColumn.KEY_LAB_REMARK;
import static gov.usgs.cida.wqp.mapping.ResultColumn.KEY_METHOD_SPECIFICATION_NAME;
import static gov.usgs.cida.wqp.mapping.ResultColumn.KEY_PARTICLE_SIZE;
import static gov.usgs.cida.wqp.mapping.ResultColumn.KEY_PRECISION;
import static gov.usgs.cida.wqp.mapping.ResultColumn.KEY_PREP_METHOD_URL;
import static gov.usgs.cida.wqp.mapping.ResultColumn.KEY_P_CODE;
import static gov.usgs.cida.wqp.mapping.ResultColumn.KEY_RESULT_COMMENT;
import static gov.usgs.cida.wqp.mapping.ResultColumn.KEY_RESULT_DEPTH_ALT_REF_PT_TXT;
import static gov.usgs.cida.wqp.mapping.ResultColumn.KEY_RESULT_DEPTH_MEAS_UNIT_CODE;
import static gov.usgs.cida.wqp.mapping.ResultColumn.KEY_RESULT_DEPTH_MEAS_VALUE;
import static gov.usgs.cida.wqp.mapping.ResultColumn.KEY_RESULT_DETECTION_CONDITION_TX;
import static gov.usgs.cida.wqp.mapping.ResultColumn.KEY_EXTERNAL_RESULT_ID;
import static gov.usgs.cida.wqp.mapping.ResultColumn.KEY_RESULT_ID;
import static gov.usgs.cida.wqp.mapping.ResultColumn.KEY_RESULT_MEASURE_VALUE;
import static gov.usgs.cida.wqp.mapping.ResultColumn.KEY_RESULT_MEAS_QUAL_CODE;
import static gov.usgs.cida.wqp.mapping.ResultColumn.KEY_RESULT_UNIT;
import static gov.usgs.cida.wqp.mapping.ResultColumn.KEY_RESULT_VALUE_STATUS;
import static gov.usgs.cida.wqp.mapping.ResultColumn.KEY_RESULT_VALUE_TYPE;
import static gov.usgs.cida.wqp.mapping.ResultColumn.KEY_RES_BIO_INDIVIDUAL_ID;
import static gov.usgs.cida.wqp.mapping.ResultColumn.KEY_RES_DATA_LOGGER_LINE;
import static gov.usgs.cida.wqp.mapping.ResultColumn.KEY_RES_DETECT_QNT_LMT_URL;
import static gov.usgs.cida.wqp.mapping.ResultColumn.KEY_RES_GROUP_SUMMARY_CT_WT;
import static gov.usgs.cida.wqp.mapping.ResultColumn.KEY_RES_GROUP_SUMMARY_CT_WT_UNIT;
import static gov.usgs.cida.wqp.mapping.ResultColumn.KEY_RES_LAB_ACCRED_AUTHORITY;
import static gov.usgs.cida.wqp.mapping.ResultColumn.KEY_RES_LAB_ACCRED_YN;
import static gov.usgs.cida.wqp.mapping.ResultColumn.KEY_RES_MEASURE_BIAS;
import static gov.usgs.cida.wqp.mapping.ResultColumn.KEY_RES_MEASURE_CONF_INTERVAL;
import static gov.usgs.cida.wqp.mapping.ResultColumn.KEY_RES_MEASURE_LOWER_CONF_LIMIT;
import static gov.usgs.cida.wqp.mapping.ResultColumn.KEY_RES_MEASURE_UPPER_CONF_LIMIT;
import static gov.usgs.cida.wqp.mapping.ResultColumn.KEY_RES_SAMPLING_POINT_NAME;
import static gov.usgs.cida.wqp.mapping.ResultColumn.KEY_RES_TAXONOMIST_ACCRED_AUTHORTY;
import static gov.usgs.cida.wqp.mapping.ResultColumn.KEY_RES_TAXONOMIST_ACCRED_YN;
import static gov.usgs.cida.wqp.mapping.ResultColumn.KEY_RLCOM_CD;
import static gov.usgs.cida.wqp.mapping.ResultColumn.KEY_RTDET_POLLUTION_TOLERANCE;
import static gov.usgs.cida.wqp.mapping.ResultColumn.KEY_RTDET_POLLUTION_TOLERNCE_SCALE;
import static gov.usgs.cida.wqp.mapping.ResultColumn.KEY_RTDET_TROPHIC_LEVEL;
import static gov.usgs.cida.wqp.mapping.ResultColumn.KEY_RTFGRP_FUNCTIONAL_FEEDING_GRP;
import static gov.usgs.cida.wqp.mapping.ResultColumn.KEY_SAMPLE_FRACTION_TYPE;
import static gov.usgs.cida.wqp.mapping.ResultColumn.KEY_SAMPLE_TISSUE_ANATOMY_NAME;
import static gov.usgs.cida.wqp.mapping.ResultColumn.KEY_SAMPLE_TISSUE_TAXONOMIC_NAME;
import static gov.usgs.cida.wqp.mapping.ResultColumn.KEY_STATISTIC_TYPE;
import static gov.usgs.cida.wqp.mapping.ResultColumn.KEY_TAXON_CITATION_CREATOR;
import static gov.usgs.cida.wqp.mapping.ResultColumn.KEY_TAXON_CITATION_DATE;
import static gov.usgs.cida.wqp.mapping.ResultColumn.KEY_TAXON_CITATION_ID;
import static gov.usgs.cida.wqp.mapping.ResultColumn.KEY_TAXON_CITATION_PUBLISHER;
import static gov.usgs.cida.wqp.mapping.ResultColumn.KEY_TAXON_CITATION_SUBJECT;
import static gov.usgs.cida.wqp.mapping.ResultColumn.KEY_TAXON_CITATION_TITLE;
import static gov.usgs.cida.wqp.mapping.ResultColumn.KEY_TEMPERATURE_BASIS_LEVEL;
import static gov.usgs.cida.wqp.mapping.ResultColumn.KEY_UNIDENTIFIEDSPECIESIDENTIFIER;
import static gov.usgs.cida.wqp.mapping.ResultColumn.KEY_VOLT_NAME;
import static gov.usgs.cida.wqp.mapping.ResultColumn.KEY_WEIGHT_BASIS_TYPE;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

public class TestNarrowResultMap {

	public static final Map<String, Object> BASE_NARROW;
	static {
		BASE_NARROW = new LinkedHashMap<String, Object>();
		BASE_NARROW.put(KEY_RESULT_ID, BigDecimal.valueOf(42));
		BASE_NARROW.put(KEY_EXTERNAL_RESULT_ID, "STORET-42");
		BASE_NARROW.put(KEY_RES_DATA_LOGGER_LINE, "resDataLoggerLine");
		BASE_NARROW.put(KEY_RESULT_DETECTION_CONDITION_TX, "resultDetectionConditionTx");
		BASE_NARROW.put(KEY_METHOD_SPECIFICATION_NAME, "methodSpecificationName");
		BASE_NARROW.put(KEY_CHARACTERISTIC_NAME, "characteristicName");
		BASE_NARROW.put(KEY_CHARACTERISTIC_TYPE, "characteristicType");
		BASE_NARROW.put(KEY_SAMPLE_FRACTION_TYPE, "sampleFractionType");
		BASE_NARROW.put(KEY_RESULT_MEASURE_VALUE, "resultMeasureValue");
		BASE_NARROW.put(KEY_RESULT_UNIT, "resultUnit");
		BASE_NARROW.put(KEY_RESULT_MEAS_QUAL_CODE, "resultMeasQualCode");
		BASE_NARROW.put(KEY_RESULT_VALUE_STATUS, "resultValueStatus");
		BASE_NARROW.put(KEY_STATISTIC_TYPE, "statisticType");
		BASE_NARROW.put(KEY_RESULT_VALUE_TYPE, "resultValueType");
		BASE_NARROW.put(KEY_WEIGHT_BASIS_TYPE, "weightBasisType");
		BASE_NARROW.put(KEY_DURATION_BASIS, "durationBasis");
		BASE_NARROW.put(KEY_TEMPERATURE_BASIS_LEVEL, "temperatureBasisLevel");
		BASE_NARROW.put(KEY_PARTICLE_SIZE, "particleSize");
		BASE_NARROW.put(KEY_PRECISION, "precision");
		BASE_NARROW.put(KEY_RES_MEASURE_BIAS, "resMeasureBias");
		BASE_NARROW.put(KEY_RES_MEASURE_CONF_INTERVAL, "resMeasureConfInterval");
		BASE_NARROW.put(KEY_RES_MEASURE_UPPER_CONF_LIMIT, "resMeasureUpperConfLimit");
		BASE_NARROW.put(KEY_RES_MEASURE_LOWER_CONF_LIMIT, "resMeasureLowerConfLimit");
		BASE_NARROW.put(KEY_RESULT_COMMENT, "resultComment");
		BASE_NARROW.put(KEY_P_CODE, "99999");
		BASE_NARROW.put(KEY_RESULT_DEPTH_MEAS_VALUE, "resultDepthMeasValue");
		BASE_NARROW.put(KEY_RESULT_DEPTH_MEAS_UNIT_CODE, "resultDepthMeasUnitCode");
		BASE_NARROW.put(KEY_RESULT_DEPTH_ALT_REF_PT_TXT, "resultDepthAltRefPtTxt");
		BASE_NARROW.put(KEY_RES_SAMPLING_POINT_NAME, "resSamplingPointName");
		BASE_NARROW.put(KEY_BIOLOGICAL_INTENT, "biologicalIntent");
		BASE_NARROW.put(KEY_RES_BIO_INDIVIDUAL_ID, "resBioIndividualId");
		BASE_NARROW.put(KEY_SAMPLE_TISSUE_TAXONOMIC_NAME, "sampleTissueTaxonomicName");
		BASE_NARROW.put(KEY_UNIDENTIFIEDSPECIESIDENTIFIER, "unidentifiedSpeciesIdentifier");
		BASE_NARROW.put(KEY_SAMPLE_TISSUE_ANATOMY_NAME, "sampleTissueAnatomyName");
		BASE_NARROW.put(KEY_RES_GROUP_SUMMARY_CT_WT, "resGroupSummaryCtWt");
		BASE_NARROW.put(KEY_RES_GROUP_SUMMARY_CT_WT_UNIT, "resGroupSummaryCtWtUnit");
		BASE_NARROW.put(KEY_CELL_FORM_NAME, "cellFormName");
		BASE_NARROW.put(KEY_CELL_SHAPE_NAME, "cellShapeName");
		BASE_NARROW.put(KEY_HABIT_NAME, "habitName");
		BASE_NARROW.put(KEY_VOLT_NAME, "voltName");
		BASE_NARROW.put(KEY_RTDET_POLLUTION_TOLERANCE, "rtdetPollutionTolerance");
		BASE_NARROW.put(KEY_RTDET_POLLUTION_TOLERNCE_SCALE, "rtdetPollutionTolernceScale");
		BASE_NARROW.put(KEY_RTDET_TROPHIC_LEVEL, "rtdetTrophicLevel");
		BASE_NARROW.put(KEY_RTFGRP_FUNCTIONAL_FEEDING_GRP, "rtfgrpFunctionalFeedingGrp");
		BASE_NARROW.put(KEY_TAXON_CITATION_TITLE, "taxonCitationTitle");
		BASE_NARROW.put(KEY_TAXON_CITATION_CREATOR, "taxonCitationCreator");
		BASE_NARROW.put(KEY_TAXON_CITATION_SUBJECT, "taxonCitationSubject");
		BASE_NARROW.put(KEY_TAXON_CITATION_PUBLISHER, "taxonCitationPublisher");
		BASE_NARROW.put(KEY_TAXON_CITATION_DATE, "taxonCitationDate");
		BASE_NARROW.put(KEY_TAXON_CITATION_ID, "taxonCitationId");
		BASE_NARROW.put(KEY_ANALYTICAL_PROCEDURE_ID, "analyticalProcedureId");
		BASE_NARROW.put(KEY_ANALYTICAL_PROCEDURE_SOURCE, "analyticalProcedureSource");
		BASE_NARROW.put(KEY_ANALYTICAL_METHOD_NAME, "analyticalMethodName");
		BASE_NARROW.put(KEY_ANLMTH_QUAL_TYPE, "anlmthQualType");
		BASE_NARROW.put(KEY_ANALYTICAL_METHOD_CITATION, "analyticalMethodCitation");
		BASE_NARROW.put(KEY_LAB_NAME, "labName");
		BASE_NARROW.put(KEY_ANALYSIS_START_DATE, "analysisStartDate");
		BASE_NARROW.put(KEY_ANALYSIS_START_TIME, "analysisStartTime");
		BASE_NARROW.put(KEY_ANALYSIS_START_TIMEZONE, "analysisStartTimezone");
		BASE_NARROW.put(KEY_ANALYSIS_END_DATE, "analysisEndDate");
		BASE_NARROW.put(KEY_ANALYSIS_END_TIME, "analysisEndTime");
		BASE_NARROW.put(KEY_ANALYSIS_END_TIMEZONE, "analysisEndTimezone");
		BASE_NARROW.put(KEY_RLCOM_CD, "rlcomCd");
		BASE_NARROW.put(KEY_LAB_REMARK, "labRemark");
		BASE_NARROW.put(KEY_RES_LAB_ACCRED_YN, "resLabAccredYN");
		BASE_NARROW.put(KEY_RES_LAB_ACCRED_AUTHORITY, "resLabAccredAuthority");
		BASE_NARROW.put(KEY_RES_TAXONOMIST_ACCRED_YN, "resTaxonomistAccredYN");
		BASE_NARROW.put(KEY_RES_TAXONOMIST_ACCRED_AUTHORTY, "resTaxonomistAccredAuthorty");
	}

	public static final Map<String, Object> NARROW;
	static {
		NARROW = new LinkedHashMap<String, Object>();
		NARROW.putAll(TestActivityMap.BASE_ACTIVITY);
		NARROW.putAll(BASE_NARROW);
		NARROW.put(KEY_ANALYTICAL_METHOD, "https://analyticalMethod");
		NARROW.put(KEY_FCDSC_URL, null);
		NARROW.put(KEY_RES_DETECT_QNT_LMT_URL, "/activities/activity/results/STORET-42/resdetectqntlmts");
		NARROW.put(KEY_PREP_METHOD_URL, null);
	}
	private TestNarrowResultMap() {
	}
}
