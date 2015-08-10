package elevator.util

/**
 * author: TRYavasU
 * date: 07/08/2015
 */
class RandomUtil {

    final static String lexicon = "ABCDEFGHIJKLMNOPQRSTUVWXYZ12345674890"

    final static Set<String> identifiers = new HashSet<String>()

    final static Random rand = new Random()

    static int generatedRandomness(List<Integer[]> ranges) {
        int randomGenerated = rand.nextInt(ranges.sum { (it.get(1) - it.get(0)) * it.get(2) } + 1)

        int margin = 0
        for (; margin < ranges.size(); margin++) {
            List<Integer> value = ranges[margin]
            randomGenerated -= ((value.get(1) - value.get(0)) * value.get(2))
            if (randomGenerated <= 0) {
                break;
            }
        }

        def hit = ranges[margin]
        rand.nextInt(hit.get(1) - hit.get(0)) + hit.get(0)
    }

    static String randomIdentifier() {
        StringBuilder builder = new StringBuilder();
        while (builder.toString().length() == 0) {
            int length = rand.nextInt(5) + 5;
            for (int i = 0; i < length; i++) {
                builder.append(lexicon.charAt(rand.nextInt(lexicon.length())));
                if (identifiers.contains(builder.toString())) {
                    builder = new StringBuilder();
                }
            }
        }
        return builder.toString();
    }

}
