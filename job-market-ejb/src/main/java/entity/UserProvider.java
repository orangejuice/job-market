package entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class UserProvider extends User {

    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinTable(name = "provider_jobs", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "job_id"))
    private List<Job> publishedJobs = new ArrayList<>();

    @Override
    public UserRole getRole() {
        return UserRole.Provider;
    }

    public List<Job> getPublishedJobs() {
        return publishedJobs;
    }

    public void setPublishedJobs(List<Job> publishedJobs) {
        this.publishedJobs = publishedJobs;
    }
}
