package com.example.restservice.Service;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.restservice.Model.Term;
import com.example.restservice.Repository.TermRepository;
import com.example.restservice.Response.CommonResponse;
import com.example.restservice.Response.Msg;
//import uk.ac.shef.dcs.jate.*;

@Service
public class TermService {
    @Autowired
    TermRepository termRepository;

    public CommonResponse<List<Term>> handleTerm(String content) {
        List<Term> terms = new LinkedList<>();
        Term term = new Term("NTU", "National Taiwan University", Arrays.asList("https://www.ntu.edu.tw"));
        terms.add(term);

        //ContentExtractor contentExtractor = new ContentExtractor();

        Msg msg = new Msg("success", "get terms");
        return new CommonResponse<List<Term>> (msg, terms);
    }

}
