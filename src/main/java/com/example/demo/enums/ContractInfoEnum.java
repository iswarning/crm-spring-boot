package com.example.demo.enums;

import java.util.ArrayList;
import java.util.List;

public class ContractInfoEnum {

    private final List<String> listS;

    public ContractInfoEnum(){
        listS = new ArrayList<>();
        listS.add("Chuyển nhượng cho khách hàng");
        listS.add("Thanh toán hợp đồng");
        listS.add("Chuyển dự án khác");
    }

    public List<String> getList(){
        return listS;
    }
}
