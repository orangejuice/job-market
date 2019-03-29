package dao;


import entity.User;
import entity.UserAdministrator;
import entity.UserFreelancer;
import entity.UserProvider;
import org.apache.commons.beanutils.BeanUtils;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;


@Stateless
public class UserFreelancerDaoImpl extends BaseDaoImpl<UserFreelancer, Long> implements UserFreelancerDao {

}
