# 说明文档

# 描述

- 玩安卓Demo 由于玩安卓api很少有符合paging中三种分页加载的格式 所以只有最后一个页面实现了paging 其他都是监听RecyclerView和NestedScrollView的滑动进行的分页加载

# 主体

- 主题是由一个**NavigationView+ViewPager**组成 图片全部由**svg**文件组成 网页由**webview**组成

# 工具（或第三方库）

- glide
- retrofit2
- mvvm jetpack(livedata viewmodel paging databinding lifecycle )
- banner



**注意：bean类无说明（篇幅太大）**

# 登录页

## 简介

- 由登录fragment和注册fragment组成
- 登录时发送网络请求 返回的errorCode若正常则做cookie持久化并且跳转到主页面
- 有记住账号密码功能 此功能的数据存储在sharedPreferences里面

## 代码

- 启动页 com/example/d_bee_final/ui/login_page/LoginActivity.java
  - 这是承载登录和注册fragment的activity 并且是启动activity
  - 内容是一个fragmentContainerView用处是替换登录和注册界面
- 登录页 com/example/d_bee_final/ui/login_page/LoginFragment.java
  - 登录fragment 主要是TextInputLayout和TextInputEditText
- 登录页ViewModel com/example/d_bee_final/ui/login_page/LoginViewModel.java
  - 存储登录页返回的网络数据的liveData
- 注册页 com/example/d_bee_final/ui/login_page/RegisterFragment.java
  - 注册fragment 主要是TextInputLayout和TextInputEditText
- 注册页ViewModel
  - 存储注册页返回的网络数据的liveData

# 首页

## 简介

-  顶部为banner loopTime为2000ms 点击之后跳转到webView
-  底部为recyclerView item中有收藏功能  点击跳转webView
-  在顶部有搜索关键词功能 搜索结果为文章 支持多个关键词（以空格隔开）
   - 搜索界面上方由一个editText和一个搜索图标组成
   - 下方为一个流式布局 这是通过网络请求获取到的搜索热词
   - 问答

- 由reyclerView组成

## 代码

### banner

- com/example/d_bee_final/ui/home_page/banner/BannerFragment.java
  - 这是banner的fragment 
- com/example/d_bee_final/ui/home_page/banner/BannerViewModel.java
  - banner的ViewModel 存放banner的liveData
- com/example/d_bee_final/ui/home_page/banner/HomePageBannerAdapter.java
  - banner的适配器 主要通过glide加载图片到banner

### search

- com/example/d_bee_final/ui/home_page/search/SearchActivity.java
  - 显示搜索界面的activity
  - 搜索跳转时会跟换下面的流式布局为文章的recyclerView
- com/example/d_bee_final/ui/home_page/search/TrendingSearchFragment.java
  - 显示搜索热词的fragment
- com/example/d_bee_final/ui/home_page/search/TrendingSearchViewModel.java
  - 存储热词的liveData
- com/example/d_bee_final/ui/home_page/search/QueriedArticleFragment.java
  - 搜索结果fragment
- com/example/d_bee_final/ui/home_page/search/QueryArticleViewModel.java
  - 搜索结果liveData

### article

- com/example/d_bee_final/ui/home_page/article/ArticleFragment.java
  - 首页展示文章的fragment
- com/example/d_bee_final/ui/home_page/article/HomePageArticleRecyclerViewAdapter.java
  - 首页文章的适配器
- com/example/d_bee_final/ui/home_page/article/HomePageArticleViewModel.java
  - 存储首页文章的liveData
- com/example/d_bee_final/ui/home_page/article/CollectViewModel.java
  - 操作收藏文章
- com/example/d_bee_final/ui/cancel_collect/UncollectViewModel.java
  - 操作取消收藏文章

## 问答

- 内容为问答文章

### 代码

- com/example/d_bee_final/ui/q_a_page/QAFragment.java
  - 问答页面fragment
- com/example/d_bee_final/ui/q_a_page/QuestionAdapter.java
  - 问答页面文章适配器
- com/example/d_bee_final/ui/q_a_page/QuestionViewModel.java
  - 问答页面viewmodel 存储问答liveData

#  体系

## 体系

- 流式布局 点击进入之后时tablayout tablayout的选项关为当前流式布局分类的item 可以左右滑动 内容为相应标签下的文章

## 导航

- 也是流式布局 主要为网站的分类 点击跳转相应网站

## 代码

### 体系细分

- com/example/d_bee_final/ui/structure_page/StructureFragment.java
  - 是体系页面的tablayout
- com/example/d_bee_final/ui/structure_page/StructureViewModel.java
  - 是体系页面的第一个流式布局（体系分类）的viewmodel 存储体系bean的liveData
- com/example/d_bee_final/ui/structure_page/classified_structure_page/FragmentFloatLayoutFragment.java
  - 体系流式布局
