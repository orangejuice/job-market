package dao;


import entity.User;
import entity.UserAdministrator;
import entity.UserFreelancer;
import entity.UserProvider;
import org.apache.commons.beanutils.BeanUtils;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import java.lang.reflect.InvocationTargetException;


@Stateless
public class UserDaoImpl extends BaseDaoImpl<User, Long> implements UserDao {

    @Override
    public User findByUsername(String username) {
        TypedQuery<User> query = em.createQuery("select u from User as u where u.username = :username", User.class);
        query.setParameter("username", username);
        return getSingleResult(query);
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        TypedQuery<User> query = em.createQuery("select u from User as u where u.username=:username and u.password=:password", User.class);
        query.setParameter("username", username);
        query.setParameter("password", password);
        return getSingleResult(query);
    }

    @Override
    public UserAdministrator findAdmin() {
        TypedQuery<UserAdministrator> query = em.createQuery("select u from UserAdministrator as u", UserAdministrator.class);
        query.setFirstResult(0);
        query.setMaxResults(1);
        return getSingleResult(query);
    }

    @Override
    public UserProvider findProvider(Long id) {
        TypedQuery<UserProvider> query = em.createQuery("select u from UserProvider as u where u.id=:id", UserProvider.class);
        query.setParameter("id", id);
        query.setFirstResult(0);
        query.setMaxResults(1);
        return getSingleResult(query);
    }

    @Override
    public User save(User entity) {
        if(entity.getRole().equals(User.UserRole.Administrator)){
            UserAdministrator administrator = new UserAdministrator();
            try {
                BeanUtils.copyProperties(administrator, entity);
                em.persist(administrator);
                em.flush();
                return administrator;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if(entity.getRole().equals(User.UserRole.Freelancer)) {
            UserFreelancer freelancer = new UserFreelancer();
            try {
                BeanUtils.copyProperties(freelancer, entity);
                em.persist(freelancer);
                return freelancer;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            UserProvider provider = new UserProvider();
            try {
                BeanUtils.copyProperties(provider, entity);
                em.persist(provider);
                return provider;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
