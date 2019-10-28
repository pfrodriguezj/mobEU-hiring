package com.mobiquityinc;


import com.mobiquityinc.packer.PackageItem;
import com.mobiquityinc.packer.PackagingUtilities;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class PackagingUtilitiesTest {

	
	@Test
	public void testGetItemsToPack1() {
		Integer maxWeight = 81;
		List<PackageItem> items = new ArrayList<PackageItem>();
		items.add(new PackageItem(1, 53.38d, 45));
		items.add(new PackageItem(2, 88.62d, 98));
		items.add(new PackageItem(3, 78.48, 3));
		items.add(new PackageItem(4, 72.30d, 76));
		items.add(new PackageItem(5, 30.18d, 9));
		items.add(new PackageItem(6, 46.34d, 48));

		List<PackageItem> pack = PackagingUtilities.getItemsToPack(items, maxWeight);
		StringBuilder packs = new StringBuilder();
		pack.stream().forEach(i->packs.append(i.getId()));
		assertEquals("4", packs.toString());
	}
	
	@Test
	public void testGetItemsToPack2() {
		Integer maxWeight = 8;
		List<PackageItem> items = new ArrayList<PackageItem>(); 
		items.add(new PackageItem(1, 15.3d, 34));

		List<PackageItem> pack = PackagingUtilities.getItemsToPack(items, maxWeight);
		
		assertNull(pack);
	}
	
	@Test
	public void testGetItemsToPack3() {
		Integer maxWeight = 75;
		List<PackageItem> items = new ArrayList<PackageItem>(); 
		items.add(new PackageItem(1, 85.31d, 29));
		items.add(new PackageItem(2, 14.55d, 74));
		items.add(new PackageItem(3, 3.98d, 16));
		items.add(new PackageItem(4, 26.24d, 55));
		items.add(new PackageItem(5, 63.69d, 52));
		items.add(new PackageItem(6, 76.25d, 75));
		items.add(new PackageItem(7, 60.02d, 74));
		items.add(new PackageItem(8, 93.18d, 35));
		items.add(new PackageItem(9, 89.95d, 75));

		List<PackageItem> pack = PackagingUtilities.getItemsToPack(items, maxWeight);
		StringBuilder packs = new StringBuilder();
		pack.stream().forEach(i->packs.append(i.getId()));
		assertEquals("27", packs.toString());
	}
	
		
	@Test
	public void testGetItemsToPack4() {
		Integer maxWeight = 56;
		List<PackageItem> items = new ArrayList<PackageItem>(); 
		items.add(new PackageItem(1, 90.72d, 13));
		items.add(new PackageItem(2, 33.80d, 40));
		items.add(new PackageItem(3, 43.15d, 10));
		items.add(new PackageItem(4, 37.97d, 16));
		items.add(new PackageItem(5, 46.81d, 36));
		items.add(new PackageItem(6, 48.77d, 79));
		items.add(new PackageItem(7, 81.80d, 45));
		items.add(new PackageItem(8, 19.36d, 79));
		items.add(new PackageItem(9, 6.76d, 64));
		
		List<PackageItem> pack = PackagingUtilities.getItemsToPack(items, maxWeight);
		System.out.println(PackagingUtilities.getIdsFromPackage(pack, "-"));
		
		StringBuilder packs = new StringBuilder();
		pack.stream().forEach(i->packs.append(i.getId()));
		assertEquals("89", packs.toString());
	}

}
