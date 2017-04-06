package ph.com.test.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;



import java.util.ArrayList;

import ph.com.test.ApiService;
import ph.com.test.DetailActivity;
import ph.com.test.R;
import ph.com.test.adapter.LvAdapter;
import ph.com.test.bean.CateItemBean;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by qf on 16/8/7.
 */
public class BaseFragment extends Fragment {

    private ListView listView;
    private CateItemBean cateItemBean;
    private LvAdapter adapter;

    public static BaseFragment getInstance(int id) {
        BaseFragment baseFragment = new BaseFragment();
        Bundle args = new Bundle();
        args.putInt("id", id);
        baseFragment.setArguments(args);
        return baseFragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fg_layout, container, false);
        listView = (ListView) view.findViewById(R.id.lv);
        cateItemBean = new CateItemBean();
        adapter = new LvAdapter(getActivity(), cateItemBean);
        cateItemBean.setTngou(new ArrayList<CateItemBean.TngouBean>());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra("id", cateItemBean.getTngou().get(i).getId());
                startActivity(intent);
            }
        });
        initData();
        return view;
    }

    private void initData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.tngou.net/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        Call<CateItemBean> call = apiService.getListData(getArguments().getInt("id"));
        call.enqueue(new Callback<CateItemBean>() {
            @Override
            public void onResponse(Call<CateItemBean> call, Response<CateItemBean> response) {
                cateItemBean.setTngou(response.body().getTngou());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<CateItemBean> call, Throwable t) {

            }
        });
    }
}
