/* AUTO-GENERATED FILE.  DO NOT MODIFY.
 *
 * This class was automatically generated by the
 * aapt tool from the resource data it found.  It
 * should not be modified by hand.
 */

package com.example.ntropytest;

public final class R {
    public static final class anim {
        public static final int fadein=0x7f040000;
        public static final int fadeout=0x7f040001;
    }
    public static final class attr {
        /** <p>Must be a reference to another resource, in the form "<code>@[+][<i>package</i>:]<i>type</i>:<i>name</i></code>"
or to a theme attribute in the form "<code>?[<i>package</i>:][<i>type</i>:]<i>name</i></code>".
         */
        public static final int buttonBarButtonStyle=0x7f010001;
        /** <p>Must be a reference to another resource, in the form "<code>@[+][<i>package</i>:]<i>type</i>:<i>name</i></code>"
or to a theme attribute in the form "<code>?[<i>package</i>:][<i>type</i>:]<i>name</i></code>".
         */
        public static final int buttonBarStyle=0x7f010000;
    }
    public static final class color {
        public static final int black=0x7f050001;
        public static final int black_overlay=0x7f050000;
        public static final int white=0x7f050002;
    }
    public static final class drawable {
        public static final int background=0x7f020000;
        public static final int endturn=0x7f020001;
        public static final int ic_launcher=0x7f020002;
        public static final int logo=0x7f020003;
        public static final int logo_full_size=0x7f020004;
        public static final int logo_neon=0x7f020005;
        public static final int one=0x7f020006;
        public static final int zero=0x7f020007;
    }
    public static final class id {
        public static final int fullscreen_content=0x7f080000;
        public static final int fullscreen_content_controls=0x7f080001;
        public static final int log_out=0x7f080003;
        public static final int matchmaking=0x7f080002;
        public static final int no_match_text=0x7f08000c;
        public static final int password_confirm_text=0x7f080008;
        public static final int password_failure_text=0x7f08000b;
        public static final int password_text=0x7f080007;
        public static final int sign_in_toggle=0x7f080005;
        public static final int submit_button=0x7f080009;
        public static final int success_text=0x7f08000d;
        public static final int textView1=0x7f080004;
        public static final int username_taken_text=0x7f08000a;
        public static final int username_text=0x7f080006;
    }
    public static final class layout {
        public static final int game_activity=0x7f030000;
        public static final int lobby_activity=0x7f030001;
        public static final int login_activity=0x7f030002;
        public static final int push_activity=0x7f030003;
    }
    public static final class string {
        public static final int app_name=0x7f060000;
        public static final int confirm_password=0x7f060005;
        public static final int dialog_submit_move=0x7f060014;
        public static final int dummy_button=0x7f06000e;
        public static final int dummy_content=0x7f06000f;
        public static final int log_out=0x7f060010;
        public static final int matchmaking=0x7f06000c;
        public static final int no=0x7f060013;
        public static final int no_match=0x7f06000a;
        public static final int password=0x7f060004;
        public static final int passwords_do_not_match=0x7f060008;
        public static final int sign_in=0x7f060002;
        public static final int sign_up=0x7f060001;
        public static final int submit=0x7f060006;
        public static final int success=0x7f06000b;
        public static final int title_activity_game=0x7f06000d;
        public static final int title_activity_lobby=0x7f060009;
        public static final int title_activity_push=0x7f060011;
        public static final int username=0x7f060003;
        public static final int username_taken=0x7f060007;
        public static final int yes=0x7f060012;
    }
    public static final class style {
        /** 
        Base application theme, dependent on API level. This theme is replaced
        by AppBaseTheme from res/values-vXX/styles.xml on newer devices.

    

            Theme customizations available in newer API levels can go in
            res/values-vXX/styles.xml, while customizations related to
            backward-compatibility can go here.

        

        Base application theme for API 11+. This theme completely replaces
        AppBaseTheme from res/values/styles.xml on API 11+ devices.

    
 API 11 theme customizations can go here. 

        Base application theme for API 14+. This theme completely replaces
        AppBaseTheme from BOTH res/values/styles.xml and
        res/values-v11/styles.xml on API 14+ devices.
    
 API 14 theme customizations can go here. 
         */
        public static final int AppBaseTheme=0x7f070000;
        /**  Application theme. 
 All customizations that are NOT specific to a particular API-level can go here. 
         */
        public static final int AppTheme=0x7f070001;
        public static final int ButtonBar=0x7f070003;
        public static final int ButtonBarButton=0x7f070004;
        public static final int FullscreenActionBarStyle=0x7f070005;
        public static final int FullscreenTheme=0x7f070002;
    }
    public static final class styleable {
        /** 
         Declare custom theme attributes that allow changing which styles are
         used for button bars depending on the API level.
         ?android:attr/buttonBarStyle is new as of API 11 so this is
         necessary to support previous API levels.
    
           <p>Includes the following attributes:</p>
           <table>
           <colgroup align="left" />
           <colgroup align="left" />
           <tr><th>Attribute</th><th>Description</th></tr>
           <tr><td><code>{@link #ButtonBarContainerTheme_buttonBarButtonStyle com.example.ntropytest:buttonBarButtonStyle}</code></td><td></td></tr>
           <tr><td><code>{@link #ButtonBarContainerTheme_buttonBarStyle com.example.ntropytest:buttonBarStyle}</code></td><td></td></tr>
           </table>
           @see #ButtonBarContainerTheme_buttonBarButtonStyle
           @see #ButtonBarContainerTheme_buttonBarStyle
         */
        public static final int[] ButtonBarContainerTheme = {
            0x7f010000, 0x7f010001
        };
        /**
          <p>This symbol is the offset where the {@link com.example.ntropytest.R.attr#buttonBarButtonStyle}
          attribute's value can be found in the {@link #ButtonBarContainerTheme} array.


          <p>Must be a reference to another resource, in the form "<code>@[+][<i>package</i>:]<i>type</i>:<i>name</i></code>"
or to a theme attribute in the form "<code>?[<i>package</i>:][<i>type</i>:]<i>name</i></code>".
          @attr name com.example.ntropytest:buttonBarButtonStyle
        */
        public static final int ButtonBarContainerTheme_buttonBarButtonStyle = 1;
        /**
          <p>This symbol is the offset where the {@link com.example.ntropytest.R.attr#buttonBarStyle}
          attribute's value can be found in the {@link #ButtonBarContainerTheme} array.


          <p>Must be a reference to another resource, in the form "<code>@[+][<i>package</i>:]<i>type</i>:<i>name</i></code>"
or to a theme attribute in the form "<code>?[<i>package</i>:][<i>type</i>:]<i>name</i></code>".
          @attr name com.example.ntropytest:buttonBarStyle
        */
        public static final int ButtonBarContainerTheme_buttonBarStyle = 0;
    };
}
