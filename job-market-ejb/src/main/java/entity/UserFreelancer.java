package entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class UserFreelancer extends User {

    private String skills;

    @Lob
    // @Column(columnDefinition = "default 'This guy is so lazy..'")
    private String descriptionMessage;

    @Column(columnDefinition = "Decimal(10,2) default '0.0'")
    private Double balance;

    @ManyToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(name = "freelancer_jobs", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "job_id"))
    private List<Job> requestedJobs = new ArrayList<>();

    @Override
    public UserRole getRole() {
        return UserRole.Freelancer;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getDescriptionMessage() {
        return descriptionMessage;
    }

    public void setDescriptionMessage(String descriptionMessage) {
        this.descriptionMessage = descriptionMessage;
    }

    public List<Job> getRequestedJobs() {
        return requestedJobs;
    }

    public void setRequestedJobs(List<Job> requestedJobs) {
        this.requestedJobs = requestedJobs;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
