package com.example.demo.enums;

import java.util.ArrayList;
import java.util.List;

public class StatusCreatedEnum {

    private List<String> listS;

    public StatusCreatedEnum(){
        listS = new ArrayList<>();
        listS.add("Nhân viên");
        listS.add("Khách");
    }

    public List<String> getList(){
        return listS;
    }
}
