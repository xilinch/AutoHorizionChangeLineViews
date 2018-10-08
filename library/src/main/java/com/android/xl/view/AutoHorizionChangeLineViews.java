package com.android.xl.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * 最多不超过8个，按照文字大小自动水平排列，一行不够则自动换到下一行
 */
public class AutoHorizionChangeLineViews extends RelativeLayout {

    /**
     * 控件
     */
    private TextView[] textViews;
    /**
     * 文本
     */
    private ArrayList<String> texts = new ArrayList();
    /**
     * 长度
     */
    private ArrayList<Float> textWidths = new ArrayList<Float>();

    /**
     * 布局参数
     */
    private ArrayList<LayoutParams> textLayoutParams = new ArrayList();

    private float width;

    private float padding_15 = dipTopx(getContext(), 15);

    /**
     * 水平的间距
     */
    private float itemHorizionSpace = padding_15;
    /**
     * 垂直的间距
     */
    private float itemVerticlSpace = padding_15;
    /**
     * 文本字体大小
     */
    private float textSize = 15;

    private int[] ids = new int[]{R.id.id_horizionview_1,
            R.id.id_horizionview_2,
            R.id.id_horizionview_3,
            R.id.id_horizionview_4,
            R.id.id_horizionview_5,
            R.id.id_horizionview_6,
            R.id.id_horizionview_7,
            R.id.id_horizionview_8};

    private int textColor = getResources().getColor(R.color.blue_6186a6);

    /**
     * 背景
     */
    private Drawable textViewBackground = getResources().getDrawable(R.drawable.dd_shape_1a389bee_content_15);
    private int textViewBackgroundRes = (R.drawable.dd_shape_1a389bee_content_15);

    private OnTextViewItemClickListener onTextViewItemClickListener;

    public AutoHorizionChangeLineViews(Context context) {
        super(context);
    }

    public AutoHorizionChangeLineViews(Context context, AttributeSet attrs) {
        super(context, attrs);
        if(attrs != null){
            TypedArray arr = getContext().obtainStyledAttributes(attrs, R.styleable.AutoHorizionChangeLineViews, 0, 0);
            itemHorizionSpace = arr.getDimension(R.styleable.AutoHorizionChangeLineViews_item_horizion_space, padding_15);
            itemVerticlSpace = arr.getDimension(R.styleable.AutoHorizionChangeLineViews_item_verticl_space, padding_15);
            textColor = arr.getColor(R.styleable.AutoHorizionChangeLineViews_item_text_color, getResources().getColor(R.color.blue_6186a6));
            textSize = arr.getInt(R.styleable.AutoHorizionChangeLineViews_item_text_size, 15);
            textViewBackgroundRes = arr.getResourceId(R.styleable.AutoHorizionChangeLineViews_item_text_bg,R.drawable.dd_shape_1a389bee_content_15);
            arr.recycle();
        }
    }

    public AutoHorizionChangeLineViews(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if(attrs != null){
            TypedArray arr = getContext().obtainStyledAttributes(attrs, R.styleable.AutoHorizionChangeLineViews, 0, 0);
            itemHorizionSpace = arr.getDimension(R.styleable.AutoHorizionChangeLineViews_item_horizion_space, padding_15);
            itemVerticlSpace = arr.getDimension(R.styleable.AutoHorizionChangeLineViews_item_verticl_space, padding_15);
            textColor = arr.getColor(R.styleable.AutoHorizionChangeLineViews_item_text_color, getResources().getColor(R.color.blue_6186a6));
            textSize = arr.getInt(R.styleable.AutoHorizionChangeLineViews_item_text_size, 15);
            textViewBackgroundRes = arr.getResourceId(R.styleable.AutoHorizionChangeLineViews_item_text_bg,R.drawable.dd_shape_1a389bee_content_15);
            arr.recycle();
        }
    }

    /**
     * 设置文本内容
     * @param texts
     */
    public void setTexts(ArrayList<String> texts){
        width = getScreenWidth(getContext()) - padding_15 * 2;
        if(texts != null){
            this.texts = texts;
        } else {
            this.texts = new ArrayList<>();
        }
        initTextView();
    }

