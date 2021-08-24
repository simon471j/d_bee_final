# 说明文档

# 描述

- 玩安卓Demo 由于玩安卓api很少有符合paging中三种分页加载的格式 所以只有最后一个页面实现了paging 其他都是监听RecyclerView和NestedScrollView的滑动进行的分页加载

# 主体

- 主题是由一个NavigationView+ViewPager组成 图片全部由svg文件组成 网页由webview组成

# 工具

- glide
- retrofit
- mvvm jetpack(livedata (rxjava太大了 而且用谷歌的会比较好？)viewmodel paging databinding lifecycle )
- banner



# 登录页

- 由登录fragment和注册fragment组成 暂无其他讲解

# 首页

-  首页是一个banner加上一个RecyclerView 这两个组件是放在不同的 Fragment中 然后再放在HomePage(由NestedScrollView嵌套)

# 问答

- 由reyclerView组成

#  体系

- tablayout组成
- 第一页是一个流式布局 点进去又是tablayout会定位到所点击的tab
- 第二页也是流式布局 点击进去会跳转到相关网页

# 我的

- 使用databinding paging 

  ## 我的积分 

  - 进入之后是当前积分和积分获得的历史

  ## 我的分享

  - 使用了paging 但是由于PagedList不能修改并且由于是加载几个小文章信息 没有使用boundaryCallback和room 所以每次进入的时候在里面调用了invalidate进而实现刷新
  - 其中还有分享文章的功能（在右上角）

  ## 我的收藏

  - 实现了收藏功能 在任何文章页面点击收藏即可 会被保存到此页面

