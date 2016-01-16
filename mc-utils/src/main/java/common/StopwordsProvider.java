package common;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created by chris on 1/3/16.
 */
public class StopwordsProvider {
    private static final Log log = LogFactory.getLog(StopwordsProvider.class);
    private final Set<String> stopwords = Collections.newSetFromMap(new ConcurrentHashMap<>());
    private final Pattern regex;
    private static String filename;
    private static StopwordsProvider provider;

    private StopwordsProvider(String filename) {
        Scanner s = null;
        StopwordsProvider.filename = filename;
        log.info("Rebuilding stopwords for: " + filename);
        try{
            s = new Scanner(new File(filename));
        }catch(FileNotFoundException e){
            log.error("Could not find stopwords file: " + filename, e);
        }

        while (s != null && s.hasNextLine()){
            stopwords.add(s.nextLine());
        }
        if(s != null){
            s.close();
        }else{
            log.error("Stopwords file not found! File: " + filename);
        }
        regex = Pattern.compile(getStopwordRegexString(stopwords));
    }

    public static synchronized StopwordsProvider getProvider(String filename){
        if(provider == null || !StopwordsProvider.filename.equals(filename)){
            provider = new StopwordsProvider(filename);
        }
        return provider;
    }

    public static StopwordsProvider getProvider(){
        String DEFAULT_STOP_FILE = System.getenv(Constants.RESOURCE_FOLDER_VAR) + "/" + Constants.STOPWORDS_FILE;
        return getProvider(DEFAULT_STOP_FILE);
    }

    public Set<String> getStopwords(){
        return stopwords;
    }
    public Pattern getRegex(){
        return regex;
    }

    private static String getStopwordRegexString(Set<String> stopwords) {
        return StringUtils.join(stopwords.parallelStream()
                                         .map(StopwordsProvider::blankSurroundedRegex)
                                         .collect(Collectors.toList()), "|");
    }

    private static String blankSurroundedRegex(String term){
        String newRegex = "\\s*\\b";
        newRegex += term;
        newRegex += "\\b\\s*";
        return newRegex;
    }

}
