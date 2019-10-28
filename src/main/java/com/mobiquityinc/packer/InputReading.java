package com.mobiquityinc.packer;

import com.mobiquityinc.exception.APIException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InputReading {

    public static final Integer ID_INDEX = 0;
    public static final Integer WEIGHT_INDEX = 1;
    public static final Integer COST_INDEX = 2;
    public static final String CURRENCY = "€";
    public static final String VALUES_SEPARATOR = ",";
    public static final String MAX_WEIGHT_DELIMITATOR = ":";

    /**
     * Reads a file and gets a string for each line, returns a list of strings containing file's lines
     * @param fileName absolute file path and name
     * @return list of string containing lines of file
     * @throws APIException
     */
    public List<String> readFile(String fileName) throws APIException {
        List<String> lines = null;
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            lines = stream.collect(Collectors.toList());
        } catch (IOException e) {
            throw new APIException("Error reading file " + fileName, e);
        }
        return lines;
    }

    /**
     * Converts a string with format 8 : (1,15.3,€34) to a PackageToSend object with a PackageItem for each item
     * between parenthesis
     * @param line
     * @return resulting PackageToSend object
     * @throws APIException
     */
    public static PackageToSend mapToPackage(String line) throws APIException {
        String[] cells = line.split(MAX_WEIGHT_DELIMITATOR);
        int packageMaxWeight = Integer.parseInt(cells[0].trim());
        String items = cells[1];
        StringTokenizer st = new StringTokenizer(items, " ");
        List<PackageItem> packageItems = new ArrayList<PackageItem>();
        PackageValidator validator = new PackageValidator();

        while (st.hasMoreTokens()) {
            packageItems.add(InputReading.getItemFromString(st.nextToken()));
        }
        PackageToSend packageToSend = new PackageToSend(packageMaxWeight, packageItems);
        validator.validatePackage(packageToSend);
        return packageToSend;
    }

    /**
     * Converts an item string with format (1,15.3,€34) to a single PackageItem object
     * @param item
     * @return
     * @throws APIException
     */
    public static PackageItem getItemFromString(String item) throws APIException {
        Integer id = null;
        Double weight = null;
        Integer cost = null;
        PackageValidator validator = new PackageValidator();
        try {
            String[] parts = item.replaceAll("\\(", "").replaceAll("\\)", "").replaceAll(CURRENCY, "")
                    .split(VALUES_SEPARATOR);
            id = Integer.parseInt(parts[ID_INDEX]);
            weight = Double.parseDouble(parts[WEIGHT_INDEX]);
            cost = Integer.parseInt(parts[COST_INDEX]);


        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            throw new APIException("Malformed line in file : " + item, e);
        }
        PackageItem packageItem = new PackageItem(id, weight, cost);
        validator.validatePackageItem(packageItem);
        return packageItem;
    }

}
