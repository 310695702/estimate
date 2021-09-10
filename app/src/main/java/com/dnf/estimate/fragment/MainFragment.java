package com.dnf.estimate.fragment;

import androidx.annotation.DrawableRes;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dnf.estimate.R;
import com.dnf.estimate.ViewHolder.CardViewHolder;
import com.dnf.estimate.activity.DetailActivity;
import com.dnf.estimate.adapter.RecyclerViewAdapter;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xui.widget.textview.MarqueeTextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

@Page(name = "estimate")
public class MainFragment extends BaseFragment {

    @BindView(R.id.tv_marquee)
    MarqueeTextView marqueeTextView;
    @BindView(R.id.main_recyclerView)
    RecyclerView recyclerView;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    protected void initViews() {
        final List<String> datas = Arrays.asList("注意，该demo仅用于学习使用，本人不承担任何提供的内容所引起的争议和法律责任，数据仅供参考。");
        marqueeTextView.startSimpleRoll(datas);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        List<Option> list = new ArrayList<>();
        list.add(new Option(1,"金秋礼包",R.drawable.jinqiu));
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(getContext(), (ArrayList) list, R.layout.item_card);
        adapter.setViewHolder("com.dnf.estimate.ViewHolder.CardViewHolder");
        adapter.setOnBindViewHolderListener(new RecyclerViewAdapter.onBindViewHolderListener() {
            @Override
            public void onBindViewHolder(Object holder, Object o) {
                CardViewHolder h = ((CardViewHolder) holder);
                Option option = (Option) o;
                h.card_textView.setText(option.getName());
                Glide.with(getContext()).load(option.getUrl()).into(h.card_imageView);
            }
        });
        adapter.setOnClickListener(new RecyclerViewAdapter.onClickListener() {
            @Override
            public void onClick(int position) {
                DetailActivity.start(getContext(),list.get(position).getId());
            }
        });
        recyclerView.setAdapter(adapter);

    }

    private class Option{
        private int id;
        private String name;
        private int url;

        public Option(Integer id, String name, @DrawableRes int drawableId) {
            this.id = id;
            this.name = name;
            this.url = drawableId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getUrl() {
            return url;
        }

        public void setUrl(int url) {
            this.url = url;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
