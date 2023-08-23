package com.etz.MPB.portal.utils;


import com.etz.MPB.portal.dto.response.PageResData;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PageUtil {

    public static PageResData getPageStatistics(Integer page, Integer sise, long pageTotal, long totalElement){
        PageResData pageResData = new PageResData();
        pageResData.setFirst(page == 1 || page == null);

        pageResData.setLast(page == pageTotal);

        pageResData.setSize(sise);
        pageResData.setNumber(page);
        pageResData.setTotalPages(pageTotal);
        pageResData.setTotalElements(totalElement);
        return pageResData;
    }
}
