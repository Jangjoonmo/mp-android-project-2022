package com.example.parentsletterproject.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.parentsletterproject.R;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class OpenAPI extends AsyncTask<Void, Void, String> implements Interface{

    private String url;

    public OpenAPI(String url) {

        this.url = url;
    }

    @Override
    protected String doInBackground(Void... params) {

        // parsing할 url 지정(API 키 포함해서)

        DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = null;
        try {
            dBuilder = dbFactoty.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        Document doc = null;
        try {
            doc = dBuilder.parse(url);
        } catch (IOException | SAXException e) {
            e.printStackTrace();
        }

        // root tag
        doc.getDocumentElement().normalize();
        System.out.println("Root element: " + doc.getDocumentElement().getNodeName()); // Root element: result

        // 파싱할 tag
        NodeList nList = doc.getElementsByTagName("row");


        for(int temp = 0; temp < nList.getLength(); temp++){

            Node nNode = nList.item(temp);

            if(nNode.getNodeType() == Node.ELEMENT_NODE){

                Element eElement = (Element) nNode;

                //System.out.println("eElement: " + eElement);

                String today_date = "20200415";
                String ye_date ="20200414";
                String tom_date = "20200416";

                String data = getTagValue("MLSV_YMD", eElement);
                String yest_date = getTagValue("MLSV_YMD", eElement);
                String tomorrow_date = getTagValue("MLSV_YMD", eElement);

                if ( ye_date.equals(yest_date)) {
                    String ye_meal = getTagValue("DDISH_NM", eElement);

                    TextView yester_text = (TextView) findViewById(R.id.yester_text);
                    yester_text.setText(ye_meal);
                }

                if (tom_date.equals(tomorrow_date)) {
                    String tom_meal = getTagValue("DDISH_NM", eElement);

                    TextView tomo_text = (TextView) findViewById(R.id.tomo_text);
                    tomo_text.setText(tom_meal);
                }

                //System.out.println(data);

                if ( today_date.equals(data)) {

                    //EditText to_text = (EditText) findViewById(R.id.to_text);
                    String to_meal = getTagValue("DDISH_NM", eElement);

                    TextView to_text = (TextView) findViewById(R.id.to_text);
                    to_text.setText(to_meal);


                    Log.d("OPEN_API", "data  : " + getTagValue("MLSV_YMD", eElement));
                    Log.d("OPEN_API", "급식 메뉴  : " + getTagValue("DDISH_NM", eElement));
                    //Log.d("OPEN_API","초미세먼지 : " + getTagValue("pm25Value", eElement));


                }
            }	// for end


        }	// if end
        return null;
    }

    @Override
    protected void onPostExecute(String str) {
        super.onPostExecute(str);
    }

    private String getTagValue(String tag, Element eElement) {
        NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
        Node nValue = (Node) nlList.item(0);
        if(nValue == null)
            return null;
        return nValue.getNodeValue();
    }

}



