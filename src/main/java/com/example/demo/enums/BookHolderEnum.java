package com.example.demo.enums;

import java.util.ArrayList;
import java.util.List;

public class BookHolderEnum {

    private final List<String> listS;

    public BookHolderEnum(){
        listS = new ArrayList<>();
        listS.add("Pháp lý");
        listS.add("Kế toán");
    }

    public List<String> getList(){
        return listS;
    }
}
