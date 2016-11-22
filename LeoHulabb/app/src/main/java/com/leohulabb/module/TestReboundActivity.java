package com.leohulabb.module;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.commonui.animation.AnimationManager;
import com.leohulabb.R;

public class TestReboundActivity extends Activity {

    private LinearLayout rootview;
    private LinearLayout llFirst;
    private LinearLayout llNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_rebound);

        rootview = (LinearLayout) findViewById(R.id.rootview);
        llFirst = (LinearLayout) findViewById(R.id.ll_first);
        findViewById(R.id.btn1);
        findViewById(R.id.btn2);
        findViewById(R.id.btn3);
        findViewById(R.id.btn4);
        llNext = (LinearLayout) findViewById(R.id.ll_next);
        findViewById(R.id.btn21);
        findViewById(R.id.btn22);
        findViewById(R.id.btn23);
        findViewById(R.id.btn24);

        AnimationManager.chainAnimOrizontal(llFirst);
        AnimationManager.chainAnimOrizontal(llNext);
        AnimationManager.chainAnim(rootview);
    }
    
}
