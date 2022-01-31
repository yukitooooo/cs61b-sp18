public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        LinkedListDeque<Character> L = new LinkedListDeque<Character>();
        for (int i = 0; i < word.length(); i += 1) {
            L.addLast(word.charAt(i));
        }
        return L;
    }

    public boolean isPalindrome(String word) {
        return isPal(word);
    }

    private boolean isPal(String word) {
        Deque<Character> L = wordToDeque(word);
        if (L.size() == 0 || L.size() == 1) {
            return true;
        } else if (L.size() % 2 == 0) {
            for (int i = 0; i < L.size() / 2; i += 1) {
                char a = L.removeFirst();
                char b = L.removeLast();
                if (a != b) {
                    return false;
                }
            }
            return true;
        } else {
            for (int i = 0; i < (L.size() - 1) / 2; i += 1) {
                char a = L.removeFirst();
                char b = L.removeLast();
                if (a != b) {
                    return false;
                }
            }
            return true;
        }

    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        return isPalOffOneBy(word, cc);
    }

    private boolean isPalOffOneBy(String word, CharacterComparator cc) {
        Deque<Character> L = wordToDeque(word);
        if (L.size() == 0 || L.size() == 1) {
            return true;
        } else if (L.size() % 2 == 0) {
            for (int i = 0; i < L.size() / 2; i += 1) {
                char a = L.removeFirst();
                char b = L.removeLast();
                if (!cc.equalChars(a, b)) {
                    return false;
                }
            }
            return true;
        } else {
            for (int i = 0; i < (L.size() - 1) / 2; i += 1) {
                char a = L.removeFirst();
                char b = L.removeLast();
                if (!cc.equalChars(a, b)) {
                    return false;
                }
            }
            return true;
        }

    }

}
