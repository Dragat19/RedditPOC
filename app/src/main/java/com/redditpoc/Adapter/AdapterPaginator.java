package com.redditpoc.Adapter;
import java.util.ArrayList;

/**
 * Created by levaa on 6/8/2017.
 */

public class AdapterPaginator {
    public static final int TOTAL_ITEMS = 49;
    public static final int ITEMS_PAGE = 10;
    public static final int ITEMS_REMAINING = TOTAL_ITEMS % ITEMS_PAGE;
    public static final int ITEMS_LAST_PAGE = TOTAL_ITEMS / ITEMS_PAGE;

    public ArrayList<Integer> generatePage(int currentPage) {
       int startItem = currentPage * ITEMS_PAGE;
       int numOfData = ITEMS_PAGE;
        ArrayList<Integer> pageData = new ArrayList<>();

        if (currentPage == ITEMS_LAST_PAGE && ITEMS_REMAINING >0){
            for (int i= startItem ; i<startItem+ITEMS_REMAINING ; i++) {
                pageData.add(i);
            }
        }else {
            for (int i= startItem ; i<startItem+numOfData ; i++) {
                pageData.add(i);
            }
        }
        return pageData;
    }
}
