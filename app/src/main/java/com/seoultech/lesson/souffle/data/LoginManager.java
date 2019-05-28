package com.seoultech.lesson.souffle.data;

import android.webkit.CookieManager;

import com.seoultech.lesson.souffle.data.model.User;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LoginManager {

    public boolean isAutoLogin(){

        return false;
    }

    public User login(int studentNumber, String password) {
        try {
//            String userAgent = "Mozilla/5.0 (Linux; U; Android 4.4.2; en-us; SCH-I535 Build/KOT49H) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30";
//            String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.169 Safari/537.36";
            String userAgent = "Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6";
            Map<String, String> data = new HashMap<>();
            data.put("userId", "15109355");
            data.put("password", "dnjs0402@889");
            data.put("ssoLangKnd","ko");
            data.put("returnUrl", "");
//            Document doc = Jsoup.connect("http://portal.seoultech.ac.kr/").get();
            Connection.Response siteResponse = Jsoup.connect("https://portal.seoultech.ac.kr/user/seouolTechLogin.face")
                    .method(Connection.Method.GET)
                    .execute();

            Map<String, String> loginTryCookie = siteResponse.cookies();

            CookieManager cookieManager = CookieManager.getInstance();
            String cookie = cookieManager.getCookie("https://portal.seoultech.ac.kr/user/seouolTechLogin.face");

            Connection.Response response = Jsoup.connect("https://portal.seoultech.ac.kr/user/seouolTechLogin.face")
//                    .userAgent(userAgent)
                    .timeout(0)
                    .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3")
                    .header("Accept-Encoding", "gzip, deflate, br")
                    .header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
                    .header("Cache-Control", "max-age=0")
                    .header("Connection", "keep-alive")
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .header("Host", "portal.seoultech.ac.kr")
                    .header("Origin", "http://portal.seoultech.ac.kr")
                    .header("Referer", "http://portal.seoultech.ac.kr/portal/default/SEOULTECH/LOGIN")
                    .header("Upgrade-Insecure-Requests", "1")
                    .header("User-Agent", "Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                    .header("Cookie", cookie)
                    .data(data)
                    .method(Connection.Method.POST)
                    .execute();

            String title = response.body();
            System.out.println(title);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
