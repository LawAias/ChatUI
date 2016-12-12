package com.example.sihuan.chatuidemo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sihuan.chatuidemo.R;
import com.example.sihuan.chatuidemo.entity.Msg;

import java.util.List;

/**
 * Created by sihuan on 2016/12/12.
 * com.example.sihuan.chatuidemo.adapter
 */

public class MultiMsgTypeAdapter extends RecyclerView.Adapter {

    private List<Msg> msgs;

    public MultiMsgTypeAdapter(List<Msg> msgs) {
        this.msgs = msgs;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case Msg.TYPE_RECEIVED:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_msg_left, parent, false);
                return new LeftMsgViewHolder(view);
            case Msg.TYPE_SEND:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_msg_right, parent, false);
                return new RightMsgViewHolder(view);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Msg msg = msgs.get(position);
        switch (getItemViewType(position)) {
            case Msg.TYPE_RECEIVED:
                LeftMsgViewHolder leftMsgHolder = (LeftMsgViewHolder) holder;
                leftMsgHolder.leftMsg.setText(msg.getContent());
                break;
            case Msg.TYPE_SEND:
                RightMsgViewHolder rightMsgHolder = (RightMsgViewHolder) holder;
                rightMsgHolder.rightMsg.setText(msg.getContent());
                break;
            default:
                throw new RuntimeException("error msg type");
        }

    }

    @Override
    public int getItemCount() {
        return msgs == null ? 0 : msgs.size();
    }

    @Override
    public int getItemViewType(int position) {
        return msgs.get(position).getType();
    }

    public void addMsg(Msg msg) {
        msgs.add(msg);
        notifyDataSetChanged();
    }

    private class RightMsgViewHolder extends RecyclerView.ViewHolder {
        TextView rightMsg;

        RightMsgViewHolder(View itemView) {
            super(itemView);
            rightMsg = (TextView) itemView.findViewById(R.id.right_msg);
        }
    }

    private class LeftMsgViewHolder extends RecyclerView.ViewHolder {
        TextView leftMsg;

        LeftMsgViewHolder(View itemView) {
            super(itemView);
            leftMsg = (TextView) itemView.findViewById(R.id.left_msg);
        }
    }
}
