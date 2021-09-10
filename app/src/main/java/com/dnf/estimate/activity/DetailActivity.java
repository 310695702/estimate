package com.dnf.estimate.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.dnf.estimate.R;
import com.dnf.estimate.fragment.DetailFragment;
import com.xuexiang.xui.XUI;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        XUI.initTheme(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        DetailFragment fragment = new DetailFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.container,fragment).commitAllowingStateLoss();
    }

    public static void start(Context context,Integer id) {
        Intent intent = new Intent(context,DetailActivity.class);
        context.startActivity(intent);
    }

}
