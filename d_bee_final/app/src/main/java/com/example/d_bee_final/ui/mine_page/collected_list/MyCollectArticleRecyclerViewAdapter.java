package com.example.d_bee_final.ui.mine_page.collected_list;

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
import com.example.d_bee_final.logic.bean.CollectListBean;
import com.example.d_bee_final.ui.WebActivity;
import com.example.d_bee_final.ui.cancel_collect.UncollectViewModel;
import com.example.d_bee_final.ui.home_page.article.CollectViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MyCollectArticleRecyclerViewAdapter extends RecyclerView.Adapter<MyCollectArticleRecyclerViewAdapter.ViewHolder> {

    List<CollectListBean.Data.Datas> articleList;
    Activity activity;
    CollectViewModel collectViewModel;

    public MyCollectArticleRecyclerViewAdapter(List<CollectListBean.Data.Datas> articleList, Activity activity, CollectViewModel collectViewModel) {
        this.articleList = articleList;
        this.activity = activity;
        this.collectViewModel = collectViewModel;
    }


    @NonNull
    @NotNull
    @Override
    public MyCollectArticleRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.article_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MyCollectArticleRecyclerViewAdapter.ViewHolder holder, int position) {
        CollectListBean.Data.Datas currentArticle = articleList.get(position);
//        初始化item
        if (!"".equals(currentArticle.getAuthor()))
            holder.author.setText(currentArticle.getAuthor());
        else
            holder.author.setText(currentArticle.getAuthor());
        holder.title.setText(currentArticle.getTitle());
        holder.superChapterName.setText(currentArticle.getChapterName());
        holder.niceShareDate.setText(currentArticle.getNiceDate());

//        收藏
        holder.like.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    collectViewModel.collect(currentArticle.getId());
                else {
                    UncollectViewModel.unCollect(currentArticle.getId());
                    articleList.remove(position);
                    notifyItemRangeRemoved(position, 1);
                }
            }
        });

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

    public void setArticleList(List<CollectListBean.Data.Datas> articleList) {
        int start = this.articleList.size();
        this.articleList.addAll(articleList);
        notifyItemRangeInserted(start, articleList.size());
    }
}
