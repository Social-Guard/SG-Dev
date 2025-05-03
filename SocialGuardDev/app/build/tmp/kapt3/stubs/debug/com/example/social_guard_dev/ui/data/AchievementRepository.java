package com.example.social_guard_dev.ui.data;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tJ\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\f\u001a\u00020\rJ\u0006\u0010\u000e\u001a\u00020\u000fJ\u0016\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u000fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0015"}, d2 = {"Lcom/example/social_guard_dev/ui/data/AchievementRepository;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "prefs", "Landroid/content/SharedPreferences;", "kotlin.jvm.PlatformType", "getAchievements", "", "Lcom/example/social_guard_dev/ui/data/Achievement;", "getAchievementsByCategory", "category", "Lcom/example/social_guard_dev/ui/data/Achievement$Category;", "getTotalPoints", "", "updateAchievementProgress", "", "id", "", "progress", "app_debug"})
public final class AchievementRepository {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    private final android.content.SharedPreferences prefs = null;
    
    public AchievementRepository(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.example.social_guard_dev.ui.data.Achievement> getAchievements() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.example.social_guard_dev.ui.data.Achievement> getAchievementsByCategory(@org.jetbrains.annotations.NotNull()
    com.example.social_guard_dev.ui.data.Achievement.Category category) {
        return null;
    }
    
    public final void updateAchievementProgress(@org.jetbrains.annotations.NotNull()
    java.lang.String id, int progress) {
    }
    
    public final int getTotalPoints() {
        return 0;
    }
}