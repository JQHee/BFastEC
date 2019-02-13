#### 一、打包加固说明
1.多渠道打包加固->爱加密
2.腾讯乐固

V1：可对签名后的文件，作适当修改，并重新压缩。
V2：不能对签名后的 APK作任何修改，包括 zipalign。因为它是针对字节进行的签名，所以任何改动都会影响最终结果。
Signature Versions不能只选择 V2(Full APK Signature)，应该选择V1(Jar Signature)，或者选择 V1和 V2。


#### 二、第三方框架整理

##### 框架
- 0.application入口文件优化：https://juejin.im/post/5b59a4e7e51d455f5f4cfa38
- 1.底部导航tabbar：https://github.com/tyzlmjj/PagerBottomTabStrip
- 2.ViewPager: https://github.com/LuckyJayce/ViewPagerIndicator
- 3.web浏览器：https://github.com/Justson/AgentWeb
- 4.快速设置圆角、边框控件不需要写一堆selector背景：https://github.com/niniloveyou/StateButton
- 5.加载框：https://github.com/maning0303/MNProgressHUD
- 6.页面显示奔溃日志：https://github.com/maning0303/MNCrashMonitor
- 7.Toast 弹窗提示：https://github.com/bboylin/UniversalToast
- 8.加载提示框架：https://github.com/ybq/Android-SpinKit
- 9.解决关闭通知toast弹不出来： https://github.com/getActivity/ToastUtils


##### 状态栏
- 1.ImmersionBar：https://github.com/gyf-dev/ImmersionBar
- 2.通用导航栏：https://github.com/getActivity/TitleBar
- 2.StatusBarUtil: https://github.com/laobie/StatusBarUtil


##### popupWindow&Dialog
- 1.EasyPopup: https://github.com/zyyoona7/EasyPopup
- 2.BasePopup: https://github.com/razerdp/BasePopup
- 3.XPopup: https://github.com/li-xiaojun/XPopup
- 4.dialogplus: https://github.com/orhanobut/dialogplus

##### 二维码扫描
- 1.*工具库（支付、扫码、弹窗）： https://github.com/vondear/RxTool
- 2.barcodescanner 封装有zbar(c语言)、zxing: https://github.com/dm77/barcodescanner
- 3.BGAQRCode生成二维码、扫描二维码、识别图片二维码：https://github.com/bingoogolapple/BGAQRCode-Android


##### 动态权限
- 1.easypermissions动态权限: https://github.com/googlesamples/easypermissions
- 2.权限：https://github.com/permissions-dispatcher/PermissionsDispatcher
- 3.权限库：https://github.com/tbruyelle/RxPermissions.git


##### 分页tab查看
- 1.MagicIndicator: https://github.com/hackware1993/MagicIndicator
- 2.FlycoTabLayout：https://github.com/H07000223/FlycoTabLayout
- 3.PageIndicatorView指示器：https://github.com/romandanylyk/PageIndicatorView
- 4.RecyclerViewPager：https://github.com/lsjwzh/RecyclerViewPager


##### 多状态提示
- 1.stateView: https://github.com/vlonjatg/progress-activity.git
- 2.MultipleStatusView：https://github.com/qyxxjd/MultipleStatusView


##### 事件总线
- 1.EventBus： https://github.com/greenrobot/EventBus


##### 刷新框架：
- 1.smartrefresh：https://github.com/scwang90/SmartRefreshLayout
- 2.BGARefreshLayout-Android：https://github.com/bingoogolapple/BGARefreshLayout-Android


##### 工具库：
- 1.AndroidUtilCode：https://github.com/Blankj/AndroidUtilCode
- 2.HTML解析：https://github.com/jhy/jsoup
- 3.收款码：https://github.com/Exrick/xpay


##### 数据库
- 1.mmkv: https://github.com/Tencent/MMKV
- 2.realm: https://github.com/realm/realm-java
- 3.greendao: https://github.com/greenrobot/greenDAO
- 4.数据库调试工具：https://github.com/amitshekhariitbhu/Android-Debug-Database
- 5.数据存储：https://github.com/orhanobut/hawk
- 6.ORM数据库框架：https://github.com/LitePalFramework/LitePal


