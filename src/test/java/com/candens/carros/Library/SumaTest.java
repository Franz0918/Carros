package com.candens.carros.Library;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SumaTest {

 @Test
 public void xmayory(){
    Suma abc = new Suma();
    int x =abc.sumar(2,3);
    assertEquals(5,x);
 }

 @Test
 public void xmenory(){
    Suma abc = new Suma();
    int y= abc.sumar(3,2);
    assertEquals(10,y);
  }

}