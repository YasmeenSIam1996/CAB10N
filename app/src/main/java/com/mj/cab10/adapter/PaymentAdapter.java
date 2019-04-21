package com.mj.cab10.adapter;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.mj.cab10.R;
import com.mj.cab10.model.CarType;
import com.mj.cab10.model.Payment;

import java.util.List;

public class PaymentAdapter extends RecyclerView.Adapter<PaymentAdapter.MyViewHolder> {
    private Context context;
    private List<Payment> objects;
    private int idChecked = -1;

    public PaymentAdapter(Context context, List<Payment> objects) {
        this.context = context;
        this.objects = objects;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_payment, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int i) {

        holder.rbPay.setChecked(idChecked == i);

        holder.imgCirclePay.setImageResource(R.drawable.rb_unselected_payment);
        holder.layoutPay.setBackgroundResource(R.drawable.bg_pay_layout_unselected);
        holder.imgPay.setImageResource(
                context.getResources().getIdentifier(objects.get(i).getImage(),
                        "drawable",
                        context.getPackageName()));

        holder.tvPay.setText(objects.get(i).getName());


        if (holder.rbPay.isChecked()) {
            holder.imgPay.setImageResource(context.getResources().getIdentifier(objects.get(i).getSelectedImg(),
                    "drawable",
                    context.getPackageName()));

            holder.imgCirclePay.setImageResource(R.drawable.rb_selected_payment);
            holder.layoutPay.setBackgroundResource(R.drawable.bg_button);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                holder.layoutPay.setElevation(10);
            }
        }
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvPay;
        ImageView imgPay, imgCirclePay;
        RadioButton rbPay;
        View layoutPay;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvPay = itemView.findViewById(R.id.tvPay);
            imgPay = itemView.findViewById(R.id.imgPay);
            rbPay = itemView.findViewById(R.id.rbPay);
            imgCirclePay = itemView.findViewById(R.id.imgCirclePay);
            layoutPay = itemView.findViewById(R.id.layoutPay);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    idChecked = getAdapterPosition();
                    idChecked = getAdapterPosition();
                    notifyDataSetChanged();
                }
            });
        }

    }
}
