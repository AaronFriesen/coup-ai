package model;

public enum Card {
    AMBASSADOR("ambassador"),
    ASSASSIN("assassin"),
    CAPTAIN("captain"),
    CONTESSA("contessa"),
    DUKE("duke");


    private String frontFile;
    private String backFile;

    public Card(String fileName) {
        this.frontFile = "../../images/card-" + fileName + ".jpg";
        this.backFile = "../../images/card-back.jpg";
    }

    public String getFront() {
        return this.frontFile;
    }

    public String getBack() {
        return this.backFile;
    }
}
