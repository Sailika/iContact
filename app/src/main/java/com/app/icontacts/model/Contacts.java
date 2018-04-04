package com.app.icontacts.model;

import java.util.ArrayList;

/**
 * Created by saili on 4/3/2018.
 */

public class Contacts {

    private String total;

    private String perPage;

    private String page;

    private ArrayList<Data> data;

    private String totalPages;

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getPerPage() {
        return perPage;
    }

    public void setPerpage(String perpage) {
        this.perPage = perpage;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public ArrayList<Data> getData() {
        return data;
    }

    public void setData(ArrayList<Data> data) {
        this.data = data;
    }

    public String getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(String total_pages) {
        this.totalPages = total_pages;
    }

    @Override
    public String toString() {
        return "ClassPojo [total = " + total + ", perpage = " + perPage + ", page = " + page + ", " +
                "data = " + data + ", total_pages = " + totalPages + "]";
    }
}
