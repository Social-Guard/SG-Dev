package com.example.social_guard_dev.ui.tasks;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0006J\u000e\u0010\u000e\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u0006J(\u0010\u0010\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00142\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017R(\u0010\u0003\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00050\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n\u00a8\u0006\u0018"}, d2 = {"Lcom/example/social_guard_dev/ui/tasks/TaskViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "itemBoxes", "Landroidx/lifecycle/MutableLiveData;", "", "Lcom/example/social_guard_dev/ui/tasks/ItemBox;", "getItemBoxes", "()Landroidx/lifecycle/MutableLiveData;", "setItemBoxes", "(Landroidx/lifecycle/MutableLiveData;)V", "addTaskItem", "", "newTask", "setCompleted", "taskItem", "updateTaskItem", "id", "Ljava/util/UUID;", "name", "", "desc", "dueTime", "Ljava/time/LocalTime;", "app_debug"})
public final class TaskViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private androidx.lifecycle.MutableLiveData<java.util.List<com.example.social_guard_dev.ui.tasks.ItemBox>> itemBoxes;
    
    public TaskViewModel() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<java.util.List<com.example.social_guard_dev.ui.tasks.ItemBox>> getItemBoxes() {
        return null;
    }
    
    public final void setItemBoxes(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.MutableLiveData<java.util.List<com.example.social_guard_dev.ui.tasks.ItemBox>> p0) {
    }
    
    public final void addTaskItem(@org.jetbrains.annotations.NotNull()
    com.example.social_guard_dev.ui.tasks.ItemBox newTask) {
    }
    
    public final void updateTaskItem(@org.jetbrains.annotations.NotNull()
    java.util.UUID id, @org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    java.lang.String desc, @org.jetbrains.annotations.Nullable()
    java.time.LocalTime dueTime) {
    }
    
    public final void setCompleted(@org.jetbrains.annotations.NotNull()
    com.example.social_guard_dev.ui.tasks.ItemBox taskItem) {
    }
}