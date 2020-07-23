package com.test.project.lts;

import com.github.ltsopensource.tasktracker.TaskTracker;

/**
 * @author denis.huang
 * @date 2017/6/21
 */
public class TaskTrackerTest {
    public static void main(String[] args) {
        TaskTracker taskTracker = new TaskTracker();
        taskTracker.setJobRunnerClass(MyJobRunner.class);
        taskTracker.setRegistryAddress("zookeeper://127.0.0.1:2181");
        taskTracker.setNodeGroup("test_trade_TaskTracker");
        taskTracker.setClusterName("test_cluster");
        taskTracker.setWorkThreads(10);
        taskTracker.addConfig("job.fail.store", "mapdb");
        taskTracker.start();

        Runtime.getRuntime().addShutdownHook(new Thread(taskTracker::stop));
    }
}
