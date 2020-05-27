/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.app.web.zlkf.search.searchworker.service.model;

import java.util.ArrayList;
import java.util.List;
import org.apdplat.word.WordSegmenter;
import org.apdplat.word.segmentation.Word;
import org.springframework.stereotype.Service;

/**
 *
 * @author king word 分词器对应的service
 */
@Service
public class WordService {

    public List<String> analyStrToWd(String str) {
        List<Word> wordList = WordSegmenter.seg(str);

        ArrayList<String> strList = new ArrayList<String>();

        for (Word word : wordList) {
            String w = word.getText();
            strList.add(w);
        }

        return strList;
    }

}
