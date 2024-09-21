package ru.pohilko.multithrades.lessons.les_35_pool;

public class OperationWithDb implements Runnable{

    private final ConnectionPool connectionPool;

    public OperationWithDb(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public void run() {
        Session session = (Session) connectionPool.openSession();
        session.makeSomeOperationWithDb();
        connectionPool.closeSession(session);
    }
}