    /**
     * 设置文本内容
     */
    private void initTextView(){
        int size = this.texts.size();
        if(size > 8){
            size = 8;
        }
        textViews = new TextView[size];
        textWidths = new ArrayList<>();
        textLayoutParams = new ArrayList<>();
        for(int i = 0; i < size; i++){
            textViews[i] = new TextView(getContext());
            final String text = texts.get(i);
            textViews[i].setText(text);
            textViews[i].setTextSize(textSize);
            textViews[i].setSingleLine(true);
            textViews[i].setEllipsize(TextUtils.TruncateAt.END);
            textViews[i].setTextColor(textColor);
            Log.i("xx","ids[i]:" + ids[i] + "  i:" + i);
            textViews[i].setId(ids[i]);
            textViews[i].setPadding((int)padding_15,0,(int)padding_15,0);
            final int index = i;
            textViews[i].setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onTextViewItemClickListener != null){
                        onTextViewItemClickListener.onClick(index, text, v);
                    }
                }
            });
            textViews[i].setBackgroundResource(textViewBackgroundRes);
            textViews[i].setGravity(Gravity.CENTER_VERTICAL);
            float width1 = textViews[i].getPaint().measureText(text) + padding_15 * 2;
            textWidths.add(width1);
            LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
            textLayoutParams.add(layoutParams);
        }

       layoutTextView(size);
    }

    private void layoutTextView(int size){
        LayoutParams params = textLayoutParams.get(0);
        TextView tv = textViews[0];
        if(params != null && tv != null){
            addView(tv, params);
        }
        float alreadyWidth = textWidths.get(0);
        int verb = 0;
        int subject = 0;
        for(int i = 1 ; i < size; i++){
            LayoutParams layoutParams = textLayoutParams.get(i);
            TextView textView = textViews[i];
            float h_width = alreadyWidth + textWidths.get(i) + itemHorizionSpace;
            Log.i("xx","第" + i + "textview" + textWidths.get(i) + "h_width:" + h_width);
            Log.i("xx","id:" + ids[i-1] + "  index:" + (i-1) +  "  text:" + texts.get(i));
            if(h_width < width){
                //部署同一行
                alreadyWidth = h_width;
                layoutParams.addRule(RelativeLayout.RIGHT_OF, ids[i-1]);
                if(subject != 0){
                    layoutParams.topMargin = (int)itemVerticlSpace;
                    layoutParams.addRule(verb, subject);
                }
                layoutParams.leftMargin = (int)itemHorizionSpace;
                Log.i("xx","同一行 " + i + "  textviewwidth:" + textWidths.get(i) + "  h_width:" + h_width);
                addView(textView, layoutParams);
            } else {
                //下一行
                alreadyWidth = textWidths.get(i);
                layoutParams.addRule(RelativeLayout.BELOW, ids[i-1]);
                verb = RelativeLayout.BELOW;
                subject = ids[i-1];
                layoutParams.topMargin = (int)itemVerticlSpace;
                Log.i("xx","下一行 " + i + "  textviewwidth:" + textWidths.get(i) + "  h_width:" + h_width );
                addView(textView, layoutParams);
            }
        }
    }

    /**
     * 获取文本控件
     * @return
     */
    public TextView[] getTextViews() {
        return textViews;
    }

    public void setOnTextViewItemClickListener(OnTextViewItemClickListener onTextViewItemClickListener) {
        this.onTextViewItemClickListener = onTextViewItemClickListener;
    }

    public interface OnTextViewItemClickListener {
        void onClick(int index, String text, View view);
    }


    /**
     * 根据手机的分辨率dp 的单位转成px(像素)
     *
     * @param context
     * @param dpValue dp
     * @return 返回像素
     */
    public static int dipTopx(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f * (dpValue >= 0 ? 1 : -1));
    }

    /**
     * 获取屏幕宽度
     *
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }
}
