package com.test;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.junit.Test;
import org.mapdb.DB;
import org.mapdb.DBMaker;

/**
 * @author denis.huang
 * @since 2017/6/27
 */
public class NewTest {
    @Test
    public void test() {
        DB db = DBMaker.fileDB(
                        new File("C:\\Users\\zjq\\.lts\\JOB_CLIENT\\test_jobClient\\job_submit_failstore\\mapdb\\JC_192.168.9.199_14264_12-20-57.111_1\\lts.db"))
                        .encryptionEnable("lts").make();
        Map<String, Object> map = db.getAll();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            System.err.println("key: " + entry.getKey() + " - value: " + entry.getValue());
        }
    }

    @Test
    public void test1() throws IOException {

    }
}
