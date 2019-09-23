package com.ghs.ghshome.base.network.okgo;

import com.ghs.ghshome.base.network.networkProxy.HttpStaticProxyInterface;
import com.ghs.ghshome.custom.LoadingDialog;
import com.ghs.ghshome.tools.Contract;
import com.ghs.ghshome.tools.PubUtil;
import com.ghs.ghshome.tools.UserInfoUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.util.Map;

/**
 * Author:wang_sir
 * Time:2019/4/3 11:36
 * Description:This is OkgoTool  网络请求库的封装
 */
public class OkgoTool<T> implements HttpStaticProxyInterface {
    @Override
    public void postToNetwork(String urlPath, Map map, final NetResponseCallBack netCallBackInterface) {
        OkGo.<String>post(Contract.BASE_URL + urlPath)
                .params(map, false)
                .headers("Authorization", "bearer " + UserInfoUtil.getInstance().getUserToken())
                .headers("api-version", "1.5.0")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String content = response.body();
                        requestOnSuccess(content, netCallBackInterface, PubUtil.getServerMessage(content));
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        requestOnError(response, netCallBackInterface);
                    }
                });
    }


    @Override
    public void getToNetwork(String urlPath, Map map, final NetResponseCallBack netCallBackInterface) {
        OkGo.<String>get(Contract.BASE_URL + urlPath)
                .params(map, false)
                .headers("Authorization", "bearer " + UserInfoUtil.getInstance().getUserToken())
                .headers("api-version", "1.5.0")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String content = response.body();
                        requestOnSuccess(content, netCallBackInterface, PubUtil.getServerMessage(content));
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        requestOnError(response, netCallBackInterface);
                    }
                });
    }


    @Override
    public void getToNetwork(String urlPath, Map map, final NetResponseCallBack netCallBackInterface, boolean returnErrorCode) {
        OkGo.<String>get(Contract.BASE_URL + urlPath)
                .params(map, false)
                .headers("Authorization", "bearer " + UserInfoUtil.getInstance().getUserToken())
                .headers("api-version", "1.5.0")

                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String content = response.body();
                        requestOnSuccess(content, netCallBackInterface, String.valueOf(PubUtil.getServerCode(content)));
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        requestOnError(response, netCallBackInterface);
                    }
                });
    }

    @Override
    public void postToNetwork(String urlPath, Map map, final NetResponseCallBack netCallBackInterface, boolean returnErrorCode) {
        OkGo.<String>post(Contract.BASE_URL + urlPath)
                .params(map, false)
                .headers("Authorization", "bearer " + UserInfoUtil.getInstance().getUserToken())
                .headers("api-version", "1.5.0")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String content = response.body();
                        requestOnSuccess(content, netCallBackInterface, String.valueOf(PubUtil.getServerCode(content)));
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        requestOnError(response, netCallBackInterface);
                    }
                });
    }

    /**
     * 请求异常
     *
     * @param response
     * @param netCallBackInterface
     */
    private void requestOnError(Response<String> response, NetResponseCallBack netCallBackInterface) {
        LoadingDialog.getInstance().dismissProgress();
        if (netCallBackInterface != null) {
            if (response.body() != null) {
                netCallBackInterface.onError(response.body());
            } else {
                netCallBackInterface.onError("服务器错误");

            }
        }
    }

    /**
     * 请求成功
     *
     * @param content
     * @param netCallBackInterface
     * @param errMsg
     */
    private void requestOnSuccess(String content, NetResponseCallBack netCallBackInterface, String errMsg) {
        LoadingDialog.getInstance().dismissProgress();
        if (PubUtil.initContent(content)) {
            if (netCallBackInterface != null) {
                if (netCallBackInterface != null) {
                    netCallBackInterface.onSuccess(content);
                }
            }
        } else {

            if (netCallBackInterface != null) {
                if (PubUtil.getServerCode(content) == 4000 ||PubUtil.getServerCode(content) == 4001) {
                    return;
                }
                netCallBackInterface.onError(errMsg);
            }
        }
    }
}
