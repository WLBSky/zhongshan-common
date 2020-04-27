package com.hebta.common.persistence;

import java.util.List;

public class ApiListBody extends ApiBody {

    private List<?> list;

    public ApiListBody() {
        super();
    }

    public ApiListBody(List<?> list) {
        super();
        setList(list);
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
        if(null == list){
            super.setApiStatus(100);
        }
    }

}