- com/example/d_bee_final/ui/structure_page/classified_structure_page/StructureArticleActivity.java
  - 体系分类中点击chip进入之后打开的activity 为tablayout和相应文章
- com/example/d_bee_final/ui/structure_page/classified_structure_page/StructureArticleAdapter.java
  - 流式布局下点击chip进入的文章
- com/example/d_bee_final/ui/structure_page/classified_structure_page/StructureArticleViewModel.java
  - 存储文章的liveData的viewmodel

### 导航细分

- com/example/d_bee_final/ui/structure_page/navi_page/NavigationFragment.java
  - 导航页面的流式布局 点击之后进入相应的网站
- com/example/d_bee_final/ui/structure_page/navi_page/NavigationViewModel.java
  - 存储请求导航页面的数据的liveData的viewmodel

# 我的

- 使用databinding paging 

## 我的积分 

- 进入之后是当前积分和积分获得的历史

## 我的分享

- 使用了paging 但是由于PagedList不能修改并且由于是加载几个小文章信息 没有使用boundaryCallback和room 所以每次进入的时候在里面调用了invalidate重新绘制进而实现刷新
- 其中还有分享文章的功能（在右上角）

## 我的收藏

- 实现了收藏功能 在任何文章页面点击收藏即可 会被保存到此页面

## 代码

### 我的分享

- com/example/d_bee_final/ui/mine_page/MineFragment.java

  - minePage的主要展示页面 包含积分、排名、等级、用户名、我的分享进入入口、我的收藏

- com/example/d_bee_final/ui/mine_page/MinePageInfoViewModel.java

  - 请求数据并且保存在viewmodel 的liveData中 xml使用databinding 会直接更新

- com/example/d_bee_final/ui/mine_page/my_coin_page/MyCoinActivity.java

  - 我的积分页面 进入之后会刷新我的积分获得历史 是recyclerView组成的

- com/example/d_bee_final/ui/mine_page/my_coin_page/MyCoinHistoryAdapter.java

  - 积分获得历史的适配器

- com/example/d_bee_final/ui/mine_page/my_coin_page/ReceiveCoinHistoryViewModel.java

  - 积分获得历史的viewmodel

- com/example/d_bee_final/ui/mine_page/my_sharing/MySharingActivity.java

  - 我的分享页面 主要是我分享的文章 由recyclerView组成 使用了paging进行分页加载

- com/example/d_bee_final/ui/mine_page/my_sharing/DeleteArticleViewModel.java

  - 删除分享

- com/example/d_bee_final/ui/mine_page/my_sharing/SharedArticleDataSource.java

  - paging的dataSource设置了业内容量 loadInitial和loadAfter的回调实现

- com/example/d_bee_final/ui/mine_page/my_sharing/SharedArticleListAdapter.java

  - 分享文章的适配器

- com/example/d_bee_final/ui/mine_page/my_sharing/SharedArticleSourceFactory.java

  - paging dataSource工厂类

- com/example/d_bee_final/ui/mine_page/my_sharing/SharedArticleViewModel.java

  - paging的View model里面配置了paging的加载策略 包括最开始加载几页 每页多少条 还剩几条时开始加载下一次 以及paging显示的最大条数

  

### 分享页

- com/example/d_bee_final/ui/mine_page/my_sharing/share_article/ShareArticleActivity.java
  - 包括分享标题、链接 （必须保证分享链接有效 否则可能会受到系统的分享限制）
- com/example/d_bee_final/ui/mine_page/my_sharing/share_article/SharingArticleViewModel.java
  - 分享结果的返回liveData存储在该viewmodel

# 逻辑层

**注意：不对网络请求接口进行描述（篇幅过长）**

### DAO

- com/example/d_bee_final/logic/dao/CookieDao.java
  - 进行cookie持久化的操作类 存储在sharedPreferences
- com/example/d_bee_final/logic/dao/UserDao.java
  - 用户进行记住密码操作时 将账户记载在sharedPreferences

### NetWork

- com/example/d_bee_final/logic/network/NetWork.java
  - 用于创造网络请求的服务方便运用在repository 并且解耦
- com/example/d_bee_final/logic/network/ServiceCreator.java
  - retrofit的服务 提供了一个接口用于简单的创建一个网络请求服务

### Util

- com/example/d_bee_final/logic/util/AddCookieInterceptor.java
  - 该拦截器用于向请求中添加以前拦截下的cookie
- com/example/d_bee_final/logic/util/ReceiveCookieInterceptor.java
  - 该拦截器用于将cookie拦截并且保存于本地
- com/example/d_bee_final/logic/util/CookieForClient.java
  - 用于操作拦截器（添加到retrofit中）

### repository

- com/example/d_bee_final/logic/Repository.java
  - 该项目主要用于进行网络访问

