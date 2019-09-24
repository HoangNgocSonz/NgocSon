import game.GameObject;

import java.util.ArrayList;

public class Employee {

    public static void main(String[] args) {

    }

    public static void doSomething(int input) throws Exception {
        if(input > 0) {
            System.out.println("okay");
        } else if(input == 0) {
            throw new Exception("Not okay");
        } else {
            System.out.println("okay");
        }
    }
}
