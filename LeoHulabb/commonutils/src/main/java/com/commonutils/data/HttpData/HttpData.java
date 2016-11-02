package com.commonutils.data.HttpData;

import com.commonutils.LogUtils;
import com.commonutils.data.APi.RequestService;
import com.commonutils.data.APi.SchoolBody;
import com.commonutils.data.BaseRespEntity;
import com.commonutils.data.BodyParama;
import com.commonutils.data.BodyParamawwww;
import com.commonutils.data.HttpResult;
import com.commonutils.data.Retrofit.ApiException;
import com.commonutils.data.Retrofit.RetrofitUtils;
import com.commonutils.data.TestListData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/*
 *所有的请求数据的方法集中地
 * 根据MovieService的定义编写合适的方法
 */
public class HttpData extends RetrofitUtils
{
    private static final RequestService service = getRetrofit().create(RequestService.class);

    //在访问HttpMethods时创建单例
    private static class SingletonHolder {
        private static final HttpData INSTANCE = new HttpData();
    }

    //获取单例
    public static HttpData getInstance(){
        return SingletonHolder.INSTANCE;
    }

    /**
     * 请求学校列表
     * @param pageIndex 页序号
     * @param pageSize  页大小
     * @return 事件源Observable
     */
    public Observable<List<TestListData>> HttpDataToSchoolList(int pageIndex, int pageSize) {
        return service.getSchoolList(new SchoolBody("", "", "", "", pageIndex, pageSize))
                .map(new HttpResultFunc<List<TestListData>>())
                .distinct()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<String> HttpDataActivityList(int pageIndex, int pageSize) {
        return service.getActivityList(new BodyParama(pageIndex, pageSize, "hula"))
                .map(new HttpBaseResultFunc<String>())
                .distinct()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public void getCode(int pageIndex, int pageSize)
    {
        Call<BaseRespEntity> httpGet = service.getCode(new BodyParamawwww("Acti100000512", "8026eb160a6f4e1ca652c5133e9410e1"));

        httpGet.enqueue(new Callback<BaseRespEntity>() {
            @Override
            public void onResponse(Call<BaseRespEntity> call, Response<BaseRespEntity> response) {
                LogUtils.e("TAG", response.body().toString());
            }

            @Override
            public void onFailure(Call<BaseRespEntity> call, Throwable t) {
                LogUtils.e("TAG", t.toString());
            }
        });
    }

    /**
     * 用来统一处理Http的resultCode,并将HttpResult的Data部分剥离出来返回给subscriber
     *
     * @param <T>   Subscriber真正需要的数据类型，也就是Data部分的数据类型
     */
    private class HttpResultFunc<T> implements Func1<HttpResult<T>, T> {

        @Override
        public T call(HttpResult<T> httpResult) {

            if (httpResult.getCode() != 1) {
                throw new ApiException(httpResult);
            }

            return httpResult.getResults();
        }
    }

    private class HttpBaseResultFunc<T> implements Func1<BaseRespEntity, T> {

        @Override
        public T call(BaseRespEntity httpResult) {

//            if (httpResult.getResponseCode() != 1) {
//                throw new ApiException(httpResult);
//            }

            return (T) httpResult.getD().getExtInfo();
        }
    }
}
