package com.mj.cab10.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mj.cab10.R;
import com.mj.cab10.adapter.OrdersAdapter;
import com.mj.cab10.model.Orders;

import java.util.ArrayList;
import java.util.List;

public class ScheduleOrdersFragment extends Fragment {

    RecyclerView rvScheduleOrders;
    OrdersAdapter adapter;
    List<Orders> list;

    public ScheduleOrdersFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new Orders());
        }

        if (list.size() == 0)
            return inflater.inflate(R.layout.fragment_empty_schdule_orders, container, false);
        else {
            View v = inflater.inflate(R.layout.fragment_your_trips, container, false);

            rvScheduleOrders = v.findViewById(R.id.rvScheduleOrders);
            adapter = new OrdersAdapter(getActivity(), list);
            rvScheduleOrders.setAdapter(adapter);
            rvScheduleOrders.setLayoutManager(new LinearLayoutManager(getActivity()));

            return v;
        }
    }
}
