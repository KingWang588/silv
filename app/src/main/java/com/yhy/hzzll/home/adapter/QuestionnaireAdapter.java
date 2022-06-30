package com.yhy.hzzll.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yhy.hzzll.R;
import com.yhy.hzzll.home.entity.Questionnaire;

import java.util.List;

public class QuestionnaireAdapter extends BaseAdapter {

    List<Questionnaire.DataBean> list;
    Context context;

    public QuestionnaireAdapter(List<Questionnaire.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        view = view = LayoutInflater.from(context).inflate(R.layout.item_lawyer_coop_question,
                null);

        TextView tv_question = view.findViewById(R.id.tv_question);
        TextView tv_answer = view.findViewById(R.id.tv_answer);


        tv_question.setText(list.get(i).getT_name());
        tv_answer.setText(list.get(i).getD_name());


        return view;
    }
}
