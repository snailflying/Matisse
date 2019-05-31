package com.theone.matisse.internal.entity;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * @Author zhiqiang
 * @Date 2019-05-31
 * @Email liuzhiqiang@moretickets.com
 * @Description
 */
public class ResultMedia implements Parcelable {

    private int width;
    private int height;
    private String path;
    private Uri uri;
    private boolean originalEnable;
    private boolean isCut;
    private int mimeType;

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }


    public boolean isCut() {
        return isCut;
    }

    public void setCut(boolean cut) {
        isCut = cut;
    }

    public boolean isOriginalEnable() {
        return originalEnable;
    }

    public void setOriginalEnable(boolean originalEnable) {
        this.originalEnable = originalEnable;
    }

    public int getMimeType() {
        return mimeType;
    }

    public void setMimeType(int mimeType) {
        this.mimeType = mimeType;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.width);
        dest.writeInt(this.height);
        dest.writeString(this.path);
        dest.writeParcelable(this.uri, flags);
        dest.writeByte(this.originalEnable ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isCut ? (byte) 1 : (byte) 0);
        dest.writeInt(this.mimeType);
    }

    public ResultMedia() {
    }

    protected ResultMedia(Parcel in) {
        this.width = in.readInt();
        this.height = in.readInt();
        this.path = in.readString();
        this.uri = in.readParcelable(Uri.class.getClassLoader());
        this.originalEnable = in.readByte() != 0;
        this.isCut = in.readByte() != 0;
        this.mimeType = in.readInt();
    }

    public static final Creator<ResultMedia> CREATOR = new Creator<ResultMedia>() {
        @Override
        public ResultMedia createFromParcel(Parcel source) {
            return new ResultMedia(source);
        }

        @Override
        public ResultMedia[] newArray(int size) {
            return new ResultMedia[size];
        }
    };
}
