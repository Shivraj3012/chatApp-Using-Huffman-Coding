import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class HuffmanCoding {
    private Map<Character, String> huffmanCodes = new HashMap<>();
    private HuffmanNode root;

    // Build frequency map
    private Map<Character, Integer> buildFrequencyMap(String text) {
        Map<Character, Integer> freqMap = new HashMap<>();
        for (char ch : text.toCharArray()) {
            freqMap.put(ch, freqMap.getOrDefault(ch, 0) + 1);
        }
        return freqMap;
    }

    // Build Huffman Tree
    private void buildTree(String text) {
        Map<Character, Integer> freqMap = buildFrequencyMap(text);
        PriorityQueue<HuffmanNode> pq = new PriorityQueue<>(Comparator.comparingInt(node -> node.freq));

        for (Map.Entry<Character, Integer> entry : freqMap.entrySet()) {
            pq.add(new HuffmanNode(entry.getKey(), entry.getValue()));
        }

        while (pq.size() > 1) {
            HuffmanNode left = pq.poll();
            HuffmanNode right = pq.poll();
            HuffmanNode parent = new HuffmanNode('\0', left.freq + right.freq);
            parent.left = left;
            parent.right = right;
            pq.add(parent);
        }

        root = pq.poll();
        buildCodes(root, "");
    }

    // Recursively assign codes
    private void buildCodes(HuffmanNode node, String code) {
        if (node == null) return;

        if (node.left == null && node.right == null) {
            huffmanCodes.put(node.ch, code);
            return;
        }

        buildCodes(node.left, code + "0");
        buildCodes(node.right, code + "1");
    }

    // Encode text
    public String encode(String text) {
        buildTree(text);
        StringBuilder encoded = new StringBuilder();
        for (char ch : text.toCharArray()) {
            encoded.append(huffmanCodes.get(ch));
        }
        return encoded.toString();
    }

    // Decode binary string
    public String decode(String encoded) {
        StringBuilder decoded = new StringBuilder();
        HuffmanNode current = root;

        for (char bit : encoded.toCharArray()) {
            current = (bit == '0') ? current.left : current.right;

            if (current.left == null && current.right == null) {
                decoded.append(current.ch);
                current = root;
            }
        }

        return decoded.toString();
    }
}


