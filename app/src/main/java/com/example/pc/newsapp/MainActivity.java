package com.example.pc.newsapp;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.design.widget.TabLayout;
import android.support.v4.view.NestedScrollingChild;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;

import com.stanfy.gsonxml.GsonXml;
import com.stanfy.gsonxml.GsonXmlBuilder;
import com.stanfy.gsonxml.XmlParserCreator;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView mNewsListView;
   // private List<NewsBean> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNewsListView = (ListView)findViewById(R.id.listView);

        new NewsTask().execute("http://fs.jtbc.joins.com/RSS/newsflash.xml");   //url넘김
    }

    //뉴스를 네트워크를통해 가져오는 Task
    class NewsTask extends AsyncTask<String, String, NewsBean> {

        private ProgressDialog prd;

        @Override
        protected void onPreExecute() {     //네트워크 통신시 progressdialog띄워야함
            prd = new ProgressDialog(MainActivity.this); //MainActivity.this 써도됨
            prd.setMessage("뉴스를 가져오는 중입니다...");
            prd.setCancelable(false);
            prd.show();
        }

        @Override
        protected NewsBean doInBackground(String... params) { //네트워크를 통해 xml값을 가져와야함

            StringBuilder output = new StringBuilder();

            try{
                URL url = new URL(params[0]);

                 BufferedReader reader = new BufferedReader( new InputStreamReader( url.openStream() ) );

                String line = null;
                while(true) {
                    line = reader.readLine();
                    if (line == null) break;
                    output.append(line + "\n");
                }

                reader.close();

                String xmlData = output.toString();
                String sub = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
                xmlData = xmlData.substring(sub.length()+1);


                //파싱시작
                GsonXml gsonXml = new GsonXmlBuilder().setXmlParserCreator(parserCreator).setSameNameLists(true).create();

                NewsBean newsBean = gsonXml.fromXml(xmlData, NewsBean.class);

                return newsBean;       //onPostExcute로 넘어감

            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }//end doInBackground

        private XmlParserCreator parserCreator = new XmlParserCreator() {
            @Override
            public XmlPullParser createParser() {
                try {
                    return XmlPullParserFactory.newInstance().newPullParser();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        };

        @Override
        protected void onPostExecute(NewsBean bean) {
            prd.dismiss();

            if(bean != null){
                NewsAdapter adapter = new NewsAdapter(MainActivity.this, bean);
                mNewsListView.setAdapter(adapter);
            }
        }//end onPostExecute
    };
}
