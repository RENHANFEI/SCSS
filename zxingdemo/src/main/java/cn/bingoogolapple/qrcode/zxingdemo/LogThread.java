package cn.bingoogolapple.qrcode.zxingdemo;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.List;

/**
 * Created by renhanfei on 17/4/7.
 */

public class LogThread extends Thread {

    private String url =
            "http://121.40.34.92:7070/api/json?cmd=login&ctrl=user&version=1&lang=zh_CN";

    public String loginResult;
    private String user;
    private String pwd;

    public LogThread(String username, String password) {
        this.user = username;
        this.pwd = password;
    }

    public void run() {
        String pwdMD5 = getMD5(pwd);
        loginResult = logon(url, user, pwdMD5);

    }

    public String getMD5(String str) {
        try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(str.getBytes());
            // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
            return "";
        }
    }

    //    /**
//     * @param url      "http://121.40.34.92:7070/api/json?cmd=login&ctrl=user&version=1&lang=zh_CN"
//     * @param user     User
//     * @param password EF4B7XXXXXXX52C7XXXXBFB64392A27
//     * @return
//     */
    @SuppressWarnings({"deprecation", "resource"})
    private String logon(String url, String user, String password) {
        String axWebSID = "";
        //登录服务器
        HttpClient httpclient = new DefaultHttpClient();
        // 创建cookie store的本地实例
        CookieStore cookieStore = new BasicCookieStore();
        // 创建本地的HTTP内容
        HttpContext context = new BasicHttpContext();
        //使用POST方法
        HttpPost httpPost = new HttpPost(url);
        HttpResponse response = null;
        try {
            String data = "{\"langKey\": \"zh_CN\",\"password\": \"" + password + "\",\"pkey\": null,\"pkeyMode\": false,\"remember\": false,\"user\": \"" + user + "\"}";
            //httpPost发送的数据
            StringEntity entity = new StringEntity(data);
            httpPost.setEntity(entity);
            httpPost.addHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0;Windows NT 5.1; SV1; .NET CLR 2.0.50727; CIBA)");
            httpPost.addHeader("Content-Type", "text/plain");
            // 设置以AJAX方式的http提交
            httpPost.addHeader("X_REQUESTED_WITH", "XMLHttpRequest");
            // 绑定cookie store到本地内容中
            context.setAttribute(ClientContext.COOKIE_STORE, cookieStore);
            response = httpclient.execute(httpPost, context); //httpclient.execute(httpPost);
        } catch (IOException e1) {
            // TODO 自动生成的 catch 块
            e1.printStackTrace();
        }

        //打印服务器返回的状态
        HttpEntity resEntity = response.getEntity();
        try {
            System.out.println(EntityUtils.toString(resEntity));
            //获取Cookie中的AXWEBSID,用于WebSocket登录
            List<Cookie> cookies = cookieStore.getCookies();
            for (int i = 0; i < cookies.size(); i++) {
                Cookie cookie = cookies.get(i);
                System.out.println("====" + cookie.getName() + "=" + cookie.getValue());
                if ("AXWEBSID".equalsIgnoreCase(cookie.getName())) {
                    axWebSID = cookie.getValue();
                    if (this.url.indexOf("?") > 0)
                        this.url += "&AXWEBSID=" + axWebSID;
                    else
                        this.url += "?AXWEBSID" + axWebSID;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return axWebSID;
    }


}
