package com.rehanjamdar.cricket;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.net.Uri;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebResourceRequest;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Button;

public class MainActivity extends Activity {
  private WebView web;
  private final String site = "https://cricketpridiction.netlify.app/";
  private final String whatsapp = "https://wa.me/919130136952?text=Cricket%20ki%20bhavishyavani%20jaanni%20hai";
  @Override public void onCreate(Bundle b) {
    super.onCreate(b);
    LinearLayout box = new LinearLayout(this); box.setOrientation(LinearLayout.VERTICAL); box.setBackgroundColor(Color.rgb(9,16,27));
    TextView title = new TextView(this); title.setText("🏏 REHAN JAMDAR 👑"); title.setTextColor(Color.rgb(255,211,80)); title.setTextSize(23); title.setTypeface(null,Typeface.BOLD); title.setPadding(16,16,16,8); title.setGravity(17); box.addView(title);
    TextView note = new TextView(this); note.setText("Cricket ki bhavishyavani jaanne ke liye hamare WhatsApp par message karein!\nPredictions sirf anumaan hain, jeet ki guarantee nahi."); note.setTextColor(Color.WHITE); note.setTextSize(14); note.setGravity(17); note.setPadding(14,5,14,8); box.addView(note);
    Button button = new Button(this); button.setText("WHATSAPP PAR MESSAGE KAREIN"); button.setTextColor(Color.WHITE); button.setBackgroundTintList(android.content.res.ColorStateList.valueOf(Color.rgb(18,155,75))); button.setOnClickListener(v -> openExternal(whatsapp)); box.addView(button);
    web = new WebView(this); web.getSettings().setJavaScriptEnabled(true); web.getSettings().setDomStorageEnabled(true);
    web.setWebViewClient(new WebViewClient() {
      @Override public boolean shouldOverrideUrlLoading(WebView v, WebResourceRequest request) {
        Uri u=request.getUrl(); String host=u.getHost();
        if (host != null && (host.equals("cricketpridiction.netlify.app"))) return false;
        openExternal(u.toString()); return true;
      }
    });
    box.addView(web,new LinearLayout.LayoutParams(-1,0,1)); setContentView(box); web.loadUrl(site);
  }
  private void openExternal(String url) { try { startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse(url))); } catch(Exception ignored) {} }
  @Override public void onBackPressed() { if(web!=null && web.canGoBack())web.goBack(); else super.onBackPressed(); }
}
