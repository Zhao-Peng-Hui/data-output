package com.example.dataoutput.thread;

import com.example.dataoutput.strategy.Export;
import com.example.dataoutput.strategy.ExportStrategy;
import com.example.dataoutput.strategy.UserExportStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExportTask<T1, T2> {

    ExportStrategy<T1, T2> exportStrategy;

    public ExportTask(ExportStrategy<T1, T2> exportStrategy) {
        this.exportStrategy = exportStrategy;
    }

    public static Integer thread_processing_number = 100;

    // future存储器
    List<Future<List<T2>>> futureList;

    public void exportTask() {
        futureList = new ArrayList<>();
        Export<T1, T2> entityExport = new Export<T1, T2>(exportStrategy);
        List<T1> list = entityExport.find();
        // 创建线程池
        int size = list.size();
        // 根据默认线程数算出子线程处理条数
        int count = size / thread_processing_number;
        // 线程池
        ExecutorService executorService = Executors.newFixedThreadPool(thread_processing_number);
        // 开启n个子线程分起任务
        for (int i = 0; i < thread_processing_number; i++) {
            int start = count * i;
            int end = count * (i + 1);
            if (i == thread_processing_number - 1) {
                end = size;
            }
            @SuppressWarnings("unchecked")
            Future<List<T2>> future = executorService.submit(new ExportCallable<>(list.subList(start, end), exportStrategy));
            // 每个异步计算的结果存放在future存储器中
            futureList.add(future);
        }
        ListenerThread listenerThread = new ListenerThread(this, exportStrategy);
        Thread resultThread = new Thread(listenerThread);
        resultThread.start();
    }
}
