package com.ekanathk.tdd.adder;

import com.ekanathk.tdd.adder.vendor.AddCallBack;
import com.ekanathk.tdd.adder.vendor.Adder;

/**
 * A sample class from Vendor that would add ints and call result in the same thread
 */
class VendorAdderDifferentThread implements Adder {


    public void add(final int a, final int b, final AddCallBack callBack) {
        new Thread(new Runnable() {
            public void run() {
                callBack.withResult(a + b);
            }
        }).start();
    }
}
