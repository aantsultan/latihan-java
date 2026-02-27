package com.latihan.java.logic.leet.abstract1;

//abstract class Animal {
//
//    void run(){
//        System.out.println("Animal run");
//    }
//
//    abstract void sound();
//    abstract Number number();
//
//}
//
//class Dog extends Animal {
//    void sound() {
//        System.out.println("Bark");
//    }
//
//    @Override
//    public Integer number() throws NumberFormatException{
//        return null;
//    }
//
//    public void run(){
//        System.out.println(" Dog Runs");
//    }
//}
//
//class Abs {
//    public static void main(String[] args) {
//        Animal dog =  new Dog();
//        dog.sound();
//        dog.run();
//    }
//}

class Animal {
    public void eat()throws NullPointerException {
        System.out.print("Animal eats "  );
    }

    static void test(){}
}
class Dog extends Animal {
    void eat(String s)throws Exception{
        System.out.print("Dog eats "+ s);
    }
}
class Abs {
    public static void main (String [ ] args)throws Exception {
        Animal a= new Dog();
        Dog d = (Dog)a;
        a.eat();
        d.eat("Meat");
    }
}
