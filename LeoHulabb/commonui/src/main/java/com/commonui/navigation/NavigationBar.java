package com.commonui.navigation;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Spanned;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.commonui.R;
import com.commonutils.SizeUtils;
import com.commonutils.ViewUtils;


/**
 * @desc:         基础导航栏
 * @author:       Leo
 * @date:         2016/9/29
 */
public class NavigationBar extends RelativeLayout
{
    private RelativeLayout m_rlLeftMenuBar;        //左边多个按钮
    private RelativeLayout m_rlCenterMenuBar;      //中间多个按钮
    private RelativeLayout m_rlRightMenuBar;       //右边多个按钮
    private FrameLayout m_flRightMenuBar;          //右边多个按钮
    private FrameLayout m_flCenterTitle;           //标题布局

    private LinearLayout m_llTitleContent;         //标题内容布局

    private TextView tvTitle;                      //标题文本框
    private TextView tvContentRight;               //右单个按钮
    private TextView tvContentLeft;                //右单个按钮
    private ImageView ivBg;                        //背景色

    private TextView tvConnecting;                 //加载view

    public NavigationBar(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        inflateContextView(context);

        tvTitle = (TextView) findViewById(R.id.tvTitle);
        ivBg = (ImageView) findViewById(R.id.ivBg);
        m_rlLeftMenuBar = (RelativeLayout) findViewById(R.id.rlLeftMenuBar);
        m_rlCenterMenuBar = (RelativeLayout) findViewById(R.id.rlCenterMenuBar);
        m_rlRightMenuBar = (RelativeLayout) findViewById(R.id.rlRightMenuBar);
        m_flRightMenuBar = (FrameLayout) findViewById(R.id.flRightMenuBar);
        m_llTitleContent = (LinearLayout) findViewById(R.id.llTitleContent);

        tvContentRight = (TextView) findViewById(R.id.tvContentRight);
        tvContentLeft = (TextView) findViewById(R.id.tvContentLeft);
        tvConnecting = (TextView) findViewById(R.id.tvConnecting);

        m_flCenterTitle = (FrameLayout) findViewById(R.id.flCenterTitle);
    }

    /**
     * 设置双击title事件
     * @param listener   双击监听
     */
    public void setTitleListener(ViewUtils.OnDoubleClickListener listener)
    {
        m_flCenterTitle.setVisibility(VISIBLE);

        ViewUtils.registerDoubleClickListener(m_flCenterTitle, listener);
    }

    /**
     * 双击title列表滚动到顶部
     * @param listView 列表
     */
    private void setTitleListener(final ListView listView)
    {
        m_flCenterTitle.setVisibility(VISIBLE);

        ViewUtils.registerDoubleClickListener(m_flCenterTitle, new ViewUtils.OnDoubleClickListener() {
            @Override
            public void OnSingleClick(View v) {

            }

            @Override
            public void OnDoubleClick(View v) {
                listView.setSelection(0);
            }
        });
    }

    public ImageView getBgImageView()
    {
        return ivBg;
    }

    public void setBgImageView(int color)
    {
        ivBg.setImageResource(color);
    }

    public TextView getConnectingTextView()
    {
        return tvConnecting;
    }

    public TextView getTitleView()
    {
        return tvTitle;
    }

    public RelativeLayout getRightMenuBar()
    {
        return m_rlRightMenuBar;
    }

    public FrameLayout getFlRightMenuBar()
    {
        return m_flRightMenuBar;
    }

    public RelativeLayout getLeftMenuBar()
    {
        return m_rlLeftMenuBar;
    }

    public LinearLayout getTitleContent()
    {
        return m_llTitleContent;
    }

    /**
     * 左边框添加文本
     * @param content 文本内容
     */
    final public void setLeftContent(String content)
    {
        m_rlLeftMenuBar.setVisibility(View.GONE);
        tvContentLeft.setVisibility(View.VISIBLE);
        tvContentLeft.setText(content);
    }

