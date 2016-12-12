package com.example.sihuan.chatuidemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.sihuan.chatuidemo.R;
import com.example.sihuan.chatuidemo.adapter.MsgAdapter;
import com.example.sihuan.chatuidemo.adapter.MultiMsgTypeAdapter;
import com.example.sihuan.chatuidemo.entity.Msg;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sihuan on 2016/12/11.
 * MainActivity
 */

public class MainActivity extends AppCompatActivity {

    private RecyclerView msgPanel;
    private Button sendMsg;
    private EditText msgInput;
    private MultiMsgTypeAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);
        findViews();
        initView();
        initEvent();

    }

    private void initEvent() {
        sendMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(msgInput.getText().toString())) {
                    return;
                }
                adapter.addMsg(new Msg(msgInput.getText().toString(), Msg.TYPE_SEND));
                msgInput.setText("");
                msgPanel.scrollToPosition(adapter.getItemCount() - 1);
            }
        });

    }

    private void initView() {
        List<Msg> list = new ArrayList();
        list.add(new Msg("hi", Msg.TYPE_RECEIVED));
        list.add(new Msg("hi", Msg.TYPE_SEND));
        msgPanel.setLayoutManager(new LinearLayoutManager(this));
//        msgPanel.setAdapter(adapter = new MsgAdapter(list));
        msgPanel.setAdapter(adapter = new MultiMsgTypeAdapter(list));

    }

    private void findViews() {
        msgPanel = (RecyclerView) findViewById(R.id.msg_panel);
        sendMsg = (Button) findViewById(R.id.send_message);
        msgInput = (EditText) findViewById(R.id.et_input);
    }
}
