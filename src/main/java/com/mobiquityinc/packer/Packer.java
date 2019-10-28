package com.mobiquityinc.packer;

import com.mobiquityinc.exception.APIException;

import java.util.List;
import java.util.stream.Collectors;

public class Packer {

    public static final String PACKAGE_SEPARATOR_CHARACTER = ",";
    public static final String PACKAGE_LINE_SEPARATOR_CHARACTER = "\n";

    private Packer() {
    }

    /**
     * Reads a file, maps to a list and return a list of packages to send for each line
     * @param fileName
     * @return String with id of suitable packages for each input line, separated by a line separator character
     * @throws APIException
     */
    public static String pack(String fileName) throws APIException {
        InputReading reader = new InputReading();
        // read a file, map it to list of PackageToSend object
        List<PackageToSend> packagesToSend = reader.readFile(fileName).stream().map(line -> {
            try {
                return InputReading.mapToPackage(line);
            } catch (APIException e) {
                new APIException(e.getMessage(), e);
            }
            return null;
        }).collect(Collectors.toList());

        //For each package, find suitable items and return their ids
        List<String> selectedPackages = packagesToSend.stream()
                .map(p -> PackagingUtilities.getIdsFromPackage(
                        PackagingUtilities.getItemsToPack(p.getItemsToSend(), p.getMaxWeight()), PACKAGE_SEPARATOR_CHARACTER))
                .collect(Collectors.toList());

        return selectedPackages.stream().collect(Collectors.joining(PACKAGE_LINE_SEPARATOR_CHARACTER));
    }
}
