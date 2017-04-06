package ph.com.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


import ph.com.test.bean.DetailBean;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailActivity extends AppCompatActivity {

    private ImageView iv;
    private TextView content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        iv = (ImageView) findViewById(R.id.iv);
        content = (TextView) findViewById(R.id.content);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.tngou.net/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        Call<DetailBean> call = apiService.getDetailData(getIntent().getIntExtra("id", 0));
        call.enqueue(new Callback<DetailBean>() {
            @Override
            public void onResponse(Call<DetailBean> call, Response<DetailBean> response) {
                DetailBean body = response.body();
                Picasso.with(DetailActivity.this).load("http://tnfs.tngou.net/image"+body.getImg()).into(iv);
                content.setText(Html.fromHtml(body.getMessage()));
            }

            @Override
            public void onFailure(Call<DetailBean> call, Throwable t) {

            }
        });
    }
}
