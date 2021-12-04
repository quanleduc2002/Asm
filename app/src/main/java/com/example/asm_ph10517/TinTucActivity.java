package com.example.asm_ph10517;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

public class TinTucActivity extends AppCompatActivity {
ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("TIN TỨC");
        overridePendingTransition(R.anim.toleft,R.anim.toright);
        setContentView(R.layout.activity_tin_tuc);
        listView=findViewById(R.id.lvTinTuc);
        AsyncTask<String,Void,String> content=new RSSDFeed().execute("https://vnexpress.net/rss/the-gioi.rss");
        arrayAdapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(arrayAdapter);
        intent=new Intent(this, WebView.class);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String link=arrayLink.get(position);
                intent.putExtra("Openlink",link);
                startActivity(intent);
            }
        });

    }
    ArrayList<String> arrayList=new ArrayList<>();
    ArrayList<String> arrayLink=new ArrayList<>();
    ArrayAdapter arrayAdapter;
    Intent intent;
    public class RSSDFeed extends AsyncTask<String,Void,String>{
        //lay du lieu tu Server
        @Override
        protected String doInBackground(String... strings) {
            StringBuilder content=new StringBuilder();
            try {
                URL url=new URL(strings[0]);
                InputStreamReader reader=new InputStreamReader(url.openConnection().getInputStream());
                BufferedReader bufferedReader=new BufferedReader(reader);
                String line="";
                while ((line=bufferedReader.readLine())!=null){
                    content.append(line);

                }
                bufferedReader.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return content.toString();
        }
        //post du lieu lên client
        @Override
        protected void onPostExecute(String s) {

            super.onPostExecute(s);
            XMLParse xmlParse=new XMLParse();
            try {
                Document document=xmlParse.getDocument(s);
                NodeList nodeList=document.getElementsByTagName("item");
                String title="";
                for(int i=0;i<nodeList.getLength();i++){
                    Element element=(Element)nodeList.item(i);//lay ve item i
                    title=xmlParse.getValue(element,"title");
                    arrayList.add(title);
                    arrayLink.add(xmlParse.getValue(element,"link"));

                }
                arrayAdapter.notifyDataSetChanged();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            }
        }
    }

}

