package pl.coderslab;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Zadanie5 {


    public static void main(String[] args) {
        Path popularWordsPath = Paths.get("popular_words.txt");
        Path filteredWordsPath = Paths.get("filtered_words.txt");

        List<String> popularWords = getWords("http://www.onet.pl/");

        try {
            Files.write(popularWordsPath, popularWords);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // -----------------

        popularWords.clear();
        try {
            for (String line : Files.readAllLines(popularWordsPath)) {
                popularWords.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        List<String> filteredWords = new ArrayList<>();
        String ignoredWords = "oraz poniewaz albo dlatego";
        List<String> filter = new ArrayList<>();
        for (String word : ignoredWords.split(" ")) {
            filter.add(word);
        }
        for (String word : popularWords) {
            if (!filter.contains(word)) {
                filteredWords.add(word);
            }
        }

        try {
            Files.write(filteredWordsPath, filteredWords);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("done");
    }

    static List<String> getWords(String url) {
        List<String> words = new ArrayList<>();
        Connection connect = Jsoup.connect(url);
        try {
            Document document = connect.get();
            Elements titles = document.select("span.title");
            for (Element e : titles) {
                String title = e.text().replaceAll("[^a-zA-Z0-9 ąćżźółęśń]", "");
                String[] titleArr = title.split(" ");
                for (String word : titleArr) {
                    if (word.length() > 3 && !words.contains(word)) {
                        words.add(word);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return words;
    }


}
