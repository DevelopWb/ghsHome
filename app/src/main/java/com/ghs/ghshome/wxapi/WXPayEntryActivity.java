package com.ghs.ghshome.wxapi;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.ghs.ghshome.tools.ActivityResultManager;
import com.ghs.ghshome.tools.ConfigUtil;
import com.ghs.ghshome.tools.PubUtil;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;


public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {

    private static final String TAG = "MicroMsg.SDKSample.WXPayEntryActivity";

    private IWXAPI api;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        api = WXAPIFactory.createWXAPI(this, ConfigUtil.WEI_XIN_APPID);
        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {
    }

    @Override
    public void onResp(BaseResp resp) {

        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {

            if (0 == PubUtil.WEI_XIN_PAY_ENTER) {//账单管理
                switch (resp.errCode) {
                    case 0://正常完成支付
                        PubUtil.WEI_XIN_PAY_TAG = ActivityResultManager.WEI_XIN_PAY_SUCCESS;
                        break;
                    case -1://支付异常
                        PubUtil.WEI_XIN_PAY_TAG = ActivityResultManager.WEI_XIN_PAY_ERROR;
                        break;
                    case -2://用户取消支付
                        PubUtil.WEI_XIN_PAY_TAG = ActivityResultManager.WEI_XIN_PAY_ERROR;
                        break;
                    default:
                        break;
                }
                finish();
            } else if (1 == PubUtil.WEI_XIN_PAY_ENTER) {//车位管理 支付
                switch (resp.errCode) {
                    case 0://正常完成支付
                        PubUtil.CAR_WEI_XIN_PAY_TAG = ActivityResultManager.WEI_XIN_PAY_SUCCESS;
                        startToPayFeeActivity();
                        break;
                    case -1://支付异常
                        PubUtil.CAR_WEI_XIN_PAY_TAG = ActivityResultManager.WEI_XIN_PAY_ERROR;
                        startToPayFeeActivity();

                        break;
                    case -2://用户取消支付
                        PubUtil.CAR_WEI_XIN_PAY_TAG = ActivityResultManager.WEI_XIN_PAY_ERROR;
                        startToPayFeeActivity();

                        break;
                    default:
                        break;
                }
            }


        }
    }


    /**
     * 支付后的跳转
     */
    private void startToPayFeeActivity() {
//        Intent intent_success = null;
//        switch (PubUtil.CAR_WEI_XIN_PAY_TAG) {
//            case ActivityResultManager.WEI_XIN_PAY_SUCCESS:
//                intent_success  = new Intent(this, CarParkActivity.class);
//                break;
//            case ActivityResultManager.WEI_XIN_PAY_ERROR:
//                intent_success  = new Intent(this, PayFeeActivity.class);
//                break;
//            default:
//                break;
//        }
//        intent_success  = new Intent(this, PayFeeActivity.class);
//        startActivity(intent_success);
        finish();
    }


}