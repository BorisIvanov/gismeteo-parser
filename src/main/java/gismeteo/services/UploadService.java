package gismeteo.services;

import gismeteo.repositories.GismeteoRepository;
import gismeteo.repositories.WeatherItem;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UploadService {

    private GismeteoRepository gismeteoRepository;

    @Autowired
    public UploadService(GismeteoRepository gismeteoRepository){
        this.gismeteoRepository = gismeteoRepository;
    }

    public void get(int id) throws IOException {
        Document doc = Jsoup.connect("https://www.gismeteo.ru/diary/" + id + "/2012/2/").get();
        Elements tdList = doc.select("#data_block tbody > tr td:eq(2)");
        List<WeatherItem> data =  new ArrayList<>();
        for(Element td : tdList){
            String value = td.childNodes().get(0).attr("text");
            WeatherItem weatherItem = new WeatherItem();
            weatherItem.setPressure(Integer.valueOf(value));
            data.add(weatherItem);
            System.out.println(value);
        }
        tdList = doc.select("#data_block tbody > tr td:eq(7)");
        for(Element td : tdList){
            String value = td.childNodes().get(0).attr("text");
            System.out.println(value);
            WeatherItem weatherItem = new WeatherItem();
            weatherItem.setPressure(Integer.valueOf(value));
            data.add(weatherItem);
        }

        gismeteoRepository.save(data);
    }

}
