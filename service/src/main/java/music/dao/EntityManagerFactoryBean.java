package music.dao;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/*
 * Guice has providers - Spring has FactoryBean instead.
 * This class provides an EntityManagerFactory object, which can be injected in several places.
 */
@Component
public class EntityManagerFactoryBean implements FactoryBean<EntityManagerFactory> {

    @Override
    public EntityManagerFactory getObject() throws Exception {

        System.out.println("  EntityManagerFactoryBean.getObject()");

        return Persistence.createEntityManagerFactory( "SheetMusic" );
    }

    @Override
    public Class<?> getObjectType() {
        return EntityManagerFactory.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    // TODO: close the EntityManagerFactory somewhere, somehow.
}
