package com.seoultech.lesson.souffle.data;

import com.seoultech.lesson.souffle.data.model.Option;
import com.seoultech.lesson.souffle.data.model.User;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class LoginManager {

    private List<String> arr;

    public LoginManager() {
    }

    public boolean isAutoLogin(Option option) {
        return option.isAutoLogin();
    }

    public User login(int studentNumber, String password) {
        try {
            String requestBody = "userId=" + studentNumber + "&password=" + password + "&ssoLangKnd=ko&returnUrl=";
            arr = new ArrayList<>();

            Connection.Response cookieRequest = Jsoup.connect("http://portal.seoultech.ac.kr/user/seouolTechLogin.face")
                    .timeout(10000)
                    .maxBodySize(0)
                    .method(Connection.Method.GET)
                    .execute();

            Map loginTryCookie2 = cookieRequest.cookies();

            Connection.Response response = Jsoup.connect("http://portal.seoultech.ac.kr/user/seouolTechLogin.face")
                    .timeout(10000)
                    .maxBodySize(0)
                    .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3")
                    .header("Accept-Encoding", "gzip, deflate, br")
                    .header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
                    .header("Cache-Control", "max-age=0")
                    .header("Connection", "keep-alive")
                    .header("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8")
                    .header("Host", "portal.seoultech.ac.kr")
                    .header("Origin", "http://portal.seoultech.ac.kr")
                    .header("Referer", "http://portal.seoultech.ac.kr/portal/default/SEOULTECH/LOGIN")
                    .referrer("http://portal.seoultech.ac.kr/portal/default/SEOULTECH/LOGIN")
                    .data("userId", Integer.toString(studentNumber))
                    .data("password", password)
                    .cookies(loginTryCookie2)
                    .requestBody(requestBody)
                    .method(Connection.Method.POST)
                    .execute();

            Document doc = response.parse();
            Element elm = doc.selectFirst("#header_wrap > div.tnb > ul > li:nth-child(1)");
            StringTokenizer token = new StringTokenizer(elm.ownText(), "( ) [ ]", false);
            for (int i = 0; i < 3; i++) {
                String temp = token.nextToken();
                if (i == 1) continue;
                arr.add(temp);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e){
            return new User("UNAUTHORIZED", -1, "UNAUTHORIZED", false);
        }
        return new User(arr.get(0), studentNumber, arr.get(1), true);
    }
}
