package dao;


import entity.UserFreelancer;
import entity.UserProvider;

import javax.ejb.Remote;

@Remote
public interface UserProviderDao extends BaseDao<UserProvider, Long> {

}
