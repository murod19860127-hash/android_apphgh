package com.example.bannerreyka;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.text.InputType;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.*;
import android.graphics.drawable.GradientDrawable;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class MainActivity extends Activity {
    LinearLayout root;
    EditText width, height, areaPrice, hRail, vRail, rail34Price, rail32Price, installPrice;
    TextView areaOut, railMetersOut, rail34Out, rail32Out, bannerOut, installOut, total34Out, total32Out;
    DecimalFormat df = new DecimalFormat("#,##0.##", new DecimalFormatSymbols(Locale.US));

    int blue = Color.rgb(11,79,128);
    int lightBlue = Color.rgb(220,236,247);
    int green = Color.rgb(213,234,199);
    int yellow = Color.rgb(255,242,194);
    int dark = Color.rgb(35,35,35);

    @Override public void onCreate(Bundle b) {
        super.onCreate(b);
        buildUi();
        calculate();
    }

    TextView tv(String s, float sp, boolean bold) {
        TextView t = new TextView(this);
        t.setText(s);
        t.setTextSize(sp);
        t.setTextColor(dark);
        t.setGravity(Gravity.CENTER_VERTICAL);
        if (bold) t.setTypeface(null, android.graphics.Typeface.BOLD);
        t.setPadding(12, 8, 12, 8);
        return t;
    }

    EditText input(String value) {
        EditText e = new EditText(this);
        e.setText(value);
        e.setTextSize(16);
        e.setTextColor(dark);
        e.setSingleLine(true);
        e.setGravity(Gravity.CENTER);
        e.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        e.setBackgroundResource(com.example.bannerreyka.R.drawable.edit_bg);
        e.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s,int st,int c,int a){}
            public void onTextChanged(CharSequence s,int st,int b,int c){ calculate(); }
            public void afterTextChanged(Editable e){}
        });
        return e;
    }

    LinearLayout row(String label, EditText input) {
        LinearLayout r = new LinearLayout(this);
        r.setOrientation(LinearLayout.HORIZONTAL);
        r.setPadding(8,2,8,2);
        TextView l = tv(label, 15, true);
        r.addView(l, new LinearLayout.LayoutParams(0, 52, 1.45f));
        r.addView(input, new LinearLayout.LayoutParams(0, 52, 0.75f));
        return r;
    }

    TextView section(String title) {
        TextView s = tv(title, 16, true);
        s.setTextColor(Color.rgb(25,55,75));
        s.setGravity(Gravity.CENTER);
        s.setBackgroundResource(R.drawable.section_bg);
        s.setPadding(6, 10, 6, 10);
        root.addView(s, new LinearLayout.LayoutParams(-1, 48));
        return s;
    }

    TextView result(String label, boolean big) {
        LinearLayout r = new LinearLayout(this);
        r.setOrientation(LinearLayout.HORIZONTAL);
        r.setPadding(8, 2, 8, 2);
        TextView l = tv(label, big ? 16 : 15, big);
        TextView v = tv("", big ? 17 : 15, true);
        v.setGravity(Gravity.CENTER);
        if (big) v.setBackgroundResource(R.drawable.result_bg);
        r.addView(l, new LinearLayout.LayoutParams(0, 54, 1.45f));
        r.addView(v, new LinearLayout.LayoutParams(0, 54, 0.75f));
        root.addView(r);
        return v;
    }

    void buildUi() {
        ScrollView sv = new ScrollView(this);
        sv.setFillViewport(true);
        root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setPadding(10, 10, 10, 24);
        root.setBackgroundColor(Color.WHITE);
        sv.addView(root);

        TextView title = tv("BANNER + REYKA KALKULYATOR — 2 XIL YECHIM", 19, true);
        title.setTextColor(Color.WHITE);
        title.setGravity(Gravity.CENTER);
        title.setBackgroundColor(blue);
        root.addView(title, new LinearLayout.LayoutParams(-1, 58));

        section("KIRITILADIGAN MA'LUMOT      QIYMAT");

        width = input("3.10");
        height = input("3.80");
        areaPrice = input("25000");
        hRail = input("0");
        vRail = input("0");
        rail34Price = input("10000");
        rail32Price = input("8000");
        installPrice = input("15000");

        root.addView(row("Banner eni (m)", width));
        root.addView(row("Banner bo'yi (m)", height));
        root.addView(row("Banner narxi (1 m²)", areaPrice));
        root.addView(row("↔ Ichki gorizontal reyka soni", hRail));
        root.addView(row("↕ Ichki vertikal reyka soni", vRail));
        root.addView(row("3×4 reyka narxi (1 metr)", rail34Price));
        root.addView(row("3×2 reyka narxi (1 metr)", rail32Price));

        section("UMUMIY HISOB                         NATIJA");
        areaOut = result("Banner maydoni (m²)", true);
        railMetersOut = result("Reyka jami metri", false);

        TextView heads = tv("                    3×4 REYKA                         3×2 REYKA", 14, true);
        heads.setGravity(Gravity.CENTER);
        heads.setBackgroundColor(lightBlue);
        root.addView(heads, new LinearLayout.LayoutParams(-1, 45));

        LinearLayout dual1 = dualRow("Reyka jami narxi");
        rail34Out = (TextView) dual1.getTag();
        rail32Out = (TextView) dual1.getChildAt(2);
        LinearLayout dual2 = dualRow("Banner jami narxi");
        bannerOut = (TextView) dual2.getTag();
        LinearLayout dual3 = dualRow("UMUMIY JAMI");
        total34Out = (TextView) dual3.getTag();
        total32Out = (TextView) dual3.getChildAt(2);

        section("MONTAJ");
        root.addView(row("G'ijduvon shahri ichida montaj (so'm/m²)", installPrice));
        installOut = result("Montaj jami", true);

        TextView note = tv("Hisob avtomatik yangilanadi. Reyka metri = perimetr + ichki gorizontal reyka × eni + ichki vertikal reyka × bo'yi.", 13, false);
        note.setTextColor(Color.DKGRAY);
        note.setPadding(12, 14, 12, 14);
        root.addView(note);

        setContentView(sv);
    }

    LinearLayout dualRow(String label) {
        LinearLayout r = new LinearLayout(this);
        r.setOrientation(LinearLayout.HORIZONTAL);
        r.setPadding(8,2,8,2);
        TextView l = tv(label, 15, true);
        TextView a = tv("", 15, true);
        TextView b = tv("", 15, true);
        a.setGravity(Gravity.CENTER);
        b.setGravity(Gravity.CENTER);
        r.addView(l, new LinearLayout.LayoutParams(0, 54, 1.2f));
        r.addView(a, new LinearLayout.LayoutParams(0, 54, .65f));
        r.addView(b, new LinearLayout.LayoutParams(0, 54, .65f));
        r.setTag(a);
        root.addView(r);
        return r;
    }

    double val(EditText e) {
        try { return Double.parseDouble(e.getText().toString().replace(",", ".")); }
        catch(Exception x) { return 0; }
    }

    String money(double n) {
        return String.format(Locale.US, "%,.0f so'm", n).replace(",", " ");
    }

    String num(double n) {
        return String.format(Locale.US, "%,.2f", n).replace(",", " ");
    }

    void calculate() {
        if (width == null) return;
        double w=val(width), h=val(height), bp=val(areaPrice);
        double hc=val(hRail), vc=val(vRail);
        double p34=val(rail34Price), p32=val(rail32Price), ip=val(installPrice);

        double area = w*h;
        double railMeters = 2*w + 2*h + hc*w + vc*h;
        double r34 = railMeters*p34;
        double r32 = railMeters*p32;
        double banner = area*bp;
        double install = area*ip;
        double t34 = banner+r34+install;
        double t32 = banner+r32+install;

        areaOut.setText(num(area) + " m²");
        railMetersOut.setText(num(railMeters) + " m");
        rail34Out.setText(money(r34));
        rail32Out.setText(money(r32));
        bannerOut.setText(money(banner));
        // Banner output is shared visually; put the same banner price into both columns.
        ((TextView)((View)bannerOut).getParent()).getChildAt(2).setText(money(banner));
        total34Out.setText(money(t34));
        total32Out.setText(money(t32));
        installOut.setText(money(install));
    }
}