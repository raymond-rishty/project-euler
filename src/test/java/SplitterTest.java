import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by raymondrishty on 1/13/14.
 */
public class SplitterTest {
    public String splitAndRejoin(String pre) {
        return Joiner.on("/").join(Splitter.onPattern("([,.|]|BREAK)").trimResults().omitEmptyStrings().split(pre));
    }

    @Test
    public void test1() {
        assertEquals("a/b/c/d/e/f", splitAndRejoin("a,b,c.d|e BREAK f"));
        assertEquals("A/B/C/D/E/F", splitAndRejoin("A,B,C.D|E BREAK F"));
    }
}
