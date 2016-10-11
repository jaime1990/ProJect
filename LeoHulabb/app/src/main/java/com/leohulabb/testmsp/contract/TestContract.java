package com.leohulabb.testmsp.contract;

import com.commonui.activity.base.BaseModel;
import com.commonui.activity.base.BasePresenter;
import com.leohulabb.data.TestData;

/**
 * @desc:
 * @author: Leo
 * @date: 2016/10/10
 */
public class TestContract {

    public interface View {
        void setDetail(TestData data);
        void noData();
    }

    public abstract static class Presenter extends BasePresenter<View, Model>{
        public abstract void getDetail();
    }

    public interface Model extends BaseModel {
    }
}