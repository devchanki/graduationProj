package com.chanki.tmi;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Map;

public class boardListAdapter extends RecyclerView.Adapter<boardListAdapter.ViewHolder> {

    private ArrayList<Board> mData = null;

    // 아이템 뷰를 저장하는 뷰홀더 클래스.
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title ;
        TextView name;
        TextView major;
        TextView date;

        ViewHolder(View itemView) {
            super(itemView) ;
            // 뷰 객체에 대한 참조. (hold strong reference)
            title = itemView.findViewById(R.id.boardTitle);
            name = itemView.findViewById(R.id.boardName);
            major = itemView.findViewById(R.id.boardMajor);
            date = itemView.findViewById(R.id.boardDate);
        }
    }

    // 생성자에서 데이터 리스트 객체를 전달받음.
    boardListAdapter(ArrayList<Board> list) {
        mData = list;
    }

    // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.
    @Override
    public boardListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext() ;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;
        View view = inflater.inflate(R.layout.board_list, parent, false) ;
        boardListAdapter.ViewHolder vh = new boardListAdapter.ViewHolder(view) ;
        return vh ;
    }

    // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
    @Override
    public void onBindViewHolder(boardListAdapter.ViewHolder holder, int position) {
        Board text = mData.get(position);
        holder.name.setText(text.getUserName());
        holder.title.setText(text.getTitle());
        holder.major.setText(text.getUserMajor());
        holder.date.setText(text.getTime());

    }

    // getItemCount() - 전체 데이터 갯수 리턴.
    @Override
    public int getItemCount() {
        return mData.size();
    }
}
