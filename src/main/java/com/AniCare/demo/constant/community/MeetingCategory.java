package com.AniCare.demo.constant.community;

public enum MeetingCategory {
    VOLUNTEER("봉사"), WALKING("산책모임"), INFO("정보");

    private final String typeName;

    MeetingCategory(String typeName) {this.typeName = typeName; }
    public String getTypeName(){return this.typeName;}
}
