package com.example.ruxing.practice;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Notification;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<StudentEntity> mData;
    private MyAdapter mAdapter;

    private View mHeaderView;
    private View mFooterView;
    private View mEmptyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        mData = new ArrayList<>();
        //setData();
        initView();
        mAdapter = new MyAdapter(mData, this);
        mAdapter.setHeaderView(mHeaderView);
        mAdapter.setFooterView(mFooterView);
        mAdapter.setEmptyView(mEmptyView);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRecyclerView.setAdapter(mAdapter);
    }

    private void setData() {
        for (int i = 0; i < 10; i++) {
            StudentEntity entity = new StudentEntity();
            entity.setName("name" + i);
            entity.setAge("age" + i);
            mData.add(entity);
        }
    }

    private void initView() {
        mHeaderView = LayoutInflater.from(this).inflate(R.layout.header, mRecyclerView, false);
        mFooterView = LayoutInflater.from(this).inflate(R.layout.footer, mRecyclerView, false);
        mEmptyView = LayoutInflater.from(this).inflate(R.layout.empty, mRecyclerView, false);
    }

}
