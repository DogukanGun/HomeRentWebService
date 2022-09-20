package com.dag.homerentwebservice.model.enums;

public enum HomeStatus {
    Rented(1),
    Sold(2),
    Renting(3),
    Selling(4);

    final public int key;

    HomeStatus(int key){ this.key = key;}
}
