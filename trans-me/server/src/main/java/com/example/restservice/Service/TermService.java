package com.example.restservice.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.restservice.Model.Term;
import com.example.restservice.Repository.TermRepository;
import com.example.restservice.Response.CommonResponse;
import com.example.restservice.Response.Msg;

@Service
public class TermService {
    @Autowired
    TermRepository termRepository;

    public CommonResponse<List<Term>> handleTerm(String content) {
        List<Term> terms = new LinkedList<>();

        // Extract Terminologies
        List<String> extractedTerms = extractTerms(content);
        for (String s: extractedTerms) {
            Term term = new Term(s, "", Arrays.asList("http://www.google.com/search?q=" + s.replace(' ', '+')));
            terms.add(term);
        }

        Msg msg = new Msg("success", "get terms");
        return new CommonResponse<List<Term>> (msg, terms);
    }

    private List<String> extractTerms(String content) {
        List<String> terms = new LinkedList<>();

        try {
            ProcessBuilder processBuilder = new ProcessBuilder("python", "./src/main/python/com/example/restservice/Service/TermExtract.py", content);
            Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line = null;
            while ((line = reader.readLine()) != null) {
                terms.add(line);
            }
        }
        catch (java.lang.Exception e) {
            System.out.println(e);
        }
        
        return terms;
    }
}
