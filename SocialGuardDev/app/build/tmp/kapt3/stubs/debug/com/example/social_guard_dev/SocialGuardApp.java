package com.example.social_guard_dev;

@dagger.hilt.android.HiltAndroidApp()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002R\u001b\u0010\u0003\u001a\u00020\u00048FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\t"}, d2 = {"Lcom/example/social_guard_dev/SocialGuardApp;", "Landroid/app/Application;", "()V", "usageStatsRepository", "Lcom/example/social_guard_dev/data/UsageStatsRepository;", "getUsageStatsRepository", "()Lcom/example/social_guard_dev/data/UsageStatsRepository;", "usageStatsRepository$delegate", "Lkotlin/Lazy;", "app_debug"})
public final class SocialGuardApp extends android.app.Application {
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy usageStatsRepository$delegate = null;
    
    public SocialGuardApp() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.social_guard_dev.data.UsageStatsRepository getUsageStatsRepository() {
        return null;
    }
}