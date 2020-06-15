package com.snowcat.search.service.impl;

import com.snowcat.mapper.TbItemMapper;
import com.snowcat.pojo.ExecuteResult;
import com.snowcat.pojo.LayuiResult;
import com.snowcat.pojo.SearchItemResult;
import com.snowcat.pojo.SearchResult;
import com.snowcat.search.service.ItemSearchService;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service

public class ItemSearchServiceImpl implements ItemSearchService {
    @Autowired
    private TbItemMapper tbItemMapper;

    @Autowired
    private SolrServer solrServer;


    @Override
    public ExecuteResult importSolr() {

            try {
                List<SearchItemResult> list =  tbItemMapper.searchItemAll();
                for (SearchItemResult result:list){
                    SolrInputDocument document = new SolrInputDocument();
                    document.addField("id", result.getId());
                    document.addField("item_title", result.getTitle());
                    document.addField("item_sell_point", result.getSellPoint());
                    document.addField("item_price", result.getPrice());
                    document.addField("item_image", result.getImage());
                    document.addField("item_category_name", result.getCategoryName());
                    document.addField("item_desc", result.getItemDesc());
                    solrServer.add(document);}
                solrServer.commit();
                return  ExecuteResult.build(200,"初始化成功");
            } catch (SolrServerException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return ExecuteResult.build(500,"初始化失败");
        }

    @Override
    public SearchResult search(String query, Integer page) {
        SearchResult searchResult = new SearchResult();


        try {


            SolrQuery solrQuery = new SolrQuery();
            solrQuery.setQuery(query);
            solrQuery.set("df","item_keywords");
            solrQuery.setHighlight(true);
            solrQuery.addHighlightField("item_title");
            solrQuery.setHighlightSimplePre("<font style ='color:red' ");
            solrQuery.setHighlightSimplePost("</font>");
            solrQuery.setStart((page-1)*60);
            solrQuery.setRows(60);


            QueryResponse response = solrServer.query(solrQuery);
            SolrDocumentList results = response.getResults();
            int counts = (int)results.getNumFound();
            searchResult.setTotalCounts(counts);
            int pages = (counts%60)==0?(counts/60):(counts/60+1);
            searchResult.setTotalPages(pages);

            List<SearchItemResult> list = new ArrayList<>();

            for(SolrDocument document  : results){
                SearchItemResult itemResult = new SearchItemResult();
                itemResult.setId((String) document.get("id"));
                itemResult.setCategoryName((String) document.get("item_category_name"));
                itemResult.setImage((String) document.get("item_image"));
                itemResult.setPrice((long) document.get("item_price"));
                itemResult.setSellPoint((String) document.get("item_sell_point"));


                String itemTitle = "";

                Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
                List<String> strings = highlighting.get(document.get("id")).get("item_title");
                if(strings!=null&&strings.size()>0){
                    itemTitle=strings.get(0);
                }else {
                    itemTitle= (String) document.get("item_title");
                }
                itemResult.setTitle(itemTitle);

                list.add(itemResult);


            }

            searchResult.setSearchItemResults(list);

            return searchResult;

        } catch (SolrServerException e) {
            e.printStackTrace();
        }

        return null;
    }



}

