package com.example.pc.newsapp;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pc on 2017-07-10.
 */

public class NewsBean {     //rss 구조

    private Channel channel;
    @SerializedName("@version")
    private String version;

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    class Channel{
        private String title;       //이름은 반드시 < >안에 내용과 같아야함
        private String link;
        private String description;
        private String pubDate;
        private List<Item> item;

       class Item{
            private String title;
            private String link;
            private String description;
            private String pubDate;

           public String getTitle() {
               return title;
           }

           public void setTitle(String title) {
               this.title = title;
           }

           public String getLink() {
               return link;
           }

           public void setLink(String link) {
               this.link = link;
           }

           public String getDescription() {
               return description;
           }

           public void setDescription(String description) {
               this.description = description;
           }

           public String getPubDate() {
               return pubDate;
           }

           public void setPubDate(String pubDate) {
               this.pubDate = pubDate;
           }
       }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getPubDate() {
            return pubDate;
        }

        public void setPubDate(String pubDate) {
            this.pubDate = pubDate;
        }

        public List<Item> getItem() {
            return item;
        }

        public void setItem(List<Item> item) {
            this.item = item;
        }
    }

}
