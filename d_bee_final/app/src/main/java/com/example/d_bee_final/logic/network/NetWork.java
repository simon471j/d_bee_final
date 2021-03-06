package com.example.d_bee_final.logic.network;

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

import retrofit2.Call;

public class NetWork {


    private static final BannerService bannerService = ServiceCreator.create(BannerService.class);
    private static final HomePageArticleService homePageArticleService = ServiceCreator.create(HomePageArticleService.class);
    private static final LoginService loginService = ServiceCreator.create(LoginService.class);
    private static final CollectService collectService = ServiceCreator.create(CollectService.class);
    private static final CollectListService collectListService = ServiceCreator.create(CollectListService.class);
    private static final UncollectService uncollectService = ServiceCreator.create(UncollectService.class);
    private static final QuestionService questionService = ServiceCreator.create(QuestionService.class);
    private static final StructureService structureService = ServiceCreator.create(StructureService.class);
    private static final StructureArticleService structureArticleService = ServiceCreator.create(StructureArticleService.class);
    private static final NavigationService navigationService = ServiceCreator.create(NavigationService.class);
    private static final TrendingWordService trendingWordService = ServiceCreator.create(TrendingWordService.class);
    private static final QueryService queryService = ServiceCreator.create(QueryService.class);
    private static final MinePageInfoService minePageInfoService = ServiceCreator.create(MinePageInfoService.class);
    private static final ReceiveCoinService receiveCoinService = ServiceCreator.create(ReceiveCoinService.class);
    private static final SharedArticleService sharedArticleService = ServiceCreator.create(SharedArticleService.class);
    private static final DeleteService deleteService = ServiceCreator.create(DeleteService.class);
    private static final SharingArticleService sharingArticleService = ServiceCreator.create(SharingArticleService.class);

    //    ??????banner
    public static Call<BannerBean> getBanner() {
        return bannerService.getBanner();
    }

    //    ????????????
    public static Call<ArticleBean> getHomePageArticleBean(int page) {
        return homePageArticleService.getHomePageArticle(page);
    }

    //    ??????????????????
    public static Call<LoginBean> getLoginResult(String username, String password) {
        return loginService.login(username, password);
    }

    //    ??????
    public static Call<LoginBean> getRegisterResult(String username, String password, String repassword) {
        return loginService.register(username, password, repassword);
    }

    //    ??????
    public static Call<CollectBean> getCollectResult(int id) {
        return collectService.collect(id);
    }

    //    ?????????????????????
    public static Call<CollectListBean> getCollectedListBean(int page) {
        return collectListService.getCollectedList(page);
    }

    //     ????????????
    public static Call<UncollectBean> uncollect(int originId) {
        return uncollectService.uncollect(originId);
    }

    //     ??????????????????
    public static Call<QuestionBean> getQuestion(int page) {
        return questionService.getQuestion(page);
    }

    //      ??????????????????
    public static Call<StructureBean> getStructure() {
        return structureService.getStructure();
    }

    //      ??????????????????????????????
    public static Call<ArticleBean> getStructureArticle(int page, int id) {
        return structureArticleService.getStructureArticle(page, id);
    }

    //      ????????????????????????
    public static Call<NavigationBean> getNavigation() {
        return navigationService.getNavigation();
    }

    //      ??????????????????
    public static Call<TrendingWordBean> getTrendingWord() {
        return trendingWordService.getTrendingWord();
    }

    //      ????????????
    public static Call<ArticleBean> getQueriedArticle(int page, String key) {
        return queryService.searchArticle(page, key);
    }

    //      ????????????????????????
    public static Call<MinePageInfoBean> getMinePageInfo() {
        return minePageInfoService.getMinePageInfo();
    }

    //      ????????????????????????
    public static Call<ReceiveCoinBean> receiveCoinBeanCall() {
        return receiveCoinService.getCoinHistory();
    }

    //       ????????????????????????
    public static Call<SharedArticleBean> sharedArticleBeanCall(int page, int pageSize) {
        return sharedArticleService.getSharedArticle(page, pageSize);
    }

    //      ?????????????????????
    public static Call<DeleteBean> deleteBeanCall(int articleId) {
        return deleteService.delete(articleId);
    }

    //      ????????????
    public static Call<UncollectBean> sharingArticleBeanCall(String title,String link){
        return sharingArticleService.share(title,link);
    }
}
