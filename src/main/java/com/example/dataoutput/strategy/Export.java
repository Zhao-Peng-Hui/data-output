package com.example.dataoutput.strategy;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class Export<T1, T2> {
    public ExportStrategy<T1, T2> exportStrategy;

    public List<T1> find() {
        return exportStrategy.find();
    }

    public List<T1> find(Pageable pageable) {
        return exportStrategy.find(pageable);
    }

    public Export(ExportStrategy exportStrategy) {
        this.exportStrategy = exportStrategy;

    }

    public T2 buildData(T1 t1) {
        T2 t2 = exportStrategy.exportBuild(t1);
        return t2;
    }

    public void export(List<T2> list) {
        exportStrategy.export(list);
    }
}