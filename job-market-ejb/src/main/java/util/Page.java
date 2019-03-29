package util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Page<T> implements Serializable {
    private int pageIndex = 0;//当前第几页
    private int pageTotal = 1;//共几页
    private int countPerPage = 10;//每页多少个
    private List<T> item = new ArrayList<>(0);

    public Page() {
    }

    public Page(int pageIndex, int pageTotal, int countPerPage, List<T> item) {
        this.pageIndex = pageIndex;
        this.pageTotal = pageTotal;
        this.countPerPage = countPerPage;
        this.item = item;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(int pageTotal) {
        this.pageTotal = pageTotal;
    }

    public int getCountPerPage() {
        return countPerPage;
    }

    public void setCountPerPage(int countPerPage) {
        this.countPerPage = countPerPage;
    }

    public List<T> getItem() {
        return item;
    }

    public void setItem(List<T> item) {
        this.item = item;
    }
}
