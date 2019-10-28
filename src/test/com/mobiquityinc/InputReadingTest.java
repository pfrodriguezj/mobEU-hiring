package com.mobiquityinc;

import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.packer.InputReading;
import com.mobiquityinc.packer.PackageItem;
import com.mobiquityinc.packer.PackageToSend;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class InputReadingTest {

	@Test
	public void testReadFile() {
		InputReading ir = new InputReading();
		try {
			List<String> fileLines = ir.readFile("/home/felipe.jimenez/Documents/packagingInput.txt");
			List<String> expectedLines = new ArrayList<String>();
			expectedLines.add("81 : (1,53.38,€45) (2,88.62,€98) (3,78.48,€3) (4,72.30,€76) (5,30.18,€9) (6,46.34,€48)");
			expectedLines.add("8 : (1,15.3,€34)");
			expectedLines.add("75 : (1,85.31,€29) (2,14.55,€74) (3,3.98,€16) (4,26.24,€55) (5,63.69,€52) (6,76.25,€75) (7,60.02,€74) (8,93.18,€35) (9,89.95,€78)");
			expectedLines.add("56 : (1,90.72,€13) (2,33.80,€40) (3,43.15,€10) (4,37.97,€16) (5,46.81,€36) (6,48.77,€79) (7,81.80,€45) (8,19.36,€79) (9,6.76,€64)");
			for(String line:fileLines) {
				assertTrue(expectedLines.contains(line));
			}
		} catch (APIException e) {
			fail();
		}
	}

	@Test
	public void testMapToPackage() {
		String line = "75 : (1,85.31,€29) (2,14.55,€74) (3,3.98,€16) (4,26.24,€55) (5,63.69,€52) (6,76.25,€75) (7,60.02,€74) (8,93.18,€35) (9,89.95,€78)";
		try {
			PackageToSend pts = InputReading.mapToPackage(line);
			List<PackageItem> expected = new ArrayList<PackageItem>();
			expected.add(new PackageItem(1, 85.31d, 29));
			expected.add(new PackageItem(2, 14.55d, 74));
			expected.add(new PackageItem(3, 3.98d, 16));
			expected.add(new PackageItem(4, 26.24d, 55));
			expected.add(new PackageItem(5, 63.69d, 52));
			expected.add(new PackageItem(6, 76.25d, 75));
			expected.add(new PackageItem(7, 60.02d, 74));
			expected.add(new PackageItem(8, 93.18d, 35));
			expected.add(new PackageItem(9, 89.95d, 75));

			List<PackageItem> actualPackageItems = pts.getItemsToSend();
			
			assertEquals(Long.valueOf(75), Long.valueOf(pts.getMaxWeight()));
			assertEquals(9, pts.getItemsToSend().size());
			assertEquals(expected.get(0).toString(), actualPackageItems.get(0).toString() );
		} catch (APIException e) {
			fail();
		}
	}

	@Test
	public void testGetItemFromString() {
		String itemString = "(1,85.31,€29)";
		try {
			PackageItem expected = new PackageItem(1, 85.31, 29);
			PackageItem actual = InputReading.getItemFromString(itemString);
		
			assertEquals(expected.getId(), actual.getId());
			assertEquals(expected.getWeight(), actual.getWeight());
			assertEquals(expected.getCost(), actual.getCost());
		} catch (APIException e) {
			fail();
		}
	}

}
