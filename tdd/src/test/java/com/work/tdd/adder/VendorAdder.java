package com.work.tdd.adder;

import com.work.tdd.adder.vendor.AddCallBack;
import com.work.tdd.adder.vendor.Adder;

/**
 * A sample class from Vendor that would add ints and call result in the same thread or a new thread
 * depending on the boolean passed
 */
class VendorAdder implements Adder {

    private boolean sameThread;

    public VendorAdder(boolean sameThread) {
        this.sameThread = sameThread;
    }

    public void add(final int a, final int b, final AddCallBack callBack) {
        if (sameThread) {
            perform(a, b, callBack);
        } else {
            new Thread(new Runnable() {
                public void run() {
                    perform(a, b, callBack);
                }
            }).start();
        }

    }

    private void perform(int a, int b, AddCallBack callBack) {
        callBack.withResult(a + b);
    }
}
