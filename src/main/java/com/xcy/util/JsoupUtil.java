package com.xcy.util;

import com.xcy.controller.ExcelController;
import com.xcy.entity.GatewayState;
import org.apache.commons.collections4.CollectionUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xcy
 * @date 2018/09/30 16:44
 * @description 页面抓取
 * @since V1.0.0
 */
public class JsoupUtil {

    private static final Logger logger = LoggerFactory.getLogger(ExcelController.class);
    /**
     * 客户端
     */
    private static final String USER_AGENT = "User-Agent";
    private static final String  COOKIE_HEADER = "Cookie";
    private static final String  REFERER_HEADER = "Referer";

    /**
     * 客户端模拟
     */
    private static final String USER_AGENT_VALUE = "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:52.0) Gecko/20100101 Firefox/52.0";

    /**
     * 超时时间
     */
    private static final Integer TIME_OUT = 3000;

    /**
     * 查询通关状态
     *
     * @param stateCode 报关单号 18位
     */
    public static GatewayState queryGatewayState(final String stateCode) throws IOException {
        /**
         * 通关状态请求地址
         */
        final String GATEWAY_STATE_QUERY_URL = "http://www.haiguan.info/OnLineSearch/Gateway/weixin/weixinGatewayStateResult.aspx";
        /**
         * 通关状态页面地址
         */
        final String GATEWAY_STATE_URL = "http://www.haiguan.info/OnLineSearch/Gateway/weixin/weixinGatewayState.html";

        //查询后得到对象
        Document document = getDocument(GATEWAY_STATE_QUERY_URL, GATEWAY_STATE_URL, new HashMap<String, String>() {
            {
                put("stateCode", stateCode);
            }
        });

        //数据列td
        Elements tds = document.getElementById("containter").select("td.td_white") ;
        logger.info("数据列:{}条", CollectionUtils.size(tds));
        if(CollectionUtils.isNotEmpty(tds)){
            GatewayState gatewayState = new GatewayState();
            gatewayState.setDeclarationNumber(StringUtils.null2EmptyWithTrimNew(tds.get(0).html()));
            gatewayState.setStatus(StringUtils.null2EmptyWithTrimNew(tds.get(1).html()));
            return gatewayState;
        }
        return null;

    }

    /**
     * 获取抓取后的文档对象
     * @param queryUrl 查询接口地址
     * @param url REFERER地址
     * @param params 参数
     * @return 文档对象
     */
    private static Document getDocument(String queryUrl, String url, Map<String,String> params) throws IOException {
        String verifyCode = random4();
        //发起查询请求
        Connection queryCon = Jsoup.connect(queryUrl);
        queryCon.header(USER_AGENT, USER_AGENT_VALUE)
                .header(COOKIE_HEADER,"CheckCode=" + verifyCode)
                .header(REFERER_HEADER,url);
        // 设置cookie和post上面的map数据
        Connection.Response queryRs = null;
        try {
            queryRs = queryCon.ignoreContentType(true)
                    .followRedirects(true)
                    .method(Connection.Method.POST)
                    .data(params)
                    .data("verifyCode",verifyCode)
                    .timeout(TIME_OUT).execute();
        } catch (IOException e) {
            throw new IOException("海关接口异常");
        }
        //查询后得到对象
        Document document = Jsoup.parse(queryRs.body());
        return document;
    }

    /**
     * 随机四位数
     * @return string
     */
    private static String random4(){
        return  Integer.toString((int)((Math.random() * 9 + 1) * 1000));
    }


    public static void main(String[] args) throws Exception {
        //queryGatewayState("534520181451298325");


        try {
            Connection connection = Jsoup.connect("http://192.168.0.13:8084/onl_server_web/scgvc.png");
            Document doc = connection.ignoreContentType(true).get();
            System.out.println(doc);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
