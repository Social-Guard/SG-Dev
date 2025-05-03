package com.example.social_guard_dev.viewmodels;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010 \n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u001f\u001a\u00020\b2\u0006\u0010 \u001a\u00020\bJ\u0006\u0010!\u001a\u00020\"J\u0006\u0010#\u001a\u00020\"R&\u0010\u0005\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u000b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\f\u001a\u0010\u0012\f\u0012\n \u000e*\u0004\u0018\u00010\r0\r0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\t0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R \u0010\u0011\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\n0\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R)\u0010\u0012\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\u00070\u0013\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0019\u0010\u0016\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0013\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0015R\u0017\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\r0\u0013\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0015R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u001b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\t0\u0013\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0015R#\u0010\u001d\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\n0\u00070\u0013\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0015\u00a8\u0006$"}, d2 = {"Lcom/example/social_guard_dev/viewmodels/UsageViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "_dailyBreakdown", "Landroidx/lifecycle/MutableLiveData;", "", "", "", "", "_error", "_isLoading", "", "kotlin.jvm.PlatformType", "_todayUsage", "Landroid/app/usage/UsageStats;", "_weeklyUsage", "dailyBreakdown", "Landroidx/lifecycle/LiveData;", "getDailyBreakdown", "()Landroidx/lifecycle/LiveData;", "error", "getError", "isLoading", "repository", "Lcom/example/social_guard_dev/data/UsageStatsRepository;", "todayUsage", "getTodayUsage", "weeklyUsage", "getWeeklyUsage", "getAppName", "packageName", "refreshData", "", "startPeriodicUsageCheck", "app_debug"})
public final class UsageViewModel extends androidx.lifecycle.AndroidViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.example.social_guard_dev.data.UsageStatsRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.Boolean> _isLoading = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Boolean> isLoading = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.String> _error = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.String> error = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.util.List<android.app.usage.UsageStats>> _todayUsage = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<android.app.usage.UsageStats>> todayUsage = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.util.Map<java.lang.String, java.lang.Long>> _weeklyUsage = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.Map<java.lang.String, java.lang.Long>> weeklyUsage = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.util.Map<java.lang.String, java.util.List<java.lang.Long>>> _dailyBreakdown = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.Map<java.lang.String, java.util.List<java.lang.Long>>> dailyBreakdown = null;
    
    public UsageViewModel(@org.jetbrains.annotations.NotNull()
    android.app.Application application) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Boolean> isLoading() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.String> getError() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<android.app.usage.UsageStats>> getTodayUsage() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.Map<java.lang.String, java.lang.Long>> getWeeklyUsage() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.Map<java.lang.String, java.util.List<java.lang.Long>>> getDailyBreakdown() {
        return null;
    }
    
    public final void refreshData() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getAppName(@org.jetbrains.annotations.NotNull()
    java.lang.String packageName) {
        return null;
    }
    
    public final void startPeriodicUsageCheck() {
    }
}