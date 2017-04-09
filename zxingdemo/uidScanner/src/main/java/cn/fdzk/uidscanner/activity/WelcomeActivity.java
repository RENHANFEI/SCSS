//package cn.fdzk.uidscanner.activity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//import cn.fdzk.uidscanner.R;
//
//public class WelcomeActivity extends BaseActivity {
//
//    @BindView(R.id.btn_login)
//    Button btnLogin;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_welcome);
//        ButterKnife.bind(this);
//
//        btnLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
//                startActivity(intent);
//            }
//        });
//    }
//}
