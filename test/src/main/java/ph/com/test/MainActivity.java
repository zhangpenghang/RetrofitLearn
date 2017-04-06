package ph.com.test;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import ph.com.test.adapter.VpAdapter;
import ph.com.test.bean.ClassfyBean;
import ph.com.test.fragment.BaseFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        viewPager = (ViewPager) findViewById(R.id.viewpager);

        initData();
    }

    private void initData() {
        OkHttpClient client = new OkHttpClient.Builder().connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.tngou.net/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        ApiService apiService = retrofit.create(ApiService.class);
        Call<ClassfyBean> call = apiService.getClassfyData();
        call.enqueue(new Callback<ClassfyBean>() {
            @Override
            public void onResponse(Call<ClassfyBean> call, Response<ClassfyBean> response) {
                ClassfyBean body = response.body();
                List<BaseFragment> fragments = new ArrayList<>();
                for (int i = 0; i < body.getTngou().size(); i++) {
                    fragments.add(BaseFragment.getInstance(body.getTngou().get(i).getId()));
                }
                VpAdapter adapter = new VpAdapter(getSupportFragmentManager(), fragments, body);
                viewPager.setAdapter(adapter);
                tabLayout.setupWithViewPager(viewPager);
            }

            @Override
            public void onFailure(Call<ClassfyBean> call, Throwable throwable) {

            }
        });
    }
}
