package com.alibaba.json.bvt;

import java.lang.reflect.Field;

import org.junit.Assert;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.parser.SymbolTable;

import junit.framework.TestCase;

public class OOMTest extends TestCase {
    public void test_oom() throws Exception {
        
        for (int i = 0; i < 1000 * 1000; ++i) {
            String text = "{\"" + i + "\":0}";
            JSON.parse(text);
        }
        
        Field field = SymbolTable.class.getDeclaredField("size");
        field.setAccessible(true);
        int size = field.getInt(ParserConfig.getGlobalInstance().getSymbolTable());
        Assert.assertEquals(4096, size);
    }
}
