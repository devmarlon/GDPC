/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gardnerdenver.util;

import java.util.Comparator;

/**
 *
 * @author Marlon
 */
class Component implements Comparator<Component> {

    private String id;
    private String partNumber;
    private Integer quantity;

    public Component() {
    }

    public Component(String id, String partNumber, Integer quantity) {
        this.id = id;
        this.partNumber = partNumber;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public int compare(Component o1, Component o2) {
        return o1.getPartNumber().compareToIgnoreCase(o2.getPartNumber());
    }
}
