package com.tzq.util;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * HttpServletRequest帮助类
 */
public class RequestUtils {
    private static final Logger log = LoggerFactory
            .getLogger(RequestUtils.class);
    /**
     * 路径分隔符
     */
    public static final String SPT = "/";
    /**
     * 索引页
     */
    public static final String INDEX = "index";
    /**
     * 默认模板
     */
    public static final String DEFAULT = "default";
    /**
     * UTF-8编码
     */
    public static final String UTF8 = "UTF-8";
    /**
     * 提示信息
     */
    public static final String MESSAGE = "message";
    /**
     * cookie中的JSESSIONID名称
     */
    public static final String JSESSION_COOKIE = "JSESSIONID";
    /**
     * url中的jsessionid名称
     */
    public static final String JSESSION_URL = "jsessionid";
    /**
     * HTTP POST请求
     */
    public static final String POST = "POST";
    /**
     * HTTP GET请求
     */
    public static final String GET = "GET";

    /**
     * 获取QueryString的参数，并使用URLDecoder以UTF-8格式转码。如果请求是以post方法提交的，
     * 那么将通过HttpServletRequest#getParameter获取。
     *
     * @param request web请求
     * @param name    参数名称
     * @return
     */
    public static String getQueryParam(HttpServletRequest request, String name) {
        if (StringUtils.isBlank(name)) {
            return null;
        }
        if (request.getMethod().equalsIgnoreCase(POST)) {
            return request.getParameter(name);
        }
        String s = request.getQueryString();
        if (StringUtils.isBlank(s)) {
            return null;
        }
        try {
            s = URLDecoder.decode(s, UTF8);
        } catch (UnsupportedEncodingException e) {
            log.error("encoding " + UTF8 + " not support?", e);
        }
        String[] values = parseQueryString(s).get(name);
        if (values != null && values.length > 0) {
            return values[values.length - 1];
        } else {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public static Map<String, Object> getQueryParams(HttpServletRequest request) {
        Map<String, String[]> map;
        if (request.getMethod().equalsIgnoreCase(POST)) {
            map = request.getParameterMap();
        } else {
            String s = request.getQueryString();
            if (StringUtils.isBlank(s)) {
                return new HashMap<String, Object>();
            }
            try {
                s = URLDecoder.decode(s, UTF8);
            } catch (UnsupportedEncodingException e) {
                log.error("encoding " + UTF8 + " not support?", e);
            }
            map = parseQueryString(s);
        }

        Map<String, Object> params = new HashMap<String, Object>(map.size());
        int len;
        for (Map.Entry<String, String[]> entry : map.entrySet()) {
            len = entry.getValue().length;
            if (len == 1) {
                params.put(entry.getKey(), entry.getValue()[0]);
            } else if (len > 1) {
                params.put(entry.getKey(), entry.getValue());
            }
        }
        return params;
    }

    /**
     * Parses a query string passed from the client to the server and builds a
     * <code>HashTable</code> object with key-value pairs. The query string
     * should be in the form of a string packaged by the GET or POST method,
     * that is, it should have key-value pairs in the form <i>key=value</i>,
     * with each pair separated from the next by a &amp; character.
     * <p/>
     * <p/>
     * A key can appear more than once in the query string with different
     * values. However, the key appears only once in the hashtable, with its
     * value being an array of strings containing the multiple values sent by
     * the query string.
     * <p/>
     * <p/>
     * The keys and values in the hashtable are stored in their decoded form, so
     * any + characters are converted to spaces, and characters sent in
     * hexadecimal notation (like <i>%xx</i>) are converted to ASCII characters.
     *
     * @param s a string containing the query to be parsed
     * @return a <code>HashTable</code> object built from the parsed key-value
     *         pairs
     * @throws IllegalArgumentException if the query string is invalid
     */
    public static Map<String, String[]> parseQueryString(String s) {
        String valArray[] = null;
        if (s == null) {
            throw new IllegalArgumentException();
        }
        Map<String, String[]> ht = new HashMap<String, String[]>();
        StringTokenizer st = new StringTokenizer(s, "&");
        while (st.hasMoreTokens()) {
            String pair = st.nextToken();
            int pos = pair.indexOf('=');
            if (pos == -1) {
                continue;
            }
            String key = pair.substring(0, pos);
            String val = pair.substring(pos + 1, pair.length());
            if (ht.containsKey(key)) {
                String oldVals[] = (String[]) ht.get(key);
                valArray = new String[oldVals.length + 1];
                for (int i = 0; i < oldVals.length; i++) {
                    valArray[i] = oldVals[i];
                }
                valArray[oldVals.length] = val;
            } else {
                valArray = new String[1];
                valArray[0] = val;
            }
            ht.put(key, valArray);
        }
        return ht;
    }

    @SuppressWarnings("unchecked")
    public static Map<String, String> getRequestMap(HttpServletRequest request,
                                                    String prefix) {
        Map<String, String> map = new HashMap<String, String>();
        Enumeration<String> names = request.getParameterNames();
        String name;
        while (names.hasMoreElements()) {
            name = names.nextElement();
            if (name.startsWith(prefix)) {
                request.getParameterValues(name);
                map.put(name.substring(prefix.length()), StringUtils.join(
                        request.getParameterValues(name), ','));
            }
        }
        return map;
    }

    /**
     * 取得URL跟路径，不包含应用上下文名称
     * 如：http://localhost:8080/StreamingWeb/admin/login ->http://localhost:8080
     * @param request
     * @return
     */
    public static String getBaseURL(HttpServletRequest request) {
        String requestURL = request.getRequestURL().toString();     // http://localhost:8080/StreamingWeb/admin/login
        String requestURI = request.getRequestURI();                /// StreamingWeb/admin/login
        return requestURL.replace(requestURI, "");
    }

    /**
     * 取得包含应用上下文名称的路径
     * 如：http://localhost:8080/StreamingWeb/admin/login ->http://localhost:8080/StreamingWeb
     * @param request
     * @return 返回应用
     */
    public static String getApplicationBaseURL(HttpServletRequest request){
       //String contextPath=request.getContextPath(); //  /StreamingWeb
       return getBaseURL(request)+request.getContextPath();
    }
    /**
     * 获取访问者IP
     * <p/>
     * 在一般情况下使用Request.getRemoteAddr()即可，但是经过nginx等反向代理软件后，这个方法会失效。
     * <p/>
     * 本方法先从Header中获取X-Real-IP，如果不存在再从X-Forwarded-For获得第一个IP(用,分割)，
     * 如果还不存在则调用Request .getRemoteAddr()。
     *
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Real-IP");
        if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
            return ip;
        }
        ip = request.getHeader("X-Forwarded-For");
        if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个IP值，第一个为真实IP。
            int index = ip.indexOf(',');
            if (index != -1) {
                return ip.substring(0, index);
            } else {
                return ip;
            }
        } else {
            return request.getRemoteAddr();
        }
    }

    /**
     * 获得当的访问路径
     * <p/>
     * HttpServletRequest.getRequestURL+"?"+HttpServletRequest.getQueryString
     *
     * @param request
     * @return
     */
    public static String getLocation(HttpServletRequest request) {
        UrlPathHelper helper = new UrlPathHelper();
        StringBuffer buff = request.getRequestURL();
        String uri = request.getRequestURI();
        String origUri = helper.getOriginatingRequestUri(request);
        buff.replace(buff.length() - uri.length(), buff.length(), origUri);
        String queryString = helper.getOriginatingQueryString(request);
        if (queryString != null) {
            buff.append("?").append(queryString);
        }
        return buff.toString();
    }

    /**
     * 判断是否为搜索引擎
     *
     * @param req
     * @return
     */
    public static boolean isRobot(HttpServletRequest req) {
        String ua = req.getHeader("user-agent");
        if (StringUtils.isBlank(ua)) return false;
        return (ua != null
                && (ua.indexOf("Baiduspider") != -1 || ua.indexOf("Googlebot") != -1
                || ua.indexOf("sogou") != -1
                || ua.indexOf("sina") != -1
                || ua.indexOf("iaskspider") != -1
                || ua.indexOf("ia_archiver") != -1
                || ua.indexOf("Sosospider") != -1
                || ua.indexOf("YoudaoBot") != -1
                || ua.indexOf("yahoo") != -1
                || ua.indexOf("yodao") != -1
                || ua.indexOf("MSNBot") != -1
                || ua.indexOf("spider") != -1
                || ua.indexOf("Twiceler") != -1
                || ua.indexOf("Sosoimagespider") != -1
                || ua.indexOf("naver.com/robots") != -1
                || ua.indexOf("Nutch") != -1
                || ua.indexOf("spider") != -1));
    }

    public static String getRootURLPATH(HttpServletRequest request) {

        return null;
    }

    /**
     * 获得请求的session id，但是HttpServletRequest#getRequestedSessionId()方法有一些问题。
     * 当存在部署路径的时候，会获取到根路径下的jsessionid。
     *
     * @param request
     * @return
     * @see javax.servlet.http.HttpServletRequest#getRequestedSessionId()
     */
    public static String getRequestedSessionId(HttpServletRequest request) {
        String sid = request.getRequestedSessionId();
        String ctx = request.getContextPath();
        // 如果session id是从url中获取，或者部署路径为空，那么是在正确的。
        if (request.isRequestedSessionIdFromURL() || StringUtils.isBlank(ctx)) {
            return sid;
        } else {
            // 手动从cookie获取
            Cookie cookie = getCookie(request, JSESSION_COOKIE);
            if (cookie != null) {
                return cookie.getValue();
            } else {
                return null;
            }
        }

    }

    /**
     * 获得cookie
     *
     * @param request HttpServletRequest
     * @param name    cookie name
     * @return if exist return cookie, else return null.
     */
    public static Cookie getCookie(HttpServletRequest request, String name) {
        Assert.notNull(request);
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie c : cookies) {
                if (c.getName().equals(name)) {
                    return c;
                }
            }
        }
        return null;
    }

