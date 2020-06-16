package com.snowcat.search.listener;

import com.snowcat.mapper.TbItemMapper;
import com.snowcat.pojo.SearchItemResult;
import com.snowcat.search.service.ItemSearchService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class MyMessageListener implements MessageListener {
    @Autowired
    private TbItemMapper tbItemMapper;

    @Autowired
    private ItemSearchService itemSearchService;

    @Override
    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage)message;
        try {
            String id = textMessage.getText();
            System.out.println(id+"======>");
            SearchItemResult itemResult = tbItemMapper.getItemById(Long.valueOf(id));
            System.out.println(itemResult);
            itemSearchService.addSearchItem(itemResult);

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
