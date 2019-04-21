package com.mj.cab10;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mj.cab10.adapter.CarTypeAdapter;
import com.mj.cab10.adapter.PaymentAdapter;
import com.mj.cab10.model.CarType;
import com.mj.cab10.model.Payment;
import com.skyfishjy.library.RippleBackground;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements OnMapReadyCallback, View.OnClickListener {

    private GoogleMap mMap;
    ImageView imgScheduleTrip;

//    boolean isPromoShown = false;

    View btnReserveCar;

    TextView tvPick, tvPickCarState, tvAddPromoCode;

    Calendar calendarCurrentTime;
    ImageView imgCalenderSchedule;
    LinearLayout btnPickTime;

    View layoutKeyboard;

    //card_trip_details views
    CardView cardTripDetails;
    RelativeLayout layoutChooseCarType, layoutChoosePaymentWay;

    //card_route views
    TextView tvDistance, tvEnd;
    View v_h;
    FrameLayout routeLine;
    ImageView imgEndPoint, imgFirstPoint, imgNavIcon;

    // drawer
    DrawerLayout drawer;
    View itemNavYourTrips, itemNavWallet, itemNavHome, itemNavFreeTrips, itemNavHelp;

    int pickCarState;

    RippleBackground pulse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        findViews();

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private void findViews() {
        //card_route views
        imgFirstPoint = findViewById(R.id.imgFirstPoint);
        imgEndPoint = findViewById(R.id.imgEndPoint);
        routeLine = findViewById(R.id.routeLine);
        v_h = findViewById(R.id.v_h);
        tvEnd = findViewById(R.id.tvEnd);
        tvDistance = findViewById(R.id.tvDistance);


        //card trip_details views
        layoutChooseCarType = findViewById(R.id.layoutChooseCarType);
        layoutChoosePaymentWay = findViewById(R.id.layoutChoosePaymentWay);


        cardTripDetails = findViewById(R.id.cardTripDetails);

        imgScheduleTrip = findViewById(R.id.imgScheduleTrip);
        btnReserveCar = findViewById(R.id.btnReserveCar);
        tvPickCarState = findViewById(R.id.tvPickCarState);
        layoutKeyboard = findViewById(R.id.layoutKeyboard);
        tvAddPromoCode = findViewById(R.id.tvAddPromoCode);
        pulse = findViewById(R.id.pulse);
        imgNavIcon = findViewById(R.id.imgNavIcon);

        // drawer
        drawer = findViewById(R.id.drawer);
        itemNavHome = findViewById(R.id.itemNavHome);
        itemNavYourTrips = findViewById(R.id.itemNavYourTrips);
        itemNavWallet = findViewById(R.id.itemNavWallet);
        itemNavFreeTrips = findViewById(R.id.itemNavFreeTrips);
        itemNavHelp = findViewById(R.id.itemNavHelp);


        imgScheduleTrip.setOnClickListener(this);
        btnReserveCar.setOnClickListener(this);
        layoutChooseCarType.setOnClickListener(this);
        layoutChoosePaymentWay.setOnClickListener(this);
        tvAddPromoCode.setOnClickListener(this);

        //drawer
        imgNavIcon.setOnClickListener(this);
        itemNavHome.setOnClickListener(this);
        itemNavYourTrips.setOnClickListener(this);
        itemNavWallet.setOnClickListener(this);
        itemNavFreeTrips.setOnClickListener(this);
        itemNavHelp.setOnClickListener(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgScheduleTrip:
                scheduleTripSheet();
                break;

            case R.id.btnPickTime:
                showDateDialog();
                break;

            case R.id.layoutChooseCarType:
                showCarTypeSheet();
                break;

            case R.id.layoutChoosePaymentWay:
                showPaySheet();
                break;

            case R.id.tvAddPromoCode:
//                showKeyboard();
                break;

            case R.id.btnAddPromo:
                layoutKeyboard.setVisibility(View.GONE);
                break;

            case R.id.imgNavIcon:
                drawer.openDrawer(Gravity.START);
                break;

            case R.id.itemNavYourTrips:
                drawer.closeDrawer(Gravity.START);
                startActivity(new Intent(this, YourTripsActivity.class));
                break;

            case R.id.itemNavWallet:
                drawer.closeDrawer(Gravity.START);
                startActivity(new Intent(this, WalletActivity.class));
                break;

            case R.id.itemNavHome:
                drawer.closeDrawer(Gravity.START);
                break;

            case R.id.itemNavFreeTrips:
                drawer.closeDrawer(Gravity.START);
                startActivity(new Intent(this, FreeDrivesActivity.class));
                break;

            case R.id.itemNavHelp:
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
                startActivity(i);
                break;

            case R.id.btnReserveCar:
                switch (pickCarState) {
                    case 0:
                        pickCarState = 1;
                        tvPickCarState.setText("تأكيد مكان الوصول");
                        imgScheduleTrip.setVisibility(View.GONE);

                        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
                        tvPickCarState.setLayoutParams(layoutParams);

                        imgEndPoint.setVisibility(View.VISIBLE);
                        routeLine.setVisibility(View.VISIBLE);
                        v_h.setVisibility(View.VISIBLE);
                        tvEnd.setVisibility(View.VISIBLE);
                        tvDistance.setVisibility(View.VISIBLE);
                        break;

                    case 1:
                        pickCarState = 2;
                        tvPickCarState.setText("انطلق الآن");
                        btnReserveCar.setBackgroundResource(R.drawable.bg_green_btn);
                        tvPickCarState.setTextColor(getResources().getColor(android.R.color.white));
                        cardTripDetails.setVisibility(View.VISIBLE);
                        break;

                    case 2:
                        pickCarState = 3;
                        tvPickCarState.setText("إلغاء");
                        btnReserveCar.setBackgroundResource(R.drawable.bg_black_btn);

                        pulse.setVisibility(View.VISIBLE);
                        pulse.startRippleAnimation();
                        break;

                    case 3:
                        showAlertCancelOrder();
                        break;
                }
                break;
        }
    }

    private void showKeyboard() {

//        isPromoShown = true;

        layoutKeyboard.setVisibility(View.VISIBLE);
        EditText txtAddPromoCode = findViewById(R.id.txtAddPromoCode);
        Button btnAddPromo = findViewById(R.id.btnAddPromo);

        btnAddPromo.setOnClickListener(this);

        txtAddPromoCode.requestFocus();
        txtAddPromoCode.setFocusableInTouchMode(true);

        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.showSoftInput(txtAddPromoCode, InputMethodManager.SHOW_FORCED);

    }


    private void showPaySheet() {
        List<Payment> list;
        PaymentAdapter adapter;
        RecyclerView rvPay;
        ImageView imgClosePayment;

        final BottomSheetDialog dialog = new BottomSheetDialog(this, R.style.SheetDialog);
        dialog.setContentView(R.layout.sheet_payment_way);
        rvPay = dialog.findViewById(R.id.rvPay);
        imgClosePayment = dialog.findViewById(R.id.imgClosePayment);

        list = new ArrayList<>();

        list.add(new Payment("ic_pay_pal", "Paypal", "ic_pay_selected_pal"));
        list.add(new Payment("ic_pay_credit", "الكتروني", "ic_pay_selected_credit"));
        list.add(new Payment("ic_pay_cash", "نقدي", "ic_pay_selected_cash"));
        list.add(new Payment("ic_pay_wallet", "المحفظة", "ic_pay_selected_wallet"));

        adapter = new PaymentAdapter(this, list);

        rvPay.setAdapter(adapter);
        rvPay.setLayoutManager(new GridLayoutManager(this, 4));

        imgClosePayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }


    //car type sheet dialog
    private void showCarTypeSheet() {
        List<CarType> list;
        CarTypeAdapter adapter;
        RecyclerView rvCarType;
        ImageView imgCloseCarType;

        final BottomSheetDialog dialog = new BottomSheetDialog(this, R.style.SheetDialog);
        dialog.setContentView(R.layout.sheet_car_category);
        rvCarType = dialog.findViewById(R.id.rvCarType);
        imgCloseCarType = dialog.findViewById(R.id.imgCloseCarType);

        list = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            list.add(new CarType());
        }
        adapter = new CarTypeAdapter(this, list);

        rvCarType.setAdapter(adapter);
        rvCarType.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        imgCloseCarType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }


    //schedule trip sheet dialog
    private void scheduleTripSheet() {

        ImageView imgCloseSchedule;
        RecyclerView rvCarTypeSchedule;
        CarTypeAdapter adapter;
        List<CarType> list;

        final BottomSheetDialog dialog = new BottomSheetDialog(this, R.style.SheetDialog);
        dialog.setContentView(R.layout.sheet_schedule_trip);
        dialog.setCanceledOnTouchOutside(false);

        imgCloseSchedule = dialog.findViewById(R.id.imgCloseSchedule);
        rvCarTypeSchedule = dialog.findViewById(R.id.rvCarTypeSchedule);
        btnPickTime = dialog.findViewById(R.id.btnPickTime);
        tvPick = dialog.findViewById(R.id.tvPick);
        imgCalenderSchedule = dialog.findViewById(R.id.imgCalenderSchedule);

        list = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            list.add(new CarType());
        }

        adapter = new CarTypeAdapter(this, list);

        rvCarTypeSchedule.setAdapter(adapter);
        rvCarTypeSchedule.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        imgCloseSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnPickTime.setOnClickListener(this);

        dialog.show();
    }

    //show calender dialog
    private void showDateDialog() {
        calendarCurrentTime = Calendar.getInstance();
        int DAY_OF_MONTH = calendarCurrentTime.get(Calendar.DAY_OF_MONTH);
        int MONTH = calendarCurrentTime.get(Calendar.MONTH);
        int YEAR = calendarCurrentTime.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                month++;

                showTimeDialog(dayOfMonth + "/" + month + "/" + year);
            }
        }, YEAR, MONTH, DAY_OF_MONTH);

        datePickerDialog.show();
    }


    private void showTimeDialog(final String pickedDate) {
        int MINUTE = calendarCurrentTime.get(Calendar.MINUTE);
        int HOUR_OF_DAY = calendarCurrentTime.get(Calendar.HOUR_OF_DAY);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                String format = "";

                if (hourOfDay == 0) {
                    hourOfDay += 12;
                    format = "AM";
                } else if (hourOfDay == 12) {
                    format = "PM";
                } else if (hourOfDay > 12) {
                    hourOfDay -= 12;
                    format = "PM";
                } else {
                    format = "AM";
                }

                tvPick.setText(pickedDate + "  " + hourOfDay + ":" + minute + " " + format);
                btnPickTime.setBackgroundResource(R.drawable.bg_time_picked);
                imgCalenderSchedule.setVisibility(View.GONE);

            }
        }, HOUR_OF_DAY, MINUTE, false);
        timePickerDialog.show();
    }

    private void showAlertCancelOrder() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.MyAlertDialogTheme);

        builder/*.setTitle("Delete Dialog")*/
                .setMessage("هل أنت متأكد من إلغاء الطلب؟")
                .setPositiveButton("نعم", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        pickCarState = 1;
                        pulse.setVisibility(View.GONE);
                        btnReserveCar.setBackgroundResource(R.drawable.bg_button);
                        cardTripDetails.setVisibility(View.GONE);
                        tvPickCarState.setTextColor(getResources().getColor(R.color.colorTextTitle));
                        onBackPressed();

                    }
                })
                .setNegativeButton("لا", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing

                    }
                }).show();
    }

    @Override
    public void onBackPressed() {

        if (pickCarState == 1) {
            pickCarState = 0;

            tvPickCarState.setText(getResources().getString(R.string.confirmPickUp));

            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_START);
            layoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
            layoutParams.setMarginStart(40);
            tvPickCarState.setLayoutParams(layoutParams);

            imgScheduleTrip.setVisibility(View.VISIBLE);

            imgEndPoint.setVisibility(View.GONE);
            routeLine.setVisibility(View.GONE);
            v_h.setVisibility(View.GONE);
            tvEnd.setVisibility(View.GONE);
            tvDistance.setVisibility(View.GONE);
        } else if (pickCarState == 2) {
            pickCarState = 1;
            tvPickCarState.setText("تأكيد مكان الوصول");
            btnReserveCar.setBackgroundResource(R.drawable.bg_button);
            tvPickCarState.setTextColor(getResources().getColor(android.R.color.black));
            cardTripDetails.setVisibility(View.GONE);
        } else if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else
            super.onBackPressed();

    }
}