    /**
     * 根据部署路径，将cookie保存在根目录。
     *
     * @param request
     * @param response
     * @param name
     * @param value
     * @return
     */
    public static Cookie addCookie(HttpServletRequest request,
                                   HttpServletResponse response, String name, String value) {
        Cookie cookie = new Cookie(name, value);
        String ctx = request.getContextPath();
        if (StringUtils.isBlank(ctx)) {
            cookie.setPath("/");
        } else {
            // @TODO ctx后是否要加'/'，应该不要？
            cookie.setPath(ctx);
        }
        response.addCookie(cookie);
        return cookie;

    }

    /**
     * 取消cookie
     *
     * @param response
     * @param name
     * @param domain
     */
    public static void cancleCookie(HttpServletResponse response, String name,
                                    String domain) {
        Cookie cookie = new Cookie(name, null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        if (!StringUtils.isBlank(domain)) {
            cookie.setDomain(domain);
        }
        response.addCookie(cookie);
    }

    /**
     * 获取COOKIE
     *
     * @param name cookie name
     */
    public static String getCookieValue(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) return null;
        for (Cookie ck : cookies) {
            if (StringUtils.equalsIgnoreCase(name, ck.getName()))
                return ck.getValue();
        }
        return null;
    }

    /**
     * 获取客户端IP地址，此方法用在proxy环境中
     *
     * @param req
     * @return
     */
    public static String getRemoteAddr(HttpServletRequest req) {
        String ip = req.getHeader("X-Forwarded-For");
        if (StringUtils.isNotBlank(ip)) {
            String[] ips = StringUtils.split(ip, ',');
            if (ips != null) {
                for (String tmpip : ips) {
                    if (StringUtils.isBlank(tmpip))
                        continue;
                    tmpip = tmpip.trim();
                    if (isIPAddr(tmpip) && !tmpip.startsWith("10.") && !tmpip.startsWith("192.168.") && !"127.0.0.1".equals(tmpip)) {
                        return tmpip.trim();
                    }
                }
            }
        }
        ip = req.getHeader("x-real-ip");
        if (isIPAddr(ip))
            return ip;
        ip = req.getRemoteAddr();
        if (ip.indexOf('.') == -1)
            ip = "127.0.0.1";
        return ip;
    }

