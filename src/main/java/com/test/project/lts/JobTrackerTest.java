package com.test.project.lts;

import com.github.ltsopensource.jobtracker.JobTracker;

/**
 * @author denis.huang
 * @since 2017/6/21
 */
public class JobTrackerTest {
    public static void main(String[] args) {
        JobTracker jobTracker = new JobTracker();
        jobTracker.setClusterName("test_cluster");
        jobTracker.setRegistryAddress("zookeeper://127.0.0.1:2181");
        jobTracker.setListenPort(35001);

        jobTracker.addConfig("jdbc.url", "jdbc:mysql://127.0.0.1:3306/lts");
        jobTracker.addConfig("jdbc.username", "root");
        jobTracker.addConfig("jdbc.password", "123");

        jobTracker.start();

        Runtime.getRuntime().addShutdownHook(new Thread(jobTracker::stop));
    }
}
