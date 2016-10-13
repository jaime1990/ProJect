package com.leohulabb.data.HttpData;

import com.leohulabb.data.APi.RequestService;
import com.leohulabb.data.APi.SchoolBody;
import com.leohulabb.data.HttpResult;
import com.leohulabb.data.Retrofit.ApiException;
import com.leohulabb.data.Retrofit.RetrofitUtils;
import com.leohulabb.data.UniversityListDto;

import java.util.List;

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
    public Observable<List<UniversityListDto>> HttpDataToSchoolList(int pageIndex, int pageSize) {
        return service.getSchoolList(new SchoolBody("", "", "", "", pageIndex, pageSize))
                .map(new HttpResultFunc<List<UniversityListDto>>())
                .distinct()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
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

    /**
     * 用来统一处理RxCacha的结果
     */
//    private  class HttpResultFuncCcche<T> implements Func1<Reply<T>, T> {
//
//        @Override
//        public T call(Reply<T> httpResult) {
//            return httpResult.getData();
//        }
//    }
}
