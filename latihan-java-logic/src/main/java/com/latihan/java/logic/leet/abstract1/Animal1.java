package com.latihan.java.logic.leet.abstract1;

class Animal1 {

    public void eat() throws Exception {
        System.out.println("Animal eats");
    }


}

class Dog1 extends Animal1 {
    public void eat(){
        System.out.println("Dog eats");
    }

    public static void main(String[] args) {
        Animal1 a = new Dog1();
        Dog1 d = new Dog1();
        d.eat();
//        a.eat(); error
    }
}