##### 侧滑菜单：
- 1.MaterialDrawer：https://github.com/mikepenz/MaterialDrawer
- 2.SwipeBackLayout（类QQ）：https://github.com/ikew0ng/SwipeBackLayout
- 3./BGASwipeBackLayout-Android侧滑返回：https://github.com/bingoogolapple/BGASwipeBackLayout-Android


##### 弹窗：
- 1.Toasty：https://github.com/GrenderG/Toasty
- 2.可定制的Dialog： https://github.com/afollestad/material-dialogs
- 3.一行代码下载Apk: https://github.com/teprinciple/UpdateAppDemo
- 4.EasyPopup弹窗：https://github.com/zyyoona7/EasyPopup


##### 图片：
- 1.gif图片：https://github.com/koral--/android-gif-drawable
- 2.圆形图片：https://github.com/hdodenhof/CircleImageView
- 3.图片剪裁：https://github.com/Yalantis/uCrop
- 4.glide图片下载：https://github.com/bumptech/glide
- 5.picasso图片下载: https://github.com/square/picasso
- 6.图片压缩Luban： https://github.com/Curzibn/Luban
- 7.图片压缩Compressor：https://github.com/zetbaitsu/Compressor


##### 布局：
- 1.flex-box: https://github.com/google/flexbox-layout
- 2.flowLayout: https://github.com/hongyangAndroid/FlowLayout


##### pickerView
- 1.仿iOSPickerView: https://github.com/Bigkoo/Android-PickerView
- 2.安卓PickerView: https://github.com/gzu-liyujiang/AndroidPicker
- 3.文件选择器：https://github.com/DroidNinja/Android-FilePicker


##### 轮播图：
- 1.ConvenientBanner：https://github.com/Bigkoo/Android-ConvenientBanner
- 2.BGABanner：https://github.com/bingoogolapple/BGABanner-Android
- 3.MZBannerView：https://github.com/pinguo-zhouwei/MZBannerView
- 4.RecyclerBanner：https://github.com/amy993/RecyclerBanner
- 5.XBanner：https://github.com/xiaohaibin/XBanner
- 6.banner 已经有段时间不维护了: https://github.com/youth5201314/banner
- 7.引导页：https://github.com/Cleveroad/SlidingTutorial-Android


##### 跑马灯
- 1.MarqueeView: https://github.com/sunfusheng/MarqueeView
- 2.MarqueeViewLibrary: https://github.com/gongwen/MarqueeViewLibrary
- 2.XMarqueeView: https://github.com/xiaohaibin/XMarqueeView


##### webView
- 1.高度封装的webView: https://github.com/Justson/AgentWeb


##### 角标
- 1.BadgeView 包含拖拽：https://github.com/bingoogolapple/BGABadgeView-Android
- 2.桌面图片角标：https://github.com/leolin310148/ShortcutBadger


##### 文件下载框架：
- 1.FileDownloader：https://github.com/lingochamp/FileDownloader


##### 图片或视频选择
- 1.Matisse由知乎推出的图片选择器：https://github.com/zhihu/Matisse
- 2.仿微信图片选择器：https://github.com/donglua/PhotoPicker
- 3.图片选择器：https://github.com/Lichenwei-Dev/ImagePicker
- 4.图片选取器：https://github.com/donkingliang/ImageSelector
- 5.图片选取器：https://github.com/bingoogolapple/BGAPhotoPicker-Android


##### 视频播放器：
- 1.节操播放器：https://github.com/lipangit/JiaoZiVideoPlayer
- 2.多功能视频播放器：https://github.com/CarGuo/GSYVideoPlayer


##### 蓝牙
- 1.FastBle：https://github.com/Jasonchenlijian/FastBle
- 2.蓝牙: https://github.com/Polidea/RxAndroidBle


##### 日志打印：
- 1.logger：https://github.com/orhanobut/logger


##### fragment:
- 1.Fragmentation: https://github.com/YoKeyword/Fragmentation


##### 注解：
- 1.butterknife：https://github.com/JakeWharton/butterknife
- 2.注入dagger2: https://github.com/google/dagger