    /**
     * 设置COOKIE
     *
     * @param name
     * @param value
     * @param maxAge
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String name,
                                 String value, int maxAge) {
        setCookie(request, response, name, value, maxAge, true);
    }

    /**
     * 设置COOKIE
     *
     * @param name
     * @param value
     * @param maxAge
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String name,
                                 String value, int maxAge, boolean all_sub_domain) {
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(maxAge);
        if (all_sub_domain) {
            String serverName = request.getServerName();
            String domain = getDomainOfServerName(serverName);
            if (domain != null && domain.indexOf('.') != -1) {
                cookie.setDomain('.' + domain);
            }
        }
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    public static void deleteCookie(HttpServletRequest request,
                                    HttpServletResponse response, String name, boolean all_sub_domain) {
        setCookie(request, response, name, "", 0, all_sub_domain);
    }

    /**
     * 获取用户访问URL中的根域名
     * 例如: www.dlog.cn -> dlog.cn
     *
     * @param host
     * @return
     */
    public static String getDomainOfServerName(String host) {
        if (isIPAddr(host))
            return null;
        String[] names = StringUtils.split(host, '.');
        int len = names.length;
        if (len == 1) return null;
        if (len == 3) {
            return makeup(names[len - 2], names[len - 1]);
        }
        if (len > 3) {
            String dp = names[len - 2];
            if (dp.equalsIgnoreCase("com") || dp.equalsIgnoreCase("gov") || dp.equalsIgnoreCase("net") || dp.equalsIgnoreCase("edu") || dp.equalsIgnoreCase("org"))
                return makeup(names[len - 3], names[len - 2], names[len - 1]);
            else
                return makeup(names[len - 2], names[len - 1]);
        }
        return host;
    }

