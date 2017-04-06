package ph.com.test;


import ph.com.test.bean.CateItemBean;
import ph.com.test.bean.ClassfyBean;
import ph.com.test.bean.DetailBean;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by 张鹏航 on 2017/4/5.
 */

public interface ApiService {
    //获取TabLayout上的数据
    @GET("api/lore/classify")
    Call<ClassfyBean> getClassfyData();

    //获取首页列表的数据
    //如果是POST请求，需要添加上面的注解，表示以表单的方式来提交数据111
    @FormUrlEncoded
    @POST("api/lore/list")
    Call<CateItemBean> getListData(@Field("id") int id);

    //获取详情数据
    @GET("api/lore/show")
    Call<DetailBean> getDetailData(@Query("id") int id);

    @GET("api/{id}/delete")
    Call<DetailBean> getData(@Path("id")String id);

}
