package dao;


import entity.User;
import entity.UserAdministrator;
import entity.UserFreelancer;
import entity.UserProvider;

import javax.ejb.Remote;

@Remote
public interface UserDao extends BaseDao<User, Long> {
    User findByUsername(String username);

    User findByUsernameAndPassword(String username, String password);

    UserAdministrator findAdmin();

    UserProvider findProvider(Long id);
}