    /**
     * 判断字符串是否是一个IP地址
     *
     * @param addr
     * @return
     */
    public static boolean isIPAddr(String addr) {
        if (StringUtils.isEmpty(addr))
            return false;
        String[] ips = StringUtils.split(addr, '.');
        if (ips.length != 4)
            return false;
        try {
            int ipa = Integer.parseInt(ips[0]);
            int ipb = Integer.parseInt(ips[1]);
            int ipc = Integer.parseInt(ips[2]);
            int ipd = Integer.parseInt(ips[3]);
            return ipa >= 0 && ipa <= 255 && ipb >= 0 && ipb <= 255 && ipc >= 0
                    && ipc <= 255 && ipd >= 0 && ipd <= 255;
        } catch (Exception e) {
        }
        return false;
    }

    private static String makeup(String... ps) {
        StringBuilder s = new StringBuilder();
        for (int idx = 0; idx < ps.length; idx++) {
            if (idx > 0)
                s.append('.');
            s.append(ps[idx]);
        }
        return s.toString();
    }

    /**
     * 获取HTTP端口
     *
     * @param req
     * @return
     * @throws java.net.MalformedURLException
     */
    public static int getHttpPort(HttpServletRequest req) {
        try {
            return new URL(req.getRequestURL().toString()).getPort();
        } catch (MalformedURLException excp) {
            return 80;
        }
    }


    /**
     * 获取浏览器提交的字符串参
     *
     * @param param
     * @param defaultValue
     * @return
     */
    public static String getParam(HttpServletRequest req, String param, String defaultValue) {
        String value = req.getParameter(param);
        return (StringUtils.isEmpty(value)) ? defaultValue : value;
    }

}

