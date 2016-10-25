package com.rsp.gankjoke.entity.news;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;

/**
 * @author 小任
 * @date 2016/10/20
 * version 1.0
 * 描述:
 */

public class NewsItem implements Parcelable {

    /**
     * author_name : 中国青年网
     * date : 2016-10-20 07:00
     * realtype : 社会
     * thumbnail_pic_s : http://05.imgmini.eastday.com/mobile/20161020/20161020070023_cd721c446098a7221b303fa025facf84_1_mwpm_03200403.jpeg
     * thumbnail_pic_s02 : http://05.imgmini.eastday.com/mobile/20161020/20161020070023_cd721c446098a7221b303fa025facf84_1_mwpl_05500201.jpeg
     * thumbnail_pic_s03 : http://05.imgmini.eastday.com/mobile/20161020/20161020070023_cd721c446098a7221b303fa025facf84_1_mwpl_05500201.jpeg
     * title : 荒唐！女会计为还房贷 请假到外地卖淫
     * type : 头条
     * uniquekey : 161020070023189
     * url : http://mini.eastday.com/mobile/161020070023189.html?qid=juheshuju
     */

    public String author_name;
    public String date;
    public String realtype;
    public String thumbnail_pic_s;
    public String thumbnail_pic_s02;
    public String thumbnail_pic_s03;
    public String title;
    public String type;
    public String uniquekey;
    public String url;
    public int height;

    public static NewsItem objectFromData(String str) {

        return new Gson().fromJson(str, NewsItem.class);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.author_name);
        dest.writeString(this.date);
        dest.writeString(this.realtype);
        dest.writeString(this.thumbnail_pic_s);
        dest.writeString(this.thumbnail_pic_s02);
        dest.writeString(this.thumbnail_pic_s03);
        dest.writeString(this.title);
        dest.writeString(this.type);
        dest.writeString(this.uniquekey);
        dest.writeString(this.url);
        dest.writeInt(this.height);
    }

    public NewsItem() {
    }

    protected NewsItem(Parcel in) {
        this.author_name = in.readString();
        this.date = in.readString();
        this.realtype = in.readString();
        this.thumbnail_pic_s = in.readString();
        this.thumbnail_pic_s02 = in.readString();
        this.thumbnail_pic_s03 = in.readString();
        this.title = in.readString();
        this.type = in.readString();
        this.uniquekey = in.readString();
        this.url = in.readString();
        this.height = in.readInt();
    }

    public static final Parcelable.Creator<NewsItem> CREATOR = new Parcelable.Creator<NewsItem>() {
        @Override
        public NewsItem createFromParcel(Parcel source) {
            return new NewsItem(source);
        }

        @Override
        public NewsItem[] newArray(int size) {
            return new NewsItem[size];
        }
    };
}
