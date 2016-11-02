package com.commonutils.data;

/**
 * Created by js on 2015/8/12.
 */
public class BaseRespBean<T>
{
    private Boolean IsSuccess;
    private Boolean NextPage;
    private String ErrorMsg;
    private T ExtInfo;
    private String __type;
    private Long ResponseCode;

    public String get__type() {
        return __type;
    }

    public void set__type(String __type) {
        this.__type = __type;
    }

    public T getExtInfo() {
        return ExtInfo;
    }

    public void setExtInfo(T extInfo) {
        ExtInfo = extInfo;
    }

    public Boolean getNextPage() {
        return NextPage;
    }

    public void setNextPage(Boolean nextPage) {
        NextPage = nextPage;
    }

    public Boolean getIsSuccess() {
        return IsSuccess;
    }

    public void setIsSuccess(Boolean isSuccess) {
        IsSuccess = isSuccess;
    }

    public String getErrorMsg() {
        return ErrorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        ErrorMsg = errorMsg;
    }

    public Long getResponseCode() {
        return ResponseCode;
    }

    public void setResponseCode(Long responseCode) {
        ResponseCode = responseCode;
    }
}
