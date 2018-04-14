package com.siemens.allkindsoftest.sqlScripts;

/**
 * Created with IntelliJ IDEA.
 * User: Simon
 * Date:
 * Time:
 */
public class TrackAudioTranscode {

    private String trackId;
    private String trackNode;
    private String audioId;
    private String video_online_hd;
    private String video_online_sd;
    private int video_online_hd_size;
    private int video_online_sd_size;
    private String video_download_hd;
    private String video_download_sd;
    private int video_download_hd_size;
    private int video_download_sd_size;

    public String getTrackId() {
        return trackId;
    }

    public void setTrackId(String trackId) {
        this.trackId = trackId;
    }

    public String getAudioId() {
        return audioId;
    }

    public void setAudioId(String audioId) {
        this.audioId = audioId;
    }

    public String getVideo_online_hd() {
        return video_online_hd;
    }

    public void setVideo_online_hd(String video_online_hd) {
        this.video_online_hd = video_online_hd;
    }

    public String getVideo_online_sd() {
        return video_online_sd;
    }

    public void setVideo_online_sd(String video_online_sd) {
        this.video_online_sd = video_online_sd;
    }

    public String getVideo_download_hd() {
        return video_download_hd;
    }

    public void setVideo_download_hd(String video_download_hd) {
        this.video_download_hd = video_download_hd;
    }

    public String getVideo_download_sd() {
        return video_download_sd;
    }

    public void setVideo_download_sd(String video_download_sd) {
        this.video_download_sd = video_download_sd;
    }

    public String getTrackNode() {
        return trackNode;
    }

    public void setTrackNode(String trackNode) {
        this.trackNode = trackNode;
    }

    public Integer getVideo_online_hd_size() {
        return video_online_hd_size;
    }

    public void setVideo_online_hd_size(int video_online_hd_size) {
        this.video_online_hd_size = video_online_hd_size;
    }

    public Integer getVideo_online_sd_size() {
        return video_online_sd_size;
    }

    public void setVideo_online_sd_size(int video_online_sd_size) {
        this.video_online_sd_size = video_online_sd_size;
    }

    public Integer getVideo_download_hd_size() {
        return video_download_hd_size;
    }

    public void setVideo_download_hd_size(int video_download_hd_size) {
        this.video_download_hd_size = video_download_hd_size;
    }

    public Integer getVideo_download_sd_size() {
        return video_download_sd_size;
    }

    public void setVideo_download_sd_size(int video_download_sd_size) {
        this.video_download_sd_size = video_download_sd_size;
    }
}
