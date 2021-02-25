/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014-2021 Tuya Inc.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.tuya.appsdk.sample.home.main

import android.content.Context

import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.tuya.appsdk.sample.resource.HomeModel

import com.tuya.appsdk.sample.user.R
import com.tuya.smart.home.sdk.TuyaHomeSdk
import com.tuya.smart.home.sdk.bean.HomeBean
import com.tuya.smart.home.sdk.callback.ITuyaGetHomeListCallback
import com.tuya.smart.home.sdk.callback.ITuyaHomeResultCallback

/**
 * Home Management Widget
 *
 * @author qianqi <a href="mailto:developer@tuya.com"/>
 * @since 2021/1/9 5:06 PM
 */
class HomeFuncWidget {
    lateinit var tvCurrentHomeName: TextView

    lateinit var mContext: Context
    fun render(context: Context): View {
        mContext = context;
        val rootView =
            LayoutInflater.from(context).inflate(R.layout.home_view_func, null, false)
        initView(rootView)
        return rootView
    }

    private fun initView(rootView: View) {
        tvCurrentHomeName = rootView.findViewById<TextView>(R.id.tv_current_home_name);
        updateHomeInfoIfNeed()
    }


    private fun updateHomeInfoIfNeed() {
        TuyaHomeSdk.getHomeManagerInstance().queryHomeList(object : ITuyaGetHomeListCallback {
            override fun onSuccess(homeBeans: MutableList<HomeBean>?) {
                if (homeBeans?.size == 0) {
                    createDefaultHomeByUserMobile()
                } else {
                    tvCurrentHomeName.text = homeBeans?.get(0)?.name
                    HomeModel.INSTANCE.setCurrentHome(
                        mContext,
                        homeBeans?.get(0)?.homeId ?: 0
                    )
                }
            }

            override fun onError(errorCode: String?, error: String?) {


            }
        })
    }

    private fun createDefaultHomeByUserMobile() {
        val user = TuyaHomeSdk.getUserInstance().user
        TuyaHomeSdk.getHomeManagerInstance().createHome(user?.mobile.toString(),
            120.52,
            30.40,
            "Shanghai",
            arrayListOf(),
            object : ITuyaHomeResultCallback {
                override fun onSuccess(bean: HomeBean?) {
                    tvCurrentHomeName.text = bean?.name
                    HomeModel.INSTANCE.setCurrentHome(
                        mContext,
                        bean?.homeId ?: 0
                    )
                }

                override fun onError(errorCode: String?, errorMsg: String?) {
                }
            })
    }
}