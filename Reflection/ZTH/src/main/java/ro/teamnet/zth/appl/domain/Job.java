package ro.teamnet.zth.appl.domain;

import ro.teamnet.zth.api.annotations.Column;
import ro.teamnet.zth.api.annotations.Id;
import ro.teamnet.zth.api.annotations.Table;

/**
 * Created by Radu.Furculesteanu on 7/12/2017.
 */
@Table(name= "JOBS")
public class Job {
    @Id (name= "JOB_ID")
    private String jobId;
    @Column (name = "JOB_TITLE")
    private String jobTitle;
    @Column (name = "MIN_SALARY")
    private Long minSalary;
    @Column (name = "MAX_SALARY")
    private Long maxSalary;

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Long getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(Long minSalary) {
        this.minSalary = minSalary;
    }

    public Long getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(Long maxSalary) {
        this.maxSalary = maxSalary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Job job = (Job) o;

        if (!jobId.equals(job.jobId)) return false;
        if (!jobTitle.equals(job.jobTitle)) return false;
        if (!minSalary.equals(job.minSalary)) return false;
        return maxSalary.equals(job.maxSalary);
    }

    @Override
    public int hashCode() {
        int result = jobId.hashCode();
        result = 31 * result + jobTitle.hashCode();
        result = 31 * result + minSalary.hashCode();
        result = 31 * result + maxSalary.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Job{" +
                "jobId='" + jobId + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                ", minSalary=" + minSalary +
                ", maxSalary=" + maxSalary +
                '}';
    }
}
