package com.example.social_guard_dev.ui.tasks;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&\u00a8\u0006\u0007"}, d2 = {"Lcom/example/social_guard_dev/ui/tasks/TaskItemClickListener;", "", "completeTaskItem", "", "itemBox", "Lcom/example/social_guard_dev/ui/tasks/ItemBox;", "editTaskItem", "app_debug"})
public abstract interface TaskItemClickListener {
    
    public abstract void editTaskItem(@org.jetbrains.annotations.NotNull()
    com.example.social_guard_dev.ui.tasks.ItemBox itemBox);
    
    public abstract void completeTaskItem(@org.jetbrains.annotations.NotNull()
    com.example.social_guard_dev.ui.tasks.ItemBox itemBox);
}