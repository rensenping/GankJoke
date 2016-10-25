package com.rsp.gankjoke.entity.gank;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;

import java.util.List;

/**
 * @author 小任
 * @date 2016/10/19
 * version 1.0
 * 描述:
 */

public class GankItem implements Parcelable {

    /**
     * _id : 5800a66b421aa95dd351b12e
     * createdAt : 2016-10-14T17:33:31.111Z
     * desc : 自定义 自动补充 email 的 EditText
     * images : ["http://img.gank.io/3ba627c7-23d2-4476-8527-7d872b608de1"]
     * publishedAt : 2016-10-18T11:50:35.205Z
     * source : web
     * type : Android
     * url : https://github.com/wangshaolei/AutoFillEmailEditText
     * used : true
     * who : fearless
     */

    public String _id;
    public String createdAt;
    public String desc;
    public String publishedAt;
    public String source;
    public String type;
    public String url;
    public int height;
    public boolean used;
    public String who;
    public List<String> images;

    public static GankItem objectFromData(String str) {

        return new Gson().fromJson(str, GankItem.class);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this._id);
        dest.writeString(this.createdAt);
        dest.writeString(this.desc);
        dest.writeString(this.publishedAt);
        dest.writeString(this.source);
        dest.writeString(this.type);
        dest.writeString(this.url);
        dest.writeByte(used ? (byte) 1 : (byte) 0);
        dest.writeString(this.who);
        dest.writeStringList(this.images);
    }

    public GankItem() {
    }

    protected GankItem(Parcel in) {
        this._id = in.readString();
        this.createdAt = in.readString();
        this.desc = in.readString();
        this.publishedAt = in.readString();
        this.source = in.readString();
        this.type = in.readString();
        this.url = in.readString();
        this.used = in.readByte() != 0;
        this.who = in.readString();
        this.images = in.createStringArrayList();
    }

    public static final Parcelable.Creator<GankItem> CREATOR = new Parcelable.Creator<GankItem>() {
        @Override
        public GankItem createFromParcel(Parcel source) {
            return new GankItem(source);
        }

        @Override
        public GankItem[] newArray(int size) {
            return new GankItem[size];
        }
    };
}
