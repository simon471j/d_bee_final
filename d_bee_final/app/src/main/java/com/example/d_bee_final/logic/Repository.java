package com.example.d_bee_final.logic;


import com.example.d_bee_final.logic.bean.BannerBean;
import com.example.d_bee_final.logic.bean.CollectBean;
import com.example.d_bee_final.logic.bean.CollectListBean;
import com.example.d_bee_final.logic.bean.ArticleBean;
import com.example.d_bee_final.logic.bean.DeleteBean;
import com.example.d_bee_final.logic.bean.LoginBean;
import com.example.d_bee_final.logic.bean.MinePageInfoBean;
import com.example.d_bee_final.logic.bean.NavigationBean;
import com.example.d_bee_final.logic.bean.QuestionBean;
import com.example.d_bee_final.logic.bean.ReceiveCoinBean;
import com.example.d_bee_final.logic.bean.SharedArticleBean;
import com.example.d_bee_final.logic.bean.StructureBean;
import com.example.d_bee_final.logic.bean.TrendingWordBean;
import com.example.d_bee_final.logic.bean.UncollectBean;
import com.example.d_bee_final.logic.network.NetWork;

import retrofit2.Call;

public class Repository {

    /**
     * @return banner的网络请求
     */
    public static Call<BannerBean> getBanner() {
        return NetWork.getBanner();
    }

    /**
     * @param page 页数
     * @return HomePageArticleBean网络请求
     */
    public static Call<ArticleBean> getHomePageArticle(int page) {
        return NetWork.getHomePageArticleBean(page);
    }

    /**
     * @param username 用户名
     * @param password 密码
     * @return 登录网络请求
     */
    public static Call<LoginBean> getLoginResult(String username, String password) {
        return NetWork.getLoginResult(username, password);
    }

    /**
     * @param username   用户名
     * @param password   密码
     * @param repassword 确认密码
     * @return 注册
     */
    public static Call<LoginBean> getRegisterResult(String username, String password, String repassword) {
        return NetWork.getRegisterResult(username, password, repassword);
    }

    /**
     * @param id 文章的id
     * @return 收藏文章
     */
    public static Call<CollectBean> getCollectResult(int id) {
        return NetWork.getCollectResult(id);
    }

    /**
     * @param page 加载第page页
     * @return 获取收藏列表
     */
    public static Call<CollectListBean> getCollectedListBean(int page) {
        return NetWork.getCollectedListBean(page);
    }

    /**
     * @param originId 文章的originId
     * @return 取消收藏
     */
    public static Call<UncollectBean> uncollect(int originId) {
        return NetWork.uncollect(originId);
    }

    /**
     * @param page 加载第page页
     * @return 获取问答数据
     */
    public static Call<QuestionBean> getQuestion(int page) {
        return NetWork.getQuestion(page);
    }

    /**
     * @return 获取体系数据
     */
    public static Call<StructureBean> getStructure() {
        return NetWork.getStructure();
    }

    /**
     * @param page 加载第page页
     * @param id   文章id
     * @return 获取体系中的细分文章
     */
    public static Call<ArticleBean> getStructureArticle(int page, int id) {
        return NetWork.getStructureArticle(page, id);
    }

    /**
     * @return 获取导航中数据
     */
    public static Call<NavigationBean> getNavigation() {
        return NetWork.getNavigation();
    }

    /**
     * @return 获取搜索热词
     */
    public static Call<TrendingWordBean> getTrendingWord() {
        return NetWork.getTrendingWord();
    }

    /**
     * @param page 页数
     * @param key  关键词
     * @return 搜索文章
     */
    public static Call<ArticleBean> getQueriedArticle(int page, String key) {
        return NetWork.getQueriedArticle(page, key);
    }

    /**
     * @return 个人信息主页面数据
     */
    public static Call<MinePageInfoBean> getMinePageInfo() {
        return NetWork.getMinePageInfo();
    }

    /**
     * @return 获得积分的部分历史
     */
    public static Call<ReceiveCoinBean> receiveCoinBeanCall() {
        return NetWork.receiveCoinBeanCall();
    }

    /**
     * @param page     页数
     * @param pageSize 每页的文章数
     * @return 返回文章的请求
     */
    public static Call<SharedArticleBean> sharedArticleBeanCall(int page, int pageSize) {
        return NetWork.sharedArticleBeanCall(page, pageSize);
    }

    /**
     *
     * @param articleId 文章的id
     * @return 删除自己分享的文章
     */
    public static Call<DeleteBean> deleteBeanCall(int articleId) {
        return NetWork.deleteBeanCall(articleId);
    }

    public static Call<UncollectBean> sharingArticleBeanCall(String title,String link){
        return NetWork.sharingArticleBeanCall(title,link);
    }
}
