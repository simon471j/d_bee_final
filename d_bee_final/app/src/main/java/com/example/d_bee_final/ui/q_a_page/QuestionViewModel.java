package com.example.d_bee_final.ui.q_a_page;
import android.util.Log;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.d_bee_final.logic.Repository;
import com.example.d_bee_final.logic.bean.QuestionBean;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuestionViewModel extends ViewModel {
    private MutableLiveData<QuestionBean> questionMutableLiveData = new MutableLiveData<>();
    private int page = 0;

    public MutableLiveData<QuestionBean> getQuestionMutableLiveData() {
        return questionMutableLiveData;
    }

    public void refreshQuestion() {
        Repository.getQuestion(page++).enqueue(new Callback<QuestionBean>() {
            @Override
            public void onResponse(Call<QuestionBean> call, Response<QuestionBean> response) {
                if (response.isSuccessful()) {
                    questionMutableLiveData.setValue(response.body());
                    Log.d(getClass().getName(), "onResponse: "+response.body());
                }
            }

            @Override
            public void onFailure(Call<QuestionBean> call, Throwable t) {
                Log.d(getClass().getName(), "onFailure: "+t);
            }
        });
    }
}
