package dao;

import entity.Job;
import entity.User;
import entity.UserFreelancer;
import entity.UserProvider;
import util.Page;

import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;


@Stateless
public class JobDaoImpl extends BaseDaoImpl<Job, Long> implements JobDao {

    @Override
    public Page<Job> getListPaged(int pageStart, int pageSize, String filter) {
        Job.JobStatus status = Job.JobStatus.Open;

        TypedQuery<Job> jobResultQuery;
        Query jobCountQuery;

        if (filter != null) {
            jobResultQuery = em.createQuery(
                    "select p from Job as p " +
                            "where p.jobStatus=:status " +
                            "and (upper(p.id) = :filter " +
                            "or upper(p.keywords) like concat('%',upper(:filter),'%') " +
                            "or upper(p.title) like concat('%',upper(:filter),'%')) " +
                            "order by p.publishDate desc ", Job.class);
            jobCountQuery = em.createQuery("select count(p.id) from Job as p " +
                    "where p.jobStatus=:status " +
                    "and (upper(p.id) = :filter " +
                    "or upper(p.keywords) like concat('%',upper(:filter),'%') " +
                    "or upper(p.title) like concat('%',upper(:filter),'%')) ");
            jobResultQuery.setParameter("filter", filter);
            jobCountQuery.setParameter("filter", filter);
        } else {
            jobResultQuery = em.createQuery(
                    "select p from Job as p " +
                            "where p.jobStatus=:status " +
                            "order by p.publishDate desc ", Job.class);
            jobCountQuery = em.createQuery("select count(p.id) from Job as p where p.jobStatus=:status");
        }
        jobResultQuery.setParameter("status", status);
        jobCountQuery.setParameter("status", status);


        jobResultQuery.setFirstResult((pageStart - 1) * pageSize);
        jobResultQuery.setMaxResults(pageSize);
        List<Job> Jobs = jobResultQuery.getResultList();


        return getJobPage(pageStart, pageSize, jobCountQuery, Jobs);
    }

    private Page<Job> getJobPage(int pageStart, int pageSize, Query jobCountQuery, List<Job> jobs) {
        int count = 1;
        try {
            count = Math.toIntExact((Long) jobCountQuery.getSingleResult());
        } catch (Exception ignored) {
        }

        count = ((count - 1) / pageSize) + 1;
        Page<Job> page = new Page<>();
        page.setPageIndex(pageStart);
        page.setPageTotal(count);
        page.setCountPerPage(pageSize);
        if (jobs != null && jobs.size() > 0) {
            page.setItem(jobs);
        }
        return page;
    }

    @Override
    public Page<Job> getListByFreelancerPaged(int pageStart, int pageSize, UserFreelancer user) {

        TypedQuery<Job> query = em.createQuery(
                "select p from Job as p " +
                        "where :freelancer member of p.requestedFreelancers " +
                        "order by p.publishDate desc ", Job.class);
        query.setParameter("freelancer", user);

        query.setFirstResult((pageStart - 1) * pageSize);
        query.setMaxResults(pageSize);
        List<Job> Jobs = query.getResultList();
        Query q = em.createQuery("select count(p.id) from Job as p " +
                "where :freelancer member of p.requestedFreelancers ");
        query.setParameter("freelancer", user);

        return getJobPage(pageStart, pageSize, q, Jobs);
    }

    @Override
    public Page<Job> getListByProviderPaged(int pageStart, int pageSize, UserProvider user) {
        TypedQuery<Job> query = em.createQuery(
                "select p from Job as p " +
                        "where p.provider=:provider " +
                        "order by p.publishDate desc ", Job.class);
        query.setParameter("provider", user);

        query.setFirstResult((pageStart - 1) * pageSize);
        query.setMaxResults(pageSize);
        List<Job> Jobs = query.getResultList();
        Query q = em.createQuery("select count(p.id) from Job as p " +
                "where p.provider=:provider");
        query.setParameter("provider", user);

        return getJobPage(pageStart, pageSize, q, Jobs);
    }

    @Override
    public Job getLatestJob() {
        TypedQuery<Job> jobTypedQuery = em.createQuery("select p from Job as p " +
                "where p.jobStatus=:status " +
                "order by p.publishDate desc", Job.class)
                .setParameter("status", Job.JobStatus.Open)
                .setMaxResults(1);

        return getSingleResult(jobTypedQuery);
    }
}
