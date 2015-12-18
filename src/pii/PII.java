/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pii;

import messegeControlle.MapControl;

/**
 *
 * @author sranga
 */
public class PII {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        A a1 =new A();
        a1.a = 10;
        System.out.println(a1.a);
        A a2 =new A();
        System.out.println(a2.a);

    }

}

class A {
    public static int a = 0; 
    A(){
        a  =0;
    }
}
