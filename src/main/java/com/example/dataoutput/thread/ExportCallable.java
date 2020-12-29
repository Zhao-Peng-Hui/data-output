package com.example.dataoutput.thread;


import com.example.dataoutput.strategy.ExportStrategy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class ExportCallable<T1, T2> implements Callable<List<T2>> {
    private static final Logger logger = LogManager.getLogger(ExportCallable.class);

    List<T1> list;

    ExportStrategy<T1, T2> exportStrategy;

    public ExportCallable(List<T1> list, ExportStrategy exportStrategy) {
        this.list = list;
        this.exportStrategy = exportStrategy;
    }

    @Override
    public List<T2> call() throws Exception {
        List<T2> t2List = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            logger.info("线程" + Thread.currentThread().getName() + " 正在处理第" + i + "条数据");
            t2List.add(exportStrategy.exportBuild(list.get(i)));
        }
        return t2List;
    }
}
