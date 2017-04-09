//package cn.fdzk.uidscanner.activity;
//
//import android.animation.Animator;
//import android.animation.AnimatorListenerAdapter;
//import android.annotation.TargetApi;
//import android.content.Intent;
//import android.content.pm.PackageManager;
//import android.support.annotation.NonNull;
//import android.support.design.widget.Snackbar;
//import android.support.v7.app.AppCompatActivity;
//import android.app.LoaderManager.LoaderCallbacks;
//
//import android.content.CursorLoader;
//import android.content.Loader;
//import android.database.Cursor;
//import android.net.Uri;
//import android.os.AsyncTask;
//
//import android.os.Build;
//import android.os.Bundle;
//import android.provider.ContactsContract;
//import android.text.TextUtils;
//import android.view.KeyEvent;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.view.inputmethod.EditorInfo;
//import android.widget.ArrayAdapter;
//import android.widget.AutoCompleteTextView;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//import cn.fdzk.uidscanner.R;
//import cn.fdzk.uidscanner.service.ApiService;
//import cn.fdzk.uidscanner.uitl.LogUtil;
//import cn.fdzk.uidscanner.uitl.ServiceUtil;
//import cn.fdzk.uidscanner.uitl.ToastUtil;
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
//import static android.Manifest.permission.READ_CONTACTS;
//
///**
// * A login screen that offers login via email/password.
// */
//public class LoginActivity extends BaseActivity {
//
//    @BindView(R.id.username) EditText editTextUsername;
//    @BindView(R.id.password) EditText editTextPassword;
//    @BindView(R.id.login_progress) View progressView;
//    @BindView(R.id.email_sign_in_button) Button btnLogin;
//
//    private ApiService apiService;
//    private static final String TAG = "LOGIN";
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);
//        ButterKnife.bind(this);
//
//        btnLogin.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                requestLogin();
//            }
//        });
//
//        apiService = new ApiService();
//    }
//
//    private void requestLogin() {
//        String username = editTextUsername.getText().toString();
//        String password = editTextPassword.getText().toString();
//
//        if (!checkInputs(username, password)) {
//            return;
//        }
//
//        progressView.setVisibility(View.VISIBLE);
//
//        apiService.login(username, password, new Callback<String>() {
//            @Override
//            public void onResponse(Call<String> call, Response<String> response) {
//                progressView.setVisibility(View.GONE);
//                String res = response.body();
//                int code = ServiceUtil.parseResponseForCode(res);
//
//                if (code != 1) {
//                    String message = ServiceUtil.parseResponseForResult(res, String.class);
//                    ToastUtil.toast(LoginActivity.this, message);
//                    return;
//                }
//
//                ToastUtil.toast(LoginActivity.this, "登录成功");
//                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                startActivity(intent);
//                finish();
//            }
//
//            @Override
//            public void onFailure(Call<String> call, Throwable t) {
//                progressView.setVisibility(View.GONE);
//                ToastUtil.showOperationError(LoginActivity.this, TAG, t);
//            }
//        });
//    }
//
//    private boolean checkInputs(String username, String password) {
//        if (username == null || username.trim().isEmpty()) {
//            ToastUtil.toast(this, "用户名不能为空");
//            return false;
//        }
//
//        if (password == null || password.trim().isEmpty()) {
//            ToastUtil.toast(this, "密码不能为空");
//            return false;
//        }
//
//        return true;
//    }
//}
//
