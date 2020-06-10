package com.snowcat.service;

import com.snowcat.pojo.*;

import java.util.List;

public interface ContentService {
    List<ContentZtreeResult> showList(Long id);

    LayuiResult showTable(Integer page, Integer limit, Long categoryId);
    ElementResult showElement(Long categoryId);

    LayuiResult deleteContent(List<Long> id);

    LayuiResult addContent(TbContent tbContent);
}
