//package cn.fdzk.uidscanner.activity;
//
//import android.content.Intent;
//import android.os.Debug;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.view.MotionEvent;
//import android.view.View;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Map;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//import cn.fdzk.uidscanner.R;
//import cn.fdzk.uidscanner.model.ProjectBean;
//import cn.fdzk.uidscanner.service.ApiService;
//import cn.fdzk.uidscanner.uitl.LogUtil;
//import cn.fdzk.uidscanner.uitl.ServiceUtil;
//import cn.fdzk.uidscanner.uitl.ToastUtil;
//import cn.fdzk.uidscanner.view.ProjectRecycleViewAdapter;
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
//public class MainActivity extends AppCompatActivity {
//
//    @BindView(R.id.rc_project_list)
//    RecyclerView recyclerView;
//
//    private ApiService apiService;
//
//    private static final String TAG = "PROJECT_LIST";
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        ButterKnife.bind(this);
//
//        apiService = new ApiService();
//
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
//        recyclerView.setLayoutManager(layoutManager);
//        ProjectRecycleViewAdapter adapter = new ProjectRecycleViewAdapter(new ArrayList<ProjectBean>());
//        adapter.setOnItemClickListener(new ProjectRecycleViewAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
//                ProjectBean projectBean = (ProjectBean) view.getTag();
//
//                Intent intent = new Intent(MainActivity.this,EditConcentratorActivity.class);
//                intent.putExtra("pid", projectBean.getId());
//                intent.putExtra("pname", projectBean.getName());
//                startActivity(intent);
//            }
//        });
//        recyclerView.setAdapter(adapter);
//
//        requestProjectList();
//    }
//
//    private void requestProjectList() {
//        apiService.getProjectList(new Callback<String>() {
//            @Override
//            public void onResponse(Call<String> call, Response<String> response) {
//                String res = response.body();
//                int code = ServiceUtil.parseResponseForCode(res);
//
//                if (code == 5) {
//                    ToastUtil.toast(MainActivity.this, "请先登录");
//                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
//                    startActivity(intent);
//                    finish();
//                    return;
//                }
//
//                if (code != 1) {
//                    String message = ServiceUtil.parseResponseForResult(res, String.class);
//                    ToastUtil.toast(MainActivity.this, message);
//                    return;
//                }
//
//                List<Map<String, Object>> result = ServiceUtil.parseResponseForResult(res, List.class);
//
//                List<ProjectBean> data = new ArrayList<>();
//                for (Map<String, Object> item: result) {
//                    ProjectBean bean = new ProjectBean();
//                    bean.setId((String) item.get("projectId"));
//                    bean.setName((String) item.get("name"));
//                    data.add(bean);
//                }
//
//                setList(data);
//            }
//
//            @Override
//            public void onFailure(Call<String> call, Throwable t) {
//                ToastUtil.showFetchError(MainActivity.this, TAG, t);
//            }
//        });
//    }
//
//    private void setList(List<ProjectBean> data) {
//        ProjectRecycleViewAdapter adapter = (ProjectRecycleViewAdapter) recyclerView.getAdapter();
//        adapter.setData(data);
//    }
//}
