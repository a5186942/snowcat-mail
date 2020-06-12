package com.snowcat.search.service.impl;

import com.snowcat.mapper.TbItemMapper;
import com.snowcat.pojo.ExecuteResult;
import com.snowcat.pojo.LayuiResult;
import com.snowcat.pojo.SearchItemResult;
import com.snowcat.search.service.ItemSearchService;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
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


    }

