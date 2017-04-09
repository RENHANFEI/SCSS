package cn.fdzk.uidscanner.model;

/**
 * Created by konie on 2017/4/8.
 */

public class DeviceBean {

    private Integer deviceType;
    private String modelId;
    private String name;

    public Integer getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(Integer deviceType) {
        this.deviceType = deviceType;
    }

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
