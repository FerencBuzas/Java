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
     * @param em        - to create the transaction 
     * @param supplier  - code to be called in the transaction
     * @param <R>       - type of the result
     * @return          - the result of the code
     */
    <R> R funcInTrans(EntityManager em, Supplier<R> supplier) {

        em.getTransaction().begin();

        R result = supplier.get();

        em.getTransaction().commit();
        em.close();

        return result;
    }
}
