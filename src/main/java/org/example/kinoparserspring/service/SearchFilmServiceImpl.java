package org.example.kinoparserspring.service;

import lombok.RequiredArgsConstructor;
import org.example.kinoparserspring.jsonSamples.JsonAns;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class SearchFilmServiceImpl implements SearchFilmService {

    @Override
    public List<JsonAns> searchFilmId(String name) {
        List<JsonAns> list = new ArrayList<>();
        if(name.matches("[+]?\\d+")){
            list.add(new JsonAns(name,name));
            return list;
        }
        try {
            Document document = Jsoup.connect("https://www.kinopoisk.ru/index.php?kp_query="+name).get();
            Elements el = document.select("p.pic");
            for (Element ele : el){
                Elements eleee = ele.getElementsByTag("a");
                String id = eleee.attr("data-id");
                for (Element ell: eleee){
                    Elements eld = ell.select("img.flap_img");
                    for (Element elm: eld){
                        String names = elm.attr("title");
                        if(!"".equals(names)){
                            list.add(new JsonAns(names,id));
                        }
                    }
                }
            }
            //elementCifr = document.getElementsByTag("a").attr("data-id");
        } catch (IOException e) {
            list.add(new JsonAns("",""));
        }

        return list;
    }
}
