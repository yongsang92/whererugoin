package com.whereareyougoing.www.demo.config;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

@Component
public class KorUrlConverter {
    
    public Link toKorURL(Link link) throws UnsupportedEncodingException {
        String href=URLDecoder.decode(link.toUri().toString(),"UTF-8");
        return new Link(href);
    }

}

