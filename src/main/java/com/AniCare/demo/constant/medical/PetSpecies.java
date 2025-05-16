package com.AniCare.demo.constant.medical;

public enum PetSpecies {
    DOG("강아지"), CAT("고양이"), PARROT("앵무새"), FERRET("페럿"),
    HAMSTER("햄스터"), SQUIRREL("다람쥐"), RABBIT("토끼"),
    HEDGEHOG("고슴도치"), MOUSE("쥐"), TURTLE("거북이"),
    FOX("여우"), MONKEY("원숭이"), PIG("돼지"), DUCK("오리"),
    CHICKEN("닭"), COW("소"), GUINEAPIG("기니피그"), CAMELEON("카멜레온"),
    SNAKE("달팽이"), LIZARD("도마뱀"), HORSE("말"), MERCAT("미어캣"),
    SNAIL("달팽이"), FISH("물고기");

    private final String typeName;

    PetSpecies(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return this.typeName;
    }
}
