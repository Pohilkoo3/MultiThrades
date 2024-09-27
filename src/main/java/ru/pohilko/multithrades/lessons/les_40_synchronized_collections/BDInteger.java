package ru.pohilko.multithrades.lessons.les_40_synchronized_collections;

public class BDInteger extends BD<Integer>{

    public BDInteger() {
        super();
        listObject.add(1);
        listObject.add(2);
        listObject.add(3);
    }

    @Override
    public void printClassName() {
        super.printClassName();

    }


}
