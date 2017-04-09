package cn.bingoogolapple.qrcode.zxingdemo;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import java.math.BigInteger;
import java.security.MessageDigest;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bingoogolapple.qrcode.zxingdemo.R;
import cn.fdzk.uidscanner.service.ApiService;
import cn.fdzk.uidscanner.uitl.ServiceUtil;
import cn.fdzk.uidscanner.uitl.ToastUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends Activity {

    private static final String TAG = "LoginActivity";
//    private static final int REQUEST_SIGNUP = 0;
//    private String user;
//    private String password;
//    private String AXWEBSID;
//
//    private static final int REQUEST_CODE_QRCODE_PERMISSIONS = 1;
//
//    private String url =
//            "http://121.40.34.92:7070/api/json?cmd=login&ctrl=user&version=1&lang=zh_CN";

    EditText editTextUsername;
    EditText editTextPassword;

//    @BindView(R.id.username_edittext) EditText editTextUsername;
//    @BindView(R.id.password_edittext) EditText editTextPassword;

    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//        ButterKnife.bind(this);
        apiService = new ApiService();
        editTextUsername = (EditText) findViewById(R.id.username_edittext);
        editTextPassword = (EditText) findViewById(R.id.password_edittext);


    }

    public void onClick_login(View v) {

//        requestLogin();
        Intent myIntent = new Intent(this, CurMapActivity.class);
        startActivity(myIntent);

    }

    public void onClick_settings(View v) {
//        requestLogin();

    }

    private void requestLogin() {
        String username = editTextUsername.getText().toString();
        String password = getMD5(editTextPassword.getText().toString());

        if (!checkInputs(username, password)) {
            return;
        }

//        progressView.setVisibility(View.VISIBLE);

        apiService.login(username, password, new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
//                progressView.setVisibility(View.GONE);
                String res = response.body();
                int code = ServiceUtil.parseResponseForCode(res);

                if (code != 1) {
                    String message = ServiceUtil.parseResponseForResult(res, String.class);
                    ToastUtil.toast(cn.bingoogolapple.qrcode.zxingdemo.LoginActivity.this, message);
                    return;
                }

                ToastUtil.toast(cn.bingoogolapple.qrcode.zxingdemo.LoginActivity.this, "登录成功");
//                Intent intent = new Intent(cn.bingoogolapple.qrcode.zxingdemo.LoginActivity.this, MainActivity.class);
//                startActivity(intent);
                Intent myIntent = new Intent(LoginActivity.this, CurMapActivity.class);
                startActivity(myIntent);
                finish();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
//                progressView.setVisibility(View.GONE);
                ToastUtil.showOperationError(cn.bingoogolapple.qrcode.zxingdemo.LoginActivity.this, TAG, t);
            }
        });
    }

    private boolean checkInputs(String username, String password) {
        if (username == null || username.trim().isEmpty()) {
            ToastUtil.toast(this, "用户名不能为空");
            return false;
        }

        if (password == null || password.trim().isEmpty()) {
            ToastUtil.toast(this, "密码不能为空");
            return false;
        }

        return true;
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


//    @Override
//    protected void onStart() {
//        super.onStart();
//        requestCodeQRCodePermissions();
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
//        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
//    }
//
//    @Override
//    public void onPermissionsGranted(int requestCode, List<String> perms) {
//    }
//
//    @Override
//    public void onPermissionsDenied(int requestCode, List<String> perms) {
//    }
//
//    @AfterPermissionGranted(REQUEST_CODE_QRCODE_PERMISSIONS)
//    private void requestCodeQRCodePermissions() {
//        String[] perms = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
//        if (!EasyPermissions.hasPermissions(this, perms)) {
//            EasyPermissions.requestPermissions(this, "扫描二维码需要打开相机和散光灯的权限", REQUEST_CODE_QRCODE_PERMISSIONS, perms);
//        }
//    }
//
//    public String getMD5(String str) {
//        try {
//            // 生成一个MD5加密计算摘要
//            MessageDigest md = MessageDigest.getInstance("MD5");
//            // 计算md5函数
//            md.update(str.getBytes());
//            // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
//            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
//            return new BigInteger(1, md.digest()).toString(16);
//        } catch (Exception e) {
//            return "";
//        }
//    }

//    /**
//     * @param url      "http://121.40.34.92:7070/api/json?cmd=login&ctrl=user&version=1&lang=zh_CN"
//     * @param user     User
//     * @param password EF4B7XXXXXXX52C7XXXXBFB64392A27
//     * @return
//     */
//    @SuppressWarnings({"deprecation", "resource"})
//    private String logon(String url, String user, String password) {
//        String axWebSID = "";
//        //登录服务器
//        HttpClient httpclient = new DefaultHttpClient();
//        // 创建cookie store的本地实例
//        CookieStore cookieStore = new BasicCookieStore();
//        // 创建本地的HTTP内容
//        HttpContext context = new BasicHttpContext();
//        //使用POST方法
//        HttpPost httpPost = new HttpPost(url);
//        HttpResponse response = null;
//        try {
//            String data = "{\"langKey\": \"zh_CN\",\"password\": \"" + password + "\",\"pkey\": null,\"pkeyMode\": false,\"remember\": false,\"user\": \"" + user + "\"}";
//            //httpPost发送的数据
//            StringEntity entity = new StringEntity(data);
//            httpPost.setEntity(entity);
//            httpPost.addHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0;Windows NT 5.1; SV1; .NET CLR 2.0.50727; CIBA)");
//            httpPost.addHeader("Content-Type", "text/plain");
//            // 设置以AJAX方式的http提交
//            httpPost.addHeader("X_REQUESTED_WITH", "XMLHttpRequest");
//            // 绑定cookie store到本地内容中
//            context.setAttribute(ClientContext.COOKIE_STORE, cookieStore);
//            response = httpclient.execute(httpPost, context); //httpclient.execute(httpPost);
//        } catch (IOException e1) {
//            // TODO 自动生成的 catch 块
//            e1.printStackTrace();
//        }
//
//        //打印服务器返回的状态
//        HttpEntity resEntity = response.getEntity();
//        try {
//            System.out.println(EntityUtils.toString(resEntity));
//            //获取Cookie中的AXWEBSID,用于WebSocket登录
//            List<Cookie> cookies = cookieStore.getCookies();
//            for (int i = 0; i < cookies.size(); i++) {
//                Cookie cookie = cookies.get(i);
//                System.out.println("====" + cookie.getName() + "=" + cookie.getValue());
//                if ("AXWEBSID".equalsIgnoreCase(cookie.getName())) {
//                    axWebSID = cookie.getValue();
//                    if (this.url.indexOf("?") > 0)
//                        this.url += "&AXWEBSID=" + axWebSID;
//                    else
//                        this.url += "?AXWEBSID" + axWebSID;
//                }
//            }
//        }  catch (IOException e) {
//            e.printStackTrace();
//        }
//        return axWebSID;
//    }
//
//
//
//

}
