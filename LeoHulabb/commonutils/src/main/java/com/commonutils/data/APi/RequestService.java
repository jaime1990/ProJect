package com.commonutils.data.APi;


import com.commonutils.data.BaseRespEntity;
import com.commonutils.data.BodyParama;
import com.commonutils.data.BodyParamawwww;
import com.commonutils.data.HttpResult;
import com.commonutils.data.TestListData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * API接口
 * 因为使用RxCache作为缓存策略 所以这里不需要写缓存信息
 */
public interface RequestService {

    //获取学校列表
    @POST("Colleges/getCollegeList")
    Observable<HttpResult<List<TestListData>>> getSchoolList(@Body SchoolBody schoolBody);

    @POST("ActivityService.svc/SelectNewestActivity")
    Observable<BaseRespEntity> getActivityList(@Body BodyParama bodyParama);

    @POST("ActivityService.svc/CancelActivity")
    Call<BaseRespEntity> getCode(@Body BodyParamawwww bodyParama);
}
