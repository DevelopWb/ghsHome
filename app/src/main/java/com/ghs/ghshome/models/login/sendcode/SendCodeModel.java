package com.ghs.ghshome.models.login.sendcode;

import android.os.Message;

import com.ghs.ghshome.base.BaseHandler;

/**
 * Author:wang_sir
 * Time:2018/7/16 18:18
 * Description:This is SendCodeModel
 */
public class SendCodeModel implements ISendCode,BaseHandler.BaseHandlerCallBack{


    private boolean run = false;
    private  ISendCode.IUpdateView iUpdateView;
    private BaseHandler myHandler = new BaseHandler(this);

    public SendCodeModel(IUpdateView iUpdateView) {
        this.iUpdateView = iUpdateView;
    }

    @Override
    public void initGetTestCodeButtonStatus() {
        //开启线程去更新button的text
        new Thread() {
            @Override
            public void run() {
                int totalTime = 60;
                for (int i = 0; i < totalTime; i++) {
                    if (!run) {
                        Message message = myHandler.obtainMessage(0x01);
                        message.arg1 = totalTime - i;
                        myHandler.sendMessage(message);
                        try {
                            sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }
                myHandler.sendEmptyMessage(0x02);
            }
        }.start();
    }

    @Override
    public void checkCodeReceived() {
        run = true;
        myHandler.sendEmptyMessage(-1);
        myHandler.removeMessages(0x01);
        myHandler.removeMessages(0x02);
    }

    @Override
    public void callBack(Message msg) {
        switch (msg.what) {
            case 0x01:
                iUpdateView.startTiming(msg.arg1);
                break;
            case 0x02:
                iUpdateView.endTiming(-1);
                break;
            default:
                break;
        }
    }



}
