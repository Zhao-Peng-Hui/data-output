package com.example.dataoutput.strategy;

import java.util.List;

public interface ExportStrategy<T1, T2> {
    List<T1> find();

    T2 exportBuild(T1 t1);

    void export(List<T2> list);

}