##### Rx
- 1.控件的事件绑定RxBinding：https://github.com/JakeWharton/RxBinding
- 3.工具库：https://github.com/vondear/RxTool
- 4.RxJava:  https://github.com/ReactiveX/RxJava
- 5.RxAndroid:  https://github.com/ReactiveX/RxAndroid
- 6.Rx生命周期：https://github.com/trello/RxLifecycle
- 7.事件总线RxBus: https://github.com/AndroidKnife/RxBus


##### 图表
- 1.MPAndroidChart：https://github.com/PhilJay/MPAndroidChart
- 2.表格：https://github.com/huangyanbin/smartTable


##### JSON
- 1.fastjson: https://github.com/alibaba/fastjson
- 2.gson: https://github.com/google/gson


##### 动画
- 1.recyclerview-animators：https://github.com/wasabeef/recyclerview-animators
- 2.ViewAnimator动画：https://github.com/florent37/ViewAnimator


#### 三、优化
- 1.内测泄漏检测：https://github.com/square/leakcanary
- 2.网络调试工具需要翻墙：https://github.com/facebook/stetho
- 3.dex65535文件报错multidex：implementation 'com.android.support:multidex:1.0.1'
- 4.安卓插件生成模型：gsonformat 支持gson、fastjson等
- 5.图片高质量压缩：https://www.jianshu.com/p/f305fb008ab6
- 6.apk优化：https://www.jianshu.com/p/244910d4e942
- 7.java文件头的注释修改：https://www.jianshu.com/p/6dde8bcd5ed7
- 8..9图片制作：https://www.jianshu.com/p/803b62730218
- 9.apk下载安装：https://www.jianshu.com/p/e3b33f57bc8d


#### 四、具有参考性的项目
- 1.今日头条：https://github.com/chaychan/TouTiao
- 2.足球项目：https://github.com/wangjian1154/FootballApp
- 3.*新闻类很好的mvp架构：https://github.com/Peakmain/gankzhihu
- 4.DialogFragment弹窗封装：https://github.com/developerHaoz/DialogFragmentDemos
- 5.万能的Dialog：https://github.com/AnthonyCoder/PowerfulDialog
- 6.通用的PopupWindow: https://github.com/crazyqiang/AndroidStudy
- 7.建造者模式衍生的全局Dialog: https://github.com/YuanTiger/Design-Pattern/blob/master/app/src/main/java/com/my/designdemo/design/builder/dialog/DialogProduct.java
- 8.tags: https://github.com/ithedan/CustomRadioGroupTag
- 9.aidl 不同进程之间进行数据通信
- 10.全面屏、刘海屏适配：https://blog.csdn.net/DJY1992/article/details/80689632  &   https://github.com/LuckyYangChen/AdaptNotch


#### 五、适配
##### 屏幕适配
- AndroidAutoSize: https://github.com/JessYanCoding/AndroidAutoSize
- 高效UI适配：https://www.jianshu.com/p/b031c40cdd76
- 好用的UI适配方案：https://www.jianshu.com/p/1302ad5a4b04
- 参考：https://www.jianshu.com/p/1302ad5a4b04

##### 全面屏和刘海屏适配
1.全面屏适配
`AndroidManifest.xml`中的`application`配置 `android:resizeableActivity="true"`
```
<meta-data android:name="android.notch_support" android:value="true"/>
<meta-data
android:name="android.max_aspect"
android:value="2.4" />
```
2.刘海屏适配
```
if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
    if (getWindow() != null) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
        getWindow().setAttributes(lp);
    }
}
```
布局调整 参考：https://github.com/LuckyYangChen/AdaptNotch

##### android 9.0 （Build.VERSION_CODES.P）适配
参考：https://www.jianshu.com/p/9e9e902ea039

1.网络适配
`res`下添加`xml`目录，并添加`network_security_config.xml`文件， 最后在`AndroidManifest.xml`中的`application`配置 `android:networkSecurityConfig="@xml/network_security_config"`
```
<network-security-config>
<base-config cleartextTrafficPermitted="true" />
</network-security-config>
```

##### android 8.0适配
- 参考：https://blog.csdn.net/qq_17766199/article/details/80965631
- 应用图片适配：https://www.jianshu.com/p/e593515cbf87

##### android 7.0适配
- 参考：https://blog.csdn.net/qq_17766199/article/details/77404712

##### android 6.0适配
1.动态权限申请适配


