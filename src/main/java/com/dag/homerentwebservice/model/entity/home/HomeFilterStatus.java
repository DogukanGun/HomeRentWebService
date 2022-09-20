package com.dag.homerentwebservice.model.entity.home;

public enum HomeFilterStatus {
    ASCENDING_BY_PRICE(1),
    DESCENDING_BY_PRICE(2),
    ASCENDING_BY_NAME(3),
    DESCENDING_BY_NAME(4),
    ;


    final public int id;
    HomeFilterStatus(int id){this.id = id;}
}
