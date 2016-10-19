package gov.usgs.cida.wqp.mapping.delimited;

import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.*;

public class ActivityDelimited extends BaseDelimited {

	//Column Headings for the Keys
	public static final String VALUE_ACTIVITY = WQX_ACT_ID;
	public static final String VALUE_ACTIVITY_TYPE_CODE = WQX_ACT_TYPE;
	public static final String VALUE_SAMPLE_MEDIA = WQX_ACT_MEDIA;
	public static final String VALUE_ACTIVITY_MEDIA_SUBDIV_NAME = WQX_ACT_MEDIA_SUB;
	public static final String VALUE_EVENT_DATE = WQX_ACT_START_DATE;
	public static final String VALUE_ACTIVITY_START_TIME = WQX_ACT_START_TIME + VAL_DEL + WQX_TIME;
	public static final String VALUE_ACT_START_TIME_ZONE = WQX_ACT_START_TIME + VAL_DEL + WQX_TIME_ZONE;
	public static final String VALUE_ACTIVITY_STOP_DATE = WQX_ACT_END_DATE;
	public static final String VALUE_ACTIVITY_STOP_TIME = WQX_ACT_END_TIME + VAL_DEL + WQX_TIME;
	public static final String VALUE_ACT_STOP_TIME_ZONE = WQX_ACT_END_TIME + VAL_DEL + WQX_TIME_ZONE;
	public static final String VALUE_ACTIVITY_RELATIVE_DEPTH = WQX_ACT_RELATIVE_DEPTH;
	public static final String VALUE_ACTIVITY_DEPTH = WQX_ACT_DEPTH + VAL_DEL + WQX_MEASURE_VALUE;
	public static final String VALUE_ACTIVITY_DEPTH_UNIT = WQX_ACT_DEPTH + VAL_DEL + WQX_MEASURE_UNIT;
	public static final String VALUE_ACTIVITY_DEPTH_REF_POINT = WQX_ACT_DEPTH_REFERENCE;
	public static final String VALUE_ACTIVITY_UPPER_DEPTH = WQX_ACT_TOP_DEPTH + VAL_DEL + WQX_MEASURE_VALUE;
	public static final String VALUE_ACTIVITY_UPPER_DEPTH_UNIT = WQX_ACT_TOP_DEPTH + VAL_DEL + WQX_MEASURE_UNIT;
	public static final String VALUE_ACTIVITY_LOWER_DEPTH = WQX_ACT_BOTTOM_DEPTH + VAL_DEL + WQX_MEASURE_VALUE;
	public static final String VALUE_ACTIVITY_LOWER_DEPTH_UNIT = WQX_ACT_BOTTOM_DEPTH + VAL_DEL + WQX_MEASURE_UNIT;
	public static final String VALUE_PROJECT_ID = WQX_PROJECT;
	public static final String VALUE_ACTIVITY_CONDUCTING_ORG = WQX_ACT_CONDUCTION_ORG;
	public static final String VALUE_ACTIVITY_COMMENT = WQX_ACT_COMMENT;
	public static final String VALUE_SAMPLE_AQFR_NAME = WQX_SAMPLE_AQUIFER;
	public static final String VALUE_HYDROLOGIC_CONDITION_NAME = WQX_HYDROLOGIC_CONDITION;
	public static final String VALUE_HYDROLOGIC_EVENT_NAME = WQX_HYDROLOGIC_EVENT;
	public static final String VALUE_ACTIVITY_LATITUDE = WQX_ACT_LOCATION + VAL_DEL + WQX_LATITUDE_MEASURE;
	public static final String VALUE_ACTIVITY_LONGITUDE = WQX_ACT_LOCATION + VAL_DEL + WQX_LONGITUDE_MEASURE;
	public static final String VALUE_ACTIVITY_SOURCE_MAP_SCALE = WQX_ACT_LOCATION + VAL_DEL + WQX_SOURCE_MAP_SCALE;
	public static final String VALUE_ACT_HORIZONTAL_ACCURACY = WQX_ACT_LOCATION + VAL_DEL + WQX_HORIZONTAL_ACCY + VAL_DEL + WQX_MEASURE_VALUE;
	public static final String VALUE_ACT_HORIZONTAL_ACCURACY_UNIT = WQX_ACT_LOCATION + VAL_DEL + WQX_HORIZONTAL_ACCY + VAL_DEL + WQX_MEASURE_UNIT;
	public static final String VALUE_ACT_HORIZONTAL_COLLECT_METHOD = WQX_ACT_LOCATION + VAL_DEL + WQX_HORIZONTAL_COLLECTION_METHOD;
	public static final String VALUE_ACT_HORIZONTAL_DATUM_NAME = WQX_ACT_LOCATION + VAL_DEL + WQX_HORIZONTAL_DATUM;
	public static final String VALUE_ASSEMBLAGE_SAMPLED_NAME = WQX_ASSEMBLAGE_SAMPLED;
	public static final String VALUE_ACT_COLLECTION_DURATION = WQX_COLLECTION_DURATION + VAL_DEL + WQX_MEASURE_VALUE;
	public static final String VALUE_ACT_COLLECTION_DURATION_UNIT = WQX_COLLECTION_DURATION + VAL_DEL + WQX_MEASURE_UNIT;
	public static final String VALUE_ACT_SAM_COMPNT_NAME = WQX_SAMPLING_COMPONENT;
	public static final String VALUE_ACT_SAM_COMPNT_PLACE_IN_SERIES = WQX_SAMPLING_COMPONENT_PLACE;
	public static final String VALUE_ACT_REACH_LENGTH = WQX_REACH_LENGTH + VAL_DEL + WQX_MEASURE_VALUE;
	public static final String VALUE_ACT_REACH_LENGTH_UNIT = WQX_REACH_LENGTH + VAL_DEL + WQX_MEASURE_UNIT;
	public static final String VALUE_ACT_REACH_WIDTH = WQX_REACH_WIDTH + VAL_DEL + WQX_MEASURE_VALUE;
	public static final String VALUE_ACT_REACH_WIDTH_UNIT = WQX_REACH_WIDTH + VAL_DEL + WQX_MEASURE_UNIT;
	public static final String VALUE_ACT_PASS_COUNT = WQX_PASS_COUNT;
	public static final String VALUE_NET_TYPE_NAME = WQX_NET_TYPE;
	public static final String VALUE_ACT_NET_SURFACE_AREA = WQX_NET_SURFACE_AREA + VAL_DEL + WQX_MEASURE_VALUE;
	public static final String VALUE_ACT_NET_SURFACE_AREA_UNIT = WQX_NET_SURFACE_AREA + VAL_DEL + WQX_MEASURE_UNIT;
	public static final String VALUE_ACT_NET_MESH_SIZE = WQX_NET_MESH_SIZE + VAL_DEL + WQX_MEASURE_VALUE;
	public static final String VALUE_ACT_NET_MESH_SIZE_UNIT = WQX_NET_MESH_SIZE + VAL_DEL + WQX_MEASURE_UNIT;
	public static final String VALUE_ACT_BOAT_SPEED = WQX_BOAT_SPEAD + VAL_DEL + WQX_MEASURE_VALUE;
	public static final String VALUE_ACT_BOAT_SPEED_UNIT = WQX_BOAT_SPEAD + VAL_DEL + WQX_MEASURE_UNIT;
	public static final String VALUE_ACT_CURRENT_SPEED = WQX_CURRENT_SPEAD + VAL_DEL + WQX_MEASURE_VALUE;
	public static final String VALUE_ACT_CURRENT_SPEED_UNIT = WQX_CURRENT_SPEAD + VAL_DEL + WQX_MEASURE_UNIT;
	public static final String VALUE_TOXICITY_TEST_TYPE_NAME = WQX_TOXICITY_TEST_TYPE;


