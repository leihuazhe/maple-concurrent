package com.may.two;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;

/**
 * @author maple 2018.09.10 下午9:38
 */
public class DataOperator<T> {

    private MapToProForkJoin task;

    private ForkJoinPool pool;

    //private Logger logger = LoggerFactory.getLogger(this.getClass());

    public DataOperator(List<Map<String, Object>> data, Class<T> type) {
        this.task = new MapToProForkJoin(data, type);
        this.pool = new ForkJoinPool();
    }

    public List<T> dealData() {
        long start = System.currentTimeMillis();
        Future<List<T>> temp = pool.submit(task);
        List<T> returnData = null;
        try {
            if (temp != null) {
                returnData = (List<T>) temp.get();
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        pool.shutdown();
        //logger.info("deal data use time {}", System.currentTimeMillis()-start);
        return returnData;
    }
}