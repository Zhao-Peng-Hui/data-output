package com.example.dataoutput.strategy;

public interface ExportStrategy<T1, T2> {
    T2 exportBuild(T1 t1);
}
