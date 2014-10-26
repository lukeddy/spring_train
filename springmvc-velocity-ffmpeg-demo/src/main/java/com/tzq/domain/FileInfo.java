package com.tzq.domain;

import com.tzq.common.domain.BaseTO;
import com.tzq.util.CustomDateSerializer;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 12-7-12
 * Time: 下午4:29
 * To change this template use File | Settings | File Templates.
 */
@Table(name = "files")
@Entity
public class FileInfo extends BaseTO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private int id;
    @Column(name = "file_name")
    private String fileName;
    @Column(name = "store_id")
    private String storeId;
    @Column(name = "title")
    private String title;
    @Column(name = "subtitle")
    private String subTitle;
    @Column(name = "thumb_url")
    private String thumbUrl;
    @Column(name = "movie_url")
    private String movieUrl;
    @Column(name = "movie_store_path")
    private String movieStorePath;
    @Column(name = "thumb_store_path")
    private String thumbStorePath;
    @Column(name = "create_time")
    private Date createTime;
    @Column(name = "update_time")
    private Date updateTime;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getThumbUrl() {
        return thumbUrl;
    }

    public void setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
    }

    public String getMovieUrl() {
        return movieUrl;
    }

    public void setMovieUrl(String movieUrl) {
        this.movieUrl = movieUrl;
    }

    public String getMovieStorePath() {
        return movieStorePath;
    }

    public void setMovieStorePath(String movieStorePath) {
        this.movieStorePath = movieStorePath;
    }

    public String getThumbStorePath() {
        return thumbStorePath;
    }

    public void setThumbStorePath(String thumbStorePath) {
        this.thumbStorePath = thumbStorePath;
    }

    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "FileInfo{" +
                "id=" + id +
                ", fileName='" + fileName + '\'' +
                ", storeId='" + storeId + '\'' +
                ", title='" + title + '\'' +
                ", subTitle='" + subTitle + '\'' +
                ", thumbUrl='" + thumbUrl + '\'' +
                ", movieUrl='" + movieUrl + '\'' +
                ", movieStorePath='" + movieStorePath + '\'' +
                ", thumbStorePath='" + thumbStorePath + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
