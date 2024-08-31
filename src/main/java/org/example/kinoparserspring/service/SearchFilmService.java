package org.example.kinoparserspring.service;

import org.example.kinoparserspring.jsonSamples.JsonAns;


public interface SearchFilmService {
    JsonAns searchFilmId(String name);
}
