package com.symbiose;


import com.symbiose.GestionUsers.gui.LoginForm;
import static com.codename1.ui.CN.*;
import com.codename1.io.Log;
import com.codename1.ui.Form;
import com.codename1.ui.Dialog;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.Toolbar;

/**
 * This file was generated by <a href="https://www.codenameone.com/">Codename
 * One</a> for the purpose of building native mobile applications using Java.
 */
public class Symbiose {

    private Form current;
    private Resources theme;

    public void init(Object context) {
        // use two network threads instead of one
        updateNetworkThreadCount(2);

        theme = UIManager.initFirstTheme("/theme");

        // Enable Toolbar on all Forms by default
        Toolbar.setGlobalToolbar(true);

        // Pro only feature
        Log.bindCrashProtection(true);

        addNetworkErrorListener(err -> {
            // prevent the event from propagating
            err.consume();
            if (err.getError() != null) {
                Log.e(err.getError());
            }
            Log.sendLogAsync();
            Dialog.show("Connection Error", "There was a networking error in the connection to " + err.getConnectionRequest().getUrl(), "OK", null);
        });
    }

    public void start() {
        
        if (current != null) {
            current.show();
//        LocalNotification n = new LocalNotification();
//        n.setId("demo-notification");
//        n.setAlertBody("It's time to take a break and look at me");
//        n.setAlertTitle("Break Time!");
//        n.setAlertSound("/notification_sound_beep-01a.mp3");
//        // alert sound file name must begin with notification_sound
//
//        Display.getInstance().scheduleLocalNotification(
//                n,
//                System.currentTimeMillis() + 5 * 1000, // fire date/time
//                LocalNotification.REPEAT_MINUTE // Whether to repeat and what frequency
//        );
            return;
        }
        new LoginForm(theme).show();
    }

    public void stop() {

    }

    public void destroy() {
    }

}
