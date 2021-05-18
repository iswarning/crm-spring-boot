package com.example.demo.enums;

import java.util.*;

public class StatusEnum {

    private final List<String> listS;

    public StatusEnum(){
        listS = new ArrayList<>();
        listS.add("Bỏ giữ chỗ");
        listS.add("Trả giữ chỗ");
        listS.add("Đang giữ chỗ");
    }

    public List<String> getList(){
        return listS;
    }

}
