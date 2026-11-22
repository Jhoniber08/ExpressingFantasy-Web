package com.jhoniber.expressingfantasy.web;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;

import com.getcapacitor.BridgeActivity;

/**
 * Expressing Fantasy — por Jhoniber De la Cruz y Justin Manuel Nolasco «Mano».
 * Contenedor Android del juego web v11.1: pantalla completa inmersiva,
 * horizontal, pantalla siempre encendida y aceleración por hardware.
 */
public class MainActivity extends BridgeActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
        WebView wv = getBridge() != null ? getBridge().getWebView() : null;
        if (wv != null) {
            wv.setLayerType(View.LAYER_TYPE_HARDWARE, null);
            WebSettings ws = wv.getSettings();
            ws.setMediaPlaybackRequiresUserGesture(false);
            ws.setDomStorageEnabled(true);
            ws.setDatabaseEnabled(true);
            ws.setTextZoom(100);
        }
        hideSystemBars();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) hideSystemBars();
    }

    @Override
    public void onResume() {
        super.onResume();
        hideSystemBars();
        notifyGame(true);
    }

    @Override
    public void onPause() {
        notifyGame(false);
        super.onPause();
    }

    /** Avisa al juego (android-bridge.js) para pausar/reanudar el audio al salir/volver. */
    private void notifyGame(boolean visible) {
        try {
            WebView wv = getBridge() != null ? getBridge().getWebView() : null;
            if (wv != null) wv.evaluateJavascript("window.__efAppVis&&window.__efAppVis(" + visible + ")", null);
        } catch (Exception ignored) { }
    }

    /** Modo inmersivo: oculta barra de estado y navegación (deslizar para verlas un momento). */
    private void hideSystemBars() {
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        WindowInsetsControllerCompat c = WindowCompat.getInsetsController(getWindow(), getWindow().getDecorView());
        c.setSystemBarsBehavior(WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE);
        c.hide(WindowInsetsCompat.Type.systemBars());
    }
}
