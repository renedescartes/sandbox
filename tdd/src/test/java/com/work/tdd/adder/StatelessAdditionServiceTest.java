package com.work.tdd.adder;

import com.work.tdd.adder.vendor.AddCallBack;
import com.work.tdd.adder.vendor.Adder;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

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

    @Test(expected = RuntimeException.class)
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

