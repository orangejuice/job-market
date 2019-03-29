package dao;

import entity.Job;
import entity.User;
import entity.UserFreelancer;
import entity.UserProvider;
import util.Page;

import javax.ejb.Remote;

@Remote
public interface JobDao extends BaseDao<Job, Long> {

    Page<Job> getListPaged(int pageStart, int pageSize, String filter);

    Page<Job> getListByFreelancerPaged(int pageStart, int pageSize, UserFreelancer user);

    Page<Job> getListByProviderPaged(int pageStart, int pageSize, UserProvider user);

    Job getLatestJob();
}
