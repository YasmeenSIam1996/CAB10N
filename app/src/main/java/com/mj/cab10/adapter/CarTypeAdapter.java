package com.mj.cab10.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import com.mj.cab10.R;
import com.mj.cab10.model.CarType;

import java.util.List;

public class CarTypeAdapter extends RecyclerView.Adapter<CarTypeAdapter.MyViewHolder> {
    private Context context;
    private List<CarType> objects;
    private int idChecked = -1;

    public CarTypeAdapter(Context context, List<CarType> objects) {
        this.context = context;
        this.objects = objects;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_car_radiobutton, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int i) {

        myViewHolder.rbSelectCar.setChecked(idChecked == i);

        if (myViewHolder.rbSelectCar.isChecked()) {
            myViewHolder.tvVehiclePrice.setVisibility(View.VISIBLE);
            myViewHolder.viewRectangle.setBackgroundResource(R.drawable.bg_selected_car_type);
            myViewHolder.rbSelectCar.setBackgroundResource(R.drawable.btn_on_car_type);
        } else {
            myViewHolder.tvVehiclePrice.setVisibility(View.GONE);
            myViewHolder.viewRectangle.setBackgroundResource(R.drawable.bg_unselected_car_type);
            myViewHolder.rbSelectCar.setBackgroundResource(R.drawable.btn_off_car_type);

        }

    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvVehiclePrice;
        RadioButton rbSelectCar;
        View viewRectangle;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvVehiclePrice = itemView.findViewById(R.id.tvVehiclePrice);
            rbSelectCar = itemView.findViewById(R.id.rbSelectCar);
            viewRectangle = itemView.findViewById(R.id.viewRectangle);

            rbSelectCar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setRbChecked();
                }

            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    idChecked = getAdapterPosition();
                    setRbChecked();
                }
            });
        }

        private void setRbChecked() {
            idChecked = getAdapterPosition();
            notifyDataSetChanged();
        }

    }
}
