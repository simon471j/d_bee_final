package com.example.d_bee_final.ui.mine_page.my_sharing;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.DataSource;
import androidx.paging.PagedList;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.d_bee_final.R;
import com.example.d_bee_final.logic.bean.SharedArticleBean;
import com.example.d_bee_final.ui.WebActivity;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class SharedArticleListAdapter extends PagedListAdapter<SharedArticleBean.ShareArticles.Datas, SharedArticleListAdapter.ViewHolder> {
    private final Context context;
    private DeleteArticleViewModel deleteArticleViewModel;
    private SharedArticleViewModel sharedArticleViewModel;

    public SharedArticleListAdapter(Context context, DeleteArticleViewModel deleteArticleViewModel, SharedArticleViewModel sharedArticleViewModel) {
        super(DIFF_CALLBACK);
        this.context = context;
        this.deleteArticleViewModel = deleteArticleViewModel;
        this.sharedArticleViewModel = sharedArticleViewModel;
    }

    private static final DiffUtil.ItemCallback<SharedArticleBean.ShareArticles.Datas> DIFF_CALLBACK = new DiffUtil.ItemCallback<SharedArticleBean.ShareArticles.Datas>() {
        @Override
        public boolean areItemsTheSame(@NonNull @NotNull SharedArticleBean.ShareArticles.Datas oldItem, @NonNull @NotNull SharedArticleBean.ShareArticles.Datas newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull @NotNull SharedArticleBean.ShareArticles.Datas oldItem, @NonNull @NotNull SharedArticleBean.ShareArticles.Datas newItem) {
            return oldItem.equals(newItem);
        }
    };


    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.shared_article_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull SharedArticleListAdapter.ViewHolder holder, int position) {
        SharedArticleBean.ShareArticles.Datas article = getItem(position);
        holder.author.setText(article.getShareUser());
        holder.title.setText(article.getTitle());
        holder.niceShareDate.setText(article.getNiceShareDate());
        holder.superChapterName.setText(article.getSuperChapterName());
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteArticleViewModel.delete(article.getId());
                holder.delete.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getCurrentList().getDataSource().invalidate();
                    }
                }, 500);
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, WebActivity.class);
                intent.putExtra("url", article.getLink());
                context.startActivity(intent);
            }
        });

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView author;
        TextView title;
        TextView niceShareDate;
        TextView superChapterName;
        ImageView delete;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            author = itemView.findViewById(R.id.tv_author_name);
            title = itemView.findViewById(R.id.tv_title);
            niceShareDate = itemView.findViewById(R.id.tv_nice_share_date);
            superChapterName = itemView.findViewById(R.id.tv_super_chapter_name);
            delete = itemView.findViewById(R.id.iv_delete);
        }
    }

}
