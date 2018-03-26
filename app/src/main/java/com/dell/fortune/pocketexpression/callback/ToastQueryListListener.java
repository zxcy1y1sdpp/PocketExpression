package com.dell.fortune.pocketexpression.callback;

import com.dell.fortune.pocketexpression.model.event.LoadingViewEvent;
import com.dell.fortune.pocketexpression.util.common.ToastUtil;


import org.greenrobot.eventbus.EventBus;

import java.util.List;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListListener;

/**
 * Created by 鹏君 on 2017/1/28.
 */

public abstract class ToastQueryListListener<BatchResult> extends QueryListListener<BatchResult> {

    public ToastQueryListListener() {
    }

    public abstract void onSuccess(List<BatchResult> list);

    @Override
    final public void done(List<BatchResult> list, BmobException e) {
        if (e == null) {
            onSuccess(list);
        } else {

            onFail(e);
        }
    }

    public void onFail(BmobException e) {
        EventBus.getDefault().post(new LoadingViewEvent(false));
        ToastUtil.showToast(e.getMessage());
        e.printStackTrace();
    }
}
