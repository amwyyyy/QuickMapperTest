package com.test.project.lts;

import com.github.ltsopensource.core.domain.Job;
import com.github.ltsopensource.jobclient.JobClient;
import com.github.ltsopensource.jobclient.RetryJobClient;
import com.github.ltsopensource.jobclient.domain.Response;

/**
 * @author denis.huang
 * @date 2017/6/21
 */
public class JobClientTest {
    public static void main(String[] args) {
        JobClient jobClient = new RetryJobClient();
        jobClient.setRegistryAddress("zookeeper://127.0.0.1:2181");
        jobClient.setClusterName("test_cluster");
        jobClient.setNodeGroup("test_jobClient");
        jobClient.addConfig("job.fail.store", "mapdb");
        jobClient.start();

        Job job = new Job();
        job.setTaskId("11111");
        job.setParam("shopId", "123456");
        job.setTaskTrackerNodeGroup("test_trade_TaskTracker");
        job.setCronExpression("0/30 * * * * ?"); // 支持 cronExpression表达式
        Response response = jobClient.submitJob(job);
        System.err.println(response);

        Runtime.getRuntime().addShutdownHook(new Thread(jobClient::stop));
    }
}
