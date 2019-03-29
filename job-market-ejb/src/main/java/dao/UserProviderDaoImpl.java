package dao;


import entity.UserFreelancer;
import entity.UserProvider;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;


@Stateless
public class UserProviderDaoImpl extends BaseDaoImpl<UserProvider, Long> implements UserProviderDao {

}
