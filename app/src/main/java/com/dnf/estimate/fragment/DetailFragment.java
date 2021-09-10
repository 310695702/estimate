package com.dnf.estimate.fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dnf.estimate.R;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.xuexiang.xui.widget.actionbar.TitleUtils;
import com.xuexiang.xui.widget.button.roundbutton.RoundButton;
import com.xuexiang.xui.widget.edittext.materialedittext.MaterialEditText;
import com.xuexiang.xui.widget.edittext.materialedittext.validation.RegexpValidator;
import com.xuexiang.xui.widget.textview.supertextview.SuperTextView;

import butterknife.BindView;
import butterknife.OnClick;

@Page(name = "详情")
public class DetailFragment extends BaseFragment{

    @BindView(R.id.tv_title)
    TextView textView;
    @BindView(R.id.et_basic)
    MaterialEditText mEtBasic;
    @BindView(R.id.et_basic2)
    MaterialEditText mEtBasic2;
    @BindView(R.id.et_basic3)
    MaterialEditText mEtBasic3;
    @BindView(R.id.bt_disenable)
    RoundButton mBtDisenable;
    @BindView(R.id.tv_content)
    TextView tv_content;
    @BindView(R.id.stv_switch_custom_theme)
    SuperTextView superTextView;
    @BindView(R.id.ll_edit2)
    LinearLayout ll_edit2;
    @BindView(R.id.ll_edit3)
    LinearLayout ll_edit3;
    private int GoldRate;
    private int price;
    private boolean flat = false;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_detail;
    }

    @Override
    protected void initViews() {
        textView.setText("售价：33800点券");
        mEtBasic.addValidator(new RegexpValidator("只能输入数字!", "\\d+"));
        mEtBasic2.addValidator(new RegexpValidator("只能输入数字!", "\\d+"));
        mEtBasic.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable!=null)
                    calculation(editable.toString());
            }
        });
        superTextView.setOnSuperTextViewClickListener(superTextView -> superTextView.setSwitchIsChecked(!superTextView.getSwitchIsChecked(), false)).setSwitchCheckedChangeListener((buttonView, isChecked) ->{
            flat = isChecked;
            if (flat){
                ll_edit2.setVisibility(View.GONE);
                ll_edit3.setVisibility(View.GONE);
                GoldRate = 0;
                price = 0;
            }else {
                ll_edit2.setVisibility(View.VISIBLE);
                ll_edit3.setVisibility(View.VISIBLE);
                mEtBasic2.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void afterTextChanged(Editable editable) {
                        if (editable!=null){
                            GoldRate = Integer.parseInt(editable.toString());
                        }
                    }
                });
                mEtBasic3.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void afterTextChanged(Editable editable) {
                        if (editable!=null){
                            price = Integer.parseInt(editable.toString());
                        }
                    }
                });
            }
        });
    }

    private void calculation(String str) {
        int num = Integer.parseInt(str);
        if (!flat){
            tv_content.setText("购买"+num+"套共需点券/代币券"+num*33800+"\n"
                    +"");
        }else {

        }
    }

    public double defultPrice(int price){
        return 0;
    }

    @Override
    protected TitleBar initTitle() {
        return TitleUtils.addTitleBarDynamic((ViewGroup) getRootView(), getPageTitle(), v -> popToBack())
                .setLeftImageDrawable(getNavigationBackDrawable(R.attr.xui_actionbar_ic_navigation_back)).setTitleSize(45)
                .setLeftClickListener(v->{getActivity().finish();});
    }

    @OnClick(R.id.bt_disenable)
    public void disEnable() {
        mEtBasic.setEnabled(!mEtBasic.isEnabled());
        mBtDisenable.setText(mEtBasic.isEnabled() ? "锁定" : "取消锁定");
    }
}
