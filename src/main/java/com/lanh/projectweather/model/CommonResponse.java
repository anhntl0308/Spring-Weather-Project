package com.lanh.projectweather.model;

import com.lanh.projectweather.dto.CityDto;

import java.util.List;


public class CommonResponse<T> {
    private List<Integer> pages;
    private List<T> list;

    public List<Integer> getPages() {
        return pages;
    }

    public void setPages(List<Integer> pages) {
        this.pages = pages;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
