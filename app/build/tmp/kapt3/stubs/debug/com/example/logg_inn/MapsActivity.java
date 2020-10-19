package com.example.logg_inn;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u00012\u00020\u0002:\u0002\u001f B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0018\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0015J\u0012\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0014J\u0010\u0010\u001d\u001a\u00020\u001a2\u0006\u0010\u001e\u001a\u00020\u0011H\u0016R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0010\u001a\u00020\u0011X\u0082.\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001a\u00020\u0005X\u0086D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0007\u00a8\u0006!"}, d2 = {"Lcom/example/logg_inn/MapsActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "Lcom/google/android/gms/maps/OnMapReadyCallback;", "()V", "curIndex", "", "getCurIndex", "()I", "setCurIndex", "(I)V", "list", "Lcom/example/logg_inn/MapsActivity$HomeFeed;", "getList", "()Lcom/example/logg_inn/MapsActivity$HomeFeed;", "setList", "(Lcom/example/logg_inn/MapsActivity$HomeFeed;)V", "mMap", "Lcom/google/android/gms/maps/GoogleMap;", "maxIndex", "getMaxIndex", "fetchLocalJson", "", "context", "Landroid/content/Context;", "fileName", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onMapReady", "googleMap", "Events", "HomeFeed", "app_debug"})
public final class MapsActivity extends androidx.appcompat.app.AppCompatActivity implements com.google.android.gms.maps.OnMapReadyCallback {
    private com.google.android.gms.maps.GoogleMap mMap;
    @org.jetbrains.annotations.NotNull()
    private com.example.logg_inn.MapsActivity.HomeFeed list;
    private final int maxIndex = 8;
    private int curIndex = 0;
    private java.util.HashMap _$_findViewCache;
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.logg_inn.MapsActivity.HomeFeed getList() {
        return null;
    }
    
    public final void setList(@org.jetbrains.annotations.NotNull()
    com.example.logg_inn.MapsActivity.HomeFeed p0) {
    }
    
    public final int getMaxIndex() {
        return 0;
    }
    
    public final int getCurIndex() {
        return 0;
    }
    
    public final void setCurIndex(int p0) {
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    public void onMapReady(@org.jetbrains.annotations.NotNull()
    com.google.android.gms.maps.GoogleMap googleMap) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String fetchLocalJson(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.lang.String fileName) {
        return null;
    }
    
    public MapsActivity() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\u0002\u0010\u0005R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u00a8\u0006\b"}, d2 = {"Lcom/example/logg_inn/MapsActivity$HomeFeed;", "", "events", "", "Lcom/example/logg_inn/MapsActivity$Events;", "(Ljava/util/List;)V", "getEvents", "()Ljava/util/List;", "app_debug"})
    public static final class HomeFeed {
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<com.example.logg_inn.MapsActivity.Events> events = null;
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<com.example.logg_inn.MapsActivity.Events> getEvents() {
            return null;
        }
        
        public HomeFeed(@org.jetbrains.annotations.NotNull()
        java.util.List<com.example.logg_inn.MapsActivity.Events> events) {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000f\u0018\u00002\u00020\u0001B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\u0006\u0012\u0006\u0010\n\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u000bR\u0011\u0010\u0007\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\t\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\n\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011R\u0011\u0010\b\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\r\u00a8\u0006\u0015"}, d2 = {"Lcom/example/logg_inn/MapsActivity$Events;", "", "lat", "", "lng", "info", "", "bilder", "txt", "genre", "link", "(DDLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getBilder", "()Ljava/lang/String;", "getGenre", "getInfo", "getLat", "()D", "getLink", "getLng", "getTxt", "app_debug"})
    public static final class Events {
        private final double lat = 0.0;
        private final double lng = 0.0;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String info = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String bilder = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String txt = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String genre = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String link = null;
        
        public final double getLat() {
            return 0.0;
        }
        
        public final double getLng() {
            return 0.0;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getInfo() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getBilder() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getTxt() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getGenre() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getLink() {
            return null;
        }
        
        public Events(double lat, double lng, @org.jetbrains.annotations.NotNull()
        java.lang.String info, @org.jetbrains.annotations.NotNull()
        java.lang.String bilder, @org.jetbrains.annotations.NotNull()
        java.lang.String txt, @org.jetbrains.annotations.NotNull()
        java.lang.String genre, @org.jetbrains.annotations.NotNull()
        java.lang.String link) {
            super();
        }
    }
}