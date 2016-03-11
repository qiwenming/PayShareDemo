package com.qwm.paysharedemo.utils;
import org.apache.http.NameValuePair;
import com.qwm.paysharedemo.constants.Constants;

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
     * 生成xml格式的请求参数
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
