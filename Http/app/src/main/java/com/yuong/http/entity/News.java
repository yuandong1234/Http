package com.yuong.http.entity;

import java.util.List;

/**
 * Created by yuandong on 2018/7/13.
 */

public class News {
    public String reason;
    public int error_code;
    public NewsData result;

    @Override
    public String toString() {
        return "News{" +
                "reason='" + reason + '\'' +
                ", error_code=" + error_code +
                ", result=" + result +
                '}';
    }

    public class NewsData{

        public  List<NewsItem> data;
        public  String stat;

        @Override
        public String toString() {
            return "NewsData{" +
                    "data=" + data +
                    ", stat='" + stat + '\'' +
                    '}';
        }
    }

    public class NewsItem{
        public String uniquekey;
        public String title;
        public String date;
        public String category;
        public String url;
        public String author_name;
        public String thumbnail_pic_s;

        @Override
        public String toString() {
            return "NewsItem{" +
                    "uniquekey='" + uniquekey + '\'' +
                    ", title='" + title + '\'' +
                    ", date='" + date + '\'' +
                    ", category='" + category + '\'' +
                    ", url='" + url + '\'' +
                    ", author_name='" + author_name + '\'' +
                    ", thumbnail_pic_s='" + thumbnail_pic_s + '\'' +
                    '}';
        }
    }
}
