import static org.junit.Assert.*;
import org.junit.Test;

import javax.annotation.processing.SupportedSourceVersion;

public class TestArrayDequeGold {

    @Test
    public void testArrayDeque(){
        StudentArrayDeque<Integer> sad1 = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> sad2 = new ArrayDequeSolution<>();

        StringBuilder msg = new StringBuilder();
        int s = 0;

        for (int i = 0; i < 100; i += 1) {
            double numberBetweenZeroAndOne = StdRandom.uniform();

            if (numberBetweenZeroAndOne < 0.25) {
                sad1.addLast(i);
                sad2.addLast(i);
                s++;
                msg.append("addLast(" + i + ")\n");
                assertEquals(msg.toString(), sad2.get(s - 1),sad1.get(s - 1));

            } else if(numberBetweenZeroAndOne < 0.5) {
                sad1.addFirst(i);
                sad2.addFirst(i);
                s++;
                msg.append("addFirst(" + i + ")\n");
                assertEquals(msg.toString(), sad2.get(0),sad1.get(0));

            } else if(numberBetweenZeroAndOne < 0.75) {
                if(sad2.isEmpty()) {
                    msg.append("isEmpty()\n");
                    assertTrue(msg.toString(), sad1.isEmpty());
                    continue;
                }
                Integer x = sad2.removeFirst();
                Integer y = sad1.removeFirst();
                s--;
                msg.append("removeFirst()\n");
                assertEquals(msg.toString(), x, y);
            } else {
                if(sad2.isEmpty()) {
                    msg.append("isEmpty()\n");
                    assertTrue(msg.toString(), sad1.isEmpty());
                    continue;
                }
                Integer x = sad2.removeLast();
                Integer y = sad1.removeLast();
                s--;
                msg.append("removeLast()\n");
                assertEquals(msg.toString(), x, y);
            }

        }

    }
}
