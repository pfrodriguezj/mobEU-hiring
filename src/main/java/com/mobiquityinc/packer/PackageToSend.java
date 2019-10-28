package com.mobiquityinc.packer;

import java.util.List;

public class PackageToSend {

    private Integer maxWeight;
    private List<PackageItem> itemsToSend;

    public PackageToSend(Integer maxWeight, List<PackageItem> itemsToSend) {
        this.maxWeight = maxWeight;
        this.itemsToSend = itemsToSend;
    }

    public Integer getMaxWeight() {
        return maxWeight;
    }

    public List<PackageItem> getItemsToSend() {
        return itemsToSend;
    }


}
