package controller;

import dao.UserDao;
import entity.Job;
import entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Named
@RequestScoped
public class ConstantBean implements Serializable {
    private static final Logger log = LoggerFactory.getLogger(ConstantBean.class);

    @EJB
    private UserDao userDao;

    public String getWebsiteName() {
        return "Online Job Marketplace";
    }

    public List<User.UserRole> getUserRoleList() {
        List<User.UserRole> userRoles;
        if (userDao.findAdmin() != null) {
            userRoles = new ArrayList<>(Arrays.asList(User.UserRole.values()));
            userRoles.remove(User.UserRole.Administrator);
        } else {
            userRoles = new ArrayList<>();
            userRoles.add(User.UserRole.Administrator);
        }
//        log.debug("return userRoles: {}", userRoles);
        return userRoles;
    }

    public User.UserRole getAdministrator() {
//        log.debug("return userRole: {}", User.UserRole.Administrator);
        return User.UserRole.Administrator;
    }

    public User.UserRole getFreelancer() {
//        log.debug("return userRole: {}", User.UserRole.Freelancer);
        return User.UserRole.Freelancer;
    }

    public User.UserRole getProvider() {
//        log.debug("return userRole: {}", User.UserRole.Provider);
        return User.UserRole.Provider;
    }

    public Job.JobStatus jobStatus(String name) {
       return Job.JobStatus.valueOf(name);
    }
}