    /**
     * 左边框添加文本
     * @param content 项目文本内容
     */
    final public void setLeftContent(int content)
    {
        m_rlLeftMenuBar.setVisibility(View.GONE);
        tvContentLeft.setVisibility(View.VISIBLE);
        tvContentLeft.setText(content);
    }

    /**
     * 左边框添加自定义按钮
     * @param button 自定义按钮
     */
    final public void setCenterMenu(WidgeButton button)
    {
        tvTitle.setVisibility(View.GONE);
        m_rlCenterMenuBar.setVisibility(View.VISIBLE);
        m_rlCenterMenuBar.removeAllViews();

        if (button == null)
            return;

        RelativeLayout.LayoutParams menuLayoutParams;
        menuLayoutParams = createLayoutParams(button);
        menuLayoutParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        m_rlCenterMenuBar.addView(button, menuLayoutParams);
    }

    /**
     * 自定义标题栏布局
     * @param view 自定义view
     * @param menuLayoutParams 布局规则
     */
    final public void setCenterMenu(View view, RelativeLayout.LayoutParams menuLayoutParams)
    {
        tvTitle.setVisibility(View.GONE);
        m_rlCenterMenuBar.setVisibility(View.VISIBLE);
        m_rlCenterMenuBar.removeAllViews();

        if (view == null)
            return;

        menuLayoutParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        m_rlCenterMenuBar.addView(view, menuLayoutParams);
    }

    /**
     * 自定义标题栏布局
     * @param v 自定义view
     */
    final public void setCenterView(View v)
    {

        tvTitle.setVisibility(View.GONE);
        m_rlCenterMenuBar.setVisibility(View.VISIBLE);
        m_rlCenterMenuBar.removeAllViews();

        if (v == null)
            return;

        RelativeLayout.LayoutParams menuLayoutParams = new RelativeLayout.LayoutParams(-2, -2);
        menuLayoutParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        m_rlCenterMenuBar.addView(v, menuLayoutParams);
    }

    /**
     * 渲染布局文件
     * @param context 上下文
     */
    protected void inflateContextView(Context context)
    {
        LayoutInflater m_inflate = LayoutInflater.from(context);
        m_inflate.inflate(R.layout.base_view_navigationbar, this, true);
    }

