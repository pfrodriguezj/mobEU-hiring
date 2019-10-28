package com.mobiquityinc.packer;

import com.mobiquityinc.exception.APIException;

public class PackageValidator {

    public static final int MAX_ITEM_WEIGHT = 100;
    public static final int MAX_ITEM_COST = 100;
    public static final int MAX_PACKAGE_WEIGHT = 100;
    public static final int MAX_PACKAGE_SIZE = 100;

    /**
     * Validates weigh for a item
     * @param itemId
     * @param weight
     * @return true if item's weight is under maximum
     * @throws APIException
     */
    private boolean validateItemWeigh(Integer itemId, Double weight) throws APIException {
        if (weight > MAX_ITEM_WEIGHT) {
            throw new APIException("Package " + itemId + " exceed max weight allowed");
        }
        return true;
    }

    /**
     * Validates id items cost is under maximum allowed
     * @param itemId
     * @param cost
     * @return true if item's cost is under maximum
     * @throws APIException
     */
    private boolean validateItemCost(Integer itemId, Integer cost) throws APIException {
        if (cost > MAX_ITEM_COST) {
            throw new APIException("Package item " + itemId + " exceed max weight allowed");
        }
        return true;
    }

    /**
     * Validates if package weight is under maximum allowed
     * @param weight
     * @return true if package weight is under maximum allowed
     * @throws APIException
     */
    private boolean validatePackageWeight(Integer weight) throws APIException {
        if (weight > MAX_PACKAGE_WEIGHT) {
            throw new APIException("Package exceed max weight allowed");
        }
        return true;
    }

    /**
     * Validates if package size is under maximum allowed
     * @param size
     * @return true if package size is under maximum allowed
     * @throws APIException
     */
    private boolean validatePackageSize(Integer size) throws APIException {
        if (size > MAX_PACKAGE_SIZE) {
            throw new APIException("Package exceed max size allowed");
        }
        return true;
    }

    /**
     * Validates constraints for a package item
     * @param item
     * @return true if all constraints are accomplished
     * @throws APIException
     */
    public boolean validatePackageItem(PackageItem item) throws APIException {
        return validateItemWeigh(item.getId(), item.getWeight()) && validateItemCost(item.getId(), item.getCost());
    }

    /**
     * Validates constraints for a package
     * @param packageToSend
     * @return true if all constraints are accomplished
     * @throws APIException
     */
    public boolean validatePackage(PackageToSend packageToSend) throws APIException {
        return validatePackageWeight(packageToSend.getMaxWeight()) && validatePackageSize(packageToSend.getItemsToSend().size());
    }


}
