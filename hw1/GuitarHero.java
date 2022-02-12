import java.util.HashMap;

public class GuitarHero {
    private static double CONCERT = 0.0;

    public static void main(String[] args) {
        synthesizer.GuitarString stringA = new synthesizer.GuitarString(CONCERT);
        String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
        char[] key = keyboard.toCharArray();
        HashMap<Character, Integer> keystring = new HashMap<>();
        for (int i = 0; i < key.length; i++) {
            keystring.put(key[i], i);
        }

        if (StdDraw.hasNextKeyTyped()) {
            char enterkey = StdDraw.nextKeyTyped();
            if (keystring.containsKey(enterkey)) {
                CONCERT = (double) (440 * Math.pow(2, (keystring.get(enterkey) - 24) / 12));
                stringA.pluck();
            } else {
                throw new RuntimeException("Invalid key!");
            }
        }

        StdAudio.play(stringA.sample());
        stringA.tic();


    }
}
