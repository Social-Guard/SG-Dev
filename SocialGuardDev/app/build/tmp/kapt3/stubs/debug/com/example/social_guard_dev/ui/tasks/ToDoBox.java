package com.example.social_guard_dev.ui.tasks;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010\u0004J&\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0016J\u001a\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u000f2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0016J\b\u0010\u0019\u001a\u00020\u0017H\u0002J\b\u0010\u001a\u001a\u00020\u0017H\u0002J\b\u0010\u001b\u001a\u00020\u0017H\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\u0004R\u000e\u0010\f\u001a\u00020\rX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001c"}, d2 = {"Lcom/example/social_guard_dev/ui/tasks/ToDoBox;", "Lcom/google/android/material/bottomsheet/BottomSheetDialogFragment;", "itemBox", "Lcom/example/social_guard_dev/ui/tasks/ItemBox;", "(Lcom/example/social_guard_dev/ui/tasks/ItemBox;)V", "binding", "Lcom/example/social_guard_dev/databinding/FragmentToDoBoxBinding;", "dueTime", "Ljava/time/LocalTime;", "getItemBox", "()Lcom/example/social_guard_dev/ui/tasks/ItemBox;", "setItemBox", "taskViewModel", "Lcom/example/social_guard_dev/ui/tasks/TaskViewModel;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onViewCreated", "", "view", "openTimePicker", "saveAction", "updateTimeButtonText", "app_debug"})
public final class ToDoBox extends com.google.android.material.bottomsheet.BottomSheetDialogFragment {
    @org.jetbrains.annotations.Nullable()
    private com.example.social_guard_dev.ui.tasks.ItemBox itemBox;
    private com.example.social_guard_dev.databinding.FragmentToDoBoxBinding binding;
    private com.example.social_guard_dev.ui.tasks.TaskViewModel taskViewModel;
    @org.jetbrains.annotations.Nullable()
    private java.time.LocalTime dueTime;
    
    public ToDoBox(@org.jetbrains.annotations.Nullable()
    com.example.social_guard_dev.ui.tasks.ItemBox itemBox) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.example.social_guard_dev.ui.tasks.ItemBox getItemBox() {
        return null;
    }
    
    public final void setItemBox(@org.jetbrains.annotations.Nullable()
    com.example.social_guard_dev.ui.tasks.ItemBox p0) {
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void openTimePicker() {
    }
    
    private final void updateTimeButtonText() {
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull()
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable()
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    private final void saveAction() {
    }
}