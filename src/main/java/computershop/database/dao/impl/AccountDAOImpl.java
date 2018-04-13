package computershop.database.dao.impl;

import computershop.database.dao.AccountDAO;
import computershop.database.entity.Account;
import org.hibernate.Session;
import org.hibernate.exception.GenericJDBCException;
import org.hibernate.query.Query;

import java.util.List;

public class AccountDAOImpl extends DbSessionFactory implements AccountDAO {
    @Override
    public List<Account> getEntities() {
        List<Account> accounts = null;
        try (Session currentSession = factory.getCurrentSession()) {
            currentSession.beginTransaction();
            Query<Account> theQuery = currentSession.createQuery("from Account", Account.class);
            accounts = theQuery.getResultList();
            currentSession.getTransaction().commit();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return accounts;
    }

    @Override
    public void saveEntity(Account entity) throws GenericJDBCException{
        try (Session currentSession = factory.getCurrentSession()) {
            currentSession.beginTransaction();
            currentSession.saveOrUpdate(entity);
            currentSession.getTransaction().commit();
        }
        catch (GenericJDBCException e){
            throw e;
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }

    }

    @Override
    public Account getEntity(int id) {
        Account account = null;
        try (Session currentSession = factory.getCurrentSession()) {
            currentSession.beginTransaction();
            account = currentSession.get(Account.class, id);
            currentSession.getTransaction().commit();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return account;
    }

    @Override
    public void deleteEntity(int id) {
        try (Session currentSession = factory.getCurrentSession()) {
            currentSession.beginTransaction();
            currentSession.createQuery("delete from Account where id=:accountId")
                    .setParameter("accountId", id).executeUpdate();
            currentSession.getTransaction().commit();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }
}
