package com.ghs.ghshome;

import android.content.Context;
import android.support.annotation.Keep;
import android.support.multidex.MultiDex;
import android.util.Log;

import com.taobao.sophix.PatchStatus;
import com.taobao.sophix.SophixApplication;
import com.taobao.sophix.SophixEntry;
import com.taobao.sophix.SophixManager;
import com.taobao.sophix.listener.PatchLoadStatusListener;


/**
 *
 * Author:wang_sir
 * Time:2019/3/26 15:45
 * Description:This is HotfixStubApplication
 */
public class HotfixStubApplication extends SophixApplication {
    private final String TAG = "HotfixStubApplication";

    private static String  HOTFIX_APPID = "25008654";
    private static String  HOTFIX_APP_SECRET = "1a569744fff7d1c03bd1cf309c979486";
    private static String  HOTFIX_RSA = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCKO4z3DSqT7zUhhJfMJ9hi5q4K1el5WRfkn6/qyC8N4O20jR5/I7aC0ZP/7XJJSrDoMmP5oPqk4LxfwKofyXgBBrY+5pR6awU4//pZsMNXShTgs+nAFWf++SHbcq+FtXM7kdmfkdaV+tSJ6EWVLYmqaNnAtqnawZxqiBUsWQm6LX5LDvHxuAyCtfoL84qJZVBKvNLG7rltwz9Axw7W7xPPmPAczpnziSHL6l6ykoRF4krWEx2VqSjTAe31qvVy2MR7awujjTmTBg7oqx1niN8jBuuU/IcCgo6Rbc/NTonGN1TLWCn30O22xUrPZUB8q+sxn6MUeg/YsUhGhnIUDpWFAgMBAAECggEAZ/y2ZZ9E+XaInIdXfiA3Wuc35DTZZw0LuVQbjV48b49Wom1KlNAz2yN3154zTElg3bwSk1ZzVxcs/kc8c58mpHb9IEJrTdA2ZJPELDaaLI8xN3yAipxP2HJn8uLKgaZnYlesw6p9J+RCgN/3gGp6VnR74spG6qadWVK3wt3UQHYYRkWCD17rusiZNvonf2xGcLSUfjH5/mXW5t20OZ4Wlqw3XcA8gTdtv23HBu7/IRBUYzKc7H/siYETKHSYt50gEAyehB4v1vtQM2Q6YvsQBqaVh6oqkMvBnp1f1X5bM/L2ll8KQ8H1c7/ocl8PP7b345I8RLPzprei4S1Eqg/9fQKBgQDsdANwsNxoJuhw+dHuMsf/cqna/IEJeL+balt2bDt8RRiRgig5hIu4yTL+K+TVGDI3n19Tp/2N1BR0JCoG1RY4jhOMRJMu3pD4cpxohfrxeEBUMtfhtNfDAEFGGN+SGrvlPce+7xUuUCmFWj1VxwZHuY2Ty1T6SRgHBfy+fDclmwKBgQCVqOzrNsolzRPb1SMeiM6qulitbcT3+KEOm/Qo+2reXoVtS0soN1iuOoYI4GqtwrUmrDs/T8JaoE9rl9YDBvkbWc5qVPzbPmXWDcRm1ywNnQhxX08/7w0gCcrw2JFWu/fBBzkrOoTQax/JabbJrOrKVTD2L191HzbA5bqkfclzXwKBgCCFnGuFh+szXskbM0ql5Wg3JzGdhUn+N6xypn3VSSFAh66LzmiK2ITj94WKGAFPQKjx3LSu6Gr3fpCy4Kzr5hCFSh8C15OcvSfO68BcnTVVKtPp4lODAiIqCNBRX37C+pD/PFgAnlMuZ2eWD9WaeDypQtXPI7MBFrLwmeDBSPx9AoGAV7OPauNHoNFF0uxa0TjwiyH6d189LudXUl1XvnBfFwKjBAI8f8u3Pxpyk18XhM32JDyH17kgtsT85ZsrwchjUIDXMn6tUrmFKeQVdQKhATVFzlJA+cFXmq/CalMvEHnEXSyjds6rKAuuq/Q0NokLahh0QcBPGy54gp/dOSmxVNcCgYAsjowJZId0pLKzDOJZiwZ+YsSpd0MLmlyYSFCuIoYB5YdbD6c4tzxPvFxgLqJvUeKUQLoAgHWixzdb2/WE8ODxnR63djUdTt0ZbHHYa6WWA55XFs3eAMaCYSICQHXTaxYzpBA9VS2eG6Jt9DmZnmXY+rDagCL8QKocNsnlFJZJeA==";

    // 此处SophixEntry应指定真正的Application，并且保证RealApplicationStub类名不被混淆。
    @Keep
    @SophixEntry(MyApplication.class)
    static class RealApplicationStub {}
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
//         如果需要使用MultiDex，需要在此处调用。
         MultiDex.install(this);
        initSophix();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (!MyApplication.IsDeBugMoDe) {
            SophixManager.getInstance().queryAndLoadNewPatch();
        }

    }

    private void initSophix() {
        String appVersion = "0.0.0";
        try {
            appVersion = this.getPackageManager()
                    .getPackageInfo(this.getPackageName(), 0)
                    .versionName;
        } catch (Exception e) {
        }
        final SophixManager instance = SophixManager.getInstance();
        instance.setContext(this)
                .setAppVersion(appVersion)
                .setSecretMetaData(HOTFIX_APPID, HOTFIX_APP_SECRET, HOTFIX_RSA)
                .setEnableDebug(true)
                .setEnableFullLog()
                .setPatchLoadStatusStub(new PatchLoadStatusListener() {
                    @Override
                    public void onLoad(final int mode, final int code, final String info, final int handlePatchVersion) {
                        if (code == PatchStatus.CODE_LOAD_SUCCESS) {
                            Log.i(TAG, "sophix load patch success!");
                        } else if (code == PatchStatus.CODE_LOAD_RELAUNCH) {
                            // 如果需要在后台重启，建议此处用SharePreference保存状态。
                            Log.i(TAG, "sophix preload patch success. restart app to make effect.");
                        }
                    }
                }).initialize();
    }
}
