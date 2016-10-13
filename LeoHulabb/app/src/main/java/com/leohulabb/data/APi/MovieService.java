package com.leohulabb.data.APi;

import com.leohulabb.data.HttpResult;
import com.leohulabb.data.UniversityListDto;
import com.leohulabb.data.VideoListDto;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * API接口
 * 因为使用RxCache作为缓存策略 所以这里不需要写缓存信息
 */
public interface MovieService {

    //获取视频地址
    @GET("classroom/getHotVideos")
    Observable<HttpResult<List<VideoListDto>>> getVideoList(@Query("pageIndex") int pageIndex, @Query("pageSize") int pageSize);

    //获取学校列表
    @POST("Colleges/getCollegeList")
    Observable<HttpResult<List<UniversityListDto>>> getSchoolList(@Body SchoolBody schoolBody);
}
