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
//import android.widget.Toast;
//
//import com.uuzuche.lib_zxing.activity.CaptureActivity;
//import com.uuzuche.lib_zxing.activity.CodeUtils;
//import com.yayandroid.locationmanager.LocationManager;
//import com.yayandroid.locationmanager.base.LocationBaseActivity;
//import com.yayandroid.locationmanager.configuration.DefaultProviderConfiguration;
//import com.yayandroid.locationmanager.configuration.LocationConfiguration;
//import com.yayandroid.locationmanager.constants.ProviderType;
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
//public class EditConcentratorActivity extends BaseActivity {
//
//    @BindView(R.id.edit_progress)
//    View progressView;
//
//    @BindView(R.id.text_project_name)
//    TextView textProjectName;
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
//    @BindView(R.id.input_type)
//    Spinner inputType;
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
//    @BindView(R.id.btn_add_terminal)
//    Button btnAddTerminal;
//
//    private LocationManager locationManager;
//    private String pid;
//    private String pname;
//
//    private ApiService apiService;
//    private static final String TAG = "EditConcentrator";
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_edit_concentrator);
//        ButterKnife.bind(this);
//
//        pid = getIntent().getStringExtra("pid");
//        pname = getIntent().getStringExtra("pname");
//
//        textProjectName.setText(pname);
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
//                        ToastUtil.toast(EditConcentratorActivity.this, "定位失败");
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
//                Intent intent = new Intent(EditConcentratorActivity.this, CaptureActivity.class);
//                startActivityForResult(intent, 123);
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
//        btnAddTerminal.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                handleAddTerminal();
//            }
//        });
//
//        apiService = new ApiService();
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (requestCode == 123) {
//            if (null != data) {
//                Bundle bundle = data.getExtras();
//                if (bundle == null) {
//                    return;
//                }
//                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
//                    String result = bundle.getString(CodeUtils.RESULT_STRING);
//                    ToastUtil.toast(EditConcentratorActivity.this, result);
//                    handleScanResult(result);
//                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
//                    ToastUtil.toast(EditConcentratorActivity.this, "解析条形码失败");
//                }
//            }
//        }
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
//        String cuid = inputUID.getText().toString();
//        double lat = inputLat.getText().toString().isEmpty() ? 0 : Double.parseDouble(inputLat.getText().toString());
//        double lng = inputLong.getText().toString().isEmpty() ? 0 : Double.parseDouble(inputLong.getText().toString());
//
//        if (name.isEmpty() || cuid.isEmpty() || lat == 0 || lng == 0) {
//            progressView.setVisibility(View.GONE);
//            ToastUtil.toast(EditConcentratorActivity.this, "表单信息不完整");
//            return;
//        }
//
//        apiService.addConcentrator(pid, name, cuid, lat, lng, new Callback<String>() {
//            @Override
//            public void onResponse(Call<String> call, Response<String> response) {
//                progressView.setVisibility(View.GONE);
//                String res = response.body();
//
//                int code = ServiceUtil.parseResponseForCode(res);
//
//                if (code == 5) {
//                    ToastUtil.toast(EditConcentratorActivity.this, "请先登录");
//                    Intent intent = new Intent(EditConcentratorActivity.this, LoginActivity.class);
//                    startActivity(intent);
//                    finish();
//                    return;
//                }
//
//                if (code != 1) {
//                    String message = ServiceUtil.parseResponseForResult(res, String.class);
//                    ToastUtil.toast(EditConcentratorActivity.this, message);
//                    return;
//                }
//
//                ToastUtil.toast(EditConcentratorActivity.this, "添加成功！");
//            }
//
//            @Override
//            public void onFailure(Call<String> call, Throwable t) {
//                progressView.setVisibility(View.GONE);
//                ToastUtil.showOperationError(EditConcentratorActivity.this, TAG, t);
//            }
//        });
//    }
//
//    private void handleAddTerminal() {
//        String name = inputName.getText().toString();
//        String cuid = inputUID.getText().toString();
//
//        if (name.isEmpty() || cuid.isEmpty()) {
//            ToastUtil.toast(this, "请填写集中器UID与集中器名称，或者扫描条码");
//            return;
//        }
//
//        Intent intent = new Intent(this, EditTerminalAcitivy.class);
//        intent.putExtra("pid", pid);
//        intent.putExtra("cname", name);
//        intent.putExtra("cuid", cuid);
//        startActivity(intent);
//    }
//}
