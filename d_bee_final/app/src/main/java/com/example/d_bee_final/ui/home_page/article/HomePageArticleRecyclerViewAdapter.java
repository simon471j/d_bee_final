package com.example.d_bee_final.ui.home_page.article;

import android.app.Activity;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.d_bee_final.R;
import com.example.d_bee_final.logic.bean.ArticleBean;
import com.example.d_bee_final.ui.WebActivity;
import com.example.d_bee_final.ui.cancel_collect.UncollectViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class HomePageArticleRecyclerViewAdapter extends RecyclerView.Adapter<com.example.d_bee_final.ui.home_page.article.HomePageArticleRecyclerViewAdapter.ViewHolder> {

    List<ArticleBean.Data.Datas> articleList;
    Activity activity;
    CollectViewModel collectViewModel;

    public HomePageArticleRecyclerViewAdapter(List<ArticleBean.Data.Datas> articleList, Activity activity, CollectViewModel collectViewModel) {
        this.articleList = articleList;
        this.activity = activity;
        this.collectViewModel = collectViewModel;
    }

    
    @NonNull
    @NotNull
    @Override
    public com.example.d_bee_final.ui.home_page.article.HomePageArticleRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.article_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull com.example.d_bee_final.ui.home_page.article.HomePageArticleRecyclerViewAdapter.ViewHolder holder, int position) {
        ArticleBean.Data.Datas currentArticle = articleList.get(position);
//        初始化item
        if (!"".equals(currentArticle.getAuthor()))
            holder.author.setText(currentArticle.getAuthor());
        else
            holder.author.setText(currentArticle.getShareUser());
        if (currentArticle.getTags().size() > 0) {
            holder.tagName.setVisibility(View.VISIBLE);
            holder.tagName.setText(currentArticle.getTags().get(0).getName());
        }
        holder.title.setText(currentArticle.getTitle());
        holder.superChapterName.setText(currentArticle.getSuperChapterName());
        holder.niceShareDate.setText(currentArticle.getNiceShareDate());

//        收藏
        holder.like.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    collectViewModel.collect(currentArticle.getId());
                else UncollectViewModel.unCollect(currentArticle.getId());
            }
        });

//        如果是已经收藏的文章 那么设置checkBox为true(点亮爱心)
        if (currentArticle.getCollect())
            holder.like.setChecked(true);

//        注册点击事件 点击之后打开链接
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String link = currentArticle.getLink();
                Intent intent = new Intent(activity, WebActivity.class);
                intent.putExtra("url", link);
                activity.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        if (articleList == null)
            return 0;
        else
            return articleList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tagName;
        TextView author;
        TextView title;
        TextView superChapterName;
        TextView niceShareDate;
        CheckBox like;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            tagName = itemView.findViewById(R.id.tv_tags_name);
            author = itemView.findViewById(R.id.tv_author_name);
            title = itemView.findViewById(R.id.tv_title);
            superChapterName = itemView.findViewById(R.id.tv_super_chapter_name);
            niceShareDate = itemView.findViewById(R.id.tv_nice_share_date);
            like = itemView.findViewById(R.id.check_box_like);
        }
    }

    public void setArticleList(List<ArticleBean.Data.Datas> articleList) {
        int start = this.articleList.size();
        this.articleList.addAll(articleList);
        notifyItemRangeInserted(start, articleList.size());
    }
}
