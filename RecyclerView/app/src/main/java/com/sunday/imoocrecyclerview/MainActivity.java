package com.sunday.imoocrecyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private MyRecyclerViewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //找到recyclerview控件并关联控件
        mRecyclerView = findViewById(R.id.recycler_view);
        // 线性布局
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        //给recyclerview设置适配器为myrecyclerviewdadapter
        mAdapter = new MyRecyclerViewAdapter(this, mRecyclerView);
        mRecyclerView.setAdapter(mAdapter);

        //设置添加、删除、移动、改变的动画效果。
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

//        itemView点击事件监听
        mAdapter.setOnItemClickListener(new MyRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(MainActivity.this, "第" + position + "数据被点击", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 添加数据
     * @param v
     */
    public void onAddDataClick (View v) {
        List<String> data = new ArrayList<>();

        for (int i = 0 ; i < 20 ; i ++) {
            String s = "第" + i + "条数据";
            data.add(s);
        }

        mAdapter.setDataSource(data);
    }

    /**
     * 切换布局
     * @param v
     */
    public void onChangeLayoutClick (View v) {
//        从线性布局 切换为 网格布局
        if (mRecyclerView.getLayoutManager().getClass() == LinearLayoutManager.class) {
//            网格布局
            GridLayoutManager gridLayoutManager = new GridLayoutManager(this , 2);
            mRecyclerView.setLayoutManager(gridLayoutManager);
        }else if (mRecyclerView.getLayoutManager().getClass() == GridLayoutManager.class) {
//            瀑布流布局
            StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
            mRecyclerView.setLayoutManager(staggeredGridLayoutManager);
        } else {
            // 线性布局
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            mRecyclerView.setLayoutManager(linearLayoutManager);
        }
    }

    /**
     * 插入一条数据
     * @param v
     */
    public void onInsertDataClick (View v) {
        mAdapter.addData(1);
    }

    /**
     * 删除一条数据
     * @param v
     */
    public void onRemoveDataClick (View v) {
        mAdapter.removeData(1);
    }
}
