package com.etz.MPB.portal.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;




@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResData {

    private long number;
    private long size;
    private long totalElements;
    private long totalPages;
    private boolean last;
    private boolean first;
    private List<?> content;
}