package com.hkm.ezwebview.webviewclients;

import android.app.Activity;
import android.media.MediaPlayer;
import android.support.v4.view.ViewCompat;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.hkm.ezwebview.videoenabledwebview.VideoEnabledWebView;
import com.lsjwzh.widget.materialloadingprogressbar.CircleProgressBar;

/**
 * Created by hayton on 27/1/2017.
 */

public class VideoEnabledWebChromeClient extends WebChromeClient
        implements MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener {

    protected OnCloseWindowCallback onCloseWindowCallback = null;

    public interface OnCloseWindowCallback {
        public void onCloseWindow(WebView window);
    }

    public interface ToggledFullscreenCallback
    {
        public void toggledFullscreen(boolean fullscreen);
    }

    private View webViewHolder;
    private ViewGroup fullScreenView;
    private View loadingView;
    private VideoEnabledWebView webView;

    private boolean isVideoFullscreen; // Indicates if the video is being displayed using a custom view (typically full-screen)
    private FrameLayout videoViewContainer;
    private CustomViewCallback videoViewCallback;

    private ToggledFullscreenCallback toggledFullscreenCallback;

    private CircleProgressBar mCircleProgressBar;
    private boolean control_webview_show_hide_onload = false;
    private int time_of_fade = 0;
    private Activity mActivity;
    private ProgressBar cpb;
    private boolean withLoadingText = false;
    private String loadingText;
    private CharSequence barTitle;

    /**
     * Never use this constructor alone.
     * This constructor allows this class to be defined as an inline inner class in which the user can override methods
     */
    @SuppressWarnings("unused")
    public VideoEnabledWebChromeClient()
    {
    }

    /**
     * Builds a video enabled WebChromeClient.
     * @param webViewHolder A View in the activity's layout that contains every other view that should be hidden when the video goes full-screen.
     * @param fullScreenView A ViewGroup in the activity's layout that will display the video. Typically you would like this to fill the whole layout.
     */
    @SuppressWarnings("unused")
    public VideoEnabledWebChromeClient(View webViewHolder, ViewGroup fullScreenView)
    {
        this.webViewHolder = webViewHolder;
        this.fullScreenView = fullScreenView;
        this.mCircleProgressBar = null;
//        this.webView = null;
        this.isVideoFullscreen = false;
    }

    /**
     * Builds a video enabled WebChromeClient.
     * @param webViewHolder A View in the activity's layout that contains every other view that should be hidden when the video goes full-screen.
     * @param fullScreenView A ViewGroup in the activity's layout that will display the video. Typically you would like this to fill the whole layout.
     * @param loadingView A View to be shown while the video is loading (typically only used in API level <11). Must be already inflated and not attached to a parent view.
     */
    @SuppressWarnings("unused")
    public VideoEnabledWebChromeClient(View webViewHolder, ViewGroup fullScreenView, CircleProgressBar mCircleProgressBar)
    {
        this.webViewHolder = webViewHolder;
        this.fullScreenView = fullScreenView;
        this.mCircleProgressBar = mCircleProgressBar;
        this.webView = null;
        this.isVideoFullscreen = false;
    }

    /**
     * Builds a video enabled WebChromeClient.
     * @param webViewHolder A View in the activity's layout that contains every other view that should be hidden when the video goes full-screen.
     * @param fullScreenView A ViewGroup in the activity's layout that will display the video. Typically you would like this to fill the whole layout.
     * @param loadingView A View to be shown while the video is loading (typically only used in API level <11). Must be already inflated and not attached to a parent view.
     * @param webView The owner VideoEnabledWebView. Passing it will enable the VideoEnabledWebChromeClient to detect the HTML5 video ended event and exit full-screen.
     * Note: The web page must only contain one video tag in order for the HTML5 video ended event to work. This could be improved if needed (see Javascript code).
     */
    @SuppressWarnings("unused")
    public VideoEnabledWebChromeClient(View webViewHolder, ViewGroup fullScreenView, CircleProgressBar mCircleProgressBar, VideoEnabledWebView webView)
    {
        this.webViewHolder = webViewHolder;
        this.fullScreenView = fullScreenView;
        this.mCircleProgressBar = mCircleProgressBar;
        this.webView = webView;
        this.isVideoFullscreen = false;
    }

    public VideoEnabledWebChromeClient(View webViewHolder, ViewGroup fullScreenView, VideoEnabledWebView webView)
    {
        this.webViewHolder = webViewHolder;
        this.fullScreenView = fullScreenView;
        this.mCircleProgressBar = null;
        this.webView = webView;
        this.isVideoFullscreen = false;
    }

    /**
     * Indicates if the video is being displayed using a custom view (typically full-screen)
     * @return true it the video is being displayed using a custom view (typically full-screen)
     */
    public boolean isVideoFullscreen()
    {
        return isVideoFullscreen;
    }

    /**
     * Set a callback that will be fired when the video starts or finishes displaying using a custom view (typically full-screen)
     * @param callback A VideoEnabledWebChromeClient.ToggledFullscreenCallback callback
     */
    @SuppressWarnings("unused")
    public void setOnToggledFullscreen(ToggledFullscreenCallback callback)
    {
        this.toggledFullscreenCallback = callback;
    }

    @Override
    public void onShowCustomView(View view, CustomViewCallback callback)
    {
        if (view instanceof FrameLayout) {
            // A video wants to be shown
            FrameLayout frameLayout = (FrameLayout) view;
            View focusedChild = frameLayout.getFocusedChild();

            // Save video related variables
            this.isVideoFullscreen = true;
            this.videoViewContainer = frameLayout;
            this.videoViewCallback = callback;

            // Hide the non-video view, add the video view, and show it
            webViewHolder.setVisibility(View.INVISIBLE);
            fullScreenView.addView(videoViewContainer, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            fullScreenView.setVisibility(View.VISIBLE);

            if (focusedChild instanceof android.widget.VideoView)
            {
                // android.widget.VideoView (typically API level <11)
                android.widget.VideoView videoView = (android.widget.VideoView) focusedChild;

                // Handle all the required events
                videoView.setOnPreparedListener(this);
                videoView.setOnCompletionListener(this);
                videoView.setOnErrorListener(this);
            }
            else
            {
                // Other classes, including:
                // - android.webkit.HTML5VideoFullScreen$VideoSurfaceView, which inherits from android.view.SurfaceView (typically API level 11-18)
                // - android.webkit.HTML5VideoFullScreen$VideoTextureView, which inherits from android.view.TextureView (typically API level 11-18)
                // - com.android.org.chromium.content.browser.ContentVideoView$VideoSurfaceView, which inherits from android.view.SurfaceView (typically API level 19+)

                // Handle HTML5 video ended event only if the class is a SurfaceView
                // Test case: TextureView of Sony Xperia T API level 16 doesn't work fullscreen when loading the javascript below
                if (webView != null && webView.getSettings().getJavaScriptEnabled() && focusedChild instanceof SurfaceView)
                {
                    // Run javascript code that detects the video end and notifies the Javascript interface
                    String js = "javascript:";
                    js += "var _ytrp_html5_video_last;";
                    js += "var _ytrp_html5_video = document.getElementsByTagName('video')[0];";
                    js += "if (_ytrp_html5_video != undefined && _ytrp_html5_video != _ytrp_html5_video_last) {";
                    {
                        js += "_ytrp_html5_video_last = _ytrp_html5_video;";
                        js += "function _ytrp_html5_video_ended() {";
                        {
                            js += "_VideoEnabledWebView.notifyVideoEnd();"; // Must match Javascript interface name and method of VideoEnableWebView
                        }
                        js += "}";
                        js += "_ytrp_html5_video.addEventListener('ended', _ytrp_html5_video_ended);";
                    }
                    js += "}";
                    webView.loadUrl(js);
                }
            }

            // Notify full-screen change
            if (toggledFullscreenCallback != null)
            {
                toggledFullscreenCallback.toggledFullscreen(true);
            }
        }
    }

    @Override @SuppressWarnings("deprecation")
    public void onShowCustomView(View view, int requestedOrientation, CustomViewCallback callback) // Available in API level 14+, deprecated in API level 18+
    {
        onShowCustomView(view, callback);
    }

    @Override
    public void onHideCustomView()
    {
        // This method should be manually called on video end in all cases because it's not always called automatically.
        // This method must be manually called on back key press (from this class' onBackPressed() method).

        if (isVideoFullscreen)
        {
            // Hide the video view, remove it, and show the non-video view
            fullScreenView.setVisibility(View.INVISIBLE);
            fullScreenView.removeView(videoViewContainer);
            webViewHolder.setVisibility(View.VISIBLE);

            // Call back (only in API level <19, because in API level 19+ with chromium webview it crashes)
            if (videoViewCallback != null && !videoViewCallback.getClass().getName().contains(".chromium."))
            {
                videoViewCallback.onCustomViewHidden();
            }

            // Reset video related variables
            isVideoFullscreen = false;
            videoViewContainer = null;
            videoViewCallback = null;

            // Notify full-screen change
            if (toggledFullscreenCallback != null)
            {
                toggledFullscreenCallback.toggledFullscreen(false);
            }
        }
    }

    @Override
    public View getVideoLoadingProgressView() // Video will start loading
    {
        if (loadingView != null)
        {
            loadingView.setVisibility(View.VISIBLE);
            return loadingView;
        }
        else
        {
            return super.getVideoLoadingProgressView();
        }
    }

    @Override
    public void onPrepared(MediaPlayer mp) // Video will start playing, only called in the case of android.widget.VideoView (typically API level <11)
    {
        if (loadingView != null)
        {
            loadingView.setVisibility(View.GONE);
        }
    }

    @Override
    public void onCompletion(MediaPlayer mp) // Video finished playing, only called in the case of android.widget.VideoView (typically API level <11)
    {
        onHideCustomView();
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) // Error while playing video, only called in the case of android.widget.VideoView (typically API level <11)
    {
        return false; // By returning false, onCompletion() will be called
    }

    public VideoEnabledWebChromeClient setOnCloseWindowCallback(OnCloseWindowCallback onCloseWindowCallback) {
        this.onCloseWindowCallback = onCloseWindowCallback;
        return this;
    }

    @Override
    public void onCloseWindow(WebView window) {
        super.onCloseWindow(window);
        if (onCloseWindowCallback != null) {
            onCloseWindowCallback.onCloseWindow(window);
        }
    }

    @Override
    public void onProgressChanged(WebView view, int progress) {
        if (mCircleProgressBar != null) {
            if (progress < 100) {
                mCircleProgressBar.setProgress(progress);
                if (mCircleProgressBar.getVisibility() == View.GONE) {
                    mCircleProgressBar.setVisibility(View.VISIBLE);
                }
                if (control_webview_show_hide_onload && view.getVisibility() == View.VISIBLE)
                    view.setVisibility(View.GONE);
            } else {
                if (time_of_fade > 0) {
                    ViewCompat.animate(mCircleProgressBar).alpha(0f).withEndAction(new Runnable() {
                        @Override
                        public void run() {
                            mCircleProgressBar.setVisibility(View.GONE);
                        }
                    });
                } else {
                    mCircleProgressBar.setVisibility(View.GONE);
                }

                if (control_webview_show_hide_onload && view.getVisibility() == View.GONE)
                    view.setVisibility(View.VISIBLE);
            }

        } else if (mActivity != null) {
            //mActivity.setProgressBarIndeterminateVisibility(true);
            if (cpb != null) {
                cpb.setVisibility(View.VISIBLE);
            }
            if (withLoadingText) {
                mActivity.setTitle(loadingText);
            }
            if (cpb != null) {
                cpb.setProgress(progress);
            }
            if (control_webview_show_hide_onload && view.getVisibility() == View.VISIBLE)
                view.setVisibility(View.GONE);
            if (progress == 100) {
                if (withLoadingText) {
                    if (barTitle == null)
                        mActivity.setTitle("no name");
                    else mActivity.setTitle(barTitle);
                }
                if (cpb != null) {
                    cpb.setVisibility(View.GONE);
                }
                if (control_webview_show_hide_onload && view.getVisibility() == View.GONE)
                    view.setVisibility(View.VISIBLE);
            }
        }

    }

    /**
     * Notifies the class that the back key has been pressed by the user.
     * This must be called from the Activity's onBackPressed(), and if it returns false, the activity itself should handle it. Otherwise don't do anything.
     * @return Returns true if the event was handled, and false if was not (video view is not visible)
     */
    @SuppressWarnings("unused")
    public boolean onBackPressed()
    {
        if (isVideoFullscreen)
        {
            onHideCustomView();
            return true;
        }
        else
        {
            return false;
        }
    }

}