package controller;

import constant.Constant;
import dao.JobDao;
import entity.Job;
import entity.User;
import entity.UserFreelancer;
import entity.UserProvider;
import mdb.LogFacilityBean;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.HTTPUtil;
import util.JMSUtil;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import static util.HTTPUtil.*;


@Named
@RequestScoped
public class JobBean implements Serializable {
    private static final Logger log = LoggerFactory.getLogger(JobBean.class);
    @EJB
    private JobDao jobDao;

    private Job job = new Job();

    @PostConstruct
    public void init() {
        long id = -1;
        try {
            id = Long.parseLong(getRequest().getParameter("id"));
        } catch (Exception ignored) {
        }

        if (id != -1) {
            job = jobDao.find(Job.class, id);
            log.debug("Requesting for job id: {} with result {}.", id, job);
        }
    }

    public String post() {
        log.debug("New job want to publish with title: {}", job.getTitle());

        job.setPublishDate(new Date());
        job.setJobStatus(Job.JobStatus.Open);

        User user = (User) HTTPUtil.getSession().getAttribute(Constant.CURRENT_USER);
        UserProvider provider = new UserProvider();
        try {
            BeanUtils.copyProperties(provider, user);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        job.setProvider(provider);

        job = jobDao.save(job);

        log.debug("New job published - title: {}, id: {}", job.getTitle(), job.getId());

        HTTPUtil.getSession().setAttribute(Constant.allTextPostListShouldBeUpdated, true);

        log.debug("分类下文章数目有变化，通知分类列表应该更新");
        HTTPUtil.getSession().setAttribute(Constant.textCategoryListShouldBeUpdated, true);
        return toUrl("/job?id=" + job.getId(), true);
    }

    public String update() {
        Job post = jobDao.find(Job.class, job.getId());

        jobDao.update(post);

        log.debug("更新文章成功");

        log.trace("分类下文章数目有变化，通知分类列表应该更新");
        HTTPUtil.getSession().setAttribute(Constant.textCategoryListShouldBeUpdated, true);

        return toUrl("/job?id=" + job.getId(), true);
    }

    public String toLogin() {
        String returnUrl = getRequest().getParameter(Constant.RETURN_URL);
        if (returnUrl != null) {
            setReturnBackUrl(returnUrl);
        }
        return toUrl("/login");
    }

    public String toRegister() {
        String returnUrl = getRequest().getParameter(Constant.RETURN_URL);
        if (returnUrl != null) {
            setReturnBackUrl(returnUrl);
        }
        return toUrl("/register");
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public String accept(UserFreelancer freelancer) {
        log.debug("trying to accept the freelancer id:{} for job [id:{}, title:{}]", freelancer.getId(), job.getTitle());

        job.setAssignedFreelancer(freelancer);
        job.setJobStatus(Job.JobStatus.Closed);

        JMSUtil.sendMessage("[Message] freelancer has been accepted for job " + job.getId());
        jobDao.update(job);
        return HTTPUtil.toUrl("job?id=" + job.getId());
    }

    public Job getNewestJob() {
        return jobDao.getLatestJob();
    }

    public void delete() {
        jobDao.delete(Job.class, job.getId());
    }
}
