package com.example.dataoutput.thread;

import com.example.dataoutput.strategy.Export;
import com.example.dataoutput.strategy.ExportStrategy;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExportTask<T1, T2> {

    public ExportStrategy<T1, T2> exportStrategy;

    //是否开启分页导出
    public boolean ENABLE_PAGINATED_EXPORT = false;

    public boolean CURRENT_PAGE_END = true;


    public ExportTask(ExportStrategy<T1, T2> exportStrategy) {
        this.exportStrategy = exportStrategy;
    }

    //指定线程数
    public static Integer THREAD_COUNT = 100;

    public static Integer PAGE_SIZE = 100000;


    public long page;

    // future存储器
    List<Future<List<T2>>> futureList;

    ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);

    public void exportTask() {
        Export<T1, T2> export = new Export<T1, T2>(exportStrategy);
        if (ENABLE_PAGINATED_EXPORT) {
//            Sort sort = Sort.by(Sort.Order.desc(""));
            Pageable pageable = PageRequest.of(0, PAGE_SIZE);
            List<T1> list = null;
            do {
                if (CURRENT_PAGE_END) {
                    page = pageable.getPageNumber() + 1;
                    CURRENT_PAGE_END = false;
                    list = export.find(pageable);
                    extracted(list);
                    pageable = PageRequest.of(pageable.getPageNumber() + 1, pageable.getPageSize());
                }
            } while (list.size() == PAGE_SIZE);
        } else {
            extracted(export.find());
        }
    }

    private void extracted(List list) {
        futureList = new ArrayList<>();
        int size = list.size();
        // 根据默认线程数算出子线程处理条数
        int count = size / THREAD_COUNT;

        // 开启n个子线程分起任务
        for (int i = 0; i < THREAD_COUNT; i++) {
            int start = count * i;
            int end = count * (i + 1);
            if (i == THREAD_COUNT - 1) {
                end = size;
            }
            @SuppressWarnings("unchecked")
            Future<List<T2>> future = executorService.submit(new ExportCallable<>(list.subList(start, end), this, exportStrategy));
            // 每个异步计算的结果存放在future存储器中
            futureList.add(future);
        }
        ListenerThread listenerThread = new ListenerThread(this, exportStrategy);
        Thread resultThread = new Thread(listenerThread);
        resultThread.start();
    }
}
