package entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table
public class Job implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String keywords;

    @Lob
    private String description;

    private Double paymentOffer;

    private Date publishDate;

    private JobStatus jobStatus;


    @ManyToOne
    private UserProvider provider;

    @ManyToMany(mappedBy = "requestedJobs", fetch = FetchType.EAGER)
    private List<UserFreelancer> requestedFreelancers = new ArrayList<>();

    @ManyToOne
    private UserFreelancer assignedFreelancer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String password) {
        this.keywords = password;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPaymentOffer() {
        return paymentOffer;
    }

    public void setPaymentOffer(Double paymentOffer) {
        this.paymentOffer = paymentOffer;
    }

    public JobStatus getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(JobStatus jobStatus) {
        this.jobStatus = jobStatus;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishedDate) {
        this.publishDate = publishedDate;
    }

    public UserFreelancer getAssignedFreelancer() {
        return assignedFreelancer;
    }

    public void setAssignedFreelancer(UserFreelancer assignedFreelancers) {
        this.assignedFreelancer = assignedFreelancers;
    }

    public UserProvider getProvider() {
        return provider;
    }

    public void setProvider(UserProvider provider) {
        this.provider = provider;
    }

    public List<UserFreelancer> getRequestedFreelancers() {
        return requestedFreelancers;
    }

    public void setRequestedFreelancers(List<UserFreelancer> requestedFreelancers) {
        this.requestedFreelancers = requestedFreelancers;
    }

    public enum JobStatus {
        Open, Closed, Completed
    }

}
