package com.pucmm.practica_1;

import java.util.List;

public class Response {
    private List<User> data;

    public Response(List<User> data) {
        this.data = data;
    }

    public List<User> getData() {
        return data;
    }

    public void setData(List<User> data) {
        this.data = data;
    }
}
