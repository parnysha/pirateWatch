package org.example.kinoparserspring.service;

import org.example.kinoparserspring.jsonSamples.JsonAns;

import java.io.IOException;
import java.util.List;


public interface SearchFilmService {
    List<JsonAns> searchFilmId(String name);
}
