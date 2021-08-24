package com.example.d_bee_final.ui.q_a_page;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.example.d_bee_final.MyApp;
import com.example.d_bee_final.R;
import com.example.d_bee_final.logic.bean.QuestionBean;
import com.example.d_bee_final.ui.home_page.article.CollectViewModel;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.List;

public class QAFragment extends Fragment {


    private QuestionViewModel questionViewModel;
    private CollectViewModel collectViewModel;
    private List<QuestionBean.Data.Datas> questionList = new ArrayList<>();
    private RecyclerView recyclerView;
    private Boolean isQuestionListOver = false;

    public QAFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_q_a, container, false);
        questionViewModel = new ViewModelProvider(this).get(QuestionViewModel.class);
        collectViewModel = new ViewModelProvider(this).get(CollectViewModel.class);
        initView(view);
        return view;
    }

    private void initView(View view) {
        recyclerView = view.findViewById(R.id.question_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        QuestionAdapter questionAdapter = new QuestionAdapter(questionList, requireActivity(), collectViewModel);
        recyclerView.setAdapter(questionAdapter);
        questionViewModel.getQuestionMutableLiveData().observe(getViewLifecycleOwner(), new Observer<QuestionBean>() {
            @Override
            public void onChanged(QuestionBean questionBean) {
                questionList = questionBean.getData().getDatas();
                questionAdapter.setQuestionList(questionList);
                if (questionBean.getData().getOver()) {
                    isQuestionListOver = true;
                }
            }
        });
        questionViewModel.refreshQuestion();
        onRecyclerViewRefresh();
    }

    //    监听recyclerView是否加载到底部 决定加载
    private void onRecyclerViewRefresh() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull @NotNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                //屏幕中最后一个可见子项的position
                assert layoutManager != null;
                int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
                //当前屏幕所看到的子项个数
                int visibleItemCount = layoutManager.getChildCount();
                //当前RecyclerView的所有子项个数
                int totalItemCount = layoutManager.getItemCount();
                if (visibleItemCount > 0 && lastVisibleItemPosition == totalItemCount - 1) {
                    if (!isQuestionListOver) {
                        questionViewModel.refreshQuestion();
                        Toast.makeText(MyApp.context, "加载中", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(MyApp.context, "没有更多数据了噢", Toast.LENGTH_SHORT).show();
                        recyclerView.removeOnScrollListener(this);
                    }
                }
            }
        });
    }


}