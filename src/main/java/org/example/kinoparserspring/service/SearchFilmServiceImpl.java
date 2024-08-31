package org.example.kinoparserspring.service;

import lombok.RequiredArgsConstructor;
import org.example.kinoparserspring.jsonSamples.JsonAns;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class SearchFilmServiceImpl implements SearchFilmService {

    @Override
    public JsonAns searchFilmId(String name) {
        String elementCifr = "1";
        try {
            Document document = Jsoup.connect("https://www.kinopoisk.ru/index.php?kp_query="+name).get();
            Elements elements = document.getElementsByTag("a");
            for (Element element : elements) {
                if (element.hasAttr("data-id")&&element.hasAttr("data-url")) {
                    elementCifr = element.attr("data-id");
                    break;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new JsonAns(elementCifr);
    }
}
