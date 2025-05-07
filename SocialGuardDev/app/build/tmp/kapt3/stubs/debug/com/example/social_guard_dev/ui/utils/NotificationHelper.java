package com.example.social_guard_dev.ui.utils;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bJ\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\u000bH\u0002J\u0018\u0010\u000e\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u0004H\u0002J\u001c\u0010\u0010\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00040\u0012J.\u0010\u0013\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0018"}, d2 = {"Lcom/example/social_guard_dev/ui/utils/NotificationHelper;", "", "()V", "CHANNEL_DESC", "", "CHANNEL_ID", "CHANNEL_NAME", "GROUP_KEY", "createNotificationChannel", "", "context", "Landroid/content/Context;", "createSettingsPendingIntent", "Landroid/app/PendingIntent;", "createSnoozePendingIntent", "packageName", "showSummaryNotification", "exceededApps", "", "showTimeLimitNotification", "appName", "usageMillis", "", "limitMillis", "app_debug"})
public final class NotificationHelper {
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String CHANNEL_ID = "app_usage_alerts";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String CHANNEL_NAME = "App Usage Alerts";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String CHANNEL_DESC = "Notifications about app usage limits";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String GROUP_KEY = "com.example.social_guard_dev.APP_USAGE_GROUP";
    @org.jetbrains.annotations.NotNull()
    public static final com.example.social_guard_dev.ui.utils.NotificationHelper INSTANCE = null;
    
    private NotificationHelper() {
        super();
    }
    
    public final void createNotificationChannel(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
    
    public final void showTimeLimitNotification(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.lang.String appName, @org.jetbrains.annotations.NotNull()
    java.lang.String packageName, long usageMillis, long limitMillis) {
    }
    
    private final android.app.PendingIntent createSnoozePendingIntent(android.content.Context context, java.lang.String packageName) {
        return null;
    }
    
    private final android.app.PendingIntent createSettingsPendingIntent(android.content.Context context) {
        return null;
    }
    
    public final void showSummaryNotification(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> exceededApps) {
    }
}