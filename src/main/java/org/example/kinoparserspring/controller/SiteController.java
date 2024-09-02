package org.example.kinoparserspring.controller;


import lombok.RequiredArgsConstructor;
import org.example.kinoparserspring.jsonSamples.JsonAns;
import org.example.kinoparserspring.jsonSamples.JsonReq;
import org.example.kinoparserspring.service.SearchFilmService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class SiteController {
    private final SearchFilmService searchService;

    @PostMapping("/id")
    public JsonAns searchFilm(@RequestBody JsonReq name) {
        return searchService.searchFilmId(name.getName().trim().toLowerCase());
    }
    @GetMapping("/search/{idVideo}")
    public ModelAndView filmPage(@PathVariable int idVideo, Model model) {
        model.addAttribute("id", idVideo);
        return new ModelAndView("testPage");
    }

}
