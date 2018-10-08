# AutoHorizionChangeLineViews
# 水平布局文本内容，一行不够的话，自动切换到下一行，最多显示8个文本字符串。
# 首先看一下效果：
![](https://github.com/xilinch/AutoHorizionChangeLineViews/blob/master/pic/xt.webp)



## 1.使用方式

导入lib，在你的项目build中增加
dependencies {
   compile 'com.android.xl.view:AutoHorizionChangeLineViews:1.0.0'
}


## 2.在布局文件中引用，

     <com.android.xl.view.AutoHorizionChangeLineViews
        app:item_verticl_space= "15dp"
        app:item_horizion_space= "15dp"
        app:item_text_size= "15"
        app:item_text_color= "#00ff00"
        android:paddingRight="15dp"
        android:paddingLeft="15dp"
        android:paddingTop="30dp"
        android:paddingBottom="30dp"
        android:background="#ffffff"
        android:layout_alignParentBottom="true"
        android:id="@+id/autoHorizionChangeLineViews"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"></com.android.xl.view.AutoHorizionChangeLineViews>

## 3.设置对应文字内容
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
        autoHorizionChangeLineViews.setOnTextViewItemClickListener(new           AutoHorizionChangeLineViews.OnTextViewItemClickListener() {
            @Override
            public void onClick(int index, String text, View view) {
                Toast.makeText(MainActivity.this, "点击了第：" + index + " 个，文本内容为：" + text , Toast.LENGTH_SHORT).show();
            }
        });
        autoHorizionChangeLineViews.setTexts(replys);


## 4.自定义属性
        app:item_verticl_space= "15dp"
        app:item_horizion_space= "15dp"
        app:item_text_size= "15"
        app:item_text_color= "#00ff00"
        app:item_text_bg= "#00ff00"

    
## 5.注意。
   setOnTextViewItemClickListener 要在setTexts方法前调用。
   


