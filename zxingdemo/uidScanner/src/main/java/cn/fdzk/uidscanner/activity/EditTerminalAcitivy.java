//package cn.fdzk.uidscanner.activity;
//
//import android.content.Intent;
//import android.location.Location;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Spinner;
//import android.widget.TextView;
//
//import com.uuzuche.lib_zxing.activity.CaptureActivity;
//import com.uuzuche.lib_zxing.activity.CodeUtils;
//import com.yayandroid.locationmanager.LocationManager;
//import com.yayandroid.locationmanager.base.LocationBaseActivity;
//import com.yayandroid.locationmanager.configuration.DefaultProviderConfiguration;
//import com.yayandroid.locationmanager.configuration.LocationConfiguration;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//import cn.fdzk.uidscanner.R;
//import cn.fdzk.uidscanner.service.ApiService;
//import cn.fdzk.uidscanner.uitl.ServiceUtil;
//import cn.fdzk.uidscanner.uitl.ToastUtil;
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
//public class EditTerminalAcitivy extends BaseActivity {
//
//    @BindView(R.id.edit_progress)
//    View progressView;
//
//    @BindView(R.id.text_concentrator_name)
//    TextView textViewConcentratorName;
//
//    @BindView(R.id.input_name)
//    EditText inputName;
//
//    @BindView(R.id.input_uid)
//    EditText inputUID;
//
//    @BindView(R.id.input_long)
//    EditText inputLong;
//
//    @BindView(R.id.input_lat)
//    EditText inputLat;
//
//    @BindView(R.id.btn_get_location)
//    Button btnGetLocation;
//
//    @BindView(R.id.btn_start_scan)
//    Button btnStartScan;
//
//    @BindView(R.id.btn_next)
//    Button btnNext;
//
//    private static final String TAG = "EditTerminal";
//
//    private LocationManager locationManager;
//    private ApiService apiService;
//
//    private String pid;
//    private String cname;
//    private String cuid;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_edit_terminal_acitivy);
//        ButterKnife.bind(this);
//
//        pid = getIntent().getStringExtra("pid");
//        cname = getIntent().getStringExtra("cname");
//        cuid = getIntent().getStringExtra("cuid");
//
//        textViewConcentratorName.setText(cname);
//
//        LocationConfiguration configuration = new LocationConfiguration.Builder()
//                .keepTracking(false)
//                .useDefaultProviders(new DefaultProviderConfiguration.Builder()
//                        .build())
//                .build();
//
//
//        locationManager = new LocationManager.Builder(getApplicationContext())
//                .activity(this)
//                .configuration(configuration)
//                .notify(new LocationBaseActivity() {
//                    @Override
//                    public LocationConfiguration getLocationConfiguration() {
//                        return null;
//                    }
//
//                    @Override
//                    public void onLocationChanged(Location location) {
//                        handleLocationChange(location);
//                    }
//
//                    @Override
//                    public void onLocationFailed(int type) {
//                        ToastUtil.toast(EditTerminalAcitivy.this, "定位失败");
//                    }
//                })
//                .build();
//
//        locationManager.get();
//        btnGetLocation.setText("定位中");
//
//        btnGetLocation.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                locationManager.get();
//                btnGetLocation.setText("定位中");
//            }
//        });
//
//        btnStartScan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(EditTerminalAcitivy.this, CaptureActivity.class);
//                startActivityForResult(intent, 124);
//            }
//        });
//
//        btnNext.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                handleSubmit();
//            }
//        });
//
//        apiService = new ApiService();
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (requestCode == 124) {
//            if (null != data) {
//                Bundle bundle = data.getExtras();
//                if (bundle == null) {
//                    return;
//                }
//                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
//                    String result = bundle.getString(CodeUtils.RESULT_STRING);
//                    ToastUtil.toast(EditTerminalAcitivy.this, result);
//                    handleScanResult(result);
//                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
//                    ToastUtil.toast(EditTerminalAcitivy.this, "解析条形码失败");
//                }
//            }
//        }
//    }
//
//    private void handleScanResult(String result) {
//        if (result == null) {
//            return;
//        }
//
//        int splitPos = result.indexOf('-');
//        if (splitPos > 0) {
//            result = result.substring(splitPos + 1);
//        }
//
//        inputUID.setText(result);
//        inputName.setText(result);
//    }
//
//    private void handleSubmit() {
//        progressView.setVisibility(View.VISIBLE);
//        String name = inputName.getText().toString();
//        String luid = inputUID.getText().toString();
//        double lat = inputLat.getText().toString().isEmpty() ? 0 : Double.parseDouble(inputLat.getText().toString());
//        double lng = inputLong.getText().toString().isEmpty() ? 0 : Double.parseDouble(inputLong.getText().toString());
//
//        if (name.isEmpty() || cuid.isEmpty() || lat == 0 || lng == 0) {
//            progressView.setVisibility(View.GONE);
//            ToastUtil.toast(EditTerminalAcitivy.this, "表单信息不完整");
//            return;
//        }
//
//        apiService.addTerminal(pid, cuid, name, luid, lat, lng, new Callback<String>() {
//            @Override
//            public void onResponse(Call<String> call, Response<String> response) {
//                progressView.setVisibility(View.GONE);
//                String res = response.body();
//
//                int code = ServiceUtil.parseResponseForCode(res);
//
//                if (code == 5) {
//                    ToastUtil.toast(EditTerminalAcitivy.this, "请先登录");
//                    Intent intent = new Intent(EditTerminalAcitivy.this, LoginActivity.class);
//                    startActivity(intent);
//                    finish();
//                    return;
//                }
//
//                if (code != 1) {
//                    String message = ServiceUtil.parseResponseForResult(res, String.class);
//                    ToastUtil.toast(EditTerminalAcitivy.this, message);
//                    return;
//                }
//
//                ToastUtil.toast(EditTerminalAcitivy.this, "添加成功！");
//            }
//
//            @Override
//            public void onFailure(Call<String> call, Throwable t) {
//                progressView.setVisibility(View.GONE);
//                ToastUtil.showOperationError(EditTerminalAcitivy.this, TAG, t);
//            }
//        });
//    }
//
//    private void handleLocationChange(Location location) {
//        btnGetLocation.setText("定位");
//        double lat = location.getLatitude();
//        double lon = location.getLongitude();
//
//        inputLat.setText("" + lat);
//        inputLong.setText("" + lon);
//    }
//}
