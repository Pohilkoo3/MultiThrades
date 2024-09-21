package ru.pohilko.multithrades.lessons.les_35_pool;

public class Session {

    private final DataBase dataBase;
    private boolean isIssued;


    public Session(DataBase dataBase) {
        this.dataBase = dataBase;
        this.isIssued = false;
    }

    public void makeSomeOperationWithDb(){
        dataBase.makeSomeOperation();
    }
}
