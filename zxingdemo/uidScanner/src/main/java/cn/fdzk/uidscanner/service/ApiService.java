package cn.fdzk.uidscanner.service;

import java.util.Arrays;

import cn.fdzk.uidscanner.api.Api;
import cn.fdzk.uidscanner.model.ConcentratorBean;
import cn.fdzk.uidscanner.model.DeviceBean;
import cn.fdzk.uidscanner.model.LoginBean;
import cn.fdzk.uidscanner.model.TerminalBean;
import cn.fdzk.uidscanner.uitl.ServiceUtil;
import retrofit2.Callback;

/**
 * Created by konie on 2017/4/8.
 */

public class ApiService {

    public void login(String username, String password, Callback<String> callback) {
        Api api = ServiceUtil.createRestService(Api.class);

        LoginBean loginBean = new LoginBean();
        loginBean.setUser(username);
        loginBean.setPassword(password);

        api.login(loginBean).enqueue(callback);
    }

    public void getProjectList(Callback<String> callback) {
        Api api = ServiceUtil.createRestService(Api.class);

        String body = "{\"wheres\":[{\"k\":\"enabled\",\"o\":\"=\",\"v\":true}],\"orders\":[{\"k\":\"name\",\"v\":\"ASC\"}]}";

        api.getProjectList(body).enqueue(callback);
    }

    public void addConcentrator(String pid, String name, String cuid, double lat, double lng, Callback<String> callback) {
        DeviceBean deviceBean = new DeviceBean();
        deviceBean.setDeviceType(2);
        deviceBean.setModelId("d3396ad8c6a7495aab79438793e43ef6");
        deviceBean.setName("Built-in METER");

        ConcentratorBean concentratorBean = new ConcentratorBean();
        concentratorBean.setPid(pid);
        concentratorBean.setName(name);
        concentratorBean.setCuid(cuid);
        concentratorBean.setCtype(1);
        concentratorBean.setCmodel("ecabc59d325942e6b695ff29486ce7f6");
        concentratorBean.setDevices(Arrays.asList(deviceBean));
        concentratorBean.setDirId("");
        concentratorBean.setLat(lat);
        concentratorBean.setLng(lng);

        ServiceUtil serviceUtil = new ServiceUtil();

        Api api = serviceUtil.createRestService(Api.class);

        api.addConcentrator(concentratorBean).enqueue(callback);
    }

    public void addTerminal(String pid, String cuid, String name, String luid, double lat, double lng, Callback<String> callback) {
        TerminalBean terminalBean = new TerminalBean();
        terminalBean.setLuid(luid);
        terminalBean.setLampmodel("747234154f144ebe91262062c6ae73c4");
        terminalBean.setPid(pid);
        terminalBean.setSid("8f70621b7a0740e6ae92c3c449f3040e");
        terminalBean.setCuid(cuid);
        terminalBean.setCtype(1);
        terminalBean.setName(name);
        terminalBean.setChannel(1);
        terminalBean.setLcumodel("c00c5b9a48a24761accfa82ff70d0d53");
        terminalBean.setLat(lat);
        terminalBean.setLng(lng);
        terminalBean.setDirId("");

        Api api = ServiceUtil.createRestService(Api.class);
        api.addTerminal(terminalBean).enqueue(callback);
    }

}
