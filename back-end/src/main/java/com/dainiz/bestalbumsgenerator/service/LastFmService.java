package com.dainiz.bestalbumsgenerator.service;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LastFmService {
    private RestTemplate template = new RestTemplate();

}
