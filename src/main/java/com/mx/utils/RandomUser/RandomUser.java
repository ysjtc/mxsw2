package com.mx.utils.RandomUser;/*
@author 郭子雄
@description 生成用户名的工具类
*/



public class RandomUser {
    public static String RandomName(){

        String name="";
        int i=(int)(Math.random()*5);
        switch(i) {
            case 0:
                while (name.length() < 10) {
                    int random = (int) (Math.random() * 10);
                    name += Integer.toString(random);
                }
                     break;
            case 1:
                while (name.length() < 9) {
                    int random = (int) (Math.random() * 9);
                    name += Integer.toString(random);
                }
                break;
            case 2:
                while (name.length() < 8) {
                    int random = (int) (Math.random() * 8);
                    name += Integer.toString(random);
                }
                break;
            case 3:
                while (name.length() < 7) {
                    int random = (int) (Math.random() * 7);
                    name += Integer.toString(random);
                }
                break;
            case 4:
                while (name.length() < 6) {
                    int random = (int) (Math.random() * 6);
                    name += Integer.toString(random);
                }
                break;
        }
        return name;
    }
}
