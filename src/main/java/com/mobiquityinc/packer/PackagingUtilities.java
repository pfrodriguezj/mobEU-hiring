package com.mobiquityinc.packer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PackagingUtilities {

    public static final String EMPTY_PACKAGE_CHARACTER = "-";

    /**
     * Given a list of PackageItems and a max weight, returns a list of suitable items that maximize the cost
     * if the package. If there are more than one possible combination of items, it takes the less weight one
     * The input items are ordered in descending order for cost and then in ascending order for weight
     * The items combination that maximizes the package cost is the first combination of items
     * whose total weight is under the maximum weight allowed to the package
     * @param items items to pack
     * @param maxWeight max weight allowed for this package
     * @return list of items to pack
     */
    public static List<PackageItem> getItemsToPack(final List<PackageItem> items, final Integer maxWeight) {
        List<PackageItem> suitableOrderedItems = items.stream().
                filter(pi -> (pi.getWeight() <= maxWeight)).
                sorted(Comparator.comparing(PackageItem::getCost).reversed().thenComparing(PackageItem::getWeight)).
                collect(Collectors.toList());

        if (suitableOrderedItems.isEmpty()) {
            return null;
        }

        List<PackageItem> itemsToPack = new ArrayList<>();

        Double totalActualWeight = Double.valueOf(0);
        for (PackageItem pi : suitableOrderedItems) {
            if (totalActualWeight + pi.getWeight() <= maxWeight) {
                itemsToPack.add(pi);
                totalActualWeight += pi.getWeight();
            }
        }
        return itemsToPack;
    }

    /**
     * Convert a list of package items to a string of its id separated for a character
     * @param items items in a package
     * @param separator character to separate each id
     * @return String of ids separated for a value
     */
    public static String getIdsFromPackage(List<PackageItem> items, String separator) {
        if (items == null || items.isEmpty()) {
            return EMPTY_PACKAGE_CHARACTER;
        }
        return items.stream().map(item -> item.getId().toString()).collect(Collectors.joining(separator));
    }
}
