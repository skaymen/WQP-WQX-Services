package gov.usgs.cida.wqp.validation;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import gov.usgs.cida.wqp.BaseSpringTest;
import gov.usgs.cida.wqp.parameter.HashMapParameterHandler;
import gov.usgs.cida.wqp.parameter.Parameters;
import gov.usgs.cida.wqp.parameter.transform.SplitTransformer;
import gov.usgs.cida.wqp.springinit.ParameterValidationConfig;

public class RegexValidatorTest extends BaseSpringTest {
	@Test
	public void testConstructors_nullParameter() {
		try {
			new RegexValidator<Object>(null, null);
			fail("Should have gotten an IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			assertEquals("The Parameter being validated must be provided.", e.getMessage());
		}
	}
	@Test
	public void testConstructors_nullPattern() {
		try {
			new RegexValidator<Object>(Parameters.ANALYTICAL_METHOD, null);
			fail("Should have gotten an IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			assertEquals("The REGEX must be provided.", e.getMessage());
		}
	}
	@Test
	public void testConstructors_defaults() {
		RegexValidator<?> validator = new RegexValidator<Object>(Parameters.ANALYTICAL_METHOD, "abc");
		assertEquals(AbstractValidator.DEFAULT_MIN_OCCURS, validator.getMinOccurs());
		assertEquals(AbstractValidator.IN_CLAUSE_LIMIT, validator.getMaxOccurs());
		assertEquals(AbstractValidator.DEFAULT_DELIMITER, validator.getDelimiter());
		assertEquals("(?:(?:^|(?<!^);)(?:abc)(?=(?<!;)$|;(?!$))){0,1000}", validator.getValidatePattern());
	}
	@Test
	public void testConstructors_customValues() {
		RegexValidator<?> validator = new RegexValidator<Object>(Parameters.ANALYTICAL_METHOD, 5, 12, "!", "[0-9]+|abc");
		assertEquals(5, validator.getMinOccurs());
		assertEquals(12, validator.getMaxOccurs());
		assertEquals("!", validator.getDelimiter());
		assertEquals("(?:(?:^|(?<!^)!)(?:[0-9]+|abc)(?=(?<!!)$|!(?!$))){5,12}", validator.getValidatePattern());
	}
	@Test
	public void testConstructors_nullDelimiter() {
		RegexValidator<?> validator = new RegexValidator<Object>(Parameters.ANALYTICAL_METHOD, 1, 1, null, "[0-9]+|abc");
		assertEquals(1, validator.getMinOccurs());
		assertEquals(1, validator.getMaxOccurs());
		assertNull(validator.getDelimiter());
		assertEquals("^[0-9]+|abc$", validator.getValidatePattern());
	}
	@Test
	public void testValidate_SimpleLiteral_match() {
		AbstractValidator<String[]> validator = new RegexValidator<String[]>(Parameters.ANALYTICAL_METHOD, "abc");
		ValidationResult<String[]> vr = validator.validate("abc");
		assertTrue(vr.isValid());
		assertEquals(0, vr.getValidationMessages().size());
		assertArrayEquals(new String[]{"abc"}, vr.getTransformedValue());
	}
	@Test
	public void testValidate_SimpleLiteral_mismatch() {
		AbstractValidator<String[]> validator = new RegexValidator<String[]>(Parameters.ANALYTICAL_METHOD, "abc");
		ValidationResult<String[]> vr = validator.validate("def");
		assertFalse(vr.isValid());
		assertEquals(1, vr.getValidationMessages().size());
		assertEquals("The value of analyticalmethod=def must match the format abc", vr.getValidationMessages().get(0));
		assertArrayEquals(new String[]{"def"}, vr.getRawValue());
	}
	@Test
	public void testValidate_SimpleNumeric_match() {
		AbstractValidator<String[]> validator = new RegexValidator<String[]>(Parameters.ANALYTICAL_METHOD, "[0-9]+");
		ValidationResult<String[]> vr = validator.validate("123");
		assertTrue(vr.isValid());
		assertEquals(0, vr.getValidationMessages().size());
		assertArrayEquals(new String[]{"123"}, vr.getTransformedValue());
	}
	@Test
	public void testValidate_SimpleNumeric_mismatch() {
		AbstractValidator<String[]> validator = new RegexValidator<String[]>(Parameters.ANALYTICAL_METHOD, "[0-9]+");
		ValidationResult<String[]> vr = validator.validate("345.678");
		assertFalse(vr.isValid());
		assertEquals(1, vr.getValidationMessages().size());
		assertEquals("The value of analyticalmethod=345.678 must match the format [0-9]+", vr.getValidationMessages().get(0));
		assertArrayEquals(new String[]{"345.678"}, vr.getRawValue());
	}
	@Test
	public void testValidate_SucceedsOnEmptyString() {
		AbstractValidator<String[]> validator = new RegexValidator<String[]>(Parameters.ANALYTICAL_METHOD, "[0-9]+|abc");
		ValidationResult<String[]> vr = validator.validate("");
		assertTrue(vr.isValid());
		assertEquals(0, vr.getValidationMessages().size());
		assertArrayEquals(new String[0], vr.getTransformedValue());
	}
	@Test
	public void testValidate_SucceedsOnNull() {
		AbstractValidator<String[]> validator = new RegexValidator<String[]>(Parameters.ANALYTICAL_METHOD, 0, 1, null, "[0-9]+|abc");
		validator.setTransformer( new SplitTransformer(AbstractValidator.DEFAULT_DELIMITER));
		ValidationResult<String[]> vr = validator.validate(null);
		assertTrue(vr.isValid());
		assertEquals(0, vr.getValidationMessages().size());
		assertArrayEquals(new String[0], vr.getTransformedValue());
	}
	@Test
	public void testValidate_MinOccursOnce_emptyStringValue() {
		AbstractValidator<String[]> validator = new RegexValidator<String[]>(Parameters.ANALYTICAL_METHOD, 1, AbstractValidator.DEFAULT_MAX_OCCURS, AbstractValidator.DEFAULT_DELIMITER, "[0-9]+|abc");
		ValidationResult<String[]> vr = validator.validate("");
		assertFalse(vr.isValid());
		assertEquals(1, vr.getValidationMessages().size());
		assertEquals("The value of analyticalmethod= must match the format [0-9]+|abc", vr.getValidationMessages().get(0));
		assertArrayEquals(new String[]{""}, vr.getRawValue());
	}
	@Test
	public void testValidate_MinOccursOnce_matchingValue() {
		AbstractValidator<String[]> validator = new RegexValidator<String[]>(Parameters.ANALYTICAL_METHOD, 1, AbstractValidator.DEFAULT_MAX_OCCURS, AbstractValidator.DEFAULT_DELIMITER, "[0-9]+|abc");
		ValidationResult<String[]> vr = validator.validate("abc");
		assertTrue(vr.isValid());
		assertEquals(0, vr.getValidationMessages().size());
		assertArrayEquals(new String[]{"abc"}, vr.getTransformedValue());
	}
	@Test
	public void testValidate_MinOccursOnce_mismatch() {
		AbstractValidator<String[]> validator = new RegexValidator<String[]>(Parameters.ANALYTICAL_METHOD, 1, AbstractValidator.DEFAULT_MAX_OCCURS, AbstractValidator.DEFAULT_DELIMITER, "[0-9]+|abc");
		ValidationResult<String[]> vr = validator.validate("abcabc");
		assertFalse(vr.isValid());
		assertEquals(1, vr.getValidationMessages().size());
		assertEquals("The value of analyticalmethod=abcabc must match the format [0-9]+|abc", vr.getValidationMessages().get(0));
		assertArrayEquals(new String[]{"abcabc"}, vr.getRawValue());
	}
	String delim = AbstractValidator.DEFAULT_DELIMITER;
	@Test
	public void testValidate_MaxOccursThrice() {
		AbstractValidator<String[]> validator = new RegexValidator<String[]>(Parameters.ANALYTICAL_METHOD, AbstractValidator.DEFAULT_MIN_OCCURS, 3, AbstractValidator.DEFAULT_DELIMITER, "[0-9]+|abc");
		ValidationResult<String[]> vr = validator.validate("abc");
		assertTrue(vr.isValid());
		assertEquals(0, vr.getValidationMessages().size());
		assertArrayEquals(new String[]{"abc"}, vr.getTransformedValue());
	}
	@Test
	public void testValidate_MaxOccursThrice_2match() {
		AbstractValidator<String[]> validator = new RegexValidator<String[]>(Parameters.ANALYTICAL_METHOD, AbstractValidator.DEFAULT_MIN_OCCURS, 3, AbstractValidator.DEFAULT_DELIMITER, "[0-9]+|abc");
		ValidationResult<String[]> vr = validator.validate("abc" + delim + "abc");
		assertTrue(vr.isValid());
		assertEquals(0, vr.getValidationMessages().size());
		assertArrayEquals(new String[]{"abc", "abc"}, vr.getTransformedValue());
	}
	@Test
	public void testValidate_MaxOccursThrice_3match() {
		AbstractValidator<String[]> validator = new RegexValidator<String[]>(Parameters.ANALYTICAL_METHOD, AbstractValidator.DEFAULT_MIN_OCCURS, 3, AbstractValidator.DEFAULT_DELIMITER, "[0-9]+|abc");
		ValidationResult<String[]> vr = validator.validate("abc" + delim + "abc" + delim + "abc");
		assertTrue(vr.isValid());
		assertEquals(0, vr.getValidationMessages().size());
		assertArrayEquals(new String[]{"abc", "abc", "abc"}, vr.getTransformedValue());
	}
	@Test
	public void testValidate_MaxOccursThrice_tooMany() {
		AbstractValidator<String[]> validator = new RegexValidator<String[]>(Parameters.ANALYTICAL_METHOD, AbstractValidator.DEFAULT_MIN_OCCURS, 3, AbstractValidator.DEFAULT_DELIMITER, "[0-9]+|abc");
		ValidationResult<String[]> vr = validator.validate("abc" + delim + "abc" + delim + "abc" + delim + "abc");
		assertFalse(vr.isValid());
		assertEquals(1, vr.getValidationMessages().size());
		assertEquals("The value of analyticalmethod=abc;abc;abc;abc must match the format [0-9]+|abc", vr.getValidationMessages().get(0));
		assertArrayEquals(new String[]{"abc;abc;abc;abc"}, vr.getRawValue());
	}
	@Test
	public void testValidate_MaxOccursUnbounded() {
		AbstractValidator<String[]> validator = new RegexValidator<String[]>(Parameters.ANALYTICAL_METHOD, "[0-9]+|abc");
		String delim = validator.getDelimiter();
		ValidationResult<String[]> vr = validator.validate("abc" + delim + "abc" + delim + "abc" + delim + "abc");
		assertTrue(vr.isValid());
		assertEquals(0, vr.getValidationMessages().size());
		assertArrayEquals(new String[]{"abc", "abc", "abc", "abc"}, vr.getTransformedValue());
	}
	@Test
	public void testCounty_defaults() {
		AbstractValidator<?> validator = HashMapParameterHandler.getValidator(Parameters.COUNTY);
		assertNotNull(validator);
		assertEquals(AbstractValidator.DEFAULT_MIN_OCCURS, validator.getMinOccurs());
		assertEquals(AbstractValidator.IN_CLAUSE_LIMIT, validator.getMaxOccurs());
		assertEquals(AbstractValidator.DEFAULT_DELIMITER, validator.getDelimiter());
	}
	@Test
	public void testCounty_match() {
		AbstractValidator<?> validator = HashMapParameterHandler.getValidator(Parameters.COUNTY);
		ValidationResult<?> vr = validator.validate("US:55:027");
		assertTrue(vr.isValid());
		assertEquals(0, vr.getValidationMessages().size());
		assertArrayEquals(new String[]{"US:55:027"}, (String[])vr.getTransformedValue());
	}
	@Test
	public void testCounty_mismatch() {
		AbstractValidator<?> validator = HashMapParameterHandler.getValidator(Parameters.COUNTY);
		ValidationResult<?> vr = validator.validate("US:55:027;CV");
		assertFalse(vr.isValid());
		assertEquals(1, vr.getValidationMessages().size());
		assertEquals("The value of countycode=US:55:027;CV must match the format (?:([A-Z]{2}):)?([0-9]{1,2}):([0-9]{3}|N/A)", vr.getValidationMessages().get(0));
		assertArrayEquals(new String[]{"US:55:027;CV"}, (String[])vr.getRawValue());
	}
	@Test
	public void testHuc_defaults() {
		AbstractValidator<?> validator = HashMapParameterHandler.getValidator(Parameters.HUC);
		assertNotNull(validator);
		assertEquals(AbstractValidator.DEFAULT_MIN_OCCURS, validator.getMinOccurs());
		assertEquals(AbstractValidator.IN_CLAUSE_LIMIT, validator.getMaxOccurs());
		assertEquals(AbstractValidator.DEFAULT_DELIMITER, validator.getDelimiter());
	}
	@Test
	public void testHuc_match() {
		AbstractValidator<?> validator = HashMapParameterHandler.getValidator(Parameters.HUC);
		ValidationResult<?> vr = validator.validate("07090002");
		assertTrue(vr.isValid());
		assertEquals(0, vr.getValidationMessages().size());
		assertArrayEquals(new String[]{"07090002"}, (String[])vr.getTransformedValue());
	}
	@Test
	public void testHuc_mismatch() {
		AbstractValidator<?> validator = HashMapParameterHandler.getValidator(Parameters.HUC);
		ValidationResult<?> vr = validator.validate("07090002;CV");
		assertFalse(vr.isValid());
		assertEquals(1, vr.getValidationMessages().size());
		assertEquals("The value of huc=07090002;CV must match the format " + ParameterValidationConfig.REGEX_HUC, vr.getValidationMessages().get(0));
		assertArrayEquals(new String[]{"07090002;CV"}, (String[])vr.getRawValue());
	}
	@Test
	public void testMinActivities_match() {
		AbstractValidator<?> validator = HashMapParameterHandler.getValidator(Parameters.MIN_ACTIVITIES);
		ValidationResult<?> vr = validator.validate("100");
		assertTrue(vr.isValid());
		assertEquals(0, vr.getValidationMessages().size());
		assertArrayEquals(new String[]{"100"}, (String[])vr.getTransformedValue());
	}
	@Test
	public void testMinActivities_mismatch() {
		AbstractValidator<?> validator = HashMapParameterHandler.getValidator(Parameters.MIN_ACTIVITIES);
		ValidationResult<?> vr = validator.validate("100;CV");
		assertFalse(vr.isValid());
		assertEquals(1, vr.getValidationMessages().size());
		assertEquals("The value of minactivities=100;CV must match the format " + ParameterValidationConfig.REGEX_POSITIVE_INT, vr.getValidationMessages().get(0));
		assertArrayEquals(new String[]{"100;CV"}, (String[]) vr.getRawValue());
	}
	@Test
	public void testMinResults_match() {
		AbstractValidator<?> validator = HashMapParameterHandler.getValidator(Parameters.MIN_RESULTS);
		ValidationResult<?> vr = validator.validate("100");
		assertTrue(vr.isValid());
		assertEquals(0, vr.getValidationMessages().size());
		assertArrayEquals(new String[]{"100"}, (String[])vr.getTransformedValue());
	}
	@Test
	public void testMinResults_mismatch() {
		AbstractValidator<?> validator = HashMapParameterHandler.getValidator(Parameters.MIN_RESULTS);
		ValidationResult<?> vr = validator.validate("100;CV");
		assertFalse(vr.isValid());
		assertEquals(1, vr.getValidationMessages().size());
		assertEquals("The value of minresults=100;CV must match the format " + ParameterValidationConfig.REGEX_POSITIVE_INT, vr.getValidationMessages().get(0));
		assertArrayEquals(new String[]{"100;CV"}, (String[]) vr.getRawValue());
	}
	@Test
	public void testSiteId_defaults() {
		AbstractValidator<?> validator = HashMapParameterHandler.getValidator(Parameters.SITEID);
		assertNotNull(validator);
		assertEquals(AbstractValidator.DEFAULT_MIN_OCCURS, validator.getMinOccurs());
		assertEquals(AbstractValidator.UNBOUNDED, validator.getMaxOccurs());
		assertEquals(AbstractValidator.DEFAULT_DELIMITER, validator.getDelimiter());
	}
	@Test
	public void testSiteId_match() {
		AbstractValidator<?> validator = HashMapParameterHandler.getValidator(Parameters.SITEID);
		ValidationResult<?> vr = validator.validate("IASF-IATC201");
		assertTrue(vr.isValid());
		assertEquals(0, vr.getValidationMessages().size());
		assertArrayEquals(new String[]{"IASF-IATC201"}, (String[])vr.getTransformedValue());
		validator = HashMapParameterHandler.getValidator(Parameters.SITEID);
		vr = validator.validate("IASF-1");
		assertTrue(vr.isValid());
		assertEquals(0, vr.getValidationMessages().size());
		assertArrayEquals(new String[]{"IASF-1"}, (String[])vr.getTransformedValue());
		vr = validator.validate("IASF- 1");
		assertTrue(vr.isValid());
		assertEquals(0, vr.getValidationMessages().size());
		assertArrayEquals(new String[]{"IASF- 1"}, (String[])vr.getTransformedValue());
	}
	@Test
	public void testSiteId_mismatch() {
		AbstractValidator<?> validator = HashMapParameterHandler.getValidator(Parameters.SITEID);
		ValidationResult<?> vr = validator.validate("CV;IASF-IATC201");
		assertFalse(vr.isValid());
		assertEquals(1, vr.getValidationMessages().size());
		assertEquals("The value of siteid=CV;IASF-IATC201 must match the format [\\w]+\\-.*\\S", vr.getValidationMessages().get(0));
		assertArrayEquals(new String[]{"CV;IASF-IATC201"}, (String[])vr.getRawValue());
		vr = validator.validate("IASF-1 ");
		assertFalse(vr.isValid());
		assertEquals(1, vr.getValidationMessages().size());
		assertEquals("The value of siteid=IASF-1  must match the format [\\w]+\\-.*\\S", vr.getValidationMessages().get(0));
		assertArrayEquals(new String[]{"IASF-1 "}, (String[])vr.getRawValue());
	}
	@Test
	public void testState_defaults() {
		AbstractValidator<?> validator = HashMapParameterHandler.getValidator(Parameters.STATE);
		assertNotNull(validator);
		assertEquals(AbstractValidator.DEFAULT_MIN_OCCURS, validator.getMinOccurs());
		assertEquals(AbstractValidator.IN_CLAUSE_LIMIT, validator.getMaxOccurs());
		assertEquals(AbstractValidator.DEFAULT_DELIMITER, validator.getDelimiter());
	}
	@Test
	public void testState_match() {
		AbstractValidator<?> validator = HashMapParameterHandler.getValidator(Parameters.STATE);
		ValidationResult<?> vr = validator.validate("US:55");
		assertTrue(vr.isValid());
		assertEquals(0, vr.getValidationMessages().size());
		assertArrayEquals(new String[]{"US:55"}, (String[])vr.getTransformedValue());
	}
	@Test
	public void testState_mismatch() {
		AbstractValidator<?> validator = HashMapParameterHandler.getValidator(Parameters.STATE);
		ValidationResult<?> vr = validator.validate("US:55;CV");
		assertFalse(vr.isValid());
		assertEquals(1, vr.getValidationMessages().size());
		assertEquals("The value of statecode=US:55;CV must match the format (?:([A-Z]{2}):)?([0-9]{1,2})", vr.getValidationMessages().get(0));
		assertArrayEquals(new String[]{"US:55;CV"}, (String[])vr.getRawValue());
	}
	@Test
	public void testMimeType() {
		AbstractValidator<?> validator = HashMapParameterHandler.getValidator(Parameters.MIMETYPE);
		ValidationResult<?> vr = validator.validate(CSV);
		assertTrue(vr.isValid());
	}
	@Test
	public void testMimeType_mismatch() {
		AbstractValidator<?> validator = HashMapParameterHandler.getValidator(Parameters.MIMETYPE);
		ValidationResult<?> vr = validator.validate("cvs"); // intentional typo
		assertFalse(vr.isValid());
		assertEquals(1, vr.getValidationMessages().size());
		assertEquals("The value of mimeType=cvs must match the format " + ParameterValidationConfig.REGEX_MIMETYPES, vr.getValidationMessages().get(0));
		assertArrayEquals(new String[]{"cvs"}, (String[])vr.getRawValue());
	}
}