package com.AniCare.demo.constant.community;

public enum ErrandCategory {
    WALKING("산책"), PETSITTER("펫시터"), ERRANDING("심부름");

    private final String typeName;

    ErrandCategory(String typeName) {this.typeName = typeName; }
    public String getTypeName(){return this.typeName;}
}
