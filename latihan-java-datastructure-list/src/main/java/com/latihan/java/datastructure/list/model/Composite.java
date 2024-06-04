package com.latihan.java.datastructure.list.model;

public class Composite {

    private Square firstSquare;
    private Square secondSquare;

    public void calculateA(int input){
        Square square = new Square();
        if(this.firstSquare == null){
            this.firstSquare = square;
        }
        this.firstSquare.setA(input);
        this.secondSquare = square;
    }

    public Square getFirstSquare() {
        return firstSquare;
    }

    public void setFirstSquare(Square firstSquare) {
        this.firstSquare = firstSquare;
    }

    public Square getSecondSquare() {
        return secondSquare;
    }

    public void setSecondSquare(Square secondSquare) {
        this.secondSquare = secondSquare;
    }
}
