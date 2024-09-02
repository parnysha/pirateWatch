package org.example.kinoparserspring.service;

import lombok.RequiredArgsConstructor;
import org.example.kinoparserspring.jsonSamples.JsonAns;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.io.IOException;
import java.net.http.HttpConnectTimeoutException;

@Service
@RequiredArgsConstructor
public class SearchFilmServiceImpl implements SearchFilmService {

    @Override
    public JsonAns searchFilmId(String name) {
        if(name.matches("[+]?\\d+")){
            return new JsonAns(name);
        }
        String elementCifr = "";
        try {
            Document document = Jsoup.connect("https://www.kinopoisk.ru/index.php?kp_query="+name).get();
            elementCifr = document.getElementsByTag("a").attr("data-id");
        } catch (IOException e) {
            return new JsonAns("");
        }

        return new JsonAns(elementCifr);
    }
}
