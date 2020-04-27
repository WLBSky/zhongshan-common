package com.hebta.common.util;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.springframework.util.StringUtils;

/**
 * 
 * @Description: HTTP请求
 * @author: yang_lirong
 * @date: 2014-11-6 下午2:46:01
 * @version: v2.0
 */
public class HttpClient {

    private static final int BUFF_SIZE = 4096;
    private HttpURLConnection http;

    public HttpClient(String url) throws IOException {
        http = (HttpURLConnection) new URL(url).openConnection();
    }

    /**
     * GET请求
     * 
     * @return
     */
    public String get() {
        InputStream is = null;
        InputStreamReader reader = null;
        try {
            http.setRequestMethod("GET");
            is = http.getInputStream();
            reader = new InputStreamReader(is);
            return readString(reader);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * POST请求
     * 
     * @param text
     * @return
     */
    public String post(String text) {
        InputStream is = null;
        OutputStream os = null;
        Reader reader = null;
        try {
            http.setRequestMethod("POST");
            if (StringUtils.hasText(text)) {
                http.setDoOutput(true);
                os = http.getOutputStream();
                os.write(text.getBytes());
                os.flush();
            }
            is = http.getInputStream();
            reader = new InputStreamReader(is, "UTF-8");
            return readString(reader);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != os) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != reader) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static String encode(String url) {
        try {
            return URLEncoder.encode(url, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return url;
        }
    }

    /**
     * url 解密
     * 
     * @param url
     * @return
     */
    public static String decode(String url) {
        try {
            return URLDecoder.decode(url, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return url;
        }
    }

    public void disconnect() {
        http.disconnect();
    }

    private String readString(Reader reader) throws IOException {
        StringBuilder buff = new StringBuilder();
        char[] cbuf = new char[BUFF_SIZE];
        for (int len; (len = reader.read(cbuf)) != -1;) {
            buff.append(cbuf, 0, len);
        }
        return buff.toString();
    }
}
