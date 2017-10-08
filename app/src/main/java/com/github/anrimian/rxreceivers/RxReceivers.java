package com.github.anrimian.rxreceivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;

/**
 * Created on 08.10.2017.
 */

public class RxReceivers {

    public static Observable<Intent> create(@NonNull final String action, @NonNull final Context ctx) {
        IntentFilter filter = new IntentFilter(action);
        return create(filter, ctx);
    }

    public static Observable<Intent> create(@NonNull final IntentFilter intentFilter, @NonNull final Context ctx) {
        return Observable.create(new ObservableOnSubscribe<Intent>() {
            Context appContext = ctx.getApplicationContext();
            @Override
            public void subscribe(@NonNull final ObservableEmitter<Intent> emitter) throws Exception {
                BroadcastReceiver receiver = new BroadcastReceiver() {
                    @Override
                    public void onReceive(Context context, Intent intent) {
                        emitter.onNext(intent);
                    }
                };
                emitter.setDisposable(new BroadcastDisposable(receiver, appContext));
                appContext.registerReceiver(receiver, intentFilter);
            }
        });
    }
}
