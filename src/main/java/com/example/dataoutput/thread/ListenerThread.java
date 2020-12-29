package com.example.dataoutput.thread;


import com.example.dataoutput.strategy.ExportStrategy;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

public class ListenerThread<T1, T2> implements Runnable {

    ExportTask<T1, T2> exportTask;
    ExportStrategy<T1, T2> exportStrategy;

    public ListenerThread(ExportTask exportTask, ExportStrategy exportStrategy) {
        this.exportTask = exportTask;
        this.exportStrategy = exportStrategy;
    }

    @Override
    public void run() {
        long a = System.currentTimeMillis();
        // 循环处理各线程执行完后的结果
        for (Future<List<T2>> future : exportTask.futureList) {
            this.outputResultFromFuture(future);
        }
        if (exportTask.ENABLE_PAGINATED_EXPORT) {
            System.out.println("本次分页任务，第[" + exportTask.page + "]页执行完毕,耗时：" + (System.currentTimeMillis() - a) + "ms");
        } else {
            System.out.println("本次任务执行完毕,耗时：" + (System.currentTimeMillis() - a) + "ms");
        }

    }

    private void outputResultFromFuture(Future<List<T2>> future) {
        try {
            while (true) {
                if (future.isDone() && !future.isCancelled()) {
                    break;
                } else {
                    Thread.sleep(1000);
                }
            }
            List<T2> t2List = future.get();
            exportStrategy.export(t2List);
            exportTask.futureList = null;
            exportTask.CURRENT_PAGE_END = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
