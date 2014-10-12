package com.tang.common;

import com.sun.syndication.feed.rss.Channel;
import com.sun.syndication.feed.rss.Content;
import com.sun.syndication.feed.rss.Item;
import org.springframework.web.servlet.view.feed.AbstractRssFeedView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * RSS定制视图解析器
 */
public class RssFeedView extends AbstractRssFeedView {
    @Override
    protected void buildFeedMetadata(Map<String, Object> model, Channel feed,
                                     HttpServletRequest request) {

        feed.setTitle("Mkyong Dot Com");
        feed.setDescription("Java Tutorials and Examples");
        feed.setLink("http://www.mkyong.com");

        super.buildFeedMetadata(model, feed, request);
    }


    @Override
    protected List<Item> buildFeedItems(Map<String, Object> model,
                                        HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        @SuppressWarnings("unchecked")
        List<SampleContent> listContent = (List<SampleContent>) model.get("feedContent");
        List<Item> items = new ArrayList<Item>(listContent.size());

        for(SampleContent tempContent : listContent ){

            Item item = new Item();

            Content content = new Content();
            content.setValue(tempContent.getSummary());
            item.setContent(content);

            item.setTitle(tempContent.getTitle());
            item.setLink(tempContent.getUrl());
            item.setPubDate(tempContent.getCreatedDate());

            items.add(item);
        }

        return items;
    }
}
