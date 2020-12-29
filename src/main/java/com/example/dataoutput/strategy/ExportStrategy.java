package com.example.dataoutput.strategy;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ExportStrategy<T1, T2> {
    List<T1> find();

    List<T1> find(Pageable pageable);

    T2 exportBuild(T1 t1);

    void export(List<T2> list);

}
