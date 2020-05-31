package com.xjtuse.mall.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;

public class HttpUtil {

    private static final Log logger = LogFactory.getLog(HttpUtil.class);

    public HttpUtil() {
    }

    public static String sendPost(String url, Map<String, String> params) {
        OutputStreamWriter out = null;
        BufferedReader in = null;
        StringBuilder result = new StringBuilder();

        try {
            URL realUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection)realUrl.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.connect();
            out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
            if (params != null) {
                StringBuilder param = new StringBuilder();
                Iterator var8 = params.entrySet().iterator();

                while(var8.hasNext()) {
                    Map.Entry<String, String> entry = (Map.Entry)var8.next();
                    if (param.length() > 0) {
                        param.append("&");
                    }

                    param.append((String)entry.getKey());
                    param.append("=");
                    param.append((String)entry.getValue());
                }

                out.write(param.toString());
            }

            out.flush();
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

            String line;
            while((line = in.readLine()) != null) {
                result.append(line);
            }
        } catch (Exception var18) {
            logger.error(var18.getMessage(), var18);
        } finally {
            try {
                if (out != null) {
                    out.close();
                }

                if (in != null) {
                    in.close();
                }
            } catch (IOException var17) {
                logger.error(var17.getMessage(), var17);
            }

        }

        return result.toString();
    }
}
