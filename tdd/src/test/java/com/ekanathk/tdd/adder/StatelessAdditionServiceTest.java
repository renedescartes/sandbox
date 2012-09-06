package com.ekanathk.tdd.adder;

import com.ekanathk.tdd.adder.vendor.AddCallBack;
import com.ekanathk.tdd.adder.vendor.Adder;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class StatelessAdditionServiceTest {

    @Test
    public void testSimpleAdditionDifferentThread() {
        StatelessAdditionService service = new StatelessAdditionService(new VendorAdder(false));
        assertEquals(5, service.add(2, 3));
    }

    @Test
    public void testSimpleAdditionSameThread() {
        StatelessAdditionService service = new StatelessAdditionService(new VendorAdder(true));
        assertEquals(5, service.add(2, 3));
    }

    @Test(expectedExceptions = RuntimeException.class, expectedExceptionsMessageRegExp = "Vendor addition")
    public void testSimpleAdditionNoResponseFromVendor() {
        StatelessAdditionService service = new StatelessAdditionService(new Adder() {
            @Override
            public void add(int a, int b, AddCallBack callBack) {
                //do nothing
            }
        });
        service.add(2, 3);
    }
}

