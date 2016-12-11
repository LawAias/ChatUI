package com.example.sihuan.chatuidemo.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.sihuan.chatuidemo.R;
import com.example.sihuan.chatuidemo.entity.Msg;

import java.util.List;


/**
 * Created by sihuan on 2016/12/11.
 * com.example.sihuan.chatuidemo.adapter
 */

public class MsgAdapter extends RecyclerView.Adapter<MsgAdapter.MsgViewHolder> {
    private static final String TAG = "MsgAdapter";

    private List<Msg> msgs;

    public MsgAdapter(List<Msg> msgs) {
        this.msgs = msgs;
    }

    @Override
    public MsgViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_msg, parent, false);
        return new MsgViewHolder(view);
    }

    public void addMsg(Msg msg) {
        msgs.add(msg);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(MsgViewHolder holder, int position) {
        Msg msg = msgs.get(position);
        Log.e(TAG, "onBindViewHolder: " + msg.getContent());
        if (msg.getType() == Msg.TYPE_RECEIVED) {
            holder.leftMsg.setVisibility(View.VISIBLE);
            holder.rightMsg.setVisibility(View.GONE);
            holder.leftMsg.setText(msg.getContent());
        } else if (msg.getType() == Msg.TYPE_SEND) {
            holder.rightMsg.setVisibility(View.VISIBLE);
            holder.leftMsg.setVisibility(View.GONE);
            holder.rightMsg.setText(msg.getContent());
        }
    }


    @Override
    public int getItemCount() {
        return msgs == null ? 0 : msgs.size();
    }

    class MsgViewHolder extends RecyclerView.ViewHolder {
        private TextView rightMsg;
        private TextView leftMsg;
//        private LinearLayout rightLaout;
//        private LinearLayout leftLayout;
//        public TextView getRightMsg() {
//            return rightMsg;
//        }
//
//        public TextView getLeftMsg() {
//            return leftMsg;
//        }

        MsgViewHolder(View itemView) {
            super(itemView);
            rightMsg = (TextView) itemView.findViewById(R.id.right_msg);
            leftMsg = (TextView) itemView.findViewById(R.id.left_msg);
//            rightLaout = (LinearLayout) itemView.findViewById(R.id.right_layout);
//            leftLayout = (LinearLayout) itemView.findViewById(R.id.left_layout);
        }
    }

}
