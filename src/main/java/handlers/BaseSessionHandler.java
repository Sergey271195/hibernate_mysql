package handlers;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import utils.DbConnectionFactory;

import java.util.Optional;
import java.util.function.Supplier;

public class BaseSessionHandler {

    protected final SessionFactory sessionFactory = DbConnectionFactory.getSessionFactory();

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    protected <T> T doInTransaction(Supplier<T> supplier) {
        Optional<Transaction> transaction = beginTransaction();
        try {
            T result = supplier.get();
            transaction.ifPresent(Transaction::commit);
            return result;
        } catch (RuntimeException err) {
            transaction
                    .filter(Transaction::isActive)
                    .ifPresent(Transaction::rollback);
            throw err;
        }
    }

    protected void doInTransaction(Runnable runnable) {
        doInTransaction(() -> {
            runnable.run();
            return null;
        });
    }

    protected Optional<Transaction> beginTransaction() {
        Transaction transaction = getSession().getTransaction();
        if (transaction.isActive()) {
            return Optional.empty();
        }
        transaction.begin();
        return Optional.of(transaction);
    }

}
