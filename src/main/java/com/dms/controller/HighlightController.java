package com.dms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dms.model.Highlight;
import com.dms.service.HighlightService;

@Controller
@RequestMapping("/dms/high-light")
public class HighlightController {

    @Autowired
    private HighlightService highlightService;


    /**
     * SAVE highlight from React
     */
    @RequestMapping(
    	    value="/save",
    	    method=RequestMethod.POST,
    	    consumes="application/json",
    	    produces="application/json"
    	)
    public @ResponseBody Highlight saveHighlight(
            @RequestBody Highlight highlight) {

        System.out.println("========== SAVE HIGHLIGHT ==========");
        System.out.println("User ID: " + highlight.getUserId());
        System.out.println("PDF URL: " + highlight.getPdfUrl());
        System.out.println("Page: " + highlight.getPageNumber());
        System.out.println("Text: " + highlight.getHighlightedText());
        System.out.println("Color: " + highlight.getColor());
        System.out.println("Rects: " + highlight.getRects());

        Highlight saved = highlightService.saveHighlight(highlight);

        System.out.println("Saved ID: " + saved.getId());

        return saved;
    }
    
    
    @RequestMapping(
    	    value="/save121",
    	    method=RequestMethod.POST,
    	    consumes="application/json",
    	    produces="application/json"
    	)
    	@ResponseBody
    	public String saveHighlight(@RequestBody String body) {

    	    System.out.println("RAW JSON RECEIVED: " + body);

    	    return "ok";
    	}
    
    @RequestMapping(value="/test", method=RequestMethod.GET)
    @ResponseBody
    public String test(){
        return "Highlight API working";
    }


    
    /**
     * GET highlights for React
     */
    @RequestMapping(
        value = "/list",
        method = RequestMethod.GET
    )
    public @ResponseBody List<Highlight> getHighlights(
            @RequestParam("userId") String userId,
            @RequestParam("pdfUrl") String pdfUrl) {

        System.out.println("========== GET HIGHLIGHTS ==========");
        System.out.println("User ID: " + userId);
        System.out.println("PDF URL: " + pdfUrl);

        List<Highlight> list = highlightService.getHighlights(userId, pdfUrl);

        System.out.println("Total highlights: " + list.size());

        return list;
    }
}