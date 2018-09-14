package com.example.ruxing.practice;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ruxing on 2018/9/5.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private final int ITEM_TYPE_HEADER = 0;
    private final int ITEM_TYPE_NRMAL = 1;
    private final int ITEM_TYPE_FOOTER = 2;
    private final int ITEM_TYPE_EMPTY = 3;

    private View mHeaderView;
    private View mFooterView;
    private View mEmptyView;

    private List<StudentEntity> mDataList;
    private Context mContext;

    public MyAdapter(List<StudentEntity> mData, Context mContext) {
        this.mDataList = mData;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {//根据不同的type，创建不同布局的ViewHolder
            case ITEM_TYPE_HEADER:
                return new MyViewHolder(mHeaderView);
            case ITEM_TYPE_EMPTY:
                return new MyViewHolder(mEmptyView);
            case ITEM_TYPE_FOOTER:
                return new MyViewHolder(mFooterView);
            default:
                return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        int type = getItemViewType(position);
        if (type == ITEM_TYPE_HEADER || type == ITEM_TYPE_FOOTER || type == ITEM_TYPE_EMPTY) return;
        holder.mTvName.setText(mDataList.get(getRealItemPosition(position)).getName());
        holder.mTvAge.setText(mDataList.get(getRealItemPosition(position)).getAge());
    }

    @Override
    public int getItemCount() {
        int itemCount = mDataList.size();
        if (null != mEmptyView && itemCount == 0) itemCount++;
        if (null != mHeaderView) itemCount++;
        if (null != mFooterView) itemCount++;
        return itemCount;
    }

    @Override
    public int getItemViewType(int position) {
        if (null != mHeaderView && position == 0)
            return ITEM_TYPE_HEADER;
        else if (null != mFooterView && position == getItemCount() - 1)
            return ITEM_TYPE_FOOTER;
        else if (null != mEmptyView && mDataList.size() == 0)
            return ITEM_TYPE_EMPTY;
        return ITEM_TYPE_NRMAL;

    }

    private int getRealItemPosition(int position) {
        if (null != mHeaderView) return position - 1;
        return position;
    }

    /**
     * 设置header
     */
    public void setHeaderView(View headerView) {
        this.mHeaderView = headerView;
        notifyItemInserted(0);
    }

    /**
     * 设置footer
     */
    public void setFooterView(View footerView) {
        this.mFooterView = footerView;
        notifyItemInserted(getItemCount() - 1);
    }

    /**
     * 设置data为空的时候显示的view
     */
    public void setEmptyView(View emptyView) {
        this.mEmptyView = emptyView;
        notifyDataSetChanged();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView mTvName;
        private TextView mTvAge;

        public MyViewHolder(View itemView) {
            super(itemView);
            if (itemView == mHeaderView || itemView == mEmptyView || itemView == mFooterView)
                return;
            mTvName = itemView.findViewById(R.id.tv_name);
            mTvAge = itemView.findViewById(R.id.tv_age);

        }
    }
}
