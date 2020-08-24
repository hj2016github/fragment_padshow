package com.tyyh.android06_fmtofm_day29;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class Fragment01 extends Fragment{
    private ListView listView;
    private List<String> list=new ArrayList<>();
    private String[] data=new String[]{"道士下山", "深夜食堂", "头脑特工队", "小森林", "如晴天，似雨天", "女间谍"};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment,container,false);
        listView= (ListView) view.findViewById(R.id.fm01_lv);
        list.add("道士下山.txt");

        ArrayAdapter adapter=new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,data);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                TextView textView = (TextView) getActivity().getFragmentManager().
//                        findFragmentById(R.id.fragment02).getView().
//                        findViewById(R.id.fm02_tv);
                TextView textView= (TextView) getActivity().findViewById(R.id.fm02_tv);//fg1获取到fg2的textview；
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                try {
                    InputStream inputStream = getActivity().getAssets().open("movie" + position + ".txt");
                    byte[] buffer = new byte[1024];
                    int temp = 0;
                    while ((temp = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer);
                        outputStream.flush();
                    }
                    String msg=outputStream.toString();
                    textView.setText(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
        return view;
    }
}
