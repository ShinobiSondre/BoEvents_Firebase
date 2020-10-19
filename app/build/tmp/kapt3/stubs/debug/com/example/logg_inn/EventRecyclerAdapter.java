package com.example.logg_inn;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u001eB\u0005\u00a2\u0006\u0002\u0010\u0003J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u000e0\rJ\u0018\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u0010H\u0016J\u0018\u0010\u0016\u001a\u00020\u00022\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0010H\u0016J\u0014\u0010\u001a\u001a\u00020\u00132\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u000e0\rJ\u0014\u0010\u001c\u001a\u00020\u00132\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u000e0\rR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0082.\u00a2\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001f"}, d2 = {"Lcom/example/logg_inn/EventRecyclerAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "()V", "closeDialog1", "Landroid/view/LayoutInflater;", "getCloseDialog1", "()Landroid/view/LayoutInflater;", "setCloseDialog1", "(Landroid/view/LayoutInflater;)V", "database", "Lcom/google/firebase/database/DatabaseReference;", "items", "", "Lcom/example/logg_inn/models/DataModel;", "getItemCount", "", "getList", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "submitList", "events", "updateList", "list", "EventsViewHodler", "app_debug"})
public final class EventRecyclerAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<androidx.recyclerview.widget.RecyclerView.ViewHolder> {
    private java.util.List<com.example.logg_inn.models.DataModel> items;
    private com.google.firebase.database.DatabaseReference database;
    @org.jetbrains.annotations.NotNull()
    public android.view.LayoutInflater closeDialog1;
    
    @org.jetbrains.annotations.NotNull()
    public final android.view.LayoutInflater getCloseDialog1() {
        return null;
    }
    
    public final void setCloseDialog1(@org.jetbrains.annotations.NotNull()
    android.view.LayoutInflater p0) {
    }
    
    public final void updateList(@org.jetbrains.annotations.NotNull()
    java.util.List<com.example.logg_inn.models.DataModel> list) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public androidx.recyclerview.widget.RecyclerView.ViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    androidx.recyclerview.widget.RecyclerView.ViewHolder holder, int position) {
    }
    
    @java.lang.Override()
    public int getItemCount() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.example.logg_inn.models.DataModel> getList() {
        return null;
    }
    
    public final void submitList(@org.jetbrains.annotations.NotNull()
    java.util.List<com.example.logg_inn.models.DataModel> events) {
    }
    
    public EventRecyclerAdapter() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013R\u0019\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0019\u0010\n\u001a\n \u0007*\u0004\u0018\u00010\u000b0\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0019\u0010\u000e\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\t\u00a8\u0006\u0014"}, d2 = {"Lcom/example/logg_inn/EventRecyclerAdapter$EventsViewHodler;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Landroid/view/View;)V", "eventAuthor", "Landroid/widget/TextView;", "kotlin.jvm.PlatformType", "getEventAuthor", "()Landroid/widget/TextView;", "eventImage", "Landroid/widget/ImageView;", "getEventImage", "()Landroid/widget/ImageView;", "eventTitle", "getEventTitle", "bind", "", "dataModel", "Lcom/example/logg_inn/models/DataModel;", "app_debug"})
    public static final class EventsViewHodler extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        private final android.widget.ImageView eventImage = null;
        private final android.widget.TextView eventTitle = null;
        private final android.widget.TextView eventAuthor = null;
        
        public final android.widget.ImageView getEventImage() {
            return null;
        }
        
        public final android.widget.TextView getEventTitle() {
            return null;
        }
        
        public final android.widget.TextView getEventAuthor() {
            return null;
        }
        
        public final void bind(@org.jetbrains.annotations.NotNull()
        com.example.logg_inn.models.DataModel dataModel) {
        }
        
        public EventsViewHodler(@org.jetbrains.annotations.NotNull()
        android.view.View itemView) {
            super(null);
        }
    }
}