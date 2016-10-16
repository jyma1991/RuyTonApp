package io.github.pwlin.cordova.plugins.fileopener2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;

/**
 * Created by gushenjie on 16/2/1.
 */
public class UpdateReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {

        PackageManager manager = context.getPackageManager();
      if (intent.getAction().equals(Intent.ACTION_PACKAGE_ADDED)) {
            String packageName = intent.getData().getSchemeSpecificPart();

        }
        if (intent.getAction().equals(Intent.ACTION_PACKAGE_REMOVED)) {
            String packageName = intent.getData().getSchemeSpecificPart();

        }
        if (intent.getAction().equals(Intent.ACTION_PACKAGE_REPLACED)) {
            String packageName = intent.getData().getSchemeSpecificPart();

            if (packageName.equalsIgnoreCase("com.ruyton.app")) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent startIntent = new Intent();
                startIntent = manager.getLaunchIntentForPackage(packageName);
                if (startIntent != null) {
                    startIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(startIntent);
                    return;
                }
                System.out.println("APP not found!");
            }
        }
    }
}
