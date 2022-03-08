package com.gan.java8.basis.objectAndClass;

public class HeroDema extends Hero {
    public int x=7;
    public HeroDema(String name, int hp, int mp) {
        super(name, hp, mp);
    }
    public void test(){
        System.out.println("123");
    }

    public static void main(String[] args) {
        Hero a=new HeroDema("德玛西亚",150,0);
        a.play(a.name,a.hp,a.mp);
        System.out.println(a);
    }
}
