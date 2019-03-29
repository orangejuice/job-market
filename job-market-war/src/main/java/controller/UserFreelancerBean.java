package controller;

import dao.JobDao;
import dao.UserFreelancerDao;
import entity.Job;
import entity.User;
import entity.UserFreelancer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.JMSUtil;
import util.MD5Util;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

import static util.HTTPUtil.getCurrentUser;
import static util.HTTPUtil.toUrl;

@Named
@RequestScoped
public class UserFreelancerBean implements Serializable {
    private static final Logger log = LoggerFactory.getLogger(UserFreelancerBean.class);

    @EJB
    private UserFreelancerDao userDao;
    @EJB
    private JobDao jobDao;

    private String msg;
    private String username;
    private String password;
    private Long id;

    @PostConstruct
    public void init() {
//        UserFreelancer freelancer = getFreelancer();
//        log.debug("query get freelancer: {} with id: {}", freelancer, getCurrentUser().getId());
    }

    public boolean ifHasTaken(Long jobId) {
        UserFreelancer freelancer = getFreelancer();

        log.debug("freelancer id:{} is checking for if has taken job id:{}", freelancer.getId(), jobId);
        if (freelancer.getRequestedJobs() != null) {
            return freelancer.getRequestedJobs().stream()
                    .anyMatch(job -> job.getId().equals(jobId));
        }
        return false;
    }

    public String revoke(Job job) {
        UserFreelancer freelancer = getFreelancer();

        freelancer.getRequestedJobs().stream()
                .filter(v -> v.getId().equals(job.getId())).findFirst()
                .ifPresent(j -> freelancer.getRequestedJobs().remove(j));

        log.debug("freelancer id:{} is willing to revoke the job id:{}", freelancer.getId(), job.getId());

        job.getRequestedFreelancers().stream()
                .filter(v -> v.getId().equals(freelancer.getId())).findFirst()
                .ifPresent(f -> job.getRequestedFreelancers().remove(f));

        jobDao.update(job);
        userDao.update(freelancer);
        return toUrl("job?id=" + job.getId());
    }

    public String undertake(Job job) {
        UserFreelancer freelancer = getFreelancer();

        freelancer.getRequestedJobs().add(job);
        job.getRequestedFreelancers().add(freelancer);

        log.debug("freelancer id:{} is willing to undertake the job [id:{}, title:{}]", freelancer.getId(), job.getId(), job.getTitle());
        JMSUtil.sendMessage("[Message] freelancer has offered to undertake the job " + job.getId());

        userDao.update(freelancer);
        return toUrl("job?id=" + job.getId());
    }

    public UserFreelancer getFreelancer() {
        return (UserFreelancer) getCurrentUser();
    }

    public String complete(Job job) {
        UserFreelancer freelancer = getFreelancer();

        job.setJobStatus(Job.JobStatus.Completed);
        freelancer.setBalance(freelancer.getBalance() + job.getPaymentOffer());

        log.debug("freelancer id:{} completed the job [id:{}, title:{}], balance now [{}]", freelancer.getId(), job.getId(), job.getTitle(), freelancer.getBalance());

        userDao.update(freelancer);
        jobDao.update(job);
        return toUrl("job?id=" + job.getId());
    }

    public void add() {
        UserFreelancer freelancer = new UserFreelancer();
        freelancer.setUsername(username);
        freelancer.setPassword(MD5Util.md5(username + password));
        freelancer.setRole(User.UserRole.Freelancer);
        userDao.save(freelancer);
        username="";
        password="";
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Long getId() {
        return id;
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

    public List<UserFreelancer> getAll() {
        return userDao.findAll(UserFreelancer.class);
    }

    public void delete() {
        userDao.delete(UserFreelancer.class, id);
    }

    public void setId(Long id) {
        this.id = id;
    }
}
