package com.hyy.hytool;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import com.hyy.htool.HyConstTool;
import com.hyy.htool.HyTool;
import com.hyy.htool.language.HyLanguageService;
import com.hyy.hytool.mvp.modile.DaoMaster;
import com.hyy.hytool.mvp.modile.DaoSession;
import com.tencent.bugly.crashreport.CrashReport;
import com.tencent.imsdk.TIMSdkConfig;
import com.tencent.qcloud.tim.uikit.TUIKit;
import com.tencent.qcloud.tim.uikit.config.CustomFaceConfig;
import com.tencent.qcloud.tim.uikit.config.GeneralConfig;
import com.tencent.qcloud.tim.uikit.config.TUIKitConfigs;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * @Author : HYY
 * 描述:
 * 时间:      2022/05/2022/5/31
 * 软件包名:   com.hyy.hytool
 */
public class ToolGatherApp extends Application {
    public static Retrofit mRetrofit;
    private OkHttpClient.Builder mBuilder;
    private SSLContext sslContext;
    private static final String CER_NAME = "";   //https签名证书name
    public static final int SDKAPPID = 1400384334; // 您的 SDKAppID
    private static DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        // 配置 Config，请按需配置
        TUIKitConfigs configs = TUIKit.getConfigs();
        configs.setSdkConfig(new TIMSdkConfig(SDKAPPID));
        configs.setCustomFaceConfig(new CustomFaceConfig());
        configs.setGeneralConfig(new GeneralConfig());
        TUIKit.init(this, SDKAPPID, configs);
        HyLanguageService.INSTANCE.init(this);
        /**
         * 初始化 HyTool
         *
         */
        HyTool.init(this);
        /**
         * 设置activity跳转动画
         */
        HyConstTool.ANIMATION = "STAGGERED_AND_DOWN";
        HyConstTool.WEIXIN_APP_ID = "";


        initRetrofit();
        storageDao();
        incomeDao();
       CrashReport.initCrashReport(getApplicationContext(), "ffeff5b80a", false);
    }

    public static DaoSession getStorageDao() {
        return daoSession;
    }

    /**
     * 配置登录数据库
     */
    private void storageDao() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "login.db", null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }

    public static DaoSession getIncomeDao() {
        return daoSession;
    }

    /**
     * 配置收入数据库
     */
    private void incomeDao() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "income.db", null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }


    public static Retrofit getRetrofit() {
        return mRetrofit;
    }

    private void initRetrofit() {
        if (TextUtils.isEmpty(CER_NAME)) {
            overlockCard();
            mBuilder = new OkHttpClient.Builder();
            //设置请求超时时长为15秒
            mBuilder.connectTimeout(20, TimeUnit.SECONDS).writeTimeout(20, TimeUnit.SECONDS)
                    .readTimeout(20, TimeUnit.SECONDS);
            mBuilder.sslSocketFactory(sslContext.getSocketFactory())
                    .hostnameVerifier(new HostnameVerifier() {
                        @Override
                        public boolean verify(String hostname, SSLSession session) {
                            return true;
                        }
                    })
                    .cookieJar(new CookieJar() {
                        private final HashMap<String, List<Cookie>> cookieStore = new HashMap<>();

                        @Override
                        public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                            cookieStore.put(url.host(), cookies);
                        }

                        @Override
                        public List<Cookie> loadForRequest(HttpUrl url) {
                            List<Cookie> cookies = cookieStore.get(url.host());
                            return cookies != null ? cookies : new ArrayList<Cookie>();
                        }
                    });
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(Constant.BASEURL)
                    .client(mBuilder.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
        }
    }

    private void overlockCard() {
        TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
            @Override
            public void checkClientTrusted(
                    X509Certificate[] chain,
                    String authType) throws CertificateException {
            }

            @Override
            public void checkServerTrusted(
                    X509Certificate[] chain,
                    String authType) throws CertificateException {
            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                X509Certificate[] x509Certificates = new X509Certificate[0];
                return x509Certificates;
            }
        }};
        try {
            sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
        } catch (Exception e) {

        }
    }
}