    /**
     * 两侧按钮写入排版规则
     * @param button 两侧按钮
     * @return 布局排版规则
     */
    private RelativeLayout.LayoutParams createLayoutParams(WidgeButton button)
    {
        //判断WidgeButton按钮的类型,图标按钮或是文字按钮
        if (button.getWidgeButtonCategory().equals(WidgeButton.WidgeButtonCategory.image))
        { // 填充图片的按钮
            int buttonHeight = (int) getResources().getDimension(R.dimen.widget_image_size);
            if (button.getBackgroundHeight() > buttonHeight)
            {
                buttonHeight = button.getBackgroundHeight();
            }
            return new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, buttonHeight);
        } else {  //填充文字的按钮
            int buttonHeight = (int) getResources().getDimension(R.dimen.widget_view_size);
            return new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, buttonHeight);
        }
    }

    /**
     * 左侧自定义添加单个按钮
     * @param button 自定义按钮
     */
    final public void setLeftMenu(WidgeButton button)
    {
        //1.隐藏默认文本容器
        tvContentLeft.setVisibility(View.GONE);
        //2.显示按钮容器
        m_rlLeftMenuBar.setVisibility(View.VISIBLE);
        //3.初始化按钮容器
        m_rlLeftMenuBar.removeAllViews();

        if (button == null)
            return;

        /**
         * 添加单个按钮时，隐藏按钮间分割线
         */
        button.hideLeftDivider();
        button.hideRightDivider();
        RelativeLayout.LayoutParams menuLayoutParams;

        //4.根据按钮类型创建不同的布局规则
        menuLayoutParams = createLayoutParams(button);
        menuLayoutParams.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
        m_rlLeftMenuBar.addView(button, menuLayoutParams);
    }

    /**
     * 左侧自定义添加多个按钮
     * @param buttons 多个自定义按钮
     */
    final public void setLeftMenus(WidgeButton[] buttons)
    {
        int MENU_LEFT_START_ID = 0x998800;

        //1.隐藏标题
        tvContentLeft.setVisibility(View.GONE);
        m_rlLeftMenuBar.setVisibility(View.VISIBLE);
        m_rlLeftMenuBar.removeAllViews();

        if (buttons == null)
            return;

        int i = 0;

        //2.读入按钮
        for (WidgeButton button : buttons)
        {
            button.hideLeftDivider();
            RelativeLayout.LayoutParams menuLayoutParams;
            menuLayoutParams = createLayoutParams(button);
            menuLayoutParams.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);

            if (m_rlLeftMenuBar.getChildCount() > 0)
            {
                menuLayoutParams.addRule(RelativeLayout.RIGHT_OF, MENU_LEFT_START_ID + i - 1);
            }

            button.setId(MENU_LEFT_START_ID + i++);
            m_rlLeftMenuBar.addView(button, menuLayoutParams);
        }
    }

    /**
     * 设置右边按钮文本
     * @param content 文本
     */
    final public void setRightContent(String content)
    {
        m_rlRightMenuBar.setVisibility(View.GONE);
        tvContentRight.setVisibility(View.VISIBLE);
        tvContentRight.setText(content);
    }

    /**
     * 设置右边按钮项目文本
     * @param content 项目文本
     */
    final public void setRightContent(int content)
    {
        m_rlRightMenuBar.setVisibility(View.GONE);
        tvContentRight.setVisibility(View.VISIBLE);
        tvContentRight.setText(content);
    }

    /**
     * 设置右边按钮文本及监听
     * @param content  文本内容
     * @param listener 点击监听
     */
    final public void setRightContent(String content, OnClickListener listener)
    {
        m_rlRightMenuBar.setVisibility(View.GONE);
        tvContentRight.setVisibility(View.VISIBLE);
        tvContentRight.setText(content);
        m_rlRightMenuBar.setOnClickListener(listener);
        tvContentRight.setOnClickListener(listener);
    }

    final public TextView getRightContent()
    {
        return tvContentRight;
    }

    /**
     * 设置右边单个自定义按钮
     *    左右按钮不可加载同一个button
     *    否则error: The specified child already has a parent
     * @param button 自定义按钮
     */
    final public void setRightMenu(WidgeButton button)
    {
        //1.隐藏默认文本容器
        tvContentRight.setVisibility(View.GONE);
        //2.显示按钮容器
        m_rlRightMenuBar.setVisibility(View.VISIBLE);
        m_rlRightMenuBar.removeAllViews();

        if (button == null)
            return;

        button.setId(R.id.navigationBar_rightButton);

        button.hideRightDivider();
        button.hideLeftDivider();
        RelativeLayout.LayoutParams menuLayoutParams;
        menuLayoutParams = createLayoutParams(button);
        menuLayoutParams.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
        menuLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        button.setLayoutParams(menuLayoutParams);
        m_rlRightMenuBar.removeView(button);

        m_rlRightMenuBar.addView(button);
    }

    /**
     * 设置右边多个自定义按钮
     * @param buttons 多个按钮数组
     */
    final public void setRightMenus(WidgeButton[] buttons)
    {
        int MENU_RIGHT_START_ID = 0x999900;

        tvContentRight.setVisibility(View.GONE);
        m_rlRightMenuBar.setVisibility(View.VISIBLE);
        m_rlRightMenuBar.removeAllViews();

        if (buttons == null)
            return;

        int i = 0;
        for (WidgeButton button : buttons)
        {
            button.hideRightDivider();
            RelativeLayout.LayoutParams menuLayoutParams;
            menuLayoutParams = createLayoutParams(button);
            menuLayoutParams.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
            if (m_rlRightMenuBar.getChildCount() > 0)
            {
                menuLayoutParams.addRule(RelativeLayout.LEFT_OF, m_rlRightMenuBar.getChildAt(i - 1).getId());
                button.getM_ivLeftDivider().setVisibility(View.GONE);
            }

            button.setId(MENU_RIGHT_START_ID + i++);

            m_rlRightMenuBar.addView(button, menuLayoutParams);
        }
    }

    /**
     * 设置标题
     * @param title 标题文本
     */
    final public void setAppWidgeTitle(String title)
    {
        tvTitle.setCompoundDrawables(null, null, null, null);
        tvTitle.setClickable(false);
        tvTitle.setVisibility(View.VISIBLE);
        m_rlCenterMenuBar.setVisibility(View.GONE);
        tvTitle.setText(title);
    }

    /**
     * 设置标题以及默认title双击事件
     * @param title    标题文本
     * @param listView 列表
     */
    final public void setAppWidgeTitle(String title, ListView listView)
    {
        tvTitle.setCompoundDrawables(null, null, null, null);
        tvTitle.setClickable(false);
        tvTitle.setVisibility(View.VISIBLE);
        m_rlCenterMenuBar.setVisibility(View.GONE);
        tvTitle.setText(title);

        if (null != listView) {
            setTitleListener(listView);
        }
    }

    /**
     * 设置标题
     * @param spanned 标题文本
     */
    final public void setAppWidgeTitle(Spanned spanned)
    {
        tvTitle.setCompoundDrawables(null, null, null, null);
        tvTitle.setClickable(false);
        tvTitle.setVisibility(View.VISIBLE);
        m_rlCenterMenuBar.setVisibility(View.GONE);
        tvTitle.setText(spanned);
    }

    /**
     * 设置标题
     * @param title 资源标题文本
     */
    final public void setAppWidgeTitle(int title)
    {
        tvTitle.setCompoundDrawables(null, null, null, null);
        tvTitle.setClickable(false);
        tvTitle.setVisibility(View.VISIBLE);
        m_rlCenterMenuBar.setVisibility(View.GONE);
        tvTitle.setText(title);
    }

    /**
     * 设置标题以及默认title双击事件
     * @param title    资源标题文本
     * @param listView 列表
     */
    final public void setAppWidgeTitle(int title, ListView listView)
    {
        tvTitle.setCompoundDrawables(null, null, null, null);
        tvTitle.setClickable(false);
        tvTitle.setVisibility(View.VISIBLE);
        m_rlCenterMenuBar.setVisibility(View.GONE);
        tvTitle.setText(title);

        if (null != listView) {
            setTitleListener(listView);
        }
    }

    /**
     * 打开网络连接提示
     */
    final public void showConnecting()
    {
        tvConnecting.setVisibility(View.VISIBLE);
        ((FrameLayout.LayoutParams) m_llTitleContent.getLayoutParams()).bottomMargin = SizeUtils.dp2px(getContext(),
                4f);
        m_llTitleContent.requestLayout();
    }

    /**
     * 隐藏网络连接提示
     */
    final public void hideConnecting()
    {
        tvConnecting.setVisibility(View.INVISIBLE);
        ((FrameLayout.LayoutParams) m_llTitleContent.getLayoutParams()).bottomMargin = 0;
        m_llTitleContent.requestLayout();
    }

    final public void setAppWidgeTitle(String title, int resId, View.OnClickListener listener)
    {
        tvTitle.setVisibility(View.VISIBLE);
        m_rlCenterMenuBar.setVisibility(View.GONE);
        tvTitle.setText(title);
        Drawable drawable = null;
        if (resId != -1)
        {
            drawable = getResources().getDrawable(resId);
        }

        int width = 0;
        int height = 0;
        if (drawable != null) {
            width = Double.valueOf(drawable.getIntrinsicWidth()).intValue();
            height = Double.valueOf(drawable.getIntrinsicHeight()).intValue();
            drawable.setBounds(0, 0, width, height);
        }
        tvTitle.setCompoundDrawables(null, null, drawable, null);
        tvTitle.setClickable(true);
        tvTitle.setOnClickListener(listener);
    }

}
