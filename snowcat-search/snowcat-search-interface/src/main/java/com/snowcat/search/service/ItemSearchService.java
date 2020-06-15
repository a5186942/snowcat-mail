package com.snowcat.search.service;

import com.snowcat.pojo.ExecuteResult;
import com.snowcat.pojo.LayuiResult;
import com.snowcat.pojo.SearchItemResult;
import com.snowcat.pojo.SearchResult;

import java.util.List;

public interface ItemSearchService {

    ExecuteResult importSolr();

    SearchResult search(String query,Integer page);
}
