package com.example.test;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.xl.view.AutoHorizionChangeLineViews;

import java.util.ArrayList;

public class MainActivity extends Activity {

    private AutoHorizionChangeLineViews autoHorizionChangeLineViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        autoHorizionChangeLineViews = findViewById(R.id.autoHorizionChangeLineViews);
        ArrayList<String> replys = new ArrayList<>();
        replys.add("有些事我都已忘记");

        replys.add("在一个晚上 我的母亲问我");
        replys.add("今天怎么不开心");
        replys.add("我说在我的想象中 有一双滑板鞋 与众不同最时尚");
        replys.add("跳舞肯定棒");
        replys.add("整个城市找遍所有的街");
        replys.add("都没有");
        replys.add("拍摄时，导演陈喆踌躇满志，他打算将庞麦郎拍出“国际巨星”的感觉，颠覆庞麦郎在大家心目中的草根形象。然而，在练舞房看到庞麦郎跳舞后，陈喆的心凉了一大截，庞麦郎记不住舞蹈动作，一个简单的八拍，舞蹈老师最起码要念二十遍");
        autoHorizionChangeLineViews.setOnTextViewItemClickListener(new AutoHorizionChangeLineViews.OnTextViewItemClickListener() {
            @Override
            public void onClick(int index, String text, View view) {
                Toast.makeText(MainActivity.this, "点击了第：" + index + " 个，文本内容为：" + text , Toast.LENGTH_SHORT).show();
            }
        });
        autoHorizionChangeLineViews.setTexts(replys);
    }
}
