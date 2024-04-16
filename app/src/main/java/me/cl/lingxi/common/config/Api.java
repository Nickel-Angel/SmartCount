package me.cl.lingxi.common.config;

import me.cl.lingxi.BuildConfig;


public class Api {

    /**
     * token
     */
    public static final String X_APP_TOKEN = "X-App-Token";
    private static String serverHost="long-if.top:8090";
    private static String server="long-if.top:8000";
//    private static String serverHost="10.0.2.2:8090";
//    private static String server="10.0.2.2:8000";
    /**
     * 收束gradle的flavor控制，将url变量在此接管
     */
    public static String baseUrl = "http://"+serverHost+"/lingxi";
    public static String rssUrl = "http://"+server+"/rss/lingxi-test";

    static {
        String flavor = BuildConfig.FLAVOR;
        switch (flavor) {
            case "alpha":
                baseUrl = "http://"+serverHost+"/lingxi";
                rssUrl = "http://"+server+"/rss/lingxi-test";
                break;
            case "local":
                baseUrl = "http://"+serverHost+"/lingxi";
                rssUrl = "http://"+server+"/rss/lingxi-test";
                break;
            case "online":
                baseUrl = "http://"+serverHost+"/lingxi";
                rssUrl = "http://"+server+"/rss/lingxi-test";
                break;
        }
    }

    /**
     * 用户注册
     */
    public static String userRegister = baseUrl + "/user/register";
    /**
     * 用户登录
     */
    public static String userLogin = baseUrl + "/user/login";
    /**
     * 重置密码
     */
    public static String resetPassword = baseUrl + "/user/reset";
    /**
     * 更新用户信息
     */
    public static String updateUser = baseUrl + "/user/update";
    /**
     * 获取用户信息
     */
    public static String userInfo = baseUrl + "/user/info";
    /**
     * 精准查询用户信息
     */
    public static String searchUser = baseUrl + "/user/search";
    /**
     * 查询用户信息
     */
    public static String queryUser = baseUrl + "/user/query";
    /**
     * 融云用户列表
     */
    public static String listRcUser = baseUrl + "/user/rc/list";
    /**
     * 动态列表
     */
    public static String pageFeed = baseUrl + "/feed/page";
    /**
     * 发布动态
     */
    public static String saveFeed = baseUrl + "/feed/save";
    /**
     * 查看动态
     */
    public static String viewFeed = baseUrl + "/feed/view";
    /**
     * 与我相关
     */
    public static String relevant = baseUrl + "/feed/relevant";
    /**
     * 我的回复
     */
    public static String mineReply = baseUrl + "/feed/mine/reply";
    /**
     * 新增动态操作,如点赞
     */
    public static String saveAction = baseUrl + "/feed/action/save";
    /**
     * 移除动态操作,如取消赞
     */
    public static String removeAction = baseUrl + "/feed/action/remove";
    /**
     * 动态评论列表
     */
    public static String pageComment = baseUrl + "/feed/comment/page";
    /**
     * 新增动态评论
     */
    public static String saveComment = baseUrl + "/feed/comment/save";
    /**
     * 获取最新app版本
     */
    public static String latestVersion = baseUrl + "/app/version/latest";
    /**
     * 上传用户图片
     */
    public static String uploadUserImage = baseUrl + "/rss/upload/user/image";
    /**
     * 上传动态图片
     */
    public static String uploadFeedImage = baseUrl + "/rss/upload/feed/image";
    /**
     * 未读条数
     */
    public static String unreadComment = baseUrl + "/feed/comment/unread";
    /**
     * 更新未读为已读
     */
    public static String updateUnread = baseUrl + "/feed/comment/unread/update";
    /**
     * 保存写给未来
     */
    public static String saveFuture = baseUrl + "/future/save";
    /**
     * 未来日记
     */
    public static String pageFuture = baseUrl + "/future/page";
    /**
     * 动态删除
     */
    public static String removeFeed = baseUrl + "/feed/remove";
    /**
     * 话题查询
     */
    public static String queryTopic = baseUrl + "/feed/topic/query";

    public static String positive= baseUrl+"/feed/emotion";
}
