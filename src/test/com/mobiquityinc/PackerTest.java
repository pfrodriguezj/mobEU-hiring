package com.mobiquityinc;

import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.packer.Packer;
import org.junit.Test;

import static org.junit.Assert.*;


public class PackerTest {

	@Test
	public void test() {
		try {
			String selectedPackages = Packer.pack("/home/felipe.jimenez/Documents/packagingInput.txt");
			String expectedValues = "4"+Packer.PACKAGE_LINE_SEPARATOR_CHARACTER+"-"+
					Packer.PACKAGE_LINE_SEPARATOR_CHARACTER+"2,7"+
					Packer.PACKAGE_LINE_SEPARATOR_CHARACTER+"8,9";
			assertEquals(expectedValues, selectedPackages);
		} catch (APIException e) {
			fail();
		}
	}

}
