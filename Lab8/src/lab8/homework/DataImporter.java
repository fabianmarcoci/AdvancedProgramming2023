package lab8.compulsory;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataImporter {
    private String url = "https://www.kaggle.com/datasets/notgibs/500-greatest-albums-of-all-time-rolling-stone";
    private Document doc;

    public DataImporter() throws IOException {
        doc = Jsoup.connect(url).get();
    }

    public void importer() {
        System.out.println(doc);
    }
    public List<String[]> importData() {
        List<String[]> data = new ArrayList<>();

        Elements albumsData = doc.select("div:containsOwn('Subgenre')").first().parent().select("div.list-group-item");
        Elements spans = albumsData.select("span");


        for (Element span : spans) {
            // Extract the data for each album
            String[] albumData = new String[6];
            albumData[0] = span.selectFirst("div.year").text();
            albumData[1] = span.selectFirst("div.title").text();
            albumData[2] = span.selectFirst("div.artist").text();
            albumData[3] = span.selectFirst("div.album").text();
            albumData[4] = span.selectFirst("div.genre").text();
            data.add(albumData);
        }

        return data;
    }
}
