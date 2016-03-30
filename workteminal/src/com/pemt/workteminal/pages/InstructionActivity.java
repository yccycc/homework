package com.pemt.workteminal.pages;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import com.pemt.workteminal.R;
public class InstructionActivity extends Activity {

    private ListView mInstructionLv;

    private void assignViews() {
        mInstructionLv = (ListView) findViewById(R.id.instruction_lv);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instruction);
        assignViews();
        mInstructionLv.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return 7;
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                return getLayoutInflater().inflate(R.layout.instruction_item,null);
            }
        });
    }
}