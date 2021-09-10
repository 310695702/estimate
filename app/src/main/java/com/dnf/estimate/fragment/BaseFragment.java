package com.dnf.estimate.fragment;

import android.graphics.drawable.Drawable;
import android.view.ViewGroup;

import com.xuexiang.xpage.base.XPageFragment;
import com.xuexiang.xui.utils.DrawableUtils;
import com.xuexiang.xui.utils.ThemeUtils;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.xuexiang.xui.widget.actionbar.TitleUtils;

public abstract class BaseFragment extends XPageFragment {

    @Override
    protected void initPage() {
        initTitle();
        initViews();
        initListeners();
    }

    protected TitleBar initTitle() {
        return TitleUtils.addTitleBarDynamic((ViewGroup) getRootView(), getPageTitle(), v -> popToBack())
                .setLeftImageDrawable(null).setTitleSize(45);
    }

    public Drawable getNavigationBackDrawable(int navigationBackId) {
        return DrawableUtils.getSupportRTLDrawable(ThemeUtils.resolveDrawable(getContext(), navigationBackId));
    }

    @Override
    protected void initListeners() {

    }



}
