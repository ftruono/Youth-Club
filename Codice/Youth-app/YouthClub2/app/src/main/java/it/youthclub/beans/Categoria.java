package it.youthclub.beans;

import java.util.ArrayList;

public class Categoria {

    public  enum types{
        BAR(1),
        PUB(2),
        DISCO(4),
        ENOTECA(8),
        UNDEFINED(16);

        private final int id;

        types(int id) {
            this.id = id;

        }
        public int getValue() { return id; }

    }

    private Categoria(){

    }

    public static Integer[] getSingleCategoriesFromValue(int value) {
        if (value>15)
            return new Integer[] {types.UNDEFINED.getValue()};
        else {
            ArrayList<Integer> list=new ArrayList<>();
            if(value>=types.ENOTECA.getValue()) {
                value-=types.ENOTECA.getValue();
                list.add(types.ENOTECA.getValue());
            }
            if(value>=types.DISCO.getValue()) {
                value-=types.DISCO.getValue();
                list.add(types.DISCO.getValue());
            }
            if(value>=types.PUB.getValue()) {
                value-=types.PUB.getValue();
                list.add(types.PUB.getValue());
            }
            if(value>=types.BAR.getValue()) {
                value-=types.BAR.getValue();
                list.add(types.BAR.getValue());
            }
            Integer rt[]=new Integer[list.size()];
            return list.toArray(rt);

        }


    }
}
