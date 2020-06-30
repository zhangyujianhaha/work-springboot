package com.work.config;

import org.springframework.context.annotation.Configuration;

import java.io.FileWriter;
import java.io.IOException;

@Configuration
public class AlipayConfig {
    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号,开发时使用沙箱提供的APPID，生产环境改成自己的APPID
    public static String APP_ID = "2016102300741452";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String APP_PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCQA3bjcN6hFYrPRrYXBif5uAR+7i7FGP8kZ8LarmkIPWNrMch1hOo+gF6FG4xsrFchbKIC5e2yLhfyJqPhKUd8Guk6JRW+Ho7d1pWR37TX5mKNdliJt89dflobs4Y//FMk9e23DO5b14hZt8JKIibASd6SSUr+oeXy7x6+dqci9gk1u8vf+3gVaKoK+U74iIc0v/3USkIzDHAGS8vg1R3X2PX7oEM/PBktQ+GdI731Wdg0ZZeG7vcQIHlDwWy64aGIAiZFV4Bp0Ruv/vQEkOCwr6IXjQEHk7mgnlYzs0rX1/CWTCJ/2n010fdqsKMTjeF8ABzUZZtN71PoJL+blaNzAgMBAAECggEAHat5DPauB0Xm6TlKPogYKEyPMI9lt1mlBx0RefhAz1/GYclJ4VxW3jWYQ5gfYCxJbytwXWR9ih4Q9OSdEsBsZ9crgb0uM5McH/wbqIgMPAbM+xQMvaLO6BYl/lsIJ5qWjqDULshNO0K2ZrWDI77ReQA/CKhs+PTl6Rz2morbeEokGo7ucveyzCcerM7mVoDWQgwCi/jgaYU9qA2J9/jLPoAMQ8YoTZUMU9EL5mAcnM4vaQmuPkpCscE5x2VTgvQtCzJIZb3vqy03vkn2tkvjESKqZ/5ZixmXNt7FsM+AnMLbK9hZFwQtWGtSu4UqCQTzOALJ6CBKMPLa8D1d8K3/UQKBgQDgb4ojswtsYny143fXvt99we7Viw9WYvOMrlqgYVV28nVMn2qO3XYYo9eE6Df9G+aHomAt6hwMurZWmKCTTPdqAieWbgSBKKpDeBfkN6nVbk7LmR89F7bwxlTTFNM2nJJnxjSy1DS6gVMLLWdEsupfqs2OfcY5395yx53GkVoBlwKBgQCkRHLvxV/gjGVvSG25tfqNF09rtW1/4lftXi0fG30DK2dTSNMSiwFPqPwflxPQ/G+OeJgGrdcug5pg/2d6SDCsQOO1WAT34HnfZQiGwGCg6U78cIkdXQ0qOSn9YjL72AGe1M2DBZu+JLB2exlBl64ofyNGk2e1Im7ZGujEEH2whQKBgAi3LCUgwdPOq0sg7zl4lxM6Ib03LjxLkWuoJarOvNPmrHlyQ2AS55AjZK4rmmkA2V3xB00hJz+zTHtmZ+3wcjcyqDjmQtSFML93/W2GX9kC9Jphct4beqY0/XxI9b7MK79N6iP7q6SGeyQW+kinrgRTDLJ43ebHc8r/R3g7ksQ3AoGBAIEkOwQbtNOxLLG/TFw7IB46HDs+aDao+J1pc6nID5ElV1cXorEqTT6WSJiBSpf7TazBTCu8EbbEyeLGCFEPXgyd+yb/avCBLu8c3AYsSgtXQLyfsHxAQnBWfpdw00pBOIzpGiSOjTGAYKbVAjcaC1ZVFaOPkXHK/0B5slMp79aNAoGAEkxeoYhz22LPoDr/PXwIUgCfDZDF3SOAv4CZRz+5At3znB+Jq241W11LFWg7MXp6+gzm33nkaMp3LXs+tfbs1vqcHIPi3DZ8vGLXZtFEoHBdt0zJ8SXWrbIBfILIMuKl0RONPKVmmOWxi+oJvCQ7jjgLyoRhO2xyjATAdxPyqTo=";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAkAN243DeoRWKz0a2FwYn+bgEfu4uxRj/JGfC2q5pCD1jazHIdYTqPoBehRuMbKxXIWyiAuXtsi4X8iaj4SlHfBrpOiUVvh6O3daVkd+01+ZijXZYibfPXX5aG7OGP/xTJPXttwzuW9eIWbfCSiImwEnekklK/qHl8u8evnanIvYJNbvL3/t4FWiqCvlO+IiHNL/91EpCMwxwBkvL4NUd19j1+6BDPzwZLUPhnSO99VnYNGWXhu73ECB5Q8FsuuGhiAImRVeAadEbr/70BJDgsK+iF40BB5O5oJ5WM7NK19fwlkwif9p9NdH3arCjE43hfAAc1GWbTe9T6CS/m5WjcwIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://localhost:8020/alipay.trade.page.pay-JAVA-UTF-8/notify_url.jsp";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问(其实就是支付成功后返回的页面)
    public static String return_url = "http://localhost:8020/doctor/doctorindex";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String CHARSET = "utf-8";

    // 支付宝网关，这是沙箱的网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 支付宝网关
    public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
