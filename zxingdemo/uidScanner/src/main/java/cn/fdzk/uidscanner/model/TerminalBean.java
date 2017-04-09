package cn.fdzk.uidscanner.model;

/**
 * Created by konie on 2017/4/8.
 */

public class TerminalBean {

    private String luid;
    private String lampmodel;
    private String pid;
    private String sid;
    private String cuid;
    private Integer ctype;
    private String name;
    private Integer channel;
    private String lcumodel;
    private String kmId;
    private Double lat;
    private Double lng;
    private String dirId;

    public String getLuid() {
        return luid;
    }

    public void setLuid(String luid) {
        this.luid = luid;
    }

    public String getLampmodel() {
        return lampmodel;
    }

    public void setLampmodel(String lampmodel) {
        this.lampmodel = lampmodel;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getCuid() {
        return cuid;
    }

    public void setCuid(String cuid) {
        this.cuid = cuid;
    }

    public Integer getCtype() {
        return ctype;
    }

    public void setCtype(Integer ctype) {
        this.ctype = ctype;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getChannel() {
        return channel;
    }

    public void setChannel(Integer channel) {
        this.channel = channel;
    }

    public String getLcumodel() {
        return lcumodel;
    }

    public void setLcumodel(String lcumodel) {
        this.lcumodel = lcumodel;
    }

    public String getKmId() {
        return kmId;
    }

    public void setKmId(String kmId) {
        this.kmId = kmId;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public String getDirId() {
        return dirId;
    }

    public void setDirId(String dirId) {
        this.dirId = dirId;
    }
}
