package name.avioli.unilinks;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import java.util.HashMap;
import android.util.Log;
import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.embedding.engine.plugins.activity.ActivityAware;
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.EventChannel;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.PluginRegistry;

public class UniLinksPlugin
        implements FlutterPlugin,
                MethodChannel.MethodCallHandler,
                EventChannel.StreamHandler,
                ActivityAware,
                PluginRegistry.NewIntentListener {

    private static final String MESSAGES_CHANNEL = "uni_links/messages";
    private static final String EVENTS_CHANNEL = "uni_links/events";

    private BroadcastReceiver changeReceiver;

    private String initialLink;
    private String latestLink;
    private Context context;
    private boolean initialIntent = true;

    private void handleIntent(Context context, Intent intent) {
        String action = intent.getAction();
        String dataString = intent.getDataString();
        String aaa=intent.getExtras().getString("account");
        Log.i("sichangyongaaa", "handleIntenta: "+aaa);
        if (dataString == null) {
            HashMap<String, String> map = (HashMap<String, String>) intent.getSerializableExtra("map");
            Log.i("sichangyong", "handleIntent: "+map);
            if (map!=null){
                dataString = "holo://holoview-lab.com/?"
                + "&userName=" + map.get("userName")
                + "&account=" + map.get("account")
                + "&nonce=" +  map.get("nonce")
                + "&timeStamp=" + map.get("timeStamp")
                + "&signature=" +  map.get("signature")
                + "&workNumber=" +  map.get("workNumber")
                + "&dispatchTime=" +  map.get("dispatchTime")
                + "&workType=" +  map.get("workType")
                + "&faultType=" +  map.get("faultType")
                + "&faultLoc=" +  map.get("faultLoc")
                + "&faultDesc=" +  map.get("faultDesc")
                + "&contactName=" +  map.get("contactName")
                + "&contactMobile=" +  map.get("contactMobile")
                + "&workStatus=" +  map.get("workStatus")
                + "&extra=" + null;
            }else{
                dataString = "holo://holoview-lab.com/?"
                        + "&userName=" + intent.getExtras().getString("userName")
                        + "&account=" + intent.getExtras().getString("account")
                        + "&nonce=" +  intent.getExtras().getString("nonce")
                        + "&timeStamp=" + intent.getExtras().getString("timeStamp")
                        + "&signature=" +  intent.getExtras().getString("signature")
                        + "&workNumber=" +  intent.getExtras().getString("workNumber")
                        + "&dispatchTime=" +  intent.getExtras().getString("dispatchTime")
                        + "&workType=" +  intent.getExtras().getString("workType")
                        + "&faultType=" +  intent.getExtras().getString("faultType")
                        + "&faultLoc=" +  intent.getExtras().getString("faultLoc")
                        + "&faultDesc=" +  intent.getExtras().getString("faultDesc")
                        + "&contactName=" +  intent.getExtras().getString("contactName")
                        + "&contactMobile=" +  intent.getExtras().getString("contactMobile")
                        + "&workStatus=" +  intent.getExtras().getString("workStatus")
                        + "&extra=" + null;
                Log.i("sichangyongaaa", "handleIntenta: "+dataString);
            }
        }
        if (Intent.ACTION_VIEW.equals(action)) {

            if (initialIntent) {
                initialLink = dataString;
                initialIntent = false;
            }
            latestLink = dataString;
            if (changeReceiver != null) changeReceiver.onReceive(context, intent);
        }
    }

    private BroadcastReceiver createChangeReceiver(final EventChannel.EventSink events) {
        return new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                // NOTE: assuming intent.getAction() is Intent.ACTION_VIEW

                // Log.v("uni_links", String.format("received action: %s", intent.getAction()));

                String dataString = intent.getDataString();
                if (dataString == null) {
                    HashMap<String, String> map = (HashMap<String, String>) intent.getSerializableExtra("map");
                    Log.i("sichangyong", "handleIntent: "+map);
                    if (map!=null){
                        dataString = "holo://holoview-lab.com/?"
                                + "&userName=" + map.get("userName")
                                + "&account=" + map.get("account")
                                + "&nonce=" +  map.get("nonce")
                                + "&timeStamp=" + map.get("timeStamp")
                                + "&signature=" +  map.get("signature")
                                + "&workNumber=" +  map.get("workNumber")
                                + "&dispatchTime=" +  map.get("dispatchTime")
                                + "&workType=" +  map.get("workType")
                                + "&faultType=" +  map.get("faultType")
                                + "&faultLoc=" +  map.get("faultLoc")
                                + "&faultDesc=" +  map.get("faultDesc")
                                + "&contactName=" +  map.get("contactName")
                                + "&contactMobile=" +  map.get("contactMobile")
                                + "&workStatus=" +  map.get("workStatus")
                                + "&extra=" + null;

                    }else{
                        dataString = "holo://holoview-lab.com/?"
                                + "&userName=" + intent.getExtras().getString("userName")
                                + "&account=" + intent.getExtras().getString("account")
                                + "&nonce=" +  intent.getExtras().getString("nonce")
                                + "&timeStamp=" + intent.getExtras().getString("timeStamp")
                                + "&signature=" +  intent.getExtras().getString("signature")
                                + "&workNumber=" +  intent.getExtras().getString("workNumber")
                                + "&dispatchTime=" +  intent.getExtras().getString("dispatchTime")
                                + "&workType=" +  intent.getExtras().getString("workType")
                                + "&faultType=" +  intent.getExtras().getString("faultType")
                                + "&faultLoc=" +  intent.getExtras().getString("faultLoc")
                                + "&faultDesc=" +  intent.getExtras().getString("faultDesc")
                                + "&contactName=" +  intent.getExtras().getString("contactName")
                                + "&contactMobile=" +  intent.getExtras().getString("contactMobile")
                                + "&workStatus=" +  intent.getExtras().getString("workStatus")
                                + "&extra=" + null;
                        Log.i("sichangyongaaa", "handleIntenta: "+dataString);
                    }
                }
                if (dataString == null) {
                    events.error("UNAVAILABLE", "Link unavailable", null);
                } else {
                    events.success(dataString);
                }
            }
        };
    }

    @Override
    public void onAttachedToEngine(FlutterPluginBinding flutterPluginBinding) {
        this.context = flutterPluginBinding.getApplicationContext();
        register(flutterPluginBinding.getFlutterEngine().getDartExecutor(), this);
    }

    private static void register(BinaryMessenger messenger, UniLinksPlugin plugin) {
        final MethodChannel methodChannel = new MethodChannel(messenger, MESSAGES_CHANNEL);
        methodChannel.setMethodCallHandler(plugin);

        final EventChannel eventChannel = new EventChannel(messenger, EVENTS_CHANNEL);
        eventChannel.setStreamHandler(plugin);
    }

    /** Plugin registration. */
    public static void registerWith(PluginRegistry.Registrar registrar) {
        // Detect if we've been launched in background
        if (registrar.activity() == null) {
            return;
        }

        final UniLinksPlugin instance = new UniLinksPlugin();
        instance.context = registrar.context();
        register(registrar.messenger(), instance);

        instance.handleIntent(registrar.context(), registrar.activity().getIntent());
        registrar.addNewIntentListener(instance);
    }

    @Override
    public void onDetachedFromEngine(FlutterPluginBinding flutterPluginBinding) {}

    @Override
    public void onListen(Object o, EventChannel.EventSink eventSink) {
        changeReceiver = createChangeReceiver(eventSink);
    }

    @Override
    public void onCancel(Object o) {
        changeReceiver = null;
    }

    @Override
    public void onMethodCall(MethodCall call, MethodChannel.Result result) {
        if (call.method.equals("getInitialLink")) {
            result.success(initialLink);
        } else if (call.method.equals("getLatestLink")) {
            result.success(latestLink);
        } else {
            result.notImplemented();
        }
    }

    @Override
    public boolean onNewIntent(Intent intent) {
        this.handleIntent(context, intent);
        return false;
    }

    @Override
    public void onAttachedToActivity(ActivityPluginBinding activityPluginBinding) {
        activityPluginBinding.addOnNewIntentListener(this);
        this.handleIntent(this.context, activityPluginBinding.getActivity().getIntent());
    }

    @Override
    public void onDetachedFromActivityForConfigChanges() {}

    @Override
    public void onReattachedToActivityForConfigChanges(
            ActivityPluginBinding activityPluginBinding) {
        activityPluginBinding.addOnNewIntentListener(this);
        this.handleIntent(this.context, activityPluginBinding.getActivity().getIntent());
    }

    @Override
    public void onDetachedFromActivity() {}
}
