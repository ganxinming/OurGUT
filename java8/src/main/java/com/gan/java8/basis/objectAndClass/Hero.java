package com.gan.java8.basis.objectAndClass;

/**
 * @author 12714
 */
public class Hero {
    protected String name;
    protected int hp;
    protected int mp;
    public Hero(String name, int hp, int mp) {
        this.name = name;
        this.hp = hp;
        this.mp = mp;
    }
    public Hero (){

    }
    public void play(String name,int hp,int mp){
        System.out.println(name+"还剩血量"+hp+"魔法"+mp);
    }

}
