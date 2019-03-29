package controller;

import dao.JobDao;
import dao.UserProviderDao;
import entity.Job;
import entity.User;
import entity.UserProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.HTTPUtil;
import util.MD5Util;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

import static util.HTTPUtil.getCurrentUser;

@Named
@RequestScoped
public class UserProviderBean implements Serializable {
    private static final Logger log = LoggerFactory.getLogger(UserProviderBean.class);

    @EJB
    private JobDao jobDao;
    @EJB
    private UserProviderDao userDao;

    private String msg;
    private String username;
    private String password;
    private Long id;

    @PostConstruct
    public void init() {
//        UserProvider provider = getProvider();
//        log.debug("query get provider: {} with id: {}", provider, getCurrentUser().getId());
    }

    public UserProvider getProvider() {
        return (UserProvider) getCurrentUser();
    }

    public String remove(Long jobId) {
        UserProvider provider = getProvider();

        log.debug("provider id:{} is willing to remove the job id:{}", provider.getId(), jobId);

        jobDao.delete(Job.class, jobId);

        return HTTPUtil.toUrl("/jobs");
    }

    public void add() {
        UserProvider freelancer = new UserProvider();
        freelancer.setUsername(username);
        freelancer.setPassword(MD5Util.md5(username + password));
        freelancer.setRole(User.UserRole.Provider);
        userDao.save(freelancer);
        username="";
        password="";
    }

    public List<UserProvider> getAll() {
        return userDao.findAll(UserProvider.class);
    }

    public void delete() {
        userDao.delete(UserProvider.class, id);
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
