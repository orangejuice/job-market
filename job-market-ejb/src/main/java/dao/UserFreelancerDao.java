package dao;


import entity.User;
import entity.UserAdministrator;
import entity.UserFreelancer;
import entity.UserProvider;

import javax.ejb.Remote;

@Remote
public interface UserFreelancerDao extends BaseDao<UserFreelancer, Long> {

}
