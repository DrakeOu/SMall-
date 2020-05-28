package com.xjtuse.mall.bean.stat;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("vChart")
@Scope("prototype")
public class VChart {

    private List<String> columns = new ArrayList<>();
    private List<Object> rows = new ArrayList<>();

    public void addRowsItem(Object item){
        rows.add(item);
    }

    public List<String> getColumns() {
        return columns;
    }

    public void setColumns(List<String> columns) {
        this.columns = columns;
    }

    public List<Object> getRows() {
        return rows;
    }

    public void setRows(List<Object> rows) {
        this.rows = rows;
    }
}
