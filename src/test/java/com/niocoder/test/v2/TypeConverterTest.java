package com.niocoder.test.v2;

import com.niocoder.beans.SimpleTypeConverter;
import com.niocoder.beans.TypeConverter;
import com.niocoder.beans.TypeMismatchException;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.fail;

/**
 * Created on 2018/11/3.
 *
 * @author zlf
 * @email i@merryyou.cn
 * @since 1.0
 */
public class TypeConverterTest {

    @Test
    public void testConvertStringToInt() {
        TypeConverter converter = new SimpleTypeConverter();
        Integer i = converter.convertIfNecessary("3",Integer.class);
        Assert.assertEquals(3,i.intValue());

        try{
            converter.convertIfNecessary("3.1",Integer.class);
        }catch (TypeMismatchException e){
            return;
        }
        fail();
    }

    @Test
    public void testConvertStringToBoolean() {
        TypeConverter  converter = new SimpleTypeConverter();
        Boolean b = converter.convertIfNecessary("true",Boolean.class);
        Assert.assertEquals(b,b.booleanValue());

        try{
            converter.convertIfNecessary("xxxxxxxx",Integer.class);
        }catch (TypeMismatchException e){
            return;
        }
        fail();
    }
}
