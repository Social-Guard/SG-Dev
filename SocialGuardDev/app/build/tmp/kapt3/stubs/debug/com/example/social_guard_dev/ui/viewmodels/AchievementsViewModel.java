package com.example.social_guard_dev.ui.viewmodels;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0006\u0010\u0017\u001a\u00020\u0018J\u0014\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u001a\u001a\u00020\u001bJ\u0006\u0010\u001c\u001a\u00020\u0018J\u0006\u0010\u001d\u001a\u00020\u0018J\u0006\u0010\u001e\u001a\u00020\u001fR\u001a\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u000b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\r\u001a\u0010\u0012\f\u0012\n \u000f*\u0004\u0018\u00010\u000e0\u000e0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0019\u0010\u0014\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0013R\u0017\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0013R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006 "}, d2 = {"Lcom/example/social_guard_dev/ui/viewmodels/AchievementsViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "application", "Landroid/app/Application;", "repository", "Lcom/example/social_guard_dev/ui/data/AchievementRepository;", "(Landroid/app/Application;Lcom/example/social_guard_dev/ui/data/AchievementRepository;)V", "_achievements", "Landroidx/lifecycle/MutableLiveData;", "", "Lcom/example/social_guard_dev/ui/data/Achievement;", "_error", "", "_isLoading", "", "kotlin.jvm.PlatformType", "achievements", "Landroidx/lifecycle/LiveData;", "getAchievements", "()Landroidx/lifecycle/LiveData;", "error", "getError", "isLoading", "getAchievedCount", "", "getAchievementsByCategory", "category", "Lcom/example/social_guard_dev/ui/data/Achievement$Category;", "getTotalCount", "getTotalPoints", "loadAchievements", "", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class AchievementsViewModel extends androidx.lifecycle.AndroidViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.example.social_guard_dev.ui.data.AchievementRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.util.List<com.example.social_guard_dev.ui.data.Achievement>> _achievements = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<com.example.social_guard_dev.ui.data.Achievement>> achievements = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.String> _error = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.String> error = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.Boolean> _isLoading = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Boolean> isLoading = null;
    
    @javax.inject.Inject()
    public AchievementsViewModel(@org.jetbrains.annotations.NotNull()
    android.app.Application application, @org.jetbrains.annotations.NotNull()
    com.example.social_guard_dev.ui.data.AchievementRepository repository) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.example.social_guard_dev.ui.data.Achievement>> getAchievements() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.String> getError() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Boolean> isLoading() {
        return null;
    }
    
    public final void loadAchievements() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.example.social_guard_dev.ui.data.Achievement> getAchievementsByCategory(@org.jetbrains.annotations.NotNull()
    com.example.social_guard_dev.ui.data.Achievement.Category category) {
        return null;
    }
    
    public final int getTotalPoints() {
        return 0;
    }
    
    public final int getAchievedCount() {
        return 0;
    }
    
    public final int getTotalCount() {
        return 0;
    }
}