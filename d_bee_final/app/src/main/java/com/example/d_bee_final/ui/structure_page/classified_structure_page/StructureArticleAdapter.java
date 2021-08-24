package com.example.d_bee_final.ui.structure_page.classified_structure_page;

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
import com.example.d_bee_final.ui.home_page.article.CollectViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class StructureArticleAdapter extends RecyclerView.Adapter<StructureArticleAdapter.ViewHolder> {

    List<ArticleBean.Data.Datas> structureArticleList;
    Activity activity;
    CollectViewModel collectViewModel;


    public StructureArticleAdapter(List<ArticleBean.Data.Datas> structureArticleList, Activity activity, CollectViewModel collectViewModel) {
        this.structureArticleList = structureArticleList;
        this.activity = activity;
        this.collectViewModel = collectViewModel;
    }

    @NonNull
    @NotNull
    @Override
    public StructureArticleAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.article_item, parent, false);
        return new StructureArticleAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull StructureArticleAdapter.ViewHolder holder, int position) {
        ArticleBean.Data.Datas currentQuestion = structureArticleList.get(position);
//        初始化item
        if (!"".equals(currentQuestion.getAuthor()))
            holder.author.setText(currentQuestion.getAuthor());
        else
            holder.author.setText(currentQuestion.getShareUser());
        if (currentQuestion.getTags().size() > 0) {
            holder.tagName.setVisibility(View.VISIBLE);
            holder.tagName.setText(currentQuestion.getTags().get(0).getName());
        }
        holder.title.setText(currentQuestion.getTitle());
        holder.superChapterName.setText(currentQuestion.getSuperChapterName());
        holder.niceShareDate.setText(currentQuestion.getNiceShareDate());

//        收藏
        holder.like.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    collectViewModel.collect(currentQuestion.getId());
                else UncollectViewModel.unCollect(currentQuestion.getId());
            }
        });

//        如果是已经收藏的文章 那么设置checkBox为true(点亮爱心)
        if (currentQuestion.getCollect())
            holder.like.setChecked(true);

//        注册点击事件 点击之后打开链接
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String link = currentQuestion.getLink();
                Intent intent = new Intent(activity, WebActivity.class);
                intent.putExtra("url", link);
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (structureArticleList == null)
            return 0;
        else
            return structureArticleList.size();
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

    public void setStructureArticleList(List<ArticleBean.Data.Datas> structureArticleList) {
        int start = this.structureArticleList.size();
        this.structureArticleList.addAll(structureArticleList);
        notifyItemRangeInserted(start, structureArticleList.size());
    }
}