	public static final String VALUE_SAMPLE_COLLECT_METHOD_ID = WQX_COLLECTION_METHOD + VAL_DEL + WQX_METHOD_ID;
	public static final String VALUE_SAMPLE_COLLECT_METHOD_CTX = WQX_COLLECTION_METHOD + VAL_DEL + WQX_METHOD_CONTEXT;
	public static final String VALUE_SAMPLE_COLLECT_METHOD_NAME = WQX_COLLECTION_METHOD + VAL_DEL + WQX_METHOD_NAME;


	public static final String VALUE_ACT_SAM_COLLECT_METH_QUAL_TYPE = WQX_COLLECTION_METHOD + VAL_DEL + WQX_METHOD_QUALIFIER_TYPE;
	public static final String VALUE_ACT_SAM_COLLECT_METH_DESC = WQX_COLLECTION_METHOD + VAL_DEL + WQX_METHOD_DESCRIPTION;


	public static final String VALUE_SAMPLE_COLLECT_EQUIP_NAME = WQX_COLLECTION_EQUIPMENT;


	public static final String VALUE_ACT_SAM_COLLECT_EQUIP_COMMENTS = WQX_COLLECTION_METHOD + VAL_DEL + WQX_COLLECTION_EQUIPMENT_COMMENT;
	public static final String VALUE_ACT_SAM_PREP_METH_ID = WQX_SAMPLE_PREPARATION_METHOD + VAL_DEL + WQX_METHOD_ID;
	public static final String VALUE_ACT_SAM_PREP_METH_CONTEXT = WQX_SAMPLE_PREPARATION_METHOD + VAL_DEL + WQX_METHOD_CONTEXT;
	public static final String VALUE_ACT_SAM_PREP_METH_NAME = WQX_SAMPLE_PREPARATION_METHOD + VAL_DEL + WQX_METHOD_NAME;
	public static final String VALUE_ACT_SAM_PREP_METH_QUAL_TYPE = WQX_SAMPLE_PREPARATION_METHOD + VAL_DEL + WQX_METHOD_QUALIFIER_TYPE;
	public static final String VALUE_ACT_SAM_PREP_METH_DESC = WQX_SAMPLE_PREPARATION_METHOD + VAL_DEL + WQX_METHOD_DESCRIPTION;
	public static final String VALUE_SAMPLE_CONTAINER_TYPE = WQX_SAMPLE_CONTAINER_TYPE;
	public static final String VALUE_SAMPLE_CONTAINER_COLOR = WQX_SAMPLE_CONTAINER_COLOR;
	public static final String VALUE_ACT_SAM_CHEMICAL_PRESERVATIVE = WQX_CHEMICAL_PRESERVATIVE;
	public static final String VALUE_THERMAL_PRESERVATIVE_NAME = WQX_THERMAL_PRESERVATIVE;
	public static final String VALUE_ACT_SAM_TRANSPORT_STORAGE_DESC = WQX_TRANSPORT_STORAGE;

	private ActivityDelimited() {
	}
}