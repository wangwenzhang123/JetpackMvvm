首先本项目使用jetpack和MVVM。并且使用Arouter做为模块化方案（原本构想是一个在APP模块放一个activity，其他所有模块全部都是fragment）
除了app模块 其他所有模块都必须导入base模块 然后App模块按需导入各个具体业务模块
网络模块使用了rxjava和retrofit 请求数据格式根据各自项目需求改
现在的定义是 一个模块一个activity，当然除了首页的tabfragment。
每个模块的activity（需要跨模块使用的fragment也需要声明地址）都在base模块下的AppActivityKey中声明地址，具体的跳转传值，还请自行看资料
每个model fragment activity 必须继承base类 model方法还请使用接口
