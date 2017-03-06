package com.example.chen.dynamicclasshookdemo;

import android.app.Application;
import android.content.Context;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * Created by chen on 2017/3/6.
 */

public class Main implements IXposedHookLoadPackage{
    @Override
    public void handleLoadPackage(final XC_LoadPackage.LoadPackageParam loadPackageParam) {
        if (!loadPackageParam.packageName.equals("com")) {
            return;
        }
        /**
         * 分包技术can't find class 的解决
         */
        XposedHelpers.findAndHookMethod(Application.class, "attach", Context.class, new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                ClassLoader cl = ((Context) param.args[0]).getClassLoader();
                if (cl != null) {
                    /*加载hook类*/
                }
            }
        });
    }
}
