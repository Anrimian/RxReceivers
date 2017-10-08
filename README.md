# RxReceivers
Simple RxJava2 wrapper for Android BroadcastReceiver

## How to use it:

```java
RxReceivers.from("YOUR ACTION HERE", context)
    .subscribe(intent -> {    
        //do what you want here, but don't forget to unsubscribe
});
```

## Dependencies:

https://github.com/ReactiveX/RxJava version 2.1.5
