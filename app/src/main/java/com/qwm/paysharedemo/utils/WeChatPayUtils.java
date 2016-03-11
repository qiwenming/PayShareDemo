package com.qwm.paysharedemo.utils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.qwm.paysharedemo.constants.Constants;

import java.util.LinkedList;
import java.util.List;

/**
 * @author qiwenming
 * @date 2016/3/11 0011 下午 4:21
 * @ClassName: WeChatPayUtils
 * @PackageName: com.qwm.paysharedemo.utils
 * @Description: 微信支付相关的类
 */
public class WeChatPayUtils {




    /**
     * 获取订单请求的xml数据
     * @return
     */
    private String genProductArgs(String trade_type) {
        List<NameValuePair> packageParams = new LinkedList<NameValuePair>();
        packageParams.add(new BasicNameValuePair("appid", Constants.WX_APPID));
        packageParams.add(new BasicNameValuePair("appkey", Constants.WX__KEY));
        packageParams.add(new BasicNameValuePair("body", "霸气的小明(qiwenmingshiwo)"));
        packageParams.add(new BasicNameValuePair("input_charset", "UTF-8"));
        packageParams.add(new BasicNameValuePair("mch_id", Constants.WX_MCH_ID));
        packageParams.add(new BasicNameValuePair("nonce_str",StringUtils.genNonceStr()));
        packageParams.add(new BasicNameValuePair("notify_url","http://www.baidu.com"));
        packageParams.add(new BasicNameValuePair("out_trade_no",StringUtils.genNonceStr()));
        packageParams.add(new BasicNameValuePair("spbill_create_ip","127.0.0.1"));
        packageParams.add(new BasicNameValuePair("total_fee", "1"));
        packageParams.add(new BasicNameValuePair("trade_type",trade_type));
        //调用genXml()方法获得xml格式的请求数据
        return genXml(packageParams);
    }

    /**
     * 生成xml格式的请求参数
     *
     * @param params
     *            参数集合
     * @return
     */
    private String genXml(List<NameValuePair> params) {
        StringBuilder sb2 = new StringBuilder();
        sb2.append("<?xml version='1.0' encoding='UTF-8' standalone='yes' ?><xml>");
        for (int i = 0; i < params.size(); i++) {
            // sb2是用来做请求的xml参数
            sb2.append("<" + params.get(i).getName() + ">");
            sb2.append(params.get(i).getValue());
            sb2.append("</" + params.get(i).getName() + ">");
        }
        sb2.append("<sign><![CDATA[");
        sb2.append(genSign(params));
        sb2.append("]]></sign>");
        sb2.append("</xml>");

        // 这一步最关键 我们把字符转为 字节后,再使用“ISO8859-1”进行编码，得到“ISO8859-1”的字符串
        try {
            return new String(sb2.toString().getBytes(), "ISO8859-1");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 生成签名
     *
     * @param params
     *            参数集合
     * @return
     */
    private String genSign(List<NameValuePair> params) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < params.size(); i++) {
            // sb是用来计算签名的
            sb.append(params.get(i).getName());
            sb.append('=');
            sb.append(params.get(i).getValue());
            sb.append('&');
        }
        sb.append("key=");
        sb.append(Constants.WX_PRIVATE_KEY);
        String packageSign = "";
        // 生成签名
        packageSign = MD5.getMessageDigest(sb.toString().getBytes()).toUpperCase();
        return packageSign;
    }



}
