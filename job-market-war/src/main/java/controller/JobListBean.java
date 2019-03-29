package controller;

import com.sun.org.apache.xpath.internal.compiler.Keywords;
import constant.Constant;
import dao.JobDao;
import dao.SettingsDao;
import entity.Job;
import entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.HTTPUtil;
import util.Page;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.List;


@Named
@RequestScoped
public class JobListBean implements Serializable {
    private static final Logger log = LoggerFactory.getLogger(JobListBean.class);

    @EJB
    private SettingsDao settingsDao;

    @Inject
    private UserFreelancerBean freelancerBean;
    @Inject
    private UserProviderBean providerBean;

    @EJB
    private JobDao jobDao;

    private Page<Job> jobs;

    @PostConstruct
    public void init() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String pageStr = request.getParameter("page");
        String search = request.getParameter("s");

        int page;
        try {
            page = Integer.parseInt(pageStr);
        } catch (NumberFormatException e) {
            page = 1;
        }

        int size = Integer.parseInt(Constant.SETTINGS_TEXT_COUNT_PER_PAGE_DEFAULT);

        String record = HTTPUtil.getUIParam("record");

        log.debug("initialize jobs list with param page:{}, s:{}, record: {}", pageStr, search, record);

        if ("true".equals(record)) {
            if (HTTPUtil.getCurrentUser().getRole() == User.UserRole.Freelancer) {
                jobs = jobDao.getListByFreelancerPaged(page, size, freelancerBean.getFreelancer());
            } else {
                jobs = jobDao.getListByProviderPaged(page, size, providerBean.getProvider());
            }
        } else {
            jobs = jobDao.getListPaged(page, size, search);
        }
    }

    public Page<Job> getJobs() {
        return jobs;
    }

    public List<Job> getAllJobs() {
        return jobDao.findAll(Job.class);
    }
}
