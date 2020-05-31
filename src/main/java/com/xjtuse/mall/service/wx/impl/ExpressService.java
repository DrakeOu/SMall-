package com.xjtuse.mall.service.wx.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xjtuse.mall.common.ExpressInfo;
import com.xjtuse.mall.common.ExpressProperties;
import com.xjtuse.mall.utils.HttpUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ExpressService {

    private final Log logger = LogFactory.getLog(ExpressService.class);
    private String ReqURL = "http://api.kdniao.com/Ebusiness/EbusinessOrderHandle.aspx";
    private ExpressProperties properties;

    public ExpressService() {
    }

    public ExpressProperties getProperties() {
        return this.properties;
    }

    public void setProperties(ExpressProperties properties) {
        this.properties = properties;
    }

    public String getVendorName(String vendorCode) {
        Iterator var2 = this.properties.getVendors().iterator();

        Map item;
        do {
            if (!var2.hasNext()) {
                return null;
            }

            item = (Map)var2.next();
        } while(!((String)item.get("code")).equals(vendorCode));

        return (String)item.get("name");
    }

    public List<Map<String, String>> getVendors() {
        return this.properties.getVendors();
    }

    public ExpressInfo getExpressInfo(String expCode, String expNo) {
        if (!this.properties.isEnable()) {
            return null;
        } else {
            try {
                String result = this.getOrderTracesByJson(expCode, expNo);
                ObjectMapper objMap = new ObjectMapper();
                ExpressInfo ei = (ExpressInfo)objMap.readValue(result, ExpressInfo.class);
                ei.setShipperName(this.getVendorName(expCode));
                return ei;
            } catch (Exception var6) {
                this.logger.error(var6.getMessage(), var6);
                return null;
            }
        }
    }

    private String getOrderTracesByJson(String expCode, String expNo) throws Exception {
        String requestData = "{'OrderCode':'','ShipperCode':'" + expCode + "','LogisticCode':'" + expNo + "'}";
        Map<String, String> params = new HashMap();
        params.put("RequestData", URLEncoder.encode(requestData, "UTF-8"));
        params.put("EBusinessID", this.properties.getAppId());
        params.put("RequestType", "1002");
        String dataSign = this.encrypt(requestData, this.properties.getAppKey(), "UTF-8");
        params.put("DataSign", URLEncoder.encode(dataSign, "UTF-8"));
        params.put("DataType", "2");
        String result = HttpUtil.sendPost(this.ReqURL, params);
        return result;
    }

    private String MD5(String str, String charset) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(str.getBytes(charset));
        byte[] result = md.digest();
        StringBuilder sb = new StringBuilder(32);

        for(int i = 0; i < result.length; ++i) {
            int val = result[i] & 255;
            if (val <= 15) {
                sb.append("0");
            }

            sb.append(Integer.toHexString(val));
        }

        return sb.toString().toLowerCase();
    }

    private String encrypt(String content, String keyValue, String charset) {
        if (keyValue != null) {
            content = content + keyValue;
        }

        try {
            byte[] src = this.MD5(content, charset).getBytes(charset);
            return Base64Utils.encodeToString(src);
        } catch (Exception var6) {
            this.logger.error(var6.getMessage(), var6);
            return null;
        }
    }
}
