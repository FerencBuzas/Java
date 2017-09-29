package music.dao;

import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.function.Supplier;

@Component
public class DaoUtil {

    /**
     * Execute the given code block in a transaction; also close the transaction.
     * This method reduces the amount of boilerplate.
     * TODO: check whether @Transactional can be used instead.
     * 
     * @param entityManager  - to create the transaction 
     * @param supplier       - code to be called in the transaction
     * @param <R>            - type of the result
     * @return               - the result of the code
     */
    <R> R funcInTrans(EntityManager entityManager,
                      Supplier<R> supplier) {

        entityManager.getTransaction().begin();

        R result = supplier.get();

        entityManager.getTransaction().commit();
        entityManager.close();

        return result;
    }
}
